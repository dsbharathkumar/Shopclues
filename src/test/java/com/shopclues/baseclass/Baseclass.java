package com.shopclues.baseclass;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.shopclues.library.ReusableMethods;


public class Baseclass extends ReusableMethods{
	
	protected static ThreadLocal<RemoteWebDriver> localDriver = new ThreadLocal<>();
	public static WebDriver driver;
	
	@BeforeSuite
	@Parameters(value= {"browser"})
	public void setupApplication(String browser) throws MalformedURLException {
				
		Reporter.log("===== Browser Session Started =====", true);
		DesiredCapabilities capabilities = null;
		
		if(browser.equals("chrome")) {
			
		    // Create Chrome Options
		    ChromeOptions option = new ChromeOptions();
		    option.addArguments("--test-type");
		    option.addArguments("--disable-popup-bloacking");
		    option.addArguments("--no-sandbox") ;
		    option.addArguments("--disable-dev-shm-usage");
		    capabilities = DesiredCapabilities.chrome();
		    capabilities.setBrowserName(browser);
		    capabilities.setJavascriptEnabled(true);
		    capabilities.setCapability(ChromeOptions.CAPABILITY, option);
		    
		    
		} else if(browser.equals("firefox")) {
			
		    // Create firefox options
		    FirefoxProfile profile = new FirefoxProfile();
		    profile.setPreference("network.proxy.no_proxies_on", "localhost");
		    profile.setPreference("javascript.enabled", true);

		    capabilities = DesiredCapabilities.firefox();
		    capabilities.setBrowserName(browser);
		    capabilities.setCapability("marionette", true);
		    capabilities.setCapability(FirefoxDriver.PROFILE, profile);
		    
		}
		
	    //Create driver object
		localDriver.set(new RemoteWebDriver(new URL("http://192.168.0.3:5555/wd/hub"), capabilities));
		driver = localDriver.get();
	    Reporter.log("=====Application Started=====", true);
	}

	 @AfterSuite
	 public void afterSuite() {
		// Quit browser
		 quitBrowser(driver); 
		//Remove the ThreadLocalMap element
		 localDriver.remove();
		 Reporter.log("=====Application Finished=====", true);
	  }
	
}
