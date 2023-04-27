package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pageobjects.HomePage;
import com.tutorialsninja.qa.pageobjects.SearchPage;

public class SearchTest extends Base {
	
	public SearchTest() {
		super();
	}
WebDriver driver;
SearchPage searchPage;
HomePage homePage;
@BeforeMethod
	public void setUp() {
		driver = initializeBrowser(prop.getProperty("browserName"));
		homePage = new HomePage(driver);
	}
@AfterMethod
	public void tearDown() {
		driver.quit();
	}
@Test(priority=1)
public void verifySearchWithValidProduct() {
	
	searchPage = homePage.searchForProduct(dataProp.getProperty("validProduct"));
	
    Assert.assertTrue(searchPage.verifyProductIsDisplayed(),"HP is not displayed");
	
}
@Test(priority=2)
public void verifySearchWithInvalidProduct() {
	
	searchPage = homePage.searchForProduct(dataProp.getProperty("invalidProduct"));
	
	String actualMessage = searchPage.verifyProductNotMatchMessage();
	Assert.assertEquals(actualMessage, dataProp.getProperty("noProductMatchWarning"),"No message displyed for invalid product");
}
@Test(priority=3,dependsOnMethods= {"verifySearchWithInvalidProduct"})
public void verifySearchWithoutAnyProduct() {
	
	
	searchPage = homePage.clickOnSearchButton();
	String actualMessage = searchPage.verifyProductNotMatchMessage();
	Assert.assertEquals(actualMessage, dataProp.getProperty("noProductMatchWarning"),"No message displyed for invalid product");
}
}
