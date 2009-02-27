package cinemacontroller.filmcontroller;

import databasecontroller.MySQLController;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.GregorianCalendar;

/**
 * Provides all the functionality required to maintain and manage a list of films
 * that the cinema can screen at any time.
 * 
 * @author Scott
 *
 */
public class FilmController {
	
	private ArrayList<Film> cinema_film_list;
	
	public FilmController(){
        // Create a new ArrayList which will store ALL the films the cinema can show
        this.cinema_film_list = new ArrayList<Film>();
    }

    /**
     * Generates a uniqueid by getting 'what would be' the next id in database or generates a
     * fall back id.
     * 
     * @return
     */
    public static int generateUniqueId(){

        try {
             // Create a new mysql connection and query database
            MySQLController connection = new MySQLController();
            ResultSet result = connection.getData("SHOW TABLE STATUS LIKE 'main_film_list'");

            if(result.next()){
                return result.getInt("Auto_increment");
            }
            
        }catch(Exception e){ }

        // Fall back id number
        java.sql.Timestamp  sqlDate = new java.sql.Timestamp(new java.util.Date().getTime());
        return Integer.parseInt(sqlDate.toString());

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
	public void addFilmToCinema(String film_title, String film_director, String film_bbfc_rating, GregorianCalendar film_available_date, GregorianCalendar film_length, int film_expected_views_per_day, double film_ticket_price) throws Exception{
 
        Film new_film = new Film(generateUniqueId(), film_title, film_director, film_bbfc_rating, film_length, film_available_date);
		new_film.setExpectedAudienceFigures(film_expected_views_per_day);
		
		// Add the film to the film manager's internal database
		this.cinema_film_list.add(new_film);
	}

    /**
     * Adds a film to the database.
     * 
     * @param film
     */
    public void addFilmToDatabase(Film film){
        this.cinema_film_list.add(film);
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

    /**
     * Removes a film from the database by uniqueid
     * @param title
     * @param director
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
	 * 
	 * @return
	 */
	public ArrayList<Film> getFilms(){
		return (ArrayList<Film>)this.cinema_film_list.clone();
	}

    /**
     * Returns a film by its uniqueid
     *
     * @param unique_id
     * @return
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
     * Function populates the list of films stored internally with the films stored in a database.
     * 
     * @throws java.sql.SQLException
     */
    public void getFilmsFromDatabase() throws SQLException {
         // Create a new mysql connection and query database
        MySQLController connection = new MySQLController();
        ResultSet result = connection.getData("SELECT * FROM main_film_list");

        // Populate the internal list of films by cycle through each film in database
        while(result.next()){

            // Get the availability date for the film
            GregorianCalendar start_date = new GregorianCalendar();
            start_date.setTimeInMillis(result.getDate("availability_date").getTime());

            // Get the film length
            GregorianCalendar film_length = new GregorianCalendar();
            film_length.setTimeInMillis(result.getTime("length").getTime());

            // Create a new film instance
            Film current_film = new Film(result.getInt("uniqueid"), result.getString("title"), result.getString("director"), result.getString("bbfc"), film_length, start_date);

            // Add the new film instance to the list of films in database
            cinema_film_list.add(current_film);
        }
    }
}
