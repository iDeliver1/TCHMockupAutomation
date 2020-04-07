package com.AP.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.AP.qa.base.TestBase;

public class Reorder extends TestBase{
	@FindBy(xpath = "//span[contains(text(),'Order history and details')]")
	WebElement orderDetails;	

	@FindBy(xpath = "//tr[contains(@class,'first_item')]")
	WebElement orderTable;
		
	@FindBy(xpath = "//tr[contains(@class,'first_item')]//a[2]")
	WebElement selectFirstOrder;
	
	@FindBy(xpath = "//span[contains(text(),'sunil jaiswal')]")
	WebElement profile;
	
	public Reorder() {
		PageFactory.initElements(driver, this);
	}
	
	public void ReorderMethod() throws InterruptedException {
		try {
		WaitForObject(profile, "Click");		
		orderDetails.click();
		selectFirstOrder.click();
		}catch(Exception e) {
			closeBrowser();
		}
		
	}

}
