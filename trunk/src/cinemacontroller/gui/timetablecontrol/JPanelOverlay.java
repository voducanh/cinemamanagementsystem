/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cinemacontroller.gui.timetablecontrol;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 *
 * @author Scott
 */
class JPanelOverlay extends JPanel {
    protected  Image ci=null;

    public JPanelOverlay() {
    }

    public void setImage(Image si) {
        this.ci=si;
        validate();
        repaint();
    }

    public Image getDisplayedImage() {
        return this.ci;
    }

    public void update(Graphics g) {
    if (ci!=null) {
      g.drawImage(ci, 0,0,this.getSize().width,this.getSize().height, this);
    } else {
      Color c=g.getColor();
      g.setColor(Color.black);
      g.fillRect(0,0,this.getWidth(), this.getHeight());
      g.setColor(c);
    }
  }

    public void paint(Graphics g) {
        update(g);
    }
}
