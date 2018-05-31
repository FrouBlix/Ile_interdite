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
    private String nom;
    private Coordonnees coordonnees;
    
    public Coordonnees getCoordonnees(){
        return this.coordonnees;
    }
    
    public String getNom(){
        return this.nom;
    }
}