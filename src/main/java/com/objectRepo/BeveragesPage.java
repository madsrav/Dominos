package com.objectRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.dominos_generic.WebDriverUtilities;

public class BeveragesPage {
	
	WebDriver driver;
	WebDriverUtilities utilities=new WebDriverUtilities();

	@FindBy(xpath="//span[text()='BEVERAGES']")
	private WebElement ClickOnBeverages;
	
	@FindBy(xpath="//span[text()='Pepsi 475ml']/../..//span[text()='ADD TO CART']")
	private WebElement ClickOnPepsiAddToCart;
	
	@FindBy(xpath="//button[@data-label='miniCartCheckout']")
	private WebElement ClickOnCheckOut;
	
	@FindBy(xpath="//span[@data-label='Sub Total']/..//span[@class='rupee']")
	private WebElement PlaceOrderSubTotal;
	
	public BeveragesPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}

	public WebElement getClickOnBeverages() {
		return ClickOnBeverages;
	}

	public WebElement getClickOnPepsiAddToCart() {
		return ClickOnPepsiAddToCart;
	}

	public WebElement getClickOnCheckOut() {
		return ClickOnCheckOut;
	}
	public WebElement getPlaceOrderSubTotal() {
		return PlaceOrderSubTotal;
	}

	public double Totalbeverages(String product) throws InterruptedException
	{
		getClickOnBeverages().click();
		Thread.sleep(3000);
		getClickOnPepsiAddToCart().click();
		Thread.sleep(3000);
		WebElement increment = driver.findElement(By.xpath("//span[.='"+product+"']/../..//div[@data-label='increase']"));
		
		for (int i = 1; i <12; i++)
		{
			utilities.jsClick(driver, increment);		
		}
		WebElement individualprice = driver.findElement(By.xpath("//div[@class='crt-cnt-descrptn']/span[.='"+product+"']/../../..//span[@class='rupee']"));	
		return Double.parseDouble(individualprice.getText().replaceAll("[^0-9.]", ""));
	}
	
	public double removeItems(String product)
	{
		 WebElement decrement = driver.findElement(By.xpath("//span[.='"+product+"']/../..//div[@data-label='decrease']"));
		for(int i=1;i<=6;i++) {
	     utilities.jsClick(driver, decrement);
	    
		}
		WebElement individualprice = driver.findElement(By.xpath("//div[@class='crt-cnt-descrptn']/span[.='"+product+"']/../../..//span[@class='rupee']"));	
		return Double.parseDouble(individualprice.getText().replaceAll("[^0-9.]", ""));
	}

	public double placeOrderSubTotal()
	{
		return  Double.parseDouble(getPlaceOrderSubTotal().getText().replaceAll("[^0-9.]", ""));
	}
	
}
