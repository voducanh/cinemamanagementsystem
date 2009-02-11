package cinemacontroller.gui.timetablecontrol;

import java.util.ArrayList;

public class TimetableItemCellSizer {
    
    private ArrayList<TimetableItem> items;

    public TimetableItemCellSizer(ArrayList<TimetableItem> items){
        this.items = items;
    }

  public int span(int row, int column)  {
        // Cycle through all the film items
        for(TimetableItem item : items){
            // Returns the length (cell span) of the current film item
            if(row == item.getPoint().x && column == item.getPoint().y){
                return item.getLength();
            }
        }
      return 1;
  }

  public int visibleCell(int row, int column)  {
        // Cycle through all the film items
        for(TimetableItem item : items){
            // Returns the starting position (column) of the current item
            if( (row == item.getPoint().x) && (column >= item.getPoint().y) && (column < (item.getPoint().y + item.getLength())) ){
                return item.getPoint().y;
            }
        }
       return column;
  }

}