package com.pack.tests;

import org.testng.annotations.Test;

public class TestOne {

	@Test(priority = 1)
	public static void firstTest() {
		System.out.println("firstTest");
	}

	@Test(priority = 2)
	public static void secondTest() {
		System.out.println("secondTest");
	}

	@Test(priority = 3)
	public static void thirdTest() {
		System.out.println("thirdTest");
	}

}
