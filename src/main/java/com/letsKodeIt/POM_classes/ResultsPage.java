package com.letsKodeIt.POM_classes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ResultsPage {

    public ResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver driver;
    private String COURSES_LIST = "//div[starts-with(@class,'col-lg-4')]";

   
	public int coursesCount() {
        List<WebElement> coursesList = driver.findElements(By.xpath(COURSES_LIST));
        return coursesList.size();
    }

    public boolean verifySearchResult() {
        boolean result = false;
        if (coursesCount() > 0) {
            result = true;
        }
        return result;
    }
    public boolean verifyFilterCourseCount(int expectedCount) {
        return coursesCount() == expectedCount;
    }
}
