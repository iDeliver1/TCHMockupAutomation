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
import com.AP.qa.pages.Reorder;

public class TC003_Reorder_Products_Checkout extends TestBase {
	Login login;
	Payment pay;
	Logout logout;
	Reorder reorderObj;
	

	@Parameters("Browser")
	@BeforeTest
	public void init(String Browser) throws Throwable{
		initialization(Browser);
	}
	
	@BeforeClass
	public void Setup() throws Throwable {
		SetUP("Automation_Practice", driver.getTitle());
		login = new Login();
		pay = new Payment();
		logout = new Logout();
		reorderObj = new Reorder();
	}
	
	@Test//(dataProvider = "Run")
	void LoginTest() throws Throwable{
		login.loginAP(prop.getProperty("username"), prop.getProperty("password"));
	}
		
	@Test(priority = 2, enabled = true, dependsOnMethods = "LoginTest")
	void reorderTest() throws Throwable{
		reorderObj.ReorderMethod();
	}
	
	@Test(priority = 3, enabled = true, dependsOnMethods = "reorderTest")
	void PaymentTest() throws Throwable{
		pay.paymentpage();	
	}
	
	@AfterClass
	public void Flush() throws Throwable
	{
		logout.LogoutTest();	
	}
	
	@AfterTest
	public void CloseBrowser() {
		closeBrowser();
	}

}
