package com.shopclues.pom;

import org.openqa.selenium.WebDriver;

import com.shopclues.library.DataHandlers;

public class Searchpage extends DataHandlers{

	WebDriver driver;
	public Searchpage(WebDriver driver) {
		this.driver = driver;
	}

	private static final String FILENAME = "object_repository/objectProperties.properties";
	private static final String CONFIGNAME = "object_repository/config.properties";
	
	// Getting config properties
	public final String BASEURL = getProperty(CONFIGNAME, "baseUrl");
	public final String DATASHEET = getProperty(CONFIGNAME, "dataSheet");
	
	// Getting object properties
	public final String INPUTSEARCH = getProperty(FILENAME, "inputXpathSearchTextbox");
	public final String CLICKSEARCH = getProperty(FILENAME, "buttonXpathSearch");
	public final String FIRSTPRODUCT = getProperty(FILENAME, "linkFirstProduct");
	
	public final String PRODUCTNAME = getProperty(FILENAME, "productName");
	public final String PRODUCTPRICE = getProperty(FILENAME, "productPrice");
	public final String PRODUCTCOUNT = getProperty(FILENAME, "resultProductCount");
	public final String NOTIFICATION = getProperty(FILENAME, "xpathNotification");
	
}
