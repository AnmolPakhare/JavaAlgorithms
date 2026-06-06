package com.practise;

import java.util.*;

class StockDataStructure {
    private TreeMap<Integer, Integer> stockData; // (timestamp -> stock price)
    private TreeMap<Integer, Integer> priceCount; // (price -> frequency)

    public StockDataStructure() {
        stockData = new TreeMap<>();
        priceCount = new TreeMap<>();
    }

    // Insert or update stock price
    public void insertOrUpdate(int timestamp, int price) {
        if (stockData.containsKey(timestamp)) {
            int oldPrice = stockData.get(timestamp);
            removePrice(oldPrice); // Remove old price from priceCount
        }
        stockData.put(timestamp, price);
        addPrice(price);
    }

    // Get the stock price of the most recent timestamp
    public int getRecentStockPrice() {
        return stockData.get(stockData.lastKey());
    }

    // Get the highest stock price
    public int getMaxStockPrice() {
        return priceCount.lastKey();
    }

    // Delete stock data at a specific timestamp
    public void deleteStock(int timestamp) {
        if (!stockData.containsKey(timestamp)) return;

        int price = stockData.get(timestamp);
        removePrice(price);
        stockData.remove(timestamp);
    }

    // Helper method to add price to priceCount map
    private void addPrice(int price) {
        priceCount.put(price, priceCount.getOrDefault(price, 0) + 1);
    }

    // Helper method to remove price from priceCount map
    private void removePrice(int price) {
        if (priceCount.get(price) == 1) {
            priceCount.remove(price);
        } else {
            priceCount.put(price, priceCount.get(price) - 1);
        }
    }

    // Print stock data (for debugging)
    public void printStockData() {
        System.out.println("Stock Data: " + stockData);
        System.out.println("Price Frequency: " + priceCount);
    }

    public static void main(String[] args) {
        StockDataStructure stockDS = new StockDataStructure();

        // Insert stock prices
        stockDS.insertOrUpdate(1, 300);
        stockDS.insertOrUpdate(2, 230);
        stockDS.insertOrUpdate(3, 123);
        stockDS.insertOrUpdate(2, 400); // Update timestamp 2
        stockDS.insertOrUpdate(4, 500);
        stockDS.insertOrUpdate(5, 450);

        System.out.println("Recent Stock Price: " + stockDS.getRecentStockPrice()); // Should be 450
        System.out.println("Highest Stock Price: " + stockDS.getMaxStockPrice()); // Should be 500

        // Delete a stock price and check again
        stockDS.deleteStock(4); // Deleting timestamp 4 (500)
        System.out.println("Highest Stock Price after deletion: " + stockDS.getMaxStockPrice()); // Should be 450

        stockDS.printStockData();
    }
}

