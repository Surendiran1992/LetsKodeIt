package com.letsKodeIt.POM_classes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchBarPage {

    public SearchBarPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver driver;
    private String SEARCH_COURSE_FIELD = "//input[@id='search']";
    private String SEARCH_COURSE_BUTTON = "//button[starts-with(@class,'find-course')]";

    public ResultsPage course(String courseName) {
        WebElement searchField = driver.findElement(By.xpath(SEARCH_COURSE_FIELD));
        searchField.clear();
        searchField.sendKeys(courseName);

        WebElement searchButton = driver.findElement(By.xpath(SEARCH_COURSE_BUTTON));
        searchButton.click();

        return new ResultsPage(driver);
    }
}