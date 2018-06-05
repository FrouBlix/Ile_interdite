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
        this.saveDP.put(tdd,0); //rester au meme endroit ne coute rien
        this.propager(grille, tdd, 1);
        return this.saveDP;
    }
    
    
    public void propager(Grille grille, Tuile tuileDeDepart, int cout){ //TODO: commenter mon code lolmdr
        Coordonnees coords = tuileDeDepart.getCoordonnees();
        ArrayList<Tuile> al = new ArrayList<>(); // cet arraylist contient les tuiles nouvellement considerees
        coords.setXplus(-1);
        Tuile tuile = grille.getTuile(coords);
        if (tuile != null  && tuile.getEtat() != EtatsTuiles.sombree && this.saveDP.get(tuile)>cout) { //la tuile existe (on ne sort pas de la map), elle n'est pas sombree, et l'eventuel chemin trouve precedemment est plus cher.
            this.saveDP.put(tuile, cout);
            al.add(tuile);
        }
        
        coords.setXplus(1);
        coords.setYplus(-1);
        tuile = grille.getTuile(coords);
        if (tuile != null  && tuile.getEtat() != EtatsTuiles.sombree && this.saveDP.get(tuile)>cout) {
            this.saveDP.put(tuile, cout);
            al.add(tuile);
        }
        
        coords.setXplus(1);
        coords.setYplus(1);
        tuile = grille.getTuile(coords);
        if (tuile != null  && tuile.getEtat() != EtatsTuiles.sombree && this.saveDP.get(tuile)>cout) {
            this.saveDP.put(tuile, cout);
            al.add(tuile);
        }
        
        coords.setXplus(-1);
        coords.setYplus(1);
        tuile = grille.getTuile(coords);
        if (tuile != null  && tuile.getEtat() != EtatsTuiles.sombree && this.saveDP.get(tuile)>cout) {
            this.saveDP.put(tuile, cout);
            al.add(tuile);
        }
        if (cout < this.getPointsAction()) {
            for(Tuile tdd: al){
                this.propager(grille, tdd, cout+1);
            }      
        }
    }
    
    
}

