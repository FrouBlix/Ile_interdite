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

    public Controlleur() {
        this.grille = new Grille();
    }
    
    
    
    
    
    @Override
    public void traiterMessage(Message msg) {
        if (msg.contenu == "bouger") {
            throw new UnsupportedOperationException("bouger n'est pas encore disponible");
        }
    }
    
    
    
}
