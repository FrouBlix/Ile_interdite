/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author billo
 */
public class VueStatut extends JPanel{
    private VueMDE vueMDE;
    private VueTresor vueTresor;
    private JButton menu;
    
    public VueStatut(MonteeDesEaux mde) {
        super(new BorderLayout());
        menu = new JButton("Menu");
        this.add(menu, BorderLayout.PAGE_START);
        vueMDE = new VueMDE(mde);
        this.add(vueMDE, BorderLayout.CENTER);
        vueTresor = new VueTresor();
        this.add(vueTresor, BorderLayout.PAGE_END);
    }

    public VueMDE getVueMDE() {
        return vueMDE;
    }

    public VueTresor getVueTresor() {
        return vueTresor;
    }

    public JButton getMenu(){
        return this.menu;
    }
}
