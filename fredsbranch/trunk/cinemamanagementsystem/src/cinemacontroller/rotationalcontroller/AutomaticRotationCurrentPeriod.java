package cinemacontroller.rotationalcontroller;

import java.sql.ResultSet;
import java.util.Calendar;

import javax.swing.JOptionPane;

import databasecontroller.MySqlController;
import usefulmethods.Useful;

public class AutomaticRotationCurrentPeriod implements Runnable {
	
	private Useful use;
	private AutomaticRotation auto;
	private int startValue = 0;

	public AutomaticRotationCurrentPeriod(AutomaticRotation auto){
		this.auto = auto;

	}

	public void run() {

		auto.getJProgressBar().setIndeterminate(true);
				
		use = new Useful();
		Calendar dateToday = use.toCalendar(use.getDateToday());
				
		if(checkContentPeriod(dateToday,use.getDateEndPeriod())){

			startRotation(dateToday,use.getDateEndPeriod());
			auto.getJProgressBar().setIndeterminate(false);
			auto.dispose();
			JOptionPane.showMessageDialog(null, "Automatic Rotation runned sucessfully.", "Automatic Rotation", JOptionPane.INFORMATION_MESSAGE);

		}
		else{
				auto.getJProgressBar().setIndeterminate(false);
				auto.dispose();
				JOptionPane.showMessageDialog(null, "This period has already been generated.", "Period Generated", JOptionPane.WARNING_MESSAGE);
					
		}
	}
	
