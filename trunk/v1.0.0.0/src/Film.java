import java.util.Calendar;


public class Film {

    private String film_title;
    private String film_director;
    private String film_bbfc_rating;
    private String film_expected_popularity;
    private Calendar film_length;
    private double film_ticket_price;
    private int film_viewing_figures;

    private Calendar film_avaliability_date;

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
    public Film(String film_title, String film_director, String film_bbfc_rating, Calendar film_avaliability_date) throws Exception
    {
        // Set all the required information
        this.setFilmTitle(film_title);
        this.setFilmDirector(film_director);
        this.setBBFCRating(film_bbfc_rating);
        this.setFilmavaliabilityDate(film_avaliability_date);
    }

    /**
    * Sets the current films's title.
    *
    */
    public void setFilmTitle(String film_title) throws Exception
    {
        // Check to see if the film title is of a correct length
        if (film_title.length() < 1)
        {
            throw new Exception();
        }
        
        // Set the film title
        this.film_title = film_title;
        
    }

    /**
    * Sets the current film's director.
    *
    * @param  film_title the new director of the film.
    */
    public void setFilmDirector(String film_director) throws Exception
    {
        if (film_director.length() < 1)
        {
            throw new Exception();
        }

        // Set the films director
        this.film_director = film_director;
    }


    /**
    * Sets the current film's BBFC rating.
    * 
    * @param  film_bbfc_rating  the new BBFC rating for the film.
    */
    public void setBBFCRating(String film_bbfc_rating) throws Exception
    {
        if (film_bbfc_rating.length() < 1)
        {
            throw new Exception();
        }

        // Set the films BBFC rating
        this.film_bbfc_rating = film_bbfc_rating;
    }


    /**
    * Simply sets the expected popularity of the film.
    *
    * @param      expected popularity of the film.
    */
    public void setExpectedPopularity(String film_expected_popularity) throws Exception
    {
        if (film_expected_popularity.length() < 1)
        {
            throw new Exception();
        }

        // Sets the films expected popularity
        this.film_expected_popularity = film_expected_popularity;
    }

    /**
    * Simply sets the length of the film.
    *
    * @param      film length.
    */
    public void setLength(int hours, int mins) throws Exception
    {
        Calendar time = Calendar.getInstance();
        time.set(0, 0, 0, 0, 0, 0);
        time.set(Calendar.HOUR, hours);
        time.set(Calendar.MINUTE, mins);
        this.film_length = time;
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
    public void setViewingFigures(int film_viewing_figures) throws Exception
    {
        if (film_viewing_figures < 1)
        {
            throw new Exception();
        }

        this.film_viewing_figures = film_viewing_figures;
    }

    /**
    * Sets the current film's availability date.
    * 
    * @param  film_avaliability_date  the new availability date of the film.
    */
    public void setFilmavaliabilityDate(Calendar film_avaliability_date) throws Exception
    {
       // if (film_avaliability_date.Day.ToString == null || film_avaliability_date.Month.ToString == null || film_avaliability_date.Year.ToString == null)
       // {
       //     throw new Exception();
      //  }

        // Set the films availability date
        this.film_avaliability_date = film_avaliability_date;
    }

    /**
    * Simply returns the title of the film object in a string based format.
    *
    * @return      the title of the film.
    */
    public String getFilmTitle()
    {
        return film_title;
    }

    /**
    * Returns the main director of the film in a string based format.
    *
    * @return      the director of the film.
    */
    public String getFilmDirector()
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
    public String getExpectedPopularity()
    {
        return film_expected_popularity;
    }

    /**
    * Simply returns the expected popularity of the film.
    *
    * @return      expected length of the film.
    */
    public Calendar getFilmLength()
    {
        return film_length;
    }

    /**
    * Returns the ticket price of the current film.
    *
    * @return      the ticket price.
    */
    public double getTickePrice()
    {
        return film_ticket_price;
    }

    /**
    * Returns the total number of viewers of the current film.
    *
    * @return      the number of people to view the film.
    */
    public double getViewingFigures()
    {
        return film_viewing_figures;
    }

    /**
    * Returns the total amount of money made from the showing of the film.
    *
    * @return      total money made from film.
    */
    public double getTotalFilmProfits()
    {
        return this.film_viewing_figures * this.film_ticket_price;
    }

    /**
    * Returns the availability date of the film in a Calendar format.
    *
    * @return      the availability date of the film.
    */
    public Calendar getAvaliabilityDate()
    {
        return film_avaliability_date;
    }


}
