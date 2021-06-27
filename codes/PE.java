package codes;

import java.util.Arrays;

public class PE {
    // un sorted array of length n<=1000, kth smallest element
    // max heap of size K {},  {4, 3, 2}
    public static void main(String[] args) {
        Integer[] arr = { 0, 1, 2, 1, 2, 0, 1 };
        new PE().sort(arr, 5);
        System.out.println(Arrays.toString(arr));
    }

    public void sort(Integer[] arr, Integer k) {
        Arrays.sort(arr);
        /*PriorityQueue<Integer> pq = new PriorityQueue<>(k, Collections.reverseOrder());
        for (Integer num: arr) {
            if (pq.size() <k) {
                pq.add(num);
            } else {
                if(pq.peek() > num) {
                    pq.poll();
                    pq.add(num);
                }
            }
        }
        System.out.println();
        System.out.println(k + "th smallest element: " + pq.peek());*/
    }
}
