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



public class TC001_Single_Product_Checkout extends TestBase {
	
	Login login;
	
	@Parameters("Browser")
	@BeforeClass
	public void init(String Browser) throws Throwable {
		initialization(Browser);
		SetUP(this.getClass().getSimpleName(), driver.getTitle());
		 new Login();
	}
	
	
	
	//Login test
	@Test(priority = 1)
	public void LoginTest() throws Throwable{
		//GlobalElement=	login.Login_Before_checkout(prop.getProperty("username"), prop.getProperty("password"));
		
		Login.signInbtn.click();
		Login.user.sendKeys(prop.getProperty("username"));
		Login.password.sendKeys(prop.getProperty("password"));
		Login.signIn.click();
		Login.home.click();
		
		
		if(Login.Beforeloginvalidation()!=null) {
			 Reporting("Pass", "Login Page Validation", "User successfull naviagted to homepage with username - "+prop.getProperty("username")+" & password - "+prop.getProperty("password"), "User should be able to  naviagted to homepage with username - "+prop.getProperty("username")+" & password - "+prop.getProperty("password"));	 
		}
		else {
			 Reporting("Fail", "Login Page Validation", "User unsuccessfull naviagted to homepage with username - "+prop.getProperty("username")+" & password - "+prop.getProperty("password"), "User should be able to  naviagted to homepage with username - "+prop.getProperty("username")+" & password - "+prop.getProperty("password"));
			 closeBrowser();
		}
		
	}
	
	
	//Select Product Test
	@Parameters("Product")
	@Test(priority = 2, enabled = true)
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
	
	
	//Payment test
	@Test(priority = 3, enabled = true)
	public void PaymentTest() throws Throwable{
		GlobalValue = Genral_Function.getMultiProductValue(homepage.Price, homepage.tax);
		GlobalArgumrnt = Genral_Function.Argvalidation("CheckOut Price ", GlobalValue,homepage.TotalPrice.getText().replace("$", ""));
		
		try{
		if(homepage.BeforeLogin_PriceValidation(GlobalArgumrnt)!=null) {
			Reporting("Pass", "Payment Page Validation", "User successfully navigate to Patment Page", "User should be able to navigate to Patment Page");
			Payment.proceed.click(); 
		}
		}
		catch(Exception e)
		{
			Reporting("Fail", "Payment Page Validation", "User unsuccessfully navigate to Patment Page", "User should be able to navigate to Patment Page");
			closeBrowser();
		}
		Payment.processAddress.click();
		Payment.checkbox.click();
		Payment.processCarrier.click();
		GlobalValue = Payment.amount.getText().replace("$", "");
		Payment.pay_method.click();
		Payment.confirm.click();
		
		if(Genral_Function.Argvalidation("Final Price Validation", GlobalValue,Payment.price.getText().replace("$", ""))==true) {
		Payment.logoutvalidation();
		}
		
	}
	
	
	//logout test
	@Test(priority = 4, enabled = true)
	public void LogoutTest() throws Throwable {
		Logout.signOut.click();
		Genral_Function.logoutvalidation(Logout.signIn.getText());
	}
	
	@AfterClass
	public void Flush() throws Throwable
	{
		closeBrowser();
	}
	
	
}
