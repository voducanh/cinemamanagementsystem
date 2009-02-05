package cinemacontroller.filmcontroller;
import java.util.HashMap;

import timeanddate.Date;
import timeanddate.Time;

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

    private String film_title;
    private String film_director;
    private String film_bbfc_rating;
    private double film_ticket_price;
    private int film_expected_viewings_per_day;
    
    private HashMap<String, Integer> film_actual_viewings_per_day;

    private Time film_length;
    private Date film_avaliability_date;

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
    public Film(String film_title, String film_director, String film_bbfc_rating, Date film_avaliability_date) throws IllegalArgumentException
    {
        // Set all the required information
        this.setTitle(film_title);
        this.setDirector(film_director);
        this.setBBFCRating(film_bbfc_rating);
        this.setFilmavaliabilityDate(film_avaliability_date);
        
        this.film_actual_viewings_per_day = new HashMap<String, Integer>();
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
        this.film_expected_viewings_per_day = film_expected_popularity;
    }

    /**
    * Simply sets the length of the film.
    *
    * @param      film length.
    */
    public void setLength(Time length) throws Exception
    {
        this.film_length = length;
    }

    /**
    * Simply sets the ticket price of the film.
    *
    * @param      film ticket price.
    */
    public void setTicketPrice(double film_ticket_price) throws Exception
    {
        if (film_ticket_price < 1)
        {
            throw new Exception();
        }

        this.film_ticket_price = film_ticket_price;
    }

    /**
    * Simply sets the total number of people to view the film.
    *
    * @param      film viewing number;
    */
    public void updateViewingFigures(String date, int viewings) throws Exception
    {
        this.film_actual_viewings_per_day.put(date, viewings);
    }

    /**
    * Sets the current film's availability date.
    * 
    * @param  film_avaliability_date  the new availability date of the film.
    */
    public void setFilmavaliabilityDate(Date film_avaliability_date)
    {
        // Set the films availability date
        this.film_avaliability_date = film_avaliability_date;
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
        return this.film_expected_viewings_per_day;
    }

    /**
    * Simply returns the expected popularity of the film.
    *
    * @return      expected length of the film.
    */
    public Time getLength()
    {
        return this.film_length;
    }

    /**
    * Returns the ticket price of the current film.
    *
    * @return      the ticket price.
    */
    public double getTickePrice()
    {
        return this.film_ticket_price;
    }

    /**
    * Returns the viewing figures for any specified date
    *
    * @return      the viewing figures
    */
    public int getViewingFigures(String date) throws Exception
    {
   	
    	if(!this.film_actual_viewings_per_day.containsKey(date)){
    		throw new Exception();
    	}
    	
        return this.film_actual_viewings_per_day.get(date);
    }
    
    /**
     * Returns all the viewing figures on all dates.
     * 
     * @return		all the viewing figures
     */
    public HashMap<String, Integer> getAllViewings(){
    	return this.film_actual_viewings_per_day;
    }

    /**
    * Returns the total amount of money made from the showing of the film.
    *
    * @return      total money made from film.
    */
    public double getTotalProfits()
    {
        return this.film_ticket_price;
    }

    /**
    * Returns the availability date of the film in a Calendar format.
    *
    * @return      the availability date of the film.
    */
    public Date getAvaliabilityDate()
    {
        return film_avaliability_date;
    }


}