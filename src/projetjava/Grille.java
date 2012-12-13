/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava;

/**
 *
 * @author Erwan
 */
public class Grille {

    String[][] tab;
    int longeur;
    int hauteur;
    int nbGrille;

    Grille(int nbGrille) {

        if (nbGrille == 1) {
          this.tab = new String[12][13];

        }

    }
}
