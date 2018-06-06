/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import javax.swing.JPanel;

/**
 *
 * @author senno
 */
public class VueAventurier extends Observe{
    private JPanel panel;

    public VueAventurier(Observateur obs) {
        this.panel = new JPanel();
        this.addObservateur(obs);
    }
    
    
    
    public JPanel asJPanel(){
        return this.panel;
    }
}
