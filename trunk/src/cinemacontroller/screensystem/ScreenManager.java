package cinemacontroller.screensystem;

import cinemacontroller.filmcontroller.Film;
import cinemacontroller.filmcontroller.FilmManager;
import databasecontroller.MySQLController;
import java.sql.ResultSet;
import java.util.ArrayList;
import timeanddate.Date;
import timeanddate.Time;

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
	 * Returns all the screens that have a minimum number of seats.
	 * 
	 * @param minimum_seats
	 * @param maximum_seats
	 * @return
	 */
	public ArrayList<Screen> getScreenBySeatRange(int minimum_seats){
		ArrayList<Screen> screens = new ArrayList<Screen>();
		
		for(Screen screen : this.cinema_screens){
			if(screen.getSeats() >= minimum_seats){
				screens.add(screen);
			}
		}
		return screens;
	}


    public boolean checkScreenFree(Screen screen, Date date, Time start_time, Time end_time){

        Screen current_screen = screen;

        for(Screening current_screening : current_screen.getScreenings()){

            if((start_time.getCalendar().after(current_screening.getStartTime().getCalendar())) && (start_time.getCalendar().before(current_screening.getEndTime().getCalendar()))) {
                return false;
            }

            if((end_time.getCalendar().after(current_screening.getStartTime().getCalendar())) && (end_time.getCalendar().before(current_screening.getEndTime().getCalendar()))) {
                return false;
            }

            if(end_time.getCalendar().equals(current_screening.getStartTime().getCalendar()) || end_time.getCalendar().equals(current_screening.getEndTime().getCalendar())){
                return false;
            }

        }

          return true;
    }


     public void getScreeningsFromDatabase(FilmManager films) {
        try {


            for(Screen current_screen : this.cinema_screens){
                int unique_id = current_screen.getIDNumber();


                String table_name = "main_screenings_list";
                // Create a new mysql connection and query database
                MySQLController connection = new MySQLController();
                ResultSet result = connection.getData("SELECT * FROM " + table_name + " WHERE screen = '" + unique_id + "';");
                // Populate the internal list of films by cycle through each film in database
                while (result.next()) {

                    Film current_film = films.getFilmByID(result.getInt("film"));
                    //System.out.println(current_film.getTitle());
                    current_screen.addScreening(new Screening(current_film, new Date(12,12,2009), new Time(16,30)));
                }



            }
            
           


        } catch (Exception e) {
            System.out.println(e);
        }
    }


     public int getScreeningCount(){
         int counter = 0;
         for(Screen current_screen : this.cinema_screens){
             counter += current_screen.getScreenings().size();
         }
         return counter;
     }
}
