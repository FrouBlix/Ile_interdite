/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author billo
 */
public class IHMDefausse extends Observe{
    private JFrame fenetre;
    private JPanel panelTop;
    private JPanel panelCenter;
    private JPanel panelBot;
    private int nombreDeCartesADefausser;
    

    public IHMDefausse(Observateur obs, Joueur joueur) {
        this.addObservateur(obs);
        fenetre = new JFrame();
        fenetre.setTitle("Defausser des cartes");
        fenetre.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        fenetre.setLayout(new BorderLayout());
        panelTop = new JPanel();
        panelCenter = new JPanel(new GridLayout(1, 0));
        panelBot = new JPanel();
        nombreDeCartesADefausser = joueur.getPersonnage().getCartesMain().size() - 5;
        panelTop.add(new JLabel(joueur.getNom()+ " doit se defausser de " + nombreDeCartesADefausser + " cartes."));
        panelTop.add(new JLabel("Selectionnez les cartes a defausser."));
        
        for (CarteTirage carte : joueur.getPersonnage().getCartesMain()) {
            panelCenter.add(new VueCarte(obs, carte).asJPanel());
        }
        
        
        
    }
    
    
}
