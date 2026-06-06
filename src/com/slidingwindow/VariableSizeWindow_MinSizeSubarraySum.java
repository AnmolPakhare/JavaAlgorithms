package com.slidingwindow;

public class VariableSizeWindow_MinSizeSubarraySum {

    public static int findMinSubArray(int S, int[] arr) {
        int minLength = Integer.MAX_VALUE;
        int windowSum = 0;
        int windowStart = 0;

        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            // Expand the window by adding the right element
            windowSum += arr[windowEnd];

            // Shrink the window from the left as long as the sum is >= S
            while (windowSum >= S) {
                // Update the minimum length found so far
                minLength = Math.min(minLength, windowEnd - windowStart + 1);

                // Subtract the leftmost element and move the start pointer
                windowSum -= arr[windowStart];
                windowStart++;
            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    public static void main(String[] args) {
        int S = 12;
        int[] arr = {2, 1, 5, 2, 3, 2};
        System.out.println("Smallest subarray length: " + findMinSubArray(S, arr));
        // Output: Smallest subarray length: 2
        // The subarray is {5, 2}
    }
}
