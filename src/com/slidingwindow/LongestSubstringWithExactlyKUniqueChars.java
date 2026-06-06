package com.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithExactlyKUniqueChars {


    public static int findLongestSubstring(String str, int uniqueChars) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        int maxLength = 0;
        int windowStart = 0;
        // Map to store characters and their last seen index
        Map<Character, Integer> charIndexMap = new HashMap<>();

        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);

            // If the character is already in the window, shrink the window
            // Move the start pointer to the right of the previous occurrence
            if (charIndexMap.containsKey(rightChar)) {
                windowStart = Math.max(windowStart, charIndexMap.get(rightChar) + 1);
            }

            // Add the current character and its index to the map
            charIndexMap.put(rightChar, windowEnd);

            // Calculate the length of the current window and update max length
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }
        return maxLength;
    }


    public static void main(String[] args){

        String s = "aabacbebebe"; int uniqueChars = 3;
        System.out.println(findLongestSubstring(s,uniqueChars));
    }
}
