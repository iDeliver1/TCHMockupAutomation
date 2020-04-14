package com.AP.qa.pages;



import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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
	
	TestUtil objTe = new TestUtil();
	public Login() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean validateSWNLogo(){
		return validate_user.isDisplayed();
	}
	
	public void Login_Before_checkout(String usr, String pass) throws Throwable {
		try {
		signInbtn.click();
		loginAP(usr,pass);
		home.click();
		}
		catch(Exception e) {
			String Cause = e.toString();
			Reporting("Fail", "Login Page Validation", "Login Page should displayed ", "Login Page is unable to show due to"+Cause.substring(1, 88));
			closeBrowser();	
		}
	}
	
	
	public void loginAP(String usr, String pass) throws Throwable {
		//signInbtn.click();
		user.sendKeys(usr);
		password.sendKeys(pass);
		signIn.click();
		LoginValidation(usr,pass);
		//home.click();
	}
	
	public void LoginValidation(String usr,String pwd) throws Throwable {
		try {
			if(validateSWNLogo()){
	
				log("Successful logging with username -  "+usr);
				Reporting("Pass"," Enter Username & Password ", "Entered Username "+usr+" & password "+pwd, "User Should be able to enter Username "+usr+" & password "+pwd);
				

				}
			}
			
			catch(Exception e)
			{
				Reporting("FAIL"," Enter Username & Password ", "Failed with enter Username"+usr+ " & password "+pwd, "User Should be able to login with Username"+usr+" & password"+pwd);
				//return new LoginPage();
				closeBrowser();
				}
			}
		
	}


