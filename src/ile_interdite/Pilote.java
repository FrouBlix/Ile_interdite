/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author reboulef
 */
public class Pilote extends Aventurier{
    private boolean pouvoirUtilise = false;
    private boolean nextpouvoir = false;
    public Pilote(Tuile spawn) {
        super(spawn);
        this.getPion().setColor(Color.blue);
        this.pouvoirAActiver = true;
        this.pouvoirDispo = true;
    }

    @Override
    public HashMap<Tuile, Integer> getDeplacementPouvoir(Grille g) {
        if (this.getPointsAction() >= 1) {
            if (pouvoirDispo) {
            HashMap<Tuile, Integer> hm = new HashMap<>();
                for (Map.Entry<Coordonnees, Tuile> en : g.getAll().entrySet()) {
                    Coordonnees key = en.getKey();
                    Tuile value = en.getValue();
                    if (value != null && value.getEtat() != EtatsTuiles.sombree) {
                        if (value.equals(this.getTuileOccupee())) {
                            hm.put(value, 0);
                        }else{
                            hm.put(value, 1);
                        }
                    }
                }
                nextpouvoir = true;
                this.setSaveDP(hm);
                return hm; 
            }else{
                return null;
            }
        } else{
            HashMap<Tuile, Integer> hm = new HashMap<>();
            hm.put(this.getTuileOccupee(), 0);
            this.setSaveDP(hm);
            return hm;
        }
           
    }

    @Override
    public boolean seDeplacer(Tuile destination) {
        if (destination != this.getTuileOccupee() && nextpouvoir) {
            pouvoirDispo = false;
        }
        return super.seDeplacer(destination);
    }
    
    
    
}
