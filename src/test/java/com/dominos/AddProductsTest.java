package com.dominos;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.dominos_generic.BaseClass;
import com.dominos_generic.I_AutoConstants;
import com.objectRepo.BeveragesPage;
import com.objectRepo.FoodItemsPage;
import com.objectRepo.HomePage;
import com.objectRepo.PizzaOnlinePage;

public class AddProductsTest extends BaseClass {
	
	@Test
	public void addProductsTest() throws EncryptedDocumentException, IOException, InterruptedException
	{
		HomePage home= new HomePage(driver);
		home.clickOnOrderOnlineButton();
		PizzaOnlinePage pizza = new PizzaOnlinePage(driver);
		pizza.clickonlocateme(excel.excelFile(I_AutoConstants.EXCELPATH, "Sheet1", 0, 1));
		FoodItemsPage food= new FoodItemsPage(driver);
		
		double prod1 = food.clickOnAddTocart(excel.excelFile(I_AutoConstants.EXCELPATH, "Sheet1", 3, 0));
		System.out.println(prod1);
		
		double prod2 = food.clickOnAddTocart(excel.excelFile(I_AutoConstants.EXCELPATH, "Sheet1", 4, 0));
		System.out.println(prod2);
		
		double ActualSubTotal = Double.parseDouble(food.getSubtotal().getText().replaceAll("[^0-9.]", ""));
		
		Assert.assertEquals(ActualSubTotal,(prod1+prod2));
		
		
		//now adding beverages
		BeveragesPage pepsi= new BeveragesPage(driver);
		double pepsiprice = pepsi.Totalbeverages(excel.excelFile(I_AutoConstants.EXCELPATH, "Sheet1", 5, 0));
		System.out.println(pepsiprice);
		
		double afterpepsiSubTotal = Double.parseDouble(food.getSubtotal().getText().replaceAll("[^0-9.]", ""));
		
		Assert.assertEquals(afterpepsiSubTotal,(prod1+prod2+pepsiprice));
		
		
		//removing mentioned products
		
		double reducingProd = food.removeItems(excel.excelFile(I_AutoConstants.EXCELPATH, "Sheet1", 3, 0));
		
		double reducepepsi = pepsi.removeItems(excel.excelFile(I_AutoConstants.EXCELPATH, "Sheet1", 5, 0));
		
		double finalSubTotal = Double.parseDouble(food.getSubtotal().getText().replaceAll("[^0-9.]", ""));
		
		System.out.println(finalSubTotal);
		
		Thread.sleep(5000);
		
		Assert.assertEquals(finalSubTotal,(reducingProd+prod2+reducepepsi));
		
		pepsi.getClickOnCheckOut().click();
	
		
		//final validation
		Assert.assertEquals(finalSubTotal, pepsi.placeOrderSubTotal());
	}

}
