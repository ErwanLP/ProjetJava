package projetjava;

// >>>>>>>>>>>> IMPORTANT => LISTE COMMENTAIRE GLOBAL A LA FIN <<<<<<<<<<
import java.util.Scanner;

/*import java.util.Random;*/
/*int valeur = 1 + r.nextInt(37 - 1);*/
public class ProjetJava {

    static int compt = 0;
    static int longueurScore = 0;
    static int[] tabScore = new int[21];

    public static void main(String[] args) {

        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("STREAM");
        System.out.println("Par Erwan Le Poder et Florian Migot");
        menuPrincipale();

    }

    public static void menuPrincipale() {

        Scanner sc = new Scanner(System.in);
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("MENU");
        System.out.println("1-jouer");
        System.out.println("2-deux"); // regle du jeux
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

        String[][] tab = new String[10][11];
        Position p = new Position();
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

    }

    public static boolean rejouer() {
        Scanner sc = new Scanner(System.in);
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("REJOUER");
        System.out.println("1-oui");
        System.out.println("2-non");
        System.out.println("Votre choix ?");
        int choix = sc.nextInt();
        System.out.println("Votre choix de menu est le\t" + choix);
        if (choix == 1) {
            return true;
        } else {
            return false;


        }

    }

    public static void netoyerTab(String[][] tab) { // initialemnt un table string a pour valeur null on renplace par rien
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                tab[i][j] = " ";
            }
        }



    }

    public static void genererTab(String[][] tab, Position pCour) {

        // Systeme de comptage ne pas supprimer
        /*System.out.println(pCour);
         System.out.println(compt);
         compt++;*/

        if (pCour.x != 11 || pCour.y != 9) {   // on rajoute un en plus pour avoir le dernier ! 10 -> 11
            tab[pCour.y][pCour.x] = "*";  // logique pourquoi inversé ! abcsisse = collone 
            genererTab(tab, Position.suivant(pCour));

        }
    }

    public static void affichertab(String[][] tab) {

        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("VOTRE GRILLE ACTUELLE:");
        // be careful affiche pas dans le bon sens mais ya de l'idée
        // ca marche maintenant  cf ligne 69
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
            System.out.println("Votre choix de placcement est\t Abscisse:\t" + choixAbscisse + "\t Ordonnee\t" + choixOrdonnee);
            //TRAITEMENT PLACEMENT
        } while ((!verifPosition(choixAbscisse, choixOrdonnee, p)) || (tab[choixOrdonnee][choixAbscisse] != "*"));/*condition si c'est dans la grille et que il n'y est aps deja un nombre (en test)*/

        tab[choixOrdonnee][choixAbscisse] = String.valueOf(valeurJeton);




    }

    public static boolean verifTab(String[][] tab) {
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                if (tab[i][j] == "*") { // si on trouve encore au moins une etoile dans la grille
                    return true;

                }
            }
        }
        return false;


    }

    public static void comptagePoint(String[][] tab, Position pCour, int[] tabScore) { //marche pas pCour valeur cets pas ca je pense
        if (pCour.x != 10 || pCour.y != 9) {
            if (pCour.valeur <= Position.suivant(pCour).valeur) {
                longueurScore++;
                comptagePoint(tab, Position.suivant(pCour), tabScore);

            } else {
                tabScore[longueurScore + 1]++;
                longueurScore = 0;
                comptagePoint(tab, Position.suivant(pCour), tabScore);
            }



        }

        int pointScore = tabScore[2] * 1 + tabScore[3] * 3 + tabScore[4] * 5 + tabScore[5] * 7 + tabScore[6] * 9 + tabScore[7] * 11 + tabScore[8] * 15 + tabScore[9] * 20 + tabScore[10] * 25 + tabScore[11] * 30 + tabScore[12] * 35 + tabScore[13] * 40 + tabScore[14] * 50 + tabScore[15] * 60 + tabScore[16] * 70 + tabScore[17] * 85 + tabScore[18] * 100 + tabScore[19] * 150 + tabScore[20] * 300;
        System.out.println(pointScore);

    }

    public static boolean verifPosition(int choixAbscisse, int choixOrdonnee, Position pCour) { //verifie que le choix du joeuur est dans la grille
        if (pCour.x != 11 || pCour.y != 9) {
            if (pCour.x == choixAbscisse && pCour.y == choixOrdonnee) {
                return true;

            } else {
                return verifPosition(choixAbscisse, choixOrdonnee, Position.suivant(pCour));

            }
        } else {
            return false;
        }

    }
}




/* Le probleme que j'ai rencontré est que la grille (tab) est pour l'instant un tableau de char 
 * (caractère) mais on mais des int dedant (chiffre)  donc ya une methode pour transformer un chiffre 
 * en cartaère quitte a retransforme lors quon ferra le decompte des points mais lorsque cest des nombres
 * (plus de 1 chiffre) on peux pas mettre des hcar mais des String , du coup c'ets un joyeux bordel
 * 
 * Jai essayer avec un tableau en sting on verra pour moi cets la meilleur solution
 * 
 * verifier que un jeton peux pes etre jouer plein de fois
 * je crois quil y a un bug a se niveau la
 * 
 * 
 */