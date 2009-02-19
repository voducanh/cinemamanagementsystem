package cinemacontroller.statisticalsystem;

import cinemacontroller.filmcontroller.Film;
import timeanddate.Date;

/**
 * This class handles all the interactions that would come under statistical data.
 * 
 * @author Scott
 */
public class StatisticController {

    public StatisticController(){

    }

    /**
     * Returns the number of films stored in the cinemas film database.
     *
     * @return
     */
    public int getTotalFilmsInDatabase(){
        return 0;
    }

    /**
     * Returns the total number of screenings in the cinemas database.
     *
     * @return
     */
    public int getTotalScreeningsInDatabase(){
        return 0;
    }

    /**
     * Returns the most popular film in the database.
     * 
     * @return
     */
    public Film getMostPopularFilm(){
        return null;
    }

    /**
     * Returns the least popular film in the database.
     *
     * @return
     */
    public Film getLeastPopularFilm(){
        return null;
    }

    /**
     * Returns the most popular film in the database on any given date.
     * 
     * @param date
     * @return
     */
    public Film getMostPopularFilmByDate(Date date){
        return null;
    }

    /**
     * Returns the least popular film in the database on any given date.
     *
     * @param date
     * @return
     */
    public Film getLeastPopularFilmByDate(Date date){
        return null;
    }


}
