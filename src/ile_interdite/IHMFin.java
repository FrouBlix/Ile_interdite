/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import GraphicsUtil.ImagePanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author Félix Reboulet
 */
public class IHMFin extends JFrame{
    private JTextArea messageFin;
    private JLabel titreMessage;
    private ImagePanel image;
    private JButton quitter;
    private JButton menu;
    

    public IHMFin(int cas){
        super("Fin de partie");
        this.setSize(1300, cas < 5 ? 390 : 460);
        
        switch (cas){
            case 5:
                messageFin = new JTextArea("Félicitatons vous avez récuperé tous les trésors\net vous avez réussi à quitter l'île !!\nVotre mission est un succès.");
            break;
            
            case 4:
                messageFin = new JTextArea("Le compteur de la montée des eaux a atteint son paroxysme,\nvous avez échoué.");
                break;
                
            case 3:
                messageFin = new JTextArea("L'un des Trésor a sombré avec l'île,\nvous ne pouvez donc plus le récupérer.\nVotre mission est un échec.");
                break;
                
            case 2:
                messageFin = new JTextArea("L'Héliport a sombré avec l'île ,\nvous ne pouvez plus vous échapper.\nVous êtes comdamner.");
                break;
                
            case 1:
                messageFin = new JTextArea("L'un des aventurier s'est noyer, vous avez échoué.\nVotre mission est un échec.");
                break;
                
            default:
                break;
            
        }
        
        messageFin.setLineWrap(true);
        messageFin.setBackground(Color.pink);
        messageFin.setTabSize(32);
        
        
        this.setLayout(new GridLayout(1,2));

        JLabel information = new JLabel();
        information.setLayout(new BorderLayout());

        JLabel boutons = new JLabel();
        boutons.setLayout(new BorderLayout());
        
        image = new ImagePanel(cas < 5 ? "image-de-Défaite.jpg" : "image-de-Victoire.jpg", cas < 5 ? 1.25 : 1.50);
        titreMessage = new JLabel(cas < 5 ? "DEFAITE" : "VICTOIRE");
        information.setBackground(Color.pink);
        this.setBackground(Color.PINK);
        
        quitter = new JButton("QUITTER");
        menu = new JButton("MENU");


        information.add(titreMessage, BorderLayout.NORTH);
        information.add(messageFin, BorderLayout.CENTER);
        information.add(boutons, BorderLayout.SOUTH);

        boutons.add(menu, BorderLayout.WEST);
        boutons.add(quitter, BorderLayout.EAST);
        
        this.add(image);
        this.add(information);


    }
}
