/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import java.net.CookieHandler;
import java.util.HashMap;

/**
 *
 * @author reboulef
 */
public class Grille {
    private HashMap<Coordonnees,Tuile> tuiles;

    public Grille() {
        this.tuiles = new HashMap<>();
        this.tuiles.put(new Coordonnees(0, 0), null); //remplir les coins avec des null
        this.tuiles.put(new Coordonnees(0, 1), null);
        this.tuiles.put(new Coordonnees(1, 0), null);
        this.tuiles.put(new Coordonnees(0, 4), null);
        this.tuiles.put(new Coordonnees(0, 5), null);
        this.tuiles.put(new Coordonnees(1, 5), null);
        this.tuiles.put(new Coordonnees(4, 0), null);
        this.tuiles.put(new Coordonnees(5, 0), null);
        this.tuiles.put(new Coordonnees(5, 1), null);
        this.tuiles.put(new Coordonnees(4, 5), null);
        this.tuiles.put(new Coordonnees(5, 4), null);
        this.tuiles.put(new Coordonnees(5, 5), null);
        addTuile(new Tuile(Special.cristal, "saucisse", new Coordonnees(3, 3)));
    }
    
    public void addTuile(Tuile tuile){
        this.tuiles.put(tuile.getCoordonnees(), tuile);
    }
    
    public void autoAddTuile(Tuile tuile) throws Exception{
        Coordonnees coords = new Coordonnees(0, 0);
        for (int i = 0; i < 6; i++) {
            coords.setX(i);
            for (int j = 0; j < 6; j++) {
                coords.setY(j);
                if (!this.tuiles.containsKey(coords)) {
                    tuile.setCoordonnees(coords);
                    this.addTuile(tuile);
                    return;
                }
            }
        }
        throw new Exception("Grille: sorti de la boucle en ajoutant " + tuile);
    }
    
    public Tuile getTuile(Coordonnees coords){
        return this.tuiles.get(coords);
    }
    
}
