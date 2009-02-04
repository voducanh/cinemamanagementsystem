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
		
		UIManager.setLookAndFeel(
	            UIManager.getSystemLookAndFeelClassName());
		
		new NewJFrame().setVisible(true);
		
	}

}
