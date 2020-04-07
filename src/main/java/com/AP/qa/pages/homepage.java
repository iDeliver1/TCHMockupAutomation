package com.AP.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import com.AP.qa.base.TestBase;


public class homepage extends TestBase{
	
	@FindBy(xpath = "//li[@class='ajax_block_product col-xs-12 col-sm-4 col-md-3 first-in-line first-item-of-tablet-line first-item-of-mobile-line hovered']//a[@class='product-name'][contains(text(),'Faded Short Sleeve T-shirts')]")
	WebElement Tshirt;
	
	@FindBy(xpath = "//ul[@id='homefeatured']//li[@class='ajax_block_product col-xs-12 col-sm-4 col-md-3 first-in-line first-item-of-tablet-line first-item-of-mobile-line']//div[@class='product-image-container']")
	WebElement Target;
	
	@FindBy(xpath = "//input[@id='quantity_wanted']") 
	WebElement qty;
	
	@FindBy(xpath = "//select[@id='group_1']")
	WebElement size;
	 
	@FindBy(xpath = "//span[contains(text(),'Add to cart')]")
	WebElement cart;

	@FindBy(xpath = "//span[contains(text(),'Proceed to checkout')]")
	WebElement checkout;
	
	@FindBy(xpath = "//div[@class='box-info-product']")
	WebElement frame;
	
	public homepage() {
		PageFactory.initElements(driver, this);
	}
	
	public void order_product() throws Throwable {
		try {
		Actions action = new Actions(driver);
		action.moveToElement(Target).perform();
		Tshirt.click();
		WaitForObject(qty, "Check");
		//wait.until(ExpectedConditions.elementToBeClickable(qty));
		qty.clear();
		qty.sendKeys(prop.getProperty("Qty"));
		Select psize = new Select(size);
		psize.selectByIndex(1);
		cart.click();
		WaitForObject(checkout, "Check");
		//wait.until(ExpectedConditions.elementToBeClickable(checkout));
		checkout.click();
		}
		catch(Exception e) {
			closeBrowser();
		}
		
	}
}
