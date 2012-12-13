/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava;

/**
 *
 * @author Erwan
 */
public class ListePos {

    int x;
    int y;
    int nbGrille;
    ListePos suivant;

    ListePos(int nbGrille) {
        if (nbGrille == 1) {
            x = 2;
            y = 2;
            this.nbGrille = nbGrille;
            suivant = fonctionSuivant(nbGrille);

        }
        if (nbGrille == 2) {
            x = 2;
            y = 6;
            this.nbGrille = nbGrille;
            suivant = fonctionSuivant(nbGrille);
        }

    }

    ListePos(int x, int y, int nbGrille, ListePos suivant) {
        this.x = x;
        this.y = y;
        this.nbGrille = nbGrille;
        this.suivant = suivant;

    }

    public static ListePos fonctionSuivant(int nbGrille) {

        if (nbGrille == 1) {
            ListePos suivant20 = new ListePos(0, 0, nbGrille, null);
            ListePos suivant19 = new ListePos(12, 11, nbGrille, suivant20);
            ListePos suivant18 = new ListePos(11, 11, nbGrille, suivant19);
            ListePos suivant17 = new ListePos(10, 11, nbGrille, suivant18);
            ListePos suivant16 = new ListePos(9, 11, nbGrille, suivant17);
            ListePos suivant15 = new ListePos(8, 11, nbGrille, suivant16);
            ListePos suivant14 = new ListePos(7, 11, nbGrille, suivant15);
            ListePos suivant13 = new ListePos(7, 10, nbGrille, suivant14);
            ListePos suivant12 = new ListePos(7, 9, nbGrille, suivant13);
            ListePos suivant11 = new ListePos(7, 8, nbGrille, suivant12);
            ListePos suivant10 = new ListePos(7, 7, nbGrille, suivant11);
            ListePos suivant9 = new ListePos(7, 6, nbGrille, suivant10);
            ListePos suivant8 = new ListePos(7, 5, nbGrille, suivant9);
            ListePos suivant7 = new ListePos(7, 4, nbGrille, suivant8);
            ListePos suivant6 = new ListePos(7, 3, nbGrille, suivant7);
            ListePos suivant5 = new ListePos(7, 2, nbGrille, suivant6);
            ListePos suivant4 = new ListePos(6, 2, nbGrille, suivant5);
            ListePos suivant3 = new ListePos(5, 2, nbGrille, suivant4);
            ListePos suivant2 = new ListePos(4, 2, nbGrille, suivant3);
            ListePos suivant = new ListePos(3, 2, nbGrille, suivant2);
            return suivant;
        }


        if (nbGrille == 2) {
            ListePos suivant23 = new ListePos(0, 0, nbGrille, null);
            ListePos suivant22 = new ListePos(7, 9, nbGrille, suivant23);
            ListePos suivant21 = new ListePos(7, 8, nbGrille, suivant22);
            ListePos suivant20 = new ListePos(7, 7, nbGrille, suivant21);
            ListePos suivant19 = new ListePos(7, 6, nbGrille, suivant20);
            ListePos suivant18 = new ListePos(7, 5, nbGrille, suivant19);
            ListePos suivant17 = new ListePos(7, 4, nbGrille, suivant18);
            ListePos suivant16 = new ListePos(7, 3, nbGrille, suivant17);
            ListePos suivant15 = new ListePos(7, 2, nbGrille, suivant16);
            ListePos suivant14 = new ListePos(8, 2, nbGrille, suivant15);
            ListePos suivant13 = new ListePos(9, 2, nbGrille, suivant14);
            ListePos suivant12 = new ListePos(10, 2, nbGrille, suivant13);
            ListePos suivant11 = new ListePos(10, 3, nbGrille, suivant12);
            ListePos suivant10 = new ListePos(10, 4, nbGrille, suivant11);
            ListePos suivant9 = new ListePos(10, 5, nbGrille, suivant10);
            ListePos suivant8 = new ListePos(10, 6, nbGrille, suivant9);
            ListePos suivant7 = new ListePos(9, 6, nbGrille, suivant8);
            ListePos suivant6 = new ListePos(8, 6, nbGrille, suivant7);
            ListePos suivant5 = new ListePos(7, 6, nbGrille, suivant6);
            ListePos suivant4 = new ListePos(6, 6, nbGrille, suivant5);
            ListePos suivant3 = new ListePos(5, 6, nbGrille, suivant4);
            ListePos suivant2 = new ListePos(4, 6, nbGrille, suivant3);
            ListePos suivant = new ListePos(3, 6, nbGrille, suivant2);
            return suivant;
        } else {
            ListePos suivant20 = new ListePos(0, 0, nbGrille, null);
            return suivant20;
        }
    }
}
