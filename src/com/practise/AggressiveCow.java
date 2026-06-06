package com.practise;

import java.util.*;
/*

You are given N stalls (positions in a sorted array) and C cows.
The goal is to place the cows in the stalls in such a way that the minimum distance between any two cows is maximized.
*/

public class AggressiveCow {

    // Function to check if we can place cows with at least 'minDist' apart
    public static boolean canPlaceCows(int[] stalls, int cows, int minDist) {
        int count = 1;  // Place the first cow in the first stall
        int lastPlaced = stalls[0];


        for (int i = 1; i < stalls.length; i++) {
            if (stalls[i] - lastPlaced >= minDist) {
                count++;
                lastPlaced = stalls[i];
                if (count == cows) {
                    return true;
                }
            }
        }
        return false;
    }

    // Function to find the maximum minimum distance
    public static int aggressiveCows(int[] stalls, int cows) {
        Arrays.sort(stalls); // Step 1: Sort the stalls

        int low = 1; // Minimum possible distance
        int high = stalls[stalls.length - 1] - stalls[0]; // Maximum possible distance
        int bestDist = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2; // Avoid integer overflow

            if (canPlaceCows(stalls, cows, mid)) {
                bestDist = mid; // Update answer
                low = mid + 1;  // Try for a larger distance
            } else {
                high = mid - 1; // Try for a smaller distance
            }
        }

        return bestDist;
    }

    public static void main(String[] args) {
        int[] stalls = {1, 2, 8, 4, 9};
        int cows = 3;
        System.out.println(aggressiveCows(stalls, cows)); // Output: 3
    }
}

