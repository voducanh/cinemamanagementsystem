package usefulmethods;

import java.awt.Color;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


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
    
    
    public String dateToday(){
    	
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
    
    
    
    
    
    
    
    
    

}
