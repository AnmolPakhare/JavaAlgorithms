package com.practise;

public class TrappingRainWater {
    public static int trap(int[] height) {
        if (height == null || height.length == 0) return 0;

        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0, water = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];  // Update left max
                } else {
                    water += leftMax - height[left];  // Trap water
                }
                left++;  // Move left pointer
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];  // Update right max
                } else {
                    water += rightMax - height[right];  // Trap water
                }
                right--;  // Move right pointer
            }
        }

        return water;
    }

    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println("Trapped water: " + trap(height)); // Output: 6
    }
}

