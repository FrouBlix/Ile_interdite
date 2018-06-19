/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author billo
 */
public class ImagePanel extends JPanel{
    
    private BufferedImage image;

    public ImagePanel(String pathToFile) {
        super();
        try {
            image = ImageIO.read(new File(pathToFile));
        } catch (IOException e) {
            System.err.println("Impossible de lire le fichier :" + pathToFile);
            e.printStackTrace();
        }
        
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }  
}
