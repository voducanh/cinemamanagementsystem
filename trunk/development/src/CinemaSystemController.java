import java.util.ArrayList;

/**
 * Main controller class that deals with all the management of the cinema
 * including its screens, screenings and its films - also handling the rotational,
 * statistical and recording system. 
 * 
 * @author Scotty
 *
 */
public class CinemaSystemController {
	private ArrayList<Screen> cinema_screens;
	
	public CinemaSystemController(){
		// Set up an ArrayList that contains all the screens
		cinema_screens = new ArrayList<Screen>();
	}

	
	
	/** 
	 * This function's job is to setup all the screens that the cinema will have and add
	 * them to the list of screens ArrayList.
	 */
	public void setupCinemaScreens(){
		int id_counter = 1;
		
		// Setup all the thousand seater screens
		for(int counter = 0; counter < 2; counter++){
			cinema_screens.add(new Screen(id_counter, 1000));
			id_counter++;
		}
		
		// Setup all the thousand seater screens
		for(int counter = 0; counter < 4; counter++){
			cinema_screens.add(new Screen(id_counter, 500));
			id_counter++;
		}
		
		// Setup all the thousand seater screens
		for(int counter = 0; counter < 4; counter++){
			cinema_screens.add(new Screen(id_counter, 200));
			id_counter++;
		}
		
		// Setup all the thousand seater screens
		for(int counter = 0; counter < 2; counter++){
			cinema_screens.add(new Screen(id_counter, 100));
			id_counter++;
		}
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
		new_film.setExpectedViewingFigures(film_expected_views_per_day);
		
		// Set the film ticket price
		new_film.setTicketPrice(film_ticket_price);
		
		// Below will be the code to find out of the film is popular, where to place it and at what times.
		
	}
	
	
}
