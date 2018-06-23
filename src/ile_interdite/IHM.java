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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author senno
 */
public class IHM extends Observe{
    private JFrame fenetreJeu;
    private VueGrille grille;
    private VueAventurier vueAventurier;
    private VueEquipe vueEquipe;
    private IHMDefausse ihmDefausse;
    private VueStatut vueStatut;
    
    private JFrame fenetreMenu;
    private ImagePanel imageMenu;
    private JLabel informations;
    private JLabel nbJoueur;
    private JComboBox choixNbJoueur;
    private String[] nombre = new String[3];
    private JLabel[] joueur = new JLabel[4];
    private JTextField[] champJoueur = new JTextField[4];
    private JLabel infoDifficulte;
    private JComboBox choixDeLaDifficulte;
    private String[] nomDifficulte = new String[4]; 
    private JLabel difficulte;
    private JLabel boutons;
    private JButton jouer;
    private JButton aide;
    private JButton quitterMenu;

    private JFrame fenetreFin;
    private JLabel messageFin;
    private JLabel titreMessage;
    private ImagePanel imageFin;
    private JButton quitterFin;
    private JButton menu;
    
    
    public IHM(Observateur observateur) {
        fenetreMenu = new JFrame("Ile interdite");
        fenetreMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetreMenu.setSize(1000,670);
        
        fenetreMenu.setLayout(new GridLayout(1,2));
        imageMenu = new ImagePanel("image-Menu.jpg",1.80);
        
        this.informations = new JLabel();
        this.informations.setLayout(new GridLayout(6,2));
        
        nbJoueur = new JLabel("Nombre de joueur :");
        this.informations.add(nbJoueur);
        
        for (int i = 0; i < 3 ; i++){
            nombre[i] = ""+(i+2);
        }
        choixNbJoueur = new JComboBox(nombre);
        choixNbJoueur.setSelectedIndex(0);
        this.informations.add(choixNbJoueur);
        
        for (int i = 0; i < 4; i++){
            joueur[i] = new JLabel("Joueur " + (i+1) + " :");
            this.informations.add(joueur[i]);
            champJoueur[i] = new JTextField();
            champJoueur[i].setColumns(30);
            this.informations.add(champJoueur[i]);
        }
        
        infoDifficulte = new JLabel();
        infoDifficulte.setLayout(new GridLayout(2,1));
        
        difficulte = new JLabel("DIfficulté");
        
        nomDifficulte[0]= "1 - Novice";
        nomDifficulte[1]= "2 - Normal";
        nomDifficulte[2]= "3 - Elite";
        nomDifficulte[3]= "4 - Légendaire";
        
        choixDeLaDifficulte = new JComboBox(nomDifficulte);
        choixDeLaDifficulte.setSelectedIndex(0);
        
        this.infoDifficulte.add(difficulte);
        this.infoDifficulte.add(choixDeLaDifficulte);
        
        this.informations.add(infoDifficulte);
        
        boutons = new JLabel();
        boutons.setLayout(new GridLayout(3,1));
        
        jouer = new JButton("JOUER");
        jouer.addActionListener(
            new ActionListener(){
            @Override
                public void actionPerformed(ActionEvent e) {
                    Message m = new Message("jouer", choixNbJoueur.getSelectedIndex()+2, choixDeLaDifficulte.getSelectedIndex()+1);
                    for (int i = 0; i < m.nbJoueur; i++){
                        m.nomJoueur[i] = champJoueur[i].getText();
                    }
                   notifierObservateur(m);
                }
            });
        
        aide = new JButton("AIDE");
        
        quitterMenu = new JButton("QUITTER");
        
        boutons.add(jouer);
        boutons.add(aide);
        boutons.add(quitterMenu);
        
        this.informations.add(boutons);
        
        fenetreMenu.add(imageMenu, BorderLayout.WEST);
        fenetreMenu.add(this.informations);
        
        fenetreMenu.setVisible(true);
        
        this.addObservateur(observateur);
    }
    
    public void jouer(Observateur observateur, Grille grilleaAfficher, ArrayList<Joueur> joueurs, MonteeDesEaux mde){
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
    
    public void afficherMenu(){
        fenetreMenu.setVisible(true);
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
        fenetreFin = new JFrame("Fin de Partie");
        fenetreFin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetreFin.setSize(1300, cas < 5 ? 390 : 460);
        
        switch (cas){
            case 5:
                messageFin = new JLabel("Félicitatons vous avez récuperé tous les trésors\net vous avez réussi à quitter l'île !!\nVotre mission est un succès.");
            break;
            
            case 4:
                messageFin = new JLabel("Le compteur de la montée des eaux a atteint son paroxysme,\nvous avez échoué.");
                break;
                
            case 3:
                messageFin = new JLabel("L'un des Trésor a sombré avec l'île,\nvous ne pouvez donc plus le récupérer.\nVotre mission est un échec.");
                break;
                
            case 2:
                messageFin = new JLabel("L'Héliport a sombré avec l'île ,\nvous ne pouvez plus vous échapper.\nVous êtes comdamner.");
                break;
                
            case 1:
                messageFin = new JLabel("L'un des aventurier s'est noyer, vous avez échoué.\nVotre mission est un échec.");
                break;
                
            default:
                break;
            
        }
        messageFin.setBackground(Color.pink);
        
        
        fenetreFin.setLayout(new GridLayout(1,2));

        JLabel information = new JLabel();
        information.setLayout(new GridLayout(3,1));

        JLabel boutons = new JLabel();
        boutons.setLayout(new GridLayout(1,2));
        
        imageFin = new ImagePanel(cas < 5 ? "image-de-Défaite.jpg" : "image-de-Victoire.jpg", cas < 5 ? 1.25 : 1.50);
        titreMessage = new JLabel(cas < 5 ? "DEFAITE" : "VICTOIRE");
        information.setBackground(Color.pink);
        fenetreFin.setBackground(Color.PINK);
        
        quitterFin = new JButton("QUITTER");
        
        menu = new JButton("MENU");
        menu.addActionListener(
            new ActionListener(){
            @Override
                public void actionPerformed(ActionEvent e) {
                    afficherMenu();
                    fenetreFin.setVisible(false);
                }
                
            });

        boutons.add(menu, BorderLayout.WEST);
        boutons.add(quitterFin, BorderLayout.EAST);

        information.add(titreMessage, BorderLayout.NORTH);
        information.add(messageFin, BorderLayout.CENTER);
        information.add(boutons, BorderLayout.SOUTH);

        
        fenetreFin.add(imageFin);
        fenetreFin.add(information);
        fenetreFin.setVisible(true);
    }
    
    
    
}
