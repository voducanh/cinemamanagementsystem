package cinemacontroller.main;

import cinemacontroller.*;
import cinemacontroller.gui.MainWindow;
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
		// Create a new GUI for the system and set controller
		MainWindow main_window = new MainWindow(new CinemaSystemController());
		// Show the GUI
		main_window.setVisible(true);

	}

}
