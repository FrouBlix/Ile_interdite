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
public class Explorateur extends Aventurier{

    public Explorateur(Tuile spawn) {
        super(spawn);
        this.getPion().setColor(Color.decode("#228B22"));
    }

    @Override
    public void propager(Grille grille, Tuile tuileDeDepart, int cout) {
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
        
        testTuile(grille.getTuile(coords.getPlus(-1, -1)), al, cout);
        testTuile(grille.getTuile(coords.getPlus(1, 1)), al, cout);
        testTuile(grille.getTuile(coords.getPlus(-1, 1)), al, cout);
        testTuile(grille.getTuile(coords.getPlus(1, -1)), al, cout);


        
        if (cout < this.getPointsAction()) {
            for(Tuile tdd: al){
                this.propager(grille, tdd, cout+1); //I'll save you! Recursion powers Activate!
            }      
        }
    }

    @Override
    public HashMap<Tuile, Integer> getAssechementPossible(Grille grille) {
        this.setSaveAP(new HashMap<>());
        if (this.getPointsAction() > 0) {
            Coordonnees coords = this.getTuileOccupee().getCoordonnees();
            this.testTuileAss(this.getTuileOccupee());
            this.testTuileAss(grille.getTuile(coords.getPlus(0, -1)));
            this.testTuileAss(grille.getTuile(coords.getPlus(-1, 0)));
            this.testTuileAss(grille.getTuile(coords.getPlus(0, 1)));
            this.testTuileAss(grille.getTuile(coords.getPlus(1, 0)));
            this.testTuileAss(grille.getTuile(coords.getPlus(1, 1)));
            this.testTuileAss(grille.getTuile(coords.getPlus(-1, -1)));
            this.testTuileAss(grille.getTuile(coords.getPlus(-1, 1)));
            this.testTuileAss(grille.getTuile(coords.getPlus(1, -1)));
        }
        return this.getSaveAP();
    }
    
    
    
}
