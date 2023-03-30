package com.trello.qspiders.learngroupexecution;

import org.testng.annotations.Test;

public class GroupB {
	@Test(groups ="sanity" )
	public void nameD() {
		System.out.println("nameD");
	}
	@Test(groups = {"functional","performance"})
	public void nameE() {
		System.out.println("nameE");
	}
	@Test(groups = "performance")
	public void nameF() {
		System.out.println("nameF");
	}
}
