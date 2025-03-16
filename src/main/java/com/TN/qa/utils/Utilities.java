package com.TN.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities 
{
	public WebDriver driver;
	public static final int IMPLICIT_WAIT_TIME=10;
	public static final int PAGE_LOAD_TIME=5;
	
	
	public static String generateEmailWithTimeStamp()
	{
		Date date=new Date();
		String timeStamp= date.toString().replace(" ", "_").replace(":", "_");
		return "test"+timeStamp+"@gmail.com";
		
	}
	
	public static Object[][] getTestDataFromXL(String sheetName) 
	{
			File excelFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\TN\\qa\\testData\\TN_LoginData.xlsx");
			XSSFWorkbook wb=null;
			try {
				FileInputStream fis=new FileInputStream(excelFile);
				wb = new XSSFWorkbook(fis);
			}catch (Throwable e) {
				e.printStackTrace();
			}
			XSSFSheet sheet=wb.getSheet(sheetName);
		
		int totalRows=sheet.getLastRowNum();
		int totalCells=sheet.getRow(0).getLastCellNum();
		
		Object[][]data=new Object[totalRows][totalCells];
		
		for(int r=0;r<totalRows;r++)
		{
			XSSFRow row=sheet.getRow(r+1);
			
			for(int c=0;c<totalCells;c++)
			{
				XSSFCell cell=row.getCell(c);
				
				CellType cellType = cell.getCellType();
				
				switch(cellType)
				{
					case STRING:
						data[r][c]=cell.getStringCellValue();
						break;
					
					case NUMERIC:
						data[r][c]=Integer.toString((int)cell.getNumericCellValue());
						break;
					case BOOLEAN:
						data[r][c]=cell.getBooleanCellValue();
						break;
				}
			}
		  }
		  
		  return data;
			
				
		}

		
		public static String captureScreenshot(WebDriver driver, String testName)
		{
			File srcScreenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String destSSpath=System.getProperty("user.dir")+"\\screenshots\\"+testName+".png";
			try 
			{
				FileHandler.copy(srcScreenshot, new File (destSSpath));
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
			return destSSpath;
		}


}
