package cinemacontroller.main;

import cinemacontroller.*;
import cinemacontroller.gui.MainWindow;
import cinemacontroller.gui.NewJDialog;
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

        boolean connection_pass = false;
        

        // SI
        try {
            MySQLController test_connector = new MySQLController();
            test_connector.connect();
            connection_pass = true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Unfortunately no MySQL database was available to connect too. \n As a result, the program will now exit.", "Database Connection Unavailable", JOptionPane.WARNING_MESSAGE);
        }

        if(connection_pass == true){
            // Create a new GUI for the system and set controller
            MainWindow main_window = new MainWindow(new CinemaSystemController());
            main_window.setVisible(true);

            new NewJDialog(main_window, true).setVisible(true);
        }

		
        
	}

}
