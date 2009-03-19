package cinemacontroller.gui.timetablecontrol;

import cinemacontroller.screencontroller.Screen;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * This class handles all the rendering for the Timetable table.
 * 
 * @author Scott
 */
@SuppressWarnings("serial")
public class TimetableRenderer extends DefaultTableCellRenderer {

    /**
     * The constructor for the timetable renderer.
     */
    public TimetableRenderer(){
    }

    /**
     * This gets the timetable renderes component. That is, the component that will be drawn
     * on the selected ROW and COLUMN in the timetable table.
     * 
     * @param table
     * @param value
     * @param isSelected
     * @param hasFocus
     * @param row
     * @param column
     * @return the component to draw on timetable.
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,boolean hasFocus, int row, int column) {

        if(column == 0){
            // If the current cell is the cell for the screen's title
           return this.createScreenCell(value);
        }

        Object current_box = table.getModel().getValueAt(row, column);

        if(current_box instanceof TimetableScreeningBox){
            // The component to show a film screening instance
            return this.createScreeningCell((TimetableScreeningBox)current_box);
        }else{
            // An empty cell if nothing special is to be drawn
            return this.createEmptyCell();
        }

    }

    /**
     * Returns the friendly human readable format of two times.
     *
     * @param time
     * @return a friendly time format for the timetable
     */
    public String getFriendlyTime(GregorianCalendar time){
        Date daterr = time.getTime();
        Format formatter = new SimpleDateFormat("kk:mm");
        return formatter.format(daterr);
    }

    /**
     * Creates a JPanel that is used for showing the screen's title.
     * 
     * @param value
     * @return the component for a screens header
     */
    public JPanel createScreenCell(Object value){
        Color default_screen_background_color = new Color(228,227,227);
        Color default_screen_text_color = new Color(0,0,0);

        // Create a blank jpanel and set its layout manager
        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        
        // Set the background color
        box.setBackground(default_screen_background_color);

        // Cast the current object to Screen
        Screen current_screen = (Screen)value;

        // Create a jlabel with the screens ID number
        JLabel title_label = new JLabel("" + current_screen.getID());
        title_label.setForeground(default_screen_text_color);
        title_label.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create a jlabel with the screens seats number
        JLabel seat_label = new JLabel("" + current_screen.getSeats() + " seats");
        seat_label.setForeground(Color.gray);
        seat_label.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Spacer
        JLabel spacer = new JLabel(" ");
        spacer.setForeground(default_screen_text_color);
        spacer.setAlignmentX(Component.CENTER_ALIGNMENT);
        

        // The title
        box.add(spacer);
        box.add(title_label);
        box.add(seat_label);
        
        return box;
    }

    /**
     * Creates a jpanel that contains the film title, time and also the color defined by
     * the screens attributes.
     *
     * @param current_box
     * @return the component for a screening
     */
    public JPanel createScreeningCell(TimetableScreeningBox current_box){
        JPanel box = new JPanel();

        box.setLayout(new BoxLayout(box, BoxLayout.PAGE_AXIS));
        box.setBackground(current_box.getBoxBackgroundColor());

        JLabel title_label = new JLabel(current_box.getScreening().getFilm().getTitle());
        title_label.setForeground(Color.white);

        JLabel title_null = new JLabel(" ");
        title_null.setForeground(Color.white);

        JLabel time_label = new JLabel(getFriendlyTime(current_box.getScreening().getStartTime()) + " - " + getFriendlyTime(current_box.getScreening().getEndTime()));
        time_label.setForeground(Color.white);

        title_label.setAlignmentX(Component.CENTER_ALIGNMENT);
        time_label.setAlignmentX(Component.CENTER_ALIGNMENT);

        box.add(title_null);
        box.add(title_label);
        box.add(time_label);

        box.setBackground(current_box.getBoxBackgroundColor());
        box.setForeground(Color.white);

        return box;
    }

    /**
     * Creates an empty jpanel.
     * @return and empty cell component
     */
    public JPanel createEmptyCell(){
        JPanel cell = new JPanel();
         cell.setBackground(Color.white);
         cell.setForeground(Color.black);
         cell.add(new JLabel(new String(" ")));
         return cell;
    }
}
