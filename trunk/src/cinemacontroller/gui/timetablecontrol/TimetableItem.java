/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cinemacontroller.gui.timetablecontrol;

import java.awt.Color;
import java.awt.Point;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Scott
 */
public class TimetableItem {
    private String film_name;
    private String film_time;
    private Point film_point;
    private Color panel_color;
    private Color text_color;
    private int film_cell_length = 6;

    public TimetableItem(String film_name, Point film_point, int film_cell_length, Color panel_color, Color text_color){
        this.film_name = film_name;
        this.film_point = film_point;
        this.panel_color = panel_color;
        this.text_color = text_color;
        this.film_cell_length = film_cell_length;
    }

    public String getName(){
        return this.film_name;
    }

    public Point getPoint(){
        return this.film_point;
    }

    public JPanel getPanel(){
        JPanel panel = new JPanel();
        panel.setBackground(this.panel_color);
        return panel;
    }

    public JLabel getTitleLabel(){
       JLabel label = new JLabel();
       label.setText(this.film_name);
       label.setForeground(this.text_color);
       return label;
    }

    public JLabel getTimeTable(){
       JLabel label = new JLabel();
       label.setText(this.film_time);
       label.setForeground(this.text_color);
       return label;
    }

    public int getLength(){
        return this.film_cell_length;
    }
    
}
