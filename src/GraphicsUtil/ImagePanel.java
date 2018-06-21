/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicsUtil;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
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
    private double scalingFactor;

    public ImagePanel(String pathToFile, double scalingFactor) {
        super();
        this.setScalingFactor(scalingFactor);
        try {
            image = ImageIO.read(new File(pathToFile));
        } catch (IOException e) {
            System.err.println("Impossible de lire le fichier :" + pathToFile);
            
            e.printStackTrace();
        }
        
    }

    public ImagePanel(String pathToFile) {
        super();
        this.setScalingFactor(1);
        try {
            image = ImageIO.read(new File(pathToFile));
        } catch (IOException e) {
            System.err.println("Impossible de lire le fichier :" + pathToFile);
            
            e.printStackTrace();
        }
    }
    
    public ImagePanel(String pathToFile, boolean flip){
        this(pathToFile);
        if (flip) {
            this.image = GraphicsUtil.FlipImage.horizontalFlip(image);

        }
    }

    public ImagePanel(String pathToFile, double scalingFactor, boolean flip) {
        this(pathToFile,scalingFactor);
        if (flip) {
        this.image = GraphicsUtil.FlipImage.horizontalFlip(image);
        }
    }
    
    
    
    

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        int w = (int)(image.getWidth() * scalingFactor);
        int h = (int)(image.getHeight() * scalingFactor);

        g.drawImage(image, 0, 0, w, h, this);

    }  

    public double getScalingFactor() {
        return scalingFactor;
    }

    public void setScalingFactor(double scalingFactor) {
        this.scalingFactor = scalingFactor;
    }
    
    
    
    @Override
    public Dimension getPreferredSize(){
        return new Dimension((int)(image.getWidth() *scalingFactor), (int)(image.getHeight()*scalingFactor));
    }
}
