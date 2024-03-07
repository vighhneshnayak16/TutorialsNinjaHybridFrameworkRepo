package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class SearchTest extends base {
  
	HomePage homePage;
	
	SearchPage searchPage;
	public SearchTest() {
		super();
	}
	
	public WebDriver driver;
	@BeforeMethod
	public void setUp() {
		
		driver=initializeBrowserOpenApplicationURL(prop.getProperty("browserName"));
	    homePage =new HomePage(driver);
	}
	
    @AfterMethod
	public void tearDown() {
		 
		driver.quit();
	}
	
	@Test(priority=1)
    public void verifySearchWithValidProduct() {
    	
		searchPage = homePage.searchForProduct(dataProp.getProperty("validProduct"));
		Assert.assertTrue(searchPage.displayStatusOfHPValidProduct(),"Valid product HP is not displayed in the search results"); 
    }
	
	@Test(priority=2)
    public void verifySearchWithInValidProduct() {
		
		searchPage = homePage.searchForProduct(dataProp.getProperty("InvalidProduct"));
		Assert.assertEquals( searchPage.reteieveNoProductMessageText(), dataProp.getProperty("NoProductTextInSearchResult"),"No product message in search results is not displayed");
    }
	
	@Test(priority=3)
	public void verifysearchWithoutAnyProduct() {
	
		searchPage =homePage.clickOnSearchButton();
		Assert.assertEquals( searchPage.reteieveNoProductMessageText(), dataProp.getProperty("NoProductTextInSearchResult"),"No product message in search results is not displayed");
    
	}
}
