/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author senno
 */
public class Controleur implements Observateur{

    private Grille grille;
    private IHM ihm;
    private ArrayList<Aventurier> tousLesAventuriers;
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
    private MonteeDesEaux mde;
    private CarteTresor carteADonner;
    private ArrayList<CarteTirage> cartesADefausser;
    private int nbCarteADefausser;
    private int cartesRegardees = -1; //l'indice de l'aventurier dont les cartes sont regardees
    private boolean defausseEnFinDeTour = false;
    private Aventurier aventurierEnCoursDeDefausse;
    private HashMap<Special, Boolean> tresorsRecup;
    private CarteTirage carteAUtiliser;
    private Aventurier aventurierPossesseur;
    private ArrayList<Aventurier> aventurierPassagers;
    
    public Controleur() {
        this.listeDesJoueurs = new ArrayList<>();
        this.joueurs = new ArrayList<>();
        this.grille = new Grille();
        this.cartesADefausser = new ArrayList<>();
        this.tousLesAventuriers = new ArrayList<>();
        this.tresorsRecup = new HashMap<>();
        tresorsRecup.put(Special.calice, false);
        tresorsRecup.put(Special.cristal, false);
        tresorsRecup.put(Special.griffon, false);
        tresorsRecup.put(Special.pierre, false);
        
        
        
        
        //demo
        
        
        Ingenieur ingenieur = new Ingenieur(this.grille.getTuilebyName("La Porte de Bronze"));
        Navigateur navigateur = new Navigateur(this.grille.getTuilebyName("La Porte d’Or"));
        Explorateur explorateur = new Explorateur(this.grille.getTuilebyName("La Porte de Cuivre"));
        Messager messager = new Messager(this.grille.getTuilebyName("La Porte d’Argent"));
        Pilote pilote = new Pilote(this.grille.getTuilebyName("Heliport"));
        Plongeur plongeur = new Plongeur(this.grille.getTuilebyName("La Porte de Fer"));
        
        tousLesAventuriers.add(ingenieur);
        tousLesAventuriers.add(navigateur);
        tousLesAventuriers.add(explorateur);
        tousLesAventuriers.add(messager);
        tousLesAventuriers.add(pilote);
        tousLesAventuriers.add(plongeur);
        
        Collections.shuffle(tousLesAventuriers);
        
        initialiserPartie(4);
        
        // initialisation des pioches et des défausses de cartes
        
        piocheInondation = new ArrayList<>();
        piocheTirage = new ArrayList<>();
        defausseInondation = new ArrayList<>();
        defausseTirage = new ArrayList<>();
        
        // initialisations et ajout des cartes inondations dans la pioche
        
        piocheInondation.add(new CarteInondation("Le Pont des Abimes"));
        piocheInondation.add(new CarteInondation("La Porte de Bronze"));
        piocheInondation.add(new CarteInondation("La Caverne des Ombres"));
        piocheInondation.add(new CarteInondation("La Porte de Fer"));
        piocheInondation.add(new CarteInondation("La Porte d’Or"));
        piocheInondation.add(new CarteInondation("Les Falaises de l’Oubli"));
        piocheInondation.add(new CarteInondation("Le Palais de Corail"));
        piocheInondation.add(new CarteInondation("La Porte d’Argent"));
        piocheInondation.add(new CarteInondation("Les Dunes de l’Illusion"));
        piocheInondation.add(new CarteInondation("Heliport"));
        piocheInondation.add(new CarteInondation("La Porte de Cuivre"));
        piocheInondation.add(new CarteInondation("Le Jardin des Hurlements"));
        piocheInondation.add(new CarteInondation("La Foret Pourpre"));
        piocheInondation.add(new CarteInondation("Le Lagon Perdu"));
        piocheInondation.add(new CarteInondation("Le Marais Brumeux"));
        piocheInondation.add(new CarteInondation("Observatoire"));
        piocheInondation.add(new CarteInondation("Le Rocher Fantome"));
        piocheInondation.add(new CarteInondation("La Caverne du Brasier"));
        piocheInondation.add(new CarteInondation("Le Temple du Soleil"));
        piocheInondation.add(new CarteInondation("Le Temple de La Lune"));
        piocheInondation.add(new CarteInondation("Le Palais des Marees"));
        piocheInondation.add(new CarteInondation("Le Val du Crepuscule"));
        piocheInondation.add(new CarteInondation("La Tour du Guet"));
        piocheInondation.add(new CarteInondation("Le Jardin des Murmures"));
        
        Collections.shuffle(piocheInondation); // mélange la pioche inondation
        
        
        // initialisation et ajout des cartes de tirage dans la pioche
        
        for (int i = 0; i < 5 ; i++){
            piocheTirage.add(new CarteTresor("tresor",Special.calice));
            piocheTirage.add(new CarteTresor("tresor",Special.griffon));
            piocheTirage.add(new CarteTresor("tresor",Special.pierre));
            piocheTirage.add(new CarteTresor("tresor",Special.cristal));
        }
        
        for (int i = 0; i < 3; i++){
            piocheTirage.add(new CarteHelico("hélicoptère", Special.helico));
        }
        
        for (int i = 0; i < 2; i++){
            piocheTirage.add(new CarteSacSable("sac de sable",Special.sacSable));
        }
        
        Collections.shuffle(piocheTirage); // mélange la pioche Tirage
        
        for (Aventurier aventurier : listeDesJoueurs){
            for (int i = 0; i < 2; i++){
                CarteTirage carte = getCarteTirageHaut();
                aventurier.addCarteMain(carte);
                piocheTirage.remove(carte);
            }
        }
        
        for (int i = 0; i < 3; i++){
            piocheTirage.add(new CarteMDE("montée des eaux",Special.mde));
        }
        
        Collections.shuffle(piocheTirage); // mélange la pioche Tirage avec les carte mde ajouté
        
        mde = new MonteeDesEaux(1);
        //debug
        
        for (Aventurier aventurier : listeDesJoueurs) {
            joueurs.add(new Joueur(aventurier, "saucisse"));
        }
        

        //fin de la demo
        
        
        this.ihm = new IHM(this, grille, joueurs, mde);

        this.ihm.getGrille().updateAll(); //on update toutes les tuiles apres avoir fini le chargement
        this.joueurEnCours = nombreDeJoueurs -1;
        this.prochainJoueur();
        this.mde.setCompteur(1);
        
        
        //debug
        
        
        aventurierEnCours.addCarteMain(new CarteHelico("DEBUG", Special.helico));
        
//        for(CarteTirage carte : aventurierEnCours.getCartesMain()){
//            this.aventurierEnCours.removeCarteMain(carte);
//        }
//        
//        for (int i = 0; i < 4; i++) {
//            this.aventurierEnCours.addCarteMain(new CarteSacSable("DEBUG", Special.sacSable));
//            this.tousLesAventuriers.get(1).addCarteMain(new CarteTresor("DEBUG", Special.cristal));
//
//        }
//        
        
    }
    
