package cinemacontroller.screencontroller;

import cinemacontroller.filmcontroller.FilmController;
import databasecontroller.MySQLController;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Provides all the database activity for the screen controller.
 * 
 * @author Scott
 */
public class ScreenDatabaseController {

    private FilmController film_controller;
    private ArrayList<Screen> screens;

    /**
     * Constructor for the Screen database controller.
     * @param film_controller
     * @param screens
     */
    public ScreenDatabaseController(FilmController film_controller, ArrayList<Screen> screens){
        this.film_controller = film_controller;
        this.screens = screens;
    }

    /**
     * Gets all the screenings from the MySQL database and returns an arraylist of
     * screenings.
     * 
     * @return arraylist of screenings
     * @throws java.sql.SQLException
     */
    public ArrayList<Screening> getScreenings() throws SQLException {
        
        ArrayList<Screening> return_screenings = new ArrayList<Screening>();

        for(Screen current_screen : screens){

        // Create a new mysql connection and query database
        MySQLController connection = new MySQLController();
        ResultSet screenings = connection.getData("SELECT * FROM main_screening_list WHERE screen_id = '" + current_screen.getID() + "'");

        // Populate the internal list of films by cycle through each film in database
        while(screenings.next()){

            // Create a new GregorianCalendar for the time of
            GregorianCalendar time = new GregorianCalendar();
            time.setTimeInMillis(screenings.getTime("start_time").getTime());

            GregorianCalendar date = new GregorianCalendar();
            date.setTimeInMillis(screenings.getDate("start_date").getTime());

            Screening current_screeing = new Screening(screenings.getInt("uniqueid"), film_controller.getFilm(screenings.getInt("film_id")), current_screen, date, time);
            current_screeing.setViewings(screenings.getInt("viewings"));

            // Add the new screening to the selected screen
            return_screenings.add(current_screeing);
        }

        }
           return return_screenings;
    }

    /**
     * Gets all the screens from the MySQL database.
     * 
     * @return arraylist of screens
     */
    ArrayList<Screen> getScreens() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void updateScreening(Screening screening) throws SQLException{
        Date time = screening.getStartTime().getTime();
        Date date = screening.getDate().getTime();

        Format mysqltime = new SimpleDateFormat("kk:mm:ss");
        Format mysqldate = new SimpleDateFormat("yyyy-M-dd");

        MySQLController connection = new MySQLController();
        connection.putData("UPDATE main_screening_list SET film_id = '"+screening.getFilm().getID()+"', screen_id = '"+screening.getScreen().getID()+"', start_date = '"+mysqldate.format(date)+"', start_time = '"+mysqltime.format(time)+"', viewings = '"+screening.getViewings()+"' WHERE uniqueid = '" + screening.getID() + "'");
        connection.putData("UPDATE historical_screening_list SET film_id = '"+screening.getFilm().getID()+"', screen_id = '"+screening.getScreen().getID()+"', start_date = '"+mysqldate.format(date)+"', start_time = '"+mysqltime.format(time)+"', viewings = '"+screening.getViewings()+"' WHERE uniqueid = '" + screening.getID() + "'");
        connection.disconnect();
    }

    public void addScreening(Screening screening) throws SQLException{

        Date time = screening.getStartTime().getTime();
        Date date = screening.getDate().getTime();

        Format mysqltime = new SimpleDateFormat("kk:mm:ss");
        Format mysqldate = new SimpleDateFormat("yyyy-M-dd");

        MySQLController connection = new MySQLController();
        connection.putData("INSERT INTO `main_screening_list` (uniqueid, film_id, screen_id, start_time, start_date, viewings) VALUES('"+screening.getID()+"','"+screening.getFilm().getID()+"','"+screening.getScreen().getID()+"','"+ mysqltime.format(time)+"', '"+mysqldate.format(date)+"','"+screening.getViewings()+"')");
        connection.putData("INSERT INTO `historical_screening_list` (uniqueid, film_id, screen_id, start_time, start_date, viewings) VALUES('"+screening.getID()+"','"+screening.getFilm().getID()+"','"+screening.getScreen().getID()+"','"+ mysqltime.format(time)+"', '"+mysqldate.format(date)+"','"+screening.getViewings()+"')");
        connection.disconnect();
    }

    public void removeScreening(Screening screening) throws SQLException{
      MySQLController connection = new MySQLController();
      connection.putData("DELETE FROM main_screening_list WHERE `uniqueid` = '"+screening.getID()+"'");
      connection.disconnect();
    }

    public Screening getScreening(int screening_id) throws SQLException{

        Screening returnme = null;

        for(Screen current_screen : screens){

        // Create a new mysql connection and query database
        MySQLController connection = new MySQLController();
        ResultSet screenings = connection.getData("SELECT * FROM main_screening_list WHERE screen_id = '" + current_screen.getID() + "' AND `uniqueid` = '"+screening_id+"'");

        // Populate the internal list of films by cycle through each film in database
        while(screenings.next()){

            // Create a new GregorianCalendar for the time of
            GregorianCalendar time = new GregorianCalendar();
            time.setTimeInMillis(screenings.getTime("start_time").getTime());

            GregorianCalendar date = new GregorianCalendar();
            date.setTimeInMillis(screenings.getDate("start_date").getTime());

            Screening current_screeing = new Screening(screenings.getInt("uniqueid"), film_controller.getFilm(screenings.getInt("film_id")), current_screen, date, time);
            current_screeing.setViewings(screenings.getInt("viewings"));

            // Add the new screening to the selected screen
            returnme = current_screeing;
        }

        }
           return returnme;
    }


}
