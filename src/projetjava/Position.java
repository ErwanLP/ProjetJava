/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava;

/**
 *
 * @author Erwan
 */
public class Position {

   int x;
    int y;
    int valeur;

    Position(int x, int y) {
        this.x = x;
        this.y = y;

    }
    
        Position() {
        this.x = 0;
        this.y = 9;

    }


    public String toString() {
        String result = "Point:";
        result += "\n Abssice:\t" + x;
        result += "\n Ordonnee:\t" + y;
        return result;
    }

    public static Position suivant(Position p) {
        if (p.x<5) {
            return new Position(p.x + 1, p.y);
        } 
        if (p.x == 5 && p.y > 0) {
            return new Position(p.x,p.y+1);
        }
        else{
            return new Position(p.x+1,p.y);
        }
    }
}
