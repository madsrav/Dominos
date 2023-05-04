package com.dominos_generic;

import java.io.IOException;


import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public WebDriver driver;
	public WebDriverUtilities utils;
	public ExcelFileUtility excel;

//	@Parameters("browser")
	@BeforeClass
//	public void launchingBrowser(String browser) throws EncryptedDocumentException, IOException
//	{
	public void launchingBroswer() throws EncryptedDocumentException, IOException {
//		if (browser.equals("chrome"))
//	   {	
		ChromeOptions options= new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		driver=new ChromeDriver(options);
		excel= new ExcelFileUtility();
		
		driver.get(excel.excelFile(I_AutoConstants.EXCELPATH, "Sheet1", 1, 1));
//	   }
//		
//		else if(browser.equals("firefox"))
//		{
//			driver= new FirefoxDriver();
//		}
//		
//		else 
//		{
//			driver= new EdgeDriver();
//		}
		
		utils = new WebDriverUtilities();
		utils.implicitWait(driver);
		utils.maximize(driver);
	}

	@AfterClass
	public void closingBrowser() {
		driver.quit();
	}

}
