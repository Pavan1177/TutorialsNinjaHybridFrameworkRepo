package com.tutorialsninja.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;
	@FindBy(id = "input-firstname")
	private WebElement firstName;
	@FindBy(id="input-lastname")
	private WebElement lastName;
	@FindBy(id="input-email")
	private WebElement emailFeild;
	@FindBy(id="input-telephone")
	private WebElement telephoneFeild;
	@FindBy(id="input-password")
	private WebElement passwordFeild;
	@FindBy(id="input-confirm")
	private WebElement confirmPassword;
	@FindBy(name="agree")
	private WebElement privacyPolicy;
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueButton;
	@FindBy(xpath = "//input[@name='newsletter'][@value='1']")
	private WebElement newsLetter;
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement emailWarning;
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement privacyPolicyWarning;
	@FindBy(xpath="//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarning;
	@FindBy(xpath="//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarning;
	@FindBy(xpath = "//input[@id='input-email']/following-sibling::div")
	private WebElement emailWarningMessage;
	@FindBy(xpath = "//input[@id='input-telephone']/following-sibling::div")
	private WebElement telePhoneWarning;
	@FindBy(xpath = "//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarning;
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
public void clickOnContinue() {
	continueButton.click();
}
	
	public AccountSuccessPage registerAccount(String firstNameText,String lastNameText,String emailText,String telephoneText,String passwordText,String confirmPasswordText) {
		firstName.sendKeys(firstNameText);
		lastName.sendKeys(lastNameText);
		emailFeild.sendKeys(emailText);
		telephoneFeild.sendKeys(telephoneText);
		passwordFeild.sendKeys(passwordText);
		confirmPassword.sendKeys(confirmPasswordText);
		newsLetter.click();
		privacyPolicy.click();
		continueButton.click();
		return new AccountSuccessPage(driver);
		
	}
	public AccountSuccessPage registerAccountWithAllFeilds(String firstNameText,String lastNameText,String emailText,String telephoneText,String passwordText,String confirmPasswordText) {
		firstName.sendKeys(firstNameText);
		lastName.sendKeys(lastNameText);
		emailFeild.sendKeys(emailText);
		telephoneFeild.sendKeys(telephoneText);
		passwordFeild.sendKeys(passwordText);
		confirmPassword.sendKeys(confirmPasswordText);
		privacyPolicy.click();
		continueButton.click();
		return new AccountSuccessPage(driver);
		
	}
	public String retriveEmailWarning() {
		String emailWarningMessage = emailWarning.getText();
		return emailWarningMessage;
	}
	public String retrivePrivacyPolicyWarning() {
		String privacyWarningMessage = privacyPolicyWarning.getText();
		return privacyWarningMessage;
	}
	public String retriveFirstNameWarning() {
		String firstNameWarningMessage = firstNameWarning.getText();
		return firstNameWarningMessage;
	}
	public String retriveLastNameWarning() {
		String lastNameWarningMessage = lastNameWarning.getText();
		return lastNameWarningMessage;
	}
	public String retriveEmailWarningMessage() {
		String lastNameWarningMessage = emailWarningMessage.getText();
		return lastNameWarningMessage;
	}
	public String retriveTelephoneWarningMessage() {
		String telephoneWarning = telePhoneWarning.getText();
		return telephoneWarning;
	}
	public String retrivePasswordWarningMessage() {
		String passwordWarningMessage = passwordWarning.getText();
		return passwordWarningMessage;
	}
}
