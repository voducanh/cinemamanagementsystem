package cinemacontroller.screensystem;

import java.util.ArrayList;
import java.util.Calendar;

import java.util.Random;

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
		
        ArrayList<Screening> returnme = (ArrayList<Screening>) this.screenings.clone();
        
        return returnme;
	}
	
	/**
	 * Adds a new screening to the list of screenings that this screen has.
	 * 
	 * @param screening
	 */
	public void addScreening(Screening screening){
		screenings.add(screening);
	}
	
    public String toString(){
        return this.screen_seat_count + " seater. (ID = " + this.getIDNumber() +")";
    }

    public static int generateID(){

        Random random = new Random();
        int pick = random.nextInt(100000000);
        
        return pick;
   }
}

