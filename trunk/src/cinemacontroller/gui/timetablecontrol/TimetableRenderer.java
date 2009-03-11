package cinemacontroller.gui.timetablecontrol;

import cinemacontroller.screensystem.Screen;
import java.awt.Color;
import java.awt.Component;
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
 *
 * @author Scott
 */
@SuppressWarnings("serial")
public class TimetableRenderer extends DefaultTableCellRenderer {

    public TimetableRenderer(){
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,boolean hasFocus, int row, int column) {

        if(column == 0){
            // If the current cell is the cell for the screen's title
           return this.createScreenCell(value);
        }

        Object current_box = table.getModel().getValueAt(row, column);

        if(current_box instanceof TimetableScreeningBox){
            return this.createScreeningCell((TimetableScreeningBox)current_box);
        }else{
            return this.createEmptyCell();
        }

    }

    /**
     * Returns the friendly human readable format of two times.
     *
     * @param time
     * @return
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
     * @return
     */
    public JPanel createScreenCell(Object value){
        Color default_screen_background_color = new Color(228,227,227);
        Color default_screen_text_color = new Color(0,0,0);

        // Create a blank jpanel and set its layout manager
        JPanel box = new JPanel();
        box.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;

        // Set the background color
        box.setBackground(default_screen_background_color);

        // Cast the current object to Screen
        Screen current_screen = (Screen)value;

        // Create a jlabel with the screens ID number
        JLabel title_label = new JLabel("" + current_screen.getIDNumber());
        title_label.setForeground(default_screen_text_color);

        // A spacer
        box.add(new JLabel(" "), c);
        // The title
        box.add(title_label, c);

        return box;
    }

    /**
     * Creates a jpanel that contains the film title, time and also the color defined by
     * the screens attributes.
     *
     * @param value
     * @return
     */
    public JPanel createScreeningCell(TimetableScreeningBox current_box){
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

    /**
     * Creates an empty jpanel.
     * @return
     */
    public JPanel createEmptyCell(){
        JPanel cell = new JPanel();
         cell.setBackground(Color.white);
         cell.setForeground(Color.black);
         cell.add(new JLabel(new String(" ")));
         return cell;
    }
}
