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
        this.ihm = new IHM(this,grille);
        this.grille.getTuilebyName("La Porte de Bronze").setEtat(EtatsTuiles.inondee);
    }
    
    
    
    
    
    @Override
    public void traiterMessage(Message msg) {
        if (msg.contenu == "bouger") {
            throw new UnsupportedOperationException("bouger n'est pas encore disponible");
        }
        if (msg instanceof MessageDeTuile) { // le polymorphisme c'est chouette
            MessageDeTuile msgDT = (MessageDeTuile) msg;
            if (msg.contenu == "clic") {
                System.out.println("Controlleur: clic de:\n" + msgDT.tuileDOrigine);
                msgDT.tuileDOrigine.setEtat(EtatsTuiles.sombree);
            }
        }

    }
    
    
    
    
}
