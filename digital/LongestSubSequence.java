package digital;

public class LongestSubSequence {

    public static void main(String[] args) {
        //System.out.println(findLongestSubsequence(new int[] { 1, 2, 4, 5, 6 }));
        System.out.println(climb(6));
    }

    public static int findLongestSubsequence(int[] array){
        int n = array.length;
        int[] longest = new int[n];
        int i, j, max = 0;

        for (i = 0; i < n; i++) {
            longest[i] = 1;
        }
        for (i = 0; i < n; i++) {
            for (j = 0; j < i; j++) {
                if (array[i] > array[j] && longest[i] < longest[j] + 1) {
                    longest[i] = longest[j] + 1;
                }
            }
        }
        for (i = 0; i < n; i++) {
            max = Math.max(max, longest[i]);
        }
        return max;
    }

    public static int climb(int n) {
        if (n <= 1) {
            return 1;
        }
        return climb(n-1) + climb(n-2);
    }

}
