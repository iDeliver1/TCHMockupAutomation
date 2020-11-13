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
import com.AP.qa.util.Business_Layer_Functions;


public class TC003_Reorder_Products_Checkout_3 extends TestBase{
/*
	Login login;

	
	@Parameters("Browser")
	@BeforeClass
	public void init(String Browser) throws Throwable {
		initialization(Browser);
		SetUP(this.getClass().getSimpleName(), driver.getTitle());
		login = new Login();
	}
	
	//Login Test
	@Test
	public void LoginTest() throws Throwable{
		
		Login.signInbtn.click();
		Login.user.sendKeys(prop.getProperty("username"));
		Login.password.sendKeys(prop.getProperty("password"));
		Login.signIn.click();
	
		
		
		if(Login.Beforeloginvalidation()!=null) {
			Reporting("Pass", "Login Page Validation", "User successfull naviagted to homepage with username - "+prop.getProperty("username"), "User should be able to  naviagted to homepage with username - "+prop.getProperty("username"), "");	 
		}
		else {
			Reporting("Fail", "Login Page Validation", "User unsuccessfull naviagted to homepage with username - "+prop.getProperty("username"), "User should be able to  naviagted to homepage with username - "+prop.getProperty("username"), "");
			 closeBrowser();
		}
	}
	
	
	//Select Product Test
	@Parameters("Product")
	@Test(priority = 2,enabled = true)
	public void ReorderTest(String Product) throws Throwable {
		WaitForObject(homepage.profile, "Click");		
		homepage.orderDetails.click();
		homepage.selectFirstOrder.click();
	}
	
	//Payment Test
	@Test(priority = 3,enabled = true)
	public void PaymentTest() throws Throwable{
		GlobalValue = Business_Layer_Functions.getMultiProductValue(homepage.Price, homepage.tax);
		
		if(Business_Layer_Functions.Argvalidation("CheckOut Price ", GlobalValue,homepage.TotalPrice.getText().replace("$", ""))==true) {
			 new Payment();
			 Payment.proceed.click();
		}
		
		Payment.processAddress.click();
		Payment.checkbox.click();
		Payment.processCarrier.click();
		GlobalValue = Payment.amount.getText().replace("$", "");
		Payment.pay_method.click();
		Payment.confirm.click();
		
		if(Business_Layer_Functions.Argvalidation("Final Price Validation", GlobalValue,Payment.price.getText().replace("$", ""))==true) {
				new Logout();
		}
		
	}
	
	//Logout Test
	@Test(priority = 4, enabled = true)
	public void LogoutTest() throws Throwable {
		Logout.signOut.click();
		Business_Layer_Functions.logoutvalidation(Logout.signIn.getText());
	}
	
	@AfterClass
	public void Flush() throws Throwable
	{
		closeBrowser();
	}
	*/
}
