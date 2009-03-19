package cinemacontroller.filmcontroller;

import java.util.GregorianCalendar;

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

    public enum TYPE { BLOCKBUSTER, SPECIALIST, NONMAINSTREAM }
    
    private int film_ID_number;

    private String film_title;
    private String film_director;
    private String film_bbfc_rating;
    private String color;
    private TYPE film_type;

    private GregorianCalendar film_length;
    private GregorianCalendar film_date_added;
    private GregorianCalendar film_expiration_date;

    /**
    * Constructor for the film class, also provides functionality to set some
    * of the films primary attributes.
    * 
    * @param film_ID_number
    * @param film_title                the title of the film.
    * @param film_director             the director of the film.
    * @param film_bbfc_rating          the BBFC rating of the film.
    * @param length
    * @param film_date_added    the availability date of the film.
    * @param color
    * @throws IllegalArgumentException
    */
    public Film(int film_ID_number, String film_title, String film_director, String film_bbfc_rating, GregorianCalendar length, GregorianCalendar film_avaliability_date, GregorianCalendar film_expiration_date, String color, TYPE film_type) throws IllegalArgumentException
    {
        // Set all the required information
        this.film_ID_number = film_ID_number;
        this.film_title = film_title;
        this.film_director = film_director;
        this.film_bbfc_rating = film_bbfc_rating;
        this.film_date_added = film_avaliability_date;
        this.film_expiration_date = film_expiration_date;
        this.film_length = length;
        this.color = color;
        this.film_type = film_type;
    }

    public void updateFilm(String film_title, String film_director, String film_bbfc_rating, GregorianCalendar length, GregorianCalendar film_avaliability_date, GregorianCalendar film_expiration_date, String color, TYPE film_type){
        this.film_title = film_title;
        this.film_director = film_director;
        this.film_bbfc_rating = film_bbfc_rating;
        this.film_date_added = film_avaliability_date;
        this.film_expiration_date = film_expiration_date;
        this.film_length = length;
        this.color = color;
        this.film_type = film_type;
    }


    /**
    * Sets the current films's title.
    *
    * @param film_title the title of the film
    * @throws IllegalArgumentException
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
    * @param film_director the director of the film
    * @throws IllegalArgumentException
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
    * @throws IllegalArgumentException
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
    * Sets the type of the film
    *
    * @param enum TYPE
    */
    public void setFilmType(TYPE film_type)
    {
        // Sets the type of the film
        this.film_type = film_type;
    }

    /**
    * Sets the screening length of the current film.
    *
    * @param length the new length of the film
    */
    public void setLength(GregorianCalendar length)
    {
        this.film_length = length;
    }

    /**
     * Returns the ID of the film
     *
     * @return the ID of the curren film
     */
    public int getID(){
        return this.film_ID_number;
    }

    /**
    * Simply returns the title of the film object in a string based format.
    *
    * @return the title of the film.
    */
    public String getTitle()
    {
        return film_title;
    }

    /**
    * Returns the main director of the film in a string based format.
    *
    * @return the director of the film.
    */
    public String getDirector()
    {
        return film_director;
    }

    /**
    * Simply returns the BBFC classification in a string based format.
    *
    * @return the BBFC rating of the film.
    */
    public String getBBFCRating()
    {
        return film_bbfc_rating;
    }

    /**
    * Returns the type of the film, either BLOCKBUSTER, SPECIALIST, NON-MAINSTREAM
    *
    * @return expected popularity of the film.
    */
    public int getFilmExpectedDailyViewings()
    {
        switch (this.film_type) {
            case BLOCKBUSTER:
                return 1000;
            case SPECIALIST:
                return 500;
            case NONMAINSTREAM:
                return 200;
            default:
                return 500;
        }
    }

    public String getType(){
        return this.film_type.toString();
    }

    /**
    * Simply returns the expected popularity of the film.
    *
    * @return expected length of the film.
    */
    public GregorianCalendar getLength()
    {
        GregorianCalendar returnme = (GregorianCalendar) this.film_length.clone();
        return returnme;
    }

    /**
    * Returns the date of which the film was added to the system.
    *
    * @return the date added of the film.
    */
    public GregorianCalendar getDateAdded()
    {
        return film_date_added;
    }

    /**
     * Returns the date at which the film expires from the internal database.
     * 
     * @return gregorianCalendar
     */
    public GregorianCalendar getExpireDate(){
        return film_expiration_date;
    }

    /**
     * Overrides toString to provide the title of the film.
     * 
     * @return string representing the film
     */
    @Override
    public String toString(){
        return this.film_title;
    }
    
    /**
     * Returns the color that represents the film.
     * 
     * @return the color of the film
     */
    public String getColor(){
        return this.color;
    }
    
 
}
