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
    private Tuile tuileOccupee;      //Tuile occupée par l'aventurier
    private HashMap<Tuile,Integer> saveDP; //deplacements possibles
    private Pion pion;

    public Aventurier(Tuile spawn){
        this.setTuileOccupee(spawn);
        this.saveDP = new HashMap<>();
        this.pion = new Pion();
    }
    
    public Pion getPion(){
        return this.pion;
    }
    
    public int getPointsAction() {
        return pointsAction;
    }
    
    public Tuile getTuileOccupee(){
        return this.tuileOccupee;
    }

    public void setPointsAction(int pointsAction) {
        this.pointsAction = pointsAction;
    }

    public void setTuileOccupee(Tuile tuileOccupee) {
        if (this.tuileOccupee != null) {
            this.tuileOccupee.removeAventurier(this);

        }
        this.tuileOccupee = tuileOccupee;
        this.tuileOccupee.addAventurier(this);

    }
    
    
    
    public HashMap<Tuile, Integer> getDeplacementPossible(Grille grille){ // cette methode renvoie un Hashmap qui pour chaque tuile renvoie le cout de s'y deplacer.
        Tuile tdd  = this.getTuileOccupee();//tuile de depart
        this.saveDP.put(tdd,0); //rester au meme endroit ne coute rien
        this.propager(grille, tdd, 1);
        return this.saveDP;
    }
    
    
    public void propager(Grille grille, Tuile tuileDeDepart, int cout){ 
        Coordonnees coords = tuileDeDepart.getCoordonnees();
        ArrayList<Tuile> al = new ArrayList<>(); // cet arraylist contient les tuiles nouvellement considerees
        coords.setXplus(-1);
        Tuile tuile = grille.getTuile(coords);
        testTuile(tuile, al, cout);
        
        coords.setXplus(1);
        coords.setYplus(-1);
        tuile = grille.getTuile(coords);
        testTuile(tuile, al, cout);
        
        coords.setXplus(1);
        coords.setYplus(1);
        tuile = grille.getTuile(coords);
        testTuile(tuile, al, cout);

        
        coords.setXplus(-1);
        coords.setYplus(1);
        tuile = grille.getTuile(coords);
        testTuile(tuile, al, cout);
        
        if (cout < this.getPointsAction()) {
            for(Tuile tdd: al){
                this.propager(grille, tdd, cout+1); //I'll save you! Recursion powrs Activate!
            }      
        }
    }
    
    public void testTuile(Tuile tuile, ArrayList<Tuile> al, int cout){
         if (tuile != null  && tuile.getEtat() != EtatsTuiles.sombree && (this.saveDP.get(tuile) == null ? 0 : this.saveDP.get(tuile))>cout) {//enfin j'espere
            this.saveDP.put(tuile, cout);
            al.add(tuile);
        }
    }
    
    public boolean seDeplacer(Tuile destination){
        if(this.saveDP.get(destination) !=null && this.saveDP.get(destination) <= this.getPointsAction()){
            this.setTuileOccupee(destination);
            this.saveDP = new HashMap<>();
            return true;
        }else{
            return false;
        }
    }
    
    
}

