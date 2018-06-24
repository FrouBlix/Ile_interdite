/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import GraphicsUtil.ImagePanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author billo
 */
public class VueTresor extends JPanel{
    private HashMap<Special, ImagePanel> tresors;
    private JLabel pointAction;
    private JLabel aventurier;

    public VueTresor() {
        super(new BorderLayout());
        JPanel tresor = new JPanel( new GridLayout(2,2));
        tresors = new HashMap<>();
        ImagePanel panel = new ImagePanel("pierre.png", 0.5);
        panel.setOpaque(false);
        tresors.put(Special.pierre, panel);
        tresor.add(panel);
        panel = new ImagePanel("calice.png", 0.5);
        panel.setOpaque(false);
        tresors.put(Special.calice, panel);
        tresor.add(panel);
        panel = new ImagePanel("zephyr.png", 0.5);
        panel.setOpaque(false);
        tresors.put(Special.griffon, panel);
        tresor.add(panel);
        panel = new ImagePanel("cristal.png", 0.5);
        panel.setOpaque(false);
        tresors.put(Special.cristal, panel);
        tresor.add(panel);
        this.add(tresor, BorderLayout.PAGE_START);
        pointAction = new JLabel("PA restants :");
        aventurier = new JLabel();
        this.add(aventurier, BorderLayout.CENTER);
        this.add(pointAction, BorderLayout.PAGE_END);
    }
    
    
    public void acquerirTrophee(Special type){
        System.out.println("get trophee");
        ImagePanel panel = new ImagePanel("iconDone.png");
        panel.setOpaque(false);
        tresors.get(type).add(panel);
        tresors.get(type).revalidate();
        tresors.get(type).repaint();
    }
    
    public void updatePointAction(int pointAction){
        this.pointAction.setText("PA restant : " + pointAction);
    }
    
    public void updateJoueurEncours(String nom){
        this.aventurier.setText("Tour en cours : " + nom);
    }
    
}
