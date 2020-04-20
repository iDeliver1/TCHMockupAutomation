package com.AP.qa.util;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.AP.qa.base.TestBase;
import com.AP.qa.pages.Login;
import com.AP.qa.pages.Logout;
import com.AP.qa.pages.Multi_Product_Parameter;
import com.AP.qa.pages.Payment;
import com.AP.qa.pages.homepage;
import com.aventstack.extentreports.ExtentTest;

public class TestUtil extends TestBase{
	
static ExtentTest logger;

public static XSSFWorkbook WB;
public static XSSFSheet sh;
public static XSSFCell cl;
static Extent_Report objRep = new Extent_Report();
public static long PAGE_LOAD_TIMEOUT = 40;
public static long IMPLICIT_WAIT = 40;
public static String Report_Folder_path = "C:\\Reporting\\Report"+fTimestamp();
static Login login = new Login();
Payment Pay = new Payment();

public static String fGetCurrentDate()
{
	Date date = new Date();  
    SimpleDateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");  
    String strDate = dateformat.format(date); 
    return strDate;
}

	
//-------------------------------------------TimeStamp Function----------------------------------	
	public static String fTimestamp()
		{
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy-hh-mm-ss");
			String time = dateFormat.format(now);
			return time.replace("-", "");
		}
		 
//-----------------------------------------------Screenshot Function-------------------------------	
	   public static String fScreenReport() throws Throwable
		{
	    	File source_image = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    	String Image_path = Report_Folder_path+"/screenshots/" + System.currentTimeMillis() + ".png";
	    	//System.out.println(Image_path);
			File Desti_image = new File(Image_path);
			FileUtils.copyFile(source_image,Desti_image);
			return ""+Desti_image;
		}
	   
		public  void takeScreenshotAtEndOfTest() throws Throwable  {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String currentDir = System.getProperty("user.dir");
			FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
		}
		
		
		//Validation for Login
		public static homepage LoginValidation(WebElement element) throws Throwable {
			try {
				Assert.assertEquals(true, element.isDisplayed());
				Reporting("Pass", "Login Validation", "User Should be able to log in with username - "+prop.getProperty("username")+" & password - "+prop.getProperty("password") , "Successfull logged in withusername - "+prop.getProperty("username")+" & password - "+prop.getProperty("password"));
				return new homepage();
			}catch(Exception e) {
				Reporting("Pass", "Login Validation", "User Should be able to log in with username - "+prop.getProperty("username")+" & password - "+prop.getProperty("password") , "Un-Successfull logged in withusername - "+prop.getProperty("username")+" & password - "+prop.getProperty("password"));
				closeBrowser();
			}
			return null;
		}
				
		
		
		public static Payment AfterLoginValidation(WebElement element) throws Throwable {
			try {
				Assert.assertEquals(true, element.isDisplayed());
				Reporting("Pass", "Login Validation", "User Should be able to log in with username - "+prop.getProperty("username")+" & password - "+prop.getProperty("password") , "Successfull logged in withusername - "+prop.getProperty("username")+" & password - "+prop.getProperty("password"));
				return new Payment();
			}catch(Exception e) {
				Reporting("Pass", "Login Validation", "User Should be able to log in with username - "+prop.getProperty("username")+" & password - "+prop.getProperty("password") , "Un-Successfull logged in withusername - "+prop.getProperty("username")+" & password - "+prop.getProperty("password"));
				closeBrowser();
			}
			return null;
		}
		
		
		public static Multi_Product_Parameter LoginValidation1(WebElement element) throws Throwable {
			try {
				Assert.assertEquals(true, element.isDisplayed());
				Reporting("Pass", "Login Validation", "User Should be able to log in with username - "+prop.getProperty("username")+" & password - "+prop.getProperty("password") , "Successfull logged in withusername - "+prop.getProperty("username")+" & password - "+prop.getProperty("password"));
				return new Multi_Product_Parameter();
			}catch(Exception e) {
				Reporting("Pass", "Login Validation", "User Should be able to log in with username - "+prop.getProperty("username")+" & password - "+prop.getProperty("password") , "Un-Successfull logged in withusername - "+prop.getProperty("username")+" & password - "+prop.getProperty("password"));
				closeBrowser();
			}
			return null;
		}
		 
