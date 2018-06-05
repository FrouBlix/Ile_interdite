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
public class MessageDeTuile extends Message {
    public Tuile tuileDOrigine;
    public MessageDeTuile(String contenu, Tuile tuileDOrigine) {
        super(contenu);
        this.tuileDOrigine = tuileDOrigine;
    }
    
}
