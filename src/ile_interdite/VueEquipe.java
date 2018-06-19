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
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;

/**
 *
 * @author billo
 */
public class VueEquipe extends Observe{
    private JPanel panel;
    private HashMap<Joueur, VueEquipier> equipiers;

    public VueEquipe(Observateur obs, ArrayList<Joueur> joueurs) {
        equipiers = new HashMap<>();
        this.addObservateur(obs);
        this.panel = new JPanel(new GridLayout(4, 1));
        for (Joueur joueur : joueurs) {
            VueEquipier panel = new VueEquipier(joueur);
            equipiers.put(joueur, panel);
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
    
    public void surligner(boolean surligner, ArrayList<Aventurier> aventuriers){
        for (Map.Entry<Joueur, VueEquipier> entry : equipiers.entrySet()) {
            Joueur key = entry.getKey();
            VueEquipier value = entry.getValue();
            if (aventuriers.contains(key.getPersonnage())) {
                value.surligner(surligner);
            }
        }
    }
    
    
}
