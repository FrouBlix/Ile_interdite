/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author reboulef
 */
public class Grille {
    private HashMap<Coordonnees,Tuile> tuiles;

    public Grille() {
        this.tuiles = new HashMap<>();
        this.tuiles.put(new Coordonnees(0, 0), null); //remplir les coins avec des null
        this.tuiles.put(new Coordonnees(0, 1), null);
        this.tuiles.put(new Coordonnees(1, 0), null);
        this.tuiles.put(new Coordonnees(0, 4), null);
        this.tuiles.put(new Coordonnees(0, 5), null);
        this.tuiles.put(new Coordonnees(1, 5), null);
        this.tuiles.put(new Coordonnees(4, 0), null);
        this.tuiles.put(new Coordonnees(5, 0), null);
        this.tuiles.put(new Coordonnees(5, 1), null);
        this.tuiles.put(new Coordonnees(4, 5), null);
        this.tuiles.put(new Coordonnees(5, 4), null);
        this.tuiles.put(new Coordonnees(5, 5), null);
        Coordonnees dummyCoordonnees = new Coordonnees(0, 0);
        ArrayList<Tuile> pileDeTuiles = new ArrayList<>();
        pileDeTuiles.add(new Tuile(Special.rien, "Le Pont des Abimes", dummyCoordonnees));
        pileDeTuiles.add(new Tuile(Special.rien, "La Porte de Bronze", dummyCoordonnees));
        pileDeTuiles.add(new Tuile(Special.cristal, "La Caverne des Ombres", dummyCoordonnees));
        pileDeTuiles.add(new Tuile(Special.rien, "La Porte de Fer", dummyCoordonnees));
        pileDeTuiles.add(new Tuile(Special.rien, "La Porte d’Or", dummyCoordonnees));
        pileDeTuiles.add(new Tuile(Special.rien, "Les Falaises de l’Oubli", dummyCoordonnees));
        pileDeTuiles.add(new Tuile(Special.calice, "Le Palais de Corail", dummyCoordonnees));
        pileDeTuiles.add(new Tuile(Special.rien, "La Porte d’Argent", dummyCoordonnees));
        pileDeTuiles.add(new Tuile(Special.rien, "Les Dunes de l’Illusion", dummyCoordonnees));
        pileDeTuiles.add(new Tuile(Special.heliport, "Heliport", dummyCoordonnees));
        pileDeTuiles.add(new Tuile(Special.rien, "La Porte de Cuivre", dummyCoordonnees));
        pileDeTuiles.add(new Tuile(Special.griffon, "Le Jardin des Hurlements", dummyCoordonnees));
        pileDeTuiles.add(new Tuile(Special.rien, "La Foret Pourpre", dummyCoordonnees));
        pileDeTuiles.add(new Tuile(Special.rien, "Le Lagon Perdu", dummyCoordonnees));
        pileDeTuiles.add(new Tuile(Special.rien, "Le Marais Brumeux", dummyCoordonnees));
        pileDeTuiles.add(new Tuile(Special.rien, "Observatoire", dummyCoordonnees));
        pileDeTuiles.add(new Tuile(Special.rien, "Le Rocher Fantome", dummyCoordonnees));
        pileDeTuiles.add(new Tuile(Special.cristal, "La Caverne du Brasier", dummyCoordonnees));
        pileDeTuiles.add(new Tuile(Special.pierre, "Le Temple du Soleil", dummyCoordonnees));
        pileDeTuiles.add(new Tuile(Special.pierre, "Le Temple de La Lune", dummyCoordonnees));
        pileDeTuiles.add(new Tuile(Special.calice, "Le Palais des Marees", dummyCoordonnees));
        pileDeTuiles.add(new Tuile(Special.rien, "Le Val du Crepuscule", dummyCoordonnees));
        pileDeTuiles.add(new Tuile(Special.rien, "La Tour du Guet", dummyCoordonnees));
        pileDeTuiles.add(new Tuile(Special.griffon, "Le Jardin des Murmures", dummyCoordonnees));
//        pileDeTuiles.add(new Tuile(Special.rien, "", dummyCoordonnees));
        Collections.shuffle(pileDeTuiles); //FIXME: decommenter ca pour melanger les tuiles a nouveau.
        for (Tuile tuileToAdd : pileDeTuiles) {
            this.autoAddTuile(tuileToAdd);
        }
    }
    
    public void addTuile(Tuile tuile){
        this.tuiles.put(tuile.getCoordonnees(), tuile);
    }
    
    public void autoAddTuile(Tuile tuile){ //ajuste automatiquement les coordonnees d'une tuile afin de la placer au bonnes coordonnees.
        Coordonnees coords = new Coordonnees(0, 0);
        for (int i = 0; i < 6; i++) {
            coords.setX(i);
            for (int j = 0; j < 6; j++) {
                coords.setY(j);
                if (!this.tuiles.containsKey(coords)) {
                    tuile.setCoordonnees(coords);
                    this.addTuile(tuile);
                    return;
                }
            }
        }
    }
    
    public Tuile getTuile(Coordonnees coords){
        return this.tuiles.get(coords);
    }
    
    public HashMap<Tuile,Integer> getTuilesInondees(){
        HashMap<Tuile, Integer> tuilesInondees = new HashMap<>();
        for (Map.Entry<Coordonnees,Tuile> entry : tuiles.entrySet()){
            Tuile value = entry.getValue();
            if (value != null){
                if (value.getEtat() == EtatsTuiles.inondee){
                    tuilesInondees.put(value,0);
                }
            }
        }
        return tuilesInondees;
    }
    
    public Tuile getTuilebyName(String name){ // utiliser cette methode est vraiment inefficace, mais il faut faire un choix entre classer par nom ou par coordonnees.
        //vu qu'on cherche par nom max 5 fois par tour, compare a 30+ 
        for (Map.Entry<Coordonnees, Tuile> entry : tuiles.entrySet()) {
            Coordonnees key = entry.getKey();
            Tuile value = entry.getValue();
            if(value !=null){
                if(value.getNom() == null ?
                    name == null : 
                    value.getNom().equals(name)){
                return value;
                } 
            }
            
        }
//        System.out.println("=======not found=======");
        return null;
    }
    
    public HashMap<Coordonnees,Tuile> getAll(){
        return this.tuiles;
    }
    
}
