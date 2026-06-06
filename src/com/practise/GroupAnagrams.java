package com.practise;

import java.util.*;

public class GroupAnagrams {
    public static List<List<String>> groupAnagrams(String[] words) {
        Map<String, List<String>> map = new HashMap<>();

        for (String word : words) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);  // Sort the characters
            String sortedWord = new String(chars);

            // Add to hashmap
            map.computeIfAbsent(sortedWord, k -> new ArrayList<>()).add(word);
        }

        return new ArrayList<>(map.values()); // Convert to list of lists
    }

    public static void main(String[] args) {
        String[] words = {"bat", "tab", "cat", "act", "tac", "dog", "god"};
        System.out.println(groupAnagrams(words));
    }
}
