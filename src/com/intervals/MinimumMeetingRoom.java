package com.intervals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MinimumMeetingRoom {

    public static int minimumMeetingRooms(int [][] input){


        Arrays.sort(input, Comparator.comparing(a -> a[0]));
        PriorityQueue<Integer> q = new PriorityQueue<Integer>();
        for(int[] interval : input){
            if(!q.isEmpty() && q.peek() <= interval[0]) q.poll();
            q.offer(interval[1]);
        }

        return q.size();
    }

    public static void main(String[] args){

        int [][] input = {{1, 5}, {2, 6}, {4, 8}, {9, 12}};
        int output = minimumMeetingRooms(input);
        System.out.println(output);

    }
}
