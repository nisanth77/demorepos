package com.trello.qsipders.learntestng;

import org.testng.annotations.Test;

public class UasgeOfTestAnnotation {
	//priority = testcase order of execution, if priority is not given it will excute first
	//enabled= whether the test case is excuted or not
	//invocationcount = no of time we want test case to be excuted
	@Test(priority = 2,enabled = false)
	public void orangeTest() {
		System.out.println("orangeTest");
	}
	@Test(priority = 1,invocationCount = 2)
public void appleTest() {
	System.out.println("appleTest");
}
	@Test
public void watermelonTest() {
	System.out.println("watermelonTest");
}
}
