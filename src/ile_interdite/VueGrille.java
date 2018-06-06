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
                System.out.println(tuile + coords.toString());
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
    
    
}
