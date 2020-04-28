package com.AP.qa.pages;



import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.AP.qa.base.TestBase;
import com.AP.qa.util.TestUtil;


public class Login extends TestBase{
	
	@FindBy(xpath = "//a[@class='login']")
	public static WebElement signInbtn;
	
	@FindBy(xpath = "//input[@id='email']")
	public static WebElement user;
	
	@FindBy(xpath = "//input[@id='passwd']")
	public static WebElement password;
	
	@FindBy(xpath = "//button[@id='SubmitLogin']")
	public static WebElement signIn;
	
	@FindBy(xpath = "//img[@class='logo img-responsive']")
	public static WebElement home;
	
	@FindBy(xpath = "//span[contains(text(),'sunil jaiswal')]")
	public static WebElement validate_user;
	
	
	public Login() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean validateSWNLogo(){
		return validate_user.isDisplayed();
	}
	
	public WebElement Login_Before_checkout(String usr, String pass) throws Throwable {
		signInbtn.click();
		user.sendKeys(usr);
		password.sendKeys(pass);
		signIn.click();
		home.click();
		return validate_user;
		
	}
	
	
	
	public static WebElement Login_After_checkout(String usr, String pass) throws Throwable {
		//signInbtn.click();
		try{
		user.sendKeys(usr);
		password.sendKeys(pass);
		signIn.click();
		return validate_user;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return null;

	}
	
	
		
	}


