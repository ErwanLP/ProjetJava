package projetjava;

import java.util.Random;
import java.util.Scanner;

public class ProjetJava {

    // INITIALISATION VARIABLE
    static int compt = 0;
    static int longueurScore = 0;
    static int[] tabScore = new int[21];
    static int scoreTotal = 0;

    public static void main(String[] args) {

        /* Fonction Principale*/
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println("STREAM");
        System.out.println("Par Erwan Le Poder et Florian Migot");
        menuPrincipale();

    }

    public static void menuPrincipale() {

        /*Fonction du menu principale*/
        Scanner sc = new Scanner(System.in);
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println("MENU");
        System.out.println("1-jouer");
        System.out.println("2-règle du jeux"); // regle du jeux
        System.out.println("3-quitter");
        System.out.println("Votre choix ?");// automatiquement 1 pour les tests
        //int choix = sc.nextInt();
        int choix = 1;
        switch (choix) {
            case 1:
                menuJouer();
                menuPrincipale();
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

    public static void menuJouer() {

        /*Fonction du menu si le joueur a decider de jouer
         * gestion du choix de la grille
         */
        Scanner sc = new Scanner(System.in);
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println("MENU JOUER");
        System.out.println("1-Grille Basique");
        System.out.println("2-Grille Boucle"); // regle du jeux
        System.out.println("3-Grille Double Boucle");
        System.out.println("4-Grille Surface");
        System.out.println("5-Menu principale");
        System.out.println("Votre choix ?");// automatiquement 1 pour les tests
        //int choix = sc.nextInt();
        int choix = 1;
        jouerGrille(choix);
        menuJouer();



    }

    public static void jouerGrille(int nbGrille) {

        /*Fonction d'initialisation et de traitement de la grille 
         * qui appelle des fonctions valables pour toute les grilles
         */
        Grille objettab = new Grille(nbGrille); //grille pour la grille 
        Position p = new Position(nbGrille); // position pour la grille 



        do {
            netoyerTab(objettab);
            genererTab(objettab, p);
            Jeton.reinitialisertabNbAleat(); //sert a reinyinialiter les jeton car sans ca il y pas asser de jeton pour faire les 2 truc
            // de tout facon une jeux de jeton part partie:
            // il fau netoyer le score <<<<<<<<<<<<<<< A FAIRE
            reinitialisertabScore(tabScore);
            longueurScore = 0;
            // reboucler ici


            for (int i = 0; i < objettab.longueur; i++) {
                objettab.tab[1][i] = ".";
            }
            for (int j = 0; j < objettab.hauteur; j++) {
                objettab.tab[j][1] = ".";

            }
            for (int k = 1; k < objettab.hauteur - 1; k++) {

                objettab.tab[objettab.tab.length - k][0] = String.valueOf(k);
            }
            for (int l = 1; l < objettab.longueur - 1; l++) {

                objettab.tab[0][l + 1] = String.valueOf(l);
            }
            do {
                affichertab(objettab);
                //Placement(tab, p, nombreAleat());
                PlacementRobot(objettab, p, nombreAleat());
            } while (verifTab(objettab));
            // la grille est finite on commence a compter les point
            affichertab(objettab);
            System.out.println("la grille est finite on commence a compter les points");
            comptagePoint(objettab, p, tabScore);
        } while (rejouer());

    }

    public static boolean rejouer() {
        Scanner sc = new Scanner(System.in);
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println("REJOUER");
        System.out.println("1-oui");
        System.out.println("2-non");
        System.out.println("Votre choix ?");
        int choix = sc.nextInt();
        if (choix == 1) {
            return true;
        } else {
            return false;


        }

    }

    public static void netoyerTab(Grille objettab) {

        /*Fonction qui reinitialise le tableau*/
        for (int i = 0; i < objettab.tab.length; i++) {
            for (int j = 0; j < objettab.tab[i].length; j++) {
                objettab.tab[i][j] = " ";
            }
        }
    }

    public static void genererTab(Grille objettab, Position pCour) {

        /*Fontion qui place des □ pour generer la grille*/
        if (pCour.x != 99 || pCour.y != 99) {
            objettab.tab[pCour.y][pCour.x] = "□";  // logique pourquoi inversé : abcsisse = collone 
            genererTab(objettab, Position.suivant(pCour));

        }
    }

    public static void affichertab(Grille objettab) {

        /*Fonction d'affichege de la grille*/
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println("VOTRE GRILLE ACTUELLE:");
        for (int i = 0; i < objettab.tab.length; i++) {
            for (int j = 0; j < objettab.tab[i].length; j++) {
                System.out.print(objettab.tab[i][j] + "\t");
            }
            System.out.println();
            System.out.println();
            System.out.println();
        }
    }

    public static int nombreAleat() {

        //NBALEAT
        Scanner sc = new Scanner(System.in);
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println("TIRAGE JETON:");
        Jeton j = new Jeton();
        int valeurJeton = j.valeur;
        // bon ca marche pas mais ne pas sup (a poursuivre),  imaginons que le chiffre soit 5
        // mail envoyer a manceny pour de l'aide
        // en fait c'ets bon jai trouvé tout seul ca marche
        // int valeurJeton = 5;
        System.out.println("Le jeton tiré est le :\t" + valeurJeton);
        return valeurJeton;



    }

    public static void Placement(Grille objettab, Position p, int valeurJeton) {


        //CHOIX PLACEMENT
        Scanner sc = new Scanner(System.in);
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println("SELECTION EMPLACEMENT:");
        int choixAbscisse;
        int choixOrdonnee;
        do {
            System.out.println("Choix Abscisse du Jeton : ");
            choixAbscisse = sc.nextInt() + 1;
            System.out.println("Choix Ordonne du Jeton");
            choixOrdonnee = objettab.tab.length - sc.nextInt();
            //tab.length - k
            //TRAITEMENT PLACEMENT
        } while ((!verifPosition(choixAbscisse, choixOrdonnee, p)) || (objettab.tab[choixOrdonnee][choixAbscisse] != "□"));/*condition si c'est dans la grille et que il n'y est aps deja un nombre (en test)*/

        objettab.tab[choixOrdonnee][choixAbscisse] = String.valueOf(valeurJeton);



    }

    public static boolean verifTab(Grille objettab) {

        /*Foncion qui sert a savoir si la grille est remplie*/
        for (int i = 0; i < objettab.tab.length; i++) {
            for (int j = 0; j < objettab.tab[i].length; j++) {
                if (objettab.tab[i][j] == "□") {
                    return true;

                }
            }
        }
        return false;


    }

    public static void comptagePoint(Grille objettab, Position pCour, int[] tabScore) {

        /*Fonction qui sert a compter le score de la table*/
        // if (pCour.x != 12 || pCour.y != 11) {
        if (Position.suivant(pCour).x != 99 || Position.suivant(pCour).y != 99) {
            /*Ne parche pas pour toute les grille :'( 
             * car on a besoin d ela penutieme case
             * */


            if (Integer.parseInt(objettab.tab[pCour.y][pCour.x]) <= Integer.parseInt(objettab.tab[Position.suivant(pCour).y][Position.suivant(pCour).x])) {
                longueurScore++;
                comptagePoint(objettab, Position.suivant(pCour), tabScore);

            } else {
                tabScore[longueurScore + 1]++;
                longueurScore = 0;
                comptagePoint(objettab, Position.suivant(pCour), tabScore);
            }



        } else {
            tabScore[longueurScore + 1]++;
            longueurScore = 0;
            int pointScore = tabScore[2] * 1 + tabScore[3] * 3 + tabScore[4] * 5 + tabScore[5] * 7 + tabScore[6] * 9 + tabScore[7] * 11 + tabScore[8] * 15 + tabScore[9] * 20 + tabScore[10] * 25 + tabScore[11] * 30 + tabScore[12] * 35 + tabScore[13] * 40 + tabScore[14] * 50 + tabScore[15] * 60 + tabScore[16] * 70 + tabScore[17] * 85 + tabScore[18] * 100 + tabScore[19] * 150 + tabScore[20] * 300;
            System.out.println("Score de la partie:" + pointScore);
            scoreTotal = scoreTotal + pointScore;
            System.out.println("Score total:" + scoreTotal);

        }

    }

    public static void reinitialisertabScore(int[] tabScore) {

        /*Fonction qui permet de reinitialiser la table des score*/
        for (int i = 0; i < tabScore.length; i++) {
            tabScore[i] = 0;

        }


    }

    public static boolean verifPosition(int choixAbscisse, int choixOrdonnee, Position pCour) {

        /*Fonction qui verifie que la choix du joueur est bien dans la grille*/
        if (pCour.x != 99 || pCour.y != 99) {
            if (pCour.x == choixAbscisse && pCour.y == choixOrdonnee) {
                return true;

            } else {
                return verifPosition(choixAbscisse, choixOrdonnee, Position.suivant(pCour));

            }
        } else {
            return false;
        }

    }

    public static void PlacementRobot(Grille objettab, Position p, int valeurJeton) {




        int choixAbscisse;
        int choixOrdonnee;
        Random r = new Random();

        do {
            choixAbscisse = 2 + r.nextInt(11);
            choixOrdonnee = 2 + r.nextInt(10);
        } while ((!verifPosition(choixAbscisse, choixOrdonnee, p)) || (objettab.tab[choixOrdonnee][choixAbscisse] != "□"));
        /*condition si c'est dans la grille et que il n'y est aps deja un nombre (en test)*/

        objettab.tab[choixOrdonnee][choixAbscisse] = String.valueOf(valeurJeton);



    }
}
