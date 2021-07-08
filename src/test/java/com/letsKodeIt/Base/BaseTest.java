package com.letsKodeIt.Base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.letsKodeIt.POM_classes.CategoryFilterPage;
import com.letsKodeIt.POM_classes.LoginPage;
import com.letsKodeIt.POM_classes.NavigationPage;
import com.letsKodeIt.POM_classes.ResultsPage;
import com.letsKodeIt.POM_classes.SearchBarPage;

public class BaseTest {
	public WebDriver driver;
	protected String baseURL;
	protected LoginPage login;
	protected NavigationPage nav;
	protected SearchBarPage search;
	protected ResultsPage result;
	protected CategoryFilterPage category;

	@BeforeClass
	@Parameters({"browser"})
	public void commonsetUp(String brows) {
		//WebDriver Factory provides the driver and system path and the parameter is set in xml
		driver = WebDriverFactory.getInstance().getDriver(brows);
		baseURL = "https://courses.letskodeit.com/";
		driver.get(baseURL);
		nav = new NavigationPage(driver);
		login = nav.login();
	}

	@AfterMethod
	public void afterMethod() {
		CheckPoint.clearHashMap();
	}
	
	@AfterClass
	public void commontearDown() {
		WebDriverFactory.getInstance().quitDriver();
	}
}
