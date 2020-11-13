package com.AP.qa.test;


import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.AP.qa.base.TestBase;
import com.AP.qa.pages.homepage;
import com.AP.qa.util.Business_Layer_Functions;




public class TC001_Single_Product_Checkout extends TestBase {
	
	//Variable Declaration 
	WebElement element ;
	Business_Layer_Functions objFun;
	Boolean Pass_Fail;
	
	//Initializing Browser 
	@Parameters("Browser")
	@BeforeClass
	public void init(String Browser) throws Throwable {
		
		initialization(Browser);
		
		SetUP(this.getClass().getSimpleName(), driver.getTitle());
		
		objFun = new Business_Layer_Functions();
		
	}
	
	
	
	//First Method Login test
	@Test(priority = 1,enabled = true)
	public void Login_Test() throws Throwable{
		
		element = objFun.LoginPerform(prop.getProperty("username"), prop.getProperty("password"));
		
		Pass_Fail = objFun.LoginValidation(element);
		
		Reporting_Description("Login Validation", "User Successfully Loged in", "User Should be able to logged in", "Failed to logged in");
		
	}
	
	
	//Second Method Booking_Test
	@Parameters("Product")
	@Test(priority = 2)
	public void Booking_Test(String Product) throws Throwable {
	
		Pass_Fail = objFun.Single_Product_Selection();
		
		Reporting_Description("CheckOut Page Validation",  "CheckOut Page is displayed","User should be able to navigate to CheckOut Page", "CheckOut Page is failed to show ");
		
	}
	
	
	//Third Method CheckOut_Test()
	@Test(priority = 3)
	public void CheckOut_Test() throws Throwable{
		
		GlobalValue = objFun.getMultiProductValue();  //Getting Price Value of a Product
		
		Pass_Fail = objFun.Argvalidation( GlobalValue,homepage.TotalPrice.getText().replace("$", ""));
		
		Reporting_Description("CheckOut Price Validation", "Check price is equal to"+GlobalValue, "CheckOut Price should be equal to "+homepage.TotalPrice.getText().replace("$", ""), "Checkout Price Doesnt Match");
		
		objFun.ClicktoElement(homepage.proceed);
	}
	
	
	//Fourth Method Payment_Test
	@Test(priority = 4)
	public void FinalPayment_Test() throws Throwable {
		
		Pass_Fail = objFun.Argvalidation( GlobalValue,objFun.Payment_Process()); //Comparing Initial  Price with Final Price
		
		Reporting_Description("Final Price Validation", "Final price is equal to"+GlobalValue, "Final Price should be equal to "+objFun.GetFinalPrice(), "Final Price Doesnt Match");
		
	}
	
	//Fifth Method logout test
	@Test(priority = 5, enabled = true)
	public void Logout_Test() throws Throwable {
		
		Pass_Fail=objFun.logoutvalidation();
		
		Reporting_Description("Logout Validation", "Logout successfull", "User should be able to Logout", "Logout Failed");
		
		LastCall=true;
			
	}
	
	//Checking Test Method whether Pass or Fail
	@AfterMethod
	public void CheckMethod() throws Throwable {
		
		if(Pass_Fail) {
			Reporting("Pass", StepDes, Actual, PassExp);
		}
		else {
			Reporting("Fail", StepDes, Actual, FailExp+" casued by "+cause);
			TearDown();
		}
	}
	
	//Closing Browser 
	@AfterClass
	public void TearDown() throws Throwable
	{
		closeBrowser();
	}
	
	
}
