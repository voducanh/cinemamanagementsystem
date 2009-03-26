/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cinemacontroller.rotationalcontroller;

import cinemacontroller.screencontroller.Screen;

/**
 * MOVE ----
 * - FROM
 * - TO
 *
 * CRAETE_SCREENING
 * - FROM
 * - TO
 * - TIME AND DATE
 *
 * REMOVE_SCREENING
 * - SCREENING
 *
 * REMOVE_FILM
 * - FILM
 *
 * EXTEND_RENTING_PERIOD
 * - FILM
 *
 *
 * @author Scott
 */


public class Action {

    // Define all the types of actions that occur
    public enum Type { CREATE_SCREENING, REMOVE_SCREENING, MOVE_SCREEN, REMOVE_FILM, EXTEND_RENTING_PERIOD }

    // Global Fields
    private Type action_type;
    private Screen current_screen;
    private Screen destination_screen;


    public Action(Type action_type){
        this.action_type = action_type;
    }






    public Type getType(){
        return this.action_type;
    }

    public Screen getCurrentScreen(){
        return this.current_screen;
    }

    public Screen getDestinationScreen(){
        return this.destination_screen;
    }




}
