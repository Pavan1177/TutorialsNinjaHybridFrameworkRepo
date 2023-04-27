package com.tutorialsninja.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
    @FindBy(id="input-email")
	private WebElement enterEmail;
    @FindBy(id="input-password")
    private WebElement enterPassword;
    @FindBy(xpath="//input[@type='submit']")
    private WebElement clickOnLoginOption;
    @FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
    WebElement emailPasswordErrorMessage;
    
    public LoginPage(WebDriver driver) {
    	this.driver = driver;
    	PageFactory.initElements(driver, this);
    }
    
    
    public void clickOnLogin() {
    	clickOnLoginOption.click();
    }
    public AccountPage login(String emailText,String passwordText ) {
    	enterEmail.sendKeys(emailText);
    	enterPassword.sendKeys(passwordText);
    	clickOnLoginOption.click();
    	return new AccountPage(driver);
    }
    public String verifyEmailPasswordNotMatchingMessage() {
    	String errorMessage = emailPasswordErrorMessage.getText();
    	return errorMessage;
    }
}
