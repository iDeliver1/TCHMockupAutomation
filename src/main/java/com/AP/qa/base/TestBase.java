package com.AP.qa.base;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.AP.qa.util.TestUtil;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class TestBase {
	public static final Logger log = Logger.getLogger(TestBase.class.getName());
	public static WebDriver driver;
	public static Properties prop;
	ExtentHtmlReporter htmlReporter;
	static ExtentReports extent;
	public static WebDriverWait wait;
	public static int step =1;
	public static String Excel_path = "C:\\Users\\ideliver\\Desktop\\Selenium1\\APTest\\test-Reports\\ExcelReport.xlsx";
	
	public TestBase(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("C:\\Users\\ideliver\\Desktop\\Selenium1\\APTest\\src\\main\\java\\com\\AP\\qa\\config\\config.properties");
			//FileInputStream ip = new FileInputStream(System.getProperty("User.dir")+"\\src\\main\\java\\com\\AP\\qa\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//the Extent report will be created only once, no matter wherever we do initialization
	static {
			 ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("C:\\Users\\ideliver\\Desktop\\Selenium1\\APTest\\test-Reports\\htmlReport.html");
			 extent = new ExtentReports();
			 extent.attachReporter(htmlReporter);
			 extent.setSystemInfo("OS", "OS");
			 extent.setSystemInfo("Browser", "browser");
			 htmlReporter.config().setDocumentTitle("Automation Practice Report");
			 htmlReporter.config().setReportName("Test Report");
			 htmlReporter.config().setTheme(Theme.STANDARD);
	}
	
	public static void initialization(String Browser) throws Throwable{
		if(Browser.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			driver = new ChromeDriver();
		}
		else {
			driver = new FirefoxDriver();
		}
		wait = new WebDriverWait(driver, 20);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.navigate().to(prop.getProperty("url"));	
		create_file();
}
	
	//create excel file
	public static void create_file() throws IOException {
		Workbook wb = null;
		wb = new XSSFWorkbook();
		wb.createSheet("Sheet1").createRow(0).createCell(0);
		FileOutputStream fout = new FileOutputStream(new File(Excel_path));
		wb.write(fout);
		fout.close();
	}
	
	//Time function
	public String getTime()
	{
	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	Date date = new Date();
	String date1= dateFormat.format(date);  // Now format the date
	return date1;
	}

	//Logging method so that the same log is added in logger as well as syso
			public void log(String data) {
				
				log.info(data);
				//Reporter.log(data);
			}
		
		
		//Getting TestName
		public void getReportname(String Reportname){
			TestUtil.CreateRoportname(Reportname,extent);
		}
		
		//Creating Report 
		public void Reporting(String Status,String StepName) throws Throwable{
			//TestUtil.Report(Status, StepName);
			
		}
		
		
		//Closing Browser And Saving Report 
		public static void closeBrowser() {
			
			driver.close();
			extent.flush();
			driver.quit();
		}

}
