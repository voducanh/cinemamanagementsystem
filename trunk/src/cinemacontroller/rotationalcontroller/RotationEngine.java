package cinemacontroller.rotationalcontroller;

import cinemacontroller.filmcontroller.Film.TYPE;
import java.util.ArrayList;


import cinemacontroller.filmcontroller.Film;
import cinemacontroller.main.CinemaSystemController;
import cinemacontroller.screencontroller.Screen;
import cinemacontroller.screencontroller.Screening;
import java.util.GregorianCalendar;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 * Provides all the functionality required for checking if films need to be
 * rotated based on their audience figures.
 * 
 * @author Scott
 */
public class RotationEngine {
	
	private CinemaSystemController controller;
    private DefaultListModel list;

    /**
     * Constructor for the rotational controller.
     */
    public RotationEngine(CinemaSystemController controller, DefaultListModel list){
		this.controller = controller;
        this.findRequiredActions();
        this.list=list;
	}
	
    /**
     * Runs all the actions that the rotational controller feels are nessasary to
     * continue premoting the film properly.
     * 
     * @param films
     * @throws java.lang.Exception
     */
    public void runRotations(ArrayList<Film> films) throws Exception{
		
	}	
	
	
	/**
     * Checks what films need to be rotated.
     */
	private void findRequiredActions(){
        
        int counter = 0;
        for(Screening screening : controller.screen_controller.getScreenings()){
            list.addElement( screening.getFilm().getTitle());
            counter++;
        }
	}

    public int getPercentage(int value, int devisor){
        return (devisor / value) * 100;
    }

    public void moveScreening(Screening screening, Screen from, Screen to){
        // Get the current screen's ID and remove the screening from it
        int screen_from_id = from.getID();
        controller.screen_controller.getScreen(screen_from_id).removeScreening(screening);

        // Get the new screens ID and add the screening to it
        int screen_to_id = to.getID();
        controller.screen_controller.getScreen(screen_to_id).addScreening(screening);

        // Update the screenings screen
        screening.setScreen(to);
    }

    /**
     * Removes a screening from a screen.
     * @param screening
     */
    public void removeScreening(Screening screening){
        this.controller.screen_controller.removeScreening(screening);
    }
	
		
}
