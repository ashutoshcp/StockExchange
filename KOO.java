public class KOO {

    public static void main(String[] args) {
        new KOO().process();
    }

    private static final int[][] directions = new int[][] { { 0, -1 }, { 1, 0 }, { -1, 0 }, { 0, 1 } };

    private void process() {
        int[][] grid = new int[][] { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } };
        printGrid(grid, "Initial");
        int sr = 1;
        int sc = 1;
        int newColor = 2;
        int prevColor = grid[sr][sc];
        int r = grid.length;
        int c = grid[0].length;
        updatePostColorGrids(grid, sr, sc, newColor, prevColor, r, c);
        printGrid(grid, "Resultant");
    }

    private void printGrid(int[][] grid, String message) {
        System.out.println(message);
        for (int[] rows : grid) {
            for (int ele : rows) {
                System.out.print(ele + " ");
            }
            System.out.println();
        }
    }

    private void updatePostColorGrids(int[][] grid, int ri, int ci, int newColor, int prevColor, int r, int c) {
        if (ri < 0 || ci < 0 || ri >= r || ci >= c) {
            return;
        }
        if (grid[ri][ci] == prevColor) {
            grid[ri][ci] = newColor;
            for (int i = 0; i < directions.length; i++) {
                int[] arr = directions[i];
                updatePostColorGrids(grid, ri + arr[0], ci + arr[1], newColor, prevColor, r, c);
            }
        }
    }
}

