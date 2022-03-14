package com.shopclues.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.shopclues.baseclass.Baseclass;
import com.shopclues.pom.Loginpage;
import com.shopclues.utilities.XLUtility;

@Listeners(com.shopclues.utilities.Listeners.class)

public class TS_001_LOGIN_PAGE_VALIDATION extends Baseclass {
	
	Loginpage loginpage;
	
	public TS_001_LOGIN_PAGE_VALIDATION() {
		loginpage = new Loginpage(driver);
	}
	
	@BeforeTest
	public void beforeTest() {
		getUrl(driver, loginpage.BASEURL);
		maximizeWindow(driver);
		implicitWait(driver, 30, TimeUnit.SECONDS);
		pageLoadTimeOut(driver);
		steadyState(driver);
	}
	
	@Test(dataProvider = "DataProvider")
	public void validateLoginPage(String mobEmail) {
		clickElement(driver, getLocator(Locators.cssSelector, loginpage.CLICKSIGNIN));
		presenceWait(driver, 30, getLocator(Locators.cssSelector, loginpage.LOGINPOPUP));
		visibleWait(driver, 10, getLocator(Locators.cssSelector, loginpage.LOGINPOPUP));
		sendKeys(driver, getLocator(Locators.cssSelector, loginpage.INPUTMOBEMAIL), mobEmail);
		clickElement(driver, getLocator(Locators.cssSelector, loginpage.CLICKLOGINVIAOTP));
		visibleWait(driver, 5, getLocator(Locators.cssSelector, loginpage.TEXTINVALID));
		
		List<WebElement> elements = findElements(driver, getLocator(Locators.cssSelector, loginpage.TEXTINVALID));
		List<String> errorMsgs = new ArrayList<String>();
		for( WebElement element: elements) {
			if(element.getText() != null && !element.getText().isEmpty() && element.getText() != "") {
				errorMsgs.add(element.getText());
			}
		}
		Reporter.log("Error messages for "+ mobEmail + " are " + errorMsgs.toString(), true);
		Assert.assertTrue(!errorMsgs.isEmpty() || errorMsgs.size() > 0, "No errors found for "+mobEmail);
		
	}
	
	@AfterTest
	public void afterTest() {
		closeBrowser(driver);
	}
	
	 @DataProvider(name="DataProvider")
		String [][] getData() throws IOException{
			
			// Read Data from Excel
			String path = System.getProperty("user.dir")+ loginpage.DATASHEET;
			int rowNum = XLUtility.getRowCount(path, "Sheet1");
			int colNum = XLUtility.getCellCount(path, "Sheet1", rowNum);
			String data[][] = new String[rowNum][colNum];
			
			for(int i =1; i <= rowNum ; i++) {
				for(int j=0; j < colNum ; j++) {
					data[i-1][j] =XLUtility.getCellData(path, "Sheet1", i, j);
				}
			}
			
			return data;
		}
}
