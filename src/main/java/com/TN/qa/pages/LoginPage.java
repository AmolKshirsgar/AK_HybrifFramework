package com.TN.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage 
{
	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="input-email")
	WebElement emailField;
	
	@FindBy(id="input-password")
	WebElement pwdField;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement loginButton;
	
	@FindBy(xpath="//div[contains(text(),'Warning: No match for E-Mail Address and/or Password.')]")
	WebElement credWarning;
	
	
	public void enterEmail(String emailText)
	{
		emailField.sendKeys(emailText);
	}
	
	public void enterPassword(String pwdText)
	{
		pwdField.sendKeys(pwdText);
	}
	
	public AccountPage clickLoginBtn()
	{
		loginButton.click();
		return new AccountPage(driver);
	}
	
	public boolean emailPwdUnmatchWarning()
	{
		boolean credWarningStatus=credWarning.isDisplayed();
		return credWarningStatus;
	}
	
	
	

}
