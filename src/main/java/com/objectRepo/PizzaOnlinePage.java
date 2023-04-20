package com.objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PizzaOnlinePage {

	@FindBy(xpath="//input[@class='srch-cnt-srch-inpt']")
	private WebElement ClickonLocateMe;
	
	@FindBy(xpath="//input[@placeholder='Enter Area / Locality']")
	private WebElement ClicktoEnterPinCode;
	
	@FindBy(xpath="//p[@class='suggestions']/following-sibling::ul/li[1]")
	private WebElement ClickOnFirstSuggestion;
	
	public PizzaOnlinePage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
	
	

	public WebElement getClickonLocateMe() {
		return ClickonLocateMe;
	}
	 
	public WebElement getClicktoEnterPinCode() {
		return ClicktoEnterPinCode;
	}

	
	public WebElement getClickOnFirstSuggestion() {
		return ClickOnFirstSuggestion;
	}
	public void clickonlocateme(String pincode) throws InterruptedException
	{
		getClickonLocateMe().click();
		Thread.sleep(3000);
		getClicktoEnterPinCode().sendKeys(pincode);
		getClickOnFirstSuggestion().click();
	}
	
	
}
