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
    /**
     * The Color object for the timetable color BLUE.
     */
    public final static Color BLUE = new Color(78, 128, 170);
    /**
     * The Color object for the timetable color RED.
     */
    public final static Color RED = new Color(208, 90, 90);
    /**
     * The Color object for the timetable color GREEB.
     */
    public final static Color GREEN = new Color(118, 165, 81);
    /**
     * The Color object for the timetable color ORANGE.
     */
    public final static Color ORANGE = new Color(216, 145, 87);
    /**
     * The Color object for the timetable color YELLOW.
     */
    public final static Color YELLOW = new Color(217, 215, 26);
    /**
     * The Color object for the timetable color BLACK.
     */
    public final static Color BLACK = new Color(41, 41, 41);

    /**
     * The Color object for the timetable color BLUE.
     */
    public final static Color blue = BLUE;
    /**
     * The Color object for the timetable color RED.
     */
    public final static Color red = RED;
    /**
     * The Color object for the timetable color GREEN.
     */
    public final static Color green = GREEN;
    /**
     * The Color object for the timetable color ORANGE.
     */
    public final static Color orange = ORANGE;
    /**
     * The Color object for the timetable color YELLOW.
     */
    public final static Color yellow = YELLOW;
    /**
     * The Color object for the timetable color BLACK.
     */
    public final static Color black = BLACK;

    /**
     * Returns the Color object specified by the string parameter.
     *
     * @param color_name_in the Color object to return
     * @return a Color object
     */
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
