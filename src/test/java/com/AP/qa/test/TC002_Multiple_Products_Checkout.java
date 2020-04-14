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
import com.AP.qa.pages.homepage;

public class TC002_Multiple_Products_Checkout extends TestBase{

	Login login;
	homepage home;
	Payment pay;
	Logout logout;
	Multi_Product_Parameter multi;

	
	@Parameters("Browser")
	@BeforeClass
	public void init(String Browser) throws Throwable {
		initialization(Browser);
		SetUP(this.getClass().getSimpleName(), driver.getTitle());
		login = new Login();
		multi = new Multi_Product_Parameter();
		home = new homepage();
		pay = new Payment();
		logout = new Logout();
	}
	
	@Test//(dataProvider = "Run")
	public void LoginTest() throws Throwable{
		login.Login_Before_checkout(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority = 2, enabled = true)
	public void MultiplePro()throws Throwable {
		multi.SelectProducts();	
	}
	
	@Test(priority = 3, enabled = true)
	public void PaymentTest() throws Throwable{
		pay.paymentpage();	
	}
	
	@AfterClass
	public void Flush() throws Throwable
	{
		logout.LogoutTest();	
		closeBrowser();
	}
	
	
}
