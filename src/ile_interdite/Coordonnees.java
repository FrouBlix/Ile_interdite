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
public class Coordonnees {

    private int x;
    private int y;
    
    public Coordonnees(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public int getXplus(int delta){
        return x + delta;
    }
    
    public int getYplus(int delta){
        return y + delta;
    }
    
    public void setXplus(int delta) {
        this.x = this.x + delta;
    }

    public void setYplus(int delta) {
        this.y = this.y + delta;
    }
    
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordonnees{" + "x=" + x + ", y=" + y + '}';
    }
    
    @Override
    public boolean equals(Object o){
        return this.hashCode() == o.hashCode();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.x;
        hash = 97 * hash + this.y;
        return hash;
    }
    
}

