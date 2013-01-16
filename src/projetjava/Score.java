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

    int[] grid;
    int[] scores = {0, 0, 1, 3, 5, 7, 9, 11, 15, 20, 25, 30, 35, 40, 50, 60, 70, 85, 100, 150, 300};
    int start, end;
    Score next;

    public Score(Score s) {
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
        Score best = new Score(this);
        while (!isMaximal()) {
            ++this.end;
            next = (end != grid.length) ? new Score(grid, end) : null;
            if (value() > best.value()) {
                best = new Score(this);
            }
        }
        end = best.end;
        next = best.next;
    }

    public Score(int[] grid) {
        this(grid, 0);
    }

    private boolean isMaximal() {
        if (end == grid.length) {
            return true;
        }
        if (((end - start) < 2) || (grid[end] == 0) || (grid[end] == grid[end - 1])) {
            return false;
        }
        for (int i = start; i != end; ++i) {
// if current or next value is a joker, no reason to stop the growth
            if ((grid[i] != 0) && (grid[i + 1] != 0)) {
                if (((grid[end] > grid[end - 1]) && (grid[i + 1] < grid[i]))
                        || ((grid[end] < grid[end - 1]) && (grid[i + 1] > grid[i]))) {
                    return true;
                }
            }
        }
        return false;
    }

    private int value() {
        return scores[end - start] + ((next != null) ? next.value() : 0);
    }

    private int getEnd() {
        return end;
    }

    public String toString() {
        String res = new String("\n seq:[0");// TODO use StringBuffer
        for (Score i = this; i != null; i = i.next) {
            res = res + ", " + i.getEnd();
        }
        res = res + "] with score:" + value();
        return res;
    }

    public static void main(String[] arg) {
        int[] testGrid = {1, 2, 5, 6, 10, 12, 12, 4, 3, 11, 11, 13};
        System.out.println(new Score(testGrid));
    }
}
