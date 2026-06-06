package com.slidingwindow;

public class FixedSizeWindow_MaxSumSubarray {

    public static int findMaxSumSubarray(int[] arr, int k) {
        if (arr.length < k) {
            return -1; // Or throw an exception
        }

        int maxSum = 0;
        int windowSum = 0;
        int windowStart = 0;

        // Calculate the sum of the first window
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }
        maxSum = windowSum;

        // Slide the window from the start to the end of the array
        for (int windowEnd = k; windowEnd < arr.length; windowEnd++) {
            // Add the new element to the window
            windowSum += arr[windowEnd];
            // Subtract the element that is leaving the window
            windowSum -= arr[windowStart];

            // Update the start pointer
            windowStart++;

            // Update the maximum sum found so far
            maxSum = Math.max(maxSum, windowSum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 1, 3, 2};
        int k = 3;
        System.out.println("Maximum sum of a subarray of size " + k + ": " + findMaxSumSubarray(arr, k));
        // Output: Maximum sum of a subarray of size 3: 9
        // The subarray is {5, 1, 3}
    }
}