    public void initialiserPartie(int nbJoueur){
        for (int i = 0; i < nbJoueur; i++){
            tousLesAventuriers.get(i).init();
            ajouterJoueur(tousLesAventuriers.get(i));
        }
    }
    
    public void resetAction(){
        this.actionEnCours = ActionEnCours.rien;
        this.ihm.getGrille().stopSurligner();
        this.ihm.getVueAventurier().resetBoutons();
        this.ihm.getGrille().updateAll();
        ihm.getVueAventurier().stopSurligner();
        ihm.getVueEquipe().surligner(false, listeDesJoueurs);
        ihm.getVueAventurier().desactiverBoutons(aventurierEnCours.getPointsAction());
        ihm.getVueAventurier().setPrendreRelique(aventurierEnCours.peutAcquerirTresor());
        ihm.getVueAventurier().enableAssecher(aventurierEnCours.isAssechementPossible());
        ihm.getVueAventurier().setPouvoirAActiver(aventurierEnCours.isPouvoirDispo());
    }
    
    public Aventurier prochainJoueur(){
        this.joueurEnCours ++;
        this.joueurEnCours %= this.nombreDeJoueurs;
        Aventurier a = this.listeDesJoueurs.get(joueurEnCours);
        a.setPointsAction(3);
        this.setAventurierEnCours(a);
        this.ihm.getVueAventurier().setPouvoirAActiver(a.pouvoirAActiver);
        if (a.isPouvoirAActiver()) {
            a.pouvoirDispo = true;
        }
        this.resetAction();
        this.afficherCartesAventurier(true);
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
        this.selectDeplacement(t);
        this.ihm.getVueAventurier().setPouvoirAActiver(aventurierEnCours.pouvoirDispo);
    }
    
