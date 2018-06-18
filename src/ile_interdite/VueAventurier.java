/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author senno
 */
public class VueAventurier extends Observe{

    
    private JPanel panel;
    private JPanel panelCartes;
    private JPanel panelBoutons;
    
    private JButton boutonBouger;
    private boolean bouger = false;
    private JButton boutonAssecher;
    private boolean assecher = false;
    private JButton boutonPouvoir;
    private boolean pouvoirAActiver = false;
    private boolean pouvoir = false;
    private JButton boutonPasser;
    private JButton boutonDonner;
    private JButton boutonPrendre;
    


    public VueAventurier(Observateur obs) {
        this.panel = new JPanel();
        this.panel.setLayout(new BorderLayout());
        this.addObservateur(obs);
        
        this.panelBoutons = new JPanel(new GridLayout(2,3));
        
        
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
        
        this.boutonPouvoir = new JButton("Activer pouvoir");
        this.boutonPouvoir.setPreferredSize(this.boutonPouvoir.getPreferredSize()); // fixe la taille du bouton
        this.boutonPouvoir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pouvoir = !pouvoir;
                notifierObservateur(new Message(pouvoir? "pouvoir" : "stop pouvoir"));
                boutonPouvoir.setText(pouvoir? "Annuler" : "Activer pouvoir");
            }
        });
        
        
        this.boutonPrendre = new JButton("prendre");
        this.boutonDonner = new JButton("donner");
        
        

        this.panelBoutons.add(boutonBouger);
        this.panelBoutons.add(boutonAssecher);
        this.panelBoutons.add(boutonPasser);
        this.panelBoutons.add(boutonPouvoir);
        this.panelBoutons.add(boutonDonner);
        this.panelBoutons.add(boutonPrendre);
        
        panel.add(panelBoutons, BorderLayout.EAST);
        
        
        panelCartes = new JPanel();
        
        
        

        panel.add(panelCartes,BorderLayout.CENTER);
        
        //this.panel.add(panelInfo);
    }
    
    public void setBouger(boolean bouger){
        this.bouger = bouger;
        boutonBouger.setText(bouger? "Annuler" : "Se deplacer");
    }
    
    public void setAssecher(boolean assecher){
        this.assecher = assecher;
        boutonAssecher.setText(bouger? "Annuler" : "Assecher");
   
    }
    
    public void setPouvoir(boolean pouvoir){
        this.pouvoir = pouvoir;
        boutonPouvoir.setText(pouvoir? "Annuler" : "Activer Pouvoir");
    }
    
    public void setPouvoirAActiver(boolean p){
        boutonPouvoir.setEnabled(p);
    }
    

    
    public JPanel asJPanel(){
        return this.panel;
    }
}
