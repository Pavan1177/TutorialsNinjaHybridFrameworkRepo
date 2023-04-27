package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utilities {
	
	public static final int IMPLICIT_WAIT_TIME = 20;
	public static final int PAGE_LOAD_TIME = 20;

	public static String generateEmailWithTimeStamp() {
		Date date = new Date();
		String timeStamp = date.toString().replace(" ", "_").replace(":", "_");
		return "pavamkumar167"+timeStamp+"@gmail.com";
	}
	public static Object[][] getTestData(String sheetName) {
		
		File excelFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\dataconfig\\TutorialsNinjaTestData1.xlsx");
		XSSFWorkbook  workBook = null;
		try {
		FileInputStream fisEcxel = new FileInputStream(excelFile);
		workBook = new XSSFWorkbook(fisEcxel);
		}catch(Throwable e) {
			e.printStackTrace();
		}
	    XSSFSheet sheet = workBook.getSheet(sheetName);
	    int rows = sheet.getLastRowNum();
	    int cols = sheet.getRow(0).getLastCellNum();
	    
	    Object[][] data = new Object[rows][cols];
	    
	    for(int i=0; i<rows; i++) {
	    	XSSFRow row = sheet.getRow(i+1);
	    	
	    	for(int j=0; j<cols; j++) {
	    		XSSFCell cell = row.getCell(j);
	    		CellType cellType = cell.getCellType();
	    		
	    		switch(cellType) {
	    		case STRING:
	    			data[i][j] = cell.getStringCellValue();
	    			break;
	    		case NUMERIC:
	    			data[i][j] = Integer.toString((int)cell.getNumericCellValue());
	    			break;
	    		case BOOLEAN:
	    			data[i][j] = cell.getBooleanCellValue();
	    			break;
	    		}
	    	}
	    }
	    return data;
	}
}
