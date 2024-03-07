package com.tutorialsninja.qa.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;
	
	 @FindBy(id="input-firstname")
	 private WebElement firstNameField;
	
	 @FindBy(id="input-lastname")
	 private WebElement lastNameField;
	
	 @FindBy(id="input-email")
     private WebElement emailAddressField;
    
	 @FindBy(id="input-telephone")	
     private WebElement telephoneField;
	
	 @FindBy(id="input-password")
     private WebElement passwordField;

	 @FindBy(id="input-confirm")
	 private WebElement confirmPasswordField;
     
	 @FindBy(name="agree")
     private WebElement privacyPolicyField;
    
	 @FindBy(xpath="//input[@value='Continue']")
	 private WebElement continueButton;
     
	 @FindBy(xpath="//input[@name=\"newsletter\"][@value=\"1\"]")
	 private WebElement yesNewsletterOption;
	 
	 @FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	 private WebElement duplicateEmailAddressWarning;
	 
	 @FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	 private WebElement privacyPolicyWarning;
	 
	 @FindBy(xpath="//input[@id=\"input-firstname\"]/following-sibling::div")
	 private WebElement firstNameWarning;
	 
	 @FindBy(xpath="//input[@id=\"input-lastname\"]/following-sibling::div")
	 private WebElement lastNameWarning;
	 
	 @FindBy(xpath="//input[@id=\"input-email\"]/following-sibling::div")
	 private WebElement emailWarning;
	
	 @FindBy(xpath="//input[@id=\"input-telephone\"]/following-sibling::div")
	 private WebElement telephoneWarning;
	 
	 @FindBy(xpath="//input[@id=\"input-password\"]/following-sibling::div")
	 private WebElement passwordWarning;
	  
	 public RegisterPage(WebDriver driver) {
		 
		   this.driver= driver;
		   PageFactory.initElements(driver, this);
	  }
	 public String  retrievePasswordWarning() {
		 String passwordWarningText = passwordWarning.getText();
		 return passwordWarningText;
	 }
	 public String  retrieveTelephoneWarning() {
		 String telephoneWarningText = telephoneWarning.getText();
		 return telephoneWarningText;
	 }
	 public String  retrieveEmailWarning() {
		 String emailWarningText = emailWarning.getText();
		 return emailWarningText;
	 }
	 public String  retrieveLasttNameWarning() {
		 String LastNameWarningText = lastNameWarning.getText();
		 return LastNameWarningText;
	 }
	 public String  retrieveFirstNameWarning() {
		 String FirstNameWarningText = firstNameWarning.getText();
		 return FirstNameWarningText;
	 }
	 public String retrievePrivacyPolicyWarning() { 
		String privacyPolicyWarningText= privacyPolicyWarning.getText();
	    return privacyPolicyWarningText;
	 }
	  //action

     public void enterFirstName(String firstNameText) { 
    	 firstNameField.sendKeys(firstNameText);
     }
     public void enterLastName(String lastNameText) { 
    	 lastNameField.sendKeys(lastNameText);
     }
     public void enterEmailAddress(String emailText) { 
    	 emailAddressField.sendKeys( emailText);
     }
     public void enterTelephoneNumber(String telephoneText) { 
    	 telephoneField .sendKeys(telephoneText);
     }
     public void enterPasswordFiled(String passwordText) { 
    	 passwordField.sendKeys(passwordText);
     } 
     public void enterConfirmPasswordFiled(String confirmPasswordText) { 
    	 confirmPasswordField.sendKeys(confirmPasswordText);
     } 
     public void selectPrivacyPolicy() { 
    	 privacyPolicyField.click();
     } 
     
     public  AccountSuccessPage  clickOnContinueButton() {
    	 continueButton.click();
    	 return new AccountSuccessPage(driver);
    	 
     }
     public void selectYesNewsletterOption() {
    	 yesNewsletterOption.click();
     }

     public String retrieveDuplicateEmailAddressWarning() {
    	String duplicateEmailAddressWarningText= duplicateEmailAddressWarning.getText();
         return duplicateEmailAddressWarningText;
    		 }
     
       public  AccountSuccessPage registerMandatoryFields(String firstNameText,String lastNameText,String  emailText,String  telephoneText,String  passwordText) {
    	   firstNameField.sendKeys(firstNameText); 
    	   lastNameField.sendKeys(lastNameText);
    	   emailAddressField.sendKeys(emailText);
    	   telephoneField .sendKeys(telephoneText);
    	   passwordField.sendKeys(passwordText);
    	   confirmPasswordField.sendKeys(passwordText);
    	   privacyPolicyField.click();
    	   continueButton.click();
    	   return new AccountSuccessPage(driver);
       }
      public AccountSuccessPage registerPageAllFields(String firstNameText,String lastNameText,String emailText,String telephoneText,String passwordText) {
    	  
    	  firstNameField.sendKeys(firstNameText);
    	  lastNameField.sendKeys(lastNameText);
    	  emailAddressField.sendKeys( emailText);
    	  telephoneField .sendKeys(telephoneText);
    	  passwordField.sendKeys(passwordText);
    	  confirmPasswordField.sendKeys(passwordText);
    	  yesNewsletterOption.click();
    	  privacyPolicyField.click();
    	  continueButton.click();
    	  return new AccountSuccessPage(driver);
      }
     public  boolean displayStatusOfWarningMessages(String expectedPrivacyPolicyWarning, String expectedFirstNameWarning,String expectedLastNameWarning,String expectedEmailWarning,String expectedTelephoneWarning,String expectedPasswordWarning) {
    	 
    	 
    	 boolean privacyPolicyWarningStstus =  privacyPolicyWarning.getText().contains(expectedPrivacyPolicyWarning);    
         boolean firstNameWarningStatus = firstNameWarning.getText().contains(expectedFirstNameWarning);
         boolean lastNameWarningStatus =lastNameWarning.getText().contains(expectedLastNameWarning);
         boolean emailWarningStatus = emailWarning.getText().contains(expectedEmailWarning);
         boolean telephoneWarningStatus =  telephoneWarning.getText().contains(expectedTelephoneWarning);
         boolean passwordWarningStatus = passwordWarning.getText().contains(expectedPasswordWarning);
 
         return privacyPolicyWarningStstus && firstNameWarningStatus && lastNameWarningStatus && emailWarningStatus && telephoneWarningStatus &&passwordWarningStatus;
     }
}
