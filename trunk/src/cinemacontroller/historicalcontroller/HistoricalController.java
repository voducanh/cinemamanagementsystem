package cinemacontroller.historicalcontroller;

import cinemacontroller.filmcontroller.Film;
import databasecontroller.MySQLController;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Provides functionality to mainian historical data on all the films and
 * screenings that the cinema management system has to deal with.
 * 
 * @author Scott
 */
public class HistoricalController {

    /**
     * The constructor for the historical contoller.
     */
    public HistoricalController(){

    }


    /**
     * Returns an arraylist of film objects based on all the information stored in
     * the historical database.
     * 
     * @return arraylist of films
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public ArrayList<Film> getAllFilms() throws SQLException, ClassNotFoundException{

        ArrayList<Film> list_of_films = new ArrayList<Film>();

        MySQLController sql_connection = new MySQLController();
        ResultSet result = sql_connection.getData("SELECT * FROM historical_film_list");

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
            Film current_film = new Film(result.getInt("uniqueid"), result.getString("title"), result.getString("director"), result.getString("bbfc"), film_length, start_date, expire_date, "GREEN", Film.TYPE.valueOf(result.getString("type")));
            list_of_films.add(current_film);
        }

        return list_of_films;
    }

    /**
     * Clears all the historical information stored in the historical database.
     * 
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public void removeAllRecords() throws SQLException, ClassNotFoundException{
        MySQLController sql_connection = new MySQLController();
        sql_connection.getData("TRUNCATE historical_film_list");
    }

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

        connection.putData("INSERT INTO historical_film_list (title, director, bbfc, popularity, length, added_date, expire_date, type)  VALUES ('" + film.getTitle() + "','" + film.getDirector() + "','" + film.getBBFCRating() + "','" + time_format.format(film.getLength().getTime()) + "','" + date_format.format(film.getDateAdded().getTime()) + "','" + date_format.format(film.getExpireDate().getTime()) + "','"+film.getType()+"')");
        connection.disconnect();
    }
}
