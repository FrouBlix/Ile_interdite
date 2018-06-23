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
public class Message {
    public String contenu;
    public String[] nomJoueur;
    public int nbJoueur;
    
    public Message(String contenu) {
        this.contenu = contenu;
    }
    
    public Message(String contenu, int nbJoueur){
        this.contenu = contenu;
        this.nomJoueur = new String[nbJoueur];
        this.nbJoueur = nbJoueur;
    }
}
