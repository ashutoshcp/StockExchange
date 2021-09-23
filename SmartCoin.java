import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SmartCoin {
    public static void main(String[] args) {
        new SmartCoin().process();
    }

    private void process() {
        // t-1
        int[] arr = new int[] {1, 2, 3, 4};
        System.out.println(getAllSolution(arr, 4, 6));
        // t-2
        arr = new int[] {1, 5, 6};
        System.out.println(getAllSolution(arr, 4, 3));
    }

    private List<List<Integer>> getAllSolution(int[] A, int F, int M) {
        List<List<Integer>> answer = new ArrayList<>();
        int x = A.length;
        int sum = (x + F) * M;
        int accumlativeSum = 0;
        for (int i = 0; i < A.length; i++) {
            accumlativeSum += A[i];
        }
        int diff = sum - accumlativeSum;
        if ((diff > (F * 6)) || (diff < F)) {
            //System.out.println("Condition");
            return answer;
        }
        System.out.println("diff: " + diff + " " + " , F: " + F);// 1-6, F => diff
        // 1, 1, 1, 1
        //
        // 1, 1, 1,
        // 6 6 6 6
        // 1, 1
        // 1, 2
        int[] a = new int[F];
        Arrays.fill(a, 1);
        List<String> in = new ArrayList<>();
        for (int i = 0; i < F; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.println(Arrays.toString(a));
                if (j!=0) {
                    a[i] += 1;
                }
                int s1 = 0;
                for (int k = 0; k < F; k++) {
                    s1+=a[k];
                }
                if (s1 == diff) {
                    System.out.println("Here");
                    System.out.println(Arrays.toString(a));
                }
            }

        }

        return answer;
    }
}
