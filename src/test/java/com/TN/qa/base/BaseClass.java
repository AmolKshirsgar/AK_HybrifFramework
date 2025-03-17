package com.TN.qa.base;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.TN.qa.utils.Utilities;

public class BaseClass 
{
	public WebDriver driver;
	public Properties prop;
	public Properties dataProp;
	
	
	public BaseClass()
	{
		prop=new Properties();
		try 
		{
			FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\TN\\qa\\config\\config.properties");
			prop.load(fis);
		} 
		catch (Throwable e) 
		{
			e.printStackTrace();
		}
		
		
		dataProp=new Properties();
		try {
			FileInputStream fis1=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\TN\\qa\\testData\\testData.properties");
			dataProp.load(fis1);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
	}
	
	public WebDriver initilizeBrowserWithLoginURL(String browserName)
	{
		if(browserName.equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			driver=new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		
		driver.get(prop.getProperty("url"));
		
		return driver;
	}
	
	
	
	

}
