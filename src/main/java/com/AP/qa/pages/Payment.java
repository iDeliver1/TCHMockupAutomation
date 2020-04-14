package com.AP.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.AP.qa.base.TestBase;
import com.AP.qa.util.Extent_Report;


public class Payment extends TestBase{
	
	@FindBy(xpath = "//a[@class='button btn btn-default standard-checkout button-medium']//span[contains(text(),'Proceed to checkout')]")
	WebElement proceed;
	
	@FindBy(xpath = "//button[@name='processAddress']//span[contains(text(),'Proceed to checkout')]")
	WebElement processAddress;
	
	@FindBy(xpath = "//input[@id='cgv']")
	WebElement checkbox;
	
	@FindBy(xpath = "//button[@name='processCarrier']//span[contains(text(),'Proceed to checkout')]")
	WebElement processCarrier; 
	
	@FindBy(xpath = "//a[@class='bankwire']")
	WebElement pay_method;
	
	@FindBy(xpath = "//span[contains(text(),'I confirm my order')]")
	WebElement confirm;
	
	@FindBy(xpath = "//span[@class='price']")
	WebElement price;
	
	@FindBy(xpath = "//strong[contains(text(),'Your order on My Store is complete.')]")
	WebElement order_msg;
	
	@FindBy(xpath = "//span[@id='total_price']")
	WebElement amount;
	
	@FindBy(xpath = "//span[contains(@id,'total_product_price')]")
	List<WebElement> Price;
	
	@FindBy(xpath = "//td[@id='total_shipping']")
	WebElement tax;
	
	@FindBy(xpath = "//span[@id='total_price']")
	WebElement TotalPrice;
	
	@FindBy(xpath = "//input[@id='email']")
	WebElement user;
	
	
	Login login = new Login();
	CreateUser usr = new CreateUser();
	
	Extent_Report objExp = new Extent_Report();
	public Payment() {
		PageFactory.initElements(driver, this);
	}
	
	public void paymentpage() throws Throwable {
		try {
		String amt2 = amount.getText();
		
		String value =  objExp.getMultiProductValue(Price,tax);
		
		try {
			objExp.Argvalidation("Price Varification", value, TotalPrice.getText().replace("$", ""));
		}catch(Exception e) {
			closeBrowser();
		}
		
		proceed.click();
		
		try {
			if(driver.getTitle().contains("Login - My Store")) {
				login.loginAP(prop.getProperty("username"), prop.getProperty("password"));
				}
		}catch(Exception e)
		{
			System.out.println("Already sign in");
		}
		
		processAddress.click();
		checkbox.click();
		processCarrier.click();
		pay_method.click();
		confirm.click();
		price_validation(amt2);
		payment_validation();
		}
		catch(Exception e){
			String Cause = e.toString();
			Reporting("Fail", "Payment Page Validation", "Payment Page  should displayed ", "Payment Page is unable to show due to"+Cause.substring(1, 88));
			closeBrowser();
		}
	}
	
	public void Create_new_User() throws Throwable {
		String amt = amount.getText();
		proceed.click();
		usr.RealTimeUserMethod();
		processAddress.click();
		checkbox.click();
		processCarrier.click();
		pay_method.click();
		confirm.click();
		price_validation(amt);
		payment_validation();
	}
	
	public void payment_validation() throws Throwable {
		
		try{
			String amt = price.getText();
			Assert.assertTrue(order_msg.isDisplayed());
			Reporting("Pass","Payment Validation","Payment successfull","Payment should be done");
			log("Payment successfull of amount -"+amt);
			
		}catch(Exception e)
		{
			System.out.println(e);
			Reporting("Fail","Payment Validation","Payment Failed","Payment should be done");
			log("Payment failed");
			
		}
	}
	
	public void price_validation(String amt2) throws Throwable {
		try{
			String amt = price.getText();
			Assert.assertEquals(amt, amt2);
			Reporting("Pass","CheckOut Price Validation","Actual Price -"+amt2,"Expected Price -"+amt);
			log("Successfully validated the order amount -"+amt2);
			
		}catch(Exception e)
		{
			String amt = price.getText();
			System.out.println(e);
			Reporting("Fail","CheckOut Price Validation","Actual Price -"+amt2,"Expected Price -"+amt);
			log("Price validation failed");
			
		}
	}
}
