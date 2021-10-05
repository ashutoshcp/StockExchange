package dp;

import java.util.Arrays;

public class Problem {
    public static void main(String[] args) {
        Problem problem = new Problem();
//        System.out.println(problem.countInString("abbabbaaa"));
//        System.out.println(problem.countInString("bbbab"));
        System.out.println(problem.countInString("bbbaaabbb"));
    }

    /**
     * s = "abbabbaaa"      [aa]abb[b]a[aa]bb[b]aaa => max length of block [l]: 3, no of blocks [n]: 5, [n*l - sizeOf(s)]
     * bbbab  bbba[aa]b[bb]
     */

    private int countInString(String input) {
        System.out.println("Input: " + input);
        int maxLength = 1;
        int size = input.length();
        int blocks = 0;
        for (int i = 0; i < size; i++) {
            int count = 1;
            while (i < size - 1 && input.charAt(i) == input.charAt(i + 1)) {
                count++;
                i++;
            }

            if (count > maxLength) {
                maxLength = count;
            }
            blocks++;
            //System.out.println(blocks + " " + input.charAt(i) + " " + count);
        }
        //System.out.println(blocks + " " + maxLength);
        return (blocks * maxLength - size);
    }

    // array of +ve integers and k => largest possible even sum of k elements
    /**
     *  array = [5, 6, 3, 4, 2] , K=5
     *  Not possible => -1
     *  K <= array.size
     *  array[i] >= 0
     *
     *  1. sort array [asc order]
     *  2. check no of odd elements[x] in last K elements
     *      a. if x is odd
     *          Remove smallest even number, add largest odd number from remaining{n-k}
     *              i.  if no odd element in n-k sub-set [-1]
     *              ii. get largest odd element, remove smallest even element (s1 + ol - es)
     *      b. else
     *          largest possible even sum
     *
     *    [9, 4, 6, 2]  3
     *
     *    [2, 4, 6, 9]
     *
     *    [2]     [9, 7]
     *    max k elements from even =>
     *    eMin => []       oMax => []
     */

    private int largestPossibleKSum(int[] array, int k) {
        int oCount = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 1) {
                oCount++;
            }
        }
        if (oCount == 0) {
            Arrays.sort(array);
            int count = 0;
            int sum = 0;
            for (int i = array.length - 1; i >= 0; i++) {
                sum += array[i];
                count++;
                if (count == k) {
                    break;
                }
            }
            return sum;
        }
        else if (oCount == array.length) {
            if (k % 2 == 0) {
                Arrays.sort(array);
                int count = 0;
                int sum = 0;
                for (int i = array.length - 1; i >= 0; i++) {
                    sum += array[i];
                    count++;
                    if (count == k) {
                        break;
                    }
                }
                return sum;
            }
            else {
                return -1;
            }
        }
        int[] oc = new int[oCount];
        int[] ec = new int[array.length - oCount];
        int ei = 0, oi = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                ec[ei++] = array[i];
            }
            else {
                oc[oi++] = array[i];
            }
        }
        Arrays.sort(oc);
        Arrays.sort(ec);
        // k
        int oddIndex = oc.length-1;
        int evenIndex = ec.length-1;
        int sum = 0;
        while (k > 1) {
            if (evenIndex-1 >= 0 && oddIndex-1 >= 0) {
                if (ec[evenIndex]+ec[evenIndex-1] > oc[oddIndex] + oc[oddIndex-1]) {
                    sum += ec[evenIndex]+ec[evenIndex-1];
                    evenIndex-=2;
                } else {
                    sum += oc[oddIndex]+oc[oddIndex-1];
                    oddIndex-=2;
                }
                k-=2;
            }
        }

        while (k > 0) {

        }
        return 0;
    }

    /**
     * Unlimited coins, min no of denominations eq to supplied value
     * [1, 2, 3], 4
     *
     *  2+2, 1+3, 1+1+1+1, 2+1+1
     */
}
