package com.hendall.surveyrest.assemblers;

public class Test {
	
	public void test(String s){
		s="1234";
	}

	public static void main(String[] args) {
		String s= new String("test");
		Test test = new Test();
		test.test(s);;
		System.out.println(s);
	}
}
