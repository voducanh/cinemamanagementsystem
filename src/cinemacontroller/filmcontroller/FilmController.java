package cinemacontroller.filmcontroller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Provides all the functionality required to maintain and manage a list of films
 * that the cinema can screen at any time. Provides functionality to get films, edit
 * and update films as well as delete them and pull film database from a MySQL database
 * via the MySQl database controller.
 * 
 * @author Scott
 *
 */
public class FilmController {
	
	private ArrayList<Film> cinema_film_list;
    /**
     * Provides access to the MySQL database and contains functions relating to
     * the film controller.
     */
    public FilmDatabaseController film_database_controller;

    /**
     * Setup the film controller's internal database as well as the handler
     * for the MySQL database.
     */
	public FilmController(){
        // Create a new ArrayList which will store ALL the films the cinema can show
        this.cinema_film_list = new ArrayList<Film>();
        this.film_database_controller = new FilmDatabaseController();
    }

    /**
     * Generates a uniqueid by getting 'what would be' the next id in database or generates a
     * fall back id.
     * 
     * @return a unique integer number
     */
    public static int generateUniqueId(){
        String returnme = GregorianCalendar.getInstance().getTimeInMillis() + "";
        return Integer.parseInt(returnme.substring(returnme.length() - 9, returnme.length()));
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
     * @param color
	 * @throws Exception
	 */
	public void addFilm(String film_title, String film_director, String film_bbfc_rating, GregorianCalendar film_available_date, GregorianCalendar film_expiration_date, GregorianCalendar film_length, int film_expected_views_per_day, String color, Film.TYPE film_type) throws Exception{
 
        Film new_film = new Film(generateUniqueId(), film_title, film_director, film_bbfc_rating, film_length, film_available_date, film_expiration_date, color, film_type);
		
		// Add the film to the film manager's internal database
		this.cinema_film_list.add(new_film);
	}

    /**
     * Adds a film to the database.
     * @param film
     */
    public void addFilm(Film film){
        this.cinema_film_list.add(film);
    }
	
	/**
	 * Removes the selected film from the internal database.
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
     * Removes a film from the database by uniqueid
     * @param uniqueid
     */
    public void removeFilm(int uniqueid){
        // Cycle through all the films stored and compare the uniqueid
        for(Film current_film : this.cinema_film_list){

            if(current_film.getID() == uniqueid){
                // Remove film if found
                this.cinema_film_list.remove(current_film);
            }

        }
    }

	/**
	 * Returns a clone of the ArrayList of all the films stored in the cinema management system.
	 * @return arraylist of films
	 */
	public ArrayList<Film> getFilms(){
		return (ArrayList<Film>)this.cinema_film_list.clone();
	}

    /**
     * Returns a film by its uniqueid
     * @param unique_id
     * @return film object
     */
    public Film getFilm(int unique_id){
        Film return_film = null;

        // Cycle all the films in the cinema's database.
        for(Film film : this.cinema_film_list){
            if(film.getID() == unique_id){
                // If a film by a specific director has been found, add it to found films.
                return_film = film;
            }
        }
        return return_film;
    }

    /**
     * Updates a film in the film arraylist.
     * 
     * @param current_film
     * @param new_film
     */
    public void updateFilm(int current_film_id, Film new_film){
        int index = 0;
        for(Film current_film : this.cinema_film_list){
            if(current_film.getID() == current_film_id){
                index = this.cinema_film_list.indexOf(current_film);
                this.cinema_film_list.set(index, new_film);
            }
        }
    }
    
    /**
     * Retrives all the films from the database and loads it internall into the program.
     * @throws java.sql.SQLException
     */
    public void populateFilmListFromDatabase() throws SQLException {
        ArrayList<Film> films = this.film_database_controller.getFilms();

        for(Film current_film : films){
            this.addFilm(current_film);
        }
    }
    
}
