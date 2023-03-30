package com.trello.qspider.datadriventesting;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class TurnOnPoi {
public static void main(String[] args) throws Throwable {
	FileInputStream fis = new FileInputStream("./src/test/resource/facebookdropdown.xlsx");
	Workbook workbook = WorkbookFactory.create(fis);
	Sheet sheet = workbook.getSheet("dropdownoption");
	//get total number of roows
	  int rowPresentInSheet = sheet.getPhysicalNumberOfRows();
	  System.out.println("rowPresentInSheet = "+rowPresentInSheet);
	  //index of row
	   int firstRowIndex = sheet.getFirstRowNum();
	   System.out.println("firstRowIndex = "+firstRowIndex);
	   int lastRowIndex = sheet.getLastRowNum();
	   System.out.println("lastRowIndex ="+lastRowIndex);
	   
}
}
