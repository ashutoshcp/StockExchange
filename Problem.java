import java.util.List;

public class Problem {
    public static void main(String[] args) {
        new Problem().process();
    }

    private void process() {
        printMaxSubSeqCharacter("0001111aabbbbb@@@@bb");
    }

    private void printMaxSubSeqCharacter(String s) {
        int currentCount = 0, maxCount = 0;
        if (s == null || s.length() ==0) {
            System.out.println("count: " + maxCount);
            return;
        }
        Character prevChar = s.charAt(0);
        Character maxChar = prevChar;
        currentCount = 1;
        maxCount = 1;
        int mc=0;
        char c, max;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            int count =1;
            while(s.charAt(i)==s.charAt(i+1) && i < s.length()) {
                i++;
                count++;
            }
            if (mc < count) {
                mc = count;
                max = c;
            }
        }
        for (int i = 1; i < s.length(); i++) {
            Character currentChar = s.charAt(i);
            if (prevChar == currentChar) {
                currentCount++;
            } else {
                if (currentCount > maxCount) {
                    maxCount = currentCount;
                    maxChar = currentChar;
                }
                prevChar = currentChar;
                currentCount = 1;
            }
//            if (currentCount > maxCount) {
//                maxCount = currentCount;
//                maxChar = currentChar;
//            }
        }
        System.out.println(maxChar + " " + maxCount);
    }

    public int countOnes(List<List<Integer>> array) {
        int rowNum = -1, sum = Integer.MIN_VALUE;
        int i =0;
        for (List<Integer> row: array) {
            i++;
            int c = findInversion(row);
            int cSum = row.size() -  c;
            if (cSum > sum) {
                sum = cSum;
                rowNum = i;
            }
        }
        System.out.println(rowNum + " " + sum);
        return rowNum;
    }
    // 0 0 0 1 1 1 1,
    // 0, 6 -> 3
    private int findInversion(List<Integer> row) {
        int low = 0, high = row.size() - 1;
        while (low < high) {
            int mid = high - ( high - low) / 2;
            Integer num = row.get(mid);
            if (num == 1) {
                if (mid-1 >= 0 && row.get(mid-1) ==0) {
                    return mid;
                }
                high = mid - 1;
            } else {
                if (mid + 1 < row.size() && row.get(mid + 1) == 1) {
                    return mid;
                }
                low = mid + 1;
            }
        }
        return low;
    }
}
