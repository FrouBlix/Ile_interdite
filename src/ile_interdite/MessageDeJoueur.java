/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

/**
 *
 * @author billo
 */
public class MessageDeJoueur extends Message{
    public Joueur joueur;
    public MessageDeJoueur(String contenu, Joueur j) {
        super(contenu);
        this.joueur = j;
    }
    
}
