/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.ColorModel;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author senno
 */
public class IHM extends Observe{
    
    private JFrame fenetreJeu;
    private VueGrille grille;

    public IHM(Observateur observateur, Grille grilleaAfficher) {
        
        
        
        
        fenetreJeu = new JFrame();
        fenetreJeu.setTitle("Jeu");
        fenetreJeu.setSize(900, 800);
        fenetreJeu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        grille = new VueGrille(observateur, grilleaAfficher);
        fenetreJeu.add(grille);
        
        fenetreJeu.setVisible(true);
        
        
        this.addObservateur(observateur);
    }
    
}
