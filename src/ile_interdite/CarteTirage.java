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
public abstract class CarteTirage{
    
    private String nom;
    
    public CarteTirage(String nom){
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }
    
    
}