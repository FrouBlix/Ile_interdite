/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;


import GraphicsUtil.ImagePanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


/**
 *
 * @author Félix Reboulet
 */
public class IHMMenu extends JFrame {
    
    private JLabel menu;
    private JLabel nbJoueur;
    private JLabel[] joueur = new JLabel[4];
    private JLabel difficulte;
    private JTextField[] champJoueur = new JTextField[4];
    private JComboBox choixNbJoueur;
    private String[] nombre = new String[3];
    private ImagePanel image;
    private JLabel information;
    private JLabel joueurs;
    
    public IHMMenu(){
        super("Ile interdite");
        this.setSize(1000,670);
        
        this.setLayout(new GridLayout(1,2));
        image = new ImagePanel("image-Menu.jpg",1.80);
        
        joueurs = new JLabel();
        joueurs.setLayout(new GridLayout(5,2));
        
        nbJoueur = new JLabel("Nombre de joueur :");
        joueurs.add(nbJoueur);
        
        for (int i = 0; i < 3 ; i++){
            nombre[i] = ""+(i+2);
        }
        choixNbJoueur = new JComboBox(nombre);
        choixNbJoueur.setSelectedIndex(0);
        joueurs.add(choixNbJoueur);
        
        for (int i = 0; i < 4; i++){
            joueur[i] = new JLabel("Joueur " + (i+1) + " :");
            joueurs.add(joueur[i]);
            champJoueur[i] = new JTextField();
            champJoueur[i].setColumns(30);
            joueurs.add(champJoueur[i]);
        }
        
        this.add(image, BorderLayout.WEST);
        this.add(joueurs);
    }

    private LayoutManager BorderLayout() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

    