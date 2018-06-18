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
public class MessageAventurier extends Message{
    public Aventurier aventurier;
    public MessageAventurier(String contenu, Aventurier a) {
        super(contenu);
        this.aventurier = a;
    }
    
}
