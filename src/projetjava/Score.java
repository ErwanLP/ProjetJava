/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava;

/**
 *
 * @author Erwan
 */
public class Score {

      // attribut  : tableau dont le score est calculé
    int[] grid;
    // attribut : tableau des scores en fonction de l'indice qui reflete la taille de la sequance
    int[] scores = {0, 0, 1, 3, 5, 7, 9, 11, 15, 20, 25, 30, 35, 40, 50, 60, 70, 85, 100, 150, 300};
    // debut de fin de la sequance etudiée
    int start, end;
    // cible vers un autre objet Score
    Score next;

    public Score(Score s) {
        // Constructeur qui copie un objet
        grid = s.grid;
        start = s.start;
        end = s.end;
        next = s.next;
    }

    public Score(int[] grid, int start) {
        this.grid = grid;
        this.start = start;
        this.end = start;
        this.next = null;
        // best : variable qui garde le meilleur score;
        Score best = new Score(this);
        while (!isMaximal()/* tant que la fonction est fausse*/) {
            //on accremnente this.end qui est la limite superieur de la sequand etudiée ( de l'objet étudier)
            ++this.end;
            // next est egale a un nouveau objet qui debutera a end (la limite de la dernière sequance etudier) si la fin de la dernière sequence étudier est 
            // differente de la taille du tableau étudié sinon next est null
            next = (end != grid.length) ? new Score(grid, end) : null;
            // si la nouvelle valeur est superieur (au niveau du score) a l'ancienne, on la prend comme nouvelle meilleur valeur
            if (value() > best.value()) {
                best = new Score(this);
            }
        }
        end = best.end;
        next = best.next;
    }

    public Score(int[] grid) {
        // constructeur qui rappelle le constructeur precedent avec pour start 0
        this(grid, 0);
    }

    private boolean isMaximal() {
        // si la limite superieur de la sequence etudiée est egale a la fin du tableau, on return true;
        if (end == grid.length) {
            return true;
        }
        // si la sequence est strictement inferieur  2 ou on tombe sur le joker ou 2 nombres consecutifs sont identique alors on return false;
        if (((end - start) < 2) || (grid[end] == 0) || (grid[end] == grid[end - 1])) {
            return false;
        }
        // boucle de start a end
        for (int i = start; i != end; ++i) {
            // si on ne tombe pas sur un joker sur la case étudiée ou la suivante
            if ((grid[i] != 0) && (grid[i + 1] != 0)) {
                // et si la case etudiée est  (superieur a la precedante et inferieur a la suivante ) ou inferieur (inferieur à la suivante et superieur a la precedente)
                // on return true
                if (((grid[end] > grid[end - 1]) && (grid[i + 1] < grid[i]))
                        || ((grid[end] < grid[end - 1]) && (grid[i + 1] > grid[i]))) {
                    return true;
                }
            }
        }
        // si aucun cas precedent n'est trouvé, on return false
        return false;
    }

    private int value() {
        // le tableau scores represente le point gagnés par la sequence, on y additionne les points des autres sequances si elles existent;
        return scores[end - start] + ((next != null) ? next.value() : 0);
    }

    private int getEnd() {
        // fonction qui return l'attribut end d'un objet
        return end;
    }

    public String toString() {
        // fonction qui affiche le score
        String res = new String("\n seq:[0");
        for (Score i = this; i != null; i = i.next) {
            res = res + ", " + i.getEnd();
        }
        res = res + "] with score:" + value();
        return res;
    }


}
