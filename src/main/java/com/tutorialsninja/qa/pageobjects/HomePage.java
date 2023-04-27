package com.tutorialsninja.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	@FindBy(xpath = "//span[text()='My Account']")
    private WebElement myAccountDropDown;
	
	@FindBy(linkText="Login")
	private WebElement loginOption;
	
	@FindBy(linkText="Register")
	private WebElement registerOption;
	
	@FindBy(xpath = "//input[@name='search']")
	private WebElement searchValidProduct;
	
	@FindBy(xpath = "//i[@class='fa fa-search']")
	private WebElement clickOnSearchButton;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	public void clickOnMyAccountOption() {
		myAccountDropDown.click();
	}
	public LoginPage clcikOnLoinOption() {
		loginOption.click();
		return new LoginPage(driver);
	}
	public RegisterPage clickOnRegisterOption() {
		registerOption.click();
		return new RegisterPage(driver);
	}
public SearchPage clickOnSearchButton() {
	clickOnSearchButton.click();
	return new SearchPage(driver);
}
	public SearchPage searchForProduct(String product) {
		searchValidProduct.sendKeys(product);
		clickOnSearchButton.click();
		return new SearchPage(driver);
	}
	
}
