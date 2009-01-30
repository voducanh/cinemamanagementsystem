import java.util.ArrayList;
import java.util.Calendar;


public class Screen { e

	private int screen_ID_number;
	private int screen_seat_count;
	
	private ArrayList<Screening> screenings;
	
	
	
	public Screen(){
		screenings = new ArrayList<Screening>();
	}
	
	public void setIDNumber(int screen_ID_number){
		this.screen_ID_number = screen_ID_number;
	}

	public void setSeats(int screen_seat_count){
		this.screen_seat_count = screen_seat_count;
	}
	
	public int getIDNumber(){
		return this.screen_ID_number;
	}
	
	public int getSeats(){
		return this.screen_seat_count;
	}
	
	public void addScreening(Screening screening){ asdfsdf
		screenings.add(screening);
	}
	
	public String checkScreenFree(Calendar start_time, Calendar end_time){
		
		for(Screening screen : screenings){
			
			System.out.println(screen.getEndTime());
			
			if(start_time.get(Calendar.HOUR) > screen.getStartTime().get(Calendar.HOUR) && start_time.get(Calendar.HOUR) < screen.getEndTime().get(Calendar.HOUR)){
				return "NO";
			}
			if(end_time.get(Calendar.HOUR) > screen.getStartTime().get(Calendar.HOUR) && end_time.get(Calendar.HOUR) < screen.getEndTime().get(Calendar.HOUR)){
				return "NO";
			}
		}
		
		return "yes";
	}
}