	public void startRotation(Calendar beginDate, Calendar endDate){
		
        try {

        	MySqlController connection = MySqlController.getInstance();

        	ResultSet r = connection.getData("SELECT VALUE FROM INDEXES WHERE NAME='START_VALUE'");
        	
        	if(r.next()){
        		startValue = r.getInt(1);
        	}

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Unable to connect to MySQL database.\n" + e, "Database Error", JOptionPane.WARNING_MESSAGE);
        }

		Calendar begindateSecondWeek = (Calendar)endDate.clone();
		begindateSecondWeek = use.changeDate(begindateSecondWeek, -6);
		
		Calendar endDateFirstWeek = (Calendar)begindateSecondWeek.clone();
		endDateFirstWeek = use.changeDate(endDateFirstWeek, -1);
		//System.out.println(use.calendarToString(endDate));
		//System.out.println(use.calendarToString(begindateSecondWeek));
		
		Calendar beginPreviousWeek = (Calendar)endDate.clone();
		beginPreviousWeek = use.changeDate(beginPreviousWeek, -20);
		
		Calendar specialDay = (Calendar)beginDate.clone();
		specialDay = use.changeDate(specialDay, 1);

		if(beginDate.compareTo(begindateSecondWeek) < 0){

			getMoviesPreviousPeriod(endDate);
			
			getMoviesFirstWeekPeriod(beginPreviousWeek,beginDate);
			screeningFilms(specialDay,endDateFirstWeek,0);
				
			getMoviesPreviousPeriod(endDate);
			getMoviesSecondWeekPeriod(beginPreviousWeek,beginDate,endDate);
			screeningFilms(begindateSecondWeek,endDate,1);
		}
		else{
			getMoviesPreviousPeriod(endDate);
			getMoviesSecondWeekPeriod(beginPreviousWeek,beginDate,endDate);
			screeningFilms(specialDay,endDate,1);
		}


	}
	
public void getMoviesPreviousPeriod(Calendar endDate){
		
		Calendar previousPeriodDateEnd = (Calendar)endDate.clone();
		previousPeriodDateEnd = use.changeDate(previousPeriodDateEnd, -14);
		//System.out.println(use.calendarToString(previousPeriodDateEnd));
		
		Calendar previousPeriodDateBegin = (Calendar)previousPeriodDateEnd.clone();
		previousPeriodDateBegin = use.changeDate(previousPeriodDateBegin, -6);
		//System.out.println(use.calendarToString(previousPeriodDateBegin));
		
        try {

        	MySqlController connection = MySqlController.getInstance();
        	
        	connection.putData("CREATE TABLE IF NOT EXISTS TEMPEXPECTEDBOOKING(ID_MOVIE INT(6) NOT NULL, EXPECTED_BOOKING INT(5) , WEEK INT(1) NOT NULL, PRIMARY KEY(ID_MOVIE))");
        	connection.putData("TRUNCATE TABLE TEMPEXPECTEDBOOKING");
        	
        	//System.out.println("SELECT DISTINCT(ID_MOVIE) FROM TIMETABLES WHERE DAY BETWEEN '"+use.calendarToString(previousPeriodDateBegin)+"' AND '"+use.calendarToString(previousPeriodDateEnd)+"'");
        	ResultSet r = connection.getData("SELECT DISTINCT(ID_MOVIE) FROM TIMETABLES WHERE DAY BETWEEN '"+use.calendarToString(previousPeriodDateBegin)+"' AND '"+use.calendarToString(previousPeriodDateEnd)+"'");

        	while (r.next()) {
        		connection.putData("INSERT INTO TEMPEXPECTEDBOOKING (ID_MOVIE,WEEK) VALUES ('"+r.getString(1)+"',0)");

			}

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Unable to connect to MySQL database.\n" + e, "Database Error", JOptionPane.WARNING_MESSAGE);
        }
        
	}
	
	
	public void getMoviesFirstWeekPeriod(Calendar beginPreviousWeek, Calendar endDate){
		
		//System.out.println(use.calendarToString(dateToday));
		//System.out.println(use.calendarToString(endDate));
		
		Calendar beginDateFirstWeek = (Calendar)beginPreviousWeek.clone();
		//System.out.println(use.calendarToString(beginDateFirstWeek));

        try {
        	//System.out.println("SELECT ID_MOVIE FROM MOVIES WHERE RELEASE_DATE BETWEEN '"+use.calendarToString(beginDateFirstWeek)+"' AND '"+use.calendarToString(endDate)+"'");
        	MySqlController connection = MySqlController.getInstance();

        	ResultSet r = connection.getData("SELECT ID_MOVIE FROM MOVIES WHERE RELEASE_DATE BETWEEN '"+use.calendarToString(beginDateFirstWeek)+"' AND '"+use.calendarToString(endDate)+"'");

        	while (r.next()) {
        		
        		ResultSet rbis = connection.getData("SELECT ID_MOVIE FROM TEMPEXPECTEDBOOKING WHERE ID_MOVIE='"+r.getString(1)+"'");
        		if(!rbis.next()){
        			//System.out.println("INSERT INTO TEMPEXPECTEDBOOKING (ID_MOVIE,WEEK) VALUES ('"+r.getString(1)+"',1)");
        			connection.putData("INSERT INTO TEMPEXPECTEDBOOKING (ID_MOVIE,WEEK) VALUES ('"+r.getString(1)+"',1)");
        		}
        		
			}
        	
        	ResultSet r1 = connection.getData("SELECT COUNT(*) FROM TEMPEXPECTEDBOOKING");
        	
        	while (r1.next()) {
        		int count = r1.getInt(1);
        		
        		Calendar checkDateEnd = (Calendar)beginDateFirstWeek.clone();
        		checkDateEnd = use.changeDate(beginDateFirstWeek, -1);
        		
        		Calendar checkDateBegin = (Calendar)checkDateEnd.clone();;
        		checkDateBegin = use.changeDate(checkDateBegin, -6);
        		
        		//System.out.println(use.calendarToString(checkDateEnd));
        		//System.out.println(use.calendarToString(checkDateBegin));
        		
        		ResultSet r2 = connection.getData("SELECT VALUE FROM INDEXES WHERE NAME='MINIMUM_FILM'");
        		while (r2.next()) {
            		while (count < r2.getInt(1)){
            			//System.out.println("SELECT ID_MOVIE FROM MOVIES WHERE RELEASE_DATE BETWEEN '"+use.calendarToString(checkDateBegin)+"' AND '"+use.calendarToString(checkDateEnd)+"'");
            			ResultSet r3 = connection.getData("SELECT ID_MOVIE FROM MOVIES WHERE RELEASE_DATE BETWEEN '"+use.calendarToString(checkDateBegin)+"' AND '"+use.calendarToString(checkDateEnd)+"'");
            			while (r3.next()) {
            				
                    		ResultSet rbis = connection.getData("SELECT ID_MOVIE FROM TEMPEXPECTEDBOOKING WHERE ID_MOVIE='"+r3.getString(1)+"'");
                    		if(!rbis.next()){
                    			connection.putData("INSERT INTO TEMPEXPECTEDBOOKING VALUES ('"+r3.getString(1)+"',0,3)");
                    			count++;
                    		}
            			}
            			
            			checkDateEnd = use.changeDate(beginDateFirstWeek, -7);
            			checkDateBegin = use.changeDate(checkDateBegin, -7);
    	
            		}
        			
        		}
			}

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Unable to connect to MySQL database.\n" + e, "Database Error", JOptionPane.WARNING_MESSAGE);
        }
	}
	
	
	public void getMoviesSecondWeekPeriod(Calendar beginPreviousWeek, Calendar endDatePreviousWeek, Calendar endDate){
		
		Calendar beginDateClone = (Calendar)beginPreviousWeek.clone();
		beginDateClone = use.changeDate(beginDateClone, 1);
		
		Calendar beginFirstWeek = (Calendar)endDate.clone();
		beginFirstWeek = use.changeDate(beginFirstWeek, -13);
		
		Calendar endFirstWeek = (Calendar)endDate.clone();
		endFirstWeek = use.changeDate(endFirstWeek, -7);
		
		
		getMoviesFirstWeekPeriod(beginPreviousWeek,endDatePreviousWeek);
		
        try {
        	//System.out.println("SELECT ID_MOVIE FROM MOVIES WHERE RELEASE_DATE BETWEEN '"+use.calendarToString(beginDateFirstWeek)+"' AND '"+use.calendarToString(endDate)+"'");
        	MySqlController connection = MySqlController.getInstance();

        	ResultSet r = connection.getData("SELECT ID_MOVIE FROM MOVIES WHERE RELEASE_DATE BETWEEN '"+use.calendarToString(beginFirstWeek)+"' AND '"+use.calendarToString(endFirstWeek)+"'");

        	while (r.next()) {
        		
        		ResultSet rbis = connection.getData("SELECT ID_MOVIE FROM TEMPEXPECTEDBOOKING WHERE ID_MOVIE='"+r.getString(1)+"'");
        		if(!rbis.next()){
        			//System.out.println("INSERT INTO TEMPEXPECTEDBOOKING (ID_MOVIE,WEEK) VALUES ('"+r.getString(1)+"',2)");
        			connection.putData("INSERT INTO TEMPEXPECTEDBOOKING (ID_MOVIE,WEEK) VALUES ('"+r.getString(1)+"',2)");
        		}
        		
			}
        	
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Unable to connect to MySQL database.\n" + e, "Database Error", JOptionPane.WARNING_MESSAGE);
        }
		
		
		
		
	}
	
