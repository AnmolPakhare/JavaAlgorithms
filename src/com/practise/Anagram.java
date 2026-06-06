package com.practise;

import java.util.Arrays;

public class Anagram {


        public boolean solve(char[] s1, char[] s2) {

            // you get the s1 and s2 strings (char sequences) and you have to return true (if they are anagram) or false otherwise
            if(s1.length != s2.length){
                return false;
            } else {

                String str1 = sortString(s1);
                String str2 = sortString(s2);
                int i = 0;
                int len = str1.length();
                boolean flag = true;
                while(i < len){

                    if(s1[i] != s2[i]){
                        flag = false;
                        break;
                    }
                    i++;
                }
                return flag;
            }
        }

        public String sortString(char[] input) {

            Arrays.sort(input);
            // Convert the sorted character array back to a string
            String sortedString = new String(input);

            return sortedString;
        }

    public static void main(String[] args) {

        int[] nums = {3, 6, 5, 2, 7, 8};

        Anagram test = new Anagram();
        System.out.println(test.solve("restful".toCharArray(), "fluster".toCharArray()));
    }

}
