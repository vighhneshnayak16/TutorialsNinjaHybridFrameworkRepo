package com.tutorialsninja.qa.testcases;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utilis.Utilities;

public class RegisterTest extends base {
	
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;
	
	public RegisterTest() {
		super();
	}
	

	public WebDriver  driver;
	  
	@BeforeMethod
	public void setup() {
		   
		driver=initializeBrowserOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage =new HomePage(driver);
		registerPage = homePage.navigateToRegisterPage();
		
	}
	
	@AfterMethod
	 public void  tearDown() {
		 driver.quit();
	 }

	@Test(priority=1)
	public void  verifyRegisteringAnaccountWithMandatoryFields() {
		accountSuccessPage = registerPage.registerMandatoryFields(dataProp.getProperty("firstName"), dataProp.getProperty("LastName"), Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"));
        Assert.assertEquals(accountSuccessPage.retrieveAccountSuccessHeading(), dataProp.getProperty( "accountSuccessfullyCreatedHeading"),"Account Success page is not displayed");
       
	}
    @Test(priority=2)
	public void verifyRegisteringAcountByProvidingAllFields() {
    	
    	accountSuccessPage = registerPage.registerPageAllFields(dataProp.getProperty("firstName"), dataProp.getProperty("LastName"), Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"));
    
        Assert.assertEquals(accountSuccessPage.retrieveAccountSuccessHeading(), dataProp.getProperty( "accountSuccessfullyCreatedHeading"),"Account Success page is not displayed");
        
     }
    @Test(priority=3)
	public void verifyRegisteringAccountWithExitingEmailAddress() {
         
    	
    	
    	registerPage.registerPageAllFields(dataProp.getProperty("firstName"), dataProp.getProperty("LastName"), prop.getProperty("validEmail"), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"));
  
        Assert.assertTrue(registerPage.retrieveDuplicateEmailAddressWarning().contains(dataProp.getProperty("duplicateEmailWarning")),"Warning message regarding  duplicate email address isn ot displayed");
     
    	
	}
   @Test(priority=4)
    public void verifyRegisteringAccountWithoutFillingAnyDetails() {
	   
	  
	   registerPage.clickOnContinueButton();
       
	   Assert.assertTrue(registerPage.displayStatusOfWarningMessages(dataProp.getProperty("privacyPolicyWarning"),dataProp.getProperty("firstNameWarning"), dataProp.getProperty("lastNameWarning"),  dataProp.getProperty("emailWarning"), dataProp.getProperty("telephoneWarning"), dataProp.getProperty("passwordWarning")));
      
   }
}
