import java.util.Calendar;


public class Screening {

	private Time start_time;
	private Calendar end_time;
	
	private Film film;
		
	
	public Screening(){
		this.end_time = Calendar.getInstance();
		this.end_time.set(0, 0, 0, 0, 0, 0);
	}
	
	public void setFilm(Film film){
		this.film = film;
	}
	
	public void setStartTime(int hours, int mins){
		this.start_time = time;
	}
	
	public int getStartTime(){
		return this.start_time;
	}
	
	public Calendar getEndTime(){

	//	System.out.println(start_time.get(Calendar.HOUR) + " " + this.film.getFilmLength().get(Calendar.HOUR));
		
		
		int hour = start_time.get(Calendar.HOUR) + this.film.getFilmLength().get(Calendar.HOUR);
		
		end_time.set(Calendar.HOUR, hour);
	
		int min = start_time.get(Calendar.MINUTE) + this.film.getFilmLength().get(Calendar.MINUTE);
		
		end_time.set(Calendar.MINUTE, min);
		
		return end_time;
	}
}
