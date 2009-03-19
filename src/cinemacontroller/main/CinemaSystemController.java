package cinemacontroller.main;

import cinemacontroller.filmcontroller.FilmController;
import cinemacontroller.historicalcontroller.HistoricalController;
import cinemacontroller.rotationalcontroller.RotationEngine;
import cinemacontroller.screencontroller.Screen;
import cinemacontroller.screencontroller.ScreenController;
import cinemacontroller.screencontroller.Screening;
import databasecontroller.MySQLController;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
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
    /**
     * The screen controller.
     */
    public ScreenController screen_controller;
    /**
     * The film controller.
     */
    public FilmController film_controller;
    /**
     * The rotational controller.
     */
    public RotationEngine rotation_controller;
    /**
     * The rotational controller.
     */
    public HistoricalController historical_controller;
    /**
     * The calendar for the cinema's opening time.
     */
    public final GregorianCalendar cinema_opening_time;
    /**
     * The calendar for the cinema's closing time.
     */
    public final GregorianCalendar cinema_closing_time;
	
    /**
     * The contstructor that sets up all the controllers for the cinema and defines
     * any essential information that the cinema management system has.
     */
    public CinemaSystemController(){
        
        // Initialize all the controllers that this controller allows access too
        film_controller = new FilmController();
        rotation_controller = new RotationEngine();
        screen_controller = new ScreenController(film_controller);
        historical_controller = new HistoricalController();

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
            this.film_controller.populateFilmListFromDatabase();
            // Load all the screenings from database
            this.screen_controller.populateScreeningsListFromDatabase();

        } catch(Exception e){
            // Show an error message
            JOptionPane.showMessageDialog(null, "Generic Database Error.\n" + e, "Database Error", JOptionPane.WARNING_MESSAGE);
            System.out.println(e);
        }

	}

    /**
     * Connects to database containing all the screens and then loads them into the program.
     * 
     * @throws java.sql.SQLException
     */
    public void loadScreensFromDatabase() throws SQLException {
        String table_name = "main_screen_list";

        // Create a new mysql connection and query database
        MySQLController connection = new MySQLController();
        ResultSet result = connection.getData("SELECT * FROM `" + table_name + "`");

        // Populate the internal list of screens by cycle through each screen stored in database
        while(result.next()){
            this.screen_controller.addNewScreen(result.getInt("uniqueid"), result.getInt("seat_count"));
        }
    }

    /**
     * Returns a unique id number.
     * @return unique id
     */
    public int generateID(){
        Random random = new Random();
        return random.nextInt(100000000);
    }
}
