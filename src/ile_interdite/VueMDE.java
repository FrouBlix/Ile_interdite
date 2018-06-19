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
public class VueMDE extends ImagePanel implements Observateur{
    private ImagePanel marqueur; 
    public VueMDE(MonteeDesEaux mde) {
        super("Niveau.png");
        marqueur = new ImagePanel("stick.png");
        marqueur.setOpaque(false);
        this.add(marqueur);
        mde.addObservateur(this);
    }
    
    public void bougercurseur(int position){
        // la flemme la
    }

    @Override
    public void traiterMessage(Message msg) {
        if (msg.contenu.contains("MDE")) {
            int niveau = (int)msg.contenu.charAt(4);
        }
    }
    
}
