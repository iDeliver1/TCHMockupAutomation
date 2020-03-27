package com.AP.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.AP.qa.base.TestBase;
import com.AP.qa.util.TestUtil;

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
	
	
	
	public Payment() {
		PageFactory.initElements(driver, this);
	}
	
	public void paymentpage() throws Throwable {
		String amt2 = amount.getText();
		proceed.click();
		processAddress.click();
		checkbox.click();
		processCarrier.click();
		pay_method.click();
		confirm.click();
		price_validation(amt2);
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
			Reporting("Pass","Price Validation","Actual Price -"+amt2,"Expected Price -"+amt);
			log("Successfully validated the order amount -"+amt2);
			
		}catch(Exception e)
		{
			String amt = price.getText();
			System.out.println(e);
			Reporting("Fail","Price Validation","Actual Price -"+amt2,"Expected Price -"+amt);
			log("Price validation failed");
			
		}
	}
}
