package projetjava;

import java.util.Random;
import java.util.Scanner;

public class ProjetJava {

    /* joker
     * grille 3 et 4 ->ok
     * calcule score grile 2
     * extention + ou -
     */
    //a b a lka place de 1/2 (?)
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
                regles();
                menuPrincipale();
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
// Si la seed = 0, on ne peux pas générer un tirage de jetons aléatoire
            } else {
                r = new Random(seed);
            }
// L'utilisateur rentre une seed et génére donc un tirage de jeton aléatoire
            longueurScore = 0;

            do {
                affichertab(objettab);
// On affiche la grille vierge
                int valeurJeton = nombreAleat(r);
// On affecte un jeton du tabeau des jetons à valeurJeton
                PlacementRobot(objettab, lp, valeurJeton);
            } while (verifTab(objettab));
// Tant que la grille n'est pas remplie, on place dans une case d'une grille donnée le jeton tiré
            affichertab(objettab);
// Puis on affiche la grille complétée
            System.out.println("la grille est finie on commence a compter les points");
            comptagePoint2(objettab, lp, nbGrille);
// On calcul le score tant qu'il continue de jouer
        } while (rejouer());

    }

    public static boolean rejouer() {
        Scanner sc = new Scanner(System.in);
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println("REJOUER");
        System.out.println("1-Oui");
        System.out.println("2-Non");
        System.out.println("Votre choix ?");
        int choix = sc.nextInt();
        if (choix == 1) {
            return true;
        } else {
            return false;


        }

    }

    public static void netoyerTab(Grille objettab) {

        /*Fonction qui rénitialise le tableau*/
        for (int i = 0; i < objettab.tab.length; i++) {
            for (int j = 0; j < objettab.tab[i].length; j++) {
                objettab.tab[i][j] = " ";
            }
        }
    }

    public static void genererTab(Grille objettab, ListePos lpCour) {

        /*Fontion qui place des □ pour generer la grille*/

        if (lpCour.x != 0 || lpCour.y != 0) {
            objettab.tab[lpCour.y][lpCour.x] = "□";  // logique pourquoi inversé : abcsisse = collone 
            genererTab(objettab, lpCour.suivant);

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

        /* Fonction qui va chercher un nombre aléatoire */
        Scanner sc = new Scanner(System.in);
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println("TIRAGE JETON:");
        Jeton j = new Jeton(r);
        // On tire un nouveau jeton
        int valeurJeton = j.valeur;
        // On regarde si le jeton est le joker, si oui on demande au joueur le valeur du jeton qu'il veut
        if (valeurJeton != 99) {
            System.out.println("Le jeton tiré est le :\t" + valeurJeton);
        } else {
            System.out.println("Le jeton tiré est le Joker, choisisser une valeur:");
            // Vérification si le nombre est bien.
            valeurJeton = sc.nextInt();
        }
        return valeurJeton;



    }

    public static void Placement(Grille objettab, ListePos lp, int valeurJeton) {


        //CHOIX PLACEMENT
        Scanner sc = new Scanner(System.in);
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println("SELECTION EMPLACEMENT:");
        int choixAbscisse;
        int choixOrdonnee;
        do {
            System.out.println("Choix de l'Abscisse du Jeton : ");
            choixAbscisse = sc.nextInt() + 1;
            System.out.println("Choix de l'Ordonnée du Jeton");
            choixOrdonnee = objettab.tab.length - sc.nextInt();
            //tab.length - k
            //TRAITEMENT PLACEMENT
            // On regarde que l'enplacement choisi est dans la grille et que cet emplacement en vide (un jeton n'a pas déja été placé)
        } while ((!verifPosition(choixAbscisse, choixOrdonnee, lp)) || (objettab.tab[choixOrdonnee][choixAbscisse] != "□"));
        // On place le jeton
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

    public static void comptagePoint2(Grille objettab, ListePos lpCour, int nbGrille) {

        switch (nbGrille) {
            case 1:

                int[] grilleligne = new int[20];
                int i = 0;
                while (lpCour.x != 0 || lpCour.y != 0) {

                    grilleligne[i] = Integer.parseInt(objettab.tab[lpCour.y][lpCour.x]);
                    System.out.print(i + "-" + grilleligne[i] + "\t");
                    i++;
                    lpCour = lpCour.suivant;
                }
                Score s = new Score(grilleligne);
                System.out.println();
                System.out.println(s.value());

                break;
            case 2:

                int[] grilleLigneGauche = new int[5];
                int[] grilleLigneBas = new int[3];
                int[] grilleLigneGaucheBas = new int[9];
                int[] grilleLigneHautDroite = new int[13];
                int[] grilleLigneGaucheHaut = new int[19];
                int[] grilleLigneBasHaut = new int[17];
                int[] grilleLigneBasDroite = new int[17];
                int[] grilleLigneGaucheDroite = new int[19];
                int[] grilleligneBoucle = new int[14];

                grilleLigneGauche[0] = Integer.parseInt(objettab.tab[6][2]);
                grilleLigneGauche[1] = Integer.parseInt(objettab.tab[6][3]);
                grilleLigneGauche[2] = Integer.parseInt(objettab.tab[6][4]);
                grilleLigneGauche[3] = Integer.parseInt(objettab.tab[6][5]);
                grilleLigneGauche[4] = Integer.parseInt(objettab.tab[6][6]);

                grilleLigneBas[0] = Integer.parseInt(objettab.tab[9][7]);
                grilleLigneBas[1] = Integer.parseInt(objettab.tab[8][7]);
                grilleLigneBas[2] = Integer.parseInt(objettab.tab[7][7]);

                grilleLigneGaucheBas[0] = Integer.parseInt(objettab.tab[6][2]);
                grilleLigneGaucheBas[1] = Integer.parseInt(objettab.tab[6][3]);
                grilleLigneGaucheBas[2] = Integer.parseInt(objettab.tab[6][4]);
                grilleLigneGaucheBas[3] = Integer.parseInt(objettab.tab[6][5]);
                grilleLigneGaucheBas[4] = Integer.parseInt(objettab.tab[6][6]);
                grilleLigneGaucheBas[5] = Integer.parseInt(objettab.tab[6][7]);
                grilleLigneGaucheBas[6] = Integer.parseInt(objettab.tab[7][7]);
                grilleLigneGaucheBas[7] = Integer.parseInt(objettab.tab[8][7]);
                grilleLigneGaucheBas[8] = Integer.parseInt(objettab.tab[9][7]);

                grilleLigneHautDroite[0] = Integer.parseInt(objettab.tab[5][7]);
                grilleLigneHautDroite[1] = Integer.parseInt(objettab.tab[4][7]);
                grilleLigneHautDroite[2] = Integer.parseInt(objettab.tab[3][7]);
                grilleLigneHautDroite[3] = Integer.parseInt(objettab.tab[2][7]);
                grilleLigneHautDroite[4] = Integer.parseInt(objettab.tab[2][8]);
                grilleLigneHautDroite[5] = Integer.parseInt(objettab.tab[2][9]);
                grilleLigneHautDroite[6] = Integer.parseInt(objettab.tab[2][10]);
                grilleLigneHautDroite[7] = Integer.parseInt(objettab.tab[3][10]);
                grilleLigneHautDroite[8] = Integer.parseInt(objettab.tab[4][10]);
                grilleLigneHautDroite[9] = Integer.parseInt(objettab.tab[5][10]);
                grilleLigneHautDroite[10] = Integer.parseInt(objettab.tab[6][10]);
                grilleLigneHautDroite[11] = Integer.parseInt(objettab.tab[6][9]);
                grilleLigneHautDroite[12] = Integer.parseInt(objettab.tab[6][8]);

                grilleLigneGaucheHaut[0] = Integer.parseInt(objettab.tab[6][2]);
                grilleLigneGaucheHaut[1] = Integer.parseInt(objettab.tab[6][3]);
                grilleLigneGaucheHaut[2] = Integer.parseInt(objettab.tab[6][4]);
                grilleLigneGaucheHaut[3] = Integer.parseInt(objettab.tab[6][5]);
                grilleLigneGaucheHaut[4] = Integer.parseInt(objettab.tab[6][6]);
                grilleLigneGaucheHaut[5] = Integer.parseInt(objettab.tab[6][7]);
                grilleLigneGaucheHaut[6] = Integer.parseInt(objettab.tab[5][7]);
                grilleLigneGaucheHaut[7] = Integer.parseInt(objettab.tab[4][7]);
                grilleLigneGaucheHaut[8] = Integer.parseInt(objettab.tab[3][7]);
                grilleLigneGaucheHaut[9] = Integer.parseInt(objettab.tab[2][7]);
                grilleLigneGaucheHaut[10] = Integer.parseInt(objettab.tab[2][8]);
                grilleLigneGaucheHaut[11] = Integer.parseInt(objettab.tab[2][9]);
                grilleLigneGaucheHaut[12] = Integer.parseInt(objettab.tab[2][10]);
                grilleLigneGaucheHaut[13] = Integer.parseInt(objettab.tab[3][10]);
                grilleLigneGaucheHaut[14] = Integer.parseInt(objettab.tab[4][10]);
                grilleLigneGaucheHaut[15] = Integer.parseInt(objettab.tab[5][10]);
                grilleLigneGaucheHaut[16] = Integer.parseInt(objettab.tab[6][10]);
                grilleLigneGaucheHaut[17] = Integer.parseInt(objettab.tab[6][9]);
                grilleLigneGaucheHaut[18] = Integer.parseInt(objettab.tab[6][8]);

                grilleLigneBasHaut[0] = Integer.parseInt(objettab.tab[9][7]);
                grilleLigneBasHaut[1] = Integer.parseInt(objettab.tab[8][7]);
                grilleLigneBasHaut[2] = Integer.parseInt(objettab.tab[7][7]);
                grilleLigneBasHaut[3] = Integer.parseInt(objettab.tab[6][7]);
                grilleLigneBasHaut[4] = Integer.parseInt(objettab.tab[5][7]);
                grilleLigneBasHaut[5] = Integer.parseInt(objettab.tab[4][7]);
                grilleLigneBasHaut[6] = Integer.parseInt(objettab.tab[3][7]);
                grilleLigneBasHaut[7] = Integer.parseInt(objettab.tab[2][7]);
                grilleLigneBasHaut[8] = Integer.parseInt(objettab.tab[2][8]);
                grilleLigneBasHaut[9] = Integer.parseInt(objettab.tab[2][9]);
                grilleLigneBasHaut[10] = Integer.parseInt(objettab.tab[2][10]);
                grilleLigneBasHaut[11] = Integer.parseInt(objettab.tab[3][10]);
                grilleLigneBasHaut[12] = Integer.parseInt(objettab.tab[4][10]);
                grilleLigneBasHaut[13] = Integer.parseInt(objettab.tab[5][10]);
                grilleLigneBasHaut[14] = Integer.parseInt(objettab.tab[6][10]);
                grilleLigneBasHaut[15] = Integer.parseInt(objettab.tab[6][9]);
                grilleLigneBasHaut[16] = Integer.parseInt(objettab.tab[6][8]);

                grilleLigneBasDroite[0] = Integer.parseInt(objettab.tab[9][7]);
                grilleLigneBasDroite[1] = Integer.parseInt(objettab.tab[8][7]);
                grilleLigneBasDroite[2] = Integer.parseInt(objettab.tab[7][7]);
                grilleLigneBasDroite[3] = Integer.parseInt(objettab.tab[6][7]);
                grilleLigneBasDroite[4] = Integer.parseInt(objettab.tab[6][8]);
                grilleLigneBasDroite[5] = Integer.parseInt(objettab.tab[6][9]);
                grilleLigneBasDroite[6] = Integer.parseInt(objettab.tab[6][10]);
                grilleLigneBasDroite[7] = Integer.parseInt(objettab.tab[5][10]);
                grilleLigneBasDroite[8] = Integer.parseInt(objettab.tab[4][10]);
                grilleLigneBasDroite[9] = Integer.parseInt(objettab.tab[3][10]);
                grilleLigneBasDroite[10] = Integer.parseInt(objettab.tab[2][10]);
                grilleLigneBasDroite[11] = Integer.parseInt(objettab.tab[2][9]);
                grilleLigneBasDroite[12] = Integer.parseInt(objettab.tab[2][8]);
                grilleLigneBasDroite[13] = Integer.parseInt(objettab.tab[2][7]);
                grilleLigneBasDroite[14] = Integer.parseInt(objettab.tab[3][7]);
                grilleLigneBasDroite[15] = Integer.parseInt(objettab.tab[4][7]);
                grilleLigneBasDroite[16] = Integer.parseInt(objettab.tab[5][7]);

                grilleLigneGaucheDroite[0] = Integer.parseInt(objettab.tab[6][2]);
                grilleLigneGaucheDroite[1] = Integer.parseInt(objettab.tab[6][3]);
                grilleLigneGaucheDroite[2] = Integer.parseInt(objettab.tab[6][4]);
                grilleLigneGaucheDroite[3] = Integer.parseInt(objettab.tab[6][5]);
                grilleLigneGaucheDroite[4] = Integer.parseInt(objettab.tab[6][6]);
                grilleLigneGaucheDroite[5] = Integer.parseInt(objettab.tab[6][7]);
                grilleLigneGaucheDroite[6] = Integer.parseInt(objettab.tab[6][8]);
                grilleLigneGaucheDroite[7] = Integer.parseInt(objettab.tab[6][9]);
                grilleLigneGaucheDroite[8] = Integer.parseInt(objettab.tab[6][10]);
                grilleLigneGaucheDroite[9] = Integer.parseInt(objettab.tab[5][10]);
                grilleLigneGaucheDroite[10] = Integer.parseInt(objettab.tab[4][10]);
                grilleLigneGaucheDroite[11] = Integer.parseInt(objettab.tab[3][10]);
                grilleLigneGaucheDroite[12] = Integer.parseInt(objettab.tab[2][10]);
                grilleLigneGaucheDroite[13] = Integer.parseInt(objettab.tab[2][9]);
                grilleLigneGaucheDroite[14] = Integer.parseInt(objettab.tab[2][8]);
                grilleLigneGaucheDroite[15] = Integer.parseInt(objettab.tab[2][7]);
                grilleLigneGaucheDroite[16] = Integer.parseInt(objettab.tab[3][7]);
                grilleLigneGaucheDroite[17] = Integer.parseInt(objettab.tab[4][7]);
                grilleLigneGaucheDroite[18] = Integer.parseInt(objettab.tab[5][7]);

                grilleligneBoucle[0] = Integer.parseInt(objettab.tab[6][7]);
                grilleligneBoucle[1] = Integer.parseInt(objettab.tab[6][8]);
                grilleligneBoucle[2] = Integer.parseInt(objettab.tab[6][9]);
                grilleligneBoucle[3] = Integer.parseInt(objettab.tab[6][10]);
                grilleligneBoucle[4] = Integer.parseInt(objettab.tab[5][10]);
                grilleligneBoucle[5] = Integer.parseInt(objettab.tab[4][10]);
                grilleligneBoucle[6] = Integer.parseInt(objettab.tab[3][10]);
                grilleligneBoucle[7] = Integer.parseInt(objettab.tab[2][10]);
                grilleligneBoucle[8] = Integer.parseInt(objettab.tab[2][9]);
                grilleligneBoucle[9] = Integer.parseInt(objettab.tab[2][8]);
                grilleligneBoucle[10] = Integer.parseInt(objettab.tab[2][7]);
                grilleligneBoucle[11] = Integer.parseInt(objettab.tab[3][7]);
                grilleligneBoucle[12] = Integer.parseInt(objettab.tab[4][7]);
                grilleligneBoucle[13] = Integer.parseInt(objettab.tab[5][7]);

                Score sg = new Score(grilleLigneGauche);
                Score sb = new Score(grilleLigneBas);
                Score sgb = new Score(grilleLigneGaucheBas);
                Score shd = new Score(grilleLigneHautDroite);
                Score sgh = new Score(grilleLigneGaucheHaut);
                Score sbh = new Score(grilleLigneBasHaut);
                Score sbd = new Score(grilleLigneBasDroite);
                Score sgd = new Score(grilleLigneGaucheDroite);
                Score sblc1 = new Score(grilleligneBoucle);
                int[] grilleBoucleCour = rotation(grilleligneBoucle);
                Score sblc2 = new Score(grilleBoucleCour);
                grilleBoucleCour = rotation(grilleBoucleCour);
                Score sblc3 = new Score(grilleBoucleCour);
                grilleBoucleCour = rotation(grilleBoucleCour);
                Score sblc4 = new Score(grilleBoucleCour);
                grilleBoucleCour = rotation(grilleBoucleCour);
                Score sblc5 = new Score(grilleBoucleCour);
                grilleBoucleCour = rotation(grilleBoucleCour);
                Score sblc6 = new Score(grilleBoucleCour);
                grilleBoucleCour = rotation(grilleBoucleCour);
                Score sblc7 = new Score(grilleBoucleCour);
                grilleBoucleCour = rotation(grilleBoucleCour);
                Score sblc8 = new Score(grilleBoucleCour);
                grilleBoucleCour = rotation(grilleBoucleCour);
                Score sblc9 = new Score(grilleBoucleCour);
                grilleBoucleCour = rotation(grilleBoucleCour);
                Score sblc10 = new Score(grilleBoucleCour);
                grilleBoucleCour = rotation(grilleBoucleCour);
                Score sblc11 = new Score(grilleBoucleCour);
                grilleBoucleCour = rotation(grilleBoucleCour);
                Score sblc12 = new Score(grilleBoucleCour);
                grilleBoucleCour = rotation(grilleBoucleCour);
                Score sblc13 = new Score(grilleBoucleCour);
                grilleBoucleCour = rotation(grilleBoucleCour);
                Score sblc14 = new Score(grilleBoucleCour);


                int scoreBest = 0;
                int scoreCour;

                scoreCour = sgb.value() + shd.value();
                System.out.println(scoreCour);
                if (scoreCour > scoreBest) {
                    scoreBest = scoreCour;
                }
                scoreCour = sgh.value() + sb.value();
                 System.out.println(scoreCour);
                if (scoreCour > scoreBest) {
                    scoreBest = scoreCour;
                }
                scoreCour = sg.value() + sb.value() + shd.value();
                 System.out.println(scoreCour);
                if (scoreCour > scoreBest) {
                    scoreBest = scoreCour;
                }
                scoreCour = sgd.value() + sb.value();
                 System.out.println(scoreCour);
                if (scoreCour > scoreBest) {
                    scoreBest = scoreCour;
                }
                scoreCour = sbd.value() + sg.value();
                 System.out.println(scoreCour);
                if (scoreCour > scoreBest) {
                    scoreBest = scoreCour;
                }
                scoreCour = sbh.value() + sg.value();
                 System.out.println(scoreCour);
                if (scoreCour > scoreBest) {
                    scoreBest = scoreCour;
                }
                scoreCour = sblc1.value() + sg.value() + sb.value();
                 System.out.println(scoreCour);
                if (scoreCour > scoreBest) {
                    scoreBest = scoreCour;
                }
                scoreCour = sblc2.value() + sg.value() + sb.value();
                 System.out.println(scoreCour);
                if (scoreCour > scoreBest) {
                    scoreBest = scoreCour;
                }
                scoreCour = sblc3.value() + sg.value() + sb.value();
                 System.out.println(scoreCour);
                if (scoreCour > scoreBest) {
                    scoreBest = scoreCour;
                }
                scoreCour = sblc4.value() + sg.value() + sb.value();
                 System.out.println(scoreCour);
                if (scoreCour > scoreBest) {
                    scoreBest = scoreCour;
                }
                scoreCour = sblc5.value() + sg.value() + sb.value();
                 System.out.println(scoreCour);
                if (scoreCour > scoreBest) {
                    scoreBest = scoreCour;
                }
                scoreCour = sblc6.value() + sg.value() + sb.value();
                 System.out.println(scoreCour);
                if (scoreCour > scoreBest) {
                    scoreBest = scoreCour;
                }
                scoreCour = sblc7.value() + sg.value() + sb.value();
                 System.out.println(scoreCour);
                if (scoreCour > scoreBest) {
                    scoreBest = scoreCour;
                }
                scoreCour = sblc8.value() + sg.value() + sb.value();
                 System.out.println(scoreCour);
                if (scoreCour > scoreBest) {
                    scoreBest = scoreCour;
                }
                scoreCour = sblc9.value() + sg.value() + sb.value();
                 System.out.println(scoreCour);
                if (scoreCour > scoreBest) {
                    scoreBest = scoreCour;
                }
                scoreCour = sblc10.value() + sg.value() + sb.value();
                 System.out.println(scoreCour);
                if (scoreCour > scoreBest) {
                    scoreBest = scoreCour;
                }
                scoreCour = sblc11.value() + sg.value() + sb.value();
                 System.out.println(scoreCour);
                if (scoreCour > scoreBest) {
                    scoreBest = scoreCour;
                }
                scoreCour = sblc12.value() + sg.value() + sb.value();
                 System.out.println(scoreCour);
                if (scoreCour > scoreBest) {
                    scoreBest = scoreCour;
                }
                scoreCour = sblc13.value() + sg.value() + sb.value();
                 System.out.println(scoreCour);
                if (scoreCour > scoreBest) {
                    scoreBest = scoreCour;
                }
                scoreCour = sblc14.value() + sg.value() + sb.value();
                 System.out.println(sblc14.value());
                  System.out.println(sg.value());
                   System.out.println(sb.value());
                 System.out.println(scoreCour);
                if (scoreCour > scoreBest) {
                    scoreBest = scoreCour;
                }
                //
                System.out.println(scoreBest);



















                break;
            case 3:

                break;
            case 4:

                break;

        }















    }

    public static void comptagePoint(Grille objettab, ListePos lpCour, int[] tabScore) {


        /* Ancien comptage de point qui de répond pas au cahier des charges*/

        if (lpCour.suivant.x != 0 || lpCour.suivant.y != 0) {



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

        /*Fonction qui permet de reinitialiser la table des scores*/
        for (int i = 0; i < tabScore.length; i++) {
            tabScore[i] = 0;

        }


    }

    public static boolean verifPosition(int choixAbscisse, int choixOrdonnee, ListePos lpCour) {

        /*Fonction qui verifie que le choix du joueur est bien dans la grille*/
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

    public static void PlacementRobot(Grille objettab, ListePos lp, int valeurJeton) {


        // IA random pour tester le jeu;

        int choixAbscisse;
        int choixOrdonnee;
        Random r = new Random();

        do {
            choixAbscisse = 2 + r.nextInt(11);
            choixOrdonnee = 2 + r.nextInt(10);
        } while ((!verifPosition(choixAbscisse, choixOrdonnee, lp)) || (objettab.tab[choixOrdonnee][choixAbscisse] != "□"));
        /* Condition si c'est dans la grille et que il n'y est pas déjà un nombre (en test) */

        objettab.tab[choixOrdonnee][choixAbscisse] = String.valueOf(valeurJeton);



    }

    public static int[] rotation(int[] tab) {
        /* fonction qui sert a faire tourné les element d'un tableau*/
        int var = tab[0];
        for (int i = 0; i < tab.length - 2; i++) {
            tab[i] = tab[i + 1];
        }
        tab[tab.length - 1] = var;
        return tab;





    }

    public static void regles() {

        System.out.println(" ------------------------------------------------------------------");
        System.out.println("|                        PRINCIPE DU JEU                           |");
        System.out.println(" ------------------------------------------------------------------\n\n");
        System.out.println("o	Au début de chaque partie, tous les jetons sont initialisés.");
        System.out.println("        Vous disposez alors des jetons suivants: 1 jeton Є {1,..,10}, 2 jetons Є {11,..,19}, 1 jeton Є {20,..,30}, 1 jeton joker.");
        System.out.println("o	Placer les nombres tirés aléatoirement dans une grille de manière à obtenir une séquence (suite) non interrompue de nombres croissants.");
        System.out.println("o	Une partie se déroule en plusieurs tours: tant que tous les nombres ne sont pas tous tirés et placés, la partie n'est pas terminée.");
        System.out.println("o	Une fois toutes les cases remplies, la partie se termine donc et votre score s'affiche. Il est alors possible de s’arrêter ou de continuer, le score sera cumulé.");
        System.out.println("o	Enfin, le but est d'obtenir les plus longues séquences pour un maximum de point.\n\n");
        System.out.println(" ------------------------------------------------------------------");
        System.out.println("|                       LISTE DES GRILLES                          |");
        System.out.println(" ------------------------------------------------------------------\n\n");
        System.out.println("1er Grille: Basique\n");
        System.out.println("□ □ □ □ □ □");
        System.out.println("            □");
        System.out.println("            □");
        System.out.println("            □");
        System.out.println("            □");
        System.out.println("            □");
        System.out.println("            □");
        System.out.println("            □");
        System.out.println("            □");
        System.out.println("            □ □ □ □ □ □\n");

        System.out.println("2éme Grille: Boucle\n");
        System.out.println("            □ □ □ □");
        System.out.println("            □      □");
        System.out.println("            □      □");
        System.out.println("            □      □");
        System.out.println("□ □ □ □ □ □ □ □ □");
        System.out.println("            □");
        System.out.println("            □");
        System.out.println("            □\n");

        System.out.println("3éme Grille: Double boucle\n");
        System.out.println("       □ □ □");
        System.out.println("       □    □");
        System.out.println("       □    □");
        System.out.println("□ □ □ □ □ □");
        System.out.println("       □    □");
        System.out.println("       □    □");
        System.out.println("       □ □ □\n");

        System.out.println("4éme Grille: Surface\n");
        System.out.println("□ □ □ □ □ ");
        System.out.println("□ □ □ □ □ ");
        System.out.println("□ □ □ □ □ ");
        System.out.println("□ □ □ □ □ \n\n");

        System.out.println(" ------------------------------------------------------------------");
        System.out.println("|                     COMPTAGE DES POINTS                          |");
        System.out.println(" ------------------------------------------------------------------\n\n");
        System.out.println("Longueur & Score");
        System.out.println("    1        0");
        System.out.println("    2        1");
        System.out.println("    3        3");
        System.out.println("    4        5");
        System.out.println("    5        7");
        System.out.println("    6        9");
        System.out.println("    7        11");
        System.out.println("    8        15");
        System.out.println("    9        20");
        System.out.println("    10       25");
        System.out.println("    11       30");
        System.out.println("    12       35");
        System.out.println("    13       40");
        System.out.println("    14       50");
        System.out.println("    15       60");
        System.out.println("    16       70");
        System.out.println("    17       85");
        System.out.println("    18       100");
        System.out.println("    19       150");
        System.out.println("    20       300");

    }
}
