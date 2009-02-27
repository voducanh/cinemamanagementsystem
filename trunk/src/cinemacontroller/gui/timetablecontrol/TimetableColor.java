/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cinemacontroller.gui.timetablecontrol;

import java.awt.Color;

/**
 *
 * @author Scott
 */
public class TimetableColor {
    public final static Color BLUE = new Color(78, 128, 170);
    public final static Color RED = new Color(208, 90, 90);
    public final static Color GREEN = new Color(118, 165, 81);
    public final static Color ORANGE = new Color(216, 145, 87);
    public final static Color YELLOW = new Color(217, 215, 26);
    public final static Color BLACK = new Color(41, 41, 41);

    public final static Color blue = BLUE;
    public final static Color red = RED;
    public final static Color green = GREEN;
    public final static Color orange = ORANGE;
    public final static Color yellow = YELLOW;
    public final static Color black = BLACK;

    public static Color getColor(String color_name_in){
        String color_name = new String(color_name_in).toUpperCase();

        if(color_name.equals("RED")){
            return RED;
        }
        if(color_name.equals("BLUE")){
            return BLUE;
        }
        if(color_name.equals("GREEN")){
            return GREEN;
        }
        if(color_name.equals("ORANGE")){
            return ORANGE;
        }
        if(color_name.equals("YELLOW")){
            return YELLOW;
        }
        if(color_name.equals("BLACK")){
            return BLACK;
        }

        return BLACK;
    }
}
