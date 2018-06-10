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
    private Aventurier aventurierEnCours;
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
        this.grille.getTuilebyName("Les Dunes de l’Illusion").setEtat(EtatsTuiles.sombree);
        //fin de la demo
        
        this.ihm = new IHM(this,grille);

        this.ihm.getGrille().updateAll(); //on update toutes les tuiles apres avoir fini le chargement
        this.joueurEnCours = nombreDeJoueurs -1;
        this.prochainJoueur();
        
        
        
    }
    
    public void resetAction(){
        this.actionEnCours = ActionEnCours.rien;
        this.stopDeplacement();
    }
    
    public Aventurier prochainJoueur(){
        this.joueurEnCours ++;
        this.joueurEnCours %= this.nombreDeJoueurs;
        Aventurier a = this.listeDesJoueurs.get(joueurEnCours);
        a.setPointsAction(3);
        setAventurierEnCours(a);
        this.resetAction();
        return a;
    }
    
    public void ajouterJoueur(Aventurier a){
        this.nombreDeJoueurs ++;
        listeDesJoueurs.add(a);
    }
    
    public void actionDeplacer(){
//        System.out.println(this.aventurierEnCours);
        this.actionEnCours = ActionEnCours.bouger;
//        System.out.println(this.aventurierEnCours.getClass().getName());
        this.ihm.getGrille().surligner(aventurierEnCours.getDeplacementPossible(grille));
    }
    
    public void selectDeplacement(Tuile t){
        this.aventurierEnCours.seDeplacer(t);
        this.resetAction();
    }
    
    public void stopDeplacement(){
        this.ihm.getGrille().stopSurligner();
        this.ihm.getVueAventurier().setBouger(false);
        this.ihm.getGrille().updateAll();
    }
    
    @Override
    public void traiterMessage(Message msg) {
//        System.out.println("message: " + msg.contenu);
        if(msg.contenu == "fin de tour"){
            this.prochainJoueur();
        }
        if(msg.contenu == "bouger"){
            this.actionDeplacer();
        }
        if (msg.contenu == "stop bouger") {
            this.selectDeplacement(this.aventurierEnCours.getTuileOccupee()); //on annule le deplacement. pour quitter proprement, on dit juste qu'on bouge en direction du meme endroit
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

    public Aventurier getAventurierEnCours() {
        return aventurierEnCours;
    }

    public void setAventurierEnCours(Aventurier aventurierEnCours) {
        this.aventurierEnCours = aventurierEnCours;
    }
    
    
    
    
    
    
}
