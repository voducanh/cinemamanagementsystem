package timeanddate;
import java.util.Calendar;

/**
 * Time Class
 * 
 * Provides functionality to easily manage time.
 * 
 * @author Scott
 *
 */

public class Time {
	
	private Calendar time;
	
	/**
	 * Constructor to set up the current time in hours and minutes.
	 * 
	 * @param hour_of_day
	 * @param min
	 */
	public Time(int hour_of_day, int min){
		
		// Create and empty calendar instance
		this.time = Calendar.getInstance();
		this.time.clear();
		
		// Sets the information regarding the time
		time.add(Calendar.HOUR_OF_DAY, hour_of_day);
		time.add(Calendar.MINUTE, min);
		
	}
	
	/**
	 * Second constructor allows the additional time management of seconds.
	 * 
	 * @param hour_of_day
	 * @param min
	 * @param seconds
	 */
	public Time(int hour_of_day, int min, int seconds){
		// Sets the information regarding the time
		time.add(Calendar.HOUR_OF_DAY, hour_of_day);
		time.add(Calendar.MINUTE, min);
		time.add(Calendar.SECOND, seconds);
	}
	
	/**
	 * Returns the current number of hours.
	 * 
	 * @return
	 */
	public int getHourOfDay(){
		return time.get(Calendar.HOUR_OF_DAY);
	}
	
	/**
	 * Returns the number of minutes.
	 * 
	 * @return
	 */
	public int getMinute(){
		return time.get(Calendar.MINUTE);
	}
	
	/** 
	 * Returns the current time (hours and minutes).
	 * 
	 * @return
	 */
	public int getTime(){
		return Integer.parseInt(time.get(Calendar.HOUR_OF_DAY) + "" + time.get(Calendar.MINUTE));
	}
	
	/** 
	 * Sets the current hour.
	 * 
	 * @param hours
	 */
	public void setHourOfDay(int hours){
		this.time.set(Calendar.HOUR_OF_DAY, hours);
		
	}
	
	/** 
	 * Sets the current minutes.
	 * 
	 * @param mins
	 */
	public void setMinute(int mins){
		this.time.set(Calendar.MINUTE, mins);
	}
}
