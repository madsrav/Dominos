package com.dominos_generic;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;

	public class WebDriverUtilities {
		
		/*
		 * use this for mousehover
		 */
		
		public void mousehover(WebDriver driver, WebElement element)
		{
			Actions action= new Actions(driver);
			action.moveToElement(element).perform();
		}
		public void scrollDown(WebDriver driver, WebElement element)
		{
			JavascriptExecutor jse= (JavascriptExecutor)driver;
			int y=element.getLocation().getY();  // to get the co ordinate value of y
			jse.executeScript("window.scrollBy(0,"+y+")", element);
		}
		/*
		 * use this method, if element is not interactable using selenium click 
		 * 
		 */
		public void jsClick(WebDriver driver, WebElement element)
		{
			JavascriptExecutor jse= (JavascriptExecutor)driver;
			jse.executeScript("arguments[0].click()", element);
		}
		public void dropDown(WebElement element, int index)
		{
			Select s= new Select(element);
			s.selectByIndex(index);
		}
		public void dropDown(WebElement element, String visibletext)
		{
			Select s= new Select(element);
			s.selectByVisibleText(visibletext);
		}
		public void acceptAlert(WebDriver driver)
		{
			driver.switchTo().alert().accept();
		}
		public void dismissAlert(WebDriver driver)
		{
			driver.switchTo().alert().dismiss();
		}
		/*
		 * takes screenshot in byte format
		 * @param driver
		 * @return byte stream of screenshot
		 */
		public  byte[] takescreenShot1(WebDriver driver)
		{
//			EventFiringWebDriver eft= new EventFiringWebDriver(driver);
//			return eft.getScreenshotAs(OutputType.BYTES);
			TakesScreenshot eft=(TakesScreenshot)driver;
		    return	eft.getScreenshotAs(OutputType.BYTES);
			
		}

		public void maximize(WebDriver driver)
		{
			driver.manage().window().maximize();
		}
		
		public void implicitWait(WebDriver driver)
		{
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		
		public void jseExecutor(String jseScript,WebDriver driver)
		{
			JavascriptExecutor jse =(JavascriptExecutor)driver;
			jse.executeScript(jseScript);
		}
		public void SwitchToWindow(String title,WebDriver driver)
		{
			String currentwindow= driver.getWindowHandle();
			for(String childId : driver.getWindowHandles())
			{
				if(!currentwindow.equals(childId))
				{
					driver.switchTo().window(childId);
					if(title.contains(driver.getTitle())) 
					{
						break;
					}
					else
					{
						throw new NoSuchWindowException(title+"window not availble");
					}
				}
			}
			
			
		}
	}