    public void actionPioche(){
        this.resetAction();
        ArrayList<CarteTirage> cartesTirees = new ArrayList<>(); // collection des carte tirées   
        
        
        for (int i = 0; i < 2; i++){
            CarteTirage carte = getCarteTirageHaut();
            if (carte.getType() == Special.mde){   // si la carte est une carte MDE
                remettreCarteInondationEnPioche();
                mde.incrementeCompteur();
                this.defausseTirage.add(carte);         
            }
            else{
                cartesTirees.add(carte);  // si la carte n'est pas une MDE on ajoute la carte dans la collection des carte tirées
            }
            this.piocheTirage.remove(carte);    
        }
        aventurierEnCours.piocheCartes(cartesTirees);   // ajout des cartes tirées dans la main du joueur;
        
        if (aventurierEnCours.isMainExcede()){
            this.cartesADefausser = new ArrayList<>();
            this.actionEnCours = ActionEnCours.defausser;
            aventurierEnCoursDeDefausse = aventurierEnCours;
            this.nbCarteADefausser = this.aventurierEnCours.getCartesMain().size() - 5;
            defausseEnFinDeTour = true;
            for(Joueur j : this.joueurs){ // c'est realtivement lent de faire ca comme ca mais y'a que 4 iterations max donc ca va
                if (j.getPersonnage() == aventurierEnCours) {
//                    System.out.println("ping");
                    this.ihm.afficherDefausse(j);
                    break;
                }
            }
        }else{
        this.piocherCartesInondation();
        }
        
    
        
    }
    
    public void piocherCartesInondation(){
        for (int i = 1 ; i <= mde.getNbCarteInodation(); i++){
            
            CarteInondation carte = carteInondationHaut();
            Tuile tuile = grille.getTuilebyName(carte.getNom());
            
            if (tuile.getEtat() == EtatsTuiles.seche){
                tuile.setEtat(EtatsTuiles.inondee);
                this.defausseInondation.add(carte);
            }
            else{
                tuile.setEtat(EtatsTuiles.sombree);
                for (Aventurier aventurier : tuile.getAventuriers()){
                    
                }
            }
            this.piocheInondation.remove(carte);
        }
        this.verifieDefaite();

    }
    
    public void remettreCarteInondationEnPioche(){
        Collections.shuffle(defausseInondation);
        for (CarteInondation carteInondation: defausseInondation){
            this.piocheInondation.add(carteInondation);
        }
        defausseInondation = new ArrayList<>();
    }
    
    public void reinitialisePiocheTirage(){
        System.out.println(defausseTirage);
        Collections.shuffle(piocheTirage);
        for (CarteTirage carte : defausseTirage){
            this.piocheTirage.add(carte);
        }
        defausseTirage = new ArrayList<>();
    }
    
    public CarteTirage getCarteTirageHaut(){
        if (this.piocheTirage.isEmpty()){
            reinitialisePiocheTirage();
        }
        return this.piocheTirage.get(this.piocheTirage.size()-1);
    }
    
    public CarteInondation carteInondationHaut(){
        if (this.piocheInondation.isEmpty()){
            remettreCarteInondationEnPioche();
        }
        return this.piocheInondation.get(this.piocheInondation.size()-1);
    }
    
    public void finishDonneCarte(Aventurier a){
        if (aventurierEnCours.isDonnationPossible(a)){
            aventurierEnCours.donneCarte(this.carteADonner, a);
        }
        this.resetAction();
        
        if (a.isMainExcede()){
            aventurierEnCoursDeDefausse = a;
            this.cartesADefausser = new ArrayList<>();
            this.actionEnCours = ActionEnCours.defausser;
            this.nbCarteADefausser = a.getCartesMain().size() - 5;
            for(Joueur j : this.joueurs){ // c'est realtivement lent de faire ca comme ca mais y'a que 4 iterations max donc ca va
                if (j.getPersonnage() == a) {
//                    System.out.println("ping");
                    this.ihm.afficherDefausse(j);
                    break;
                }
            }
        }
        ihm.getVueAventurier().afficherCartes(listeDesJoueurs.get(cartesRegardees));
    }
    
