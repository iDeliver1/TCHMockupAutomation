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
import com.AP.qa.util.TestUtil;



public class TC001_Single_Product_Checkout1 extends TestBase {
	
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
	
	//(dataProvider = "Run")
	@Test(priority = 1)
	public void LoginTest() throws Throwable{
		GlobalElement=	login.Login_Before_checkout(prop.getProperty("username"), prop.getProperty("password"));
		home=TestUtil.LoginValidation(GlobalElement);
	
	}
	
	
	@Parameters("Product")
	@Test(priority = 2, enabled = true)
	public void BookingTest(String Product) throws Throwable{
		
		try {
			TestUtil.MoveElement(home.Target);	//Moving object to desired postion 
			
			home.Tshirt.click();
			
			WaitForObject(home.qty, "Check");
			
			home.qty.clear();
			
			home.qty.sendKeys(prop.getProperty("Qty"));	//Can give number of quantity 
			
			TestUtil.SelectQuantity(home.size, "L");	//Select Size -S / M /L
			
			home.cart.click();
			
			WaitForObject(home.checkout, "Click");
			
			}
			catch(Exception e) {
				String Cause = e.toString();
				Reporting("Fail", "Payment Page Validation", "Payment Page should displayed ", "Payment Page is unable to show due to"+Cause.substring(1, 88));
				closeBrowser();
			}	
	}
	
	@Test(priority = 3, enabled = true)
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
