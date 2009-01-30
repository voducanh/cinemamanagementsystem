
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
	private String screening_date;
	private Time screening_start_time;
	
		
	/**
	 * Constructor for the Screening class. Sets up the state of the screening.
	 */
	public Screening(Film film, String screening_date, Time start_time){
		
		// Clear the calendar so we are only dealing with hours, minutes and seconds if required.
		// this.screening_start_time.clear();
		
		// Sets the information about the screening.
		this.setFilm(film);
		this.setScreeningDate(screening_date);
		this.setStartTime(start_time);
	}
	
	/**
	 * Sets the date at which the screening will be shown.
	 * 
	 * @param screening_date
	 */
	public void setScreeningDate(String screening_date){
		this.screening_date = screening_date;
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
	 * Function to set the time that the screening will be starting at.
	 * 
	 * @param hours
	 * @param mins
	 */
	public void setStartTime(Time start_time){		
		this.screening_start_time = start_time;
	}
	
	/**
	 * Returns the date at which the screening will be shown.
	 * 
	 * @return
	 */
	public String getDate(){
		return this.screening_date;
	}
	
	/**
	 * Returns the time at which the screening will start at.
	 * 
	 * @return
	 */
	public Time getStartTime(){
		return this.screening_start_time;
	}
	
	/**
	 * Returns the time at which the screening will be ending at.
	 * 
	 * @return
	 */
	public Time getEndTime(){

		Time film_end_time = screening_film.getLength();
		
		film_end_time.setHourOfDay(screening_film.getLength().getHourOfDay() + screening_start_time.getHourOfDay());
		film_end_time.setMinute(screening_film.getLength().getMinute() + screening_start_time.getMinute());
		
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
