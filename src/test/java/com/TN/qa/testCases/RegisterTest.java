package com.TN.qa.testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.TN.qa.base.BaseClass;
import com.TN.qa.pages.AccountSuccessPage;
import com.TN.qa.pages.HomePage;
import com.TN.qa.pages.RegisterPage;
import com.TN.qa.utils.Utilities;

//this is to check pull

public class RegisterTest extends BaseClass
{
	public WebDriver driver;
	public HomePage homePage;
	public RegisterPage registerPage;
	public AccountSuccessPage accountSuccessPage;
	
	public RegisterTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setUp()
	{
		driver=initilizeBrowserWithLoginURL(prop.getProperty("browserName"));
		
		homePage=new HomePage(driver);
		homePage.clkMyAccDroMenu();
		registerPage=homePage.selectRegOption();
	}
	
	@Test(priority=1)
	public void registerWithMandatoryFields()
	{
		registerPage.enterFirstName(dataProp.getProperty("firstName1"));
		registerPage.enterLastName(dataProp.getProperty("lastName1"));
		registerPage.enterEmail(Utilities.generateEmailWithTimeStamp());
		registerPage.enterEtelephone(dataProp.getProperty("telephone1"));
		registerPage.enterPwd(dataProp.getProperty("validPassword1"));
		registerPage.confirmPwd(dataProp.getProperty("validPassword1"));
		registerPage.coclkOnAgree();
		accountSuccessPage=registerPage.coclkOnContBtn();
		
		Assert.assertTrue(accountSuccessPage.AccCreatedSuccessMsg());
	}	
	
	
	@Test(priority=2)
	public void registerWithExistingUser()
	{	
		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.enterEmail(prop.getProperty("validEmail"));
		registerPage.enterEtelephone(prop.getProperty("telephone"));
		registerPage.enterPwd(prop.getProperty("validPassword"));
		registerPage.confirmPwd(prop.getProperty("validPassword"));
		registerPage.coclkOnAgree();
		registerPage.coclkOnContBtn();
		
		Assert.assertTrue(registerPage.existingUserWarningMsg());
	}
	
	@Test(priority=3)
	public void registerWithoutDetails()
	{	
		registerPage.coclkOnContBtn();
		
		Assert.assertTrue(registerPage.clkPrivacyWarningMsg(),"No Warning msg");
		Assert.assertTrue(registerPage.enterFirstNameWarningMsg(),"No FirstName Warning msg");
		Assert.assertTrue(registerPage.enterLastNameWarningMsg(),"No LastName Warning msg");
		Assert.assertTrue(registerPage.enterEmailWarningMsg(),"No Eamil Warning msg");
		Assert.assertTrue(registerPage.enterEmailWarningMsg(),"No Telephone Warning msg");
		Assert.assertTrue(registerPage.enterPwdWarningMsg(),"No Pwd Warning msg");
	}

	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}
