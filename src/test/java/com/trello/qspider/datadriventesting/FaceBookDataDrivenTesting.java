package com.trello.qspider.datadriventesting;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FaceBookDataDrivenTesting {
	WebDriver driver;
	Properties pobj;
	
	@BeforeMethod()
public void configBeforeMethod() throws Throwable {
		FileInputStream fis = new FileInputStream("./src/test/resource/facebookdata.properties");
		pobj=new Properties();
		pobj.load(fis);
	driver = new EdgeDriver();
	driver.manage().window().maximize();
	
	
}@AfterMethod
public void configAfterMethod() {
	driver.manage().window().minimize();
	//driver.quit();
	
}
@Test
public void testcase_01() throws InterruptedException {
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	driver.get(pobj.getProperty("url"));
	driver.findElement(By.name("firstname")).sendKeys(pobj.getProperty("firstname"));
	driver.findElement(By.name("lastname")).sendKeys(pobj.getProperty("surname"));
	driver.findElement(By.name("reg_email__")).sendKeys(pobj.getProperty("email"));
	driver.findElement(By.name("reg_email_confirmation__")).sendKeys(pobj.getProperty("email"));
	driver.findElement(By.id("password_step_input")).sendKeys(pobj.getProperty("password"));
	List<WebElement> dobDropDown = driver.findElements(By.xpath("//span/select"));
	for (WebElement dropDown : dobDropDown) {
		if (dropDown.getAttribute("id").equals("day")) {
			Select dayDropDown = new Select(dropDown);
			dayDropDown.selectByVisibleText(pobj.getProperty("day"));
		}
		else if (dropDown.getAttribute("id").equals("month")) {
			Select monthDropDown = new Select(dropDown);
			monthDropDown.selectByVisibleText(pobj.getProperty("month"));
		}
		else if (dropDown.getAttribute("id").equals("year")) {
			Select yearDropDown = new Select(dropDown);
			yearDropDown.selectByVisibleText(pobj.getProperty("year"));
			
		} 
		
	}
	//String radioButtonSelect = "//label[text()='"+pobj.getProperty("radio")+"']/../input[@type='radio']";	
	
	driver.findElement(By.xpath("//label[text()='"+pobj.getProperty("radio")+"']/../input[@type='radio']")).click();
	
	driver.findElement(By.name("websubmit")).click();

	
	Thread.sleep(10000);
	
}
}
