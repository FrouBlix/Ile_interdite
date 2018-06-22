/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import GraphicsUtil.ImagePanel;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Félix Reboulet
 */
public class IHMFin extends Observe{
    private JFrame fenetre;
    private JLabel fetreFin;
    private JLabel messageFin;
    private JLabel titreMessage;
    private ImagePanel image;
    private JButton quitter;
    private JButton menu;
    

    public IHMFin(Observateur obs){
        fenetre = new JFrame("fin de partie");
        
        fenetre.setLayout(new GridLayout(1,2));
        
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
        
        fenetre.add(image);
        fenetre.add(information);
        fenetre.setSize(300, 400);
        
        fenetre.setVisible(false);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        fenetre.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                notifierObservateur(new Message("fenetre de fin fermee"));
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
        
        
        
    }

    public JFrame asJFrame() {
        return fenetre;
    }
}
