package com.AP.qa.util;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.AP.qa.base.TestBase;



public class Genral_Function extends TestBase {



	//-------------Function For Login Page Validation----------------------
	public static boolean LoginValidation(WebElement element) throws Throwable {
		try {
			Assert.assertEquals(true, element.isDisplayed());
			Reporting("Pass", "Login Validation", "User Should be able to log in with username - "+prop.getProperty("username")+" & password - "+prop.getProperty("password") , "Successfull logged in withusername - "+prop.getProperty("username")+" & password - "+prop.getProperty("password"), "");
			return true;
		}catch(Exception e) {
			Reporting("Pass", "Login Validation", "User Should be able to log in with username - "+prop.getProperty("username")+" & password - "+prop.getProperty("password") , "Un-Successfull logged in withusername - "+prop.getProperty("username")+" & password - "+prop.getProperty("password"), "");
			closeBrowser();
		}
		return false;
	}
			
	
	
	//----------------Function for Logout Validation--------------------
	public static void logoutvalidation(String signin) throws Throwable {
		try {
					Assert.assertEquals(true, signin.contains("Sign in"));
					Reporting("Pass","Logout Validation","Logout successfull with the user -"+prop.getProperty("username"),"User should Logout from Store", "LAST");
					log("Logout successfull");
				
				}catch(Exception e)
				{
					System.out.println(e);
					Reporting("Fail","Logout Validation","Logout Failed","User should Logout from Store", "LAST");
					log("Logout failed");
					
				}
	}
	

	//---------------------Function for Adding Values------------------
	public static   String getMultiProductValue(List<WebElement> element,WebElement prtx) {
		   float value = 0;
		 
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
	
	

	//------------------Function for Argument Validation-------------------------
	public static Boolean Argvalidation(String StepName,String Actual,String Expected) throws Throwable{
		try{
			Assert.assertEquals(Actual, Expected);
			Reporting("Pass","Verifying "+StepName,StepName+" is equal to "+Actual,StepName+"should be equal to "+Expected, "");
			
			log(StepName+" Validation     "+Actual + " is equal to " +Expected);
			
			
			return true;
			
			}catch(Exception e){ 
			log(StepName+"  Validation    "+ Actual + " is not  equal to " +Expected+" because "+e);
				
				Reporting("Fail","Verifying "+StepName,StepName+"is equal to "+Actual+""+e,StepName+"should be equal to "+Expected, "");
				closeBrowser();
			}
		return false;
	
	}

	
	
	
	
}
