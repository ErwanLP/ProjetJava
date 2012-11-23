package projetjava;

import java.util.Scanner;

/*import java.util.Random;*/
/*int valeur = 1 + r.nextInt(37 - 1);*/
public class ProjetJava {

    static int compt = 0;

    public static void main(String[] args) {

        System.out.println("-----------------------------------------------------------");
        System.out.println("STREAM");
        System.out.println("Par Erwan Le Poder et Florian Migot");
        menuPrincipale();

    }

    public static void menuPrincipale() {

        Scanner sc = new Scanner(System.in);
        System.out.println("-----------------------------------------------------------");
        System.out.println("MENU");
        System.out.println("1-jouer");
        System.out.println("2-deux");
        System.out.println("3-trois");
        System.out.println("Votre choix ? (automatiquement 1 pour les tests)");
        // int choix = sc.nextInt();
        int choix = 1;
        System.out.println("Votre choix de menu est le\t" + choix);
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
        // reboucler ici
        Affichertab(tab);
        NombreAleat();
        Placement();
    }

    public static void GenererTab(char[][] tab, Position pCour) {

        // Systeme de comptage ne pas supprimer
        /*System.out.println(pCour);
         System.out.println(compt);
         compt++;*/
        if (pCour.x != 10 || pCour.y != 9) {
            tab[pCour.x][pCour.y] = '*';
            GenererTab(tab, Position.suivant(pCour));

        }
    }

    public static void Affichertab(char[][] tab) {

        System.out.println("-----------------------------------------------------------");
        System.out.println("VOTRE GRILLE ACTUELLE:");
        // be careful affiche pas dans le bon sens mais ya de l'idée
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                System.out.print(tab[i][j] + "\t");
            }
            System.out.println();
        }

    }

    public static void NombreAleat() {

        Scanner sc = new Scanner(System.in);
        System.out.println("-----------------------------------------------------------");
        System.out.println("TIRAGE JETON:");
        //Jeton j = new Jeton();
        //int valeurJeton = j.valeur;
        // bon ca marchepas,  imaginons que le chiffre soit 5
        System.out.println("Le jeton tiré est le :\t" + "5");
    }

    public static void Placement() {

        Scanner sc = new Scanner(System.in);
        System.out.println("-----------------------------------------------------------");
        System.out.println("SELECTION EMPLACEMENT:");
        System.out.println("Choix Abscisse du Jeton : ");
        int choixAbscisse = sc.nextInt();
        System.out.println("Choix Ordonne du Jeton");
        int choixOrdonnee = sc.nextInt();
        System.out.println("Votre choix de placcement est\t Abscisse:\t" + choixAbscisse + "\t Ordonnee\t" + choixOrdonnee);

    }
}
