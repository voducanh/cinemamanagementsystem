package cinemacontroller;

import javax.swing.UIManager;


/**
 * TESTER
 * 
 * Simple main class to test all the other classes and functions. Will be updated to the "bootstrapper"
 * that will set up the GUI and the rest of the core components.
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
		
		// Set the default look and feel of the window to be the native OS look and feel.
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		// Create a new GUI for the system
		NewJFrame graphicaluserinterface = new NewJFrame();
		
		// Set the default controller of the GUI
		graphicaluserinterface.setController(new CinemaSystemController());
		
		// Show the GUI
		graphicaluserinterface.setVisible(true);
		
		
	}

}
