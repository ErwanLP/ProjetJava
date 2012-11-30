package projetjava;

import java.util.Scanner;

public class ProjetJava {

    // INITIALISATION VARIABLE
    static int compt = 0;
    static int longueurScore = 0;
    static int[] tabScore = new int[21];

    public static void main(String[] args) {

        /* Fonction Principale*/
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("STREAM");
        System.out.println("Par Erwan Le Poder et Florian Migot");
        menuPrincipale();

    }

    public static void menuPrincipale() {

        /*Fonction du menu principale*/
        Scanner sc = new Scanner(System.in);
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("MENU");
        System.out.println("1-jouer");
        System.out.println("2-deux"); // regle du jeux
        System.out.println("3-trois");
        System.out.println("Votre choix ?");// automatiquement 1 pour les tests
        // int choix = sc.nextInt();
        int choix = 1;
        switch (choix) {
            case 1:
                menuJouer();
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
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("MENU JOUER");
        System.out.println("1-Grille Basique");
        System.out.println("2-Grille Boucle"); // regle du jeux
        System.out.println("3-Grille Double Boucle");
        System.out.println("4-Grille Surface");
        System.out.println("Votre choix ?");// automatiquement 1 pour les tests
        // int choix = sc.nextInt();
        int choix = 1;
        switch (choix) {
            case 1:
                jouerGrille1();
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

    public static void jouerGrille1() {

        /*Fonction d'initialisation et de traitement de la grille 1
         * qui appelle des fonctions valables pour toute les grilles
         */
        String[][] tab = new String[10][11]; //grille pour la grille 1
        Position p = new Position(); // position pour la grille 1
        do {
            netoyerTab(tab);
            genererTab(tab, p);
            // reboucler ici
            do {
                affichertab(tab);
                nombreAleatPlacement(tab, p);
            } while (verifTab(tab));
            // la grille est finite on commence a compter les point
            affichertab(tab);
            System.out.println("la grille est finite on commence a compter les points");
            comptagePoint(tab, p, tabScore);
        } while (rejouer());

    }

    public static boolean rejouer() {
        Scanner sc = new Scanner(System.in);
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("REJOUER");
        System.out.println("1-oui");
        System.out.println("2-non");
        System.out.println("Votre choix ?");
        int choix = sc.nextInt();
        if (choix == 1) {
            Jeton.reinitialisertabNbAleat(); //sert a reinyinialiter les jeton car sans ca il y pas asser de jeton pour faire les 2 truc
            // de tout facon une jeux de jeton part partie:
            // il fau netoyer le score <<<<<<<<<<<<<<< A FAIRE
            reinitialisertabScore(tabScore);
            return true;
        } else {
            return false;


        }

    }

    public static void netoyerTab(String[][] tab) {

        /*Fonction qui reinitialise le tableau*/
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                tab[i][j] = " ";
            }
        }
    }

    public static void genererTab(String[][] tab, Position pCour) {

        /*Fontion qui place des * pour generer la grille*/
        if (pCour.x != 99 || pCour.y != 99) {
            tab[pCour.y][pCour.x] = "*";  // logique pourquoi inversé : abcsisse = collone 
            genererTab(tab, Position.suivant(pCour));

        }
    }

    public static void affichertab(String[][] tab) {

        /*Fonction d'affichege de la grille*/
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("VOTRE GRILLE ACTUELLE:");
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                System.out.print(tab[i][j] + "\t");
            }
            System.out.println();
            System.out.println();
            System.out.println();
        }
    }

    public static void nombreAleatPlacement(String[][] tab, Position p) {

        //NBALEAT
        Scanner sc = new Scanner(System.in);
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("TIRAGE JETON:");
        Jeton j = new Jeton();
        int valeurJeton = j.valeur;
        // bon ca marche pas mais ne pas sup (a poursuivre),  imaginons que le chiffre soit 5
        // mail envoyer a manceny pour de l'aide
        // en fait c'ets bon jai trouvé tout seul ca marche
        // int valeurJeton = 5;
        System.out.println("Le jeton tiré est le :\t" + valeurJeton);
        //CHOIX PLACEMENT
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("SELECTION EMPLACEMENT:");
        int choixAbscisse;
        int choixOrdonnee;
        do {
            System.out.println("Choix Abscisse du Jeton : ");
            choixAbscisse = sc.nextInt();
            System.out.println("Choix Ordonne du Jeton");
            choixOrdonnee = sc.nextInt();
            //TRAITEMENT PLACEMENT
        } while ((!verifPosition(choixAbscisse, choixOrdonnee, p)) || (tab[choixOrdonnee][choixAbscisse] != "*"));/*condition si c'est dans la grille et que il n'y est aps deja un nombre (en test)*/

        tab[choixOrdonnee][choixAbscisse] = String.valueOf(valeurJeton);



    }

    public static boolean verifTab(String[][] tab) {

        /*Foncion qui sert a savoir si la grille est remplie*/
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                if (tab[i][j] == "*") {
                    return true;

                }
            }
        }
        return false;


    }

    public static void comptagePoint(String[][] tab, Position pCour, int[] tabScore) {

        /*Fonction qui sert a compter le score de la table*/
        if (pCour.x != 99 || pCour.y != 99) {

            if (Integer.parseInt(tab[pCour.y][pCour.x]) <= Integer.parseInt(tab[Position.suivant(pCour).y][Position.suivant(pCour).x])) {
                longueurScore++;
                comptagePoint(tab, Position.suivant(pCour), tabScore);

            } else {
                tabScore[longueurScore + 1]++;
                longueurScore = 0;
                comptagePoint(tab, Position.suivant(pCour), tabScore);
            }



        } else {
            tabScore[longueurScore + 1]++;
            longueurScore = 0;
            int pointScore = tabScore[2] * 1 + tabScore[3] * 3 + tabScore[4] * 5 + tabScore[5] * 7 + tabScore[6] * 9 + tabScore[7] * 11 + tabScore[8] * 15 + tabScore[9] * 20 + tabScore[10] * 25 + tabScore[11] * 30 + tabScore[12] * 35 + tabScore[13] * 40 + tabScore[14] * 50 + tabScore[15] * 60 + tabScore[16] * 70 + tabScore[17] * 85 + tabScore[18] * 100 + tabScore[19] * 150 + tabScore[20] * 300;
            System.out.println(pointScore);
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
    
    public static void remplissageRobot (){
        
        
    }
    
    
}
