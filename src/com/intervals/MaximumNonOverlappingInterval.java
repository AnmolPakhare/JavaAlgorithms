package com.intervals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MaximumNonOverlappingInterval {

    public static int maximumNonOverlappingInterval(int [][] intervals){


        Arrays.sort(intervals, Comparator.comparing(a -> a[1]));
        int count = 0, lastEnd = Integer.MIN_VALUE;
        for (int[] interval : intervals) {
            if (interval[0] >= lastEnd) {
                count++;
                lastEnd = interval[1];
            }
        }
        return count;
    }

    public static int maximumNonOverlappingInterval2(int [][] intervals){

        Arrays.sort(intervals, Comparator.comparing(a -> a[1]));
        PriorityQueue<Integer> q = new PriorityQueue<Integer>();
        q.offer(intervals[0][1]);
        for(int i=1; i < intervals.length; i++){
            if(intervals[i][0] >= q.peek() ) q.offer(intervals[i][1]);
        }

        return q.size();
    }

    public static void main(String[] args){

        // For max overlapping , sort by end time of interval
        // for min , or merge , sort by start time of interval
        int [][] input = {{1,3},{2,4},{3,5},{0,6},{5,7},{8,9}};
        int output = maximumNonOverlappingInterval2(input);
        System.out.println(output);

    }
}
