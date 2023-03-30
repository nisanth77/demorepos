package com.trello.qspiders.learngroupexecution;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenTestingByClass {
	@Test(dataProvider = "iAmDataProvider")
	public void supplyDataToMe(String src, String dest, String noOfTickets, String phno) {
		System.out.println("  From==>" + src + "  To==> " + dest + "  noOfTickets==> " + noOfTickets + "  phoneNo==> " + phno);
	}

	@DataProvider
	public  Object iAmDataProvider() {
		Object[][] obj = new Object[4][4];

		obj[0][0] = "Mysore";
		obj[0][1] = "Bangalore";
		obj[0][2] = "4";
		obj[0][3] = "123456789";

		obj[1][0] = "Huballi";
		obj[1][1] = "Bangalore";
		obj[1][2] = "24";
		obj[1][3] = "098765432";

		obj[2][0] = "Belgavi";
		obj[2][1] = "Bangalore";
		obj[2][2] = "3";
		obj[2][3] = "96379123456";

		obj[3][0] = "Shimoga";
		obj[3][1] = "Bangalore";
		obj[3][2] = "4";
		obj[3][3] = "789963852741";

		return obj;
	}
}

