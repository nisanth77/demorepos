package com.trello.qsipders.learntestng;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HardAssertAutomationScript {
	WebDriver driver;
	   Properties pobj;
	   @BeforeMethod
	public void configBeforeMethod() throws Throwable {
		
		FileInputStream fis = new FileInputStream("./src/test/resource/hardassert.properties");
	        pobj=new Properties(); 
	        pobj.load(fis);
	       String browserName = pobj.getProperty("browserName");
		if (browserName.equals("edge")) {
			driver = new EdgeDriver();
		}
		else if (browserName.equals("firefox")) {
			driver= new FirefoxDriver();
		}
		else if (browserName.equals("chrome")) {
			driver=new ChromeDriver();
		}
		
		driver.manage().window().maximize();
		driver.get(pobj.getProperty("url"));
	
	}
	   @AfterMethod
	public void configAfterMethod() {
		//driver.manage().window().minimize();
		//driver.quit();
	}
	   @Test
	public void checkLogOut( ) {
		   //for hard and soft assert we should not use explictwait for condition if it is false it throw exception
	       SoftAssert softAssert = new SoftAssert();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		Assert.assertEquals(driver.getTitle(), "actiTIME - Login","title is in correct");
		softAssert.assertEquals(driver.getCurrentUrl(), "https:/demo.actitime.com/login.do");
		driver.findElement(By.id("username")).sendKeys(pobj.getProperty("userNameValue"));
		driver.findElement(By.name("pwd")).sendKeys(pobj.getProperty("passwordValue"));
		driver.findElement(By.id("loginButton")).click();
		Reporter.log("Login Successfull");
		//softassert.assertTrue(driver.getTitle().equals("actiTIME - Enter Time-Track"),"title is found incorrect");
		Assert.assertTrue(wait.until(ExpectedConditions.titleIs("actiTIME -    Enter Time-Track")), "Title of homepage found incorrect");
		Assert.assertTrue(wait.until(ExpectedConditions.urlToBe("https://demo.actitime.com/user/submit_tt.do")), "url is found incorrect");
		Reporter.log("Home page is displayed");
		Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOfElementLocated( By.xpath("//div[@id='preInsertedTransformedMoireId']"))));
		Assert.assertTrue(wait.until(ExpectedConditions.elementToBeClickable(By.id("logoutLink"))).isDisplayed(),"Logout link is not displayed ");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("logoutLink")));
		driver.findElement(By.id("logoutLink")).click();
		Reporter.log("Logout Successful");
		softAssert.assertAll("The script problem in");
}
}