package cinemacontroller.filmcontroller;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * Film Class
 * 
 * The film class contains all the operational requirements to manage a film.
 * Contains information such as film directors, tiles and length.
 * 
 * @author Scott
 *
 */
public class Film {

    private int film_id;
    
    private String film_title;
    private String film_director;
    private String film_bbfc_rating;

    private int popularity;

    private GregorianCalendar film_length;
    private GregorianCalendar film_avaliability_date;

    private String color;
    /**
    * Constructor for the film class, also provides functionality to set some
    * of the films primary attributes.
    * 
    * @param  film_title                the title of the film.
    * @param  film_director             the director of the film.
    * @param  film_bbfc_rating          the BBFC rating of the film.
    * @param  film_expected_popularity  expected popularity of the film.
    * @param  film_avaliability_date    the availability date of the film.
    */
    public Film(int film_id, String film_title, String film_director, String film_bbfc_rating, GregorianCalendar length, GregorianCalendar film_avaliability_date, String color) throws IllegalArgumentException
    {
        // Set all the required information
        this.film_id = film_id;
        this.film_title = film_title;
        this.film_director = film_director;
        this.film_bbfc_rating = film_bbfc_rating;
        this.film_avaliability_date = film_avaliability_date;
        this.film_length = length;
        this.color = color;
    }


    /**
    * Sets the current films's title.
    *
    */
    public void setTitle(String film_title) throws IllegalArgumentException
    {
        // Check to see if the film title is of a correct length
        if (film_title.isEmpty())
        {
            throw new IllegalArgumentException();
        }
        
        // Set the film title
        this.film_title = film_title;
        
    }

    /**
    * Sets the current film's director.
    *
    * @param  film_title the new director of the film.
    */
    public void setDirector(String film_director) throws IllegalArgumentException
    {
        if (film_director.isEmpty())
        {
            throw new IllegalArgumentException();
        }
        // Set the films director
        this.film_director = film_director;
    }


    /**
    * Sets the current film's BBFC rating.
    * 
    * @param  film_bbfc_rating  the new BBFC rating for the film.
    */
    public void setBBFCRating(String film_bbfc_rating) throws IllegalArgumentException
    {
        if (film_bbfc_rating.isEmpty())
        {
            throw new IllegalArgumentException();
        }

        // Set the films BBFC rating
        this.film_bbfc_rating = film_bbfc_rating;
    }


    /**
    * Simply sets the expected popularity of the film.
    *
    * @param      expected popularity of the film.
    */
    public void setExpectedAudienceFigures(int film_expected_popularity) throws IllegalArgumentException
    {
        // Sets the films expected popularity
        this.popularity = film_expected_popularity;
    }

    /**
    * Simply sets the length of the film.
    *
    * @param      film length.
    */
    public void setLength(GregorianCalendar length)
    {
        this.film_length = length;
    }

    public void setLength(int hour, int min, int second){
        this.film_length.set(Calendar.HOUR_OF_DAY, hour);
        this.film_length.set(Calendar.MINUTE, min);
        this.film_length.set(Calendar.SECOND, second);
    }

    /**
    * Sets the current film's availability date.
    * 
    * @param  film_avaliability_date  the new availability date of the film.
    */
    public void setFilmavaliabilityDate(GregorianCalendar film_avaliability_date)
    {
        // Set the films availability date
        this.film_avaliability_date = film_avaliability_date;
    }

    /**
     * Returns the ID of the film
     *
     * @return
     */
    public int getID(){
        return this.film_id;
    }

    /**
    * Simply returns the title of the film object in a string based format.
    *
    * @return      the title of the film.
    */
    public String getTitle()
    {
        return film_title;
    }

    /**
    * Returns the main director of the film in a string based format.
    *
    * @return      the director of the film.
    */
    public String getDirector()
    {
        return film_director;
    }

    /**
    * Simply returns the BBFC classification in a string based format.
    *
    * @return      the BBFC rating of the film.
    */
    public String getBBFCRating()
    {
        return film_bbfc_rating;
    }

    /**
    * Simply returns the expected popularity of the film.
    *
    * @return      expected popularity of the film.
    */
    public int getExpectedAudienceFigures()
    {
        return this.popularity;
    }

    /**
    * Simply returns the expected popularity of the film.
    *
    * @return      expected length of the film.
    */
    public GregorianCalendar getLength()
    {
        GregorianCalendar returnme = (GregorianCalendar) this.film_length.clone();
        return returnme;
    }

    /**
    * Returns the availability date of the film in a Calendar format.
    *
    * @return      the availability date of the film.
    */
    public GregorianCalendar getAvaliabilityDate()
    {
        return film_avaliability_date;
    }


    /**
     * Overrides toString to provide the title of the film.
     * 
     * @return
     */
    public String toString(){
        return this.film_title;
    }
    
    public static int generateID(){
         Random random = new Random();
        return random.nextInt(100000000);
    }

    public String getColor(){
        return this.color;
    }
    
 
}
