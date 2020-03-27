package com.AP.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

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

import com.AP.qa.base.TestBase;




public class Excel_Libraries extends TestBase {
	static String [][] Data;
	static String Excel_path = Report_Folder_path+"\\Excel.xls";
	public static XSSFWorkbook WB;
	public static XSSFSheet sh;
	public static XSSFCell cl;
	
	public static void create_file() throws IOException {
		Workbook wb = null;
		wb = new XSSFWorkbook();
		wb.createSheet("Sheet1").createRow(0).createCell(0);
		FileOutputStream fout = new FileOutputStream(new File(Excel_path));
		wb.write(fout);
		fout.close();
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

	
}
