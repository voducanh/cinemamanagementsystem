package cinemacontroller.gui.timetablecontrol;

import javax.swing.table.TableModel;

public class TimetableItemCellSizer {
    
    private TableModel table_model;

    public TimetableItemCellSizer(TableModel table_model){
        this.table_model = table_model;
    }

  public int span(int row, int column)  {

        Object current_box = table_model.getValueAt(row, column);

        if(current_box instanceof TimetableScreeningBox){
            TimetableScreeningBox box = (TimetableScreeningBox)current_box;
            return box.getBoxSize();
        }

      return 1;
  }

  public int visibleCell(int row, int column)  {
       return column;
  }

}