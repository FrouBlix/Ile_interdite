/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author reboulef
 */
public abstract class Aventurier extends Observe{
    private int pointsAction;       //Points d'action de l'aventurier
    private Tuile tuileOccupee;      //Tuile occupée par l'aventurier
    private HashMap<Tuile,Integer> saveDP; //deplacements possibles
    private HashMap<Tuile, Integer> saveAP; // assechements possibles
    private Pion pion;
    private ArrayList<CarteTirage> cartesMain;
    private ArrayList<Aventurier> saveDonationsP;
    private Tuile spawn;
    
    public boolean mainExcede;
    public boolean pouvoirAActiver;
    public boolean pouvoirDispo = false;

    public Aventurier(Tuile spawn){
        cartesMain = new ArrayList<>();
        this.spawn = spawn;
        this.saveDP = new HashMap<>();
        this.pion = new Pion();
        this.setPointsAction(3);
        this.pouvoirAActiver = false;
        this.mainExcede = false;
    }
    
    public void init(){
        // Definie la tuile de depart
        this.setTuileOccupee(spawn);

    }
    
    
    public Pion getPion(){
        // donne le pion de l'aventurier
        return this.pion;
    }
    
    public int getPointsAction() {
        // donne le nombre de PA disponible
        return pointsAction;
    }
    
    public Tuile getTuileOccupee(){
        // donne la tuile occuper par l'aventurier
        return this.tuileOccupee;
    }



    public void setPointsAction(int pointsAction) {
        // definie le nombre de PA de l'aventurier
        this.pointsAction = pointsAction;
    }

    public void setTuileOccupee(Tuile tuileOccupee) {
        // redefinie la tuile definie par l'aventurier
        if (this.tuileOccupee != null) {
            this.tuileOccupee.removeAventurier(this);

        }
        this.tuileOccupee = tuileOccupee;
        this.tuileOccupee.addAventurier(this);

    }

    public HashMap<Tuile, Integer> getSaveAP(){
        // donne les tuiles qui peuvent etres inondée
        return this.saveAP;
    }

    public void setSaveAP(HashMap<Tuile, Integer> saveAP) {
        // definie les tuiles qui peuvent etre inondée
        this.saveAP = saveAP;
    }

    
    
    public HashMap<Tuile, Integer> getSaveDP() {
        // donne les tuiles sur lesquelles l'aventurier peut se deplacer
        return saveDP;
    }

    public void setSaveDP(HashMap<Tuile, Integer> saveDP) {
        // definie les tuiles sur lesquelle l'aventurier peut se deplacer
        this.saveDP = saveDP;
    }
    
    public HashMap<Tuile, Integer> getAssechementPossible(Grille grille){
        // verifie les case pour l'assechement
        this.saveAP = new HashMap<>();
        if (this.getPointsAction() > 0) {
            Coordonnees coords = this.getTuileOccupee().getCoordonnees();
            this.testTuileAss(this.getTuileOccupee());
            this.testTuileAss(grille.getTuile(coords.getPlus(0, -1)));
            this.testTuileAss(grille.getTuile(coords.getPlus(-1, 0)));
            this.testTuileAss(grille.getTuile(coords.getPlus(0, 1)));
            this.testTuileAss(grille.getTuile(coords.getPlus(1, 0)));
        }
        return this.saveAP;
    }
    
    public boolean isAssechementPossible(){
        // verifie le nombre de PA du joueur pour l'assechement
        return this.getPointsAction() > 0;
    }
    
    
    public void testTuileAss(Tuile tuile){
        // verifie que la tuille n'est pas seche et l'ajoute a saveAP
        if (tuile != null && tuile.getEtat() == EtatsTuiles.inondee) {
            this.saveAP.put(tuile, 1);
        }
    }
    
