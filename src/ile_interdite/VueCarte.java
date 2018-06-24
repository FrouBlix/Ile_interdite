/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import GraphicsUtil.ImagePanel;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author senno
 */
public class VueCarte extends Observe{
    
    private JPanel panel2;
    private ImagePanel panel;
    private CarteTirage carte;
    private JLabel nom;

    public VueCarte(Observateur o, CarteTirage c){
        this.addObservateur(o);
        carte = c;
        nom = new JLabel(carte.getNom());
        panel2 = new JPanel();
        switch(carte.getType()){
            case cristal:
                panel = new ImagePanel("cartecristal.png", 0.1);
                break;
            case calice:
                panel = new ImagePanel("cartecalice.png", 0.1);
                break;
            case pierre:
                panel = new ImagePanel("cartepierre.png", 0.1);
                break;
            case griffon:
                panel = new ImagePanel("cartegriffon.png", 0.1);
                break;
            case sacSable:
                panel = new ImagePanel("SacsDeSable.png", 0.1);
                break;
            case helico:
                panel = new ImagePanel("Helicoptere.png", 0.1);
        }
        
        
        
        
        panel.add(nom);
        
        panel.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                notifierObservateur(new MessageCarteTirage("clic", carte));
//                System.out.println("ping");
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        
        panel.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        panel2.add(panel);
        panel2.setOpaque(false);
    }
    
    public JPanel asJPanel() {
        return panel2;
    }
 
    public void surligner(boolean surligner){
        if (surligner) {
            panel.setBorder(BorderFactory.createLineBorder(Color.yellow, 2, true));
        }else{
            panel.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        }
        panel.repaint();
    }
    
}
