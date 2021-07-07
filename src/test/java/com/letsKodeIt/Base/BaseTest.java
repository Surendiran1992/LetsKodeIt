package com.letsKodeIt.Base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

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
	public void commonsetUp() {
		System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        baseURL = "https://courses.letskodeit.com/";
		driver.get(baseURL);
		nav = new NavigationPage(driver);
		login = nav.login();
	}
	 @AfterClass
	    public void commontearDown() {
	        driver.quit();
	    }
}
