package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LeetCode {
    public static void main(String[] args) {
        new LeetCode().process();
    }

    private void process() {
        //System.out.println(removeOccurrences("axxxxyyyyb", "xy"));
        System.out.println(canBeIncreasing(new int[]{2,3,1,2}));
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
}
