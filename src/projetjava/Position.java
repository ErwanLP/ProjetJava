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

    String abscisse;
    int ordonnee;
    int valeur;

    Position(String abscisse, int ordonnee) {
        this.abscisse = abscisse;
        this.ordonnee = ordonnee;

    }

    public String toString() {
        String result = "Point:";
        result += "\n Abssice:\t" + abscisse;
        result += "\n Ordonnee:\t" + ordonnee;
        return result;
    }

    public static Position suivant(Position p) {
        if (p.colonne == 8) {
            return new Position(p.ligne + 1, 0);

        } else {
            return new Position(p.ligne, p.colonne + 1);

        }

    }
}
