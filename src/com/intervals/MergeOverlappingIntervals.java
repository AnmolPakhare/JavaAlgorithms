package com.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class MergeOverlappingIntervals {


    public static List mergeIntervals(int [][] input){

        List output = new ArrayList<int[]>();

        Arrays.sort(input, Comparator.comparing(a -> a[0]));
        int[] current = input[0];
        for(int i = 1; i < input.length; i++){
            if(current[1] >= input[i][0]){
                current[1] = Math.max(current[1],input[i][1]);
            } else {
                output.add(current);
                current = input[i];
            }
        }
        output.add(current);
        return output;
    }

    public static void main(String[] args){

        int [][] input = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        List<int []> output = mergeIntervals(input);
        printArray(output);
    }

    public static void printArray(List<int []> arr){

        arr.stream().forEach(row -> {
            Arrays.stream(row).forEach(element -> System.out.print(element + " "));
            System.out.println();
        });
    }
}
