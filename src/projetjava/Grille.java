package projetjava;

public class Grille {

// On défini ici les différents attributs de la classe Grille :
    String[][] tab; // <- On va utiliser un tableau contenant des chaines de caractères
    int longueur;
    int hauteur;
    int nbGrille;

    Grille(int nbGrille) {

// On créer la première grille du jeu à l'aide des attributs :
        if (nbGrille == 1) {
            this.tab = new String[12][13];
            this.nbGrille = nbGrille;
            hauteur = 12;
            longueur = 13;
        }

// On créer la deuxième grille du jeu à l'aide des attributs :
        if (nbGrille == 2) {
            this.tab = new String[10][11];
            this.nbGrille = nbGrille;
            hauteur = 10;
            longueur = 11;



        }
        if (nbGrille == 3) {
            this.tab = new String[9][8];
            this.nbGrille = nbGrille;
            hauteur = 9;
            longueur = 8;



        }
        if (nbGrille == 4) {
            this.tab = new String[6][7];
            this.nbGrille = nbGrille;
            hauteur = 6;
            longueur = 7;


        }

    }
}
