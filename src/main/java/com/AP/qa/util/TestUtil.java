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
import org.testng.Assert;

import com.AP.qa.base.TestBase;
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
				
				
				//Function For Value Validation
				
		
}
