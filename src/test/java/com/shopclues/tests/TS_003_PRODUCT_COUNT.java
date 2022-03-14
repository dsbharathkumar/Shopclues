package com.shopclues.tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.shopclues.baseclass.Baseclass;
import com.shopclues.pom.Searchpage;

public class TS_003_PRODUCT_COUNT extends Baseclass{

	Searchpage searchpage;
	
	public TS_003_PRODUCT_COUNT() {
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
	
	@Test
	public void productCount() throws AWTException {
		try {
			visibleWait(driver, 30, getLocator(Locators.xpath, searchpage.NOTIFICATION));
			if(findElement(driver, getLocator(Locators.xpath, searchpage.NOTIFICATION)).isDisplayed()) {
				findElement(driver, getLocator(Locators.xpath, searchpage.NOTIFICATION)).click();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		visibleWait(driver, 30, getLocator(Locators.xpath, searchpage.INPUTSEARCH));
		clickableWait(driver, 10, getLocator(Locators.xpath, searchpage.INPUTSEARCH));
		clickElement(driver, getLocator(Locators.xpath, searchpage.INPUTSEARCH));
		
		Robot robot = new Robot();
		
		robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyPress(KeyEvent.VK_H);
		robot.keyRelease(KeyEvent.VK_H);
		robot.keyPress(KeyEvent.VK_E);
		robot.keyRelease(KeyEvent.VK_E);
		robot.keyPress(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_A);
		robot.keyPress(KeyEvent.VK_D);
		robot.keyRelease(KeyEvent.VK_D);
		robot.keyPress(KeyEvent.VK_P);
		robot.keyRelease(KeyEvent.VK_P);
		robot.keyPress(KeyEvent.VK_H);
		robot.keyRelease(KeyEvent.VK_H);
		robot.keyPress(KeyEvent.VK_O);
		robot.keyRelease(KeyEvent.VK_O);
		robot.keyPress(KeyEvent.VK_N);
		robot.keyRelease(KeyEvent.VK_N);
		robot.keyPress(KeyEvent.VK_E);
		robot.keyRelease(KeyEvent.VK_E);
		robot.keyPress(KeyEvent.VK_S);
		robot.keyRelease(KeyEvent.VK_S);
		robot.keyRelease(KeyEvent.VK_SHIFT);
		
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		
		WebElement element = findElement(driver, getLocator(Locators.xpath, searchpage.INPUTSEARCH));
		element.sendKeys(Keys.ENTER);
		
		pageLoadTimeOut(driver);
		steadyState(driver);
		
		visibleWait(driver, 30, getLocator(Locators.cssSelector, searchpage.PRODUCTCOUNT));
		String count = fetchText(driver, getLocator(Locators.cssSelector, searchpage.PRODUCTCOUNT));
		Reporter.log("Results of products found --->>> " +count, true);
		Assert.assertTrue(Integer.parseInt(count) > 0, "Products not found");
		
	}
	
	@AfterTest
	public void afterTest() {
		closeBrowser(driver);
	}
	
}
