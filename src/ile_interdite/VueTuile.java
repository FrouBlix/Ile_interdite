/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author senno
 */
public class VueTuile extends Observe implements Observateur{
    private JPanel panelTuile;
    private Tuile tuile;
    public VueTuile(Tuile tuile, Observateur obs) {
        this.addObservateur(obs);
        panelTuile = new JPanel();
        panelTuile.addMouseListener(new MouseListener() {
            
            @Override
            public void mouseClicked(MouseEvent e) {
                notifierObservateur(new MessageDeTuile("clic",tuile));
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
        switch(this.tuile.getEtat()){
                case seche: this.panelTuile.setBackground(Color.green);
                    break;
                case inondee: this.panelTuile.setBackground(Color.blue);
                    break;
                case sombree: this.panelTuile.setBackground(Color.darkGray);
            }
        this.tuile.addObservateur(this);
    }
    
    public JPanel asJPanel(){
        return this.panelTuile;
    }

    @Override
    public void traiterMessage(Message msg) {
        if (msg.contenu == "update etat") {
            switch(this.tuile.getEtat()){
                case seche: this.panelTuile.setBackground(Color.green);
                    break;
                case inondee: this.panelTuile.setBackground(Color.blue);
                    break;
                case sombree: this.panelTuile.setBackground(Color.darkGray);
            }
        }
    }
    
    
}
