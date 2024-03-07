 package com.tutorialsninja.qa.testcases;


 
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utilis.Utilities;

public class LoginTest extends base {
	
	LoginPage loginPage;
	
	public LoginTest() {
		super();
	}
	
	 public WebDriver driver;
  
	@BeforeMethod
	public void setup() {
		driver=initializeBrowserOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);  
		loginPage=homePage.naviageToLoginPage();
	}
   
   
	@AfterMethod
	public void teardown() {
	driver.quit();

	}
	@Test(priority=1,dataProvider="validCredentialsSupplier")
	public void verifyLoginWithValidCredentials(String email, String password) {

    AccountPage accountPage = loginPage.login(email, password);
    Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption(),"Edit your account information is not displayed");
	}
	@DataProvider(name="validCredentialsSupplier")
	public Object[][] supplyTestData() {
		
		Object[][] data= Utilities.getTestDataFromExcel("Login");
		return data;
	}
	
	@Test(priority=2)
	 public void verifyLoginWithInvalidCrendentials() {
		
		loginPage.login(Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("invalidPassword"));		
		Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("emailpasswordNoMatchWarning") ),"Edit your account information is not displayed");
	   
	 }
	
	@Test(priority=3)
	public void verifyLoginWithInvalidEmailAndValidPassword() {
	    loginPage.login(Utilities.generateEmailWithTimeStamp(), "validPassword");
		
	    Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("emailpasswordNoMatchWarning") ),"Edit your account information is not displayed");
	}
	@Test(priority=4)
	public void verifyLoginWithValidEmailAndInvalidPassword() {
		loginPage.login(prop.getProperty("validEmail"), dataProp.getProperty("invalidPassword"));
		
		Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("emailpasswordNoMatchWarning") ),"Edit your account information is not displayed");
	}
	
	
	@Test(priority=5)
	public void verifyLoginWithoutProvideCredentials() {
	  
		loginPage.clickOnLoginButton();
		Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("emailpasswordNoMatchWarning") ),"Edit your account information is not displayed");
		   
	}	
	}
	
	
	