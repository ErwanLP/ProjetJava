package projetjava;

import java.util.Scanner;

/*import java.util.Random;*/
/*int valeur = 1 + r.nextInt(37 - 1);*/
public class ProjetJava {

    public static void main(String[] args) {
        /*    int n = 12;
         int m = 12;
         int[][] grille = new int[10][10];
         char[][] tabVide = new char[12][12];
         GenereTableauVide(tabVide, n, m);
         AfficheGrille(grille);*/
        menuPrincipale();

    }

    public static void menuPrincipale() {
        Scanner sc = new Scanner(System.in);
        System.out.println("-----------------------------------------------------------");
        System.out.println("STREAM");
        System.out.println("Par Erwan Le Poder et Florian Migot");
        System.out.println("-----------------------------------------------------------");
        System.out.println("MENU");
        System.out.println("1-jouer");
        System.out.println("2-deux");
        System.out.println("3-trois");
        System.out.println("-----------------------------------------------------------");
        System.out.println("Votre choix ?");
        int choix = sc.nextInt();
        String monthString;
        switch (choix) {
            case 1:
                jouer();
                break;
            case 2:
                //deux();
                break;
            case 3:
                //trois();
                break;

            default:
                menuPrincipale();
                break;
        }

    }
    
    
    
    public static void jouer(){
        
        
        
        
        
    }

    public static void GenereTableauVide(char[][] tabVide, int n, int m) {
        int i;
        int j;
        char caractere = 'o';
        for (i = 0; i < n; i++) {
            for (j = 0; j < m; j++) {
                tabVide[i][j] = caractere;
            }
        }
    }

    public static void AfficheGrille(int[][] grille) {
        int i;
        int j;
        for (i = 0; i < grille.length; i++) {
            System.out.println("");
            for (j = 0; j < grille[i].length; j++) {
                System.out.print("#\t");
            }
        }
        System.out.println();
    }
}
