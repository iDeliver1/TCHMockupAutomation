package com.AP.qa.pages;



import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.AP.qa.base.TestBase;
import com.AP.qa.util.TestUtil;

public class Login extends TestBase{
	
	@FindBy(xpath = "//a[@class='login']")
	WebElement signInbtn;
	
	@FindBy(xpath = "//input[@id='email']")
	WebElement user;
	
	@FindBy(xpath = "//input[@id='passwd']")
	WebElement password;
	
	@FindBy(xpath = "//button[@id='SubmitLogin']")
	WebElement signIn;
	
	@FindBy(xpath = "//img[@class='logo img-responsive']")
	WebElement home;
	
	@FindBy(xpath = "//span[contains(text(),'sunil jaiswal')]")
	WebElement validate_user;
	
	public Login() {
		PageFactory.initElements(driver, this);
	}
	
	public void loginAP(String usr, String pass) throws Throwable {
		signInbtn.click();
		user.sendKeys(usr);
		password.sendKeys(pass);
		signIn.click();
		LoginValidation();
		home.click();
	}
	
	public void LoginValidation() throws Throwable {
		try{
			Assert.assertTrue(validate_user.isDisplayed());
			TestUtil.Report("Pass","Login Validation","Login successfull with the user -"+prop.getProperty("username"),"User should be able to Login");
			log("Login successfull with the user -"+prop.getProperty("username"));
			TestUtil.fExcelReporter("Login Validation", "Login successfull", "User should be able to Login", "Pass", getTime());
		}catch(Exception e)
		{
			System.out.println(e);
			TestUtil.Report("Fail","Login Validation","Login Failed","User should be able to Login");
			log("login failed");
			TestUtil.fExcelReporter("Login Validation", "Login successfull", "User should be able to Login", "Fail", getTime());
		}
		
	}

}
