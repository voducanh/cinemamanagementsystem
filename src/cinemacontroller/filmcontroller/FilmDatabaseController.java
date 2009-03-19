package cinemacontroller.filmcontroller;

import databasecontroller.MySQLController;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * This class handles all the database interactions for the film controller. Provides
 * methods to remove a film, get films and also to add new films.
 *
 * @author Scott
 */
public class FilmDatabaseController {
/**
     * Adds a new film into the MySQL database.
     *
     * @param film
     * @throws java.sql.SQLException
     */
    public void addFilm(Film film) throws SQLException {
        // Create a new mysql connection and query database
        MySQLController connection = new MySQLController();

        Format time_format = new SimpleDateFormat("kk:mm:ss");
        Format date_format = new SimpleDateFormat("yyyy-M-dd");

        connection.putData("INSERT INTO main_film_list (uniqueid, title, director, bbfc, length, added_date, expire_date, color, type) VALUES ('" + film.getID() + "','" + film.getTitle() + "','" + film.getDirector() + "','" + film.getBBFCRating() + "','" + time_format.format(film.getLength().getTime()) + "','" + date_format.format(film.getDateAdded().getTime()) + "','" + date_format.format(film.getExpireDate().getTime()) + "', '" + film.getColor() +"','"+film.getType()+"')");
        connection.disconnect();
    }

    /**
     * Updates a films details in the database.
     * 
     * @param film
     * @throws java.sql.SQLException
     */
    public void updateFilm(Film film) throws SQLException {
        // Create a new mysql connection and query database
        MySQLController connection = new MySQLController();

        Format time_format = new SimpleDateFormat("kk:mm:ss");
        Format date_format = new SimpleDateFormat("yyyy-M-dd");

        connection.putData("UPDATE main_film_list SET title = '" + film.getTitle() + "', director = '" + film.getDirector() + "', bbfc = '" + film.getBBFCRating() + "', length = '" + time_format.format(film.getLength().getTime()) + "', added_date = '" + date_format.format(film.getDateAdded().getTime()) + "', expire_date = '" + date_format.format(film.getExpireDate().getTime()) + "', color = '" + film.getColor() + "', type = '"+film.getType()+"' WHERE `uniqueid` = "+film.getID()+"");
        connection.disconnect();
    }


    /**
     * Function pulls all the films stored in the MySQL database and returns a list
     * of film objects in an ArrayList.
     *
     * @throws java.sql.SQLException
     * @return an arraylist of films
     */
    public ArrayList<Film> getFilms() throws SQLException {
        // Create a new mysql connection and query database
        MySQLController connection = new MySQLController();
        ResultSet result = connection.getData("SELECT * FROM main_film_list");
        ArrayList<Film> return_films = new ArrayList<Film>();
        

        // Populate the internal list of films by cycle through each film in database
        while(result.next()){

            // Get the availability date for the film
            GregorianCalendar start_date = new GregorianCalendar();
            start_date.setTimeInMillis(result.getDate("added_date").getTime());

            // Get the availability date for the film
            GregorianCalendar expire_date = new GregorianCalendar();
            expire_date.setTimeInMillis(result.getDate("expire_date").getTime());

            // Get the film length
            GregorianCalendar film_length = new GregorianCalendar();
            film_length.setTimeInMillis(result.getTime("length").getTime());

            
            // Create a new film instance
            Film current_film = new Film(result.getInt("uniqueid"), result.getString("title"), result.getString("director"), result.getString("bbfc"), film_length, start_date, expire_date, result.getString("color"), Film.TYPE.valueOf(result.getString("type")));

            // Add the new film instance to the list of films in database
            return_films.add(current_film);
        }

        connection.disconnect();

        return return_films;
    }
    
}
