package com.TN.qa.testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.TN.qa.base.BaseClass;
import com.TN.qa.pages.HomePage;
import com.TN.qa.pages.SearchPage;

public class SearchProductTest extends BaseClass
{
	public WebDriver driver;
	public HomePage homePage;
	public SearchPage searchPage;
	
	public SearchProductTest()
	{
		super();
	}
	
	
	@BeforeMethod
	public void setUp()
	{
		driver=initilizeBrowserWithLoginURL(prop.getProperty("browserName"));
		homePage=new HomePage(driver);
	}
	
	@Test(priority=1)
	public void searchValidProduct()
	{
//		homePage=new HomePage(driver);
		homePage.searchProductName(dataProp.getProperty("productName1"));
		searchPage=homePage.clickSearchBtn();
			
		Assert.assertTrue(searchPage.productDispalyMsg());
	}
	
	@Test(priority=2)
	public void searchInvalidProduct()
	{
//		homePage=new HomePage(driver);
		homePage.searchProductName(dataProp.getProperty("productName22"));
		homePage.clickSearchBtn();
		
		Assert.assertTrue(homePage.productNameFailMsgWarning());
	}
	
	@Test(priority=3,dependsOnMethods= {"searchInvalidProduct"})
	public void searchBlankProduct()
	{
//		homePage=new HomePage(driver);
		homePage.clickSearchBtn();
		
		Assert.assertTrue(homePage.productNameFailMsgWarning());
	}
	
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

}
