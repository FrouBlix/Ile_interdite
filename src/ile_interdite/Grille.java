/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import java.util.HashMap;

/**
 *
 * @author reboulef
 */
public class Grille {

    public Grille() {
        this.tuiles = new HashMap<>();
    }
    private HashMap<Coordonnees,Tuile> tuiles;
    
    public void addTuile(Tuile tuile){
        this.tuiles.put(tuile.getCoordonnees(), tuile);
    }
    
}
