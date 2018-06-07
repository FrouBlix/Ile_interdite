/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        HashMap<Tuile,Integer> allSaveDP = new HashMap<>();
        if(this.getPointsAction() >0){
                    this.propager(grille, tdd, allSaveDP, 1);
        }
        
        for (Map.Entry<Tuile, Integer> entry : allSaveDP.entrySet()) {
            Tuile key = entry.getKey();
            Integer value = entry.getValue();
            if (key.getEtat() != EtatsTuiles.sombree) {
                this.getSaveDP().put(key, value);
            }
        }
        this.getSaveDP().put(tdd,0); //rester au meme endroit ne coute rien

        
        return this.getSaveDP();
    }

    
    
    public void propager(Grille grille, Tuile tuileDeDepart, HashMap<Tuile,Integer> allSaveDP, int cout) {
        Coordonnees coords = tuileDeDepart.getCoordonnees();
        ArrayList<Tuile> al = new ArrayList<>(); // cet arraylist contient les tuiles nouvellement considerees
        Tuile tuile = grille.getTuile(coords.getPlus(-1, 0));
        testTuile(tuile, al,allSaveDP, cout);
        
        tuile = grille.getTuile(coords.getPlus(0, -1));
        testTuile(tuile, al,allSaveDP, cout);

        tuile = grille.getTuile(coords.getPlus(1, 0));
        testTuile(tuile, al,allSaveDP, cout);

        tuile = grille.getTuile(coords.getPlus(0, 1));
        testTuile(tuile, al,allSaveDP, cout);
        
        if (cout < this.getPointsAction()) {
            for(Tuile tdd: al){
                
                if (tdd.getEtat() != EtatsTuiles.seche) {
                    this.propager(grille, tdd,allSaveDP, cout); //I'll save you! Recursion powers Activate!
                }else{
                    this.propager(grille, tdd,allSaveDP, cout+1); //I'll save you! Recursion powers Activate!
                }
                
                
                
            }      
        }
    }

    public void testTuile(Tuile tuile, ArrayList<Tuile> al, HashMap<Tuile,Integer> allSaveDP, int cout) {
        if (tuile != null) {
            
            int oldCout;
            if(allSaveDP.get(tuile) == null){
                oldCout = 4; // 4 sera toujours plus grand que cout.
            }else{
                oldCout = allSaveDP.get(tuile);
            }
            if (oldCout > cout) {
                
                allSaveDP.put(tuile, cout);
                al.add(tuile);
//                System.out.println(tuile.getNom());
            }
            
            
        }
    }


    
    
}


