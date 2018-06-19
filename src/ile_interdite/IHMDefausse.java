/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JButton;
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
    private JButton boutonConfirmer;
    private int nombreDeCartesADefausser;
    private HashMap<CarteTirage, VueCarte> cartes;
    

    public IHMDefausse(Observateur obs, Joueur joueur) {
        this.addObservateur(obs);
        cartes = new HashMap<>();
        fenetre = new JFrame();
        fenetre.setTitle("Defausser des cartes");
        fenetre.setSize(400, 200);
        fenetre.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        fenetre.setLayout(new BorderLayout());
        panelTop = new JPanel();
        panelCenter = new JPanel(new GridLayout(1, 0));
        panelBot = new JPanel();
        nombreDeCartesADefausser = joueur.getPersonnage().getCartesMain().size() - 5;
        panelTop.add(new JLabel(joueur.getNom()+ " doit se defausser de " + nombreDeCartesADefausser + " cartes."));
        panelTop.add(new JLabel("Selectionnez les cartes a defausser."));
        
        for (CarteTirage carte : joueur.getPersonnage().getCartesMain()) {
            VueCarte vuecarte = new VueCarte(obs, carte);
            panelCenter.add(vuecarte.asJPanel());
            cartes.put(carte, vuecarte);
        }
        
        boutonConfirmer = new JButton("Valider");
        boutonConfirmer.setEnabled(false); // le bouton n'est actif que apres assez de cartes
        boutonConfirmer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifierObservateur(new Message("defausse valider"));
            }
        });
        panelBot.add(boutonConfirmer);
        
        
        fenetre.add(panelTop, BorderLayout.NORTH);
        fenetre.add(panelCenter, BorderLayout.CENTER);
        fenetre.add(panelBot, BorderLayout.SOUTH);
        
        fenetre.setVisible(true);
        
        
    }
    
    public void surlignerCarte(CarteTirage carte){
        cartes.get(carte).surligner(true);
    }
    public void stopSurlignerCarte(CarteTirage carte){
        cartes.get(carte).surligner(false);
    }
    public void boutonActif(boolean actif){
        boutonConfirmer.setEnabled(actif);
    }
    
    
    public void fermer(){
        this.fenetre.setVisible(false);
        this.fenetre.dispose();
    }
    
}