    public void actionDonneCarte(){
        resetAction();
        this.actionEnCours = ActionEnCours.donner;
        ihm.getVueAventurier().setDonner(true);
        ihm.getVueAventurier().afficherCartes(aventurierEnCours);
        this.cartesRegardees = joueurEnCours;
        ihm.getVueAventurier().surlignerCartesDonnables(true);
    }
    
    public void selectCarteADonner(CarteTresor carte){
        this.carteADonner = carte;
        ihm.getVueAventurier().stopSurligner();
        ihm.getVueAventurier().surlignerCarte(carteADonner);
        ihm.getVueEquipe().surligner(true, aventurierEnCours.getAventurierDonne(listeDesJoueurs));
    }
    
    public void validerDefausse(){
        if (cartesADefausser.size() == nbCarteADefausser) { //normalement on peut pas clic si c'est pas vrai mais on sait jamais
            System.out.println("ping");
            for (CarteTirage carte : cartesADefausser) {
                System.out.println("ping bis");
                aventurierEnCoursDeDefausse.removeCarteMain(carte);
                defausseTirage.add(carte);
            }
        }
        this.resetAction();
        this.ihm.fermerIhmDefausse();
        if (defausseEnFinDeTour) {
            this.piocherCartesInondation();
            defausseEnFinDeTour = false;
        }
    }
    
    
    public void selectCarteDefausse(CarteTirage carte){
        if (cartesADefausser.remove(carte)) {
            ihm.getIhmDefausse().stopSurlignerCarte(carte);
        }else{
            ihm.getIhmDefausse().surlignerCarte(carte);
            cartesADefausser.add(carte);
        }
        ihm.getIhmDefausse().boutonActif(cartesADefausser.size() == nbCarteADefausser);
    }
    
    public void afficherCartesAventurier(boolean suivant){ // suivant = true: l'aventurier suivant, false: l'aventurier precedent
        this.cartesRegardees += (suivant? 1 : -1);
        if (cartesRegardees > nombreDeJoueurs -1) {
            cartesRegardees = 0;
        }
        if (cartesRegardees <0) {
            cartesRegardees = nombreDeJoueurs -1;
        }
//        System.out.println("cartes regardees : " + cartesRegardees);
        ihm.getVueAventurier().afficherCartes(listeDesJoueurs.get(cartesRegardees));
    }
    
    public void utiliseCarte(CarteTirage carteTirage){ 
        switch(carteTirage.getType()){
            case sacSable:
                this.resetAction();
                this.actionEnCours = ActionEnCours.sacDeSable;
                carteAUtiliser = carteTirage;
                aventurierPossesseur = listeDesJoueurs.get(cartesRegardees);
                HashMap<Tuile, Integer> tuilesinondees = this.grille.getTuilesInondees();
                ihm.getGrille().surligner(tuilesinondees);
                this.aventurierEnCours.setSaveAP(tuilesinondees);
                ihm.getVueAventurier().surlignerCarte(carteTirage);
                break;
            case helico:
                if (toutLesTresorsPosede() && tousSurHeliport()){
//                    terminerPartie();

                }
                
                if (toutLesTresorsPosede() & tousSurHeliport()) {
                    terminerPartie(ConditionsFin.victoire);
                }
                else{
                
                    this.resetAction();
                    this.actionEnCours = ActionEnCours.helicoptere;
                    carteAUtiliser = carteTirage;
                    aventurierPossesseur = aventurierEnCours;
                    this.aventurierPassagers = new ArrayList<>();
                    ihm.getVueEquipe().surligner(true, listeDesJoueurs);
                    if (toutLesTresorsPosede() && tousSurHeliport()){
    //                    terminerPartie();
                    }
                    else{
                        ArrayList<Aventurier> aventuriers = new ArrayList<>();
                        for (Aventurier aventurier : listeDesJoueurs){
                            if (aventurier != this.aventurierEnCours){
                                aventuriers.add(aventurier);
                            }
                        }
                        ihm.getVueEquipe().surligner(true,aventuriers);
                    }
                }
                break;
            default:
                break;
        }
    }
    
