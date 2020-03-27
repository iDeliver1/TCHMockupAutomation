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
			Assert.assertEquals(true, sign.contains("Sign in"));
			Reporting("Pass","Logout Validation","Logout successfull with the user -"+prop.getProperty("username"),"User should Logout from Store");
			log("Logout successfull");
		
		}catch(Exception e)
		{
			System.out.println(e);
			Reporting("Fail","Logout Validation","Logout Failed","User should Logout from Store");
			log("Logout failed");
			
		}
	}

}