		public static void logoutvalidation(String signin) throws Throwable {
					
			
			try {
						Assert.assertEquals(true, signin.contains("Sign in"));
						Reporting("Pass","Logout Validation","Logout successfull with the user -"+prop.getProperty("username"),"User should Logout from Store");
						log("Logout successfull");
					
					}catch(Exception e)
					{
						System.out.println(e);
						Reporting("Fail","Logout Validation","Logout Failed","User should Logout from Store");
						log("Logout failed");
						
					}
		}
		
		
		public static String CheckoutPriceValidation() {
		
			String value =  getMultiProductValue();
			return value;
		}
		
		public static   String getMultiProductValue() {
			   float value = 0;
			   List<WebElement> element = Payment.Price;
			   WebElement prtx = Payment.tax;
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
		
		
		public static Payment Argvalidationforlogin(String StepName,String Actual,String Expected) throws Throwable{
			 
				try{
					Assert.assertEquals(Actual, Expected);
					Reporting("PASS","Verifying "+StepName,StepName+" is equal to "+Actual,StepName+"should be equal to "+Expected);
					
					log(StepName+" Validation     "+Actual + " is equal to " +Expected);
					Payment.proceed.click();
					
					try {
						if(driver.getTitle().contains("Login - My Store")) {
							login = new Login();
							WebElement element = 		login.Login_After_checkout(prop.getProperty("username"), prop.getProperty("password"));
							return AfterLoginValidation( element);
							}
					}catch(Exception e)
					{
						System.out.println("Already sign in");
					}
					
					}catch(Exception e){ 
					log(StepName+"  Validation    "+ Actual + " is not  equal to " +Expected+" because "+e);
						
						Reporting("FAIL","Verifying "+StepName,StepName+"is equal to "+Actual+""+e,StepName+"should be equal to "+Expected);
					}
				return null;
			
			}

		public static Payment Argvalidation(String StepName,String Actual,String Expected) throws Throwable{
			 Expected = Payment.TotalPrice.getText().replace("$", "");
			try{
				Assert.assertEquals(Actual, Expected);
				Reporting("PASS","Verifying "+StepName,StepName+" is equal to "+Actual,StepName+"should be equal to "+Expected);
				
				log(StepName+" Validation     "+Actual + " is equal to " +Expected);
				Payment.proceed.click();
				
				return new Payment();
				
				
				}catch(Exception e){ 
				log(StepName+"  Validation    "+ Actual + " is not  equal to " +Expected+" because "+e);
					
					Reporting("FAIL","Verifying "+StepName,StepName+"is equal to "+Actual+""+e,StepName+"should be equal to "+Expected);
				}
			return null;
		
		}

		
		public static  Logout price_validation(String amt2,String amt) throws Throwable {
			
			try{
				
				Assert.assertEquals(amt, amt2);
				Reporting("Pass","Final Price Validation","Actual Price -"+amt2,"Expected Price -"+amt);
				log("Successfully validated the order amount -"+amt2);
				
				
				return new Logout();
				
			}catch(Exception e)
			{
				
				System.out.println(e);
				Reporting("Fail","Final Price Validation","Actual Price -"+amt2,"Expected Price -"+amt);
				log("Price validation failed");
				
			}
			return null;
		}
		
		//Function for move able object 
		
		public static void MoveElement(WebElement element) {
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
		}
		
		public static void SelectQuantity(List<WebElement> element,String size) {
			System.out.println(element.size());
			for(int i=0;i<element.size();i++) {
				if(element.get(i).getAttribute("Title").contains(size)) {
					element.get(i).click();
					break;
				}
			}
		}
}
