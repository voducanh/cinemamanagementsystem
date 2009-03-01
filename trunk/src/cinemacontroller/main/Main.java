package cinemacontroller.main;

import cinemacontroller.*;
import cinemacontroller.gui.MainWindow;
import cinemacontroller.gui.LoginDialog;
import databasecontroller.MySQLController;
import javax.swing.JOptionPane;
import javax.swing.UIManager;


/**
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
        

        // Check there is a database that the program is able to connect to
        try {
            new MySQLController();

            // Create a new GUI for the system and set controller
            MainWindow main_window = new MainWindow(new CinemaSystemController());
            main_window.setVisible(true);

            // Show the login dialog
            new LoginDialog(main_window, true).setVisible(true);
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Unfortunately no MySQL database was available to connect too. \nAs a result, the program will now exit.", "Database Connection Unavailable", JOptionPane.WARNING_MESSAGE);
        }
	
        
	}

}
