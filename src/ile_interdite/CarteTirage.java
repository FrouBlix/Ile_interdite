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
    private Special type;
    
    public CarteTirage(String nom, Special type){
        this.nom = nom;
        this.type = type;
    }

    public String getNom() {
        return nom;
    }
    
    public Special getType(){
        return this.type;
    }
    
    
}
