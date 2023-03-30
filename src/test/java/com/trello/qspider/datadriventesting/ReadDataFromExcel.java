package com.trello.qspider.datadriventesting;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataFromExcel {
public static void main(String[] args) throws EncryptedDocumentException, IOException {
	//create object file inputstream
	FileInputStream fis = new FileInputStream("./src/test/resource/facebookdropdown.xlsx");
	//create workbookfactory using poi and call create() on same class
	  Workbook workbook = WorkbookFactory.create(fis);
	  //call the excelsheet using getsheetmethod
	  Sheet sheet = workbook.getSheet("dropdownoption");
	  //get the value from excel
	  Row row = sheet.getRow(0);
	  Cell cell = row.getCell(5);
	  double value = cell.getNumericCellValue();
	  System.out.println("value in row=0,cell=0:"+value);
	  //typecast to get value
	  long typecastvalue = (long)value;
	  System.out.println("after typecast: "+typecastvalue);
	  //precondition to close
	  workbook.close();
	  
}         
}
