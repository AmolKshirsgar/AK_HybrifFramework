package com.TN.qa.listners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.TN.qa.utils.ExtentReporter;
import com.TN.qa.utils.Utilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.ReportStats;

public class MyListners implements ITestListener  //if import not coming change test to compile from  dependency <scope>compile</scope> 
{
	ExtentReports reports;
	ExtentTest extentTest;
	String testName;
	
	@Override
	public void onStart(ITestContext context) 
	{
//		System.out.println("Execution of project started..!!!");    //syso will print on console
		reports=ExtentReporter.generateExtentReports();
	}
	
	@Override
	public void onTestStart(ITestResult result) 
	{
		testName=result.getName();
		extentTest=reports.createTest(testName);
		extentTest.log(Status.INFO, testName+" started execution..!!");
		
//		System.out.println(testName+" started execution..!!");
	}

	@Override
	public void onTestSuccess(ITestResult result) 
	{
		extentTest.log(Status.PASS, testName+" got successfully executed..!!");
//		System.out.println(testName+" got successfully executed..!!");
	}

	@Override
	public void onTestFailure(ITestResult result) 
	{
		
		WebDriver driver = null;
		try 
		{
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} 
		catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) 
		{
			e.printStackTrace();
		}
		
		String destSSpath=Utilities.captureScreenshot(driver, testName);
	
/*     just fyi below, code Added in seperate method
 
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
*/	
		
		extentTest.addScreenCaptureFromPath(destSSpath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, testName+" got failed...");
		
		
		
		
//		System.out.println(testName+" got failed...");
//		System.out.println(result.getThrowable());		//will show reason of failure

	}

	@Override
	public void onTestSkipped(ITestResult result) 
	{
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName+" got skipped...");
		
//		System.out.println(testName+" got skipped...");
//		System.out.println(result.getThrowable());	
	}

	
	@Override
	public void onFinish(ITestContext context) 
	{
	//	System.out.println("Execution of project finished..!!!");	
		reports.flush();				//mandatory to print extentreport info
		
		String pathOfExtentReport=System.getProperty("user.dir")+"\\test-output\\TNextentReports\\extentReport.html";
		File extentReportFile=new File(pathOfExtentReport);
	//OR 	File extentReportFile=new File(System.getProperty("user.dir")+"\\test-output\\TNextentReports\\extentReport.html");
		try 
		{
			Desktop.getDesktop().browse(extentReportFile.toURI());
		} 
		catch (Throwable e) 
		{
			e.printStackTrace();
		}
	}
	
	

}
