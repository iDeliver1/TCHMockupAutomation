package com.AP.qa.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.AP.qa.base.TestBase;
import com.AP.qa.pages.Login;
import com.AP.qa.pages.Logout;
import com.AP.qa.pages.Payment;
import com.AP.qa.pages.homepage;

public class TC004_Single_Products_WithoutLogin_Checkout extends TestBase{

	Login login;
	homepage home;
	Payment pay;
	Logout logout;
	

	
	@BeforeTest
	public void init() throws Throwable{
		
	}
	
	@Parameters("Browser")
	@BeforeClass
	public void Setup(String Browser) throws Throwable {
		initialization(Browser);
		SetUP(this.getClass().getSimpleName(), driver.getTitle());
		login = new Login();
		home = new homepage();
		pay = new Payment();
		logout = new Logout();
	}
	
	
	@Test(priority = 1, enabled = true)
	public void BookingTest() throws Throwable{
		home.order_product();
	}
	

	@Test(priority = 2, enabled = true, dependsOnMethods = "BookingTest")
	public void PaymentTest() throws Throwable{
		pay.paymentpage();	
		
	}
	
	@AfterClass
	public void Flush() throws Throwable
	{
		logout.LogoutTest();	
		closeBrowser();
	}
	
	@AfterTest
	public void CloseBrowser() {
		
	}
}
