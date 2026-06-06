package com.dynamicprogramming;

import java.util.Arrays;

public class CoinChange {

	// recursive coin change EXPONENTIAL RUNNING TIME O(2^N)
	public int naiveCoinChange(int M, int[] v, int index) {
		
		if( M < 0 ) return 0;
		if( M == 0 ) return 1;
		
		if( v.length == index ) return 0;
		
		return naiveCoinChange(M-v[index], v, index) + naiveCoinChange(M, v, index+1);
	}
	
	public int  dynamicProgrammingCoinChange(int[] coins, int amount) {
		
		int[] dp = new int[amount + 1];
		Arrays.fill(dp	, amount + 1); // Fill with a large number
		dp[0] = 0; // Base case: 0 coins for amount 0

		for (int i = 1; i <= amount; i++) {
			for (int coin : coins) {
				if (i >= coin) {
					dp[i] = Math.min(dp[i], dp[i - coin] + 1);
				}
			}
		}
		return dp[amount] > amount ? -1 : dp[amount];


	}
	
public static void main(String[] args) {
		
		int[] v = {1,2,3};
		int M = 10;
		
		CoinChange change = new CoinChange();
		
		//System.out.println( change.naiveCoinChange(M, v, 0) );
	System.out.println("Solution "+change.dynamicProgrammingCoinChange(v, M));
	}
}
