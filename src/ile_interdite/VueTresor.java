/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import GraphicsUtil.ImagePanel;
import java.awt.GridLayout;
import java.util.HashMap;
import javax.swing.JPanel;

/**
 *
 * @author billo
 */
public class VueTresor extends JPanel{
    private HashMap<Special, ImagePanel> tresors;

    public VueTresor() {
        super(new GridLayout(2, 2));
        tresors = new HashMap<>();
        ImagePanel panel = new ImagePanel("pierre.png");
        panel.setOpaque(false);
        tresors.put(Special.pierre, panel);
        panel = new ImagePanel("calice.png");
        panel.setOpaque(false);
        tresors.put(Special.calice, panel);
        panel = new ImagePanel("zephyr.png");
        panel.setOpaque(false);
        tresors.put(Special.griffon, panel);
        panel = new ImagePanel("cristal.png");
        panel.setOpaque(false);
        tresors.put(Special.cristal, panel);
    }
    
    
    public void acquerirTrophee(Special type){
        ImagePanel panel = new ImagePanel("iconDone.png");
        panel.setOpaque(false);
        tresors.get(type).add(panel);
    }
    
    
    
    
}
