package cinemacontroller.rotationalsystem;

import java.util.ArrayList;


import cinemacontroller.filmcontroller.Film;

public class RotationEngine {
	
	

	public RotationEngine(){
		
	}
	
	public void runRotations(ArrayList<Film> films) throws Exception{
		// Cycle through all the films on database
		for(Film current_film : films){
			// Compare daily viewing figures to expected daily viewing figures
		//	if(current_film.getViewingFigures("12/12/2009") < current_film.getExpectedAudienceFigures()){
				
				// Function calls to rotate films here
				
			//}
		}
	}	
	
	
	
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
	
	
	
	public void moveFilm(){
		
	}
		
}
