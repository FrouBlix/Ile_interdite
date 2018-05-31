/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

/**
 *
 * @author reboulef
 */
public class Tuile {

 
    private EtatsTuiles etat;
    private final Special special;
    private final String nom;
    private Coordonnees coordonnees;
    
    public Special getSpecial() {
        return special;
    }

    public EtatsTuiles getEtat() {
        return etat;
    }

    public void setEtat(EtatsTuiles etat) {
        this.etat = etat;
    }

    public Tuile(Special special, String nom, Coordonnees coordonnees) {
        this.special = special;
        this.nom = nom;
        this.coordonnees = coordonnees;
        this.setEtat(EtatsTuiles.seche); // une tuile nouvellement cree est forcement seche
    }

    public Coordonnees getCoordonnees(){
        return this.coordonnees;
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