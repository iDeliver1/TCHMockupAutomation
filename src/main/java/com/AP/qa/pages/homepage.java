package com.AP.qa.pages;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.AP.qa.base.TestBase;
import com.AP.qa.util.TestUtil;



public class homepage extends TestBase{
	
	@FindBy(xpath = "//a[@class='button btn btn-default standard-checkout button-medium']//span[contains(text(),'Proceed to checkout')]")
	public static  WebElement proceed;
	
	@FindBy(xpath = "//span[contains(text(),'Order history and details')]")public static
	  WebElement orderDetails;	

	@FindBy(xpath = "//tr[contains(@class,'first_item')]")public
	  WebElement orderTable;
		
	@FindBy(xpath = "//tr[contains(@class,'first_item')]//a[2]")public static
	  WebElement selectFirstOrder;
	
	@FindBy(xpath = "//span[contains(text(),'tester demo')]")public static
	  WebElement profile;
	
	@FindBy(xpath = "//li[@class='ajax_block_product col-xs-12 col-sm-4 col-md-3 first-in-line first-item-of-tablet-line first-item-of-mobile-line hovered']//a[@class='product-name'][contains(text(),'Faded Short Sleeve T-shirts')]")
	public static
	  WebElement Tshirt;
	
	@FindBy(xpath = "//ul[@id='homefeatured']//li[@class='ajax_block_product col-xs-12 col-sm-4 col-md-3 first-in-line first-item-of-tablet-line first-item-of-mobile-line']//div[@class='product-image-container']")
	public static
	  WebElement Target;
	
	@FindBy(xpath = "//input[@id='quantity_wanted']") public static
	  WebElement qty;
	
	@FindBy(xpath = "//div[@id='uniform-group_1']//following-sibling::option")
	public static
	 List < WebElement> size;
	 
	@FindBy(xpath = "//span[contains(text(),'Add to cart')]")
	public static
	  WebElement cart;

	@FindBy(xpath = "//span[contains(text(),'Proceed to checkout')]")
	public static
	  WebElement checkout;
	
	@FindBy(xpath = "//div[@class='box-info-product']")public
	  WebElement frame;
	
	
	   int counter=0;
	
	@FindBy(xpath = "//ul[@id='homefeatured']//following-sibling::li")public static
	  List<WebElement> MultiProducts;
	
	@FindBy(xpath = "//li[contains(@class,'ajax_block_product col-xs-12 col-sm-4 col-md-3')]//span[contains(text(),'Add to cart')]")public static
	  List<WebElement> Addtocart;
	
	@FindBy(xpath = "//span[@class='continue btn btn-default button exclusive-medium']")public static
	  WebElement ContinueShop;
	
	
	@FindBy(xpath = "//span[contains(text(),'Proceed to checkout')]")public static
	  WebElement ProccedCheckout;
	
	@FindBy(xpath = "//span[contains(@id,'total_product_price')]")
	public  List<WebElement> Price;
	
	@FindBy(xpath = "//span[@id='total_price']")public static 
	  WebElement TotalPrice;
	
	@FindBy(xpath = "//td[@id='total_shipping']")
	public  WebElement tax;
	
	@FindBy(xpath = "//td[@id='total_shipping']")public
	  WebElement FinalProced;
	
	@FindBy(xpath = "//span[contains(text(),'Summary')]")
	public 
	WebElement processAddress;
	
	
	
	public homepage() {
		PageFactory.initElements(driver, this);
	}
	
	public void order_product() throws Throwable {
		
	
			TestUtil.MoveElement(homepage.Target);	//Moving object to desired position 
			
			Tshirt.click();
			
			WaitForObject(qty, "Check");
			
			qty.clear();
			
			qty.sendKeys(prop.getProperty("Qty"));	//Can give number of quantity 
			
			TestUtil.SelectQuantity(size, "L");	//Select Size -S / M /L
			
			cart.click();
			
			
			WaitForObject(checkout, "Click");
			
			
		}
	
	
	public WebElement returnProcessElement() {
		
		return processAddress;
	}
	
	public static Payment BeforeLogin_PriceValidation(Boolean a) {
		try {
		
		if(a==true) {
			return new Payment();
		}
		}catch(Exception e) {
			
		}
		return  null;
	}
	
	
	
	public static Payment AfterLogin_PriceValidation(Boolean a) {
		try {
		
		if(a==true) {
			return new Payment();
		}
		}catch(Exception e) {
			
		}
		return  null;
	}
	
	
}
	
		
		
