package cinemacontroller.gui.timetablecontrol;

import javax.swing.table.TableModel;

/**
 * This class defines how many cells a Timetable item should span across by
 * getting the film length and then decided how many cells are needed to represent
 * that length.
 * 
 * @author Scott
 */
public class TimetableItemCellSizer {
    
    private TableModel table_model;

    /**
     * Contstructor allows access in this class to the timetable model that contains
     * all the sheduling information.
     * 
     * @param table_model the timetable model
     */
    public TimetableItemCellSizer(TableModel table_model){
        this.table_model = table_model;
    }

    /**
     * Returns how many cells a timetable item should span.
     *
     * @param row the current row
     * @param column the current column
     * @return the span size for a cell
     */
    public int span(int row, int column)  {

        Object current_box = table_model.getValueAt(row, column);

        // If the current item at ROW and COLUMN is a timetable screening box
        if(current_box instanceof TimetableScreeningBox){
            TimetableScreeningBox box = (TimetableScreeningBox)current_box;
            // Returns the box's size (the size of the cell)
            return box.getBoxSize();
        }

      return 1;
  }

    /**
     * Returns what cell is visible
     * 
     * @param row
     * @param column
     * @return
     */
    public int visibleCell(int row, int column)  {
       return column;
  }

}