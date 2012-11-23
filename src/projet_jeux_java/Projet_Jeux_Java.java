package projet_jeux_java;

import java.util.*;

public class Projet_Jeux_Java {

    public static void function1() {
        
    }

    public static void main(String[] args) {
        // TODO code application logic here
       /* init();*/
        /*genereTabNbAleat();*/
        genereNbAleat();
    }

    public static void init() {

        int n = 12;
        int m = 13;
        char[][] tab = new char[n][m];
        genereGrille(tab, n, m);
        afficheGrille(tab, n, m);
    }

    public static void genereGrille(char[][] tab, int n, int m) {

        int i;
        int j;
        char caractere = '#';
        for (i = 0; i < n; i++) {
            for (j = 0; j < m; j++) {
                if ((i != 1 || j != 1) && (i != 1 || j != 2) && (i != 1 || j != 3) && (i != 1 || j != 4) && (i != 1 || j != 5) && (i != 1 || j != 6) && (i != 2 || j != 6) && (i != 3 || j != 6) && (i != 4 || j != 6) && (i != 2 || j != 6) && (i != 5 || j != 6) && (i != 6 || j != 6) && (i != 7 || j != 6) && (i != 8 || j != 6) && (i != 9 || j != 6) && (i != 10 || j != 6) && (i != 10 || j != 7) && (i != 10 || j != 8) && (i != 10 || j != 9) && (i != 10 || j != 10) && (i != 10 || j != 11)) {
                    tab[i][j] = caractere;
                }

            }
        }
    }

    public static void afficheGrille(int[][] tab, int n, int m) {
        int i;
        int j;
        System.out.print(tab[0][0]);
        for (i = 0; i < n; i++) {
            for (j = 0; j < m; j++) {
                System.out.print(tab[i][j] + "\t");

            }
            System.out.println();
        }
    }

    public static void genereNbAleat() {

        for (int i = 0; i < 10; i++) {
            Random r = new Random();
            int valeur = 1 + r.nextInt(37 - 1);
            System.out.println(valeur);
        }

    }

    public static void genereTabNbAleat() {

        //char[][] tabNbAleat = {{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38},{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30}}; 
        int[][] tabNbAleat = {{0, 1, 2, 3}, {4, 5, 6, 7}, {8, 9, 10, 11}};
        System.out.println(tabNbAleat[1][1]);
        afficheGrille(tabNbAleat, 3, 4);
    }

    public static void genereNbAleat2() {

        char[][] tabNbAleat = new char[2][39];
        int i;
        int j;
        char caractere = '#';
        for (i = 0; i < 2; i++) {
            for (j = 0; j < 39; j++) {
                tabNbAleat[i][j] = caractere;
            }

        }
     /*   afficheGrille(tabNbAleat, 2, 39);*/
    }
}
   