	public void calculationExpectedBooking(int week){
		
        try {

        	MySqlController connection = MySqlController.getInstance();
        	
        	//for week 0
        	ResultSet r = connection.getData("SELECT ID_MOVIE FROM TEMPEXPECTEDBOOKING WHERE WEEK='0'");
        	
        	ResultSet r7 = connection.getData("SELECT VALUE FROM INDEXES WHERE NAME='PREVIOUS_PERIOD_MALUS'");
        	float malus = 0.0f;
    		if (r7.next()) {
    			malus = r7.getFloat(1);
			}
        	
        	while (r.next()) {
        		//System.out.println("SELECT B.INDEX,T.INDEX,E.INDEX FROM MOVIES M,BBFC_RATING B,TYPES T,EXPECTED_AUDIENCES E WHERE M.ID_MOVIE='"+r.getString(1)+"' AND M.ID_BBFC=B.ID_BBFC AND M.ID_TYPE=T.ID_TYPE AND M.ID_EXPECTED_AUDIENCE=E.ID_EXPECTED_AUDIENCE");

        		ResultSet r2 = connection.getData("SELECT B.INDEX,T.INDEX,E.INDEX FROM MOVIES M,BBFC_RATING B,TYPES T,EXPECTED_AUDIENCES E WHERE M.ID_MOVIE='"+r.getString(1)+"' AND M.ID_BBFC=B.ID_BBFC AND M.ID_TYPE=T.ID_TYPE AND M.ID_EXPECTED_AUDIENCE=E.ID_EXPECTED_AUDIENCE");
            	float tempExpectedBooking = startValue;
        		
        		while (r2.next()) {
        			tempExpectedBooking = tempExpectedBooking*r2.getFloat(1)*r2.getFloat(2)*r2.getFloat(3)*(malus+(malus*week));
        			//System.out.println(tempExpectedBooking);
        			connection.putData("UPDATE TEMPEXPECTEDBOOKING SET EXPECTED_BOOKING='"+tempExpectedBooking+"' WHERE ID_MOVIE='"+r.getString(1)+"'");
    			}
			}
        	
        	
        	//for week 1
        	r = connection.getData("SELECT ID_MOVIE FROM TEMPEXPECTEDBOOKING WHERE WEEK='1'");
        	while (r.next()) {
        		
        		ResultSet r2 = connection.getData("SELECT B.INDEX,T.INDEX,E.INDEX FROM MOVIES M,BBFC_RATING B,TYPES T,EXPECTED_AUDIENCES E WHERE M.ID_MOVIE='"+r.getString(1)+"' AND M.ID_BBFC=B.ID_BBFC AND M.ID_TYPE=T.ID_TYPE AND M.ID_EXPECTED_AUDIENCE=E.ID_EXPECTED_AUDIENCE");
            	float tempExpectedBooking = startValue;
        		
        		while (r2.next()) {
        			if(week == 0){
        				tempExpectedBooking = tempExpectedBooking*r2.getFloat(1)*r2.getFloat(2)*r2.getFloat(3);
        			}
        			else{
        				tempExpectedBooking = tempExpectedBooking*r2.getFloat(1)*r2.getFloat(2)*r2.getFloat(3)*malus;
        			}
        			
        			//System.out.println(tempExpectedBooking);
        			connection.putData("UPDATE TEMPEXPECTEDBOOKING SET EXPECTED_BOOKING='"+tempExpectedBooking+"' WHERE ID_MOVIE='"+r.getString(1)+"'");
    			}
			}
        	
        	
        	//for week 2
        	r = connection.getData("SELECT ID_MOVIE FROM TEMPEXPECTEDBOOKING WHERE WEEK='2'");
        	while (r.next()) {
        		
        		ResultSet r2 = connection.getData("SELECT B.INDEX,T.INDEX,E.INDEX FROM MOVIES M,BBFC_RATING B,TYPES T,EXPECTED_AUDIENCES E WHERE M.ID_MOVIE='"+r.getString(1)+"' AND M.ID_BBFC=B.ID_BBFC AND M.ID_TYPE=T.ID_TYPE AND M.ID_EXPECTED_AUDIENCE=E.ID_EXPECTED_AUDIENCE");
            	float tempExpectedBooking = startValue;
        		
        		while (r2.next()) {
        			
        			tempExpectedBooking = tempExpectedBooking*r2.getFloat(1)*r2.getFloat(2)*r2.getFloat(3);

        			//System.out.println(tempExpectedBooking);
        			connection.putData("UPDATE TEMPEXPECTEDBOOKING SET EXPECTED_BOOKING='"+tempExpectedBooking+"' WHERE ID_MOVIE='"+r.getString(1)+"'");
    			}
			}
        	
        	//for week 3
        	r = connection.getData("SELECT ID_MOVIE FROM TEMPEXPECTEDBOOKING WHERE WEEK='3'");
        	float malus3 = 0.0f;
        	while (r.next()) {
        		//System.out.println("SELECT B.INDEX,T.INDEX,E.INDEX FROM MOVIES M,BBFC_RATING B,TYPES T,EXPECTED_AUDIENCES E WHERE M.ID_MOVIE='"+r.getString(1)+"' AND M.ID_BBFC=B.ID_BBFC AND M.ID_TYPE=T.ID_TYPE AND M.ID_EXPECTED_AUDIENCE=E.ID_EXPECTED_AUDIENCE");

            	ResultSet r1 = connection.getData("SELECT VALUE FROM INDEXES WHERE NAME='ADDITIONAL_MOVIE_MALUS'");
        		while (r1.next()) {
        			malus3 = r1.getFloat(1);
    			}
        		
        		ResultSet r2 = connection.getData("SELECT B.INDEX,T.INDEX,E.INDEX FROM MOVIES M,BBFC_RATING B,TYPES T,EXPECTED_AUDIENCES E WHERE M.ID_MOVIE='"+r.getString(1)+"' AND M.ID_BBFC=B.ID_BBFC AND M.ID_TYPE=T.ID_TYPE AND M.ID_EXPECTED_AUDIENCE=E.ID_EXPECTED_AUDIENCE");
            	float tempExpectedBooking = startValue;
        		
        		while (r2.next()) {
        			tempExpectedBooking = tempExpectedBooking*r2.getFloat(1)*r2.getFloat(2)*r2.getFloat(3)*malus3;
        			//System.out.println(tempExpectedBooking);
        			connection.putData("UPDATE TEMPEXPECTEDBOOKING SET EXPECTED_BOOKING='"+tempExpectedBooking+"' WHERE ID_MOVIE='"+r.getString(1)+"'");
    			}
			}
        	

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Unable to connect to MySQL database.\n" + e, "Database Error", JOptionPane.WARNING_MESSAGE);
        }
        
	}
	
