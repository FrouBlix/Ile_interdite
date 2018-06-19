/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import java.awt.BorderLayout;
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
public class VueTuile extends Observe implements Observateur{
    private JPanel panelTuile;
    private JPanel panelPions;
    private JLabel labelTuile;
    private JLabel labelCout;
    private JPanel panelTop;
    private Tuile tuile;
    public VueTuile(Tuile tuile, Observateur obs) {
        this.tuile = tuile;   
        this.addObservateur(obs);
        panelTuile = new JPanel(new BorderLayout());
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
        this.panelTop = new JPanel();
        this.labelTuile = new JLabel(tuile.getNom());
        switch(this.tuile.getSpecial()){
            case calice: 
                this.panelTop.setBackground(Color.white);
                this.labelTuile.setForeground(Color.black);
                break;
            case cristal:
                this.panelTop.setBackground(Color.red);
                break;
            case griffon:
                this.panelTop.setBackground(Color.yellow);
                break;
            case pierre:
                this.panelTop.setBackground(Color.blue);
                this.labelTuile.setForeground(Color.white);
                break;
            case heliport:
                this.panelTop.setBackground(Color.CYAN);
                break;
            default:
                this.panelTop.setOpaque(false);
                    break;
        }
        panelTop.add(labelTuile);
        panelTuile.add(this.panelTop, BorderLayout.NORTH);
        this.panelTuile.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        this.labelCout = new JLabel();
        this.panelTuile.add(labelCout, BorderLayout.SOUTH);
        this.tuile.addObservateur(this);
    }
    
    public JPanel asJPanel(){
        return this.panelTuile;
    }
    
    public void surligner(Integer cout){
        
        this.panelTuile.setBorder(BorderFactory.createLineBorder(Color.red, 1, true));
        this.labelCout.setText(cout.toString() + " PA");
        switch(this.tuile.getEtat()){
                case seche: this.panelTuile.setBackground(Color.green);
                    this.labelCout.setForeground(Color.darkGray);

                    break;
                case inondee: this.panelTuile.setBackground(Color.blue);
                    this.labelCout.setForeground(Color.white);
                    break;
                case sombree: this.panelTuile.setBackground(Color.darkGray);
                    this.labelCout.setForeground(Color.white);
            }
        this.panelTuile.repaint();
        this.labelCout.revalidate();
        this.labelCout.repaint();
    }

    public void stopSurligner(){
        this.panelTuile.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        this.labelCout.setText("");
        this.panelTuile.repaint();
        this.labelCout.revalidate();
        this.labelCout.repaint();
    }
    
    @Override
    public void traiterMessage(Message msg) {
        if ("update etat".equals(msg.contenu)) {
            switch(this.tuile.getEtat()){
                case seche: this.panelTuile.setBackground(Color.decode("#008800"));
                    this.labelCout.setForeground(Color.white);
                    break;
                case inondee: this.panelTuile.setBackground(Color.decode("#000088"));
                    this.labelCout.setForeground(Color.white);
                    break;
                case sombree: this.panelTuile.setBackground(Color.decode("#202020"));
                    this.labelCout.setForeground(Color.white);


            }
        }else if("update players".equals(msg.contenu)){
            if (panelPions != null) {
                this.panelTuile.remove(panelPions);
            }
            this.panelPions = new JPanel();            
            this.panelPions.setOpaque(false);
            for (Aventurier aventurier : this.tuile.getAventuriers()) {
                this.panelPions.add(aventurier.getPion());
                aventurier.getPion().revalidate(); //ouais au fait c'est REpaint
                aventurier.getPion().repaint();
            }

            this.panelTuile.add(panelPions, BorderLayout.CENTER);

        }
        
    }
    
    
    
    
}
