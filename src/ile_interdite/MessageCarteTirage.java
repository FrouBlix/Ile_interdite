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
public class MessageCarteTirage extends Message{
    public CarteTirage carte;

    public MessageCarteTirage(String contenu, CarteTirage carte) {
        super(contenu);
        this.carte = carte;
    }
    
}
