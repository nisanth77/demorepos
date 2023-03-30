package com.trello.qspiders.learnactionclass;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class ActionOnMytra {
	WebDriver driver;
	@BeforeMethod
public void configBeforeMethod() {
	String browserName="chrome";
	String url="https://www.myntra.com/";
	if (browserName.equals("chrome")) {
		driver=new ChromeDriver();
	}
	else if (browserName.equals("edge")) {
		driver= new EdgeDriver();
	}
	else if (browserName.equals("firefox")) {
		driver= new FirefoxDriver();
	}
	driver.manage().window().maximize();
	driver.get(url);
}@AfterMethod
public void configAfterMethod() {
	driver.manage().window().minimize();
	driver.quit();
}
@Test
public void moveMousePointerOnMenSection() {
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
	WebElement menSectionNav = driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Men']"));
	Actions actions = new Actions(driver);
	actions.moveToElement(menSectionNav).pause(10000).perform();
	
}
@Test
public void moveOnSoManyElements() {
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
	List<WebElement> optionOnMenuNav = driver.findElements(By.xpath("//div[@class='desktop-navLink']"));
	Actions actions =new Actions(driver);
	for (WebElement optionNavigation : optionOnMenuNav) {
		actions.moveToElement(optionNavigation).pause(2000).perform();
	}
}
@Test
public void moveOnAllElementInTopWear() {
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
	WebElement menSectionNav = driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Men']"));
	Actions actions = new Actions(driver);
	actions.clickAndHold(menSectionNav).moveToElement(driver.findElement(By.xpath("//a[@href='/men-tshirts']"))).
	moveByOffset(0, 24).pause(1000).build().perform();
	/*List<WebElement> topWearOptions = driver.findElements(By.xpath("//ul[@data-reactid='27']//a[contains(@href,'/men')]"));
	for (WebElement topWearNav : topWearOptions) {
		actions.moveToElement(topWearNav).pause(2000).perform();
	}*/
	
}
@Test
public void moveElementUsingMoveByOffsetMethod() {
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
	WebElement menSectionNav = driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Men']"));
	Actions actions = new Actions(driver);
	actions.moveToElement(menSectionNav).pause(1000).moveByOffset(73, 0).pause(2000).moveByOffset(105, 0).pause(2000).
	moveByOffset(168, 0).pause(2000).moveByOffset(212, 0).pause(2000).moveByOffset(437, 0).pause(2000).build().perform();
	//actions.moveToElement(menSectionNav).pause(1000).moveByOffset(74, 0).pause(1000).moveByOffset(105, 0).pause(2000).moveByOffset(203, 0).pause(1000).moveByOffset(354, 0).pause(1000).moveByOffset(437, 0).build().perform();
}
}
