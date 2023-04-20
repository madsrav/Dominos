package com.dominos_generic;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;


public class BaseClass {
	
	
	public WebDriver driver;
	public WebDriverUtilities utils;
	//public static WebDriver sdriver;
	public ExcelFileUtility excel;
	@BeforeClass
	public void launchingBrowser() throws EncryptedDocumentException, IOException
	{
		
		ChromeOptions options= new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver=new ChromeDriver(options);
		excel= new ExcelFileUtility();
		
		driver.get(excel.excelFile(I_AutoConstants.EXCELPATH, "Sheet1", 1, 1));
		
		utils = new WebDriverUtilities();
		utils.implicitWait(driver);
		utils.maximize(driver);	
		//sdriver=driver;
	}

//	@AfterClass
	public void closingBrowser()
	{
		driver.quit();
	}

}
