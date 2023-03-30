package com.trello.qspiders.learngroupexecution;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class StoreDataInXml {
	WebDriver driver;
	@Test
	@Parameters("browserName")
	public void launchBrowser(String browserName) {
		if (browserName.equals("edge")) {
			driver=new EdgeDriver();
		}
		else if(browserName.equals("firefox")) {
			driver=new FirefoxDriver();
		}
	}
}
