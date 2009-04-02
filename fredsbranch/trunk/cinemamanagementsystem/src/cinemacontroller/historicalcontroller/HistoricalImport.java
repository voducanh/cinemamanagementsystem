package cinemacontroller.historicalcontroller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import databasecontroller.MySqlController;

public class HistoricalImport implements Runnable {
	
	private HistoricalWaiting waiting;
	private String dateBegin = "";
	private String dateEnd = "";
	private String folder = "historicals\\";
	private ArrayList<String> nameFile = new ArrayList<String>();
	private ArrayList<String> nameFilePeriod = new ArrayList<String>();
	
	public HistoricalImport(HistoricalWaiting waiting, String date){
		this.dateBegin = date;
		this.waiting = waiting;
		
	}
	
	public HistoricalImport(HistoricalWaiting waiting, String dateBegin, String dateEnd){
		this.dateBegin = dateBegin;
		this.dateEnd = dateEnd;
		this.waiting = waiting;
		
	}
	
	public void run(){
		waiting.getJProgressBar().setIndeterminate(true);
		importData();
		waiting.getJProgressBar().setIndeterminate(false);
		waiting.dispose();
		JOptionPane.showMessageDialog(null, "Historical imported sucessfully.", "Import Historical", JOptionPane.INFORMATION_MESSAGE);

	}
	
