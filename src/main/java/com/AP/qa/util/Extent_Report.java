package com.AP.qa.util;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.AP.qa.base.TestBase;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class Extent_Report {
	 static ExtentTest logger,child_logger,parent_logger;
	  private static ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();
	  private static ThreadLocal<ExtentReports>  extent1 =new ThreadLocal<ExtentReports>();
	  ExtentHtmlReporter htmlReporter;
	  static ExtentReports extent;
	  static String Report_Folder_path = "C:\\Reporting\\Report"+TestUtil.fTimestamp();
	  static int Stepnumber=1,i=1,functioncall=1,j=1;
	  
	  
	    static   {
		  ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(Report_Folder_path + "\\testReport.html");
	    extent = new ExtentReports();
	    extent.attachReporter(htmlReporter);
	    extent.setSystemInfo("OS", "OS");
	    extent.setSystemInfo("Browser", "browser");
	    //htmlReporter.config().setChartVisibilityOnOpen(true);
	    htmlReporter.config().setDocumentTitle("Extent Report Demo");
	    htmlReporter.config().setReportName("Test Report");
	    //htmlReporter.config().setTestViewChartLocation(CharacterSection.TOP);
	    htmlReporter.config().setTheme(Theme.STANDARD);
	    extent1.set(extent);
	  
	}


	  public synchronized ExtentReports getInstance() {	
		  	return extent1.get();
	  }


	//Getting TestName
	  public  ExtentTest CreateRoportname(String Step_details){
		  logger =getInstance().createTest(Step_details);
		  functioncall=1;
		  j=1;
		  extentTest.set(logger);
			return extentTest.get();
			
	  }
	  
	  
	

	  public synchronized  ExtentTest getTest() {
	      return extentTest.get();
	  }
	  
	  public  void Report(String Status1,String Description,String ActualStep,String ExpectedStep) throws Throwable{
			
		  
		  
		  String ReportStatus = "<b>Step Number "+functioncall+"<br>Description :</b> "+Description+"<br><b>Expected :</b> "+ExpectedStep+"<br><b>Actual :</b> "+ActualStep;
			System.out.println(getTest());
			try{
			if(Status1.equalsIgnoreCase("PASS")){	
				extentTest.get().log(Status.PASS, ReportStatus);
				//logger.log(Status.PASS, ReportStatus, MediaEntityBuilder.createScreenCaptureFromPath(Gernric_functions.fScreenReport()).build());
				extentTest.get().addScreenCaptureFromPath(TestUtil.fScreenReport());
				flush();
			}
			else{
				
				extentTest.get().log(Status.FAIL, ReportStatus);
				extentTest.get().addScreenCaptureFromPath(TestUtil.fScreenReport());
				TestBase.closeBrowser();
				//logger.log(Status.FAIL, ReportStatus, MediaEntityBuilder.createScreenCaptureFromPath(Gernric_functions.fScreenReport()).build());
				
			}
			
			}catch(Exception e){
				System.out.println(e);
			}
			functioncall=functioncall+1;
			
		}
	  
		//Validation for actual and expected 
		public  void validation(String StepName,String Actual,String Expected) throws Throwable{
			System.out.println(extent1.get());
			System.out.println(extentTest.get());
			try{
				Assert.assertEquals(Actual, Expected);
				Report("PASS",StepName,"Page launch Successfull "+ Actual ,"Should be able to launch "+Expected);
				}catch(Exception e){ 
					Report("FAIL",StepName,"Page launch unsuccessfull "+ Actual ,"Should be able to launch "+Expected);
				}
		
		}
		
	public  void Argvalidation(String StepName,String Actual,String Expected) throws Throwable{
		System.out.println(extent1.get());
		System.out.println(extentTest.get());
			try{
				Assert.assertEquals(Actual, Expected);
				Report("PASS","Verifying "+StepName,StepName+" is equal to "+Actual,StepName+"should be equal to "+Expected);
				
				//log(StepName+" Validation     "+Actual + " is equal to " +Expected);
				
				}catch(Exception e){ 
				//	log(StepName+"  Validation    "+ Actual + " is not  equal to " +Expected+" because "+e);
					
					Report("FAIL","Verifying "+StepName,StepName+"is equal to "+Actual+""+e,StepName+"should be equal to "+Expected);
				}
		
		}

	public  void Menuvalidation(String StepName,WebElement element) throws Throwable{
		
		try{
			
			 Assert.assertEquals(true, element.isDisplayed());
			Report("PASS","Verifying "+ StepName,StepName+" is Visible ",StepName+" Must be visible");
			//log(StepName + " is Visible ");
			}catch(Exception e){ 
				String cause = e.toString();
				//log(StepName+" is not Visible ");
				Report("FAIL","Verifying "+StepName, StepName+" is not visible because "+cause.substring(1, 88) ,StepName+" Must be visible");
				
			}
	}

	public  String getMultiProductValue(List<WebElement> element,WebElement prtx) {
		   float value = 0;
		 
		   System.out.println(extent1.get());
			System.out.println(extentTest.get());
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
	
	
	public void flush(){
		getInstance().flush();
	}
	
	
}
