/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import java.awt.Color;

/**
 *
 * @author reboulef
 */
public class CarteTresor extends CarteTirage{

    private Special type;
    
    public CarteTresor(String nom, Special type) {
        super(nom);
        this.type = type;
    }

    public Special getType() {
        return type;
    }

}
