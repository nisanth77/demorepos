package com.trello.qspider.datadriventesting;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Praticedata {
	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("./src/test/resource/facebookdropdown.xlsx");
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheet("dropdownoption");
		Row row = sheet.getRow(1);
		// System.out.println(row);
		Cell cell = row.getCell(2);
		System.out.println(cell);
		String month = cell.getStringCellValue();
		System.out.println(month);
		workbook.close();
	}
}
