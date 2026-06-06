package com.slidingwindow;

import java.util.*;

public class SubArrayAverage {

    public static void main (String[] args){

        /*
            Given an array and a target average, find if any contiguous subarray has that exact average.

        */

        int[] arr = {2, 1, 5, 2, 3, 2};
        int[] result = findSubArray(arr,2.6);
        System.out.println(Arrays.toString(result));

    }


    private static int[] findSubArray(int[] arr, double target) {
        // We use a TreeMap to find the "closest" previous sum
        TreeMap<Double, Integer> map = new TreeMap<>();
        map.put(0.0, -1);

        double runningSum = 0;
        double epsilon = 0.02; // Allow for the 0.01 rounding error you found!

        for (int i = 0; i < arr.length; i++) {
            runningSum += (arr[i] - target);

            // Find a previous sum that is very close to our current runningSum
            // This looks for any key between (runningSum - epsilon) and (runningSum + epsilon)
            Double closestKey = map.subMap(runningSum - epsilon, runningSum + epsilon).isEmpty()
                    ? null
                    : map.subMap(runningSum - epsilon, runningSum + epsilon).firstKey();

            if (closestKey != null) {
                int start = map.get(closestKey) + 1;
                return Arrays.copyOfRange(arr, start, i + 1);
            }

            map.putIfAbsent(runningSum, i);
        }

        return new int[0];
    }
}
