package com.letsKodeIt.POM_classes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Base.BasePage;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    	this.driver = driver;
    }

    /***
     * Variables
     * Locators
     * URL
     */
    public WebDriver driver;
    private String LOGIN_LINK = "//a[@href='/login']";
    private String EMAIL_FIELD = "email";
    private String PASSWORD_FIELD = "password";
    private String LOG_IN_BUTTON = "//input[@value='Login']";

    /***
     * Methods
     */
    public void open() {
        driver.findElement(By.xpath(LOGIN_LINK)).click();
    }

    public NavigationPage signInWith(String email, String password) {
        WebElement emailField = driver.findElement(By.id(EMAIL_FIELD));
        emailField.clear();
        emailField.sendKeys(email);

        WebElement passwordField = driver.findElement(By.id(PASSWORD_FIELD));
        passwordField.clear();
        passwordField.sendKeys(password);

        WebElement loginButton = driver.findElement(By.xpath(LOG_IN_BUTTON));
        loginButton.click();

        return new NavigationPage(driver);
    }
}
