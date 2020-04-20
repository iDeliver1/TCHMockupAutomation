package com.AP.qa.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.AP.qa.base.TestBase;
import com.AP.qa.pages.Login;
import com.AP.qa.pages.Logout;
import com.AP.qa.pages.Payment;
import com.AP.qa.pages.Reorder;
import com.AP.qa.pages.homepage;
import com.AP.qa.util.TestUtil;

public class TC003_Reorder_Products_Checkout_3 extends TestBase{

	Login login;
	homepage home;
	Payment pay;
	Logout logout;
	
	@Parameters("Browser")
	@BeforeClass
	public void init(String Browser) throws Throwable {
		initialization(Browser);
		SetUP(this.getClass().getSimpleName(), driver.getTitle());
		login = new Login();
	}
	
	@Test
	public void LoginTest() throws Throwable{
		GlobalElement=	login.Login_Before_checkout(prop.getProperty("username"), prop.getProperty("password"));
		home=TestUtil.LoginValidation(GlobalElement);
	}
	
	
	@Parameters("Product")
	@Test(priority = 2,enabled = true)
	public void ReorderTest(String Product) throws Throwable {
		WaitForObject(home.profile, "Click");		
		home.orderDetails.click();
		home.selectFirstOrder.click();
	}
	
	@Test(priority = 3,enabled = true)
	public void PaymentTest() throws Throwable{
		GlobalValue = TestUtil.CheckoutPriceValidation();
		pay = TestUtil.Argvalidation("CheckOut Price ", GlobalValue,Payment.TotalPrice.getText().replace("$", ""));
		FinalPriceValue = pay.paymentpage();	
		logout = TestUtil.price_validation(FinalPriceValue, Payment.price.getText().replace("$", ""));	
	}
	
	
	@Test(priority = 4, enabled = true)
	public void LogoutTest() throws Throwable {
		GlobalValue = logout.LogoutTest();	
		TestUtil.logoutvalidation(GlobalValue);
	}
	
	
	@AfterClass
	public void Flush() throws Throwable
	{
		closeBrowser();
	}
	
}