    public void selectAventurierHeli(Joueur j){ //FIXME: surligner les bons equipers est casse.
        
        if (aventurierPassagers.contains(j.getPersonnage())) {
            //on a deja select le perso
            //le retirer
            aventurierPassagers.remove(j.getPersonnage());
            //stop le surligner
            ihm.getVueEquipe().selectionner(j, false);
            //si y'a plus de perso surligner tout le monde
            if (aventurierPassagers.isEmpty()) {
                ihm.getVueEquipe().surligner(true, listeDesJoueurs);
                ihm.getGrille().stopSurligner();
            }
        }else{
            //le perso n'est pas select
            //test si on a le droit de le select etant donne la selection actuelle et le rajouter si possible
            if (!aventurierPassagers.isEmpty()) {
                // tester si on a le droit de select
                if (aventurierPassagers.get(0).getNeighbors().contains(j.getPersonnage())) {
                    //on a le droit
                    aventurierPassagers.add(j.getPersonnage());
                    ihm.getVueEquipe().selectionner(j, true);
                }
            }else{
                //premiere selection: ajouter l'aventurier a la liste
                aventurierPassagers.add(j.getPersonnage());
                ihm.getVueEquipe().surligner(false, listeDesJoueurs);
                ihm.getVueEquipe().surligner(true, j.getPersonnage().getNeighbors());
                ihm.getVueEquipe().selectionner(j, true);
                ihm.getGrille().surlignerAll();
            }
        }
        
    }
    
    public void selectTuileHeli(Tuile t){
        if (t.getEtat() != EtatsTuiles.sombree) {
            HashMap<Tuile, Integer> dp = new HashMap<>();
            dp.put(t, 0);
            for (Aventurier a : aventurierPassagers) {
                a.setSaveDP(dp);
                a.seDeplacer(t);
            }
            resetAction();
            aventurierPossesseur.utiliseCarte(carteAUtiliser);
            ihm.getVueAventurier().afficherCartes(listeDesJoueurs.get(cartesRegardees));
            //TODO retirer la tuile de la fenetre defausser
            ihm.getGrille().updateAll();
        }
    }
    
    public void selectSacDeSable(Tuile t){
        aventurierPossesseur.utiliseCarte(carteAUtiliser);
        t.setEtat(EtatsTuiles.seche);
        ihm.getVueAventurier().afficherCartes(listeDesJoueurs.get(cartesRegardees));
                //TODO: retirer la carte de la fenetre defausser.
        this.resetAction();
    }
    
    
    public void actionPrendre(){
        Special type = aventurierEnCours.getTuileOccupee().getSpecial();
        if (!tresorsRecup.get(type)) {
            if (aventurierEnCours.obtenirTresor()) {
                tresorsRecup.put(type, true);
                ihm.getVueStatut().getVueTresor().acquerirTrophee(type);
                this.resetAction();
            }
        }
    }
    
    public boolean toutLesTresorsPosede(){
        int nbTresorsObtenus = 0;
        for (Map.Entry<Special,Boolean> tresor : tresorsRecup.entrySet()){
            if (tresor.getValue()){
                nbTresorsObtenus ++;
            }
        }
        return nbTresorsObtenus == 4;
    }

    public boolean tousSurHeliport(){
        int nbJoueur = 0;
        for (Aventurier aventurier : listeDesJoueurs){
            if (aventurier.getTuileOccupee().getSpecial() == Special.heliport){
                nbJoueur ++;
            }
        }
        return this.nombreDeJoueurs == nbJoueur;
    }
    
