package cinemacontroller.screencontroller;

import java.util.ArrayList;

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
}
