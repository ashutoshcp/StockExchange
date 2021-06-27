package vahak;

public class RotatingMatrix {
    public static void main(String[] args) {
        new RotatingMatrix().process();
    }

    private void process() {
        int[][] array = new int[][] {
                { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        printArray("Input: ", array);
        rotateByNinetyDegree(array);
        printArray("Output: ", array);
    }

    private void printArray(String inp, int[][] array) {
        System.out.println("--------");
        System.out.println(inp);
        for (int[] rows : array) {
            for (int ele : rows) {
                System.out.print(ele + " ");
            }
            System.out.println();
        }
        System.out.println("--------");
    }

    private void rotateByNinetyDegree(int[][] array) {
        int N = array.length;
        for (int i = 0; i < (N + 1) / 2; i++) {
            for (int j = 0; j < N / 2; j++) {
                int temp = array[N - 1 - j][i];
                array[N - 1 - j][i] = array[N - 1 - i][N - j - 1];
                array[N - 1 - i][N - j - 1] = array[j][N - 1 - i];
                array[j][N - 1 - i] = array[i][j];
                array[i][j] = temp;
            }
        }
    }
}
/*
--------
Input:
1 2 3
4 5 6
7 8 9
--------
--------
Output:
7 4 1
8 5 2
9 6 3
--------
 */