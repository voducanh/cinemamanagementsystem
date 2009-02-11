package cinemacontroller.main;

import cinemacontroller.filmcontroller.FilmManager;
import cinemacontroller.rotationalsystem.RotationEngine;
import cinemacontroller.screencontroller.ScreenManager;

/**
 * Main controller class that deals with all the management of the cinema
 * including its screens, screenings and its films - also handling the rotational,
 * statistical and recording system. 
 * 
 * @author Scott
 *
 */
public class CinemaSystemController {
	public ScreenManager screen_manager;
	public FilmManager film_manager;
	public RotationEngine rotation_manager;
	
	public CinemaSystemController(){
		screen_manager = new ScreenManager();
		film_manager = new FilmManager();
		rotation_manager = new RotationEngine();
	}

	
	/** 
	 * This function's job is to setup all the screens that the cinema will have and add
	 * them to the list of screens ArrayList.
	 */
	public void setupCinemaScreens(){
		int id_counter = 1;
		
		// Setup all the thousand seater screens
		for(int counter = 0; counter < 2; counter++){
			this.screen_manager.addNewScreen(id_counter, 1000);
			id_counter++;
		}
		
		// Setup all the thousand seater screens
		for(int counter = 0; counter < 4; counter++){
			this.screen_manager.addNewScreen(id_counter, 500);
			id_counter++;
		}
		
		// Setup all the thousand seater screens
		for(int counter = 0; counter < 4; counter++){
			this.screen_manager.addNewScreen(id_counter, 200);
			id_counter++;
		}
		
		// Setup all the thousand seater screens
		for(int counter = 0; counter < 2; counter++){
			this.screen_manager.addNewScreen(id_counter, 100);
			id_counter++;
		}
	}
	
	
	
	
}
