/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author senno
 */
public class VueTuile extends Observe implements Observateur{
    private JPanel panelTuile;
    private JLabel labelTuile;
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
        this.labelTuile = new JLabel(tuile.getNom());
        panelTuile.add(this.labelTuile);
        
        this.tuile = tuile;
        switch(this.tuile.getEtat()){
                case seche: this.panelTuile.setBackground(Color.green);
                    break;
                case inondee: this.panelTuile.setBackground(Color.blue);
                    this.labelTuile.setForeground(Color.white);
                    break;
                case sombree: this.panelTuile.setBackground(Color.darkGray);
                    this.labelTuile.setForeground(Color.white);
            }
        this.panelTuile.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
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
                    this.labelTuile.setForeground(Color.darkGray);
                    break;
                case inondee: this.panelTuile.setBackground(Color.blue);
                    this.labelTuile.setForeground(Color.white);
                    break;
                case sombree: this.panelTuile.setBackground(Color.darkGray);
                    this.labelTuile.setForeground(Color.white);

            }
        }
    }
    
    
}
