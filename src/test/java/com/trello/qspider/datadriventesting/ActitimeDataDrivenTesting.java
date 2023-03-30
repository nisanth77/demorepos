package com.trello.qspider.datadriventesting;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActitimeDataDrivenTesting {
	public static WebDriver driver;
public static void main(String[] args) throws IOException, InterruptedException {
	FileInputStream fis = new FileInputStream("./src/test/resource/actitimedata.properties");
	Properties pobj = new Properties();
	pobj.load(fis);
	String browserName = pobj.getProperty("browser");
	if (browserName.equals("chrome")) {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--remote-allow-origins=*");
		driver=new ChromeDriver(option);
		}
	else if (browserName.equals("firefox")) {
		driver=new FirefoxDriver();
	}
	else if (browserName.equals("edge")) {
		driver=new EdgeDriver();
	}
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	driver.get(pobj.getProperty("url"));
	driver.findElement(By.id("username")).sendKeys(pobj.getProperty("un"));
	driver.findElement(By.name("pwd")).sendKeys(pobj.getProperty("pwd"));
	driver.findElement(By.id("loginButton")).click();

	wait.until(ExpectedConditions.titleContains("actiTIME - Enter Time-Track"));
	System.out.println(driver.getTitle());
	if (driver.getTitle().equals(pobj.getProperty("title"))) {
		System.out.println("Home page is dispalyed");
	}
	else {
		System.out.println("home page is not dispalyed");
	}
		
	driver.manage().window().minimize();
	driver.quit();
	
}
}
