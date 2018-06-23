/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author senno
 */
public class IHM extends Observe{
   
    private IHMMenu fenetreMenu;
    private JFrame fenetreJeu;
    private IHMFin fenetreFin;
    private VueGrille grille;
    private VueAventurier vueAventurier;
    private VueEquipe vueEquipe;
    private IHMDefausse ihmDefausse;
    private VueStatut vueStatut;

    public IHM(Observateur observateur, Grille grilleaAfficher, ArrayList<Joueur> joueurs, MonteeDesEaux mde) {
        
        fenetreMenu = new IHMMenu();
        fenetreJeu = new JFrame();
        fenetreJeu.setTitle("Jeu");
        fenetreJeu.setSize(1000, 900);
        fenetreJeu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetreJeu.setLayout(new BorderLayout());
        
        grille = new VueGrille(observateur, grilleaAfficher);
        fenetreJeu.add(grille, BorderLayout.CENTER);
        
        vueAventurier = new VueAventurier(observateur);
        vueAventurier.addObservateur(observateur);
        fenetreJeu.add(vueAventurier.asJPanel(), BorderLayout.SOUTH);
        
        vueEquipe = new VueEquipe(observateur, joueurs);
        fenetreJeu.add(vueEquipe.asJPanel(), BorderLayout.WEST);
        
        vueStatut = new VueStatut(mde);
        fenetreJeu.add(vueStatut, BorderLayout.EAST);
        
        fenetreMenu.setVisible(true);
        
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
    
    
    public void afficherDefausse(Joueur joueur){
        this.ihmDefausse = new IHMDefausse(this.getObservateur(), joueur);
    }

    public VueEquipe getVueEquipe() {
        return vueEquipe;
    }

    public IHMDefausse getIhmDefausse() {
        return ihmDefausse;
    }
    
    public void fermerIhmDefausse(){
        this.getIhmDefausse().fermer();
    }

    public VueStatut getVueStatut() {
        return vueStatut;
    }
    
    public void finirPartie(int cas){
        fenetreJeu.setVisible(false);
        fenetreFin = new IHMFin(cas);
        fenetreFin.setVisible(true);
    }
    
    
    
}
