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
import com.AP.qa.util.Excel_Libraries;
import com.AP.qa.util.Genral_Function;
import com.AP.qa.util.TestUtil;


public class TC005_Multiple_Products_WithoutLogin_Checkout_5 extends TestBase{
	
	
	@Parameters("Browser")
	@BeforeClass
	public void Setup(String Browser) throws Throwable {
		initialization(Browser);
		SetUP(this.getClass().getSimpleName(), driver.getTitle());
		 new homepage();
	}
	
	//Select Product test
	@Parameters("Product")
	@Test(priority = 1, enabled = true)
	public void BookingTest(String Product) throws Throwable{
		//Selection for Multiple products
		int counter = 0;
		WaitForObject(homepage.Target, "check");
		try {
			int multiproducts  = Integer.parseInt(prop.getProperty("MultiProduct"));
			
			for(int j = 0;j<multiproducts;j++ ) {
					
				System.out.println(homepage.MultiProducts.size());
					for(int i=0;i<=homepage.MultiProducts.size();i++)
					{
						try {
						if(Excel_Libraries.getdata(j).isEmpty()==false) {
							
							if(homepage.MultiProducts.get(i).getText().contains(Excel_Libraries.getdata(j)))
							{
							//	Reporting("Pass", j+1+" Validation for Input Value", "There must be an Input value", " Input Value"+Excel_Libraries.getdata(j));
								TestUtil.MoveElement(homepage.MultiProducts.get(i));
								
								WaitForObject(homepage.Addtocart.get(i), "Click");
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
						homepage.ProccedCheckout.click();
					}
					else
						WaitForObject(homepage.ContinueShop, "Click");		
			}
		
			
			}
			catch(Exception e) {
				String Casue = e.toString();
				Reporting("Fail", "Home  Page  Validation", "Home Page should displayed ", "Home Page is unable to show due to"+Casue.substring(1, 88));
				closeBrowser();
			}
	
					}
	

	//payment & login test
	@Test(priority = 2, enabled = true)
	public void PaymentTest() throws Throwable{
		GlobalValue = Genral_Function.getMultiProductValue(Payment.Price, Payment.tax);
		
		if(Genral_Function.Argvalidation("CheckOut Price ", GlobalValue,Payment.TotalPrice.getText().replace("$", ""))==true) {
			homepage.proceed.click();
				new Login();
				try {
					
					//Checking For User is logged in or not 
				if(driver.getTitle().contains("Login - My Store")) {
						GlobalElement = Login.Login_After_checkout(prop.getProperty("username"), prop.getProperty("password"));
						if(Genral_Function.LoginValidation(GlobalElement)) {
								new Payment();	
						}
				}
				}catch(Exception e) {
				new Payment();
				System.out.println("Alread Logged in");	
				}
				
				Payment.processAddress.click();
				Payment.checkbox.click();
				Payment.processCarrier.click();
				GlobalValue = Payment.amount.getText().replace("$", "");
				Payment.pay_method.click();
				Payment.confirm.click();
				
				if(Genral_Function.Argvalidation("Final Price Validation", GlobalValue,Payment.price.getText().replace("$", ""))==true) {
					 new Logout();
				}	
		}
	}
	

//Logout test
	@Test(priority = 4, enabled = true)
	public void LogoutTest() throws Throwable {
		Logout.signOut.click();
		GlobalValue = Logout.signIn.getText();
		Genral_Function.logoutvalidation(GlobalValue);
	}
	
	@AfterClass
	public void Flush() throws Throwable
	{
		
		closeBrowser();
	}
	
}
