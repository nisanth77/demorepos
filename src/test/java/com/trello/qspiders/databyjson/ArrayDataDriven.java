package com.trello.qspiders.databyjson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.Duration;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ArrayDataDriven {
	WebDriver driver;
	@BeforeMethod
public void configBeforeMethod() {
	String browserName="chrome";
	String url="https://demo.actitime.com/";
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
}
	@AfterMethod
public void configAfterMethod() {
	driver.manage().window().minimize();
	driver.quit();
}
	@Test(dataProvider = "readData")
	public void vaildUserName(String data) {
		  String user[]=data.split(",");
		 SoftAssert softassert = new SoftAssert();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.findElement(By.id("username")).sendKeys(user[0]);
		driver.findElement(By.name("pwd")).sendKeys(user[1]);
		driver.findElement(By.id("loginButton")).click();
		wait.until(ExpectedConditions.titleContains(user[3]));;
		softassert.assertEquals(driver.getTitle(), user[3],"Title found in correct");
		Reporter.log("Login successfull");
		
		
	}
	@DataProvider
	public String[] readData() throws Throwable {
		JSONParser jsonpaser= new JSONParser();
		FileReader fileReader= new FileReader("./src/test/resource/arrayactitime.json");
		Object object = jsonpaser.parse(fileReader);
		  JSONObject jsonObject= (JSONObject) object;
			JSONArray jsonArray = (JSONArray) jsonObject.get("userlogin");
			String [] str= new String[jsonArray.size()];
			String url=(String) jsonObject.get("url");
			String title= (String) jsonObject.get("title");
			String browser=(String) jsonObject.get("browser");
			for (int i = 0; i < jsonArray.size(); i++) 
			{
				  JSONObject jsonArrayObject=(JSONObject) jsonArray.get(i);
				  String un=(String) jsonArrayObject.get("un");
				  String pwd= (String) jsonArrayObject.get("pwd");
				  str[i]=un+","+pwd+","+url+","+title+","+browser;
				
			}
		return str;
	}
}
