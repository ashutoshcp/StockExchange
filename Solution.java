public class Solution {
    public static void main(String[] args) {
        new Solution().process();
    }

    private void process() {
        System.out.println(rc(2));
    }
    // babad

    private String findLongestPalindromeV2(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        int length = s.length();
        if (length <= 1) {
            return s;
        }
        int maxLength = 1;
        int low, high, start = 0;
        for (int i = 1; i < length; i++) {
            low = i - 1;
            high = i;
            while (low >= 0 && high < length && s.charAt(low) == s.charAt(high)) {
                if (high - low + 1 > maxLength) {
                    start = low;
                    maxLength = high - low + 1;
                }
                low--;
                high++;
            }
            low = i - 1;
            high = i + 1;
            while (low >= 0 && high < length && s.charAt(low) == s.charAt(high)) {
                if (high - low + 1 > maxLength) {
                    start = low;
                    maxLength = high - low + 1;
                }
                low--;
                high++;
            }
        }
        System.out.println("Start: " + start + " max: " + maxLength);
        return s.substring(start, start + maxLength);
    }

    private String findLongestPalindrome(String s) {
        int l = s.length();
        boolean[][] dp = new boolean[l][l];
        int start = 0;
        // sub string of len 1
        int maxLength = 1;
        for (int i = 0; i < l; i++) {
            dp[i][i] = true;
        }
        // palindrome of length 2
        for (int i = 0; i < l - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                //System.out.println("Here: " + i);
                dp[i][i + 1] = true;
                start = i;
                maxLength = 2;
            }
        }
        //printDp(dp, l);
        // a b a
        System.out.println(start);
        for (int i = 3; i <= l; i++) {
            for (int j = 0; j < l - i + 1; j++) {
                int k = j + i - 1;
                if (dp[j + 1][k - 1] && s.charAt(j) == s.charAt(k)) {
                    dp[j][k] = true;
                    if (i > maxLength) {
                        start = j;
                        maxLength = i;
                    }
                }
            }
        }
        System.out.println(start + " " + maxLength);
        return s.substring(start, start + maxLength - 1 + 1);
    }

    private void printDp(boolean[][] dp, int l) {
        System.out.println();
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private String rc(long n) {
        System.out.println(n);
        long col = n - ((n / 702) * 702);
        long row = n / 702;
        if (n % 702 != 0) {
            row++;
        }
        System.out.println(col + " " + row);
        return row + getCol(col);
    }

    private String getCol(long col) {
        StringBuilder s = new StringBuilder();
        while (col != 0) {
            col--;
            s.append((char) ('A' + (col % 26)));
            col = col / 26;
        }
        return s.reverse().toString();
    }

    public int partitionDisjoint(int[] nums) {
        int n = nums.length;
        int[] maxLeft = new int[n];
        int[] minRight = new int[n];
        maxLeft[0] = nums[0];
        minRight[n - 1] = nums[n - 1];
        for (int i = 1; i < n; i++) {
            maxLeft[i] = Math.max(maxLeft[i - 1], nums[i]);
            minRight[n - i - 1] = Math.min(minRight[n - i - 1], nums[n - i]);
        }
        for (int i = 1; i < n; ++i)
            if (maxLeft[i - 1] <= minRight[i]) {
                return i;
            }
        return -1;
    }
}
