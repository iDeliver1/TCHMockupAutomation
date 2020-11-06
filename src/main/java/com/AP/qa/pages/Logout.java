package com.AP.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.AP.qa.base.TestBase;

public class Logout extends TestBase{
	@FindBy(xpath = "//a[@class='logout']")
	public static WebElement signOut;
	
	@FindBy(xpath = "//div[@class='header_user_info']" )
	public static WebElement signIn;
	
	public Logout() {
		PageFactory.initElements(driver, this);
	}
	
	public String LogoutTest() throws Throwable {
		signOut.click();
        return signIn.getText();
	}
	
	
}
