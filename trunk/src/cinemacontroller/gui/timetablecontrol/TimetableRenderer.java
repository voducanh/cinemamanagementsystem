/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cinemacontroller.gui.timetablecontrol;

import cinemacontroller.screensystem.Screen;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Scott
 */
@SuppressWarnings("serial")
public class TimetableRenderer extends DefaultTableCellRenderer {

    private ArrayList<TimetableScreeningBox> list_of_box_controls;


    public TimetableRenderer(ArrayList<TimetableScreeningBox> arrayList){
       this.list_of_box_controls = arrayList;
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,boolean hasFocus, int row, int column) {

        // Create a default cell component
        Component cell_component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        cell_component.setBackground(Color.white);
        cell_component.setForeground(Color.black);


        if(column == 0){

                JPanel box = new JPanel();
                box.setLayout(new GridBagLayout());
                GridBagConstraints c = new GridBagConstraints();
                c.anchor = GridBagConstraints.CENTER;

                box.setBackground(new Color(228,227,227));

                // Cast the current object to Screen
                Screen current_screen = (Screen)value;

                JLabel title_label = new JLabel("" + current_screen.getIDNumber());
                title_label.setForeground(Color.black);


                JLabel title_label2 = new JLabel(" ");
                title_label2.setForeground(Color.black);



                box.add(title_label2, c);
                box.add(title_label, c);

                return box;
            }

        for(TimetableScreeningBox current_box : list_of_box_controls){

        	
            if(current_box.getTableRow() == row  && current_box.getStartColumn() == column){

            	JPanel box = new JPanel();

                box.setLayout(new BoxLayout(box, BoxLayout.PAGE_AXIS));
                box.setBackground(current_box.getBoxBackgroundColor());
                
                JLabel title_label = new JLabel(current_box.getScreening().getFilm().getTitle());
                title_label.setForeground(current_box.getBoxForegroundColor());

                JLabel title_null = new JLabel(" ");
                title_null.setForeground(current_box.getBoxForegroundColor());

                JLabel time_label = new JLabel(getFriendlyTime(current_box.getScreening().getStartTime()) + " - " + getFriendlyTime(current_box.getScreening().getEndTime()));
                time_label.setForeground(current_box.getBoxForegroundColor());
                
                title_label.setAlignmentX(Component.CENTER_ALIGNMENT);
                time_label.setAlignmentX(Component.CENTER_ALIGNMENT);

                box.add(title_null);
                box.add(title_label);
                box.add(time_label);
                
                box.setBackground(current_box.getBoxBackgroundColor());
                box.setForeground(current_box.getBoxForegroundColor());

                return box;
            }
            
            
            
        }

         JPanel cell = new JPanel();
         cell.setBackground(Color.white);
         cell.setForeground(Color.black);
         cell.add(new JLabel(new String(" ")));

                
                return cell;
    }

    public String getFriendlyTime(GregorianCalendar time){
        Date daterr = time.getTime();
        Format formatter = new SimpleDateFormat("kk:mm");
        return formatter.format(daterr);
    }

}
