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
import com.AP.qa.base.TestBase;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

public class TestUtil extends TestBase{
	
static ExtentTest logger;
static String [][] Data;
public static XSSFWorkbook WB;
public static XSSFSheet sh;
public static XSSFCell cl;

//---------------------------------------------Getting TestName---------------------------------------------------------
	public static ExtentTest CreateRoportname(String Step_details,ExtentReports extent1){

		logger = extent1.createTest(Step_details);
		return logger;
		
	}
	
	
//--------------------------------------------Reporting for Pass & Fail Event---------------------------------------------
	public static void Report(String Status1,String des,String actual, String expected/*,int sNO*/) throws Throwable{
		
		if(Status1.equalsIgnoreCase("PASS")){
			logger.pass(String.format("<b>Step No : </b>"+step+"</br><b> Description : </b>"+des+"<br /> <b>Actual Result :</b> "+actual+"<br /> <b>Expected Result :</b> "+expected),MediaEntityBuilder.createScreenCaptureFromPath(fScreenReport()).build());
			step++;
		}
		else{
			logger.fail(String.format(/*"Step No : "+sNO+*/" <b> Description :</b> "+des+"<br /> <b>Actual Result :</b> "+actual+"<br /> <b>Expected Result :</b> "+expected),MediaEntityBuilder.createScreenCaptureFromPath(fScreenReport()).build());
			closeBrowser();
		}
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
	    	String Image_path = System.getProperty("user.dir")+"\\Screenshot\\"+fTimestamp()+".png";
	    	//System.out.println(Image_path);
			File Desti_image = new File(Image_path);
			FileUtils.copyFile(source_image,Desti_image);
			return ""+Desti_image;
		}
	   
//------------------------------------------------Reading Data from Excel-------------------------------	   
	   public static Object[][] getExceldata() {
		  try {
		  FileInputStream fin = new FileInputStream("C:\\Users\\ideliver\\Desktop\\Selenium1\\APTest\\src\\main\\java\\com\\AP\\qa\\data\\testdata.xlsx");
			  //FileInputStream fin = new FileInputStream(System.getProperty("User.dir")+"\\src\\main\\java\\com\\AP\\qa\\data\\testdata.xlsx");
		  Workbook wb;	
		  wb = WorkbookFactory.create(fin);
		  Sheet sh = wb.getSheet("sheet1");
		  Row rw = sh.getRow(0);
		  Cell cl = rw.getCell(0);
		  int row = sh.getPhysicalNumberOfRows();
		  int col = rw.getPhysicalNumberOfCells();
		  
		   Data= new String[row][col];
		  
		  for(int i=0;i<row;i++)
		  {
			  for (int j=0; j<col; j++)
			  {
				  cl = sh.getRow(i).getCell(j);
				  //System.out.println(cl.getStringCellValue());
				  Data[i][j] = cl.getStringCellValue();
			  }
		  }
		  
		  
		  }catch(Exception e) {
			  System.out.println(e);
		  }
		  return (Data);
	   }

//----------------------------------------------Excel Reporting-------------------------------------------------------	   
	   public static void fExcelReporter(String Step_details,String Actual,String Expected,String Status,String Time) throws Throwable
		{ 
		   String[] Attribute = {"Step_details","Actual", "Expected","Status","Time"};
				try{
					FileInputStream fin = new FileInputStream(Excel_path); 
					WB = new XSSFWorkbook(fin);
					}
				catch(Exception e){
					System.out.println(e);
					}
			sh = WB.getSheet("Sheet1");
			//sh = WB.createSheet("Sheet1");	
			int Row_cnt = sh.getLastRowNum();
			XSSFRow newRow = sh.createRow(0);
			

			//Creating front color by cell style  	
			XSSFFont customFont = WB.createFont();
			customFont.setBold(true);
			CellStyle style = WB.createCellStyle();
			
			  	for(int j=0;j<=4;j++)
			  	{	
					newRow.createCell(j).setCellValue(Attribute[j]);
			  	}
			
			  
			String Attribute_value[] =  {Step_details,Actual,Expected,Status,Time};
			XSSFRow newRow1 = sh.createRow(Row_cnt+1);
				for(int i=0;i<=4;i++)
					{
					newRow1.createCell(i).setCellValue(Attribute_value[i]);
					if(Attribute_value[i].equalsIgnoreCase("Pass"))
						{
							customFont.setColor(IndexedColors.GREEN.getIndex());
							style.setFont(customFont);
							newRow1.getCell(i).setCellStyle(style);
						}
					else if(Attribute_value[i].equalsIgnoreCase("Fail"))
						{
							customFont.setColor(IndexedColors.RED.getIndex());
							style.setFont(customFont);
							newRow1.getCell(i).setCellStyle(style);
						}
					}	
			FileOutputStream fout = new FileOutputStream(Excel_path);
			WB.write(fout);
			fout.close();
		} 
	   
//------------------------------------------------Create excel File--------------------------------	   
}
