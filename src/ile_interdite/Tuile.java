/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import java.util.ArrayList;

/**
 *
 * @author reboulef
 */
public class Tuile extends Observe{


 
    private EtatsTuiles etat;
    private final Special special;
    private final String nom;
    private Coordonnees coordonnees;
    private ArrayList<Aventurier> aventuriers;
    
    public void addAventurier(Aventurier a){
        if (!this.aventuriers.contains(a)) {
            this.aventuriers.add(a);
            notifierObservateur(new Message("update players"));

        }
    }
    
    public void removeAventurier(Aventurier a){
        this.aventuriers.remove(a);
        notifierObservateur(new Message("update players"));
    }
    
    public ArrayList<Aventurier> getAventuriers(){
        return this.aventuriers;
    }
    
    public Special getSpecial() {
        return special;
    }

    public EtatsTuiles getEtat() {
        return etat;
    }

    public void setEtat(EtatsTuiles etat) {
        this.etat = etat;
        this.notifierObservateur(new Message("update etat"));
    }

    public Tuile(Special special, String nom, Coordonnees coordonnees) {
        this.special = special;
        this.nom = nom;
        this.coordonnees = coordonnees;
        this.etat = etat.seche; // une tuile nouvellement cree est forcement seche
        this.aventuriers = new ArrayList<>();
    }

    public Coordonnees getCoordonnees(){
        return this.coordonnees;
    }
    
    public void setCoordonnees(Coordonnees coords){
        this.coordonnees = coords;
    }
    
    public String getNom(){
        return this.nom;
    }


       @Override
    public String toString() {
        return "Tuile :" + this.getCoordonnees() + "\n"
                + "\tNom:" + this.getNom()+"\n"
                + "\tEtat:" + this.getEtat()+"\n"
                + "\tSpecial:" + this.getSpecial();
    }
}