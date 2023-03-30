package com.trello.qspider.datadriventesting;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckForOrderOfDropDownAndFunctionalityTesting {
	public static void main(String[] args) throws Throwable {
		List<String> actualDayDropDown = new ArrayList<String>();
		List<String> actualMonthDropDown = new ArrayList<String>();
		List<String> actualYearDropDown = new ArrayList<String>();
		List<String> exceptedDayDropDown = new ArrayList<String>();
		List<String> exceptedMonthDropDown = new ArrayList<String>();
		List<String> exceptedYearDropDown = new ArrayList<String>();
		FileInputStream fis = new FileInputStream("./src/test/resource/facebookdropdown.xlsx");
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheet("dropdownoption");
		int firstRowIndex = sheet.getFirstRowNum();
		int lastRowIndex = sheet.getLastRowNum();
		for (int i = firstRowIndex; i <= lastRowIndex; i++) {
			short lastCellIndex = sheet.getRow(i).getLastCellNum();
			short firstCellIndex = sheet.getRow(i).getFirstCellNum();
			for (int j = firstCellIndex; j < lastCellIndex; j++) {
				String cellType = sheet.getRow(i).getCell(j).getCellType().toString();
				if (cellType.equals("NUMERIC")) {

					if (lastCellIndex == 31) {
						int value = (int) sheet.getRow(i).getCell(j).getNumericCellValue();

						exceptedDayDropDown.add(String.valueOf(value));

					} else if (lastCellIndex == 119) {
						int value = (int) sheet.getRow(i).getCell(j).getNumericCellValue();

						exceptedYearDropDown.add(String.valueOf(value));
					}

				} else if (cellType.equals("STRING")) {
					exceptedMonthDropDown.add(sheet.getRow(i).getCell(j).toString());
				}

			}
		}
		workbook.close();
		System.out.println("Excel sheet data is taken");
		System.out.println(
				"===============================================================================================");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.get("https://www.facebook.com/signup");
		List<WebElement> allDropDowns = driver.findElements(By.tagName("select"));
		for (WebElement dropDown : allDropDowns) {
			if (dropDown.isDisplayed()) {
				Select option = new Select(dropDown);
				if (dropDown.getAttribute("title").equals("Day")) {
					List<WebElement> daysPresent = option.getOptions();
					for (WebElement days : daysPresent) {
						actualDayDropDown.add(days.getText());
					}
				} else if (dropDown.getAttribute("title").equals("Month")) {
					List<WebElement> monthsPresent = option.getOptions();
					for (WebElement month : monthsPresent) {
						actualMonthDropDown.add(month.getText());
					}

				} else if (dropDown.getAttribute("title").equals("Year")) {
					List<WebElement> yearPresent = option.getOptions();
					for (WebElement year : yearPresent) {
						actualYearDropDown.add(year.getText());
					}
				}

			}
		}
		System.out.println("Fuctionality testing for spelling ");
		System.out.println("==============================================================================");
		System.out.println("actualDayDropDown:   " + actualDayDropDown);
		System.out.println("exceptedDayDropDown: " + exceptedDayDropDown);
		if (actualDayDropDown.equals(exceptedDayDropDown)) {
			System.out.println("pass:Day drop down is verified and order of days are checked and is correct");
		} else {
			System.out.println("Fail:Day drop down is verified and order of days are checked and is incorrect");
		}
		System.out.println("actualMonthDropDown:  " + actualMonthDropDown);
		System.out.println("exceptedMonthDropDown:" + exceptedMonthDropDown);
		if (actualMonthDropDown.equals(exceptedMonthDropDown)) {
			System.out.println("pass:Month drop down is verified and order of days are checked and is correct");
		} else {
			System.out.println("Fail:month drop down is verified and order of days are checked and is incorrect");
		}

		System.out.println("actualYearDropDown:  " + actualYearDropDown);
		System.out.println("exceptedYearDropDown:" + exceptedYearDropDown);
		if (actualYearDropDown.equals(exceptedYearDropDown)) {
			System.out.println("pass:year drop down is verified and order of days are checked and is correct");

		} else {
			System.out.println("Fail:month drop down is verified and order of days are checked and is incorrect");
		}
		System.out.println(
				"==============================================================================================");
		System.out.println("Functionality for selection of dropdown");
		for (WebElement dropdown : allDropDowns) {
			if (dropdown.isDisplayed()) {
				Select option = new Select(dropdown);
				if (dropdown.getAttribute("id").equals("day")) {
					List<WebElement> dayPresent = option.getOptions();
					for (WebElement day : dayPresent) {
						option.selectByVisibleText(day.getText());
						if (day.isSelected()) {
							System.out.println("pass: " + day.getText() + " is selected");
						} else {
							System.out.println("fail: " + day.getText() + " is not selected");
						}

					}
				} else if (dropdown.getAttribute("id").equals("month")) {
					List<WebElement> monthPresent = option.getOptions();
					for (WebElement months : monthPresent) {
						option.selectByVisibleText(months.getText());
						if (months.isSelected()) {
							System.out.println("pass: " + months.getText() + " is selected");
						} else {
							System.out.println("fail: " + months.getText() + " is not selected");
						}
					}

				} else if (dropdown.getAttribute("id").equals("year")) {
					List<WebElement> yearPresent = option.getOptions();
					for (WebElement year : yearPresent) {
						option.selectByVisibleText(year.getText());
						if (year.isSelected()) {
							System.out.println("pass: " + year.getText() + " is selected");
						} else {
							System.out.println("fail: " + year.getText() + " is not selected");
						}
					}
				}
			}
			System.out.println("====================================================================================");
		}

		driver.manage().window().minimize();
		driver.quit();
	}
}
