package usefulmethods;

public class Hour {
	
	private int hour = 0;
	private int mins = 0;
	private Useful use;
	
	public Hour(String hourIn){
		
		use = new Useful();
		
		hour = use.getHour(hourIn);
		mins = use.getMins(hourIn);
		
	}
	
	public Hour(){
		
		use = new Useful();
		
		hour = 0;
		mins = 0;
		
	}
	
	public Hour(int hourIn, int minIn){
		
		use = new Useful();
		
		hour = hourIn;
		mins = minIn;
		
	}
	
	public Hour addHours(Hour hour1){
		
		Hour outHour = new Hour();
		
		outHour.mins = this.mins+hour1.mins;
		outHour.hour = this.hour+hour1.hour;
		
		while(outHour.mins >= 60){
			
			outHour.mins -= 60;
			outHour.hour += 1;
			
		}
		while(outHour.hour >= 24){
			
			outHour.hour -= 24;
			
		}

		return outHour;
		
	}
	
	public int compareHours(Hour firstHour){
	
		int value =0;
		
		int totalMinsFirst = this.hour*60+this.mins;
		int totalMinssecond = firstHour.hour*60+firstHour.mins;
		
		if(totalMinsFirst < totalMinssecond){
			value = -1;
		}
		else if(totalMinsFirst > totalMinssecond){
			value = 1;
		}
		
		return value;
	}
	
	
	public Hour removeHours(Hour hour1){
		
		Hour outHour = new Hour();
		Hour endHour = hour1;
		
		if(this.compareHours(endHour) > 0){
			
			endHour.hour += 24;
			
		}
		
		outHour.mins = endHour.mins-this.mins;
		outHour.hour = endHour.hour-this.hour;
		
		if(outHour.mins < 0){
			
			outHour.mins += 60;
			outHour.hour -= 1;
		}
		
		if(outHour.hour < 0){
			
			outHour.hour += 24;
		}

		return outHour;
		
	}
	
	
	public void setHour(String hourIn){
		hour = use.getHour(hourIn);
		mins = use.getMins(hourIn);
	}
	
	public int getHour(){
		return hour;
	}
	
	public int getMins(){
		return mins;
	}


}
