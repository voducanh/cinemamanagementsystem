package cinemacontroller.gui.timetablecontrol;

import java.awt.Color;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;
import javax.swing.JTextPane;

import usefulmethods.Hour;
import usefulmethods.Useful;

import databasecontroller.MySqlController;

public class TimetableController {
	
	private static final int startX = 3;
	private static final int startY = 30;
	private static final int columnWidth = 87;
	private static final int hourPx = 41;
	private int x = startX;
	private int y = startY;
	
	private Useful use;
	private ArrayList<JTextPane> shows = new ArrayList<JTextPane>();
	
	public TimetableController(){
		use = new Useful();
	}
	
	public void goNextScreen(){
		x += columnWidth;
		y = startY;
	}
	
	public void goNewWeek(){
		x = startX;
		y = startY;
	}
	
	public void addClosedScreen(){
		
        shows.add(new JTextPane());
        shows.get(shows.size()-1).setEditable(false);
        shows.get(shows.size()-1).setBounds(x, y, 80, 497);
        shows.get(shows.size()-1).setBackground(new Color(224,224,224));
        shows.get(shows.size()-1).setText("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n Closed Screen");
	}
	
	public void addOpenScreen(int nbShows, Hour runningTime, int idMovie, int expectedBooking, int nbSeatsBooked, Hour pauseTime,Hour startHour){
		
		String color = "";
		String nameType = "";
		String nameMovie = "";
		String nameExpectedAudience = "";
		String nameBBFC = "";
		
		int red = 0;
		int green = 0;
		int blue = 0;
		
		int height = (int)(runningTime.getHour()*hourPx+runningTime.getMins()*hourPx/60);
		int pause = (int)(pauseTime.getHour()*hourPx+pauseTime.getMins()*hourPx/60);
		
		String runningTimeMins = "";
		
		try {

        	MySqlController connection = MySqlController.getInstance();

        	ResultSet r = connection.getData("SELECT * FROM MOVIES WHERE ID_MOVIE='"+idMovie+"'");
        	
        	if(r.next()){
        		nameMovie = r.getString(3);
        		ResultSet r1 = connection.getData("SELECT * FROM TYPES WHERE ID_TYPE='"+r.getInt(2)+"'");
        		
        		if(r1.next()){
        			nameType = r1.getString(2);
        			color = r1.getString(3);
        			
        			red = new Integer(color.substring(0, color.indexOf(",")));
        			color = color.substring(color.indexOf(",")+1, color.length());
        			
        			green = new Integer(color.substring(0, color.indexOf(",")));
        			color = color.substring(color.indexOf(",")+1, color.length());
        			
        			blue = new Integer(color);
        			
            	}
        		
        		ResultSet r2 = connection.getData("SELECT NAME FROM BBFC_RATING WHERE ID_BBFC='"+r.getInt(4)+"'");
        		
        		if(r2.next()){
        			nameBBFC = r2.getString(1);
            	}
        		
        		ResultSet r3 = connection.getData("SELECT NAME FROM EXPECTED_AUDIENCES WHERE ID_EXPECTED_AUDIENCE='"+r.getInt(6)+"'");
        		
        		if(r3.next()){
        			nameExpectedAudience = r3.getString(1);
            	}

        		Hour tabTime = startHour;
        		
        		for(int i=0;i<nbShows;i++){
        		
        			String startHours = new String(tabTime.getHour()+":");
        			
        			if(tabTime.getMins() < 10){
        				startHours = startHours+"0";
        			}
        			
        			startHours += tabTime.getMins();

        			tabTime = tabTime.addHours(runningTime);
        			
        			String endHours = tabTime.getHour()+":";
        			
        			if(tabTime.getMins() < 10){
        				endHours = endHours+"0";
        			}
        			
        			if(runningTime.getMins() < 10){
        				runningTimeMins = "0"+runningTime.getMins();
        			}
        			else{
        				runningTimeMins = new Integer(runningTime.getMins()).toString();
        			}
        			
        			endHours += tabTime.getMins();
        			
        			tabTime = tabTime.addHours(pauseTime);
        				
        			shows.add(new JTextPane());
                    shows.get(shows.size()-1).setEditable(false);
                    shows.get(shows.size()-1).setBounds(x, y, 80, height);
                    shows.get(shows.size()-1).setBackground(new Color(red,green,blue));
                    shows.get(shows.size()-1).setText(nameMovie);
                    shows.get(shows.size()-1).setToolTipText("<html>Title: "+nameMovie+"<br/><br/>Start time: "+startHours+"<br/>End Time: "+endHours+"<br/>Running Time: "+runningTime.getHour()+":"+runningTimeMins+"<br/><br/>Expected Audience: "+nameExpectedAudience+"<br/>Type: "+nameType+"<br/>BBFC: "+nameBBFC+"<br/><br/>Expected Booking per Day: "+expectedBooking+"<br/>Seats booked per Day: "+nbSeatsBooked+"</html>");
                    
                    y += height+pause;   
        		}

        	}

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Unable to connect to MySQL database.\n" + e, "Database Error", JOptionPane.WARNING_MESSAGE);
        }

	}
	
