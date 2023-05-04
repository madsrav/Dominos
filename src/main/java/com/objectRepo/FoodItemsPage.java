package com.objectRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.dominos_generic.WebDriverUtilities;

public class FoodItemsPage {
	
	WebDriver driver;
	WebDriverUtilities utilities=new WebDriverUtilities();
	
	@FindBy(xpath="//span[.='VEG PIZZA']")
	private WebElement ClickOnVegPizzaSection ;
	
	@FindBy(xpath="//span[text()='ADD']")
	private WebElement FinalClick;
	
	@FindBy(xpath="//div[@class='chkot-ftr']")
	private WebElement Subtotal;
	
	public FoodItemsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	

	public WebElement getClickOnVegPizzaSection() {
		return ClickOnVegPizzaSection;
	}
	public WebElement getFinalClick() {
		return FinalClick;
	}

	public WebElement getSubtotal() {
		return Subtotal;
	}


	public double clickOnAddTocart(String product) throws InterruptedException {
		//clicking on vegPizzaSection
		getClickOnVegPizzaSection().click();
		Thread.sleep(3000);
		//Adding the product to cart by using javaScriptClick
		WebElement element=driver.findElement(By.xpath("//span[.='"+product+"']/../..//span[text()='ADD TO CART']"));
		utilities.jsClick(driver, element);
		Thread.sleep(3000);
		getFinalClick().click();
		
		//for increasing the no of produts
		WebElement increment = driver.findElement(By.xpath("//span[.='"+product+"']/../..//div[@data-label='increase']"));
		utilities.jsClick(driver, increment);
		WebElement individualprice = driver.findElement(By.xpath("//div[@class='crt-cnt-descrptn']/span[.='"+product+"']/../../..//span[@class='rupee']"));
		return Double.parseDouble(individualprice.getText().replaceAll("[^0-9.]", ""));
				
	}
	
	public double removeItems(String product)
	{
		//for decreasing no of products
	      WebElement decrement = driver.findElement(By.xpath("//span[.='"+product+"']/../..//div[@data-label='decrease']"));
	      utilities.jsClick(driver, decrement);
	      
	      WebElement individualprice = driver.findElement(By.xpath("//div[@class='crt-cnt-descrptn']/span[.='"+product+"']/../../..//span[@class='rupee']"));
			return Double.parseDouble(individualprice.getText().replaceAll("[^0-9.]", ""));
	      
	}

}
