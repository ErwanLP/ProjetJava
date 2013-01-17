package projetjava;

public class Score {

     // Attribut  : tableau dont le score est calculé
    int[] grid;
    // Attribut : tableau des scores en fonction de l'indice qui reflète la taille de la séquence
    int[] scores = {0, 0, 1, 3, 5, 7, 9, 11, 15, 20, 25, 30, 35, 40, 50, 60, 70, 85, 100, 150, 300};
    // Début de fin de la séquence étudiée
    int start, end;
    // Cible vers un autre objet Score
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
            // On incrémente this.end qui est la limite supérieure de la séquence étudiée ( de l'objet étudié )
            ++this.end;
            // next est égale à un nouveau objet qui débutera a end (la limite de la dernière séquence étudiée) si la fin de la dernière séquence étudiée est 
            // différente de la taille du tableau étudié sinon next est null
            next = (end != grid.length) ? new Score(grid, end) : null;
            // Si la nouvelle valeur est superieure (au niveau du score) à l'ancienne, on la prend comme nouvelle meilleur valeur
            if (value() > best.value()) {
                best = new Score(this);
            }
        }
        end = best.end;
        next = best.next;
    }

    public Score(int[] grid) {
        // Constructeur qui rappelle le constructeur précèdent avec pour start 0
        this(grid, 0);
    }

    private boolean isMaximal() {
        // Si la limite supérieure de la séquence étudiée est égale a la fin du tableau, on retourne true;
        if (end == grid.length) {
            return true;
        }
        // Si la séquence est strictement inferieur  2 ou on tombe sur le joker ou 2 nombres consecutifs sont identiques alors on retourne false;
        if (((end - start) < 2) || (grid[end] == 0) || (grid[end] == grid[end - 1])) {
            return false;
        }
        // Boucle de start a end
        for (int i = start; i != end; ++i) {
            // Si on ne tombe pas sur un joker, sur la case étudiée ou la suivante
            if ((grid[i] != 0) && (grid[i + 1] != 0)) {
                // et si la case étudiée est  (supérieure à la précédente et inférieur à la suivante ) ou (inferieur à la précédente et superieur à la suivante)
                // on retourne true
                if (((grid[end] > grid[end - 1]) && (grid[i + 1] < grid[i]))
                        || ((grid[end] < grid[end - 1]) && (grid[i + 1] > grid[i]))) {
                    return true;
                }
            }
        }
        // Si aucun des cas précédent n'est trouvé, on retourne false
        return false;
    }

    private int value() {
        // Le tableau des scores représentent les point gagnés par la séquence, on y additionne les points des autres séquences si elles existent;
        return scores[end - start] + ((next != null) ? next.value() : 0);
    }

    private int getEnd() {
        // Fonction qui return l'attribut end d'un objet
        return end;
    }

    public String toString() {
        // Fonction qui affiche le score
        String res = new String("\n seq:[0");
        for (Score i = this; i != null; i = i.next) {
            res = res + ", " + i.getEnd();
        }
        res = res + "] with score:" + value();
        return res;
    }


}
