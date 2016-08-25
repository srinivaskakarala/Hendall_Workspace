package com.test;

import java.util.Arrays;

public class TEst {
	
	public static void main(String[] args) {
	
	
		final String separator = ",";
		
		Arrays.asList( "a", "b", "d" ).forEach(
		    (  e ) -> System.out.print( e + separator ) );

	}

}
