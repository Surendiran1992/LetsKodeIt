package com.letsKodeIt.POM_classes;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Base.BasePage;


public class CategoryFilterPage extends BasePage {
	public WebDriver driver;
	public Select select;
	
	@FindBy(name="categories")
	WebElement categoryDropdown;
	
	@FindBy(xpath="//div[starts-with(@class,'col-lg-4')]")
	List<WebElement> courseList;
	
	public CategoryFilterPage(WebDriver driver) {
		super(driver);
		this.driver = driver;  
		PageFactory.initElements(driver, this);
	}

	public void clickCategoryDropdown() {
		categoryDropdown.click();
	}
	public List<WebElement> findCategory() {
		select = new Select(categoryDropdown);
		List<WebElement> categoryOption = select.getOptions();
		return categoryOption;
	}
	public ResultsPage select(String categoryName) {
		select = new Select(categoryDropdown);
		select.selectByVisibleText(categoryName);
		return new ResultsPage(driver);
	}
	public int findCoursesCount(String categoryName) {
		return courseList.size();
	 }
}
