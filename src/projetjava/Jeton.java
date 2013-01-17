package projetjava;

import java.util.Random;

public class Jeton {

// Définition des différents attributs :
    int valeur;
    static int[] tabNbAleat = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 99};
// Un tableau contenant tous les jetons.
    static Random r;
    
    Jeton(Random r) {  // Conscruteur appelant la fonction générer un jeton
        this.valeur = genereJeton(r);
    }

    public int genereJeton(Random r) {

        int valeur;
        do {
            
            valeur = +r.nextInt(40 - 0);    // saisie d'une valeur aléatoire
        } while (tabNbAleat[valeur] == 0);  // tant que la valeur du jeton est égal a 0
        int jeton = tabNbAleat[valeur];
        tabNbAleat[valeur] = 0; // le jeton utilisé on est mis 0
        return jeton; // on return la valeur du jeton

    }

    public static void reinitialisertabNbAleat() {
// Fonction de rénitialisation des jetons
        int[] tabNbAleatbis = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 99};
// tableau des jetons
        for (int i = 0; i < tabNbAleat.length; i++) {
            tabNbAleat[i] = tabNbAleatbis[i];   // On écrase les anciennes valeurs (= 0), ce qui rénitialise les jetons
        }

    }
}
