package com.AP.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.AP.qa.base.TestBase;
import com.AP.qa.util.Excel_Libraries;

public class Multi_Product_Parameter extends TestBase{

int counter=0;
	
	@FindBy(xpath = "//ul[@id='homefeatured']//following-sibling::li")
List<WebElement> MultiProducts;
	
	@FindBy(xpath = "//li[contains(@class,'ajax_block_product col-xs-12 col-sm-4 col-md-3')]//span[contains(text(),'Add to cart')]")
	List<WebElement> Addtocart;
	
	@FindBy(xpath = "//span[@class='continue btn btn-default button exclusive-medium']")
	WebElement ContinueShop;
	
	
	@FindBy(xpath = "//span[contains(text(),'Proceed to checkout')]")
	WebElement ProccedCheckout;
	
	@FindBy(xpath = "//span[contains(@id,'product_price')]/span")
	List<WebElement> Price;
	
	@FindBy(xpath = "//span[@id='total_price']")
	WebElement TotalPrice;
	
	@FindBy(xpath = "//td[@id='total_shipping']")
	WebElement tax;
	
	@FindBy(xpath = "//td[@id='total_shipping']")
	WebElement FinalProced;
	
	public Multi_Product_Parameter() {
		PageFactory.initElements(driver, this);
	}

	public void SelectProducts() throws Throwable {
		int multiproducts  = Integer.parseInt(prop.getProperty("MultiProduct"));
		
		for(int j = 0;j<multiproducts;j++ ) {
				
			System.out.println(MultiProducts.size());
				for(int i=0;i<=MultiProducts.size();i++)
				{
					try {
					if(MultiProducts.get(i).getText().contains(Excel_Libraries.getdata(j)))
					{
						Actions action = new Actions(driver);
						action.moveToElement(MultiProducts.get(i)).build().perform();		
						WaitForObject(Addtocart.get(i), "Click");
						counter=counter+1;
						break;
					}
				}
				catch(Exception f){
					f.printStackTrace();
					System.out.println("Not founds");
				}
				}
				
				if(counter==multiproducts) {
					ProccedCheckout.click();
				}
				else
					WaitForObject(ContinueShop, "Click");		
		}
		
		
		
		
	}
}
