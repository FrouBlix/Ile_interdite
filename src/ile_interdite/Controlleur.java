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
        Coordonnees coord1 = new Coordonnees(1, 0);
        Coordonnees coord2 = new Coordonnees(1, 0);
        System.out.println(coord1 == coord2);
        this.grille = new Grille();
        this.ihm = new IHM(this,grille);
    }
    
    
    
    
    
    @Override
    public void traiterMessage(Message msg) {
        if (msg.contenu == "bouger") {
            throw new UnsupportedOperationException("bouger n'est pas encore disponible");
        }
    }
    
    
    
}
