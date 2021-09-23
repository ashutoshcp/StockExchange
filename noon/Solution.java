package noon;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import model.Pair;

public class Solution {

    public static void main(String[] args) {
        new Solution().process();
    }

    /**
     * Input  -> bottom  [1, 3, 4, 2, 5, -2, 1, 9] top, 8
     * Output -> bottom  [3, 4, 2, 5, -2, 1, 9, 1] top
     */
    private void process() {
        System.out.println();
    }
//    [1, 2, 3, 4]

    private Stack<Integer> output;

    public Stack<Integer> bringToTop(Stack<Integer> input){
        // pop, push, isEmpty, peek
//        input.remove(0);
        //input.size();
        if (input.isEmpty()) {
            return new Stack<>();
        }
        input.push(input.pop());
        return bringToTop(input);
    }
    // [1, 9, -1, 7, 2], key = 9 , shortest continuous subsequence, starting index
    // [1, 8, -2], 6
//    i   sum     minValue           ansStart    currentEnd       currentStart
//        0       MAX_VALUE           0           0                   0
//    0   1       MAX_VALUE           0           0
//    1   9->0                                                        2

    public int shortestInteger(int[] array, int k) {
        int sum = 0;
        int minValue = Integer.MAX_VALUE;
        int ansStart = 0;
        int currentStart  = 0;
        int currentEnd = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                sum += array[j];
                if (sum == k) {
                    currentEnd = j;
                    if (currentEnd - currentStart < minValue) {
                        minValue = currentEnd - currentStart;
                        ansStart = currentStart;
                    }
                    currentStart = j + 1;
                } else if (sum > k) {
                    sum = 0;
                    currentStart = j + 1;
                }
            }
        }
        return ansStart;
    }
}

//
