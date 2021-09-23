package digital;

import java.util.HashMap;
import java.util.Map;

public class Problem {
    public static void main(String[] args) {

    }

    int find(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            while (arr[i] >= 1 && arr[i] <= n && arr[i] != arr[arr[i] - 1]) {
                int temp = arr[arr[i] - 1];
                arr[arr[i] - 1] = arr[i];
                arr[i] = temp;
            }
        }

        for (int i = 0; i < n; i++)
            if (arr[i] != i + 1) {
                return (i + 1);
            }

        return (n + 1);
    }

    public int solution(String S) {
        // write your code in Java SE 8
        int b = 0, a = 0, l = 0, o = 0, n = 0;
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            switch (c) {
                case 'B':
                    b++;
                    break;
                case 'A':
                    a++;
                    break;
                case 'L':
                    l++;
                    break;
                case 'O':
                    o++;
                    break;
                case 'N':
                    n++;
                    break;
                default:
                    break;
            }
        }
        l = l / 2;
        o = o / 2;
        return Math.min(b, Math.min(a, Math.min(l, Math.min(o, n))));
    }

    public int solution(int[] A) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            map.putIfAbsent(A[i], 0);
            map.put(A[i], map.get(A[i]) + 1);
        }
        return map.values().stream().mapToInt(i -> i).map(count -> (count * (count - 1)) / 2).sum();
    }
}
