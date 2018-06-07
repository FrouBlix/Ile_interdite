/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author reboulef
 */
public class Plongeur extends Aventurier{

    public Plongeur(Tuile spawn) {
        super(spawn);
        this.getPion().setColor(Color.decode("#368BC1"));
    }

    @Override
    public HashMap<Tuile, Integer> getDeplacementPossible(Grille grille) {
        Tuile tdd  = this.getTuileOccupee();//tuile de depart
        this.getSaveDP().put(tdd,0); //rester au meme endroit ne coute rien
        ArrayList<Tuile> validees = new ArrayList<>();
        if(this.getPointsAction() >0){
                    this.propager(grille, tdd, validees, 1);
        }
        return this.getSaveDP();
    }

    
    
    public void propager(Grille grille, Tuile tuileDeDepart, ArrayList<Tuile> validees, int cout) {
        Coordonnees coords = tuileDeDepart.getCoordonnees();
        ArrayList<Tuile> al = new ArrayList<>(); // cet arraylist contient les tuiles nouvellement considerees
        Tuile tuile = grille.getTuile(coords.getPlus(-1, 0));
        testTuile(tuile, al, cout);
        
        tuile = grille.getTuile(coords.getPlus(0, -1));
        testTuile(tuile, al, cout);

        tuile = grille.getTuile(coords.getPlus(1, 0));
        testTuile(tuile, al, cout);

        tuile = grille.getTuile(coords.getPlus(0, 1));
        testTuile(tuile, al, cout);
        validees.add(tuileDeDepart);
        if (cout < this.getPointsAction()) {
            for(Tuile tdd: al){
                if (!validees.contains(tdd)) {
                    if (tdd.getEtat() != EtatsTuiles.seche) {
                        this.propager(grille, tdd,validees, cout); //I'll save you! Recursion powers Activate!
                    }else{
                        this.propager(grille, tdd,validees, cout+1); //I'll save you! Recursion powers Activate!
                    }
                }
                
                
            }      
        }
    }

    public void testTuile(Tuile tuile, ArrayList<Tuile> al, int cout) {
        if (tuile != null) {
            
            int oldCout;
            if(this.getSaveDP().get(tuile) == null){
                oldCout = 4; // 4 sera toujours plus grand que cout.
            }else{
                oldCout = this.getSaveDP().get(tuile);
            }
            if (oldCout > cout) {
                
                if (tuile.getEtat() != EtatsTuiles.sombree) {
                    this.getSaveDP().put(tuile, cout);
                }
                al.add(tuile);
                System.out.println(tuile.getNom());
            }
            
            
        }
    }


    
    
}


