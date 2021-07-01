package codes;

import java.util.ArrayList;
import java.util.List;

import model.Pair;

public class SolutionTwo {
    // 2-D array with some value +ve or -ve
    // up, down, left, right
    // maximize the sum in 2d array
    /*
    *  1   2  -3
    *  4   5  -6
    *  -7   8  9
    *
    *
    * [[0,6,0],
       [5,8,7],
       [0,9,0]]
       *
       * [6, 8, 9] = 23               0,1
       * [5, 8, 9] = 22               1,1
       * [8, 9] = 17                  2,1
       * [7, 8, 9] = 24
       * [9, 8, 7] = 24
    * */

    public static void main(String[] args) {

    }

    public Pair<Integer, List<Integer>> maxSum(int[][] array) {
        Pair<Integer, List<Integer>> ans = null;
        List<Integer> paths = new ArrayList<>();
        int xLength = array.length;
        int yLength = array[0].length;
        boolean[][] visited = new boolean[xLength][yLength];
        for (int i = 0; i < xLength; i++) {
            for (int j = 0; j < yLength; j++) {
                visited[i][j] = false;
            }
        }

        for (int i = 0; i < xLength; i++) {
            for (int j = 0; j < yLength; j++) {
                int val1 = array[i][j];
                if (val1==0) {
                    visited[i][j] = true;
                    continue;
                }
                List<Integer> path = new ArrayList<>();
                path.add(val1);
                int xi = i;
                int yi = j;
                while (xi < xLength && yi < yLength && !visited[xi][yi]) {
                    // [xi, yi + 1], [xi, yi-1], [xi-1, yi], [xi+1, yi]
                }

            }
        }
        return ans;
    }


}