    public boolean assecher(Tuile t){
        //asseche la tuile en verifiant que l'aventurier a les PA et que la tuile peu etre assecher
        if (this.getPointsAction() >0 && this.saveAP.containsKey(t)) {
            this.pointsAction --;
            t.setEtat(EtatsTuiles.seche);// pas besoin de tester plus: si la tuile est dans saveAP, elle est inondee.
            this.saveAP = new HashMap<>();
            return true;
        }
        return false;
    }
    
    
    public HashMap<Tuile, Integer> getDeplacementPossible(Grille grille){ 
        // cette methode renvoie un Hashmap qui pour chaque tuile renvoie le cout de s'y deplacer.
        this.setSaveDP(new HashMap<>());
        Tuile tdd  = this.getTuileOccupee();//tuile de depart
        this.saveDP.put(tdd,0); //rester au meme endroit ne coute rien
        if(this.getPointsAction() >0){
                    this.propager(grille, tdd, 1);
        }
        return this.saveDP;
    }
    
    
    public void propager(Grille grille, Tuile tuileDeDepart, int cout){ 
        // cette methode permet de tester les tuiles autour d'une tuile donnée et de lui donner un cout
        Coordonnees coords = tuileDeDepart.getCoordonnees();
        ArrayList<Tuile> al = new ArrayList<>(); // cet arraylist contient les tuiles nouvellement considerees
        Tuile tuile = grille.getTuile(coords.getPlus(-1, 0));
        testTuile(tuile, al, cout);
        
        tuile = grille.getTuile(coords.getPlus(0, -1));
        testTuile(tuile, al, cout);

        tuile = grille.getTuile(coords.getPlus(1, 0));
        testTuile(tuile, al, cout);

        tuile = grille.getTuile(coords.getPlus(0, 1));
        testTuile(tuile, al, cout);
        
        if (cout < this.getPointsAction()) {
            for(Tuile tdd: al){
                this.propager(grille, tdd, cout+1); //Recursion powers Activate!
            }      
        }
    }
    
    public void testTuile(Tuile tuile, ArrayList<Tuile> al, int cout){
        // cette methode test si une tuile est accesible et qu'un cout inferieur ne lui a pas ete donnée
        
        
//        System.out.println(tuile.toString()); 
//        System.out.println(this.saveDP);
        
        
        if (tuile != null && tuile.getEtat() != EtatsTuiles.sombree) {
            
            int oldCout;
            if(this.saveDP.get(tuile) == null){
                oldCout = 4; // 4 sera toujours plus grand que cout.
            }else{
                oldCout = this.saveDP.get(tuile);
                System.out.println(this.saveDP.get(tuile));
            }
//            System.out.println(oldCout);
            if (oldCout > cout) {
                this.saveDP.put(tuile, cout);
//                System.out.println("=====ACCEPT=====");
                al.add(tuile);
            }
        }

    }
    
    public ArrayList<Aventurier> getAventurierDonne(ArrayList<Aventurier> aventuriers){
        // Donne les joueur a qui on peu donner une carte
        this.saveDonationsP = new ArrayList<>();
        for (Aventurier aventurier : aventuriers){
            if (isDonnationPossible(aventurier) && (this != aventurier)){
                saveDonationsP.add(aventurier);
            }
        }
        return saveDonationsP;
    }
    
    public boolean seDeplacer(Tuile destination){
        //Deplace l'aventurier et lui retire un PA
        if(this.saveDP.get(destination) !=null && this.saveDP.get(destination) <= this.getPointsAction()){
            this.setTuileOccupee(destination);
            this.pointsAction -= this.saveDP.get(destination);
            this.saveDP = new HashMap<>();
            return true;
        }else{
            return false;
        }
    }
    
    public void addCarteMain(CarteTirage carte){
        // donne une carte et verifie que la main n'est pas exece
        this.cartesMain.add(carte);
        mainExcede = this.cartesMain.size() > 5;
//        System.out.println("cartes dans la main: " +this.cartesMain.size());
//        System.out.println("mainexcede: " + mainExcede);
        notifierObservateur(new Message("update"));
    }
    
    public void removeCarteMain(CarteTirage carte){
        // verifie chaques carte en main pour verifier celle qui doit etre defausser et la defausse 
        for (int i = 0; i < cartesMain.size(); i++) {
            if (cartesMain.get(i).getId() == carte.getId()) {
                cartesMain.remove(i);
                break;
            }
        }
        mainExcede = this.cartesMain.size() > 5;
        notifierObservateur(new Message("update"));
    }
    
    public void piocheCartes(ArrayList<CarteTirage> cartes){
        // ajoute une carte a la main de l'aventurier
        for (CarteTirage carte : cartes){
            addCarteMain(carte);
        }
    }
    
    public HashMap<Tuile, Integer> getDeplacementPouvoir(Grille g){
        // cette methode est utiliser pour les aventurier utilisant un pouvoir
        return null;
    }

