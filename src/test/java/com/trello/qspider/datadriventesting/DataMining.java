package com.trello.qspider.datadriventesting;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DataMining {
	public static void main(String[] args) {
		List<String> dayOption = new ArrayList<String>();
		List<String> monthOption = new ArrayList<String>();
		List<String> yearOption = new ArrayList<String>();

		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.get("https://www.facebook.com/signup");
		List<WebElement> allDropDown = driver.findElements(By.tagName("select"));
		for (WebElement dropDown : allDropDown) {
			if (dropDown.isDisplayed()) {
				if (dropDown.getAttribute("id").equals("day")) {
					Select dayDropDown = new Select(dropDown);
					List<WebElement> dayPresent = dayDropDown.getOptions();
					for (WebElement days : dayPresent) {
						dayOption.add(days.getText());
					}
					System.out.println(dayOption);
				} else if (dropDown.getAttribute("id").equals("month")) {
					Select monthDropDown = new Select(dropDown);
					List<WebElement> monthPresent = monthDropDown.getOptions();
					for (WebElement months : monthPresent) {
						monthOption.add(months.getText());
					}
					System.out.println(monthOption);

				} else if (dropDown.getAttribute("id").equals("year")) {
					Select yearDropDown = new Select(dropDown);
					List<WebElement> yearPresent = yearDropDown.getOptions();
					for (WebElement year : yearPresent) {
						yearOption.add(year.getText());
					}
					System.out.println(yearOption);

				}
			} else {
				System.out.println("The element is not visible");
			}

		}
		driver.manage().window().minimize();
		driver.quit();
	}
}
