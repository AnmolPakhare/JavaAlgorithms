package com;

public class Test {
	
	int i ;
	Integer r;
	int arr[] = new int[10];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer j ; // needs intialisation for local variables
		
		Integer f = null;
			Test t = new Test();
			System.out.println(t.i);
			System.out.println(t.arr[0]);
			System.out.println(t.r);
			System.out.println(f);

			A a = new B();
			a.method(new Integer(123));
			a.method(123);
			B b = new B();
			b.method(123);
			b.method(new Integer(123));
			A.msg();
			B.msg();
	}



}
 class A {

	int a = 1;

	public void method(int i){
		System.out.println("A class is invoked "+a);
	}

	 public static String msg() {
		 System.out.println("A class is invoked ");
		 return "A";
	 }
}

 class B extends A {

	 int a = 2;

	 public void method(int i){
		 System.out.println("B class is invoked "+a);
	 }

	public void method(Integer i){
		System.out.println("B class is invoked "+a);
	}
	 public static String msg() {
		 System.out.println("B class is invoked ");
		 return "B";
	 }
}