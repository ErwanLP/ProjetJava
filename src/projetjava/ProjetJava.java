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
        System.out.println("1-jouer en mode automatique");
        System.out.println("2-jouer en mode manuelle");
        System.out.println("3-règle du jeux");
        System.out.println("4-quitter");
        System.out.println("Votre choix :");
        int choix = sc.nextInt();
        switch (choix) {
            case 1:
                menuJouer(0);
                menuPrincipale();
                break;
            case 2:
                System.out.println("Choisir votre graine : ");
                long choixSeed = sc.nextLong();
                menuJouer(choixSeed);
                menuPrincipale();
                break;
            case 3:
                //trois();
                break;
            case 4:
                System.out.println("Fin du jeux");
                System.out.println("-----------------------------------------------------------------------------------------------------");
                break;

            default:
                menuPrincipale();
                break;
        }

    }

    public static void menuJouer(long seed) {

        /*Fonction du menu si le joueur a decider de jouer
         * gestion du choix de la grille
         */
        System.out.println(seed);
        Scanner sc = new Scanner(System.in);
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println("MENU JOUER");
        System.out.println("1-Grille Basique");
        System.out.println("2-Grille Boucle");
        System.out.println("3-Grille Double Boucle");
        System.out.println("4-Grille Surface");
        System.out.println("5-Menu principale");
        System.out.println("Votre choix :");
        int choix = sc.nextInt();
        if (choix > 0 && choix < 5) {
            jouerGrille(choix, seed);
            menuJouer(seed);
        } else {
            menuPrincipale();

        }


    }

    public static void jouerGrille(int nbGrille, long seed) {

        /*Fonction d'initialisation et de traitement de la grille 
         * qui appelle des fonctions valables pour toute les grilles
         */
        Grille objettab = new Grille(nbGrille);
        ListePos lp = new ListePos(nbGrille);

        do {
            netoyerTab(objettab);
            genererTab(objettab, lp);
            Jeton.reinitialisertabNbAleat();
            reinitialisertabScore(tabScore);
            Random r;
            if (seed == 0) {
                r = new Random(System.currentTimeMillis());
            } else {
                r = new Random(seed);
            }
            longueurScore = 0;
            // reboucler ici



            do {
                affichertab(objettab);
                int valeurJeton = nombreAleat(r);
                //Placement(objettab, lp, valeurJeton);
                PlacementRobot(objettab, lp, valeurJeton);
            } while (verifTab(objettab));
            // la grille est finite on commence a compter les point
            affichertab(objettab);
            System.out.println("la grille est finie on commence a compter les points");
            //comptagePoint(objettab, lp, tabScore);
            comptagePoint2(objettab, lp, tabScore);
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

    public static void genererTab(Grille objettab, /*Position pCour*/ ListePos lpCour) {

        /*Fontion qui place des □ pour generer la grille*/

        if (lpCour.x != 0 || lpCour.y != 0) {
            objettab.tab[lpCour.y][lpCour.x] = "□";  // logique pourquoi inversé : abcsisse = collone 
            genererTab(objettab, /*Position.suivant(pCour)*/ lpCour.suivant);

        }
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

    public static int nombreAleat(Random r) {

        //NBALEAT
        Scanner sc = new Scanner(System.in);
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println("TIRAGE JETON:");
        Jeton j = new Jeton(r);
        int valeurJeton = j.valeur;
        // bon ca marche pas mais ne pas sup (a poursuivre),  imaginons que le chiffre soit 5
        // mail envoyer a manceny pour de l'aide
        // en fait c'ets bon jai trouvé tout seul ca marche
        // int valeurJeton = 5;
        if (valeurJeton != 99) {
            System.out.println("Le jeton tiré est le :\t" + valeurJeton);
        } else {
            System.out.println("Le jeton tiré est le Joker, choisisser une valeur:");
            //verif si le nombre est bien.
            valeurJeton = sc.nextInt();
        }
        return valeurJeton;



    }

    public static void Placement(Grille objettab, /*Position p*/ ListePos lp, int valeurJeton) {


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
        } while ((!verifPosition(choixAbscisse, choixOrdonnee, lp)) || (objettab.tab[choixOrdonnee][choixAbscisse] != "□"));/*condition si c'est dans la grille et que il n'y est aps deja un nombre (en test)*/

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

    public static void comptagePoint2(Grille objettab, ListePos lpCour, int[] tabScore) {
        int[] grilleligne = new int[20];
        int i = 0;
        while (lpCour.x != 0 || lpCour.y != 0) {

            grilleligne[i] = Integer.parseInt(objettab.tab[lpCour.y][lpCour.x]);
            System.out.print(i + "-" + grilleligne[i] + "\t");
            i++;
            lpCour = lpCour.suivant;
        }
        Score s = new Score(grilleligne);
        System.out.println(s);


        //Score s = new Score();






    }

    public static void comptagePoint(Grille objettab, /*Position pCour*/ ListePos lpCour, int[] tabScore) {

        /*Fonction qui sert a compter le score de la table*/
        // if (pCour.x != 12 || pCour.y != 11) {
        //if (Position.suivant(pCour).x != 0 || Position.suivant(pCour).y != 0) {
        if (lpCour.suivant.x != 0 || lpCour.suivant.y != 0) {
            /*Ne parche pas pour toute les grille :'( 
             * car on a besoin d ela penutieme case
             * */


            if (Integer.parseInt(objettab.tab[lpCour.y][lpCour.x]) <= Integer.parseInt(objettab.tab[lpCour.suivant.y][lpCour.suivant.x])) {
                longueurScore++;
                comptagePoint(objettab, lpCour.suivant, tabScore);

            } else {
                tabScore[longueurScore + 1]++;
                longueurScore = 0;
                comptagePoint(objettab, lpCour.suivant, tabScore);
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

    public static boolean verifPosition(int choixAbscisse, int choixOrdonnee, /*Position pCour*/ ListePos lpCour) {

        /*Fonction qui verifie que la choix du joueur est bien dans la grille*/
        if (lpCour.x != 0 || lpCour.y != 0) {
            if (lpCour.x == choixAbscisse && lpCour.y == choixOrdonnee) {
                return true;

            } else {
                return verifPosition(choixAbscisse, choixOrdonnee, lpCour.suivant);

            }
        } else {
            return false;
        }

    }

    public static void PlacementRobot(Grille objettab, /*Position p*/ ListePos lp, int valeurJeton) {




        int choixAbscisse;
        int choixOrdonnee;
        Random r = new Random();

        do {
            choixAbscisse = 2 + r.nextInt(11);
            choixOrdonnee = 2 + r.nextInt(10);
        } while ((!verifPosition(choixAbscisse, choixOrdonnee, lp)) || (objettab.tab[choixOrdonnee][choixAbscisse] != "□"));
        /*condition si c'est dans la grille et que il n'y est aps deja un nombre (en test)*/

        objettab.tab[choixOrdonnee][choixAbscisse] = String.valueOf(valeurJeton);



    }
}
