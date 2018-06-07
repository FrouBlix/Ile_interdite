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
public class Ingenieur extends Aventurier{
    private boolean prochainAssechementGratuit = false;
    public Ingenieur(Tuile spawn) {
        super(spawn);
        this.getPion().setColor(Color.red);
    }

    @Override
    public boolean seDeplacer(Tuile destination) {
        if (destination != this.getTuileOccupee()) {
            this.prochainAssechementGratuit = false;
        }
        return super.seDeplacer(destination); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean assecher(Tuile t) {
        if (this.getPointsAction() >0 && this.getSaveAP().containsKey(t)) {
            if (!prochainAssechementGratuit) {
                this.setPointsAction(this.getPointsAction()-1);
                prochainAssechementGratuit = true;
            }else{
                prochainAssechementGratuit = false;
            }
            
            t.setEtat(EtatsTuiles.seche);// pas besoin de tester plus: si la tuile est dans saveAP, elle est inondee.
            this.setSaveAP(new HashMap<>());
            return true;
        }
        return false;
    }

    @Override
    public void testTuileAss(Tuile tuile) {
        if (tuile != null && tuile.getEtat() == EtatsTuiles.inondee) {
            this.getSaveAP().put(tuile, (this.prochainAssechementGratuit? 0 : 1));
        }
    }


    
    
    
}
