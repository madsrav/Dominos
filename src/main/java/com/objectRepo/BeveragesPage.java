package com.objectRepo;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.dominos_generic.ExcelFileUtility;
import com.dominos_generic.I_AutoConstants;
import com.dominos_generic.WebDriverUtilities;

public class BeveragesPage {
	
	WebDriver driver;
	WebDriverUtilities utilities=new WebDriverUtilities();
	ExcelFileUtility excel;

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

	public double Totalbeverages(String product) throws InterruptedException, NumberFormatException, EncryptedDocumentException, IOException
	{
		getClickOnBeverages().click();
		Thread.sleep(3000);
		getClickOnPepsiAddToCart().click();
		Thread.sleep(3000);
		WebElement increment = driver.findElement(By.xpath("//span[.='"+product+"']/../..//div[@data-label='increase']"));
		excel= new ExcelFileUtility();
		//for increase the products count is taking from excel
		int size = Integer.parseInt(excel.excelFile(I_AutoConstants.EXCELPATH, "Sheet1", 5, 1));
		for (int i = 1; i<size ; i++)
		{
			utilities.jsClick(driver, increment);		
		}
		WebElement individualprice = driver.findElement(By.xpath("//div[@class='crt-cnt-descrptn']/span[.='"+product+"']/../../..//span[@class='rupee']"));	
		return Double.parseDouble(individualprice.getText().replaceAll("[^0-9.]", ""));
	}
	
	public double removeItems(String product) throws NumberFormatException, EncryptedDocumentException, IOException
	{
		 WebElement decrement = driver.findElement(By.xpath("//span[.='"+product+"']/../..//div[@data-label='decrease']"));
		 int size=Integer.parseInt(excel.excelFile(I_AutoConstants.EXCELPATH, "Sheet1", 5, 2));
		for(int i=1;i<size;i++) {
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
