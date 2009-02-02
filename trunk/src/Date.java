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
	
	/**
	 * Sets up a really simple calendar in natural text form.
	 * 
	 * @param day
	 * @param month
	 * @param year
	 */
	public Date(int day, int month, int year){
		// Create and empty calendar instance
		this.calendar = Calendar.getInstance();
		this.calendar.clear();
		
		this.calendar.set(Calendar.DAY_OF_MONTH, day);
		this.calendar.set(Calendar.MONTH, month);
		this.calendar.set(Calendar.YEAR, year);
	}
	
	/**
	 * Returns the calendar object
	 * 
	 * @return	calendar object
	 */
	public Calendar getDate(){
		return this.calendar;
	}
	
	/**
	 * Returns the current date in the form DD/MM/YYYY in string format
	 * 
	 * @return	string format date
	 */
	public String toString(){
		return this.calendar.get(Calendar.DAY_OF_MONTH) + "/" + this.calendar.get(Calendar.MONTH) + "/" + this.calendar.get(Calendar.YEAR); 
		
	}
	
	public boolean before(Date date){
		if(this.getDate().before(date.getDate())){
			return true;
		}else{
			return false;
		}
	}
	
}
