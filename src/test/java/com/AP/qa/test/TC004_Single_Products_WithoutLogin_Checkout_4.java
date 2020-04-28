package com.AP.qa.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.AP.qa.base.TestBase;
import com.AP.qa.pages.Login;
import com.AP.qa.pages.Logout;
import com.AP.qa.pages.Payment;
import com.AP.qa.pages.homepage;
import com.AP.qa.util.Genral_Function;
import com.AP.qa.util.TestUtil;

public class TC004_Single_Products_WithoutLogin_Checkout_4 extends TestBase{

	
	
	
	@Parameters("Browser")
	@BeforeClass
	public void Setup(String Browser) throws Throwable {
		initialization(Browser);
		SetUP(this.getClass().getSimpleName(), driver.getTitle());
		 new homepage();
		
	}
	
	//Select prdouct test
	@Parameters("Product")
	@Test(priority = 1, enabled = true)
	public void BookingTest(String Product) throws Throwable{
		try {
			TestUtil.MoveElement(homepage.Target);	//Moving object to desired postion 
			
			homepage.Tshirt.click();
			
			WaitForObject(homepage.qty, "Check");
			
			homepage.qty.clear();
			
			homepage.qty.sendKeys(prop.getProperty("Qty"));	//Can give number of quantity 
			
			TestUtil.SelectQuantity(homepage.size, "L");	//Select Size -S / M /L
			
			homepage.cart.click();
			
			WaitForObject(homepage.checkout, "Click");
			
			}
			catch(Exception e) {
				String Cause = e.toString();
				Reporting("Fail", "Payment Page Validation", "Payment Page should displayed ", "Payment Page is unable to show due to"+Cause.substring(1, 88));
				closeBrowser();
			}
	}
	

	//payment & login test
	@Test(priority = 2, enabled = true)
	public void PaymentTest() throws Throwable{
		
		GlobalValue = Genral_Function.getMultiProductValue(homepage.Price, homepage.tax);
		
		if(Genral_Function.Argvalidation("CheckOut Price ", GlobalValue,homepage.TotalPrice.getText().replace("$", ""))==true) {
			homepage.proceed.click();
				new Login();
				try {
					//Checking For User is logged in or not 
				if(driver.getTitle().contains("Login - My Store")) {
				GlobalElement = Login.Login_After_checkout(prop.getProperty("username"), prop.getProperty("password"));
						if(Genral_Function.LoginValidation(GlobalElement)) {
								 new Payment();	
						}
				}
				}catch(Exception e) {
					new Payment();
				System.out.println("Alread Logged in");	
				}
				
				Payment.processAddress.click();
				Payment.checkbox.click();
				Payment.processCarrier.click();
				GlobalValue = Payment.amount.getText().replace("$", "");
				Payment.pay_method.click();
				Payment.confirm.click();
				
				if(Genral_Function.Argvalidation("Final Price Validation", GlobalValue,Payment.price.getText().replace("$", ""))==true) {
					new Logout();
				}	
		}
	}
	


	//Logout test
	@Test(priority = 4, enabled = true)
	public void LogoutTest() throws Throwable {
		Logout.signOut.click();
		GlobalValue = Logout.signIn.getText();
		Genral_Function.logoutvalidation(GlobalValue);
	}
	
	@AfterClass
	public void Flush() throws Throwable
	{
		
		closeBrowser();
	}
	
}
