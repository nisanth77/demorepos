package com.trello.qsipders.learntestng;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UsageOfBeforeAndAfterMethod {
	WebDriver driver;
	//before and after method will run each and every no of test anotation present
	@BeforeMethod
public void configBeforeMethod() {
	 driver= new EdgeDriver();
	driver.manage().window().maximize();
	driver.get("https://demo.actitime.com/login.do");
}
	@AfterMethod
public void configAfterMethod() {
 driver.manage().window().minimize();
 driver.quit();
}
	@Test
	public void testcase_01() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.findElement(By.id("loginButton")).click();
		//wait.until(ExpectedConditions.titleContains("actiTIME - Enter Time-Track"));
		if (driver.getTitle().equals("actiTIME - Enter Time-Track")) {
			System.out.println("fail");
		}else {
			System.out.println("pass");
		}
		
	}
}
