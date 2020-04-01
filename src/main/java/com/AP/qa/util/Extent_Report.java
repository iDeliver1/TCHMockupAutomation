package com.AP.qa.util;

import com.AP.qa.base.TestBase;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class Extent_Report extends TestBase {

	static ExtentTest logger,child_logger,parent_logger;
	  private static ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();
	  //private static ThreadLocal<ExtentReports>  extent1 =new ThreadLocal<ExtentReports>();
	  ExtentHtmlReporter htmlReporter;
	  public static ExtentReports extent;
	
	  static int Stepnumber=1,i=1,functioncall=1,j=1;
	 
	  
	 
	//Getting TestName
	  
	  public static ExtentReports createins() throws Throwable {
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
	       Excel_Libraries.create_file();
	       return extent;
	     
	}
	  
	  
	  
	  public static ExtentTest CreateRoportname(String Step_details){
		  logger = extent.createTest(Step_details);
		  functioncall=1;
		  j=1;
		 
			return logger;
			
	  }

	  public synchronized  ExtentTest getTest() {
	      return extentTest.get();
	  }
	  
	  public  void Report(String Status1,String Description,String ActualStep,String ExpectedStep) throws Throwable{

		 
		 String ReportStatus = "<b>Step Number "+functioncall+"<br>Description :</b> "+Description+"<br><b>Expected :</b> "+ExpectedStep+"<br><b>Actual :</b> "+ActualStep;
		  
		 //Excel Reporting
		 Excel_Libraries.fExcelReporter(Description, ActualStep, ExpectedStep, Status1, TestUtil.fGetCurrentDate());
			try{
			if(Status1.equalsIgnoreCase("PASS")){	
				logger.log(Status.PASS, ReportStatus);
				//logger.log(Status.PASS, ReportStatus, MediaEntityBuilder.createScreenCaptureFromPath(Gernric_functions.fScreenReport()).build());
				logger.addScreenCaptureFromPath(TestUtil.fScreenReport());
				
			}
			else{
				logger.log(Status.FAIL, ReportStatus);
				logger.addScreenCaptureFromPath(TestUtil.fScreenReport());
				closeBrowser();	
			}
			
			}catch(Exception e){
				System.out.println(e);
			}
			functioncall=functioncall+1;
		}
	  
		


	
	
	
}
