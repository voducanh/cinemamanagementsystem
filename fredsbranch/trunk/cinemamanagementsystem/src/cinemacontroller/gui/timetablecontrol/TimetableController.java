package cinemacontroller.gui.timetablecontrol;

import java.awt.Color;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;
import javax.swing.JTextPane;

import cinemacontroller.gui.MainWindow;

import usefulmethods.Useful;

import databasecontroller.MySqlController;

public class TimetableController {
	
	private static final int startX = 3;
	private static final int startY = 30;
	private static final int columnWidth = 87;
	private static final int hourPx = 38;
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
        shows.get(shows.size()-1).setText("Screen Closed");
	}
	
	public void addOpenScreen(int nbShows, int hour, int mins, int idMovie, int expectedBooking, int nbSeatsBooked, String pausedTime,String startHour){
		
		String color = "";
		String nameType = "";
		String nameMovie = "";
		String nameExpectedAudience = "";
		String nameBBFC = "";
		int red = 0;
		int green = 0;
		int blue = 0;
		
		int height = (int)(hour*hourPx+mins*hourPx/60);
		int pause = (int)(use.getHour(pausedTime)*hourPx+use.getMins(pausedTime)*hourPx/60);
		
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

        		int[] tabTime = new int[2];
        		tabTime[0] = use.getHour(startHour);
        		tabTime[1] = use.getMins(startHour);
        		
        		for(int i=0;i<nbShows;i++){
        		
        			String startHours = tabTime[0]+":";
        			
        			if(tabTime[1] < 10){
        				startHours = startHours+"0";
        			}
        			
        			startHours += tabTime[1];

        			tabTime = addTime(tabTime[0], tabTime[1], hour, mins);
        			
        			String endHours = tabTime[0]+":";
        			
        			if(tabTime[1] < 10){
        				endHours = endHours+"0";
        			}
        			
        			endHours += tabTime[1];
        			
        			tabTime = addTime(tabTime[0], tabTime[1], use.getHour(pausedTime), use.getMins(pausedTime));
        				
        			shows.add(new JTextPane());
                    shows.get(shows.size()-1).setEditable(false);
                    shows.get(shows.size()-1).setBounds(x, y, 80, height);
                    shows.get(shows.size()-1).setBackground(new Color(red,green,blue));
                    shows.get(shows.size()-1).setText(nameMovie);
                    shows.get(shows.size()-1).setToolTipText("<html>Title: "+nameMovie+"<br/><br/>Start time: "+startHours+"<br/>End Time: "+endHours+"<br/>Running Time: "+hour+":"+mins+"<br/><br/>Expected Audience: "+nameExpectedAudience+"<br/>Type: "+nameType+"<br/>BBFC: "+nameBBFC+"<br/><br/>Expected Booking: "+expectedBooking+"<br/>Seats booked: "+nbSeatsBooked+"</html>");
                    
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
	
	public ArrayList<JTextPane> getShows() {
		return shows;
	}
	
	public int[] addTime(int hour1,int mins1,int hour2,int mins2){
		
		int[] tab = new int[2];
		int hour1Temp = hour1;
		int hour2Temp = hour2;
		int mins1Temp = mins1;
		int mins2Temp = mins2;

		tab[0] = hour1Temp+hour2Temp;
		tab[1] = mins1Temp+mins2Temp;
		
		while(tab[1] >= 60){
			
			tab[1] += -60;
			tab[0] += 1;
			
		}
		while(tab[0] >= 24){
			
			tab[0] += -24;
			
		}

		return tab;
		
	}
	

	
	
	
	
	

}
