package com.tutorialsninja.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	WebDriver driver;
	
	
	
	
	@FindBy(linkText = "HP LP3065")
	private WebElement validProductDisplyed;
	
	@FindBy(xpath = "//p[contains(text(),'There is no product that matches the search criteria.')]")
	private WebElement productNotMatchMessage;
	
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public boolean verifyProductIsDisplayed() {
		boolean validProduct = validProductDisplyed.isDisplayed();
		return validProduct;
	}
	public void clickOnSearchButton() {
		
	}
	public String verifyProductNotMatchMessage() {
		String invalidProductMessage = productNotMatchMessage.getText();
		return invalidProductMessage;
	}
}
