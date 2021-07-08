package com.letsKodeIt.POM_classes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.BasePage;
//import org.openqa.selenium.WebElement;
import utilities.Util;

public class NavigationPage extends BasePage {

	public NavigationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	/***
	 * Variables
	 * URL
	 * Title
	 */
	public WebDriver driver;
	//private String URL = "https://courses.letskodeit.com/courses";
	private String ALL_COURSES_LINK = "//a[contains(text(),'ALL COURSES')]";
	private String MY_COURSES_LINK = "//a[contains(text(),'MY COURSES')]";
	private String ACCOUNT_ICON = "dropdownMenu1";
	private String LOGIN_LINK = "//a[@href='/login']";
	private String LOGOUT_LINK = "//a[@href='/logout']";


	public void allCourses() {
		driver.findElement(By.xpath(ALL_COURSES_LINK)).click();
	}

	public void myCourses() {
		driver.findElement(By.xpath(MY_COURSES_LINK)).click();
	}
	public boolean isUserLoggedIn() {
		try {
			driver.findElement(By.id(ACCOUNT_ICON));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public boolean verifyHeader() {
        WebElement link = driver.findElement(By.xpath(ALL_COURSES_LINK));
        String text = link.getText();
        return Util.verifyTextContains(text, "ALL COURSES");
    }
	public LoginPage login() {
		driver.findElement(By.xpath(LOGIN_LINK)).click();
		return new LoginPage(driver);
	}
	public void logout() {
		driver.findElement(By.id(ACCOUNT_ICON)).click();
		WebDriverWait wait = new WebDriverWait(driver, 3);
		WebElement logoutLink = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath(LOGOUT_LINK)));
		logoutLink.click();
	}

}
