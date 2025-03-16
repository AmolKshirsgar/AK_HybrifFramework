package com.TN.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter 
{
	public static ExtentReports generateExtentReports() 
	{
		File extentFile=new File(System.getProperty("user.dir")+"\\test-output\\TNextentReports\\extentReport.html");
		ExtentSparkReporter reporter=new ExtentSparkReporter(extentFile);
		reporter.config().setTheme(Theme.DARK);							  //will set dark them
		reporter.config().setReportName("TN Hybrid Framework Result");    //will print name of report next to date at right corner
		reporter.config().setDocumentTitle("Hybrid Framewok Automation"); //will print title instaed of url
		reporter.config().setTimeStampFormat("dd/MM/yyyy HH:mm:ss");	  ////will print manual set time instaed default
		
		ExtentReports reports=new ExtentReports();
		reports.attachReporter(reporter);
		
		Properties extentProp=new Properties();
		try {
			FileInputStream fis2=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\TN\\qa\\config\\config.properties");
			extentProp.load(fis2);
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		reports.setSystemInfo("Application URL", extentProp.getProperty("url"));
		reports.setSystemInfo("Browser Name", extentProp.getProperty("browserName"));
		reports.setSystemInfo("Email", extentProp.getProperty("validEmail"));
		reports.setSystemInfo("Password", extentProp.getProperty("validPassword"));
		reports.setSystemInfo("Operating System", System.getProperty("os.name"));
		reports.setSystemInfo("User Name", System.getProperty("user.name"));
		reports.setSystemInfo("Java Version", System.getProperty("java.version"));
		
		return reports;
	}

}
