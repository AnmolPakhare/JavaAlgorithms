package com.intervals;

import java.util.*;

public class FreeTimeInterval {

    public static List freeTimeInterval(int [][] intervals){

        List output = new ArrayList<int[]>();

        Arrays.sort(intervals, Comparator.comparing(a -> a[0]));
        int[] current = intervals[0];
        for(int i = 1; i < intervals.length; i++){
            if(intervals[i-1][1] < intervals[i][0]){
                output.add(new int[]{intervals[i-1][1],intervals[i][0]});
            }
        }
        return output;
    }

    public static void main(String[] args){

        int [][] input = {{1,3},{6,7},{2,4},{2,5},{9,12}};
        List output = freeTimeInterval(input);
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
