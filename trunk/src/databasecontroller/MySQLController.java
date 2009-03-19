package databasecontroller;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

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
    private String server_database;

    private Connection connection_stream = null;

	/**
	 * Sets up the class for interfacing with the oracle database.
	 *
     * @throws SQLException
	 */

     public MySQLController() throws SQLException {
        // Setup all the required settings for the controller to connect to
        this.server_host = "localhost";
        this.server_username = "root";
        this.server_password = "";
        this.server_port = 3306;
        this.server_database = "cinemamanagementsystem";

        // Connect to the database
        this.connect();
	}

     /**
      * Connects to the MySQL database server. Throws errors if unable to.
      * 
      * @throws java.sql.SQLException
      */
	private void connect() throws SQLException{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch(Exception e){
            System.out.println("Unable to laod the MySQL driver file. Please make sure it is availiable in /lib/ folder.");
        }

        try {
            this.connection_stream = DriverManager.getConnection("jdbc:mysql://" + server_host + ":" + server_port + "/" + server_database, server_username, server_password);
        }catch(Exception e){
            System.out.println("Unable to connect to MySQL database. Check username, password and host name.");
            throw new SQLException();
        }
	}

    /**
     * Disconnects from MySQL server.
     */
    public void disconnect(){
        try {
            this.connection_stream.close();
        } catch (SQLException ex) {
            System.out.println("Unable to disconnect from database.");
        }
    }

    /**
     * Sends data to the MySQL server.
     * 
     * @param query_string
     * @throws java.sql.SQLException
     */
    public void putData(String query_string) throws SQLException{
            Statement st = this.connection_stream.createStatement();
            st.executeUpdate(query_string);
	}

    /**
     * Gets data from the MySQL server and returns a result set.
     * @param query_string
     * @return
     * @throws java.sql.SQLException
     */
    public ResultSet getData(String query_string) throws SQLException{
            Statement st = this.connection_stream.createStatement();
            return st.executeQuery(query_string);
	}

}
