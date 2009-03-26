/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cinemacontroller.gui.timetablecontrol;

import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JTable;
import javax.swing.table.TableModel;


/**
 * Class creates a new TimeTable control, a control which extends JTable
 * but provides functionality for multicell spanning and displaying screens and
 * screening controls.
 * 
 * @author Scott
 */
@SuppressWarnings("serial")
public class TimetableTable extends JTable {
	
	private int table_columns;
	private int table_total_hours;
	private int table_start_hour;
	
    /**
     * The timetablecellsize that defines the sizes of each cell.
     */
    public TimetableItemCellSizer map;
  
    /**
     * The constructor that sets up the the timetable control.
     * 
     * @param cmp
     * @param tbl
     * @param table_total_hours
     * @param table_start_hour
     */
    public TimetableTable(TimetableItemCellSizer cmp, TableModel tbl, int table_total_hours, int table_start_hour) {
	  
	  super(tbl);
	  map = cmp;
	  setUI(new TimetableCellPainter());
	  
	  this.table_columns = this.getColumnCount() - 1;
	  this.table_total_hours = table_total_hours;
	  this.table_start_hour = table_start_hour;
  
	  
  }
  
  /**
   * Returns the number of columns the Timetable control contains.
   * @return column count
   */
  public int getTableColumns(){
	  return this.table_columns;
  }
  
  /**
   * The total number of hours that the timetable shows.
   * @return total number of hours on timetable
   */
  public int getTableTotalHours(){
	  return this.table_total_hours;
  }
  
  /**
   * The starting hour for the timetable control.
   * @return starting hour
   */
  public int getTableStartHour(){
	  return this.table_start_hour;
  }


    @Override
  public Rectangle getCellRect(int row, int column, boolean includeSpacing){
    // required because getCellRect is used in JTable constructor
    if (map==null) return super.getCellRect(row,column, includeSpacing);
   // add widths of all spanned logical cells
    int sk=map.visibleCell(row,column);
    Rectangle r1=super.getCellRect(row,sk,includeSpacing);
    if (map.span(row,sk)!=1)
    for (int i=1; i<map.span(row,sk); i++){
        r1.width+=getColumnModel().getColumn(sk+i).getWidth();
      }
    return r1;
  }
  
    @Override
  public int columnAtPoint(Point p) {
    int x=super.columnAtPoint(p);
    // -1 is returned by columnAtPoint if the point is not in the table
    if (x<0) return x;
    int y=super.rowAtPoint(p);
    return map.visibleCell(y,x);
  }

  /**
   * Makes sure each cell is not editable (directly).
   * 
   * @param rowIndex
   * @param mColIndex
   * @return
   */
    @Override
  public boolean isCellEditable(int rowIndex, int mColIndex) {
    return false;
  }

  
}