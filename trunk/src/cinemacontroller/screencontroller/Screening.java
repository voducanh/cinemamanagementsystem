package cinemacontroller.screencontroller;

import java.util.Calendar;
import java.util.GregorianCalendar;

import timeanddate.Date;
import timeanddate.Time;
import cinemacontroller.filmcontroller.Film;

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
	
	/**
	 * Constructor for the Screening class. Sets up the state of the screening.
	 */
	public Screening(Film film, Date screening_date, Time start_time){
		
		// Clear the calendar so we are only dealing with hours, minutes and seconds if required.
		// this.screening_start_time.clear();
		
		// Sets the information about the screening.
		this.setDate(screening_date);
		this.setStartTime(start_time);
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
	public void setDate(Date date){
		// Set the date for the screening
		this.screening_date.set(Calendar.DAY_OF_MONTH, date.getDay());
		this.screening_date.set(Calendar.MONTH, date.getMonth());
		this.screening_date.set(Calendar.YEAR, date.getYear());
	}
	
	/**
	 * Function to set the time that the screening will be starting at.
	 * 
	 * @param hours
	 * @param mins
	 */
	public void setStartTime(Time start_time){		
		this.screening_date.set(Calendar.HOUR_OF_DAY, start_time.getHourOfDay());
		this.screening_date.set(Calendar.MINUTE, start_time.getMinute());
	}
	
	
	/**
	 * Returns the date at which the screening will be shown.
	 * 
	 * @return
	 */
	public Date getDate(){		
		return new Date(this.screening_date.get(Calendar.DAY_OF_MONTH), this.screening_date.get(Calendar.MONTH), this.screening_date.get(Calendar.YEAR));
	}
	
	/**
	 * Returns the time at which the screening will start at.
	 * 
	 * @return
	 */
	public Time getStartTime(){
		return new Time(this.screening_date.get(Calendar.HOUR_OF_DAY), this.screening_date.get(Calendar.MINUTE));
	}
	
	/**
	 * Returns the time at which the screening will be ending at.
	 * 
	 * @return
	 */
	public Time getEndTime(){

		Time film_end_time = screening_film.getLength();
		
		film_end_time.setHourOfDay(screening_film.getLength().getHourOfDay() + this.screening_date.get(Calendar.HOUR_OF_DAY));
		film_end_time.setMinute(screening_film.getLength().getMinute() + this.screening_date.get(Calendar.MINUTE));
		
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
