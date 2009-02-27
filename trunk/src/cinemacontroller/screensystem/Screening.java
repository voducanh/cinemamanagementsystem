package cinemacontroller.screensystem;

import java.util.Calendar;
import java.util.GregorianCalendar;

import cinemacontroller.filmcontroller.Film;
import java.awt.Color;
import java.sql.Time;

/**
 * Screening Class
 * 
 * Provides functionality to manage the screenings of films. Screenings are films with dates
 * and times attached to them.
 * 
 * @author Scott
 *
 */

public class Screening {

	private Film screening_film;
	private GregorianCalendar screening_date;
    private GregorianCalendar screening_time;
    private Color background_color;
	
	/**
	 * Constructor for the Screening class. Sets up the state of the screening.
	 */
	public Screening(Film film, GregorianCalendar screening_date, GregorianCalendar start_time){
		
        this.screening_date = screening_date;
        this.screening_time = start_time;
        this.screening_film = film;

	}
	
	/**
	 * Function to set the film that will be screening.
	 * 
	 * @param film
	 */
	public void setFilm(Film film){
		this.screening_film = film;
	}
	
	/**
	 * Sets the date at which the screening will be shown.
	 * 
	 * @param screening_date
	 */
	public void setDate(GregorianCalendar date){
		// Set the date for the screening
		this.screening_date.set(Calendar.DAY_OF_MONTH, date.get(Calendar.DAY_OF_MONTH));
		this.screening_date.set(Calendar.MONTH, date.get(Calendar.MONTH));
		this.screening_date.set(Calendar.YEAR, date.get(Calendar.YEAR));
	}
	
	/**
	 * Function to set the time that the screening will be starting at.
	 * 
	 * @param hours
	 * @param mins
	 */
	public void setStartTime(GregorianCalendar start_time){
		this.screening_time = start_time;
	}


    public void setColor(Color color){
        this.background_color = color;
    }

    public Color getColor(){
        return this.background_color;
    }
	
	/**
	 * Returns the date at which the screening will be shown.
	 * 
	 * @return
	 */
	public GregorianCalendar getDate(){
		return this.screening_date;
	}
	
	/**
	 * Returns the time at which the screening will start at.
	 * 
	 * @return
	 */
	public GregorianCalendar getStartTime(){
		return this.screening_time;
	}
	
	/**
	 * Returns the time at which the screening will be ending at.
	 * 
	 * @return
	 */
	public GregorianCalendar getEndTime(){

		GregorianCalendar film_end_time = screening_film.getLength();

        film_end_time.add(Calendar.HOUR_OF_DAY, this.screening_time.get(Calendar.HOUR_OF_DAY));
        film_end_time.add(Calendar.MINUTE, this.screening_time.get(Calendar.MINUTE));
		
		return film_end_time;
		
	}

	/** 
	 * Returns the film
	 * 
	 * @return
	 */
	public Film getFilm(){
		return this.screening_film;
	}

 
}
