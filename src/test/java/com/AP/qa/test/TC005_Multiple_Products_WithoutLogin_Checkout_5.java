package com.AP.qa.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.AP.qa.base.TestBase;
import com.AP.qa.pages.Logout;
import com.AP.qa.pages.Payment;
import com.AP.qa.pages.homepage;
import com.AP.qa.util.Excel_Libraries;
import com.AP.qa.util.TestUtil;


public class TC005_Multiple_Products_WithoutLogin_Checkout_5 extends TestBase{
	homepage home;
	Payment pay;
	Logout logout;
	
	@Parameters("Browser")
	@BeforeClass
	public void Setup(String Browser) throws Throwable {
		initialization(Browser);
		SetUP(this.getClass().getSimpleName(), driver.getTitle());
		home = new homepage();
	}
	
	@Parameters("Product")
	@Test(priority = 1, enabled = true)
	public void BookingTest(String Product) throws Throwable{
		//Selection for Multiple products
		
				int counter = 0;
				try {
					int multiproducts  = Integer.parseInt(prop.getProperty("MultiProduct"));
					
					for(int j = 0;j<multiproducts;j++ ) {
							
						System.out.println(home.MultiProducts.size());
							for(int i=0;i<=home.MultiProducts.size();i++)
							{
								try {
								if(Excel_Libraries.getdata(j).isEmpty()==false) {
									
									if(home.MultiProducts.get(i).getText().contains(Excel_Libraries.getdata(j)))
									{
										Reporting("Pass", j+1+" Validation for Input Value", "There must be an Input value", " Input Value"+Excel_Libraries.getdata(j));
										TestUtil.MoveElement(home.MultiProducts.get(i));
										
										WaitForObject(home.Addtocart.get(i), "Click");
										counter=counter+1;
										break;
									}
								}
							}
							catch(Exception f){
								f.printStackTrace();
								Reporting("Fail", j+1+" Validation for Input Value", "There must be an Input value", " Input Value"+Excel_Libraries.getdata(j));
							}
							}
							
							if(counter==multiproducts) {
								home.ProccedCheckout.click();
							}
							else
								WaitForObject(home.ContinueShop, "Click");		
					}
				
					
					}
					catch(Exception e) {
						String Casue = e.toString();
						Reporting("Fail", "Home  Page  Validation", "Home Page should displayed ", "Home Page is unable to show due to"+Casue.substring(1, 88));
						closeBrowser();
					}
				
				
	}
	

	@Test(priority = 2, enabled = true)
	public void PaymentTest() throws Throwable{
		GlobalValue = TestUtil.CheckoutPriceValidation();
		pay = TestUtil.Argvalidationforlogin("Check Out Price ", GlobalValue,Payment.TotalPrice.getText().replace("$", ""));
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
