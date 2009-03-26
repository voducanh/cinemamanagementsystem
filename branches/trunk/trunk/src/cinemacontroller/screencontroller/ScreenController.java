package cinemacontroller.screencontroller;

import cinemacontroller.filmcontroller.Film;
import cinemacontroller.filmcontroller.FilmController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Provides functionality for managing the screen of the cinema.
 * 
 * @author Scott
 *
 */
public class ScreenController {
	private ArrayList<Screen> cinema_screens;
    public ScreenDatabaseController database_controller;

	
    /**
     * The constructor fo the screen controller. Sets up the screen controllers
     * access to the MySQL database controller.
     * 
     * @param film_controller
     */
    public ScreenController(FilmController film_controller){
		this.cinema_screens = new ArrayList<Screen>();
        this.database_controller = new ScreenDatabaseController(film_controller, cinema_screens);
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
			if(screen.getID() == screen_id){
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
        this.getScreen(screen.getID()).addScreening(screening);
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

            // Check current date
            if(date.get(Calendar.DAY_OF_MONTH) == current_screening.getDate().get(Calendar.DAY_OF_MONTH) && date.get(Calendar.MONTH) == current_screening.getDate().get(Calendar.MONTH) && date.get(Calendar.YEAR) == current_screening.getDate().get(Calendar.YEAR)){

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
        }

        // Return true if the screen is free
        return true;
    }


    public int getAllViewings(Film film){
        int counter = 0;
        for(Screening current_screening : this.getScreenings()){
            if(current_screening.getFilm() == film){
                 counter += current_screening.getViewings();
            }
            
         }
        return counter;
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

     /**
      * Returns all the screenings stored in the cinema management system.
      * 
      * @return ArrayList<Screening> list of screenings
      */
     public ArrayList<Screening> getScreenings(){
         ArrayList<Screening> returnme = new ArrayList<Screening>();

         for(Screen current_screen : this.getScreens()){
            for(Screening current_screening : current_screen.getScreenings()){
                returnme.add(current_screening);
            }
         }

         return returnme;
     }


     /**
      * Populates the internal program database with all the screens found in the
      * MySQL database.
      * 
      * @throws java.sql.SQLException
      */
     public void populateScreenListFromDatabase() throws SQLException {
        ArrayList<Screen> screens = this.database_controller.getScreens();

        for(Screen current_screen : screens){
            this.addNewScreen(current_screen);
        }
    }

     public void populateScreeningsListFromDatabase() throws SQLException {
        ArrayList<Screening> screenings = this.database_controller.getScreenings();

        for(Screening current_screening : screenings){
            this.addScreening(current_screening.getScreen(), current_screening);
        }
     }

     public void removeScreening(Screening screening){
         for(Screening current : this.getScreenings()){
             if(current.equals(screening)){
                 Screen found = current.getScreen();
                 found.removeScreening(screening);
             }
         }
     }
}
