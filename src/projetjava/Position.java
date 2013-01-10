/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava;

/**
 *
 * @author Erwan
 */
public class Position {

    int x;
    int y;
    int nbGrille;
    
    /*
     * >>>>>>>>>>>>>>>>>>>>>>>USELESS<<<<<<<<<<<<<<<<<<<<<<<<<
     * 
     */
    

    Position(int nbGrille) {
        if (nbGrille == 1) {
            x = 2;
            y = 2;
            this.nbGrille = nbGrille;

        }
        if (nbGrille == 2) {
            x = 2;
            y = 6;
            this.nbGrille = nbGrille;


        }

    }

    Position(int x, int y, int nbGrille) {
        this.x = x;
        this.y = y;
        this.nbGrille = nbGrille;

    }

    public String toString() {
        String result = "Point:";
        result += "\n Abssice:\t" + x;
        result += "\n Ordonnee:\t" + y;
        return result;
    }

    public static Position suivant(Position p) {
        if (p.nbGrille == 1) {


            if (p.x < 7 && p.y == 2) {
                return new Position(p.x + 1, p.y, p.nbGrille);
            }
            if (p.x == 7 && p.y < 11) {
                return new Position(p.x, p.y + 1, p.nbGrille);
            }
            if (p.x < 12 && p.y == 11) {
                return new Position(p.x + 1, p.y, p.nbGrille);
            }
            //if(p.x == 10 && p.y == 10){
            return new Position(99, 99,p.nbGrille);


        }
        if(p.nbGrille == 2){
            
            if(p.x<10 && p.y ==6){
                return new Position(p.x + 1, p.y, p.nbGrille);                
            }
           // if(p.x ==7 && )
            
            //On peut pas use cette methode car si la position est sur une case elle ne peut allé que sur une autre case on doit utiliser les liste
            
        }
        
        
        return p;
    }
}
