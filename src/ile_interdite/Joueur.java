/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

/**
 *
 * @author billo
 */
public class Joueur {
    private Aventurier personnage;
    private String nom;

    public Joueur(Aventurier personnage, String nom) {
        this.personnage = personnage;
        if ("".equals(nom)) {
            this.nom = personnage.getClass().getSimpleName();
        }else{
            this.nom = nom + " (" + personnage.getClass().getSimpleName() + ")";
        }
    }

    public Aventurier getPersonnage() {
        return personnage;
    }

    public String getNom() {
        return nom;
    }
    
    
    
}
