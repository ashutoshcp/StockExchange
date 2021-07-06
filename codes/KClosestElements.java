package codes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KClosestElements {

    public static void main(String[] args) {
        int[] array = { 1, 2,3,4,5 };
        List<Integer> closest = new KClosestElements().findClosest(array, 3, 4);
        closest.forEach(ele -> System.out.print(ele + " "));
        System.out.println();
    }

    private int findPoint(int[] array, int low, int high, int x) {
        while (low <= high) {
            int mid = high - ((high - low) / 2);
            if (array[mid] == x) {
                return mid;
            }
            else if (array[mid] < x) {
                low = mid + 1;
            }
            else {
                high = mid + 1;
            }
        }
        return -1;
    }

    private List<Integer> findClosest(int[] array, int x, int k) {
        List<Integer> closest = new ArrayList<>();
        // basic checks
        int length = array.length - 1;
        int left = findPoint(array, 0, length, x) - 1;
        int right = left + 2;
        int count = 0;
        while (right <= length && left >= 0 && count < k) {
            if (x - array[left] > array[right] - x) {
                closest.add(array[right]);
                right++;
            }
            else {
                closest.add(array[left]);
                left--;
            }
            count++;
        }
        while (right <= length && count < k) {
            closest.add(array[right]);
            right++;
            count++;
        }
        while (left >= 0 && count < k) {
            closest.add(array[left]);
            left--;
            count++;
        }
        Collections.sort(closest);
        return closest;
    }
}