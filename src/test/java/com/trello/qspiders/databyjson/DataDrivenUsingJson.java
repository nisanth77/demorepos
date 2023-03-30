package com.trello.qspiders.databyjson;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DataDrivenUsingJson {
	            
		public static WebDriver driver;
	public static void main(String[] args) throws IOException, InterruptedException, ParseException {
		      JSONParser jsonpaser= new JSONParser();
		      FileReader fileReader= new FileReader("./src/test/resource/actitime.json");
		      //TAKE THE DATA FROM FILE BUT RETURN IS OBJECT
		      Object parsedObject = jsonpaser.parse(fileReader);
		      //DOWNCAST TO JSONOBJECT
		     JSONObject jsonObject = (JSONObject) parsedObject;
		     //DOWNCAST TO STRING
		    String browserName=(String) jsonObject.get("browser");
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
		driver.get((String)jsonObject.get("url"));
		driver.findElement(By.id("username")).sendKeys(((String)jsonObject.get("un")));
		driver.findElement(By.name("pwd")).sendKeys((String)jsonObject.get("pwd"));
		driver.findElement(By.id("loginButton")).click();

		wait.until(ExpectedConditions.titleContains("actiTIME - Enter Time-Track"));
		System.out.println(driver.getTitle());
		if (driver.getTitle().equals((String)jsonObject.get("title"))) {
			System.out.println("Home page is dispalyed");
		}
		else {
			System.out.println("home page is not dispalyed");
		}
			
		driver.manage().window().minimize();
		driver.quit();
	}
}
