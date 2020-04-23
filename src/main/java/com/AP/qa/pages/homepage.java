package com.AP.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import com.AP.qa.base.TestBase;
import com.AP.qa.util.Excel_Libraries;
import com.AP.qa.util.TestUtil;


public class homepage extends TestBase{
	
	@FindBy(xpath = "//span[contains(text(),'Order history and details')]")public
	  WebElement orderDetails;	

	@FindBy(xpath = "//tr[contains(@class,'first_item')]")public
	  WebElement orderTable;
		
	@FindBy(xpath = "//tr[contains(@class,'first_item')]//a[2]")public
	  WebElement selectFirstOrder;
	
	@FindBy(xpath = "//span[contains(text(),'sunil jaiswal')]")public
	  WebElement profile;
	
	@FindBy(xpath = "//li[@class='ajax_block_product col-xs-12 col-sm-4 col-md-3 first-in-line first-item-of-tablet-line first-item-of-mobile-line hovered']//a[@class='product-name'][contains(text(),'Faded Short Sleeve T-shirts')]")
	public
	  WebElement Tshirt;
	
	@FindBy(xpath = "//ul[@id='homefeatured']//li[@class='ajax_block_product col-xs-12 col-sm-4 col-md-3 first-in-line first-item-of-tablet-line first-item-of-mobile-line']//div[@class='product-image-container']")
	public
	  WebElement Target;
	
	@FindBy(xpath = "//input[@id='quantity_wanted']") public
	  WebElement qty;
	
	@FindBy(xpath = "//div[@id='uniform-group_1']//following-sibling::option")
	public
	 List < WebElement> size;
	 
	@FindBy(xpath = "//span[contains(text(),'Add to cart')]")
	public
	  WebElement cart;

	@FindBy(xpath = "//span[contains(text(),'Proceed to checkout')]")
	public
	  WebElement checkout;
	
	@FindBy(xpath = "//div[@class='box-info-product']")public
	  WebElement frame;
	
	
	   int counter=0;
	
	@FindBy(xpath = "//ul[@id='homefeatured']//following-sibling::li")public
	  List<WebElement> MultiProducts;
	
	@FindBy(xpath = "//li[contains(@class,'ajax_block_product col-xs-12 col-sm-4 col-md-3')]//span[contains(text(),'Add to cart')]")public
	  List<WebElement> Addtocart;
	
	@FindBy(xpath = "//span[@class='continue btn btn-default button exclusive-medium']")public
	  WebElement ContinueShop;
	
	
	@FindBy(xpath = "//span[contains(text(),'Proceed to checkout')]")public
	  WebElement ProccedCheckout;
	
	@FindBy(xpath = "//span[contains(@id,'product_price')]/span")public
	  List<WebElement> Price;
	
	@FindBy(xpath = "//span[@id='total_price']")public
	  WebElement TotalPrice;
	
	@FindBy(xpath = "//td[@id='total_shipping']")public
	  WebElement tax;
	
	@FindBy(xpath = "//td[@id='total_shipping']")public
	  WebElement FinalProced;
	
	
	
	
	public homepage() {
		PageFactory.initElements(driver, this);
	}
	
	public void order_product(String Product) throws Throwable {
		
		
		switch (Product) {
		case "Single":
			
			try {
				TestUtil.MoveElement(Target);
				Tshirt.click();
				WaitForObject(qty, "Check");
				qty.clear();
				qty.sendKeys(prop.getProperty("Qty"));
				
				TestUtil.SelectQuantity(size, "L");//Select Size -S / M /L
				cart.click();
				WaitForObject(checkout, "Check");
				checkout.click();
				}
				catch(Exception e) {
					String Cause = e.toString();
					Reporting("Fail", "Home Page Validation", "homepage should displayed ", "Homepage is unable to show due to"+Cause.substring(1, 88));
					closeBrowser();
				}
			
			break;
			
		case "Multi":
			try {
				int multiproducts  = Integer.parseInt(prop.getProperty("MultiProduct"));
				
				for(int j = 0;j<multiproducts;j++ ) {
						
					System.out.println(MultiProducts.size());
						for(int i=0;i<=MultiProducts.size();i++)
						{
							try {
							if(Excel_Libraries.getdata(j).isEmpty()==false) {
								Reporting("Pass", j+1+" Validation for Input Value", "There must be an Input value", " Input Value"+Excel_Libraries.getdata(j));
								if(MultiProducts.get(i).getText().contains(Excel_Libraries.getdata(j)))
								{
									TestUtil.MoveElement(MultiProducts.get(i));
									WaitForObject(Addtocart.get(i), "Click");
									counter=counter+1;
									break;
								}
							}
						}
						catch(Exception f){
							f.printStackTrace();
							Reporting("Fail", j+1+" Validation for Input Value", "There must be an Input value", " Input Value"+Excel_Libraries.getdata(j));
						}
						}
						
						if(counter==multiproducts) {
							ProccedCheckout.click();
						}
						else
							WaitForObject(ContinueShop, "Click");		
				}
			
				
				}
				catch(Exception e) {
					String Casue = e.toString();
					Reporting("Fail", "Home  Page  Validation", "Home Page should displayed ", "Home Page is unable to show due to"+Casue.substring(1, 88));
					closeBrowser();
				}
					break;
					
		case "Reorder":
			WaitForObject(profile, "Click");		
			orderDetails.click();
			selectFirstOrder.click();
			
			break;
		
				default:
					
					break;
		}
		}
}
	
		
		
