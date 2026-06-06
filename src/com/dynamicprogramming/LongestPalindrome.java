package com.dynamicprogramming;

public class LongestPalindrome {

    public static String longestPalindrome(String s) {
        int n = s.length();
        if (n == 0) return "";

        boolean[][] dp = new boolean[n][n];
        int start = 0, maxLength = 1;

        // Every single character is a palindrome
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

       // System.out.println(dp);

        // Check for two-character palindromes
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                start = i;
                maxLength = 2;
            }
        }

        // Check for substrings longer than 2
        for (int len = 3; len <= n; len++) { // Length of substring
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1; // Ending index

                // Check if the inner substring is palindrome
                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    start = i;
                    maxLength = len;
                }
            }
        }

        return s.substring(start, start + maxLength);
    }

    public static void main(String[] args) {
        String s = "babad";
        System.out.println("Longest Palindromic Substring: " + longestPalindrome(s)); // "bab" or "aba"

        s = "racecar";
        System.out.println("Longest Palindromic Substring: " + longestPalindrome(s)); // "bb"
    }
}