    public ArrayList<CarteTirage> getCartesMain() {
        //donne une collection des cartes du joueurs
        return cartesMain;
    }

    public boolean isMainExcede() {
        // donne vrai si la main du joueur est exede
        return mainExcede;
    }
    
    public CarteTirage getCarteParIndice(int index){
        // donne une carte en fonction de son id
        return this.cartesMain.get(index);
    }
    
    public void utiliseCarte(CarteTirage carte){
        //supprime une carte de la main de l'aventurier si celui ci l'utilise
        this.removeCarteMain(carte);
    }
    
    public boolean donneCarte(CarteTirage carte, Aventurier a){
        // Apres verification, ajoute une carte a l'aventurier cibler et la supprime a l'aventurier en cours
        if (this.getPointsAction() > 0 && saveDonationsP.contains(a)) {
            a.addCarteMain(carte);
            this.removeCarteMain(carte);
            this.pointsAction --;
            return true;
        }else return false;
    }

    public ArrayList<Aventurier> getSaveDonationsP() {
        // donne les joueur a qui l'aventurier en cours peut donner une carte
        return saveDonationsP;
    }

    public void setSaveDonationsP(ArrayList<Aventurier> saveDonationsP) {
        // definie les joueur a qui l'aventurier en cours peut donner une carte
        this.saveDonationsP = saveDonationsP;
    }
    
    public boolean isDonnationPossible(Aventurier a){
        // verifie que le joueur ciblé est sur la meme case que le joueur en cours
        return this.tuileOccupee.equals(a.tuileOccupee);
    }
    
    public boolean peutAcquerirTresor(){
        // verifie que le joueur possede 4 cartes de la meme couleur que la case ou il se trouve
        if (this.getTuileOccupee().getSpecial() != Special.rien && this.getTuileOccupee().getSpecial() != Special.heliport /*&& this.getPointsAction() >0*/) {
            int nbcartes = 0;
            for (CarteTirage carteTirage : cartesMain) {
                System.out.println(carteTirage);
                if (carteTirage.getType() == this.getTuileOccupee().getSpecial()) {
                    nbcartes ++;
                }
            }
            return nbcartes >= 4;
        }else return false;
    }
    
    public boolean obtenirTresor(){
        
        if (this.tuileOccupee.getSpecial() != Special.rien && this.tuileOccupee.getSpecial() != Special.heliport && this.getPointsAction() > 0){
            ArrayList<CarteTirage> cartesTresor = new ArrayList<>();
            int nbCarte = 0;
            for (CarteTirage carte : cartesMain){
                if (carte instanceof CarteTresor && carte.getType() == this.tuileOccupee.getSpecial()){
                    cartesTresor.add(carte);
                    nbCarte += 1;
                }
            }
            if (nbCarte >= 4){
                for (int i = 0; i<4 ; i++){
                    if (nbCarte < 5){
                        removeCarteMain(cartesTresor.get(i));
                    }
                }
                this.pointsAction --;
                return true;
            } 
        }
        return false;
    }

    public boolean isPouvoirAActiver() {
        // verifie que le pouvoir est activer
        return pouvoirAActiver;
    }

    public boolean isPouvoirDispo() {
        // verifie que le pouvoir peut etre utiliser
        return pouvoirDispo && this.getPointsAction() > 0;
    }
    
    public boolean hasNeighbors(){
        // verifie si l'aventurier est seul sur sa tuile
        return this.getTuileOccupee().getAventuriers().size() > 1;
    }
    
    public ArrayList<Aventurier> getNeighbors(){
        //donne les aventurier qui sont sur la meme case
        ArrayList<Aventurier> ret = this.getTuileOccupee().getAventuriers();
        ret.remove(this);
        return ret;
    }
    
    public HashMap<Tuile,Integer> getEchappePossible(Grille grille){
        // verifie si l'aventurier peu quitter la case ou il se trouve il elle coule
        HashMap<Tuile,Integer> tuiles = new HashMap<>();
        
        for (Tuile tuile : this.tuileOccupee.getTuileAdjacente(grille)){
            if (tuile != null && tuile.getEtat() != EtatsTuiles.sombree)
                tuiles.put(tuile,0);
        }
        return tuiles;
    }
    
    
    @Override
    public String toString() {
        return "Aventurier{\n" + "pointsAction=" + pointsAction + ", \ntuileOccupee=" + tuileOccupee + '}';
    }
    
    
}
