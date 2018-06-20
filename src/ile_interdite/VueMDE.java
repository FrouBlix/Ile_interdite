/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import GraphicsUtil.ImagePanel;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author billo
 */
public class VueMDE extends ImagePanel implements Observateur{
    private ImagePanel marqueur; 
    
    public VueMDE(MonteeDesEaux mde) {
        super("Niveau.png" , 0.35);
        marqueur = new ImagePanel("stick.png",0.35, true);
        marqueur.setOpaque(false);
        this.setLayout(null);
        this.add(marqueur);
        mde.addObservateur(this);
    }
    
    public void bougercurseur(int position){
        int x = (int)(150*this.getScalingFactor());
        int y = 385 - (40 * (position -1));
        marqueur.setLocation(x, y);
        marqueur.setBounds(new Rectangle(new Point(x, y), marqueur.getPreferredSize()));
        marqueur.revalidate();
        marqueur.repaint();
        this.revalidate();
        this.repaint();
    }

    @Override
    public void traiterMessage(Message msg) {
        if (msg.contenu.contains("MDE")) {
            int niveau = Integer.parseInt(msg.contenu.substring(4));
            this.bougercurseur(niveau);
        }
    }
    
}
