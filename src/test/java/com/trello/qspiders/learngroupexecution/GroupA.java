package com.trello.qspiders.learngroupexecution;

import org.testng.annotations.Test;

public class GroupA {
	@Test(groups="adhoc")
	public void nameA() {
		System.out.println("nameA");
	}
	@Test(groups = {"functional","integration"})
	public void nameB() {
		System.out.println("nameB");
	}
	@Test(groups = {"integration","smoke"})

	public void nameC() {
		System.out.println("nameC");
	}
}

