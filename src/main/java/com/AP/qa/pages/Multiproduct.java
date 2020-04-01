package com.AP.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.AP.qa.base.TestBase;

public class Multiproduct extends TestBase{
	@FindBy(xpath = "//body[@id='category']/div[@id='page']/div/div[@id='columns']/div/div[@id='center_column']/ul/li[1]/div[1]/div[1]/div[1]")
	WebElement product1;
	
	@FindBy(xpath = "//li[@class='ajax_block_product col-xs-12 col-sm-6 col-md-4 last-item-of-tablet-line']//a[@class='product_img_link']")
	WebElement product2;
	
	@FindBy(xpath = "//*[@id=\"block_top_menu\"]/ul/li[2]/a")
	WebElement dresses;
	
	@FindBy(xpath = "//li[2]//div[1]//div[2]//div[2]//a[1]//span[1]")
	WebElement add2cart2;
	
	@FindBy(xpath = "//span[@class='continue btn btn-default button exclusive-medium']//span[1]")
	WebElement Continue;
	
	@FindBy(xpath = "//li[1]//div[1]//div[2]//div[2]//a[1]//span[1]")
	WebElement add2cart;
	
	@FindBy(xpath = "//b[contains(text(),'Cart')]")
	WebElement cart;
	
	public Multiproduct() {
		PageFactory.initElements(driver, this);
	}
	
	public void addproduct() {
		Actions action = new Actions(driver);
		dresses.click();
		action.moveToElement(product1).perform();
		add2cart.click();
		Continue.click();
		action.moveToElement(product2).perform();
		add2cart2.click();
		Continue.click();
		cart.click();
	}

}
