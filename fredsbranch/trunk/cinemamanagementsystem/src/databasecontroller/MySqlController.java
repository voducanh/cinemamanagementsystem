package databasecontroller;

import java.sql.*;

/**
 *
 * This class contains all the fetch, get and update commands to interface with the oracle
 * database.
 *
 * @author Scott
 *
 */

public class MySqlController {
	
	private static MySqlController instance = null;
 	private Connection connection = null;
    private String server_host;
    private String server_username;
    private String server_password;
    private int server_port;
    private String server_database;


	/**
	 * Sets up the class for interfacing with the oracle database.
	 *
	 * @param server_host
	 * @param server_username
	 * @param server_password
	 * @param server_port
	 */
     
     private MySqlController() throws SQLException {
    	 //Setup all the required settings for the controller to connect to
    	 
         this.server_host = "localhost";
         this.server_username = "root";
         this.server_password = "";
         this.server_port = 3306;
         this.server_database = "cinemamanagementsystem";
         
         try {
             Class.forName("com.mysql.jdbc.Driver");
         }
         catch(Exception e){
             System.out.println("Unable to load the MySQL driver file. Please make sure it is availiable in /lib/ folder.");
         }

         try {
             this.connection = DriverManager.getConnection("jdbc:mysql://"+server_host+"/"+server_database, server_username, server_password);
         }
         catch(Exception e){
             System.out.println("Unable to connect to MySQL database. Check username, password and host name.");
             throw new SQLException();
         }
	 }


	public static synchronized MySqlController getInstance() throws SQLException{
	        if (instance == null)
	        	instance = new MySqlController();
	        
	    return instance;
	}

	public void disconnect() {
		try {
			connection.close();
		}
		catch (SQLException e) {
			System.out.println("Unable to disconnect from database.");
		}
	}
	
 	public Connection getConnnection() {
 		return connection;
 	}

	public void putData(String query_string) throws SQLException{
            Statement st = this.connection.createStatement();
            st.executeUpdate(query_string);
	}

	public ResultSet getData(String query_string) throws SQLException{
            Statement st = this.connection.createStatement();
            return st.executeQuery(query_string);
	}

}
