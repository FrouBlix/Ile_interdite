/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author senno
 */
public class IHM extends Observe{
    
    private JFrame fenetreJeu;
    private VueGrille grille;
    private VueAventurier vueAventurier;
    private VueEquipe vueEquipe;

    public IHM(Observateur observateur, Grille grilleaAfficher, ArrayList<Joueur> joueurs) {
        
        
        
        
        fenetreJeu = new JFrame();
        fenetreJeu.setTitle("Jeu");
        fenetreJeu.setSize(900, 800);
        fenetreJeu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetreJeu.setLayout(new BorderLayout());
        
        grille = new VueGrille(observateur, grilleaAfficher);
        fenetreJeu.add(grille, BorderLayout.CENTER);
        
        vueAventurier = new VueAventurier(observateur);
        vueAventurier.addObservateur(observateur);
        fenetreJeu.add(vueAventurier.asJPanel(), BorderLayout.SOUTH);
        
        vueEquipe = new VueEquipe(observateur, joueurs);
        fenetreJeu.add(vueEquipe.asJPanel(), BorderLayout.WEST);
        
        fenetreJeu.setVisible(true);
        
        
        this.addObservateur(observateur);
    }

    public void setFenetreJeu(JFrame fenetreJeu) {
        this.fenetreJeu = fenetreJeu;
    }

    public void setGrille(VueGrille grille) {
        this.grille = grille;
    }

    public void setVueAventurier(VueAventurier vueAventurier) {
        this.vueAventurier = vueAventurier;
    }

    public JFrame getFenetreJeu() {
        return fenetreJeu;
    }

    public VueGrille getGrille() {
        return grille;
    }

    public VueAventurier getVueAventurier() {
        return vueAventurier;
    }
    
    
    
}
