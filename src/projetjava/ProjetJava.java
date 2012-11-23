package projetjava;

import java.util.Scanner;

/*import java.util.Random;*/
/*int valeur = 1 + r.nextInt(37 - 1);*/
public class ProjetJava {

    static int compt = 0;

    public static void main(String[] args) {

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
        // int choix = sc.nextInt();
        int choix = 1;
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

    public static void jouer() {


        char[][] tab = new char[10][11];
        Position p = new Position();
        GenererTab(tab, p);
        Affichertab(tab);
    }

    public static void GenererTab(char[][] tab, Position pCour) {
        /*System.out.println(pCour);
         System.out.println(compt);
         compt++;*/
        if (pCour.x != 10 || pCour.y != 9) {
            tab[pCour.x][pCour.y] = '*';
            GenererTab(tab, Position.suivant(pCour));

        }
    }

    public static void Affichertab(char[][] tab) {
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                System.out.print(tab[i][j] + "\t");

            }
            System.out.println();

        }


    }
}