	public ArrayList<String> fillComboBoxWeekRotation(){
		
		ArrayList<String> content = new ArrayList<String>();
		Calendar endPeriod = null;
		Calendar beginWeek;
		Calendar endWeek;
		
		try {

        	MySqlController connection = MySqlController.getInstance();

        	ResultSet r = connection.getData("SELECT END_DATE FROM PERIOD");
        	
        	if(r.next()){
        		endPeriod = use.toCalendar(r.getString(1));
        		//System.out.println(use.calendarToString(endPeriod));
        	}
        	
        	beginWeek = (Calendar)endPeriod.clone();
        	beginWeek = use.changeDate(beginWeek, -13);
        	//System.out.println(use.calendarToString(beginWeek));
        	
        	endWeek = (Calendar)endPeriod.clone();
        	endWeek = use.changeDate(endWeek, -7);
        	//System.out.println(use.calendarToString(endWeek));
        	
        	content.add(use.calendarToString(beginWeek)+" to "+use.calendarToString(endWeek));
        	
        	for(int i=0;i<3;i++){

            	beginWeek = use.changeDate(beginWeek, 7);
            	//System.out.println(use.calendarToString(beginWeek));
            	
            	endWeek = use.changeDate(endWeek, 7);
            	//System.out.println(use.calendarToString(endWeek));
            	
            	content.add(use.calendarToString(beginWeek)+" to "+use.calendarToString(endWeek));
        		
        	}
       

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Unable to connect to MySQL database.\n" + e, "Database Error", JOptionPane.WARNING_MESSAGE);
        }
        
        return content;
		
	}

	
	public ArrayList<String> fillComboBoxWeekHistoricalRotation(){
		
		ArrayList<String> content = new ArrayList<String>();
		Calendar endPeriod = null;
		Calendar beginWeek;
		Calendar endWeek;
		Calendar endPreviousPeriod = null;
		Calendar oldestDate = null;
		
		try {

        	MySqlController connection = MySqlController.getInstance();

        	ResultSet r3 = connection.getData("SELECT * FROM TEMPHISTORICALTIMETABLES");
        	
        	ResultSet r = connection.getData("SELECT END_DATE FROM PERIOD");
        	
        	ResultSet r1 = connection.getData("SELECT MIN(DAY) FROM TEMPHISTORICALTIMETABLES");
        	
        	if (r3.next()){
        		
        		if(r1.next()){
            		oldestDate = use.toCalendar(r1.getString(1));
            		System.out.println(use.calendarToString(oldestDate));

            	}
            	
            	if(r.next()){
            		endPeriod = use.toCalendar(r.getString(1));
            		//System.out.println(use.calendarToString(endPeriod));
            		endPreviousPeriod = use.changeDate(endPeriod, -14);
            	}
            	
            	beginWeek = (Calendar)endPeriod.clone();
            	beginWeek = use.changeDate(beginWeek, -6);
            	//System.out.println(use.calendarToString(beginWeek));

            	endWeek = (Calendar)endPeriod.clone();

            	//System.out.println(use.calendarToString(endWeek));
            	
            	while(endWeek.compareTo(oldestDate) >= 0){

            		ResultSet r2 = connection.getData("SELECT * FROM TEMPHISTORICALTIMETABLES WHERE DAY BETWEEN '"+use.calendarToString(beginWeek)+"' AND '"+use.calendarToString(endWeek)+"'");
            		
            		if(r2.next()){
            			content.add(use.calendarToString(beginWeek)+" to "+use.calendarToString(endWeek));
            		}
            		
            		
                	beginWeek = use.changeDate(beginWeek, -7);
                	//System.out.println(use.calendarToString(beginWeek));
                	
                	endWeek = use.changeDate(endWeek, -7);
                	//System.out.println(use.calendarToString(endWeek));
                	
                	
            		
            	}
        		
        	}

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Unable to connect to MySQL database.\n" + e, "Database Error", JOptionPane.WARNING_MESSAGE);
        }
        
        return content;
		
	}
	
	public ArrayList<JTextPane> getShows() {
		return shows;
	}

}
