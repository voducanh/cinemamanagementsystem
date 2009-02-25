package cinemacontroller.main;

import cinemacontroller.filmcontroller.FilmController;
import cinemacontroller.rotationalsystem.RotationEngine;
import cinemacontroller.screensystem.Screen;
import cinemacontroller.screensystem.ScreenManager;
import databasecontroller.MySQLController;
import java.sql.ResultSet;
import java.sql.SQLException;


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
	public FilmController film_manager;
	public RotationEngine rotation_manager;
	
	public CinemaSystemController(){
        
        // Initialize all the controllers that this controller allows access too
        film_manager = new FilmController();
        rotation_manager = new RotationEngine();
        screen_manager = new ScreenManager();


        try {
            // Setup all the cinema screens
            this.loadScreensFromDatabase();
        } catch(Exception e){ }

	}


    public void loadFilmsFromDatabase(){

    }

    public void loadScreeningsFromDatabase() throws SQLException, ClassNotFoundException{
        for(Screen current_screen : this.screen_manager.getScreens()){

            // Create a new mysql connection and query database
            MySQLController connection = new MySQLController();
            ResultSet screenings = connection.getData("SELECT * FROM main_screening_list WHERE screen_id = '" + current_screen.getIDNumber() + "'");

            // Populate the internal list of films by cycle through each film in database
            while(screenings.next()){

              //  Screening current_screeing = new Screening(this.film_manager.getFilmByID(screenings.getInt("film_id"), screenings.getDate("start_date"), screenings.getInt("film_id")));

            }
            
        }
    }

    /**
     * Connects to database containing all the screens and then loads them into the program.
     * 
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public void loadScreensFromDatabase() throws SQLException, ClassNotFoundException{
        String table_name = "main_screen_list";

        // Create a new mysql connection and query database
        MySQLController connection = new MySQLController();
        ResultSet result = connection.getData("SELECT * FROM `" + table_name + "`");

        // Populate the internal list of films by cycle through each film in database
        while(result.next()){
            this.screen_manager.addNewScreen(result.getInt("uniqueid"), result.getInt("seat_count"));
        }
    }
	
}
