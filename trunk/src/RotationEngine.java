

public class RotationEngine {
	
	

	public RotationEngine(){
		
	}
	
	public void checkForNewRotations(Screen screen){
		
		// WORKING ON!!!!! PROBABLY BOLLOCKS
		
		// Cycle all the films in all screens and check if their viewing figures are close to their
		// expected viewing figures.
		
		
		// If under achieving, mark it for rotation.
		// If over achieving, mark it to be moved to a larger screen (if available).
		
		for(Screening screening : screen.getScreenings()){
			try {
		
				
			int film_viewing_figures = screening.getFilm().getViewingFigures(new Date(14,3,2009).toString());
			int film_expected_viewing_figures = screening.getFilm().getExpectedAudienceFigures();
			
			
			if(film_viewing_figures < film_expected_viewing_figures){
				System.out.println("Step 1: Move to a less popular screen");
				System.out.println("Step 2: Create a new marketing strategy to premote the film");
			}
			
			if(film_viewing_figures > film_expected_viewing_figures){
				System.out.println("Step 1: Move to a larger more popular screen");
				System.out.println("Step 2: Create more screenings for film");
			}
			
			}catch(Exception e){
				System.out.println("Error Retriving stats");
			}
			
		}
			
		
	}
	
	public void performNewRotations(){
		
	}
	
	public void moveFilm(){
		
	}
		
}
