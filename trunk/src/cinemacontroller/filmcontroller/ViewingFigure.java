/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cinemacontroller.filmcontroller;


/**
 *
 * @author Scott
 */
public class ViewingFigure {

    String date;
    int viewing_figure;
    Film film;
    
    public ViewingFigure(Film film, String date, int viewing_figure){
        this.date = date;
        this.viewing_figure = viewing_figure;
    }

    public int getViewingFigure(){
        return this.viewing_figure;
    }

    public String getDate(){
        return this.date;
    }

    public Film getFilm(){
        return this.film;
    }
}
