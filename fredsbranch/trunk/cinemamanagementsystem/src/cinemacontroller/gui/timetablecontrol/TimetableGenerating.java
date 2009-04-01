package cinemacontroller.gui.timetablecontrol;

import java.sql.ResultSet;
import java.util.Calendar;

import javax.swing.JOptionPane;

import usefulmethods.Useful;
import cinemacontroller.gui.MainWindow;
import cinemacontroller.rotationalcontroller.AutomaticRotation;
import databasecontroller.MySqlController;

public class TimetableGenerating implements Runnable {
	
	private TimetableRunning auto;
	private MainWindow window;
	private Useful use;
	private TimetableController timetableD;
	
	public TimetableGenerating(TimetableRunning auto,MainWindow window){
		this.auto = auto;
		this.window = window;
		use = new Useful();
	}
	
	public void run() {
		auto.getJProgressBar().setIndeterminate(true);
		
		
		
		timetableD = new TimetableController();
		
		timetableD.goNewWeek();

		int day = window.getComboDay().getSelectedIndex();
		int index = window.getComboWeek().getSelectedIndex();

		String temp = (String)window.getComboWeek().getItemAt(index);
		temp = temp.substring(0,temp.indexOf(" ")).trim();

		Calendar beginSelectedWeek = use.toCalendar(temp);
		beginSelectedWeek = use.changeDate(beginSelectedWeek, day);

		try {

	    	MySqlController connection = MySqlController.getInstance();
	    	//System.out.println("SELECT * FROM OPERATING_HOUR WHERE BEGIN_DATE<'"+use.calendarToString(beginSelectedWeek)+"' && END_DATE IS NULL");
	    	ResultSet r4 = connection.getData("SELECT * FROM OPERATING_HOUR WHERE BEGIN_DATE<'"+use.calendarToString(beginSelectedWeek)+"' && END_DATE IS NULL");
	    
	    	String pausedTime = "";
	    	String startHour = "";
	    	
	    	if(r4.next()){
	    		
	    		if(day == 1 || day == 2 || day == 3){
	    			pausedTime = r4.getString(6);
	    			startHour = r4.getString(5);
	    		}
	    		else{
	    			pausedTime = r4.getString(3);
	    			startHour = r4.getString(2);
	    		}
	    		
	    	}

	    	ResultSet r2 = connection.getData("SELECT * FROM TIMETABLES WHERE DAY='"+use.calendarToString(beginSelectedWeek)+"'");
	    	if(!r2.next()){
	    		auto.dispose();
	    		JOptionPane.showMessageDialog(null, "No shows scheduled for this day.", "Display Timetable", JOptionPane.WARNING_MESSAGE);
	    	}
	    	else{
	        	ResultSet r = connection.getData("SELECT * FROM SCREENS ORDER BY ID_SCREEN");
	        	
	        	while(r.next()){
	        		//System.out.println("SELECT * FROM TIMETABLES WHERE DAY='"+use.calendarToString(beginSelectedWeek)+"' AND ID_SCREEN='"+r.getInt(1)+"'");
	        		ResultSet r1 = connection.getData("SELECT * FROM TIMETABLES WHERE DAY='"+use.calendarToString(beginSelectedWeek)+"' AND ID_SCREEN='"+r.getInt(1)+"'");
	        		
	        		if(r1.next()){
	        			int idMovie = r1.getInt(2);
	        			
	        			if(idMovie != 0){
	            			int nbShows = 0;
	            			nbShows = use.numberShows(r1.getString(2), beginSelectedWeek);
	            			
	            			ResultSet r3 = connection.getData("SELECT * FROM MOVIES WHERE ID_MOVIE='"+idMovie+"'");
	            			
	            			if(r3.next()){
	            				String runningTime = r3.getString(10);

	            				int hour = use.getHour(runningTime);
	            				int mins = use.getMins(runningTime);

	            				timetableD.addOpenScreen(nbShows,hour,mins,idMovie,r1.getInt(4),r1.getInt(5),pausedTime,startHour);
	            				
	            			}
	        			}
	        			else{
	        				timetableD.addClosedScreen();
	        			}
	        		}
	        		timetableD.goNextScreen();
	        	}
	    	}
	    	


	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "Unable to connect to MySQL database.\n" + e, "Database Error", JOptionPane.WARNING_MESSAGE);
	    }
	    
	    for(int i=0;i<timetableD.getShows().size();i++){
	    	window.getLayeredPane().add(timetableD.getShows().get(i), javax.swing.JLayeredPane.DRAG_LAYER);
		}
	    
	    window.displayJtable();
		
		auto.getJProgressBar().setIndeterminate(false);
		auto.dispose();

	}

}
