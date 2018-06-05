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

    public void setPointsAction(int pointsAction) {
        this.pointsAction = pointsAction;
    }

    public void setTuileOccupe(Tuile tuileOccupe) {
        this.tuileOccupe = tuileOccupe;
    }
    
    
    
    public HashMap<Tuile, Integer> getDeplacementPossible(Grille grille){ // cette methode renvoie un Hashmap qui pour chaque tuile renvoie le cout de s'y deplacer.
        Tuile tdd  = this.getTuileOccupe();//tuile de depart
        this.saveDP.put(tdd,0); //rester au meme endroit ne coute rien
        this.propager(grille, tdd, 1);
        return this.saveDP;
    }
    
    
    public void propager(Grille grille, Tuile tuileDeDepart, int cout){ 
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
        if (tuile != null  && tuile.getEtat() != EtatsTuiles.sombree && this.saveDP.get(tuile)>cout) {//ouais je me repete.
            this.saveDP.put(tuile, cout);
            al.add(tuile);
        }
        
        coords.setXplus(1);
        coords.setYplus(1);
        tuile = grille.getTuile(coords);
        if (tuile != null  && tuile.getEtat() != EtatsTuiles.sombree && this.saveDP.get(tuile)>cout) {//le compilateur optimisera tout ca
            this.saveDP.put(tuile, cout);
            al.add(tuile);
        }
        
        coords.setXplus(-1);
        coords.setYplus(1);
        tuile = grille.getTuile(coords);
        if (tuile != null  && tuile.getEtat() != EtatsTuiles.sombree && this.saveDP.get(tuile)>cout) {//enfin j'espere
            this.saveDP.put(tuile, cout);
            al.add(tuile);
        }
        if (cout < this.getPointsAction()) {
            for(Tuile tdd: al){
                this.propager(grille, tdd, cout+1); //I'll save you! Recursion powrs Activate!
            }      
        }
    }
    
    public boolean seDeplacer(Tuile destination){
        if(this.saveDP.get(destination) !=null && this.saveDP.get(destination) <= this.getPointsAction()){
            this.setTuileOccupe(destination);
            this.saveDP = new HashMap<>();
            return true;
        }else{
            return false;
        }
    }
    
    
}

