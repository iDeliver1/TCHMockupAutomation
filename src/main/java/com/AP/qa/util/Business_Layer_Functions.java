package com.AP.qa.util;


import java.util.List;


import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.AP.qa.base.TestBase;
import com.AP.qa.pages.Checkout;
import com.AP.qa.pages.Login;
import com.AP.qa.pages.Logout;
import com.AP.qa.pages.Payment;
import com.AP.qa.pages.Reorder;
import com.AP.qa.pages.homepage;





public class Business_Layer_Functions extends TestBase {
	
	Login objLogin;
	homepage objHome ;
	Checkout objCheck ;
	Logout objLogout ;
	Payment objPay;
	Reorder objReorder ;
	int pass=0,failelement = -1,failpageload = -2;
	WebElement element;
	
	public Business_Layer_Functions() {
		
		 objLogin = new Login();
		 objHome = new homepage();
		 objCheck = new Checkout();
		 objLogout = new Logout();
		 objPay = new Payment();
		 objReorder = new Reorder();
		 
	}
	
	//-----------------------------------Function for Login Performance-------------------------------------- 
	public WebElement LoginPerform(String Username, String Password) {
		
		element = objLogin.LoginAction(Username, Password);
			ClicktoElement(objLogin.home);
			return element;
		
	}
	
	
  //---------------------------------Function for Single Product Selection-----------------------------------
	public Boolean Single_Product_Selection() throws Throwable {
		
		objHome.order_product();
		try {
			WebElement element  =  objHome.returnProcessElement();
			Assert.assertEquals(true, element.isDisplayed());
			return true;
		}catch(Exception e) {
			cause = e.toString();
			return false;
		}
			
	
	}
	
	
	//----------------------Function for Price Validation---------------------------------------
	public  int BeforeLogin_PriceValidation(Boolean Expected) {
		try {
			Assert.assertTrue(Expected);
			ClicktoElement(objPay.proceed);
			
			return pass;
		
		}catch(Exception e) {
			cause = e.toString();
			return  failelement;
		}
	}
	
	
	
	
	//---------------Function for Payment Process--------
	public String Payment_Process() throws Throwable { 
		
		 objPay.paymentpage();
		return FinalPrice.replace("$", "");
	}
	
	//---------------Function for Logout Process--------
	public String LogoutPerform() throws Throwable {
		
			return objLogout.LogoutTest();
		
		
	}

	//-------------Function For Login Page Validation----------------------
	public  boolean LoginValidation(WebElement element) throws Throwable {
		try {
			Assert.assertEquals(true, element.isDisplayed());
			
			return true;
		}catch(Exception e) {
			cause = e.toString();
			return false;
		}
		
	}
	//---------------Function for Getting Price Value--------
	public String GetFinalPrice() {
		return FinalPrice.replace("$", "");
	}
	
	//----------------Function for Logout Validation--------------------
	public  Boolean logoutvalidation() throws Throwable {
		try {
			String signin = LogoutPerform();
					Assert.assertEquals(true, signin.contains("Sign in"));
					
					return true;
				
				}catch(Exception e)
				{
					System.out.println(e);
					cause = e.toString();
					return false;
					
				}
	}
	

	//---------------------Function for Adding Values------------------
	public    String getMultiProductValue() {
		   float value = 0;
		   List<WebElement> element = objHome.Price;
		   WebElement prtx = objHome.tax;
		   
		   try {
			   
			   for(int i =0;i<element.size();i++) {
				   String a = element.get(i).getText();
				  value = value +Float.parseFloat(a.replace("$", ""));
			   }
			   value = value+Float.parseFloat(prtx.getText().replace("$", ""));
			   
			   return String.format("%.02f", value);
			   
		   }catch(Exception e) {
			   e.printStackTrace();
			 
		   }   
		return null;		
}
	
	
	//---------------Function for Status Code--------
	public String Status_Code_Response(int code) {
		if(code==-1) {
			return "Element not found";
		}
		else if(code==-2) {
			return "Page not Found";
		}
		
		return null;
		
	}

	//------------------Function for Argument Validation-------------------------
	public  Boolean Argvalidation(String Actual,String Expected) throws Throwable{
		try{
			Assert.assertEquals(Actual, Expected);
			return true;
			}catch(Exception e){ 
				cause = e.toString();
				return false;
			}
		
	
	}

	//---------------Function for Error_Reason--------
	public String Error_Reason(String Reason) {
		switch (Reason) {
		  case "Login":
		   return "Failed to Loged in";
		   
		  case "Logout":
			  return "Failed to Lo";
		  case "Payment":
			  return "Failed to navigate to Payment Page";
		  case "Homepage":
			  return "Failed to navigate to Payment Page";
		 
		}
		return "Unccessful Navigated";
		
	}
	
	
	//--------------Function for Click Element--------------
	public void ClicktoElement(WebElement element) {
		element.click();
	}
	
	
}
