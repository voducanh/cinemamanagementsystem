package cinemacontroller.rotationalcontroller;

import java.util.ArrayList;


import cinemacontroller.filmcontroller.Film;

/**
 * Provides all the functionality required for checking if films need to be
 * rotated based on their audience figures.
 * 
 * @author Scott
 */
public class RotationEngine {
	
	

    /**
     * Constructor for the rotational controller.
     */
    public RotationEngine(){
		
	}
	
    /**
     * Runs all the actions that the rotational controller feels are nessasary to
     * continue premoting the film properly.
     * 
     * @param films
     * @throws java.lang.Exception
     */
    public void runRotations(ArrayList<Film> films) throws Exception{
		// Cycle through all the films on database
		for(Film current_film : films){
			// Compare daily viewing figures to expected daily viewing figures
		//	if(current_film.getViewingFigures("12/12/2009") < current_film.getExpectedAudienceFigures()){
				
				// Function calls to rotate films here
				
			//}
		}
	}	
	
	
	/**
     * Checks what films need to be rotated.
     */
	private void findRequiredActions(){
		
		// If Screen turn-out is 50% BELOW target figures
				// Make more screenings
				// Move to smaller screens
				// Mark for promotion
		
		// If Screen turn-out is 25% BELOW target figures
				// Make more screenings
				// Move to smaller screens
		
		// If Screen turn-out is ON TARGET
				// Put your feet up and have a beer
		
		// If Screen turn-out is 25% ABOVE target figures
				// More Screenings
				// Move to LARGER screens
		
		// If Screen turn-out is 50% ABOVE target figures
				// More Screenings
				// Move to LARGER screens
	}
	
	
		
}
