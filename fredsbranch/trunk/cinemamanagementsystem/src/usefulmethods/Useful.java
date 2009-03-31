package usefulmethods;

import java.awt.Color;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import databasecontroller.MySqlController;


public class Useful {
	
	private static final String FORMAT = "yyyy-MM-dd";
	
	public Useful(){
		
	}
	
    public String sha1(String pass) {
    	
        String ret = "";
        byte[] digest = null;
        byte[] digestAsBytes = null;
        
        try {   
        	
            digestAsBytes = pass.getBytes();

            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(digestAsBytes);
            digest = md.digest();
            
            for (byte b : digest){
                ret += Integer.toHexString(b & 0xff);
            }
            
        }
        catch(NoSuchAlgorithmException g){
            return("");
        };
        return(ret);
    }
    
    public String cutColor(Color c) {
    	
    	String color = c.toString().substring(c.toString().indexOf("[")+1, c.toString().length()-1);
    	color = color.replace("r=", "").replace("g=", "").replace("b=", "").trim();
    	
    	return color;
    	
    }
    
    
    public String getDateToday(){
    	
        SimpleDateFormat formater = new SimpleDateFormat(Useful.FORMAT);
        Date date = new Date();
        
        return formater.format(date);

    }
    
    public Calendar changeDate(Calendar dateEnd,int nb){
    	
    	dateEnd.add(Calendar.DATE, nb);
    	SimpleDateFormat formater = new SimpleDateFormat(Useful.FORMAT);
    	String newDate = formater.format(dateEnd.getTime());

		return toCalendar(newDate);

    }

    public Calendar toCalendar(String dateString) {
        try {
           SimpleDateFormat format = new SimpleDateFormat(Useful.FORMAT);
           Date date = format.parse(dateString);
           Calendar calendar = Calendar.getInstance();
           calendar.setTime(date);
           return calendar;
        } catch (ParseException e) {
           throw new IllegalArgumentException(e);
        }
    }


    public String calendarToString(Calendar calendardate){
    	String strdate = null;

    	SimpleDateFormat sdf = new SimpleDateFormat(Useful.FORMAT);

    	if (calendardate != null) {
    		strdate = sdf.format(calendardate.getTime());
    	}
    	return strdate;
    }
    
	public int numberShows(String idFilm, Calendar workingDate){

		ArrayList<Integer> weekend = new ArrayList<Integer>();
		weekend.add(6);weekend.add(7);weekend.add(1);
		Time timeAvailable = new Time(0);
		Calendar t = Calendar.getInstance();
		t.setTimeInMillis(3600000);

		int countShow = 0;
		
		try {

        	MySqlController connection = MySqlController.getInstance();
        	ResultSet r;
        	
        	if(weekend.contains(workingDate.get(Calendar.DAY_OF_WEEK))){
        		//System.out.println("SELECT START_HOUR_WE,PAUSE_TIME_WE,END_HOUR_WE FROM OPERATING_HOUR WHERE BEGIN_DATE<'"+calendarToString(workingDate)+"' AND END_DATE IS NULL");
        		r = connection.getData("SELECT DATE_FORMAT(START_HOUR_WE,'%T'),DATE_FORMAT(PAUSE_TIME_WE,'%T'),DATE_FORMAT(END_HOUR_WE,'%T') FROM OPERATING_HOUR WHERE BEGIN_DATE<'"+calendarToString(workingDate)+"' AND END_DATE IS NULL");
        	}
        	else{
        		//System.out.println("SELECT START_HOUR,PAUSE_TIME,END_HOUR FROM OPERATING_HOUR WHERE BEGIN_DATE<'"+calendarToString(workingDate)+"' AND END_DATE IS NULL");
        		r = connection.getData("SELECT DATE_FORMAT(START_HOUR,'%T'),DATE_FORMAT(PAUSE_TIME,'%T'),DATE_FORMAT(END_HOUR,'%T') FROM OPERATING_HOUR WHERE BEGIN_DATE<'"+calendarToString(workingDate)+"' AND END_DATE IS NULL");
        	}
        	
        	if(r.next()){

        		ResultSet r1 = connection.getData("SELECT DATE_FORMAT(RUNNING_TIME,'%T') FROM MOVIES WHERE ID_MOVIE='"+idFilm+"'");
        		
        		if(r1.next()){
        			if(r.getTime(1).compareTo(r.getTime(3)) > 0){
        				timeAvailable = new Time(r.getTime(3).getTime()-r.getTime(1).getTime()-t.getTimeInMillis());
        			}
        			else{
        				timeAvailable = new Time(r.getTime(3).getTime()-r.getTime(1).getTime());
        			}
        			
            		
        			//System.out.println(timeAvailable);
            		//System.out.println(-r1.getTime(1).getTime()-t.getTimeInMillis());
            		//System.out.println();
            		
        			if(timeAvailable.compareTo(new Time(0)) < 0){
        				while(timeAvailable.compareTo(new Time(-r1.getTime(1).getTime()-t.getTimeInMillis())) < 0){
        					timeAvailable = new Time(timeAvailable.getTime()+r1.getTime(1).getTime()+t.getTimeInMillis()+t.getTimeInMillis()+r.getTime(2).getTime());
        					countShow++;
        					//System.out.println("sd "+timeAvailable);
        				}
        				//System.out.println("neg");

            		}
        			else{
        				while(timeAvailable.compareTo(new Time(r1.getTime(1).getTime()+t.getTimeInMillis())) > 0){
        					timeAvailable = new Time(timeAvailable.getTime()-r1.getTime(1).getTime()-t.getTimeInMillis()-t.getTimeInMillis()-r.getTime(2).getTime());
        					countShow++;
        					//System.out.println("sdf "+timeAvailable);
        				}
        				//System.out.println("pos");
        				
        			}
        		}

        	}

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Unable to connect to MySQL database.\n" + e, "Database Error", JOptionPane.WARNING_MESSAGE);
        }
		return countShow;	
	}

}
