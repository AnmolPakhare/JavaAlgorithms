package com.intervals;

import java.util.*;

public class IntervalIntersection {

    public static List intersectionInterval(int [][] firstintervals,int [][] secondintervals){

        List output = new ArrayList<int[]>();

        Arrays.sort(firstintervals, Comparator.comparing(a -> a[0]));
        Arrays.sort(secondintervals, Comparator.comparing(a -> a[0]));
        int i = 0,j = 0;
        while(i < firstintervals.length && j < secondintervals.length){

            int firstIntervalStart = firstintervals[i][0];
            int secondIntervalStart = secondintervals[j][0];
            int firstIntervalEnd = firstintervals[i][1];
            int secondIntervalEnd = secondintervals[j][1];

            int start = Math.max(firstIntervalStart,secondIntervalStart);
            int end = Math.min(firstIntervalEnd,secondIntervalEnd);

            if(start <= end){
                output.add(new int[]{start,end});
            }
            if (firstIntervalEnd < secondIntervalEnd) i++;
            else j++;
        }
        return output;
    }

    public static void main(String[] args){

        int[][] firstList = {{0,2},{5,10},{13,23},{24,25}};
        int[][] secondList = {{1,5},{8,12},{15,24},{25,26}};
        // Expected output : Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
        List output = intersectionInterval(firstList,secondList);
        printArray(output);

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

    public static void printArray(List<int []> arr){

        arr.stream().forEach(row -> {
            Arrays.stream(row).forEach(element -> System.out.print(element + " "));
            System.out.println();
        });
    }
}
