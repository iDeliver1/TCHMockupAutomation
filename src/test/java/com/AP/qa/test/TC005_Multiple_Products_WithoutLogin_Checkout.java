package com.AP.qa.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.AP.qa.base.TestBase;
import com.AP.qa.pages.Login;
import com.AP.qa.pages.Logout;
import com.AP.qa.pages.Multi_Product_Parameter;
import com.AP.qa.pages.Payment;


public class TC005_Multiple_Products_WithoutLogin_Checkout extends TestBase{
	Login login;
	Multi_Product_Parameter multi;
	Payment pay;
	Logout logout;
	
	
	@Parameters("Browser")
	@BeforeClass
	public void Setup(String Browser) throws Throwable {
		initialization(Browser);
		SetUP(this.getClass().getSimpleName(), driver.getTitle());
		login = new Login();
		multi = new Multi_Product_Parameter();
		pay = new Payment();
		logout = new Logout();
	}
	
	@Test(priority = 1, enabled = true)
	public void BookingTest() throws Throwable{
		multi.SelectProducts();	
	}
	
	
	@Test(priority = 2, enabled = true, dependsOnMethods = "BookingTest")
	public	void PaymentTest() throws Throwable{
		pay.paymentpage();	
	}
	
	@AfterClass
	public void Flush() throws Throwable
	{
		logout.LogoutTest();	
		closeBrowser();
	}

}
