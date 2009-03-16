/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cinemacontroller.filmcontroller;

import databasecontroller.MySqlController;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 *
 * @author Scott
 */
public class FilmDatabaseController {
/**
     * Adds a new film to the sql database.
     *
     * @param film
     * @throws java.sql.SQLException
     */
    public void addFilmToDatabase(Film film) throws SQLException {
        // Create a new mysql connection and query database
    	MySqlController connection = MySqlController.getInstance();

        Format time_format = new SimpleDateFormat("kk:mm:ss");
        Format date_format = new SimpleDateFormat("yyyy-M-dd");

        connection.putData("INSERT INTO main_film_list (uniqueid, title, director, bbfc, popularity, length, availability_date, color)  VALUES ('" + film.getID() + "','" + film.getTitle() + "','" + film.getDirector() + "','" + film.getBBFCRating() + "','" + film.getExpectedAudienceFigures() + "','" + time_format.format(film.getLength().getTime()) + "','" + date_format.format(film.getAvaliabilityDate().getTime()) + "', '" + film.getColor() +"')");
        connection.disconnect();
    }

    /**
     * Function populates the list of films stored internally with the films stored in a database.
     *
     * @throws java.sql.SQLException
     */
    public ArrayList<Film> getFilmsFromDatabase() throws SQLException {
        // Create a new mysql connection and query database
    	MySqlController connection = MySqlController.getInstance();
        ResultSet result = connection.getData("SELECT * FROM main_film_list");
        ArrayList<Film> return_films = new ArrayList<Film>();
        

        // Populate the internal list of films by cycle through each film in database
        while(result.next()){

            // Get the availability date for the film
            GregorianCalendar start_date = new GregorianCalendar();
            start_date.setTimeInMillis(result.getDate("availability_date").getTime());

            // Get the film length
            GregorianCalendar film_length = new GregorianCalendar();
            film_length.setTimeInMillis(result.getTime("length").getTime());

            // Create a new film instance
            Film current_film = new Film(result.getInt("uniqueid"), result.getString("title"), result.getString("director"), result.getString("bbfc"), film_length, start_date, result.getString("color"));

            // Add the new film instance to the list of films in database
            return_films.add(current_film);
        }

        connection.disconnect();

        return return_films;
    }
}
