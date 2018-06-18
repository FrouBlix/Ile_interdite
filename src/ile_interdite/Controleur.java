/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author senno
 */
public class Controleur implements Observateur{

    private Grille grille;
    private IHM ihm;
    private IHM ihmSupression;
    private ArrayList<Aventurier> listeDesJoueurs;
    private ArrayList<Joueur> joueurs;
    private int joueurEnCours =0;
    private Aventurier aventurierEnCours;
    private int nombreDeJoueurs=0;
    private ActionEnCours actionEnCours = ActionEnCours.rien;
    private ArrayList<CarteTirage> piocheTirage;
    private ArrayList<CarteInondation> piocheInondation;
    private ArrayList<CarteTirage> defausseTirage;
    private ArrayList<CarteInondation> defausseInondation;
    private CarteTresor carteADonner;
    
    public Controleur() {
        this.listeDesJoueurs = new ArrayList<>();
        this.joueurs = new ArrayList<>();
        this.grille = new Grille();
        
        //demo
        Explorateur joueur3 = new Explorateur(this.grille.getTuilebyName("Observatoire"));
        Ingenieur joueur4 = new Ingenieur(this.grille.getTuilebyName("Le Palais des Marees"));
        Pilote joueur5 = new Pilote(this.grille.getTuilebyName("Heliport"));
        Plongeur plongeur = new Plongeur(this.grille.getTuilebyName("Le Pont des Abimes"));
        this.ajouterJoueur(plongeur);
        this.ajouterJoueur(joueur3);
        this.ajouterJoueur(joueur4);
        this.ajouterJoueur(joueur5);
        
        this.grille.getTuilebyName("La Porte de Bronze").setEtat(EtatsTuiles.inondee);
        this.grille.getTuilebyName("Les Dunes de l’Illusion").setEtat(EtatsTuiles.sombree);
        this.grille.getTuilebyName("Le Lagon Perdu").setEtat(EtatsTuiles.inondee);
        this.grille.getTuilebyName("Le Marais Brumeux").setEtat(EtatsTuiles.sombree);
        this.grille.getTuilebyName("Observatoire").setEtat(EtatsTuiles.inondee);
        this.grille.getTuilebyName("Le Rocher Fantome").setEtat(EtatsTuiles.sombree);
        this.grille.getTuilebyName("La Caverne du Brasier").setEtat(EtatsTuiles.inondee);
        this.grille.getTuilebyName("Le Temple de La Lune").setEtat(EtatsTuiles.sombree);
        this.grille.getTuilebyName("Le Jardin des Murmures").setEtat(EtatsTuiles.inondee);
        
        //debug
        
        for (Aventurier aventurier : listeDesJoueurs) {
            joueurs.add(new Joueur(aventurier, ""));
        }
        
        
        
        
        

        //fin de la demo
        
        
        
        this.ihm = new IHM(this, grille, joueurs);

        this.ihm.getGrille().updateAll(); //on update toutes les tuiles apres avoir fini le chargement
        this.joueurEnCours = nombreDeJoueurs -1;
        this.prochainJoueur();
        
        
        
    }
    
    public void resetAction(){
        this.actionEnCours = ActionEnCours.rien;
        this.ihm.getGrille().stopSurligner();
        this.ihm.getVueAventurier().resetBoutons();
        this.ihm.getGrille().updateAll();
        //ihm.stopsurlignercartes
        //ihm.stopsurlignerAventuriers
    }
    
    public Aventurier prochainJoueur(){
        this.joueurEnCours ++;
        this.joueurEnCours %= this.nombreDeJoueurs;
        Aventurier a = this.listeDesJoueurs.get(joueurEnCours);
        a.setPointsAction(3);
        setAventurierEnCours(a);
        this.ihm.getVueAventurier().setPouvoirAActiver(a.pouvoirAActiver);
        a.pouvoirDispo = true;
        this.resetAction();
        return a;
    }
    
    public void ajouterJoueur(Aventurier a){
        this.nombreDeJoueurs ++;
        listeDesJoueurs.add(a);
    }
    
    public void actionDeplacer(){
        this.resetAction();
        this.ihm.getVueAventurier().setBouger(true);
//        System.out.println(this.aventurierEnCours);
        this.actionEnCours = ActionEnCours.bouger;
//        System.out.println(this.aventurierEnCours.getClass().getName());
        this.ihm.getGrille().surligner(aventurierEnCours.getDeplacementPossible(grille));
    }
    
    public void selectDeplacement(Tuile t){
        this.aventurierEnCours.seDeplacer(t);
        this.resetAction();
    }
    

    
    public void actionAssecher(){
        this.resetAction();
        this.ihm.getVueAventurier().setAssecher(true);
        this.actionEnCours = ActionEnCours.assecher;
        this.ihm.getGrille().surligner(aventurierEnCours.getAssechementPossible(grille));
    }

