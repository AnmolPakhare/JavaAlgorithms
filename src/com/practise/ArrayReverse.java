package com.practise;

import java.util.Arrays;

public class ArrayReverse {


        // this is the algorithm that reverses the input array (nums)
        public int[] solve(int[] nums) {

            // hint: define 2 pointers (pointing to the last and first item of the array) and make a single iteration ...
            int start = 0;
            int end = nums.length - 1;

            while (start < end) {
                // Swap elements at start and end indices
                swap(nums,start,end);

                // Move towards the center of the array
                start++;
                end--;
            }


            return nums;
        }

        private void swap(int[] nums, int index1, int index2) {

            int temp = nums[index1];
            nums[index1] = nums[index2];
            nums[index2] = temp;

        }

        public static void main(String[] args){

            int[] nums = {3,6,5,2,7,8};

            ArrayReverse test = new ArrayReverse();
            Arrays.stream(test.solve(nums)).forEach(System.out::print);
        }

}
