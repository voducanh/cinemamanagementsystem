import java.util.ArrayList;

/**
 * 
 * This class contains all the fetch, get and update commands to interface with the oracle
 * database.
 * 
 * @author Scott
 *
 */
public class OracleController {
	private String server_host;
	private String server_username;
	private String server_password;
	private int server_port;
	
	/**
	 * Sets up the class for interfacing with the oracle database.
	 * 
	 * @param server_host
	 * @param server_username
	 * @param server_password
	 * @param server_port
	 */
	public OracleController(String server_host, String server_username, String server_password, int server_port){
		// Setup all the required settings for the controller to connect to
		this.server_host = server_host;
		this.server_username = server_username;
		this.server_password = server_password;
		this.server_port = server_port;
	}
	
	public void connect(){
		
	}
	
	public void disconnect(){
		
	}
	
	public void putData(){
		
	}
	
	public ArrayList<String> getData(){
		return new ArrayList<String>();
	}
	
	public int countRows(String table_name){
		return 0;
	}
}
