/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava;

/**
 *
 * @author Erwan
 */
public class Point {
    
    
    String abscisse;
    int ordonnee;
    int valeur;
    
    



Point(String abscisse, int ordonnee){
this.abscisse = abscisse;
this.ordonnee = ordonnee;

}



    

  

    public String toString() {
        String result = "Point:\t" + nom;
        result += "\n Abssice:\t" + numero;
        result += "\n Solde du compte:\t" + solde;
        result += "\n Montant du découvert maximal autorisé:\t" + decouvertMax;
        result += "\n montant du débit maximal autorisé:\t" + debitMax;
        if (solde < 0) {
            result += "\n Vous etes a découvert !";
        }
        return result;
    }

    public void crediter(int somme) {
        if (somme > 0) {
            solde = solde + somme;
            System.out.println("votre compte a été crediter de:\t" + somme);
            releveCompte += "OPERATION :\t" + somme + "\tDE CREDITER \n";
        } else {
            System.out.println("Vous ne pouvez pas credité d un montant negatif");

        }
    }

    public boolean debiter(int somme) {
        if (somme > 0) {
            if ((somme < debitMax) && (solde - somme > -decouvertMax)) {
                solde = solde - somme;
                System.out.println("votre compte a été debité de:\t" + somme);
                releveCompte += "OPERATION :\t" + somme + "\tDE DEBITER \n";
                return true;
            } else {
                System.out.println("Votre compte n a pas été debité");
                return false;
            }

        } else {
            System.out.println("Vous ne pouvez pas debiter d un montant negatif");
            return false;
        }
    }

    public boolean virementVers(int somme, Compte c) {
        if (somme > 0) {
            if (solde - somme > -decouvertMax) {
                solde = solde - somme;
                c.solde = c.solde + somme;
                System.out.println("Le transfert de \t" + somme + "\t a été effectué");
                releveCompte += "OPERATION :\t" + somme + "\tDE VIRER  \n";
                return true;
            } else {
                System.out.println("Le transfert n a pas été effectué");
                return false;
            }
        } else {
            System.out.println("Vous ne pouvez pas faire un virement d un montant negatif");
            return false;
        }
    }

    public void setDecouvertMax(int somme) {

        decouvertMax = somme;

    }

    void setDebitMax(int somme) {

        debitMax = somme;
    }

    public void releve() {
        System.out.println("------------------------------------");
        System.out.println(nom);
        System.out.println();
        System.out.println(releveCompte);
        System.out.println("------------------------------------");
    }
}*/