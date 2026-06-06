package com.eggdropping;

public class App {

	public static void main(String args[]) {
		EggDroppingProblem eggDroppingProblem = new EggDroppingProblem();
		System.out.println("Minimum number of drops to find the critical floor: "+eggDroppingProblem.solve());
	}
}
