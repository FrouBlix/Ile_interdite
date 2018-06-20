/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 * @author billo
 */
public class VueStatut extends JPanel{
    private VueMDE vueMDE;
    private VueTresor vueTresor;
    
    public VueStatut(MonteeDesEaux mde) {
        super(new BorderLayout());
        vueMDE = new VueMDE(mde);
        this.add(vueMDE, BorderLayout.NORTH);
        vueTresor = new VueTresor();
        this.add(vueTresor, BorderLayout.CENTER);
    }

    public VueMDE getVueMDE() {
        return vueMDE;
    }

    public VueTresor getVueTresor() {
        return vueTresor;
    }
    
}