    public void verifieDefaite(){
        ConditionsFin indicateurDefaite = ConditionsFin.aucun;
        for (Aventurier aventurier : listeDesJoueurs){
            Tuile tuile = aventurier.getTuileOccupee();
            if (tuile.getEtat() == EtatsTuiles.sombree){
                HashMap<Tuile,Integer> tuiles = new HashMap<>();
                tuiles = aventurier.getEchappePossible(grille);
                if (tuiles.size() > 0){
                    this.actionEnCours = ActionEnCours.bouger;
                    ihm.getGrille().surligner(tuiles);
                    aventurier.setSaveDP(tuiles);
                }
                else {
                    indicateurDefaite = ConditionsFin.noyade;
                }
            }
        }
        if (grille.getTuilebyName("Heliport").getEtat() == EtatsTuiles.sombree){
            indicateurDefaite = ConditionsFin.heliport;
        }
        for (Map.Entry<Special,Boolean> tresor : tresorsRecup.entrySet()){
            if (tresor.getValue() == false){
               int nbTuilesTesorInondees = 0;
               for (Tuile tuile : grille.getTuiles()){
                   if (tuile != null && tuile.getEtat() == EtatsTuiles.sombree & tuile.getSpecial() == tresor.getKey()){
                       nbTuilesTesorInondees ++;
                   }
               }
               if (nbTuilesTesorInondees == 2){
                   indicateurDefaite = ConditionsFin.tresor;
               }
            }
                
        }
        if (this.mde.getCompteur() > 9){
            indicateurDefaite = ConditionsFin.MDE;
        }
        if (indicateurDefaite != ConditionsFin.aucun ){
            terminerPartie(indicateurDefaite);
        }
        else{
            this.prochainJoueur();
        }
    }
    
    public void terminerPartie(ConditionsFin condition){
        switch (condition){
            case noyade:
                System.out.println("un aventurier s'est noyer vous avez perdu");
                break;
            case heliport:
                System.out.println("L'Héliport à sombrée vous avez perdu");
                break;
            case tresor:
                System.out.println("L'un des Trésor est devenu irrécupérable vous avez perdu");
                break;
            case MDE:
                System.out.println("Le compteur de la montée des Eaux à atteint son paroxysme vous avez perdu");
                break;
            case victoire:
                System.out.println("ouais ouais ouais c'est gagné");
                break;
            default:
                break;
        }
        ihm.finirPartie();
    }
    
    
    @Override
    public void traiterMessage(Message msg) {
//        System.out.println("message: " + msg.contenu);
//        System.out.println(this.aventurierEnCours.getPointsAction());
        if ("fenetre de fin fermee".equals(msg.contenu)) {
            ihm.getFenetreJeu().dispose(); // la fenetre existait toujours juqu'ici, juste invisible.
        }
        
        if (actionEnCours !=ActionEnCours.defausser) {
            if("fin de tour".equals(msg.contenu)){
                this.actionPioche();
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
            if ("stop pouvoir".equals(msg.contenu)) {
                this.resetAction();
            }
            if ("donner".equals(msg.contenu)) {
                this.actionDonneCarte();
            }
            if ("stop donner".equals(msg.contenu)) {
                this.resetAction();
            }
            if ("prendre".equals(msg.contenu)) {
                this.actionPrendre();
            }
        }
        
        if ("cartes next".equals(msg.contenu)) {
            this.afficherCartesAventurier(true);
        }
        if ("cartes prev".equals(msg.contenu)) {
            this.afficherCartesAventurier(false);
        }
        
        if ("defausse valider".equals(msg.contenu)) {
            this.validerDefausse();
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
                    case sacDeSable:
                        this.selectSacDeSable(msgDT.tuileDOrigine);
                        break;
                    case helicoptere:
                        this.selectTuileHeli(msgDT.tuileDOrigine);
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
                        break;
                    case defausser:
//                        System.out.println("ping");
                        this.selectCarteDefausse(msgDC.carte);
                        break;
                    case sacDeSable:
                    case helicoptere:
                        this.resetAction();
                        break;
                    default:
                        utiliseCarte(msgDC.carte);
                        break;
                }
            }
        }
        
        if (msg instanceof MessageDeJoueur) { //le polymorphisme c'est VRAIMENT chouette
            MessageDeJoueur msgJ = (MessageDeJoueur) msg;
            if ("clic".equals(msgJ.contenu)) {
                switch(actionEnCours){
                    case donner:
                        this.finishDonneCarte(msgJ.joueur.getPersonnage());
                        break;
                    case helicoptere:
                        this.selectAventurierHeli(msgJ.joueur);
                    default:
                        break;
                }
            }
        }
    }
}
