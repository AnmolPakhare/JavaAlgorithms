package com.fibonacci;

import java.util.HashMap;
import java.util.Map;

public class FibonacciAlgorithm {

	public Map<Integer, Integer> memoizeTable; // O(1) (top to down approach)

	private Map<Integer, Integer> tabulationTable; // (Bottom to top approach)
	
	public FibonacciAlgorithm(){
		this.memoizeTable = new HashMap<>();
		this.tabulationTable = new HashMap<>();
		this.memoizeTable.put(0, 0);
		this.memoizeTable.put(1, 1);
		this.tabulationTable.put(0, 0);
		this.tabulationTable.put(1, 1);
	}
	
	public int fibonacciMemoize(int n){ // top - bottom approach
		
		if( this.memoizeTable.containsKey(n) ) {
			return this.memoizeTable.get(n);
		}
		
		this.memoizeTable.put(n-1, fibonacciMemoize(n-1));
		this.memoizeTable.put(n-2, fibonacciMemoize(n-2));
		
		int calculatedNumber = this.memoizeTable.get(n-1) + this.memoizeTable.get(n-2);
		this.memoizeTable.put(n, calculatedNumber);
		
		return calculatedNumber;
		
	}

	public Map fibonacciTabulation(int n) // bottom - top approach
	{

		for (int i = 2; i <= n; i++)
		{
			tabulationTable.put(i,tabulationTable.get(i-1)+tabulationTable.get(i-2));
		}

		return tabulationTable;
	}
	
	public int naiveFibonacci(int n){
		
		if( n == 0 ) return 0;
		if( n == 1 ) return 1;
		
		return (naiveFibonacci(n-1) + naiveFibonacci(n-2));	
	}
}
