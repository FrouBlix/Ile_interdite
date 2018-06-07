/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author senno
 */
public class VueAventurier extends Observe{

    
    private JPanel panel;
    private JButton boutonBouger;
    private boolean bouger = false;
    private JButton boutonAssecher;
    private boolean assecher = false;
    private JButton boutonPasser;

    public VueAventurier(Observateur obs) {
        this.panel = new JPanel();
        this.panel.setLayout(new GridLayout(0, 1));
        this.addObservateur(obs);
        this.boutonBouger = new JButton("Se deplacer");
        this.boutonBouger.setPreferredSize(this.boutonBouger.getPreferredSize()); // fixe la taille du bouton
        this.boutonBouger.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bouger = !bouger;
                notifierObservateur(new Message(bouger? "bouger" : "stop bouger"));
                boutonBouger.setText(bouger? "Annuler" : "Se deplacer");
            }
        });
        
        this.boutonPasser = new JButton("Passer le tour");
        this.boutonPasser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifierObservateur(new Message("fin de tour"));
            }
        });
        
        this.boutonAssecher = new JButton("Assecher");
        this.boutonAssecher.setPreferredSize(this.boutonAssecher.getPreferredSize()); // fixe la taille du bouton
        this.boutonAssecher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                assecher = !assecher;
                notifierObservateur(new Message(assecher? "assecher" : "stop assecher"));
                boutonAssecher.setText(assecher? "Annuler" : "Assecher");
            }
        });

        this.panel.add(boutonBouger);
        this.panel.add(boutonAssecher);
        this.panel.add(boutonPasser);
    }
    
    public void setBouger(boolean bouger){
        this.bouger = bouger;
        boutonBouger.setText(bouger? "Annuler" : "Se deplacer");
    }
    
    public void setAssecher(boolean assecher){
        this.assecher = assecher;
        boutonAssecher.setText(bouger? "Annuler" : "Assecher");
   
    }
    
    public JPanel asJPanel(){
        return this.panel;
    }
}
