import java.util.Calendar;


public class Main {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		Screen screen = new Screen();
		Screening screening = new Screening();
		
		Film film = new Film("007 Live and Let Die", "Steven Pielberg", "PG", null);
		film.setLength(2, 32);
		
		
		Calendar start_time_1 = Calendar.getInstance();
		start_time_1.set(0, 0, 0, 0, 0, 0);
		start_time_1.add(Calendar.HOUR, 3);
		start_time_1.add(Calendar.MINUTE, 30);
		
		Calendar start_time_2 = Calendar.getInstance();
		start_time_2.set(0, 0, 0, 0, 0, 0);
		start_time_2.add(Calendar.HOUR, 3);
		start_time_2.add(Calendar.MINUTE, 33);
		
		
		
		
		screening.setFilm(film);
		screening.setStartTime(start_time_1);
		
		screen.addScreening(screening);
		
		System.out.println(screen.checkScreenFree(start_time_2, start_time_2));

	}

}
