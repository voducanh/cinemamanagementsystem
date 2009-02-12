/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cinemacontroller.gui.timetablecontrol;

import java.awt.Color;
import java.awt.Component;
import java.awt.Label;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Scott
 */
public class TimetableRenderer extends DefaultTableCellRenderer {

    private ArrayList<TimetableItem> films_on_timetable;


    public TimetableRenderer(ArrayList<TimetableItem> films){
       this.films_on_timetable = films;
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,boolean hasFocus, int row, int column) {

        // Create a default cell component
        Component cell_component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        cell_component.setBackground(Color.white);
    
        for(TimetableItem current_film : films_on_timetable){

            if(current_film.getPoint().x == row  && current_film.getPoint().y == column){

                JPanel cell = current_film.getPanel();
                cell.add(current_film.getTitleLabel());
                cell.add(current_film.getTitleLabel());

                return cell;
            }
        }

         JPanel cell = new JPanel();
                cell.setBackground(Color.white);
                cell.setForeground(Color.black);
                cell.add(new JLabel((String)value));

                
                return cell;
    }

}
