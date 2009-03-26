package cinemacontroller.gui.timetablecontrol;

import java.awt.Color;
import cinemacontroller.screencontroller.Screening;
import java.util.Calendar;

/**
 * Provides functionality for all the screening box objects that are shown
 * on the main timetable interface.
 * 
 * @author Scott
 *
 */
public class TimetableScreeningBox {
	
	private Screening screening;
	private Color box_background_color;
	private TimetableTable table;
	private int table_row;
	
	/**
	 * Sets up the class.
	 * 
	 * @param screening
	 * @param box_background_color
     * @param table
     * @param table_row
	 */
	public TimetableScreeningBox(Screening screening, Color box_background_color, TimetableTable table, int table_row){
		this.screening = screening;
		this.box_background_color = box_background_color;
		this.table_row = table_row;
		this.table = table;
	}



	/**
	 * Returns the screening associated with the box control.
	 * 
	 * @return screening object
	 */
	public Screening getScreening(){
		return this.screening;
	}
	
	/**
	 * Returns the background colour of the box.
	 * 
	 * @return background color object
	 */
	public Color getBoxBackgroundColor(){
		return this.box_background_color;
	}
	
	
	/**
	 * Returns the row on which the component will be rendered.
	 * @return screening box row
	 */
	public int getTableRow(){
		return this.table_row;
	}
	
	/**
	 * Returns the number of the column for the box to start at.
	 * 
	 * @return screening box start column
	 */
	public int getStartColumn(){
		// Calculate the number of blocks per hour
		int block_per_hour = Math.round(table.getTableColumns() / table.getTableTotalHours());
		// Calculate the block to sta at
        int starting_block = block_per_hour * (this.screening.getStartTime().get(Calendar.HOUR_OF_DAY) - table.getTableStartHour());
        // Round to the nearest 'block'
        if(this.screening.getStartTime().get(Calendar.MINUTE) >= 30){ starting_block++; }
        
        return starting_block + 1;
	}
	
	/**
	 * Returns the length (in columns) of the block.
	 * 
	 * @return screening box span size
	 */
	public int getBoxSize(){
		// Calculate the number of blocks per hour
		int block_per_hour = Math.round((table.getTableColumns()) / table.getTableTotalHours());
		// Calculate the block to start at
        int block_length = (this.screening.getFilm().getLength().get(Calendar.HOUR_OF_DAY) * block_per_hour);
        // Round to the nearest 'block'
      //  System.out.println(Calendar.MINUTE);
        if(this.screening.getFilm().getLength().get(Calendar.MINUTE) >= 45){ block_length = block_length + 2; }else{
            if(this.screening.getFilm().getLength().get(Calendar.MINUTE) >= 15){
                 block_length = block_length + 1; 
            }
        }
                
        return block_length;
	}
}
