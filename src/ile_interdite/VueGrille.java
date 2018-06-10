/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;

/**
 *
 * @author senno
 */
public class VueGrille extends JPanel {
    private HashMap<Tuile,VueTuile> tuiles;
    VueGrille(Observateur observateurdesTuiles, Grille grille) {
        super(new GridLayout(6, 6));
        this.tuiles = new HashMap<>();
        //TODO: populate tuiles
        Coordonnees coords = new Coordonnees(0, 0);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                coords.setX(i);
                coords.setY(j);
                Tuile tuile = grille.getTuile(coords);
                if(tuile != null){
                    VueTuile vueTuile = new VueTuile(tuile, observateurdesTuiles);
                    tuiles.put(tuile, vueTuile);
                    this.add(vueTuile.asJPanel());
                }else{
                    this.add(new JPanel());
                }
                
            }
        }
    }
    
    public void updateAll(){
        for (Map.Entry<Tuile, VueTuile> entry : tuiles.entrySet()) {
            Tuile key = entry.getKey();
            VueTuile value = entry.getValue();
            value.traiterMessage(new Message("update etat"));
            value.traiterMessage(new Message("update players"));

        }
    }
    
    public void surligner(HashMap<Tuile, Integer> tuilesASur){
        for (Map.Entry<Tuile, Integer> entrySet : tuilesASur.entrySet()) {
            Tuile key = entrySet.getKey();
            Integer value = entrySet.getValue();
            this.tuiles.get(key).surligner(value);
        }
    }
    public void stopSurligner(){
        for (Map.Entry<Tuile, VueTuile> entrySet : tuiles.entrySet()) {
            Tuile key = entrySet.getKey();
            VueTuile value = entrySet.getValue();
            value.stopSurligner();
        }
    }
    
}
