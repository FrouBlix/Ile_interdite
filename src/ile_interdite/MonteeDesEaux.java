/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

/**
 *
 * @author reboulef
 */
public class MonteeDesEaux extends Observe{
    private int compteur;
    private int nbCarteInondation;
    private boolean inondationTotal;
    
    public MonteeDesEaux(int niveau){
        this.compteur = niveau;
        this.inondationTotal = false;
        setNbCarteInondation(compteur);
    }
    
    public void setNbCarteInondation(int compteur){
        if (compteur < 3){
            this.nbCarteInondation = 2;
        }
        else if (compteur > 2 & compteur < 6){
            this.nbCarteInondation = 3;
        }
        else if (compteur > 5 & compteur < 8){
            this.nbCarteInondation = 4;
        }
        else if (compteur > 7 & compteur < 10){
            this.nbCarteInondation = 5;
        }
        else if (compteur == 10){
            this.inondationTotal = true;
        }
    }
    
    public void incrementeCompteur(){
        setCompteur(compteur + 1);
        setNbCarteInondation(this.compteur);
    }
    
    public void setCompteur(int compteur){
        this.compteur = compteur;
        notifierObservateur(new Message("MDE " + compteur));
    }
    
    public int getNbCarteInodation(){
        return this.nbCarteInondation;
    }
    
    public int getCompteur(){
        return this.compteur;
    }
}
