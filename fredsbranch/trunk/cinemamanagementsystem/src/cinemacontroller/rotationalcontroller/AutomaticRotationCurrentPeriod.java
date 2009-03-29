package cinemacontroller.rotationalcontroller;

import java.sql.ResultSet;
import java.util.Calendar;

import javax.swing.JOptionPane;

import databasecontroller.MySqlController;
import usefulmethods.Useful;

public class AutomaticRotationCurrentPeriod {
	
	private Useful use;
	
	public AutomaticRotationCurrentPeriod(){
		use = new Useful();
		Calendar dateToday = use.toCalendar(use.dateToday());
		
		if(checkContentPeriod(dateToday,getDateEndPeriod())){
			//we can modify
			startRotation(dateToday,getDateEndPeriod());
		}
		else{
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
		
	}
	
	public void getMoviesPreviousPeriod(Calendar endDate){
		
		Calendar previousPeriodDateEnd = (Calendar)endDate.clone();
		previousPeriodDateEnd = use.changeDate(previousPeriodDateEnd, -14);
		System.out.println(use.calendarToString(previousPeriodDateEnd));
		
		Calendar previousPeriodDateBegin = (Calendar)previousPeriodDateEnd.clone();
		previousPeriodDateBegin = use.changeDate(previousPeriodDateBegin, -14);
		System.out.println(use.calendarToString(previousPeriodDateBegin));
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
