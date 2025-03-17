package com.TN.qa.testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.TN.qa.base.BaseClass;
import com.TN.qa.pages.AccountPage;
import com.TN.qa.pages.HomePage;
import com.TN.qa.pages.LoginPage;
import com.TN.qa.utils.Utilities;

//this is third commit

public class LoginTest extends BaseClass
{
	
	public WebDriver driver;
	public LoginPage loginPage;
	public HomePage homePage;
	public AccountPage accountPage;
	
	public LoginTest()    //create constructor of Login with super to call prop file from Base constructor (parent)
	{
		super();
	}
		
	@BeforeMethod
	public void setUp()
	{
		driver=initilizeBrowserWithLoginURL(prop.getProperty("browserName"));
		
		homePage=new HomePage(driver);
		homePage.clkMyAccDroMenu();
		loginPage=homePage.selectLoginOption();   //after clk on login it will navigate to Login Page so return type Loginpage
	
	}
	
	
//	@Test(priority=1, dataProvider="dp")
	@Test(priority=1)
//	public void loginWithValidCred(String email,String pwd)
	public void loginWithValidCred()
	{			
//		loginPage.enterEmail(email);
//		loginPage.enterPassword(pwd);
//		loginPage=new LoginPage(driver);
		loginPage.enterEmail(prop.getProperty("validEmail"));
		loginPage.enterPassword(prop.getProperty("validPassword"));
		accountPage=loginPage.clickLoginBtn();
			
		accountPage=new AccountPage(driver);
		Assert.assertTrue(accountPage.myACcountTextOnRegPage(),"My Account not displayed");
	}

	
	
//	@DataProvider(name="dp")
//	public Object[][] supplyTestData()	
	{
		
	/* with DP hardcode
		Object[][]data= {
							{"amotooricap3@gmail.com","12345"},
							{"akk@test.com","ak123"},
							{"laksh@yahoo.com","Lakshmi"}
				
						};
		
		return data;
	*/	
		//with DP xl DD 
//		Object[][]data= Utilities.getTestDataFromXL("Login");
//		return data;
		
		
	}
	
	@Test(priority=2)
	public void loginWithInvalidCred()
	{	
		loginPage.enterEmail(Utilities.generateEmailWithTimeStamp());
		loginPage.enterPassword(dataProp.getProperty("invalidPassword1"));
		loginPage.clickLoginBtn();
		
		Assert.assertTrue(loginPage.emailPwdUnmatchWarning(),"Failed message not displayed");
		
//		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());  //without extend here Utilities class is static
//		driver.findElement(By.id("input-password")).sendKeys("12345");
//		driver.findElement(By.xpath("//input[@type='submit']")).click();
//		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Warning: No match for E-Mail Address and/or Password.')]")).isDisplayed(),"Failed message not displayed");
	}	
	
	@Test(priority=3)
	public void loginWithInvalidEmailValidPwd()
	{	
		loginPage.enterEmail(Utilities.generateEmailWithTimeStamp());
		loginPage.enterPassword(prop.getProperty("validPassword"));
		loginPage.clickLoginBtn();
		
		Assert.assertTrue(loginPage.emailPwdUnmatchWarning(),"Failed message not displayed");
	}
	
	@Test(priority=4)
	public void loginWithValidEmailInvalidPwd()
	{	
		loginPage.enterEmail(prop.getProperty("validEmail"));
		loginPage.enterPassword(dataProp.getProperty("invalidPassword1"));
		loginPage.clickLoginBtn();
		
		Assert.assertTrue(loginPage.emailPwdUnmatchWarning(),"Failed message not displayed");
	}
	
	@Test(priority=5)
	public void loginWithoutCred()
	{	
		loginPage.clickLoginBtn();
		
		Assert.assertTrue(loginPage.emailPwdUnmatchWarning(),"Failed message not displayed");
	}
	

	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	


}
