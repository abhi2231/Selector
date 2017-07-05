package com.pack.tests;

import org.testng.annotations.Test;

public class TestThree {

	@Test(priority = 7)
	public static void seventhTest() {
		System.out.println("seventhTest");
	}

	@Test(priority = 8)
	public static void eightTest() {
		System.out.println("eightTest");
	}

	@Test(priority = 9)
	public static void ninethTest() {
		System.out.println("ninethTest");
	}

	@Test(priority = 10)
	public static void tenthTest() {
		System.out.println("tenthTest");
	}
}
