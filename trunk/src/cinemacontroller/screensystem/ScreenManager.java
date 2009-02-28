package cinemacontroller.screensystem;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Provides functionality for managing the screen of the cinema.
 * 
 * @author Scott
 *
 */
public class ScreenManager {
	private ArrayList<Screen> cinema_screens;

	
	public ScreenManager(){
		cinema_screens = new ArrayList<Screen>();
	}
	
	/**
	 * Creates a new screen and adds it to the cinema's screen database.
	 * 
	 * @param screen_id
	 * @param seat_count
	 */
	public void addNewScreen(int screen_id, int seat_count){
		Screen screen = new Screen(screen_id, seat_count);
		
		this.cinema_screens.add(screen);
	}

    /**
     * Adds a new screen to the screen database.
     *
     * @param screen
     */
    public void addNewScreen(Screen screen){
		this.cinema_screens.add(screen);
    }
    
	/**
	 * Returns a screen specified by its unique identifier
	 * 
	 * @param screen_id
	 * @return
	 */
	public Screen getScreen(int screen_id){
		for(Screen screen : this.cinema_screens){
			if(screen.getIDNumber() == screen_id){
				return screen;
			}
		}
		return null;
	}
	
	/**
	 * Returns all the screens stored in the screen database.
	 * 
	 * @return
	 */
	public ArrayList<Screen> getScreens(){
		return this.cinema_screens;
	}


    /**
     * Adds a new screening to a screens database.
     * 
     * @param screen
     * @param screening
     */
    public void addScreening(Screen screen, Screening screening){
        this.getScreen(screen.getIDNumber()).addScreening(screening);
    }

	/**
	 * Returns all the screens that have a minimum number of seats and maximum number of seats.
	 * 
	 * @param minimum_seats
	 * @param maximum_seats
	 * @return
	 */
	public ArrayList<Screen> getScreenBySeatRange(int minimum_seats, int maximum_seats){
		ArrayList<Screen> screens = new ArrayList<Screen>();
		
		for(Screen screen : this.cinema_screens){
			if(screen.getSeats() >= minimum_seats && screen.getSeats() <= maximum_seats){
				screens.add(screen);
			}
		}
		return screens;
	}
	
    /**
     * Checks if the screen is free by checking if the required time for a screening falls between any other screenings
     * start and end time.
     * 
     * @param screen
     * @param date
     * @param start_time
     * @param end_time
     * @return
     */
    public boolean checkScreenFree(Screen screen, GregorianCalendar date, GregorianCalendar start_time, GregorianCalendar end_time){
       
        // Cycle through all the screenigs for the current screen
        for(Screening current_screening : screen.getScreenings()){
            
            // Check if start time is in between any other screening
            if(start_time.after(current_screening.getStartTime()) && start_time.before(current_screening.getEndTime())) {
                return false;
            }

            // Check if end time is in between any other screening
            if(end_time.after(current_screening.getStartTime()) && end_time.before(current_screening.getEndTime())) {
                return false;
            }

            // Check if screening is starting and stopping at any other screenings start and end time
            if(start_time.equals(current_screening.getStartTime()) || end_time.equals(current_screening.getEndTime())){
                return false;
            }
        }

        // Return true if the screen is free
        return true;
    }


    

    /**
     * Returns the number of total screenings accross all screens.
     * 
     * @return int screen count
     */
     public int getScreeningCount(){
         int counter = 0;

         // Cycle through each screen and count the number of screenings
         for(Screen current_screen : this.cinema_screens){
             counter += current_screen.getScreenings().size();
         }

         return counter;
     }

     
     public ArrayList<Screening> getScreenings(){
         ArrayList<Screening> returnme = new ArrayList<Screening>();

         for(Screen current_screen : this.getScreens()){
            for(Screening current_screening : current_screen.getScreenings()){
                returnme.add(current_screening);
            }
         }

         return returnme;
     }
}
