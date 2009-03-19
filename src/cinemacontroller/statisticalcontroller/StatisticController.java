package cinemacontroller.statisticalcontroller;

import cinemacontroller.filmcontroller.Film;
import cinemacontroller.main.CinemaSystemController;
import cinemacontroller.screencontroller.Screening;
import java.util.GregorianCalendar;

/**
 * This class handles all the interactions that would come under statistical data.
 * 
 * @author Scott
 */
public class StatisticController {

    private CinemaSystemController controller;
    
    /**
     * Constructor for the statistical controller.
     */
    public StatisticController(CinemaSystemController controller){
        this.controller = controller;
    }

    /**
     * Returns the number of films stored in the cinemas film database.
     *
     * @return
     */
    public int getTotalFilmsInDatabase(){
        return this.controller.film_controller.getFilms().size();
    }

    /**
     * Returns the total number of screenings in the cinemas database.
     *
     * @return
     */
    public int getTotalScreeningsInDatabase(){
        return this.controller.screen_controller.getScreeningCount();
    }

    /**
     * Returns the most popular film in the database.
     * 
     * @return
     */
  //  public Film getMostPopularFilm(){
      
 //   }

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
    public Film getMostPopularFilmByDate(GregorianCalendar date){
        return null;
    }

    /**
     * Returns the least popular film in the database on any given date.
     *
     * @param date
     * @return
     */
    public Film getLeastPopularFilmByDate(GregorianCalendar date){
        return null;
    }


}
