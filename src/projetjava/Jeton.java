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
    static int[] tabNbAleat = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 99};
    static Random r;
    
    Jeton(Random r) {  //Conscruteur
        this.valeur = genereJeton(r);
    }

    public int genereJeton(Random r) {

        int valeur;
        do {
            
            valeur = +r.nextInt(40 - 0);
        } while (tabNbAleat[valeur] == 0);  // tant que le jeton est == de 0 // be careful
        int jeton = tabNbAleat[valeur];
        tabNbAleat[valeur] = 0; // le jeton est utiliser on le met a 0
        return jeton; // on return la valeur du jeton

    }

    public static void reinitialisertabNbAleat() {

        int[] tabNbAleatbis = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 99};
        for (int i = 0; i < tabNbAleat.length; i++) {
            tabNbAleat[i] = tabNbAleatbis[i];
        }

    }
}
