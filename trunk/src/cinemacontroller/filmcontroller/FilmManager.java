package cinemacontroller.filmcontroller;
import java.util.ArrayList;

import timeanddate.Date;
import timeanddate.Time;

/**
 * Provides all the functionality required to maintain and manage a list of films
 * that the cinema can screen at any time.
 * 
 * @author Scott
 *
 */
public class FilmManager {
	
	private ArrayList<Film> cinema_film_list;
	
	public FilmManager(){
		// Create a new ArrayList which will store ALL the films the cinema can show
		cinema_film_list = new ArrayList<Film>();
	}
	
	/**
	 * This function's job is to add any desired film to the list of films that the
	 * cinema system contains.
	 * 
	 * @param film_title
	 * @param film_director
	 * @param film_bbfc_rating
	 * @param film_available_date
	 * @param film_length
	 * @param film_expected_views_per_day
	 * @param film_ticket_price
	 * @throws Exception
	 */
	public void addFilmToCinema(String film_title, String film_director, String film_bbfc_rating, Date film_available_date, Time film_length, int film_expected_views_per_day, double film_ticket_price) throws Exception{
		Film new_film = new Film(film_title, film_director, film_bbfc_rating, film_available_date);
		
		// Set some information related to the film
		new_film.setLength(film_length);
		new_film.setExpectedAudienceFigures(film_expected_views_per_day);
		
		// Set the film ticket price
		new_film.setTicketPrice(film_ticket_price);
		
		// Add the film to the film manager's internal database
		this.cinema_film_list.add(new_film);
	}
	
	/**
	 * Removes the selected film from the internal database.
	 * 
	 * @param film
	 */
	public void removeFilm(Film film){
		// Make sure the film actually exists in the database
		if(cinema_film_list.contains(film)){
			// Remove the film from the internal database
			this.cinema_film_list.remove(film);
		}
	}
	
}
