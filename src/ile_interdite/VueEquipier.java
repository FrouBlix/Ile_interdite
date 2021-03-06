/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author billo
 */
public class VueEquipier extends JPanel implements Observateur{
    private JPanel panelInfo;
    private JPanel panelCartes;
    private Joueur joueur;
    public VueEquipier(Joueur j) {
        super(new GridLayout(2, 1));
        j.getPersonnage().addObservateur(this);
        
        this.joueur = j;
        
        panelInfo = new JPanel(new FlowLayout());
        Pion pion = new Pion().setColorRet(j.getPersonnage().getPion().getColor());
        panelInfo.add(pion);
        pion.setPreferredSize(new Dimension(20, 20));

        panelInfo.add(new JLabel(j.getNom()));
        this.add(panelInfo);   
        
        panelCartes = new JPanel();
        this.add(panelCartes);
        this.updateCartes();
    }
    
    public void updateCartes(){
        panelCartes.removeAll();
        for (CarteTirage carte : joueur.getPersonnage().getCartesMain()) {
            if (carte instanceof CarteTresor) {
                CarteTresor ca = (CarteTresor) carte;
                switch(ca.getType()){
                    case cristal:
                        panelCartes.add(new Pion().setColorRet(Color.red)); // on utilise des pions pour simplifier l'affichage.
                        break;
                    case calice:
                        panelCartes.add(new Pion().setColorRet(Color.CYAN));
                        break;
                    case pierre:
                        panelCartes.add(new Pion().setColorRet(Color.blue));
                        break;
                    case griffon:
                        panelCartes.add(new Pion().setColorRet(Color.yellow));
                        break;
                }
            }else{
                if (carte instanceof CarteHelico) {
                    panelCartes.add(new Pion().setColorRet(Color.decode("#FF00FF")));
                }else{
                    if (carte instanceof CarteSacSable) {
                        panelCartes.add(new Pion().setColorRet(Color.decode("#FFA500")));
                    }
                }
            }
        }
        this.revalidate();
        this.repaint();
    }
    
    @Override
    public void traiterMessage(Message msg) {
        if ("update".equals(msg.contenu)) {
            this.updateCartes();
        }
    }
    
    public void surligner(boolean surligner){
        if (surligner) {
            this.setBorder(BorderFactory.createLineBorder(Color.yellow, 3, true));
        }else{
            this.setBorder(null);
        }
    }
    
    public void selectionner(boolean select){
        this.setBorder(select ? BorderFactory.createLineBorder(Color.red, 3, true) : BorderFactory.createLineBorder(Color.yellow, 3, true));
    }
    
}
