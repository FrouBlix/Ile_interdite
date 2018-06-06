/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

/**
 *
 * @author senno
 */
public class Controlleur implements Observateur{

    private Grille grille;
    private IHM ihm;

    public Controlleur() {
        this.grille = new Grille();
        Messager joueurTest = new Messager(this.grille.getTuilebyName("Heliport"));
        Navigateur joueur2 = new Navigateur(this.grille.getTuilebyName("Heliport"));
        this.ihm = new IHM(this,grille);
        this.grille.getTuilebyName("La Porte de Bronze").setEtat(EtatsTuiles.inondee);
        
        this.ihm.getGrille().updateAll(); //on update toutes les tuiles apres avoir fini le chargement
    }
    
    
    
    
    
    @Override
    public void traiterMessage(Message msg) {
        System.out.println("message: " + msg.contenu);
        if (msg instanceof MessageDeTuile) { // le polymorphisme c'est chouette
            MessageDeTuile msgDT = (MessageDeTuile) msg;
            if (msg.contenu == "clic") {
                System.out.println("Controlleur: clic de:\n" + msgDT.tuileDOrigine);
                switch(msgDT.tuileDOrigine.getEtat()){
                    case inondee: msgDT.tuileDOrigine.setEtat(EtatsTuiles.sombree);
                    break;
                    case seche: msgDT.tuileDOrigine.setEtat(EtatsTuiles.inondee);
                    break;
                    case sombree: msgDT.tuileDOrigine.setEtat(EtatsTuiles.seche);
                }
            }
        }

    }
    
    
    
    
}
