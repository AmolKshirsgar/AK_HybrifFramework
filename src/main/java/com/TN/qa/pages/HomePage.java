package com.TN.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//this is Home Page

public class HomePage 
{
	public WebDriver driver;
	
	public HomePage(WebDriver driver)      						//create constructor
	{	
		this.driver=driver;
		PageFactory.initElements(driver, this);					//here this specify parent class
// OR	PageFactory.initElements(driver, HomePage.class);  
		
	}
	
	
	//Objects	
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccDropMenu;
	
	@FindBy(linkText="Login")
	private WebElement loginOption;
	
	@FindBy(linkText="Register")
	private WebElement registerOption;

	//Actions
	public void clkMyAccDroMenu()
	{
		myAccDropMenu.click();
	}
	
	public LoginPage selectLoginOption()
	{
		loginOption.click();
		return new LoginPage(driver);
	}
	
	public RegisterPage selectRegOption()
	{
		registerOption.click();
		return new RegisterPage(driver);
	}
	
	@FindBy(name="search")
	private WebElement productName;
	
	@FindBy(xpath="(//button[@type='button'])[4]")
	private WebElement seachBtn;
	
	@FindBy(xpath="//p[contains(text(),'There is no product that matches the search criteria.')]")
	private WebElement productNameFailMsg;
	
	
	
	public void searchProductName(String product)
	{
		productName.sendKeys(product);
	}
	
	public SearchPage clickSearchBtn()
	{
		seachBtn.click();
		return new SearchPage(driver);
	}
	
	public boolean productNameFailMsgWarning()
	{
		boolean productNameFailMsgStatus=productNameFailMsg.isDisplayed();
		return productNameFailMsgStatus;
	}
	
	
	
}
