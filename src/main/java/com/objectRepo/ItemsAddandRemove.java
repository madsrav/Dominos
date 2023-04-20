package com.objectRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ItemsAddandRemove {
	WebDriver driver;
	
	public void RuntimeIncrease(String product)
	{
	
	driver.findElement(By.xpath("//span[.='"+product+"']/../..//div[@data-label='increase']"));
	
	}
	
	public void Runtimedecrease(String product)
	{
	
	driver.findElement(By.xpath("//span[.='"+product+"']/../..//div[@data-label='decrease']"));
	
	}

}
