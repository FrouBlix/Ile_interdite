/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author billo
 */
public class VueEquipe extends Observe{
    private JPanel panel;

    public VueEquipe(Observateur obs, ArrayList<Joueur> joueurs) {
        this.addObservateur(obs);
        this.panel = new JPanel(new GridLayout(4, 1));
        for (Joueur joueur : joueurs) {
            VueEquipier panel = new VueEquipier(joueur);
            panel.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    notifierObservateur(new MessageAventurier("clic", joueur.getPersonnage()));
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });
            this.panel.add(panel);
        }
    }

    public JPanel asJPanel() {
        return panel;
    }
    
    
}
