package com.trello.qspiders.learngroupexecution;

import java.time.Duration;

import org.checkerframework.common.util.report.qual.ReportCall;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CrossBrowserTestingUsingActitime {
	WebDriver driver ;
	@BeforeMethod
	@Parameters({"browserName","url"})
public void configBeforeMethod(String browserName, String url) {
	if (browserName.equals("chrome")) {
		driver= new ChromeDriver();
		
	}
	else if (browserName.equals("edge")) {
		driver= new EdgeDriver();
		
	}
	else if (browserName.equals("firefox")) {
		driver= new FirefoxDriver();
		
	}
	driver.manage().window().maximize();
	driver.get(url);
}
	@AfterMethod
public void configAfterMethod() {
	driver.manage().window().minimize();
	driver.quit();
}
	@Test(groups = "adhoc")
	@Parameters({"userName","userNameValue","password","passwordValue","loginButton","logout","browserName","url"})
public void logoutTestcase(String userName, String userNameValue, String password, String passwordValue, String loginButton, String logout,String browserName, String url) throws Exception {
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	driver.findElement(By.id(userName)).sendKeys(userNameValue);
	driver.findElement(By.name(password)).sendKeys(passwordValue);
	driver.findElement(By.id(loginButton)).click();
	
	Reporter.log("login succesfull");
    wait.until(ExpectedConditions.elementToBeClickable(By.id(logout)));
    Thread.sleep(5000);
	driver.findElement(By.id(logout)).click();
	Reporter.log("logout successful end to end testing is pass");
}
}
