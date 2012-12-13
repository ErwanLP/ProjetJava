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
    int longueur;
    int hauteur;
    int nbGrille;

    Grille(int nbGrille) {

        if (nbGrille == 1) {
            this.tab = new String[12][13];
            this.nbGrille = nbGrille;
            hauteur = 12;
            longueur = 13;
        }
        if (nbGrille == 2) {
            this.tab = new String[10][11];
            this.nbGrille = nbGrille;
            hauteur = 10;
            longueur = 11;



        }

    }
}
