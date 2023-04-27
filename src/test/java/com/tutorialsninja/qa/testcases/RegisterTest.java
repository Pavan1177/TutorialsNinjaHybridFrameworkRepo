package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pageobjects.AccountSuccessPage;
import com.tutorialsninja.qa.pageobjects.HomePage;
import com.tutorialsninja.qa.pageobjects.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends Base {
	
	public RegisterTest() {
		super();
	}
WebDriver driver;
RegisterPage registerPage;
AccountSuccessPage successPage;
@AfterMethod
public void tearDown() {
	driver.quit();
}
@BeforeMethod
public void setUp() {
	
	driver = initializeBrowser(prop.getProperty("browserName"));
	HomePage homePage = new HomePage(driver);
	homePage.clickOnMyAccountOption();
	registerPage = homePage.clickOnRegisterOption();
	
}
	@Test(priority=1)
	public void verifyRegisteringAnAccountWithMandotoryFeilds() {
		successPage = registerPage.registerAccount(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"), prop.getProperty("validPassword"));
		String actualSuccessMessage = successPage.retriveAccountSuccessMessage();
		Assert.assertEquals(actualSuccessMessage, dataProp.getProperty("accountSuccessfullyCreateMessage"),"Account create success message not displyed");
		}
	@Test(priority=2)
	
	public void verifyRegisteringAccountwithAllFeilds() {
	successPage = registerPage.registerAccountWithAllFeilds(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"), prop.getProperty("validPassword"));
		String actualSuccessMessage = successPage.retriveAccountSuccessMessage();
		Assert.assertEquals(actualSuccessMessage, dataProp.getProperty("accountSuccessfullyCreateMessage"),"Account create success message not displyed");
		}
	@Test(priority=3)
	public void verifyRegisteringAccountWithExistingEmailAddress() {
		registerPage.registerAccountWithAllFeilds(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), prop.getProperty("validEmail"), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"), prop.getProperty("validPassword"));
		String actualWarningMesaage = registerPage.retriveEmailWarning();
		Assert.assertTrue(actualWarningMesaage.contains(dataProp.getProperty("duplicateEmailWarning")),"Warning message for duplicate account not displyed");
		}
	@Test(priority=4)
	public void verifyRegisteringAccountWithoutEnteringAnyFeilds() {
	
		registerPage.clickOnContinue();
		String actualWarningMessage = registerPage.retrivePrivacyPolicyWarning();
		Assert.assertTrue(actualWarningMessage.contains(dataProp.getProperty("privacyPolicyWarning")),"Warning message is not displyed");
		
		String actualFirstnameWarning = registerPage.retriveFirstNameWarning();
		Assert.assertEquals(actualFirstnameWarning, dataProp.getProperty("firstNameWarning"),"First name warning is not displayed");
		
		String actualLastNameWarning = registerPage.retriveLastNameWarning();
		Assert.assertEquals(actualLastNameWarning, dataProp.getProperty("lastNameWarning"),"Lastname warning is not displayed");
		
		String actualEmailWarning = registerPage.retriveEmailWarningMessage();
		Assert.assertEquals(actualEmailWarning, dataProp.getProperty("emailWarning"),"Email warning is not displayed");
		
		String actualTelephoneWarning = registerPage.retriveTelephoneWarningMessage();
		Assert.assertEquals(actualTelephoneWarning, dataProp.getProperty("telephoneWarning"),"Telephone warning is not displayed");
		
		String actualPasswordWarning = registerPage.retrivePasswordWarningMessage();
		Assert.assertEquals(actualPasswordWarning, dataProp.getProperty("passwordWarning"),"Password warning is not displyed");
		
		}
}
