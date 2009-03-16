package cinemacontroller.main;

import cinemacontroller.filmcontroller.FilmController;
import cinemacontroller.rotationalcontroller.RotationEngine;
import cinemacontroller.screencontroller.Screen;
import cinemacontroller.screencontroller.ScreenManager;
import cinemacontroller.screencontroller.Screening;
import databasecontroller.MySqlController;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;


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

    public final GregorianCalendar cinema_opening_time;
    public final GregorianCalendar cinema_closing_time;
	
	public CinemaSystemController(){
        
        // Initialize all the controllers that this controller allows access too
        film_manager = new FilmController();
        rotation_manager = new RotationEngine();
        screen_manager = new ScreenManager();

        // Set the opening and closing times of the cinema
        this.cinema_opening_time = new GregorianCalendar();
        this.cinema_opening_time.setTimeInMillis(0);
        this.cinema_opening_time.set(Calendar.HOUR_OF_DAY, 9);

        this.cinema_closing_time = new GregorianCalendar();
        this.cinema_closing_time.setTimeInMillis(0);
        this.cinema_closing_time.set(Calendar.HOUR_OF_DAY, 23);
        this.cinema_closing_time.set(Calendar.MINUTE, 59);

        try {
            // Setup all the cinema screens
            this.loadScreensFromDatabase();
            // Load all films from database
            this.film_manager.film_database_controller.getFilmsFromDatabase();
            // Load all the screenings from database
            this.loadScreeningsFromDatabase();

        } catch(Exception e){
            // Show an error message
            JOptionPane.showMessageDialog(null, "Generic Database Error.\n" + e, "Database Error", JOptionPane.WARNING_MESSAGE);
        }

	}

    public void loadScreeningsFromDatabase() throws SQLException {
        // Cycle through all the screens in the database
        for(Screen current_screen : this.screen_manager.getScreens()){

            // Create a new mysql connection and query database
        	MySqlController connection = MySqlController.getInstance();
            ResultSet screenings = connection.getData("SELECT * FROM main_screening_list WHERE screen_id = '" + current_screen.getIDNumber() + "'");

            // Populate the internal list of films by cycle through each film in database
            while(screenings.next()){

                // Create a new GregorianCalendar for the time of 
                GregorianCalendar time = new GregorianCalendar();
                time.setTimeInMillis(screenings.getTime("start_time").getTime());

                GregorianCalendar date = new GregorianCalendar();
                date.setTimeInMillis(screenings.getDate("start_date").getTime());

                Screening current_screeing = new Screening(this.film_manager.getFilm(screenings.getInt("film_id")), date, time);

                // Add the new screening to the selected screen
                this.screen_manager.addScreening(current_screen, current_screeing);
            }
            
        }
    }

    /**
     * Connects to database containing all the screens and then loads them into the program.
     * 
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public void loadScreensFromDatabase() throws SQLException {
        String table_name = "main_screen_list";

        // Create a new mysql connection and query database
        MySqlController connection = MySqlController.getInstance();
        ResultSet result = connection.getData("SELECT * FROM `" + table_name + "`");

        // Populate the internal list of screens by cycle through each screen stored in database
        while(result.next()){
            this.screen_manager.addNewScreen(result.getInt("uniqueid"), result.getInt("seat_count"));
        }
    }
	
}
