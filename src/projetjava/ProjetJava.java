package projetjava;

// >>>>>>>>>>>> IMPORTANT => LISTE COMMENTAIRE GLOBAL A LA FIN <<<<<<<<<<
import java.util.Scanner;

/*import java.util.Random;*/
/*int valeur = 1 + r.nextInt(37 - 1);*/
public class ProjetJava {

    static int compt = 0;

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

        String[][] tab = new String[10][11];
        Position p = new Position();
        NetoyerTab(tab);
        GenererTab(tab, p);
        // reboucler ici
        Affichertab(tab);
        NombreAleatPlacement(tab);

    }

    public static void NetoyerTab(String[][] tab) { // initialemnt un table string a pour valeur null on renplace pas rien
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                tab[i][j] = " ";
            }
        }



    }

    public static void GenererTab(String[][] tab, Position pCour) {

        // Systeme de comptage ne pas supprimer
        /*System.out.println(pCour);
         System.out.println(compt);
         compt++;*/

        if (pCour.x != 11 || pCour.y != 9) {   // on rajoute un en plus pour avoir le dernier ! 10 -> 11
            tab[pCour.y][pCour.x] = "*";  // logique pourquoi inversé ! abcsisse = collone 
            GenererTab(tab, Position.suivant(pCour));

        }
    }

    public static void Affichertab(String[][] tab) {

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

    public static void NombreAleatPlacement(String[][] tab) {

        //NBALEAT
        Scanner sc = new Scanner(System.in);
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("TIRAGE JETON:");
        Jeton j = new Jeton();
        int valeurJeton = j.valeur;
        // bon ca marche pas mais ne pas sup (a poursuivre),  imaginons que le chiffre soit 5
        // mail envoyer a manceny pour de l'aide
        //int valeurJeton = 5;
        System.out.println("Le jeton tiré est le :\t" + valeurJeton);
        //CHOIX PLACEMENT
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("SELECTION EMPLACEMENT:");
        System.out.println("Choix Abscisse du Jeton : ");
        int choixAbscisse = sc.nextInt();
        System.out.println("Choix Ordonne du Jeton");
        int choixOrdonnee = sc.nextInt();
        System.out.println("Votre choix de placcement est\t Abscisse:\t" + choixAbscisse + "\t Ordonnee\t" + choixOrdonnee);
        //TRAITEMENT PLACEMENT
        if (true/*condition si c'est dans la grille*/) {
             tab[choixOrdonnee][choixAbscisse] = String.Valueof(valeurJeton);
        }



    }
}




/* Le probleme que j'ai rencontré est que la grille (tab) est pour l'instant un tableau de char 
 * (caractère) mais on mais des int dedant (chiffre)  donc ya une methode pour transformer un chiffre 
 * en cartaère quitte a retransforme lors quon ferra le decompte des points mais lorsque cest des nombres
 * (plus de 1 chiffre) on peux pas mettre des hcar mais des String , du coup c'ets un joyeux bordel
 * 
 * Jai essayer avec un tableau en sting on verra pour moi cets la meilleur solution
 */