package com.trello.qsipders.learntestng;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAssertAutomationScript {
	WebDriver driver;
	@BeforeMethod
	@Parameters({"browserName","url"})
public void configBeforeMethod(String browserName, String url, ITestResult result) {
	if (browserName.equals("edge")) {
		driver = new EdgeDriver();
	}
	else if (browserName.equals("firefox")) {
		driver= new FirefoxDriver();
	}
	else if (browserName.equals("chrome")) {
		driver=new ChromeDriver();
	}
	Reporter.setCurrentTestResult(result);
	Reporter.log("Browser is lancuhed");
	driver.manage().window().maximize();
	driver.get(url);
	Reporter.log("url is trigged");
}
	@AfterMethod
public void configAfterMethod() {
	driver.manage().window().minimize();
	driver.quit();
}
	@Test
	@Parameters({"userNameValue","passwordValue"})
public void checkLogOut(String userNameValue, String passwordValue ) {
    SoftAssert softassert = new SoftAssert();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	softassert.assertEquals(driver.getTitle(), "actiTIME - Login","title is in correct");
	softassert.assertEquals(driver.getCurrentUrl(), "https:/demo.actitime.com/login.do");
	driver.findElement(By.id("username")).sendKeys(userNameValue);
	driver.findElement(By.name("pwd")).sendKeys(passwordValue);
	driver.findElement(By.id("loginButton")).click();
	Reporter.log("Login Successfull");
	//softassert.assertTrue(driver.getTitle().equals("actiTIME -Enter Time-Track"),"title is found incorrect");
	softassert.assertTrue(wait.until(ExpectedConditions.titleIs("actiTIME - Enter Time-Track")), "Title of homepage found incorrect");
	softassert.assertTrue(wait.until(ExpectedConditions.urlToBe("https://demo.actitime.com/user/submit_tt.do")), "url is found incorrect");
	Reporter.log("Home page is displayed");
	softassert.assertTrue(wait.until(ExpectedConditions.invisibilityOfElementLocated( By.xpath("//div[@id='preInsertedTransformedMoireId']"))));
	softassert.assertTrue(wait.until(ExpectedConditions.elementToBeClickable(By.id("logoutLink"))).isDisplayed(),"Logout link is not displayed ");
	wait.until(ExpectedConditions.elementToBeClickable(By.id("logoutLink")));
	driver.findElement(By.id("logoutLink")).click();
	Reporter.log("Logout Successful");
	//if we need the false condition statement we need to put this assert all method
	softassert.assertAll("The script fail due to");
	
}
}