    public Aventurier getAventurierEnCours() {
        return aventurierEnCours;
    }

    public void setAventurierEnCours(Aventurier aventurierEnCours) {
        this.aventurierEnCours = aventurierEnCours;
    }
    
    
    public void selectAssechement(Tuile t){
        if (t!=null) {
            this.aventurierEnCours.assecher(t);
        }
        this.resetAction();
    }
    
    
    public void actionPouvoir(){
        this.resetAction();
        this.ihm.getVueAventurier().setPouvoir(true);
        this.actionEnCours = ActionEnCours.pouvoir;
        this.ihm.getGrille().surligner(aventurierEnCours.getDeplacementPouvoir(grille));
    }
    
    public void selectPouvoir(Tuile t){
        aventurierEnCours.seDeplacer(t);
        this.resetAction();
        this.ihm.getVueAventurier().setPouvoirAActiver(aventurierEnCours.pouvoirDispo);
    }
    
    public void actionPioche(){
        ArrayList<CarteTirage> cartesTirees = new ArrayList<>();
        for (int i = 0; i < 2; i++){
            CarteTirage carte = getCarteTirageHaut();
            if (carte.getNom() == "MDE"){
                remettreCarteInondationEnPioche();
            }
            else{
                cartesTirees.add(carte);
            }
            this.piocheTirage.remove(carte);
        }
        aventurierEnCours.piocheCartes(cartesTirees);
        
        if (aventurierEnCours.mainExcede){
            // TODO : afficher ecran pour afficher une carte puis retrait de celle-ci
            // TODO : IHM de supression
        }
    }
    
    public void remettreCarteInondationEnPioche(){
        Collections.shuffle(defausseInondation);
        for (CarteInondation carteInondation: defausseInondation){
            this.piocheInondation.add(carteInondation);
        }
    }
    
    public CarteTirage getCarteTirageHaut(){
        return this.piocheTirage.get(this.piocheTirage.size()-1);
    }
    
    public void finishDonneCarte(CarteTirage carte, Aventurier a){
        aventurierEnCours.donneCarte(carte, a);
    }
    
    public void actionDonneCarte(){
        //on veut qu'il select une carte, puis un aventurier
        resetAction();
        this.actionEnCours = ActionEnCours.donner;
        ihm.getVueAventurier().setDonner(true);
        //TODO: ihm.surlignercartes
    }
    
    public void selectCarteADonner(CarteTresor carte){
        this.carteADonner = carte;
        // TODO: ihm.surligneraventuriers(joueurencours.getcase().getaventuriers());
    }
    
    
    @Override
    public void traiterMessage(Message msg) {
//        System.out.println("message: " + msg.contenu);
//        System.out.println(this.aventurierEnCours.getPointsAction());
        if("fin de tour".equals(msg.contenu)){
            this.actionPioche();
            this.prochainJoueur();
        }
        if("bouger".equals(msg.contenu)){
            this.actionDeplacer();
        }
        if ("stop bouger".equals(msg.contenu)) {
            this.selectDeplacement(this.aventurierEnCours.getTuileOccupee()); //on annule le deplacement. pour quitter proprement, on dit juste qu'on bouge en direction du meme endroit
        }
        if ("assecher".equals(msg.contenu)) {
            this.actionAssecher();
        }
        if ("stop assecher".equals(msg.contenu)) {
            this.selectAssechement(null);
        }
        if ("pouvoir".equals(msg.contenu)) {
            this.actionPouvoir();
        }
        if ("donner".equals(msg.contenu)) {
            this.actionDonneCarte();
        }
        
        
        if (msg instanceof MessageDeTuile) { // le polymorphisme c'est chouette
            MessageDeTuile msgDT = (MessageDeTuile) msg;
            if ("clic".equals(msg.contenu)) {
                switch(actionEnCours){
                    case bouger: this.selectDeplacement(msgDT.tuileDOrigine);
                        break;
                    case assecher: this.selectAssechement(msgDT.tuileDOrigine);
                        break;
                    case pouvoir: this.selectPouvoir(msgDT.tuileDOrigine);
                        break;
                    default: break;
                }
            }
        }
        
        if (msg instanceof MessageCarteTirage) { // j'ai deja dit que le polymorphisme c'etait chouette?
            MessageCarteTirage msgDC = (MessageCarteTirage) msg;
            if ("clic".equals(msgDC.contenu)) {
                switch(actionEnCours){
                    case donner:
                        if (msgDC.carte instanceof CarteTresor) {
                            this.selectCarteADonner((CarteTresor)msgDC.carte); 
                        }
                        break;//on ne fait rien si l'utilisateur clique sur une carte speciale donc c'est bon
                        default:
                            break; // TODO: activer cartes speciales
                }
            }
        }

    }
    
    
    
    
}
