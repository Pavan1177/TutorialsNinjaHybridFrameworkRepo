package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pageobjects.AccountPage;
import com.tutorialsninja.qa.pageobjects.HomePage;
import com.tutorialsninja.qa.pageobjects.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

public class LoginTest extends Base{
	
	public LoginTest() {
		super();
	}
	WebDriver driver;
	LoginPage loginPage;
	
	
	@BeforeMethod
	public void setUp() {
		driver = initializeBrowser(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccountOption();
		loginPage = homePage.clcikOnLoinOption();
		
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	@Test(priority=1,dataProvider="validCredentialsSupplier")
	public void verifyLoginWithValidCredentials(String email, String password) {
		AccountPage accountPage = loginPage.login(email, password);
		Assert.assertTrue(accountPage.verifyAccountOptionisDisplyed(),"Not displyed");
		
	}
	@DataProvider(name="validCredentialsSupplier")
	public Object[][] supplyData() {
		Object[][] data = Utilities.getTestData("Login");
		return data;
	}
	@Test(priority=2)
	public void verifyLoginWithInValidCredentials() {
		loginPage.login(Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("invalidPassword"));
	
		String actualWarningMessage = loginPage.verifyEmailPasswordNotMatchingMessage();
		String expectedWarningMesaage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMesaage),"Expected warning message is not displyed");
	
	}
	@Test(priority=3)
	public void verifyinvalidEmailandValidPassword() {
		loginPage.login(Utilities.generateEmailWithTimeStamp(), prop.getProperty("validPassword"));
		
		
		String actualWarningMessage = loginPage.verifyEmailPasswordNotMatchingMessage();
		String expectedWarningMesaage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMesaage),"Expected warning message is not displyed");
	
		
	}
	@Test(priority=4)
	public void verifyValidEmailandInavalidPassword() {
		 loginPage.login(prop.getProperty("validEmail"), dataProp.getProperty("invalidPassword"));
		
		
		String actualWarningMessage = loginPage.verifyEmailPasswordNotMatchingMessage();
		String expectedWarningMesaage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMesaage),"Expected warning message is not displyed");
	
	}
	@Test(priority=5)
	public void verifyLoginWithoutCredentials() {
		
		loginPage.clickOnLogin();
		String actualWarningMessage = loginPage.verifyEmailPasswordNotMatchingMessage();
		String expectedWarningMesaage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMesaage),"Expected warning message is not displyed");
		
	}
	
}
