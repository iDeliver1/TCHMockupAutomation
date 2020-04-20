package com.AP.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.AP.qa.base.TestBase;




public class Excel_Libraries extends TestBase {
	static String [][] Data;
	static String Excel_path = TestUtil.Report_Folder_path+"\\Excel.xls";
	public static XSSFWorkbook WB;
	static boolean abc ;
	static String Reportn;
	static int Sheetindex=0;
	
	 public static String createExcel(String Reportname) throws InvalidFormatException, IOException{
			WB = new XSSFWorkbook();
		if ((new File(Excel_path)).exists()==false) {	
			
			Reportn = Reportname;
			String filename = System.getProperty("user.dir")+"/src/test/java/com/AP/qa/test" ;
		
			File file = new File(filename);
		    String[] fileList = file.list();
		    
		    try {
		    for(String name:fileList){
		    	System.out.println(name.replace(".java", ""));
		    	//WB.createSheet(name.replace(".java", "")).createRow(0).createCell(0);
		    }
		    }catch(Exception f) {
		    	System.out.println(f);
		    
		    }
		    
		    WB.createSheet(Reportname).createRow(0).createCell(0);
			
			FileOutputStream fout;
			try {
				fout = new FileOutputStream(new File(Excel_path));
				WB.write(fout);
				fout.close();
			} catch (FileNotFoundException e) {
			
				e.printStackTrace();
			} catch (IOException e) {
			
				e.printStackTrace();
			}
		
			
		}
		
		else {
			
			try{
				 FileInputStream fin = new FileInputStream(Excel_path);
				 WB = new XSSFWorkbook();
				 WB =  (XSSFWorkbook) WorkbookFactory.create(fin);
				}
			catch(Exception e){
				System.out.println(e);
				}
			
			 WB.createSheet(Reportname).createRow(0).createCell(0);
			 
			 FileOutputStream fout = new FileOutputStream(Excel_path);
				WB.write(fout);
				fout.close();
			
			Reportn = Reportname;
		}
		
		
		
		Sheetindex= Sheetindex+1;
		return Reportn;
		
	}
	
	//----------------------------------------------Excel Reporting-------------------------------------------------------	   
	   public static void fExcelReporter(String Step_details,String Actual,String Expected,String Status,String Time) throws Throwable
		{ 
		   
		   String[] Attribute = {"Step_details","Actual", "Expected","Status","Date"};
				try{
					 FileInputStream fin = new FileInputStream(Excel_path);
					 WB = new XSSFWorkbook();
					 WB =  (XSSFWorkbook) WorkbookFactory.create(fin);
					}
				catch(Exception e){
					System.out.println(e);
					}
				 
		int	shindex = WB.getActiveSheetIndex();
		Sheet sh = WB.getSheetAt(shindex+Sheetindex-1);
		sh.setDefaultColumnWidth(25);
		
		try {
			int Row_cnt = sh.getLastRowNum();
			
			Row newRow = sh.createRow(0);
		
		
			//Creating front color by cell style  	
			XSSFFont customFont = WB.createFont();
			CellStyle style = WB.createCellStyle();
		
			  	for(int j=0;j<=4;j++)
			  	{	
			  		newRow.createCell(j).setCellValue(Attribute[j]);
			  		
			  	}
			
			  
			String Attribute_value[] =  {Step_details,Actual,Expected,Status,Time};
		Row	 newRow1 = sh.createRow(Row_cnt+1);
			
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
		catch(Exception e) {
			System.out.println(e);
		}
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

	//------------------------------------------Get Data from Excel-----------------------------------------------------
	   public static String getdata(int index) {
		   try {
		   FileInputStream fin = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/AP/"+ "qa/data/testdata.xlsx");
		   //FileInputStream fin = new FileInputStream(System.getProperty("User.dir")+"\\src\\main\\java\\com\\AP\\qa\\data\\testdata.xlsx");
		   Workbook wb;
		   wb = WorkbookFactory.create(fin);
		   Sheet sh = wb.getSheet("sheet1");
		   //Sheet sh = wb.getSheet(sheet);
		   Row rw = sh.getRow(0);
		   Cell cl = rw.getCell(0);

		    cl = sh.getRow(0).getCell(index);
		   System.out.println(cl.getStringCellValue());

		   return cl.getStringCellValue();
		   
		   
		   }catch(Exception e) {
		   System.out.println(e);
		   }
		   return null;
		    }
	   
	   //Checking Sheet has value or not
	   public static boolean  cellempty(XSSFWorkbook WB,String Rep) throws InvalidFormatException, IOException {
		   FileInputStream fin = new FileInputStream(Excel_path);
		   WB = (XSSFWorkbook) WorkbookFactory.create(fin);
			  Sheet sh = WB.getSheet(Rep);
			  Row rw = sh.getRow(0);
			  Cell cell = rw.getCell(0);
			  
			  if (cell != null) { // use row.getCell(x, Row.CREATE_NULL_AS_BLANK) to avoid null cells
			        return true;
			    }

			    if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
			        return true;
			    }

			    if (cell.getCellType()!= Cell.CELL_TYPE_STRING && cell.getStringCellValue().trim().isEmpty()) {
			        return true;
			    }
		   
		return false;
		   
	   }
	   
	   public static String fRead(String Attri_name,String Sheet_name) throws Throwable
	    {
	FileInputStream fin=new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/AP/"+ "qa/data/testdata.xlsx");
	WB=(XSSFWorkbook) WorkbookFactory.create(fin);
	Sheet sh = WB.getSheet(Sheet_name);
	int Row_cnt = sh.getLastRowNum();
	  for(int i=0;i<=Row_cnt;i++)  
	  {
	  Row rw_obj = sh.getRow(i);
	  Cell cell_obj = rw_obj.getCell(0);
	  String Attri = cell_obj.toString();
	     if(Attri.equals(Attri_name))
	     {
	  Cell Attri_valu= rw_obj.getCell(1);
	  return Attri_valu.toString();
	     }
	  }
	return null;  
	}

	   
}
