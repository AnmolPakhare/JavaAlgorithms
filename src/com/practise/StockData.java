package com.practise;

import java.util.*;

class StockData {
    private Map<Integer, Integer> timestampToPrice; // Maps timestamp to price
    private PriorityQueue<int[]> maxHeap; // Max-heap to track maximum price
    private Map<Integer, Integer> priceToCount; // Tracks occurrences of each price
    private int latestTimestamp; // Tracks the latest timestamp

    public StockData() {
        timestampToPrice = new HashMap<>();
        maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]); // Max-heap based on price
        priceToCount = new HashMap<>();
        latestTimestamp = 0;
    }

    // Operation 1: Insert the stock data
    public void insert(int timestamp, int price) {
        timestampToPrice.put(timestamp, price);
        if (timestamp > latestTimestamp) {
            latestTimestamp = timestamp;
        }
        maxHeap.offer(new int[]{price, timestamp});
        priceToCount.put(price, priceToCount.getOrDefault(price, 0) + 1);
    }

    // Operation 2: Retrieve the stock price of the most recent timestamp
    public int getLatestPrice() {
        return timestampToPrice.get(latestTimestamp);
    }

    // Operation 3: Update the data value with a timestamp
    public void update(int timestamp, int price) {
        if (timestampToPrice.containsKey(timestamp)) {
            int oldPrice = timestampToPrice.get(timestamp);
            priceToCount.put(oldPrice, priceToCount.get(oldPrice) - 1);
            if (priceToCount.get(oldPrice) == 0) {
                priceToCount.remove(oldPrice);
            }
        }
        timestampToPrice.put(timestamp, price);
        if (timestamp > latestTimestamp) {
            latestTimestamp = timestamp;
        }
        maxHeap.offer(new int[]{price, timestamp});
        priceToCount.put(price, priceToCount.getOrDefault(price, 0) + 1);
    }

    // Operation 4: Get the highest stock price in the current data structure
    public int getHighestPrice() {
        while (!maxHeap.isEmpty() && timestampToPrice.get(maxHeap.peek()[1]) != maxHeap.peek()[0]) {
            maxHeap.poll();
        }
        return maxHeap.peek()[0];
    }

    // Operation 5: Delete the stock data at a particular timestamp
    public void delete(int timestamp) {
        if (!timestampToPrice.containsKey(timestamp)) {
            return; // Timestamp does not exist
        }
        int price = timestampToPrice.get(timestamp);
        timestampToPrice.remove(timestamp);
        priceToCount.put(price, priceToCount.get(price) - 1);
        if (priceToCount.get(price) == 0) {
            priceToCount.remove(price);
        }
        if (timestamp == latestTimestamp) {
            latestTimestamp = Collections.max(timestampToPrice.keySet());
        }
    }

    public static void main(String[] args) {
        StockData stockData = new StockData();
        stockData.insert(1, 100); // Insert (1, 100)
        stockData.insert(2, 200); // Insert (2, 200)
        System.out.println(stockData.getLatestPrice()); // Output: 200 (latest price)
        stockData.update(1, 150); // Update timestamp 1 to price 150
        System.out.println(stockData.getHighestPrice()); // Output: 200 (highest price)
        stockData.delete(2); // Delete timestamp 2
        System.out.println(stockData.getLatestPrice()); // Output: 150 (latest price after deletion)
    }
}
