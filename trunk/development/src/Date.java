import java.util.Calendar;

/**
 * 
 * Simple wrapper to make it easier to create days. Created specifically for its purpose
 * and is likely to be very limited.
 * 
 * @author Scott
 *
 */

public class Date {

	private Calendar calendar;
	
	public Date(int day, int month, int year){
		// Create and empty calendar instance
		this.calendar = Calendar.getInstance();
		this.calendar.clear();
		
		this.calendar.set(Calendar.DAY_OF_MONTH, day);
		this.calendar.set(Calendar.MONTH, month);
		this.calendar.set(Calendar.YEAR, year);
	}
	
	public Calendar getDate(){
		return this.calendar;
	}
	
	public String toString(){
		return this.calendar.get(Calendar.DAY_OF_MONTH) + "/" + this.calendar.get(Calendar.MONTH) + "/" + this.calendar.get(Calendar.YEAR); 
		
	}
	
}
