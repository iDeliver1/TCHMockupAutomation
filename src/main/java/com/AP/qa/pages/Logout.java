package com.AP.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.AP.qa.base.TestBase;
import com.AP.qa.util.TestUtil;

public class Logout extends TestBase{
	@FindBy(xpath = "//a[@class='logout']")
	WebElement signOut;
	
	@FindBy(xpath = "//div[@class='header_user_info']" )
	WebElement signIn;
	
	public Logout() {
		PageFactory.initElements(driver, this);
	}
	
	public void LogoutTest() throws Throwable {
		signOut.click();
		Logout_validation();
	}
	
	public void Logout_validation() throws Throwable {
		
		try {
			String sign = signIn.getText();
			System.out.println(sign);
			//Assert.assertEquals(sign, "\r\n" + 
			//		"			Sign in\r\n" + 
			//		"		");
			Assert.assertEquals(true, sign.contains("Sign in"));
			//TestUtil.Report("Pass","Logout successfull");
			TestUtil.Report("Pass","Logout Validation","Logout successfull with the user -"+prop.getProperty("username"),"User should Logout from Store");
			log("Logout successfull");
			TestUtil.fExcelReporter("Logout Validation", "Logout successfull", "User should Logout from Store", "Pass", getTime());
		}catch(Exception e)
		{
			System.out.println(e);
			//TestUtil.Report("Fail","Logout failed");
			TestUtil.Report("Fail","Logout Validation","Logout Failed","User should Logout from Store");
			log("Logout failed");
			TestUtil.fExcelReporter("Logout Validation", "Logout successfull", "User should Logout from Store", "Fail", getTime());
		}
	}

}
