package com.slidingwindow;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SlidingWindowPractise {



    public static Set longestSubString(String input){

        int maxSubString = 0, windowStart = 0;
        Map characterIntegerHashMap = new HashMap<Character,Integer>();
        for(int windowEnd = 0; windowEnd < input.length(); windowEnd ++){

            char c = input.charAt(windowEnd);
            if(characterIntegerHashMap.containsKey(c)){
                windowStart = windowEnd;
                characterIntegerHashMap.clear();
            }
            characterIntegerHashMap.put(c,windowEnd);
            maxSubString = Math.max(maxSubString,(windowEnd - windowStart + 1));
        }

        return characterIntegerHashMap.keySet();
    }

    public static void main (String [] args){

        //Longest continuous non duplicate chars in string
        String str = "abcaefghabcbbsdfg";
        Set continuousChars = longestSubString(str);
        System.out.println(continuousChars.stream().count());
    }
}
