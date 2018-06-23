/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import GraphicsUtil.ImagePanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author senno
 */
public class IHM extends Observe{
    private JFrame fenetreJeu;
    private IHMFin fenetreFin;
    private VueGrille grille;
    private VueAventurier vueAventurier;
    private VueEquipe vueEquipe;
    private IHMDefausse ihmDefausse;
    private VueStatut vueStatut;
    
    private JFrame fenetreMenu;
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
    private JButton jouer;

    public IHM(Observateur observateur) {
        
        
        fenetreMenu = new JFrame("Ile interdite");
        fenetreMenu.setSize(1000,670);
        
        fenetreMenu.setLayout(new GridLayout(1,2));
        image = new ImagePanel("image-Menu.jpg",1.80);
        
        this.joueurs = new JLabel();
        this.joueurs.setLayout(new GridLayout(6,2));
        
        nbJoueur = new JLabel("Nombre de joueur :");
        this.joueurs.add(nbJoueur);
        
        for (int i = 0; i < 3 ; i++){
            nombre[i] = ""+(i+2);
        }
        choixNbJoueur = new JComboBox(nombre);
        choixNbJoueur.setSelectedIndex(0);
        this.joueurs.add(choixNbJoueur);
        
        for (int i = 0; i < 4; i++){
            joueur[i] = new JLabel("Joueur " + (i+1) + " :");
            this.joueurs.add(joueur[i]);
            champJoueur[i] = new JTextField();
            champJoueur[i].setColumns(30);
            this.joueurs.add(champJoueur[i]);
        }
        
        jouer = new JButton("JOUER");
        jouer.addActionListener(
            new ActionListener(){
            @Override
                public void actionPerformed(ActionEvent e) {
                    Message m = new Message("jouer",choixNbJoueur.getSelectedIndex()+2);
                    for (int i = 0; i < m.nbJoueur; i++){
                        m.nomJoueur[i] = champJoueur[i].getText();
                    }
                   notifierObservateur(m);
                }
            });
        
        joueurs.add(jouer);
        
        fenetreMenu.add(image, BorderLayout.WEST);
        fenetreMenu.add(this.joueurs);
        
        fenetreMenu.setVisible(true);
        
        this.addObservateur(observateur);
    }
    
    public void Jouer(Observateur observateur, Grille grilleaAfficher, ArrayList<Joueur> joueurs, MonteeDesEaux mde){
        fenetreMenu.setVisible(false);
        
        fenetreJeu = new JFrame();
        fenetreJeu.setTitle("Jeu");
        fenetreJeu.setSize(1000, 900);
        fenetreJeu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetreJeu.setLayout(new BorderLayout());
        
        grille = new VueGrille(observateur, grilleaAfficher);
        fenetreJeu.add(grille, BorderLayout.CENTER);
        
        vueAventurier = new VueAventurier(observateur);
        vueAventurier.addObservateur(observateur);
        fenetreJeu.add(vueAventurier.asJPanel(), BorderLayout.SOUTH);
        
        vueEquipe = new VueEquipe(observateur, joueurs);
        fenetreJeu.add(vueEquipe.asJPanel(), BorderLayout.WEST);
        
        vueStatut = new VueStatut(mde);
        fenetreJeu.add(vueStatut, BorderLayout.EAST);
        fenetreJeu.setVisible(true);
    }

    public void setFenetreJeu(JFrame fenetreJeu) {
        this.fenetreJeu = fenetreJeu;
    }

    public void setGrille(VueGrille grille) {
        this.grille = grille;
    }

    public void setVueAventurier(VueAventurier vueAventurier) {
        this.vueAventurier = vueAventurier;
    }

    public JFrame getFenetreJeu() {
        return fenetreJeu;
    }

    public VueGrille getGrille() {
        return grille;
    }

    public VueAventurier getVueAventurier() {
        return vueAventurier;
    }
    
    
    public void afficherDefausse(Joueur joueur){
        this.ihmDefausse = new IHMDefausse(this.getObservateur(), joueur);
    }

    public VueEquipe getVueEquipe() {
        return vueEquipe;
    }

    public IHMDefausse getIhmDefausse() {
        return ihmDefausse;
    }
    
    public void fermerIhmDefausse(){
        this.getIhmDefausse().fermer();
    }

    public VueStatut getVueStatut() {
        return vueStatut;
    }
    
    public void finirPartie(int cas){
        fenetreJeu.setVisible(false);
        fenetreFin = new IHMFin(cas);
        fenetreFin.setVisible(true);
    }
    
    
    
}
