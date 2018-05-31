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
public abstract class Aventurier {
    private int pointsAction;       //Points d'action de l'aventurier
    private Tuile tuileOccupe;      //Tuile occupée par l'aventurier

    public int getPointsAction() {
        return pointsAction;
    }
    
    public Tuile getTuileOccupe(){
        return this.tuileOccupe;
    }
}