	public void screeningFilms(Calendar dateToday, Calendar endDate, int week){

		Calendar workingDate = (Calendar)dateToday.clone();
		workingDate = use.changeDate(workingDate, -1);

		int minCountScreenUsed = 0;

		//System.out.println(use.calendarToString(workingDate));
		//System.out.println(use.calendarToString(endDateFirstWeek));
		try{
			MySqlController connection = MySqlController.getInstance();
			
			connection.putData("CREATE TABLE IF NOT EXISTS TEMPTIMETABLES (ID_SCREEN INT(2) NOT NULL,ID_MOVIE INT(6),DAY DATE NOT NULL,EXPECTED_BOOKING INT(4),NB_SEATS_BOOKED INT(4),PRIMARY KEY (ID_SCREEN,DAY))");

		 	ResultSet r = connection.getData("SELECT VALUE FROM INDEXES WHERE NAME='MINIMUM_SCREEN_USE'");
		 	if(r.next()){
		 		minCountScreenUsed = r.getInt(1);
		 	}
			
		while(!workingDate.equals(endDate)){
			workingDate = use.changeDate(workingDate, 1);
			//System.out.println(use.calendarToString(workingDate));
			
			calculationExpectedBooking(week);
			int countScreenUsed=0;
			
			ResultSet r1 = connection.getData("SELECT * FROM SCREENS ORDER BY NB_SEATS DESC");
			while(r1.next()){
				ResultSet r2 = connection.getData("SELECT * FROM TEMPEXPECTEDBOOKING ORDER BY EXPECTED_BOOKING DESC");
				 
				if(r2.next()){
					int bestExpectedBooking = r2.getInt(2);
					ResultSet r3 = connection.getData("SELECT * FROM TEMPCLOSEDSCREENS WHERE ID_SCREEN='"+r1.getInt(1)+"' AND DAY='"+use.calendarToString(workingDate)+"'");
					if(r3.next()){
						connection.putData("INSERT INTO TEMPTIMETABLES (ID_SCREEN,ID_MOVIE,DAY) VALUES ('"+r1.getInt(1)+"','0','"+use.calendarToString(workingDate)+"')");
					}
					else{
						if(bestExpectedBooking <= 0){
							if(countScreenUsed < minCountScreenUsed){
								//System.out.println("INSERT INTO TEMPTIMETABLES VALUES ('"+r1.getString(1)+"','"+r2.getString(1)+"','"+use.calendarToString(workingDate)+"','0','0')");
								connection.putData("INSERT INTO TEMPTIMETABLES VALUES ('"+r1.getString(1)+"','"+r2.getString(1)+"','"+use.calendarToString(workingDate)+"','0','0')");
								
								bestExpectedBooking = bestExpectedBooking-r1.getInt(2);
								connection.putData("UPDATE TEMPEXPECTEDBOOKING SET EXPECTED_BOOKING='"+bestExpectedBooking+"' WHERE ID_MOVIE='"+r2.getInt(1)+"'");
							}
							else{
								break;
							}
						}
						else{
							bestExpectedBooking -= r1.getInt(2);
							
							int nbShows = 0;
			        		nbShows = use.numberShows(r2.getString(1),workingDate);
			        		
							if(bestExpectedBooking > 0){
								//System.out.println("INSERT INTO TEMPTIMETABLES VALUES ('"+r1.getString(1)+"','"+r2.getString(1)+"','"+use.calendarToString(workingDate)+"','"+r1.getInt(2)*nbShows+"','0')");
								connection.putData("INSERT INTO TEMPTIMETABLES VALUES ('"+r1.getString(1)+"','"+r2.getString(1)+"','"+use.calendarToString(workingDate)+"','"+r1.getInt(2)*nbShows+"','0')");
							}
							else{
								//System.out.println("INSERT INTO TEMPTIMETABLES VALUES ('"+r1.getString(1)+"','"+r2.getString(1)+"','"+use.calendarToString(workingDate)+"','"+r2.getInt(2)*nbShows+"','0')");
								connection.putData("INSERT INTO TEMPTIMETABLES VALUES ('"+r1.getString(1)+"','"+r2.getString(1)+"','"+use.calendarToString(workingDate)+"','"+r2.getInt(2)*nbShows+"','0')");
							}
							//System.out.println("UPDATE TEMPEXPECTEDBOOKING SET EXPECTED_BOOKING='"+bestExpectedBooking+"' WHERE ID_MOVIE='"+r2.getInt(1)+"'");
							connection.putData("UPDATE TEMPEXPECTEDBOOKING SET EXPECTED_BOOKING='"+bestExpectedBooking+"' WHERE ID_MOVIE='"+r2.getInt(1)+"'");
						}
					}


				}
				
			}
			
			connection.putData("INSERT TIMETABLES (ID_SCREEN,ID_MOVIE,DAY,EXPECTED_BOOKING,NB_SEATS_BOOKED) SELECT ID_SCREEN,ID_MOVIE,DAY,EXPECTED_BOOKING,NB_SEATS_BOOKED FROM TEMPTIMETABLES");
			connection.putData("TRUNCATE TABLE TEMPTIMETABLES");

		}
		
		}
		catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Unable to connect to MySQL database.\n" + e, "Database Error", JOptionPane.WARNING_MESSAGE);
        }
	}
		
	public Boolean checkContentPeriod(Calendar dateToday, Calendar dateEndPeriod){
		
		boolean content = false;
		
        try {

        	MySqlController connection = MySqlController.getInstance();

        	ResultSet r = connection.getData("SELECT * FROM TIMETABLES WHERE DAY BETWEEN '"+use.calendarToString(dateToday)+"' AND '"+use.calendarToString(dateEndPeriod)+"'");
        	
        	if(!r.next()) {
        		
        		content = true;
			}

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Unable to connect to MySQL database.\n" + e, "Database Error", JOptionPane.WARNING_MESSAGE);
        }
        
        return content;
	}





}
