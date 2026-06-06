package com.practise;

import java.util.*;

class StockPrice {
    private Map<Integer, Integer> timestampToPrice; // Maps timestamp to price
    private PriorityQueue<int[]> maxHeap; // Max-heap to track maximum price
    private PriorityQueue<int[]> minHeap; // Min-heap to track minimum price
    private int latestTimestamp; // Tracks the latest timestamp

    public StockPrice() {
        timestampToPrice = new HashMap<>();
        maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]); // Max-heap based on price
        minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]); // Min-heap based on price
        latestTimestamp = 0;
    }

    public void update(int timestamp, int price) {
        // Update the price for the given timestamp
        timestampToPrice.put(timestamp, price);

        // Update the latest timestamp if necessary
        if (timestamp > latestTimestamp) {
            latestTimestamp = timestamp;
        }

        // Push the new price to both heaps
        maxHeap.offer(new int[]{price, timestamp});
        minHeap.offer(new int[]{price, timestamp});
    }

    public int current() {
        // Return the price at the latest timestamp
        return timestampToPrice.get(latestTimestamp);
    }

    public int maximum() {
        // Ensure the top of the max-heap has the correct price
        while (!maxHeap.isEmpty() && timestampToPrice.get(maxHeap.peek()[1]) != maxHeap.peek()[0]) {
            maxHeap.poll();
        }
        return maxHeap.peek()[0];
    }

    public int minimum() {
        // Ensure the top of the min-heap has the correct price
        while (!minHeap.isEmpty() && timestampToPrice.get(minHeap.peek()[1]) != minHeap.peek()[0]) {
            minHeap.poll();
        }
        return minHeap.peek()[0];
    }

    public static void main(String[] args) {
        StockPrice stockPrice = new StockPrice();
        stockPrice.update(1, 10); // Timestamp = 1, Price = 10
        stockPrice.update(2, 5);  // Timestamp = 2, Price = 5
        System.out.println(stockPrice.current());  // Output: 5 (latest price)
        System.out.println(stockPrice.maximum());  // Output: 10 (maximum price)
        System.out.println(stockPrice.minimum());  // Output: 5 (minimum price)
        stockPrice.update(1, 3);  // Correct price at timestamp 1 to 3
        System.out.println(stockPrice.maximum());  // Output: 5 (updated maximum price)
    }
}
