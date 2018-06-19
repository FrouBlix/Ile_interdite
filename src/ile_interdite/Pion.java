/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author senno
 */
public class Pion extends JPanel{
    private Color color;
    
    @Override
    public void paint(Graphics g) {
        g.setColor(color);
        int h = this.getSize().height-1;
        int w = this.getSize().width-1;
        
        g.fillOval(0, 0, h, w);
        g.setColor(Color.black);
        g.drawOval(0, 0, h, w);
//        System.out.println("painting pion.");
    }
    
    public void setColor(Color c){
        this.color = c;
    }

    public Pion setColorRet(Color c){
        this.setColor(c);
        return this;
    }
    
    public Color getColor(){
        return this.color;
    }
    
}
