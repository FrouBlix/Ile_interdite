/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import java.util.ArrayList;
import org.omg.CORBA.INTERNAL;

/**
 *
 * @author senno
 */
public class Controlleur implements Observateur{

    private Grille grille;
    private IHM ihm;
    private ArrayList<Aventurier> listeDesJoueurs;
    private int joueurEnCours =0;
    private int nombreDeJoueurs=0;
    private ActionEnCours actionEnCours = ActionEnCours.rien;
    
    public Controlleur() {
        this.listeDesJoueurs = new ArrayList<>();
        this.grille = new Grille();
        
        //demo
        Messager joueurTest = new Messager(this.grille.getTuilebyName("Heliport"));
        Navigateur joueur2 = new Navigateur(this.grille.getTuilebyName("Heliport"));
        this.ajouterJoueur(joueurTest);
        this.ajouterJoueur(joueur2);
        this.grille.getTuilebyName("La Porte de Bronze").setEtat(EtatsTuiles.inondee);
        //fin de la demo
        
        
        
        this.ihm = new IHM(this,grille);

        this.ihm.getGrille().updateAll(); //on update toutes les tuiles apres avoir fini le chargement
        
        
        
        
    }
    
    public Aventurier prochainJoueur(){
        this.joueurEnCours ++;
        this.joueurEnCours %= this.nombreDeJoueurs;
        Aventurier a = this.listeDesJoueurs.get(joueurEnCours);
        a.setPointsAction(3);
        this.actionEnCours = ActionEnCours.rien;
        return a;
    }
    
    public void ajouterJoueur(Aventurier a){
        this.nombreDeJoueurs ++;
        listeDesJoueurs.add(a);
    }
    
    public void actionDeplacer(){
        this.actionEnCours = ActionEnCours.bouger;
        System.out.println(this.listeDesJoueurs.get(joueurEnCours).getClass().getName());
        this.ihm.getGrille().surligner(this.listeDesJoueurs.get(joueurEnCours).getDeplacementPossible(grille));
    }
    
    public void selectDeplacement(Tuile t){
        this.actionEnCours = ActionEnCours.rien;
        this.ihm.getGrille().stopSurligner();
        this.listeDesJoueurs.get(joueurEnCours).seDeplacer(t);
    }
    
    
    @Override
    public void traiterMessage(Message msg) {
        System.out.println("message: " + msg.contenu);
        if(msg.contenu == "fin de tour"){
            this.prochainJoueur();
        }
        if(msg.contenu == "bouger"){
            this.actionDeplacer();
        }
        
        
        
        if (msg instanceof MessageDeTuile) { // le polymorphisme c'est chouette
            MessageDeTuile msgDT = (MessageDeTuile) msg;
            if (msg.contenu == "clic") {
                switch(actionEnCours){
                    case bouger: this.selectDeplacement(msgDT.tuileDOrigine);
                        break;
                    case assecher:
                    default: break;
                }
            }
        }

    }
    
    
    
    
}
