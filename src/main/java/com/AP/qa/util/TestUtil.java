package com.AP.qa.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.AP.qa.base.TestBase;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

public class TestUtil extends TestBase{
	
static ExtentTest logger;

public static XSSFWorkbook WB;
public static XSSFSheet sh;
public static XSSFCell cl;
static Extent_Report objRep = new Extent_Report();
public static long PAGE_LOAD_TIMEOUT = 20;
public static long IMPLICIT_WAIT = 20;
static String Report_Folder_path = "C:\\Reporting\\Report"+fTimestamp();

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
		
		
		//Validation for Page Launch
				public static void validation(String StepName,String Actual,String Expected) throws Throwable{
					
					try{
						Assert.assertEquals(true, Actual.contains(Expected));
						
						objRep.Report("PASS",StepName,"Page launch Successfull "+ Actual ,"Should be able to launch "+Expected);
						}catch(Exception e){ 
							String cause = e.toString();
							objRep.	Report("FAIL",StepName,"Page launch unsuccessfull "+ Actual+" Because of "+cause.substring(1, 88) ,"Should be able to launch "+Expected);
						}
				}
				
				//Function For Value Validation
				
			public  void Argvalidation(String StepName,String Actual,String Expected) throws Throwable{
					
					try{
						Assert.assertEquals(Actual, Expected);
						objRep.Report("PASS","Verifying "+StepName,StepName+" is equal to "+Actual,StepName+"should be equal to "+Expected);
						
						log(StepName+" Validation     "+Actual + " is equal to " +Expected);
						
						}catch(Exception e){ 
						log(StepName+"  Validation    "+ Actual + " is not  equal to " +Expected+" because "+e);
							
						objRep.Report("FAIL","Verifying "+StepName,StepName+"is equal to "+Actual+""+e,StepName+"should be equal to "+Expected);
						}
				
				}

			//Function for is Object is visble or not
			public  void Menuvalidation(String StepName,WebElement element) throws Throwable{
				
				try{
					 Assert.assertEquals(true, element.isDisplayed());
					 objRep.Report("PASS","Verifying "+ StepName,StepName+" is Visible ",StepName+" Must be visible");
					log(StepName + " is Visible ");
					}catch(Exception e){ 
						String cause = e.toString();
						log(StepName+" is not Visible ");
						objRep	.Report("FAIL","Verifying "+StepName, StepName+" is not visible because "+cause.substring(1, 88) ,StepName+" Must be visible");
						
					}
			}
		
   
}
