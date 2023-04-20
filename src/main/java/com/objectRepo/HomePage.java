package com.objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	@FindBy(xpath="//button[.='ORDER ONLINE NOW']")
	private WebElement ClickOrderOnlineButton;
	
	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getClickOrderOnlineButton() {
		return ClickOrderOnlineButton;
	}
	
	public void clickOnOrderOnlineButton()
	{
		getClickOrderOnlineButton().click();
	}
	
	
}
