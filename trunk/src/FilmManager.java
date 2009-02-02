import java.util.ArrayList;

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
	 * Adds a new film to the internal film database
	 * 
	 * @param film_name
	 * @param film_director
	 * @param BBFC_rating
	 * @param film_length
	 */
	public void addNewFilm(String film_name, String film_director, String BBFC_rating, Time film_length, Date film_availability_date){
		// Create a new film object
		Film film = new Film(film_name, film_director, BBFC_rating, film_availability_date);
		// Add the new film object to the internal database
		this.cinema_film_list.add(film);
	}
	
	/**
	 * Removes the selected film from the internal database
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
	
	/**
	 * Creates a new film screening.
	 * 
	 * @param film
	 * @param date
	 * @param time
	 * @throws Exception
	 */
	public void createFilmScreening(Film film, Date date, Time time) throws Exception{
		// Check if the availability date is before the date/time it is request to be shown
		if(!date.before(film.getAvaliabilityDate())){
			throw new Exception();
		}else{
			// Create a new screening
			new Screening(film, date.toString(), time);
		}
	}
	
}
