package com.fibonacci;

public class App {

	public static void main(String[] args) {

		FibonacciAlgorithm fibonacciAlgorithm = new FibonacciAlgorithm();
		long startTime = System.currentTimeMillis();
		fibonacciAlgorithm.fibonacciMemoize(30);
		long endTime = System.currentTimeMillis();
		System.out.println(fibonacciAlgorithm.memoizeTable);
		System.out.println("Memoize Time taken: " + (endTime - startTime) + " milliseconds");
		startTime = System.currentTimeMillis();
		System.out.println(fibonacciAlgorithm.fibonacciTabulation(30));
		endTime = System.currentTimeMillis();
		System.out.println("Tabulation Time taken: " + (endTime - startTime) + " milliseconds");
		startTime = System.currentTimeMillis();
		for(int i =0; i <= 30 ; i++)
			System.out.print(fibonacciAlgorithm.naiveFibonacci(i)+ " ");

		endTime = System.currentTimeMillis();
		System.out.println();
		System.out.println("Recursion Time taken: " + (endTime - startTime) + " milliseconds");


	}
}