    public void importData(){
    	

    	MySqlController connection;

		try {
			
			connection = MySqlController.getInstance();
			fileAllRepertory();
			
			//a day
			if(this.dateEnd.isEmpty()){
				
				for(int i=0;i<nameFile.size();i++){
					if(nameFile.get(i).startsWith(dateBegin)){

						parseOneFile(dateBegin);
					}
				}
				
			}
			else{
				//a period
				parsePeriodFile();
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		}

    }
    
    public void parseOneFile(String dateBegin) {

		String lineData = null;
		BufferedReader buffer = null;
		ArrayList<String> idScreen = new ArrayList<String>();
		ArrayList<String> idMovie = new ArrayList<String>();
		ArrayList<String> day = new ArrayList<String>();
		ArrayList<String> expectedBooking = new ArrayList<String>();
		ArrayList<String> nbSeatsBooked = new ArrayList<String>();

		File file = new File(dateBegin+"_Historical.xml");

		try {
			buffer = new BufferedReader(new FileReader(new File(getPath(file))));
			
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		try {
			while ( (lineData = buffer.readLine()) != null) {

				if (lineData.contains("<idScreen>")) {
					idScreen.add(lineData.replace("<idScreen>", "").replace("</idScreen>", "").trim());
				}
				else{
					if (lineData.contains("<idMovie>")) {
						idMovie.add(lineData.replace("<idMovie>", "").replace("</idMovie>", "").trim());
						
					}
					else{
						if (lineData.contains("<day>")) {
							day.add(lineData.replace("<day>", "").replace("</day>", "").trim());
							
						}
						else{
							if (lineData.contains("<expectedBooking>")) {
								expectedBooking.add(lineData.replace("<expectedBooking>", "").replace("</expectedBooking>", "").trim());
								
							}
							else{
								if (lineData.contains("<nbSeatsBooked>")) {
									nbSeatsBooked.add(lineData.replace("<nbSeatsBooked>", "").replace("</nbSeatsBooked>", "").trim());
									
								}
					
							}
				
						}
			
					}
		
				}

			}
			
			MySqlController connection = MySqlController.getInstance();
			
			for(int j=0;j<idScreen.size();j++){
//				System.out.println("SELECT * FROM TEMPHISTORICALTIMETABLES WHERE DAY='"+this.dateBegin+"' AND ID_SCREEN='"+idScreen.get(j)+"'");
				ResultSet r = connection.getData("SELECT * FROM TEMPHISTORICALTIMETABLES WHERE DAY='"+this.dateBegin+"' AND ID_SCREEN='"+idScreen.get(j)+"'");
				
			//System.out.println("SELECT * FROM MOVIES WHERE ID_MOVIE='"+idMovie.get(j)+"'");
				ResultSet r1 = connection.getData("SELECT * FROM MOVIES WHERE ID_MOVIE='"+idMovie.get(j)+"'");

				if(!r.next()){
					
					if(r1.next()){
						connection.putData("INSERT INTO TEMPHISTORICALTIMETABLES VALUES ('"+idScreen.get(j)+"','"+idMovie.get(j)+"','"+day.get(j)+"','"+expectedBooking.get(j)+"','"+nbSeatsBooked.get(j)+"')");
					}
					else{
						if(idMovie.get(j).equals("0")){
							connection.putData("INSERT INTO TEMPHISTORICALTIMETABLES VALUES ('"+idScreen.get(j)+"','"+idMovie.get(j)+"','"+day.get(j)+"','"+expectedBooking.get(j)+"','"+nbSeatsBooked.get(j)+"')");
						}
					}

				}

			}

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
			
		} catch (IOException e) {

			e.printStackTrace();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 finally {
			try {

				buffer.close();
				
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	}
    
    
    public void parsePeriodFile() {

		String lineData = null;
		BufferedReader buffer = null;
		ArrayList<String> idScreen = new ArrayList<String>();
		ArrayList<String> idMovie = new ArrayList<String>();
		ArrayList<String> day = new ArrayList<String>();
		ArrayList<String> expectedBooking = new ArrayList<String>();
		ArrayList<String> nbSeatsBooked = new ArrayList<String>();
		
		filePeriodRepertory();
		
		for(int i=0;i<nameFilePeriod.size();i++){
			
			idScreen.clear();
			idMovie.clear();
			day.clear();
			expectedBooking.clear();
			nbSeatsBooked.clear();


			File file = new File(nameFilePeriod.get(i));

			try {
				buffer = new BufferedReader(new FileReader(new File(getPath(file))));
				
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
			try {
				while ( (lineData = buffer.readLine()) != null) {

					if (lineData.contains("<idScreen>")) {

						idScreen.add(lineData.replace("<idScreen>", "").replace("</idScreen>", "").trim());
					}
					else{
						if (lineData.contains("<idMovie>")) {
							idMovie.add(lineData.replace("<idMovie>", "").replace("</idMovie>", "").trim());
							
						}
						else{
							if (lineData.contains("<day>")) {
								day.add(lineData.replace("<day>", "").replace("</day>", "").trim());
								
							}
							else{
								if (lineData.contains("<expectedBooking>")) {
									expectedBooking.add(lineData.replace("<expectedBooking>", "").replace("</expectedBooking>", "").trim());
									
								}
								else{
									if (lineData.contains("<nbSeatsBooked>")) {
										nbSeatsBooked.add(lineData.replace("<nbSeatsBooked>", "").replace("</nbSeatsBooked>", "").trim());
										
									}
						
								}
					
							}
				
						}
			
					}

				}
				
				String date = nameFilePeriod.get(i).substring(0,nameFilePeriod.get(i).indexOf("_"));
				MySqlController connection = MySqlController.getInstance();
				
				for(int j=0;j<idScreen.size();j++){
//					System.out.println("SELECT * FROM TEMPHISTORICALTIMETABLES WHERE DAY='"+this.dateBegin+"' AND ID_SCREEN='"+idScreen.get(j)+"'");
					ResultSet r = connection.getData("SELECT * FROM TEMPHISTORICALTIMETABLES WHERE DAY='"+date+"' AND ID_SCREEN='"+idScreen.get(j)+"'");
					
				//System.out.println("SELECT * FROM MOVIES WHERE ID_MOVIE='"+idMovie.get(j)+"'");
					ResultSet r1 = connection.getData("SELECT * FROM MOVIES WHERE ID_MOVIE='"+idMovie.get(j)+"'");

					if(!r.next()){
						
						if(r1.next()){
							connection.putData("INSERT INTO TEMPHISTORICALTIMETABLES VALUES ('"+idScreen.get(j)+"','"+idMovie.get(j)+"','"+day.get(j)+"','"+expectedBooking.get(j)+"','"+nbSeatsBooked.get(j)+"')");
						}
						else{
							if(idMovie.get(j).equals("0")){
								connection.putData("INSERT INTO TEMPHISTORICALTIMETABLES VALUES ('"+idScreen.get(j)+"','"+idMovie.get(j)+"','"+day.get(j)+"','"+expectedBooking.get(j)+"','"+nbSeatsBooked.get(j)+"')");
							}
						}

					}

				}

			} catch (UnsupportedEncodingException e) {

				e.printStackTrace();
				
			} catch (IOException e) {

				e.printStackTrace();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			 finally {
				try {

					buffer.close();
					
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
			
		}
		

	}
    
    
    
    
    public void fileAllRepertory(){

    	String [] listefichiers;
    	File file = new File(folder);

    	listefichiers=file.list();
    	for(int i=0;i<listefichiers.length;i++){
    		if(listefichiers[i].endsWith(".xml")){

    			nameFile.add(listefichiers[i]);
    		}
    	}
    }
    
    public void filePeriodRepertory(){

    	String [] listefichiers;
    	File file = new File(folder);

    	listefichiers=file.list();
    	for(int i=0;i<listefichiers.length;i++){
    		if(listefichiers[i].endsWith(".xml")){
    			
    			String date = listefichiers[i].substring(0,listefichiers[i].indexOf("_"));

    			if(date.compareTo(dateBegin) >= 0 && date.compareTo(dateEnd) <= 0){
    				nameFilePeriod.add(listefichiers[i]);
    			}
	
    		}
    	}
    }

    public String getPath(File file){
    	String path = file.getAbsolutePath().replace(file.toString(), "")+folder+file.toString();
    	return path;
    }

}
