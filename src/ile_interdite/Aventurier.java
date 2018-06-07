/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author reboulef
 */
public abstract class Aventurier {
    private int pointsAction;       //Points d'action de l'aventurier
    private Tuile tuileOccupee;      //Tuile occupée par l'aventurier
    private HashMap<Tuile,Integer> saveDP; //deplacements possibles
    private HashMap<Tuile, Integer> saveAP; // assechements possibles
    private Pion pion;
    public boolean pouvoirAActiver;
    public boolean pouvoirDispo = false;

    public Aventurier(Tuile spawn){
        this.setTuileOccupee(spawn);
        this.saveDP = new HashMap<>();
        this.pion = new Pion();
        this.setPointsAction(3);
        this.pouvoirAActiver = false;
    }
    
    
    
    public Pion getPion(){
        return this.pion;
    }
    
    public int getPointsAction() {
        return pointsAction;
    }
    
    public Tuile getTuileOccupee(){
        return this.tuileOccupee;
    }



    public void setPointsAction(int pointsAction) {
        this.pointsAction = pointsAction;
    }

    public void setTuileOccupee(Tuile tuileOccupee) {
        if (this.tuileOccupee != null) {
            this.tuileOccupee.removeAventurier(this);

        }
        this.tuileOccupee = tuileOccupee;
        this.tuileOccupee.addAventurier(this);

    }
    
    public HashMap<Tuile, Integer> getSaveAP(){
        return this.saveAP;
    }

    public void setSaveAP(HashMap<Tuile, Integer> saveAP) {
        this.saveAP = saveAP;
    }

    
    
    public HashMap<Tuile, Integer> getSaveDP() {
        return saveDP;
    }

    public void setSaveDP(HashMap<Tuile, Integer> saveDP) {
        this.saveDP = saveDP;
    }
    
    
    
    public HashMap<Tuile, Integer> getAssechementPossible(Grille grille){
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
    
    
    public void testTuileAss(Tuile tuile){
        if (tuile != null && tuile.getEtat() == EtatsTuiles.inondee) {
            this.saveAP.put(tuile, 1);
        }
    }
    
    public boolean assecher(Tuile t){
        if (this.getPointsAction() >0 && this.saveAP.containsKey(t)) {
            this.pointsAction --;
            t.setEtat(EtatsTuiles.seche);// pas besoin de tester plus: si la tuile est dans saveAP, elle est inondee.
            this.saveAP = new HashMap<>();
            return true;
        }
        return false;
    }
    
    
    public HashMap<Tuile, Integer> getDeplacementPossible(Grille grille){ // cette methode renvoie un Hashmap qui pour chaque tuile renvoie le cout de s'y deplacer.
        Tuile tdd  = this.getTuileOccupee();//tuile de depart
        this.saveDP.put(tdd,0); //rester au meme endroit ne coute rien
        if(this.getPointsAction() >0){
                    this.propager(grille, tdd, 1);
        }
        return this.saveDP;
    }
    
    
    public void propager(Grille grille, Tuile tuileDeDepart, int cout){ 
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
                this.propager(grille, tdd, cout+1); //I'll save you! Recursion powers Activate!
            }      
        }
    }
    
    public void testTuile(Tuile tuile, ArrayList<Tuile> al, int cout){
//        System.out.println(tuile.toString()); 
//        System.out.println(this.saveDP);
        
        
        if (tuile != null && tuile.getEtat() != EtatsTuiles.sombree) {
            
            int oldCout;
            if(this.saveDP.get(tuile) == null){
                oldCout = 4; // 4 sera toujours plus grand que cout.
            }else{
                oldCout = this.saveDP.get(tuile);
            }
//            System.out.println(oldCout);
            if (oldCout > cout) {
                this.saveDP.put(tuile, cout);
//                System.out.println("=====ACCEPT=====");
                al.add(tuile);
            }
            
            
        }
  

    }
    
    public boolean seDeplacer(Tuile destination){
        if(this.saveDP.get(destination) !=null && this.saveDP.get(destination) <= this.getPointsAction()){
            this.setTuileOccupee(destination);
            this.pointsAction -= this.saveDP.get(destination);
            this.saveDP = new HashMap<>();
            return true;
        }else{
            return false;
        }
    }

    
    public HashMap<Tuile, Integer> getDeplacementPouvoir(Grille g){
        return null;
    }
    
    @Override
    public String toString() {
        return "Aventurier{\n" + "pointsAction=" + pointsAction + ", \ntuileOccupee=" + tuileOccupee + '}';
    }
    
    
}