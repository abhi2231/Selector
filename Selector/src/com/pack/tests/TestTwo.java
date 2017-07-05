package com.pack.tests;

import org.testng.annotations.Test;

public class TestTwo {

	@Test(priority = 4)
	public static void fourthTest() {
		System.out.println("fourthTest");
	}

	@Test(priority = 5)
	public static void fifthTest() {
		System.out.println("fifthTest");
	}

	@Test(priority = 6)
	public static void sixthTest() {
		System.out.println("sixthTest");
	}

}
