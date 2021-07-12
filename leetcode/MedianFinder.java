package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianFinder {
    private final PriorityQueue<Integer> minHeap;
    private final PriorityQueue<Integer> maxHeap;
    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    }

    public void addNum(int num) {
        if (maxHeap.size() == 0) {
            maxHeap.add(num);
        } else if (maxHeap.size() <= minHeap.size()) {
            if (minHeap.peek() < num) {
                maxHeap.add(minHeap.poll());
                minHeap.add(num);
            } else {
                maxHeap.add(num);
            }
        } else {
            if (maxHeap.peek() > num) {
                minHeap.add(maxHeap.poll());
                maxHeap.add(num);
            } else {
                minHeap.add(num);
            }
        }
    }

    public double findMedian() {
        if ((minHeap.size() + maxHeap.size()) % 2 != 0) {
            return (double) maxHeap.peek();
        }
        return (double) (minHeap.peek() + maxHeap.peek()) / 2;
    }
}