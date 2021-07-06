package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class RemoveAdjacentDuplicates {
    public static void main(String[] args) {
        new RemoveAdjacentDuplicates().process();
    }

    private void process() {
        String str1 = "mississipie";
        System.out.println(removeDuplicates(str1));
        String str2 = "ocvvcolop";
        System.out.println(removeDuplicates(str2));
    }

    private String removeDuplicates(String s) {
        char ch = ' ';
        return removeDuplicateUtils(s, ch);
    }

    private String removeDuplicateUtils(String s, char ch) {
        if (s == null || s.length() < 2) {
            return s;
        }
        int i = 0;
        while (i < s.length()) {
            if (i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1)) {
                int j = i;
                while (j + 1 < s.length() && s.charAt(j) == s.charAt(j + 1)) {
                    j++;
                }
                char lastChar = i > 0 ? s.charAt(i - 1) : ch;
                String remStr = removeDuplicateUtils(s.substring(j + 1), lastChar);
                s = s.substring(0, i);
                int k = s.length(), l = 0;
                while (remStr.length() > 0 && s.length() > 0 && remStr.charAt(0) == s.charAt(s.length() - 1)) {
                    while (remStr.length() > 0 && remStr.charAt(0) != ch && remStr.charAt(0) == s
                            .charAt(s.length() - 1)) {
                        remStr = remStr.substring(1);
                    }
                    s = s.substring(0, s.length() - 1);
                }
                s = s + remStr;
                i = j;
            }
            else {
                i++;
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.poll();
        return s;
    }
}
