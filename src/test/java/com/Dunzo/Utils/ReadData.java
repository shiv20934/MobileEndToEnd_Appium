package com.Dunzo.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.microsoft.schemas.office.visio.x2012.main.CellType;

public class ReadData {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		readFromExcel_ApachePOI("Login");
		writeToExcel_ApachePOI();
		
	}
	
	/*To read XLS files, an HSSF implementation is provided by POI library.

To read XLSX, XSSF implementation of POI library will be the choice. Let's study these implementations in detail.
*/
	
	/*Workbook: XSSFWorkbook and HSSFWorkbook classes implement this interface.
	XSSFWorkbook: Is a class representation of XLSX file.
	HSSFWorkbook: Is a class representation of XLS file.
	Sheet: XSSFSheet and HSSFSheet classes implement this interface.
	XSSFSheet: Is a class representing a sheet in an XLSX file.
	HSSFSheet: Is a class representing a sheet in an XLS file.
	Row: XSSFRow and HSSFRow classes implement this interface.
	XSSFRow: Is a class representing a row in the sheet of XLSX file.
	HSSFRow: Is a class representing a row in the sheet of XLS file.
	Cell: XSSFCell and HSSFCell classes implement this interface.
	XSSFCell: Is a class representing a cell in a row of XLSX file.
	HSSFCell: Is a class representing a cell in a row of XLS file.
	*/
	
	public static void readFromExcel_ApachePOI(String testCase) throws Exception{
		String fileName = "TestData.xlsx";
		String SAMPLE_XLSX_FILE_PATH = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TestData.xlsx";
		File file = new File(SAMPLE_XLSX_FILE_PATH);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook workbook = null;
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		
	    if(fileExtensionName.equals(".xlsx")){


	    	workbook = new XSSFWorkbook(inputStream);

	    }

	    //Check condition if the file is xls file

	    else if(fileExtensionName.equals(".xls")){

	        //If it is xls file then create object of HSSFWorkbook class

	    	workbook = new HSSFWorkbook(inputStream);

	    }
		//System.out.println("Reading from right sheet");     
		Sheet sheet = workbook.getSheet("TestSheet");
		for(int i = 0;i<sheet.getLastRowNum();i++) {
			if(testCase.equalsIgnoreCase(sheet.getRow(i).getCell(0).getStringCellValue())) {
				Row row = sheet.getRow(i);
				String userID = row.getCell(1).getStringCellValue();
				int pwd = (int) row.getCell(2).getNumericCellValue();
			}
		}
		Row row = sheet.getRow(1);
		Cell cell = row.getCell(0);
		String testCaseName = cell.getStringCellValue();
		String userID = row.getCell(1).getStringCellValue();
		int pwd = (int) row.getCell(2).getNumericCellValue();
		System.out.println(testCaseName);
		System.out.println(userID);
		System.out.println(pwd);
			
		workbook.close();
	}

	public static void writeToExcel_ApachePOI() throws Exception{

		//==========================
		//System.out.println("In writeToExcel_ApachePOI");
		String fileName = "TestData.xlsx";
		String fileExtensionName = fileName.substring(fileName.indexOf("."));

		String SAMPLE_XLSX_FILE_PATH = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TestData.xlsx";
		FileInputStream fis = new FileInputStream(new File(SAMPLE_XLSX_FILE_PATH)); 
		Workbook workbook = null;

		//Get workbook for XLS file.
		if(fileExtensionName.equals(".xlsx")){


	    	workbook = new XSSFWorkbook(fis);

	    }

	    //Check condition if the file is xls file

	    else if(fileExtensionName.equals(".xls")){

	        //If it is xls file then create object of HSSFWorkbook class

	    	workbook = new HSSFWorkbook(fis);

	    }	 
		Sheet sheet = workbook.getSheet("TestSheet");    
		Row row = sheet.getRow(0);
		int lastCellNum = row.getLastCellNum();
		String statusToUpdate = "Pass";
		int cellToUpdate = 0;
		for(int i = 0;i<lastCellNum;i++) {
			if(row.getCell(i).getStringCellValue().equalsIgnoreCase("Test Status")) {
				cellToUpdate = i;
			}
		}
		sheet.getRow(1).createCell(cellToUpdate);
		sheet.getRow(1).getCell(cellToUpdate).setCellValue(statusToUpdate);
		FileOutputStream fileOut = new FileOutputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TestData.xlsx");
		workbook.write(fileOut);
		fileOut.close();
		workbook.close();
	}

}
