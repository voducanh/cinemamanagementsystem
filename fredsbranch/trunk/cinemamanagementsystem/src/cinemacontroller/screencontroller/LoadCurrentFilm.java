package cinemacontroller.screencontroller;

import java.sql.ResultSet;
import java.util.Calendar;

import javax.swing.JOptionPane;

import databasecontroller.MySqlController;

import usefulmethods.Useful;

public class LoadCurrentFilm {
	
	private Useful use;
	
	public LoadCurrentFilm(){
		use = new Useful();
	}
	
	public void start(Calendar selectedDay, Calendar endDate){
		
		Calendar begindateSecondWeek = (Calendar)endDate.clone();
		begindateSecondWeek = use.changeDate(begindateSecondWeek, -6);
		
		Calendar endDateFirstWeek = (Calendar)begindateSecondWeek.clone();
		endDateFirstWeek = use.changeDate(endDateFirstWeek, -1);

		Calendar beginPreviousWeek = (Calendar)endDate.clone();
		beginPreviousWeek = use.changeDate(beginPreviousWeek, -20);
		
		if(selectedDay.compareTo(endDate) < 0){

			if(selectedDay.compareTo(begindateSecondWeek) < 0){
				getMoviesPreviousPeriod(endDate);
				
				getMoviesFirstWeekPeriod(beginPreviousWeek,selectedDay);
			}
			else{
				getMoviesPreviousPeriod(endDate);
				getMoviesSecondWeekPeriod(beginPreviousWeek,selectedDay,endDate);	
			}

		}
		else{
			
			begindateSecondWeek = (Calendar)endDate.clone();
			begindateSecondWeek = use.changeDate(begindateSecondWeek, -6);

			endDateFirstWeek = (Calendar)begindateSecondWeek.clone();
			endDateFirstWeek = use.changeDate(endDateFirstWeek, -1);

			beginPreviousWeek = (Calendar)endDate.clone();
			beginPreviousWeek = use.changeDate(beginPreviousWeek, -20);
			
			if(selectedDay.compareTo(begindateSecondWeek) < 0){
				getMoviesPreviousPeriod(endDate);
				
				getMoviesFirstWeekPeriod(beginPreviousWeek,selectedDay);
			}
			else{
				getMoviesPreviousPeriod(endDate);
				getMoviesSecondWeekPeriod(beginPreviousWeek,selectedDay,endDate);	
			}

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


}
