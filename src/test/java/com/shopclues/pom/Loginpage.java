package com.shopclues.pom;

import org.openqa.selenium.WebDriver;

import com.shopclues.library.DataHandlers;

public class Loginpage extends DataHandlers{
	WebDriver driver;
	public Loginpage(WebDriver driver) {
		this.driver = driver;
	}

	private static final String FILENAME = "object_repository/objectProperties.properties";
	private static final String CONFIGNAME = "object_repository/config.properties";
	
	// Getting config properties
	public final String BASEURL = getProperty(CONFIGNAME, "baseUrl");
	public final String DATASHEET = getProperty(CONFIGNAME, "dataSheet");
	
	// Getting object properties
	public final String CLICKSIGNIN = getProperty(FILENAME, "buttonCssSignIn");
	public final String INPUTMOBEMAIL = getProperty(FILENAME, "inputCssMobOrEmail");
	public final String CLICKLOGINVIAOTP = getProperty(FILENAME, "buttonCssLoginViaOtp");
	public final String TEXTINVALID = getProperty(FILENAME, "inValidCss");
	public final String LOGINPOPUP = getProperty(FILENAME, "popupCss");
	
	// Re-usable methods
	
}
	
