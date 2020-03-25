package com.AP.qa.test;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.AP.qa.base.TestBase;
import com.AP.qa.pages.Login;
import com.AP.qa.pages.Logout;
import com.AP.qa.pages.Payment;
import com.AP.qa.pages.homepage;
import com.AP.qa.util.DBconnect;

public class APTest extends TestBase {
	
	Login login;
	homepage home;
	Payment pay;
	Logout logout;
	DBconnect db;

	@Parameters("Browser")
	@BeforeTest
	public void setUp(String Browser) throws Throwable{
		initialization(Browser);
		getReportname("Automation_Practice");
		login = new Login();
		home = new homepage();
		pay = new Payment();
		logout = new Logout();
		db = new DBconnect();
	}
	
	/*@DataProvider(name = "Run")
	public Object[][] testdata() throws Throwable{
		Object[][] data = TestUtil.getExceldata();
		return data;
		//return new Object[][]{{"iDeliver","iDeliver1"}};
	}*/
	
	@Test//(dataProvider = "Run")
	void LoginTest() throws Throwable{
		//getReportname(new Object(){}.getClass().getEnclosingMethod().getName());
		//login.loginAP(prop.getProperty("username"), prop.getProperty("password"));
		//login.loginAP(usr, pass);
		String usr = db.getdbdata("user");
		String pass = db.getdbdata("pass");
		login.loginAP(usr, pass);
	}
	
	
	@Test(priority = 2, enabled = true, dependsOnMethods = "LoginTest")
	void BookingTest() throws Throwable{
		//getReportname(new Object(){}.getClass().getEnclosingMethod().getName());
		home.order_product();
	}
	

	@Test(priority = 3, enabled = true, dependsOnMethods = "BookingTest")
	void PaymentTest() throws Throwable{
		//getReportname(new Object(){}.getClass().getEnclosingMethod().getName());
		pay.paymentpage();
	}
	
	@AfterTest
	public void Flush() throws Throwable
	{
		logout.LogoutTest();
		db.closedb();
		closeBrowser();
	}
	
}
