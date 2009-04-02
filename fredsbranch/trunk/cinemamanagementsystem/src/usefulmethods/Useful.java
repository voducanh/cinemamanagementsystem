package usefulmethods;

import java.awt.Color;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
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
    
	public Calendar getDateEndPeriod(){
		
		String dateEnd = "";
		Calendar dateEndCal;
		
        try {

        	MySqlController connection = MySqlController.getInstance();

        	ResultSet r = connection.getData("SELECT END_DATE FROM PERIOD");
        	
        	
        	while (r.next()) {
        		
        		dateEnd = r.getString(1);
			}

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Unable to connect to MySQL database.\n" + e, "Database Error", JOptionPane.WARNING_MESSAGE);
        }
        
        dateEndCal = toCalendar(dateEnd);
        
        return dateEndCal;
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

    	int countShow = 0;
    	Hour pauseTime = new Hour();
    	Hour startHour = new Hour();
    	Hour endHour = new Hour();
    	
    	Hour availableTime = new Hour();
    	Hour runningTime= new Hour();
	
	try {

    	MySqlController connection = MySqlController.getInstance();
    	
    	ResultSet r = connection.getData("SELECT * FROM OPERATING_HOUR WHERE BEGIN_DATE<'"+calendarToString(workingDate)+"' && END_DATE IS NULL");

    	if(r.next()){
        	if(weekend.contains(workingDate.get(Calendar.DAY_OF_WEEK))){
        		startHour.setHour(r.getString(5));
        		pauseTime.setHour(r.getString(6));
        		endHour.setHour(r.getString(7));
        	}
        	else{
        		startHour.setHour(r.getString(2));
        		pauseTime.setHour(r.getString(3));
        		endHour.setHour(r.getString(4));
        	}

        	availableTime = startHour.removeHours(endHour);
        	
        	ResultSet r1 = connection.getData("SELECT * FROM MOVIES WHERE ID_MOVIE='"+idFilm+"'");
        	if(r1.next()){
        		runningTime.setHour(r1.getString(10));
        	}
        	
        	while(availableTime.compareHours(runningTime.addHours(pauseTime)) >= 0){
        		availableTime = runningTime.removeHours(availableTime);
        		countShow++;
        		availableTime = pauseTime.removeHours(availableTime);

        	}

    	}

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Unable to connect to MySQL database.\n" + e, "Database Error", JOptionPane.WARNING_MESSAGE);
    }
	return countShow;	
}
	
	
	public int getHour(String time){

		int hour = new Integer(time.substring(0, time.indexOf(":")));
		
		return hour;

	}
	
	public int getMins(String time){

		String timeM = time;
		
		timeM = timeM.substring(timeM.indexOf(":")+1, timeM.length());
		int mins = new Integer(timeM.substring(0, timeM.indexOf(":")));
		
		return mins;
	}

}
