package com.shopclues.tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.shopclues.baseclass.Baseclass;
import com.shopclues.pom.Searchpage;
import com.shopclues.utilities.XLUtility;

public class TS_002_SEARCH_PRODUCT extends Baseclass{
	Searchpage searchpage;
	
	public TS_002_SEARCH_PRODUCT() {
		searchpage = new Searchpage(driver);
	}
	
	@BeforeTest
	public void beforeTest() {
		getUrl(driver, searchpage.BASEURL);
		maximizeWindow(driver);
		implicitWait(driver, 30, TimeUnit.SECONDS);
		pageLoadTimeOut(driver);
		steadyState(driver);
	}
	
	@Test(dataProvider = "DataProvider")
	public void searchItem(String product) {
		visibleWait(driver, 30, getLocator(Locators.xpath, searchpage.INPUTSEARCH));
		sendKeys(driver, getLocator(Locators.xpath, searchpage.INPUTSEARCH), product);
		clickableWait(driver, 5, getLocator(Locators.xpath, searchpage.CLICKSEARCH));
		clickElement(driver, getLocator(Locators.xpath, searchpage.CLICKSEARCH));
		
		presenceWait(driver, 30, getLocator(Locators.xpath, searchpage.FIRSTPRODUCT));
		clickElement(driver, getLocator(Locators.xpath, searchpage.FIRSTPRODUCT));
		
		switchToNextTab(driver);
		steadyState(driver);
		pageLoadTimeOut(driver);
		
		presenceWait(driver, 30, getLocator(Locators.xpath, searchpage.PRODUCTNAME));
		visibleWait(driver, 30, getLocator(Locators.xpath, searchpage.PRODUCTNAME));
		String productName = fetchText(driver, getLocator(Locators.xpath, searchpage.PRODUCTNAME));
		
		visibleWait(driver, 30, getLocator(Locators.cssSelector, searchpage.PRODUCTPRICE));
		String productPrice = fetchText(driver, getLocator(Locators.cssSelector, searchpage.PRODUCTPRICE));
		
		Reporter.log("Name of the product is "+productName+" whose price is "+productPrice, true);
		
		
	}
	
	@AfterTest
	public void afterTest() {
		closeBrowser(driver);
	}
	
	 @DataProvider(name="DataProvider")
		String [][] getData() throws IOException{
			
			// Read Data from Excel
			String path = System.getProperty("user.dir")+ searchpage.DATASHEET;
			int rowNum = XLUtility.getRowCount(path, "Sheet2");
			int colNum = XLUtility.getCellCount(path, "Sheet2", rowNum);
			String data[][] = new String[rowNum][colNum];
			
			for(int i =1; i <= rowNum ; i++) {
				for(int j=0; j < colNum ; j++) {
					data[i-1][j] =XLUtility.getCellData(path, "Sheet2", i, j);
				}
			}
			
			return data;
		}
	
}
