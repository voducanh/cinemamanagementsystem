package databasecontroller;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * 
 * This class contains all the fetch, get and update commands to interface with the oracle
 * database.
 * 
 * @author Scott
 *
 */

public class MySQLController {
	private String server_host;
	private String server_username;
	private String server_password;
	private int server_port;

    private Connection connection_stream = null;
	
	/**
	 * Sets up the class for interfacing with the oracle database.
	 * 
	 * @param server_host
	 * @param server_username
	 * @param server_password
	 * @param server_port
	 */
	public MySQLController(){
		// Setup all the required settings for the controller to connect to
		this.server_host = "localhost";
		this.server_username = "root";
		this.server_password = "";
		this.server_port = 3306;

        this.connect();
	} 
	
	public void connect(){
        try {

            Class.forName("com.mysql.jdbc.Driver");
            this.connection_stream = DriverManager.getConnection("jdbc:mysql://localhost/cinemamanagementsystem", "root", "");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading MySQL driver. MySQL functionality will not work.", "MySQL Controller Missing", JOptionPane.WARNING_MESSAGE);
        }
	}
	
	public void disconnect(){
		
	}
	
	public void putData(String query_string){
        try {
            Statement st = this.connection_stream.createStatement();
            st.executeQuery(query_string);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL Error: " + ex.toString(), "MySQL Error", JOptionPane.WARNING_MESSAGE);
        }
        
	}
	
	public ResultSet getData(String query_string){
        try {

            Statement st = this.connection_stream.createStatement();
            ResultSet rs = st.executeQuery(query_string);
            return rs;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL Error: " + ex.toString(), "MySQL Error", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        
	}
	
	public int countRows(String table_name){
		return 0;
	}
}
