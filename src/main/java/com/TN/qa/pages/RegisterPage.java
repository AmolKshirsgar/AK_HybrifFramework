package com.TN.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage 
{
	public WebDriver driver;
	
	public RegisterPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(id="input-firstname")
	private WebElement firstNameField;
	
	@FindBy(id="input-lastname")
	private WebElement lastNameField;
	
	@FindBy(id="input-email")
	private WebElement emailField;
	
	@FindBy(id="input-telephone")
	private WebElement telephoneField;
	
	@FindBy(id="input-password")
	private WebElement pwdField;
	
	@FindBy(name="confirm")
	private WebElement ConfirmpwdField;
	
	@FindBy(name="agree")
	private WebElement agreementChkBox;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueBtn;
	
	@FindBy(xpath="//div[contains(text(),'Warning: E-Mail Address is already registered!')]")
	private WebElement existingUserMsg;
	
	public void enterFirstName(String fName)
	{
		firstNameField.sendKeys(fName);
	}
	
	public void enterLastName(String lName)
	{
		lastNameField.sendKeys(lName);
	}
	
	public void enterEmail(String email)
	{
		emailField.sendKeys(email);
	}
	
	public void enterEtelephone(String telephone)
	{
		telephoneField.sendKeys(telephone);
	}
	
	public void enterPwd(String pwd)
	{
		pwdField.sendKeys(pwd);
	}
	
	public void confirmPwd(String pwd)
	{
		ConfirmpwdField.sendKeys(pwd);
	}
	
	public void coclkOnAgree()
	{
		agreementChkBox.click();
	}
	
	public AccountSuccessPage coclkOnContBtn()
	{
		continueBtn.click();
		return new AccountSuccessPage(driver);
	}
	
	public boolean existingUserWarningMsg()
	{
		boolean existingUserMsgStatus=existingUserMsg.isDisplayed();
		return existingUserMsgStatus;
	}
	
	
	@FindBy(xpath="//div[text()='Warning: You must agree to the Privacy Policy!']")
	private WebElement privacyWarningMsg;
	
	@FindBy(xpath="//div[text()='First Name must be between 1 and 32 characters!']")
	private WebElement firstNameWarningMsg;
	
	@FindBy(xpath="//div[text()='Last Name must be between 1 and 32 characters!']")
	private WebElement lastNameWarningMsg;
	
	@FindBy(xpath="//div[text()='E-Mail Address does not appear to be valid!']")
	private WebElement emailWarningMsg;
	
	@FindBy(xpath="//div[text()='Telephone must be between 3 and 32 characters!']")
	private WebElement telephoneWarningMsg;
	
	@FindBy(xpath="//div[text()='Password must be between 4 and 20 characters!']")
	private WebElement pwdWarningMsg;
	
	public boolean clkPrivacyWarningMsg()
	{
		boolean privacyWarningMsgStatus=privacyWarningMsg.isDisplayed();
		return privacyWarningMsgStatus;
	}
	
	public boolean enterFirstNameWarningMsg()
	{
		boolean enterfirstNameWarningMsgStatus=firstNameWarningMsg.isDisplayed();
		return enterfirstNameWarningMsgStatus;
	}
	
	public boolean enterLastNameWarningMsg()
	{
		boolean enterLastNameWarningMsgStatus=lastNameWarningMsg.isDisplayed();
		return enterLastNameWarningMsgStatus;
	}
	
	public boolean enterEmailWarningMsg()
	{
		boolean enterEmailWarningMsgStatus=emailWarningMsg.isDisplayed();
		return enterEmailWarningMsgStatus;
	}
	
	public boolean enterTelephoneWarningMsg()
	{
		boolean enterTelephoneWarningMsgStatus=telephoneWarningMsg.isDisplayed();
		return enterTelephoneWarningMsgStatus;
	}
	
	public boolean enterPwdWarningMsg()
	{
		boolean enterPwdWarningMsgStatus=pwdWarningMsg.isDisplayed();
		return enterPwdWarningMsgStatus;
	}
	
	
	
	
	
	
}
