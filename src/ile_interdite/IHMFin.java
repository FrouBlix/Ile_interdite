/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import GraphicsUtil.ImagePanel;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Félix Reboulet
 */
public class IHMFin extends JFrame{
    private JLabel fetreFin;
    private JLabel messageFin;
    private JLabel titreMessage;
    private ImagePanel image;
    private JButton quitter;
    private JButton menu;
    

    public IHMFin(){
        super("Fin de partie");
        
        this.setLayout(new GridLayout(1,2));
        
        JLabel information = new JLabel();
        information.setLayout(new GridLayout(3,1));
        
        JLabel boutons = new JLabel();
        boutons.setLayout(new GridLayout(1,2));
        
        image = new ImagePanel("image-de-Défaite.jpg",1,false);
        titreMessage = new JLabel("VICOTORY");
        messageFin = new JLabel("ouais ouais ouais c'est gagné");
        
        quitter = new JButton("QUITTER");
        menu = new JButton("MENU");
        
        boutons.add(quitter);
        boutons.add(menu);
        
        information.add(titreMessage);
        information.add(messageFin);
        information.add(boutons);
        
        this.add(image);
        this.add(information);
        
        this.setVisible(false);
        
    }
}
