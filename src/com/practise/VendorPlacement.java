package com.practise;

import java.util.Arrays;

public class VendorPlacement {

    public static boolean allocateSpace(int[] spaces, int shopcount, int distance){

        Arrays.sort(spaces);
        boolean flag = false;
        int lastposition = spaces[0];
        int cnt = 1;
        for(int i=1; i < spaces.length; i++){

            if((spaces[i] - lastposition) >= distance){
                cnt++;
                lastposition = spaces[i];
                if(cnt == shopcount) {flag = true; break;}
            }

        }
        return flag;
    }


    public static void main(String[] args){

        int[] spaces = {1, 2, 8, 4, 9};
        int k = 3;
        int d = 3;
        System.out.print(allocateSpace(spaces,k, d));
    }
}
