/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava;

import java.util.Random;

/**
 *
 * @author Erwan
 */
public class Jeton {

    int valeur;
    int[][] tabNbAleat = {{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39}, {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 50}};

    Jeton() {
        //this.valeur = genereJeton();
    }

    public void genereJeton() {

        Random r = new Random();
        int vari;
        int valeur = +r.nextInt(39 - 0);
        for (int i = 0; i < 40; i++) {
            if (valeur == i) {
                vari = i;
            }
        }


        /*for (int i = 0; i < 2; i++) {
         for (int j = 0; j <40 ; j++) {
         System.out.print(tabNbAleat[i][j] + "\t");

         }
         System.out.println();
         }*/



    }
}
