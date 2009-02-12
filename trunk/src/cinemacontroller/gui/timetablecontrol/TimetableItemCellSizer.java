package cinemacontroller.gui.timetablecontrol;

import java.util.ArrayList;

public class TimetableItemCellSizer {
    
    private ArrayList<TimetableScreeningBox> list_of_box_controls;

    public TimetableItemCellSizer(ArrayList<TimetableScreeningBox> arrayList){
        this.list_of_box_controls = arrayList;
    }

  public int span(int row, int column)  {
        // Cycle through all the film items
        for(TimetableScreeningBox current_box : list_of_box_controls){
            // Returns the length (cell span) of the current film item
            if(row == current_box.getTableRow() && column == current_box.getStartColumn()){
                return current_box.getBoxSize();
            }
        }
      return 1;
  }

  public int visibleCell(int row, int column)  {
        // Cycle through all the film items
        for(TimetableScreeningBox current_box : list_of_box_controls){
            // Returns the starting position (column) of the current item
            if( (row == current_box.getTableRow()) && (column >= current_box.getStartColumn()) && (column < (current_box.getStartColumn() + current_box.getBoxSize())) ){
                return current_box.getStartColumn();
            }
        }
       return column;
  }

}