package cinemacontroller.filmcontroller;

import databasecontroller.MySQLController;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;
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
            
        try {
            
            // Get all the films stored in the database
            this.getFilmsFromDatabase();

        } catch(Exception e){}
	}

    public int generateID(){
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
	public void addFilmToCinema(String film_title, String film_director, String film_bbfc_rating, Date film_available_date, Time film_length, int film_expected_views_per_day, double film_ticket_price) throws Exception{
		Film new_film = new Film(this.generateID(), film_title, film_director, film_bbfc_rating, film_available_date);
		
		// Set some information related to the film
		new_film.setLength(new Time(2,30));
		new_film.setExpectedAudienceFigures(film_expected_views_per_day);
		
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
	
	/**
	 * Returns and ArrayList of all the films stored in the cinema management system.
	 * 
	 * @return
	 */
	public ArrayList<Film> getAllFilms(){
		return this.cinema_film_list;
	}
	
	/**
	 * Returns a film if stored in the cinemas database that contains a specified title.
	 * 
	 * @param title
	 * @return
	 * @throws Exception
	 */
	public Film getFilmByTitle(String title) throws Exception{
		// Cycle through all the films stored in the cinemas film database.
		for(Film film : this.cinema_film_list){
			// If the current film has the same title as expected, return the film.
			if(film.getTitle().equals(title)){
				return film;
			}
		}
		// Throw and exception if the film is not stored in the database.
		throw new Exception();
	}
	
	/**
	 * Returns a list of films by a specific director.
	 * 
	 * @param director
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Film> getFilmByDirector(String director) throws Exception{
		ArrayList<Film> found_films = new ArrayList<Film>();
		
		// Cycle all the films in the cinema's database.
		for(Film film : this.cinema_film_list){
			if(film.getDirector().equals(director)){
				// If a film by a specific director has been found, add it to found films.
				found_films.add(film);
			}
		}
		
		// Throw an exception if no film has been found
		if(found_films.size() == 0){
			throw new Exception();
		}
		
		return found_films;		
	}

    /**
     * Function populates the list of films stored internally with the films stored in a database.
     * 
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public void getFilmsFromDatabase() throws ClassNotFoundException, SQLException {
        String table_name = "film_main_database";

        // Create a new mysql connection and query database
        MySQLController connection = new MySQLController();
        ResultSet result = connection.getData("SELECT * FROM `" + table_name + "`");

        // Populate the internal list of films by cycle through each film in database
        while(result.next()){
            Date current_date = new Date(result.getInt("film_availability_date_day"), result.getInt("film_availability_date_month"), result.getInt("film_availability_date_year"));
            Film current_film = new Film(result.getInt("film_id"), result.getString("film_title"), result.getString("film_director"), result.getString("film_bbfc_rating"), current_date);
            current_film.setLength(new Time(2,30));
            cinema_film_list.add(current_film);
        }
    }


    public Film getFilmByID(int unique_id){
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

    public void addFilmToDatabase(){
        try {
            MySQLController connection = new MySQLController();
            connection.putData("INSERT INTO `film_main_database` VALUES ('','','','','','','','','')");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FilmManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FilmManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

       
}
