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
    
    public static int lastId =0;
    private String nom;
    private Special type;
    
    private int id;
    
    public CarteTirage(String nom, Special type){
        this.nom = nom;
        this.type = type;
        this.id = CarteTirage.lastId +1;
        CarteTirage.lastId ++;
    }

    public String getNom() {
        return nom;
    }
    
    public Special getType(){
        return this.type;
    }
    
    public int getId(){
        return this.id;
    }

    
    
}
