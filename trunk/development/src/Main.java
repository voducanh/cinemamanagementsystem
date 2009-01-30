/**
 * TESTER
 * 
 * Simple main class to test all the other classes and functions. Will be updated.. obviously
 * 
 * @author Scott
 *
 */

public class Main {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		// Create a new screen
		Screen screen = new Screen(1, 5000);
		
		// Create a new film
		Film film = new Film("007 Live and Let Die", "Steven Pielberg", "PG", null);
		film.setLength(new Time(1,30));
		film.setExpectedViewingFigures(12);
		film.updateViewingFigures(new Date(14,3,2009).toString(), 4000);
		
		// Create the new screening date for the showing
		Date screening_date = new Date(14,3,2009);

		
		// Create a new screening
		Screening screening = new Screening(film, screening_date.toString(), new Time(2,00));
		
		// Add the screening to the screen
		screen.addScreening(screening);
		
		
		RotationEngine rotater = new RotationEngine();
		rotater.checkForNewRotations(screen);
		
		//*****************************************************************************************************/
		// TEST FUNCTON CALLS
		
		// Testing to see if the screen is available at a certain time on a certain date
		// System.out.println("Is the screen free to place new screening? " + screen.checkScreenFree(null, new Time(1,35), new Time(3,35)));
		
		
		
	}

}
