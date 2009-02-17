/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cinemacontroller.historicalsystem;

import cinemacontroller.filmcontroller.Film;
import databasecontroller.MySQLController;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import timeanddate.Date;

/**
 *
 * @author Scott
 */
public class HistoricalController {

    public HistoricalController(){

    }


    /**
     * Returns a film object if the provided film is in the historical database.
     * 
     * @param film_title
     * @return
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public Film getFilmByTitle(String film_title) throws SQLException, ClassNotFoundException {
        MySQLController sql_connection = new MySQLController();
        ResultSet result = sql_connection.getData("SELECT * FROM `film_main_database` WHERE `film_title` = '" + film_title + "';");
        return new Film(result.getString("film_title"), result.getString("film_director"), result.getString("film_bbfc_rating"), new Date(result.getInt("film_availability_date_day"), result.getInt("film_availability_date_month"), result.getInt("film_availability_date_year")));
    }

    public ArrayList<Film> getAllFilms() throws SQLException, ClassNotFoundException{

        ArrayList<Film> list_of_films = new ArrayList<Film>();

        MySQLController sql_connection = new MySQLController();
        ResultSet result = sql_connection.getData("SELECT * FROM `film_main_database`");

        while(result.next()){
            list_of_films.add(new Film(result.getString("film_title"), result.getString("film_director"), result.getString("film_bbfc_rating"), new Date(result.getInt("film_availability_date_day"), result.getInt("film_availability_date_month"), result.getInt("film_availability_date_year"))));
        }

        return list_of_films;
    
    }

    public void removeAllRecords() throws SQLException, ClassNotFoundException{
        MySQLController sql_connection = new MySQLController();
        ResultSet result = sql_connection.getData("TRUNCATE `film_main_database`");
    }
}
