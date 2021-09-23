package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LeetCode {
    public static void main(String[] args) {
        new LeetCode().process();
    }

    private void process() {
        //System.out.println(removeOccurrences("axxxxyyyyb", "xy"));
        //System.out.println(canBeIncreasing(new int[]{2,3,1,2}));
        System.out.println(isIsomorphic("bbbaaaba", "aaabbbba"));
    }
    public String removeOccurrences(String s, String part) {
        while (s.contains(part)) {
            s = s.replaceAll(part, "");
        }
        return s;
    }

    public boolean canBeIncreasing(int[] array) {
        int k = 1;
        int pointer = array.length-1;
        int count = 0;

        for(int i = array.length-2; i >= 0; i--) {
            if(array[pointer]-array[i] > 0) {
                pointer = i;
            }
            else {
                int l = i;
                int r = pointer;
                int leftCount = count;
                int rightCount = count;

                while(array[l] >= array[pointer] && l > 0) {
                    leftCount++;
                    l--;
                    if(leftCount > k) {
                        break;
                    }
                }

                while(array[i] >= array[r] && r < array.length-1) {
                    rightCount++;
                    r++;
                    if(rightCount > k) {
                        break;
                    }
                }
                if(leftCount > k && rightCount > k) {
                    return false;
                }
                if(leftCount < rightCount) {
                    count = leftCount;
                    pointer = l;
                    i = l;
                }
                else {
                    count = rightCount;
                    pointer = i;
                    i--;
                }

                if(count > k) {
                    return false;
                }
            }
        }

        return count <= k;
    }

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();
        List<Integer> sorted = new ArrayList<Integer>();

        for (int i = nums.length - 1; i >= 0; i--) {
            if (sorted.isEmpty()) {
                sorted.add(nums[i]);
                result.add(0);
            }
            else if (nums[i] > sorted.get(sorted.size() - 1)) {
                sorted.add(sorted.size(), nums[i]);
                result.add(sorted.size() - 1);
            }
            else {
                int l = 0;
                int r = sorted.size() - 1;
                while (l < r) {
                    int m = l + (r - l) / 2;
                    if (nums[i] > sorted.get(m)) {
                        l = m + 1;
                    }
                    else {
                        r = m;
                    }
                }
                sorted.add(r, nums[i]);
                result.add(r);
            }
        }

        Collections.reverse(result);

        return result;
    }

    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int rows = mat.length, cols = mat[0].length;
        if (rows * cols != r * c) {
            return mat;
        }
        int row = 0, col = 0;
        int[][] result = new int[r][c];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++) {
                result[row][col] = mat[i][j];
                col++;
                if (col == c) {
                    col = 0;
                    row++;
                }
            }
        return result;
    }

    public int minSetSize(int[] arr) {
        int[] numFreqArr = new int[100001];
        int inputSize = arr.length;
        int maxFreq = 0;
        for (int num : arr) {
            numFreqArr[num] = numFreqArr[num] + 1;
            maxFreq = Math.max(maxFreq, numFreqArr[num]);
        }
        int[] freqArray = new int[maxFreq + 1];
        for (int i : numFreqArr) {
            freqArray[i] = freqArray[i] + 1;
        }
        int ans = 0;
        int halfLength = (inputSize / 2);
        for (int freq = freqArray.length - 1; freq >= 0 && inputSize > halfLength; ) {
            int freqElementCount = freqArray[freq];
            if (freqElementCount == 0) {
                freq--;
                continue;
            }
            inputSize = inputSize - (freq);
            freqArray[freq] = freqArray[freq] - 1;
            ans++;

            if (inputSize <= halfLength) {
                return ans;
            }
        }
        return ans;
    }

    public boolean isIsomorphic(String s, String t) {
        Map<Character, Integer> map1 = new TreeMap<>();
        for (char c: s.toCharArray()) {
            map1.putIfAbsent(c, 0);
            map1.put(c, map1.get(c) + 1);
        }
        Map<Character, Integer> map2 = new TreeMap<>();
        for (char c: t.toCharArray()) {
            map2.putIfAbsent(c, 0);
            map2.put(c, map2.get(c) + 1);
        }
        if (map1.size() != map2.size()) {
            return false;
        }
        List<Integer> one = new ArrayList<>();
        List<Integer> two = new ArrayList<>();
        map1.forEach((k,v) -> one.add(v));
        map2.forEach((k,v) -> two.add(v));
        if (one.size()!=two.size()) {
            return false;
        }
        Collections.sort(one);Collections.sort(two);
        for (int i = 0; i < one.size(); i++) {
            if (!one.get(i).equals(two.get(i))) {
                return false;
            }
        }
        return true;
    }
}
