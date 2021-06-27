package ola;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindPairs {
    public static void main(String[] args) {
        new FindPairs().process();
    }

    public class Pair<K, V> {
        private K first;
        private V second;

        public Pair(K first, V second) {
            this.first = first;
            this.second = second;
        }

        public K getFirst() {
            return first;
        }

        public void setFirst(K first) {
            this.first = first;
        }

        public V getSecond() {
            return second;
        }

        public void setSecond(V second) {
            this.second = second;
        }

        @Override
        public String toString() {
            return "Pair{" + "first=" + first + ", second=" + second + '}';
        }
    }

    private void process() {
        int[] array = new int[] { 1, 5, 7, 6, 2 };
        //int[] array = new int[]{1, 5, 2, 4, 3, 3 };
        int target = 6;
        System.out.println("Input Array: " + Arrays.toString(array) + ", target: " + target);
        List<Pair<Integer, Integer>> pairs = findPairs(array, target);
        if (pairs.size() == 0) {
            System.out.println("No Pair found");
        }
        else {
            System.out.println("Printing pairs");
            pairs.forEach(System.out::println);
        }
    }

    private List<Pair<Integer, Integer>> findPairs(int[] array, int target) {
        if (array == null || array.length <= 1) {
            return new ArrayList<>();
        }
        List<Pair<Integer, Integer>> pairs = new ArrayList<>();
        Arrays.sort(array);
        int start = 0;
        int end = array.length - 1;
        while (start <= end) {
            int tempTarget = array[start] + array[end];
            if (tempTarget == target) {
                pairs.add(new Pair<>(array[start], array[end]));
                start++;
                end--;
            }
            else if (tempTarget > target) {
                end--;
            }
            else {
                start++;
            }
        }
        return pairs;
    }
}
