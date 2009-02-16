package cinemacontroller.screensystem;

import java.util.ArrayList;
import java.util.Calendar;

import timeanddate.Time;


/**
 * Screen Class
 * 
 * The screen class is used to manage and create screens which contains screenings.
 * Each screen has its own timetable of what is showing and when.
 * 
 * @author Scott
 *
 */
public class Screen {

	private int screen_ID_number;
	private int screen_seat_count;
	private ArrayList<Screening> screenings;
	
	/**
	 * Constructor for the screen class.
	 * 
	 * @param screen_id_number
	 * @param screen_seat_count
	 */
	public Screen(int screen_id_number, int screen_seat_count){
		// Set some required information for the screen to be valid
		this.screen_ID_number = screen_id_number;
		this.screen_seat_count = screen_seat_count;
		
		// Create a new ArrayList to hold all the screenings 
		screenings = new ArrayList<Screening>();
	}
	
	/**
	 * Sets the screens unique identification number.
	 * 
	 * @param screen_ID_number
	 */
 	public void setIDNumber(int screen_ID_number){
		this.screen_ID_number = screen_ID_number;
	}

   /**
    * Sets the number of seats the screen has available.
    * 
    * @param  screen_seat_count		the number of seats in the screen.
    */
	public void setSeats(int screen_seat_count){
		this.screen_seat_count = screen_seat_count;
	}
	
   /**
    * Returns the unique identification number of the screen.
    * 
    * @return
    */
	public int getIDNumber(){
		return this.screen_ID_number;
	}
	
   /**
    * Returns the number of seats available in the screen.
    * 
    * @return
    */
	public int getSeats(){
		return this.screen_seat_count;
	}
	
	/**
	 * Returns the list of screenings
	 * 
	 * @return
	 */
	public ArrayList<Screening> getScreenings(){
		return this.screenings;
	}
	
	/**
	 * Adds a new screening to the list of screenings that this screen has.
	 * 
	 * @param screening
	 */
	public void addScreening(Screening screening){
		screenings.add(screening);
	}
	
	/**
	 * Checks if the screen is available on a certain date and at a specific time.
	 * 
	 * @param date
	 * @param start_time
	 * @param end_time
	 * @return				returns TRUE if screen is free, FALSE otherwise.
	 */
	public boolean checkScreenFree(Calendar date, Time start_time, Time end_time){

		for(Screening screen : screenings){
			
			if(Integer.parseInt(start_time.getHourOfDay() + "" + start_time.getMinute()) >= Integer.parseInt(screen.getStartTime().getHourOfDay() + "" + screen.getStartTime().getMinute()) && Integer.parseInt(start_time.getHourOfDay() + "" + start_time.getMinute()) <= Integer.parseInt(screen.getEndTime().getHourOfDay() + "" + screen.getEndTime().getMinute())){
				return false;
			}
		}
		
		return true;
	}
}

