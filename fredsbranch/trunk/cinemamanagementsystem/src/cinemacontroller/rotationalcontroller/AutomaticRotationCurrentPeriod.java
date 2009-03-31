package cinemacontroller.rotationalcontroller;

import java.sql.ResultSet;
import java.util.Calendar;

import javax.swing.JOptionPane;

import databasecontroller.MySqlController;
import usefulmethods.Useful;

public class AutomaticRotationCurrentPeriod implements Runnable {
	
	private Useful use;
	private AutomaticRotation auto;
	
	public AutomaticRotationCurrentPeriod(AutomaticRotation auto){
		this.auto = auto;
	}
	
	public void run() {
		auto.getJProgressBar().setIndeterminate(true);
		
		use = new Useful();
		Calendar dateToday = use.toCalendar(use.getDateToday());
		
		if(checkContentPeriod(dateToday,getDateEndPeriod())){
			//we can modify
			//for(long i=0;i<1000000000;i++){
				
			//}
			startRotation(dateToday,getDateEndPeriod());
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

		Calendar begindateSecondWeek = (Calendar)endDate.clone();
		begindateSecondWeek = use.changeDate(begindateSecondWeek, -6);
		
		//System.out.println(use.calendarToString(endDate));
		//System.out.println(use.calendarToString(begindateSecondWeek));
		
		//get all films in the previous period
		getMoviesPreviousPeriod(endDate);
		
		getMoviesFirstWeekPeriod(beginDate,endDate);

		screeningFilms2(beginDate,begindateSecondWeek);
		
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
        	
        	ResultSet r = connection.getData("SELECT DISTINCT(ID_MOVIE) FROM TIMETABLES WHERE DAY BETWEEN '"+use.calendarToString(previousPeriodDateBegin)+"' AND '"+use.calendarToString(previousPeriodDateEnd)+"'");

        	while (r.next()) {
        		connection.putData("INSERT INTO TEMPEXPECTEDBOOKING (ID_MOVIE,WEEK) VALUES ('"+r.getString(1)+"',0)");

			}

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Unable to connect to MySQL database.\n" + e, "Database Error", JOptionPane.WARNING_MESSAGE);
        }
        
	}
	
	
	public void getMoviesFirstWeekPeriod(Calendar dateToday, Calendar endDate){
		
		//System.out.println(use.calendarToString(dateToday));
		//System.out.println(use.calendarToString(endDate));
		
		Calendar beginDateFirstWeek = (Calendar)endDate.clone();
		beginDateFirstWeek = use.changeDate(beginDateFirstWeek, -20);
		//System.out.println(use.calendarToString(beginDateFirstWeek));
		
		
        try {
        	//System.out.println("SELECT ID_MOVIE FROM MOVIES WHERE RELEASE_DATE BETWEEN '"+use.calendarToString(beginDateFirstWeek)+"' AND '"+use.calendarToString(dateToday)+"'");
        	MySqlController connection = MySqlController.getInstance();
        
        	ResultSet r = connection.getData("SELECT ID_MOVIE FROM MOVIES WHERE RELEASE_DATE BETWEEN '"+use.calendarToString(beginDateFirstWeek)+"' AND '"+use.calendarToString(dateToday)+"'");

        	while (r.next()) {
        		
        		ResultSet rbis = connection.getData("SELECT ID_MOVIE FROM TEMPEXPECTEDBOOKING WHERE ID_MOVIE='"+r.getString(1)+"'");
        		if(!rbis.next()){
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
	
	
	public void calculationExpectedBooking(){
		
        try {

        	MySqlController connection = MySqlController.getInstance();
        	
        	//for week 0
        	ResultSet r = connection.getData("SELECT ID_MOVIE FROM TEMPEXPECTEDBOOKING WHERE WEEK='0'");
        	float malus = 0.0f;
        	while (r.next()) {
        		//System.out.println("SELECT B.INDEX,T.INDEX,E.INDEX FROM MOVIES M,BBFC_RATING B,TYPES T,EXPECTED_AUDIENCES E WHERE M.ID_MOVIE='"+r.getString(1)+"' AND M.ID_BBFC=B.ID_BBFC AND M.ID_TYPE=T.ID_TYPE AND M.ID_EXPECTED_AUDIENCE=E.ID_EXPECTED_AUDIENCE");

            	ResultSet r1 = connection.getData("SELECT VALUE FROM INDEXES WHERE NAME='PREVIOUS_PERIOD_MALUS'");
        		while (r1.next()) {
        			malus = r1.getFloat(1);
    			}
        		
        		ResultSet r2 = connection.getData("SELECT B.INDEX,T.INDEX,E.INDEX FROM MOVIES M,BBFC_RATING B,TYPES T,EXPECTED_AUDIENCES E WHERE M.ID_MOVIE='"+r.getString(1)+"' AND M.ID_BBFC=B.ID_BBFC AND M.ID_TYPE=T.ID_TYPE AND M.ID_EXPECTED_AUDIENCE=E.ID_EXPECTED_AUDIENCE");
            	float tempExpectedBooking = 400f;
        		
        		while (r2.next()) {
        			tempExpectedBooking = tempExpectedBooking*r2.getFloat(1)*r2.getFloat(2)*r2.getFloat(3)*malus;
        			//System.out.println(tempExpectedBooking);
        			connection.putData("UPDATE TEMPEXPECTEDBOOKING SET EXPECTED_BOOKING='"+tempExpectedBooking+"' WHERE ID_MOVIE='"+r.getString(1)+"'");
    			}
			}
        	
        	
        	//for week 1
        	r = connection.getData("SELECT ID_MOVIE FROM TEMPEXPECTEDBOOKING WHERE WEEK='1'");
        	while (r.next()) {
        		
        		ResultSet r2 = connection.getData("SELECT B.INDEX,T.INDEX,E.INDEX FROM MOVIES M,BBFC_RATING B,TYPES T,EXPECTED_AUDIENCES E WHERE M.ID_MOVIE='"+r.getString(1)+"' AND M.ID_BBFC=B.ID_BBFC AND M.ID_TYPE=T.ID_TYPE AND M.ID_EXPECTED_AUDIENCE=E.ID_EXPECTED_AUDIENCE");
            	float tempExpectedBooking = 400f;
        		
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
            	float tempExpectedBooking = 400f;
        		
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
	
	public void screeningFilms2(Calendar dateToday, Calendar begindateSecondWeek){

		Calendar workingDate = (Calendar)dateToday.clone();
		workingDate = use.changeDate(workingDate, -1);
		Calendar endDateFirstWeek = (Calendar)begindateSecondWeek.clone();
		endDateFirstWeek = use.changeDate(endDateFirstWeek, -1);
		
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
			
		while(!workingDate.equals(endDateFirstWeek)){
			workingDate = use.changeDate(workingDate, 1);
			//System.out.println(use.calendarToString(workingDate));
			
			calculationExpectedBooking();
			int countScreenUsed=0;
			
			ResultSet r1 = connection.getData("SELECT * FROM SCREENS ORDER BY NB_SEATS DESC");
			while(r1.next()){
				ResultSet r2 = connection.getData("SELECT * FROM TEMPEXPECTEDBOOKING ORDER BY EXPECTED_BOOKING DESC");
				 
				if(r2.next()){
					int bestExpectedBooking = r2.getInt(2);
					ResultSet r3 = connection.getData("SELECT * FROM TEMPCLOSEDSCREENS WHERE ID_SCREEN='"+r1.getInt(1)+"' AND DAY='"+use.calendarToString(workingDate)+"'");
					if(r3.next()){
						connection.putData("INSERT INTO TEMPTIMETABLES (ID_SCREEN,DAY) VALUES ('"+r1.getInt(1)+"','"+use.calendarToString(workingDate)+"')");
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

	public Calendar getDateEndPeriod(){
		
		String dateEnd = "";
		Calendar dateEndCal;
		
        try {

        	MySqlController connection = MySqlController.getInstance();

        	ResultSet r = connection.getData("SELECT END_DATE FROM PERIOD");
        	
        	
        	while (r.next()) {
        		
        		dateEnd = r.getString(1);
			}

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Unable to connect to MySQL database.\n" + e, "Database Error", JOptionPane.WARNING_MESSAGE);
        }
        
        dateEndCal = use.toCalendar(dateEnd);
        
        return dateEndCal;
	}



}
