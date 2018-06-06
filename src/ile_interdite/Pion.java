/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        int h = this.getSize().height;
        int w = this.getSize().width;
        
        g.fillOval(0, 0, h, w);
        g.setColor(Color.black);
        g.drawOval(0, 0, h, w);
//        System.out.println("painting pion.");
    }
    
    public void setColor(Color c){
        this.color = c;
    }
    
}
