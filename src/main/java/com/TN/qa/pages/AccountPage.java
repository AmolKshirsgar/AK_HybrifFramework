package com.TN.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//this is account page

public class AccountPage 
{
	WebDriver driver;
	
	public AccountPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h2[text()='My Account']")
	WebElement myAccountText;
	
	public boolean myACcountTextOnRegPage()
	{
		boolean textDisplatStatus=myAccountText.isDisplayed();
		return textDisplatStatus;
	}

}
