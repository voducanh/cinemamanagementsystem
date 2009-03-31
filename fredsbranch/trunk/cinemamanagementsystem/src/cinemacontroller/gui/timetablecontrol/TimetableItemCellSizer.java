package cinemacontroller.gui.timetablecontrol;

import javax.swing.table.TableModel;

public class TimetableItemCellSizer {
    
    private TableModel table_model;

    public TimetableItemCellSizer(TableModel table_model){
        this.table_model = table_model;
    }



  public int visibleCell(int row, int column)  {
       return column;
  }

}