package com.letsKodeIt.test_classes;


import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.letsKodeIt.Base.BaseTest;
import com.letsKodeIt.POM_classes.CategoryFilterPage;
import com.letsKodeIt.POM_classes.SearchBarPage;


public class AllCoursesTests extends BaseTest {
       
    @BeforeClass
    public void setUp() {
    	nav = login.signInWith("test@email.com", "abcabc");
    }

    @Test
    public void verifySearchCourse() {
        nav.allCourses();
        search = new SearchBarPage(driver);
        result = search.course("rest api");
        boolean searchResult = result.verifySearchResult();
        Assert.assertTrue(searchResult);
    }
    
    @Test
    public void filterByCategory() {
        nav.allCourses();
        category = new CategoryFilterPage(driver);
        result = category.select("Test Automation");
        int count = category.findCoursesCount("Test Automation");
        boolean filterResult = result.verifyFilterCourseCount(count);
        Assert.assertTrue(filterResult);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
