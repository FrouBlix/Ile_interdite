/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author reboulef
 */
public class Messager extends Aventurier{

    public Messager(Tuile spawn) {
        super(spawn);
        this.getPion().setColor(Color.white);
    }
    
    @Override
    public boolean isDonnationPossible(Aventurier a){
        return true;
    }

    @Override
    public ArrayList<Aventurier> getAventurierDonne(ArrayList<Aventurier> aventuriers) {
        this.setSaveDonationsP(new ArrayList<>());
        this.getSaveDonationsP().addAll(aventuriers);
        this.getSaveDonationsP().remove(this);
        return this.getSaveDonationsP();
    }
    
}
