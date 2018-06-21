/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.naming.OperationNotSupportedException;
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
    private boolean donner = false;
    
    private JButton boutonNext, boutonPrevious;
    private JPanel panelCartesCentral;
    
    private HashMap<CarteTirage, VueCarte> cartes;


    public VueAventurier(Observateur obs) {
        this.cartes = new HashMap<>();
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
        
        
        this.boutonPrendre = new JButton("Prendre une relique");
        boutonPrendre.setPreferredSize(boutonPrendre.getPreferredSize());
        boutonPrendre.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                notifierObservateur(new Message("prendre"));
            }
        });
        boutonPrendre.setEnabled(false); // impossible de pouvoir prendre une relique des le premier tour
        
        
        
        this.boutonDonner = new JButton("Donner");
        this.boutonDonner.setPreferredSize(this.boutonDonner.getPreferredSize()); // fixe la taille du bouton
        this.boutonDonner.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDonner(!donner);
                notifierObservateur(new Message(donner? "donner" : "stop donner"));
                boutonDonner.setText(donner? "Annuler" : "Donner");
            }
        });
        

        this.panelBoutons.add(boutonBouger);
        this.panelBoutons.add(boutonAssecher);
        this.panelBoutons.add(boutonPasser);
        this.panelBoutons.add(boutonPouvoir);
        this.panelBoutons.add(boutonDonner);
        this.panelBoutons.add(boutonPrendre);
        
        panel.add(panelBoutons, BorderLayout.EAST);
        
        panelCartes = new JPanel(new BorderLayout());
        boutonNext = new JButton(">");
        boutonNext.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                notifierObservateur(new Message("cartes next"));
            }
        });
        boutonPrevious = new JButton("<");
        boutonPrevious.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                notifierObservateur(new Message("cartes prev"));
            }
        });

        panelCartesCentral = new JPanel(new GridLayout(1, 0));

        panelCartes.add(boutonPrevious, BorderLayout.WEST);
        panelCartes.add(boutonNext, BorderLayout.EAST);
        panelCartes.add(panelCartesCentral, BorderLayout.CENTER);



        panel.add(panelCartes,BorderLayout.CENTER);
        
        
        
        
        
        
    }
    
    public void setBouger(boolean bouger){
        this.bouger = bouger;
        boutonBouger.setText(bouger? "Annuler" : "Se deplacer");
    }
    
    public void setAssecher(boolean assecher){
        this.assecher = assecher;
        boutonAssecher.setText(assecher? "Annuler" : "Assecher");
    }
    public void enableAssecher(boolean enable){
        boutonAssecher.setEnabled(enable);
    }
    
    public void setPouvoir(boolean pouvoir){
        this.pouvoir = pouvoir;
        boutonPouvoir.setText(pouvoir? "Annuler" : "Activer Pouvoir");
    }
    
    
    public void setDonner(boolean donner){
        this.donner = donner;
        boutonDonner.setText(donner? "Annuler" : "Donner");
        this.boutonNext.setEnabled(!donner);
        this.boutonPrevious.setEnabled(!donner);
    }
    
    public void setPouvoirAActiver(boolean p){
        boutonPouvoir.setEnabled(p);
    }
    
    public void setPrendreRelique(boolean b){
        boutonPrendre.setEnabled(b);
    }
    
    public void setColor(Color c){
        panelCartesCentral.setBackground(c);
        panelCartesCentral.repaint();
    }

    
    public void resetBoutons(){
        setAssecher(false);
        setBouger(false);
        setDonner(false);
        setPouvoir(false);
    }
    
    public void afficherCartes(Aventurier a){
        cartes = new HashMap<>();
        panelCartesCentral.removeAll();
        for (CarteTirage carte : a.getCartesMain()) {
            VueCarte vue = new VueCarte(this.getObservateur(), carte);
            panelCartesCentral.add(vue.asJPanel());
            cartes.put(carte, vue);
        }
        panelCartesCentral.revalidate();
        panelCartesCentral.repaint();
    }
    
    public void surlignerCartesDonnables(boolean surligner){
        for (Map.Entry<CarteTirage, VueCarte> entry : cartes.entrySet()) {
            CarteTirage key = entry.getKey();
            VueCarte value = entry.getValue();
            if (key instanceof CarteTresor) {
                value.surligner(surligner);
            }
        }
    }
    
    public void surlignerCarte(CarteTirage carte){
        cartes.get(carte).surligner(true);
    }
    
    public void stopSurligner(){
        for (Map.Entry<CarteTirage, VueCarte> entry : cartes.entrySet()) {
            VueCarte value = entry.getValue();
            value.surligner(false);
        }
    }
    
    public void desactiverBoutons(int nombredePA){
        this.boutonBouger.setEnabled(nombredePA > 0);
        this.boutonDonner.setEnabled(nombredePA > 0);
    }
    
    public JPanel asJPanel(){
        return this.panel;
    }
}
