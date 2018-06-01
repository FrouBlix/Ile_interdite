/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author reboulef
 */
public abstract class Aventurier {
    private int pointsAction;       //Points d'action de l'aventurier
    private Tuile tuileOccupe;      //Tuile occupée par l'aventurier
    private HashMap<Tuile,Integer> saveDP; //deplacements possibles

    public Aventurier(int PA, Tuile spawn){
        this.pointsAction = PA;
        this.tuileOccupe = spawn;
        this.saveDP = new HashMap<>();
    }
    
    public int getPointsAction() {
        return pointsAction;
    }
    
    public Tuile getTuileOccupe(){
        return this.tuileOccupe;
    }
    
    public HashMap<Tuile, Integer> getDeplacementPossible(Grille grille){
        Tuile tdd  = this.getTuileOccupe();//tuile de depart
        
        for (Tuile tuile : this.propager(grille,tdd)) {
             //TODO: je sais pas c'est complique
             //mais c'est pas fini
             
        }
        
        return this.saveDP;
    }
    
    
    public ArrayList<Tuile> propager(Grille grille, Tuile tuileDeDepart){ //TODO: commenter mon code lolmdr
        ArrayList<Tuile> al = new ArrayList<>();        
        Coordonnees coords = tuileDeDepart.getCoordonnees();
        
        coords.setXplus(-1);
        Tuile tuile = grille.getTuile(coords);
        if (tuile != null  && tuile.getEtat() != EtatsTuiles.sombree && !this.saveDP.containsKey(tuile)) {
            al.add(tuile);
        }
        
        coords.setXplus(1);
        coords.setYplus(-1);
        tuile = grille.getTuile(coords);
        if (tuile != null  && tuile.getEtat() != EtatsTuiles.sombree && !this.saveDP.containsKey(tuile)) {
            al.add(tuile);
        }
        
        coords.setXplus(1);
        coords.setYplus(1);
        tuile = grille.getTuile(coords);
        if (tuile != null  && tuile.getEtat() != EtatsTuiles.sombree && !this.saveDP.containsKey(tuile)) {
            al.add(tuile);
        }
        
        coords.setXplus(-1);
        coords.setYplus(1);
        tuile = grille.getTuile(coords);
        if (tuile != null  && tuile.getEtat() != EtatsTuiles.sombree && !this.saveDP.containsKey(tuile)) {
            al.add(tuile);
        }
        
        return al;
    }
}

