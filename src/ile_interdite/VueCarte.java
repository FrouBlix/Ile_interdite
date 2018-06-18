/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.naming.OperationNotSupportedException;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author senno
 */
public class VueCarte extends Observe{
    private JPanel panel;
    private CarteTirage carte;
    private JLabel nom;

    public VueCarte(Observateur o, CarteTirage c){
        panel = new JPanel();
        carte = c;
        nom = new JLabel(carte.getNom());
        if (carte instanceof CarteTresor) {
            CarteTresor ca = (CarteTresor) carte;
            switch(ca.getType()){
                case cristal:
                    panel.setBackground(Color.red);
                    break;
                case calice:
                    panel.setBackground(Color.blue);
                    break;
                case pierre:
                    panel.setBackground(Color.white);
                    break;
                case griffon:
                    panel.setBackground(Color.yellow);
                    break;
            }
        }
        
        
        
        panel.add(nom);
        
        panel.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                notifierObservateur(new MessageCarteTirage("clic", carte));
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
        
        this.addObservateur(o);
    }
    
    public JPanel asJPanel() {
        return panel;
    }
    
}
