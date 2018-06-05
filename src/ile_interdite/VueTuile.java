/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author senno
 */
public class VueTuile extends Observe {
    private JPanel panelTuile;
    private Tuile tuile;
    public VueTuile(Tuile tuile) {
        panelTuile = new JPanel();
        panelTuile.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                notifierObservateur(new MessageDeTuile("clic",tuile));
                System.out.println("clic");
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
        panelTuile.add(new JLabel(tuile.getNom()));
        this.tuile = tuile;
    }
    
    public JPanel asJPanel(){
        return this.panelTuile;
    }
    
    
}
