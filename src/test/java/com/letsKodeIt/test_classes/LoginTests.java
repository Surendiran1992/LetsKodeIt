package com.letsKodeIt.test_classes;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.letsKodeIt.Base.BaseTest;
import com.letsKodeIt.Base.CheckPoint;

public class LoginTests extends BaseTest {

	@AfterMethod
	public void afterMethod() {
		if (nav.isUserLoggedIn()) {
			nav.logout();
			nav.login();
		}
	}

	@Test
	public void testLogin() {
		nav = login.signInWith("test@email.com", "abcabc");
		boolean result1 = nav.verifyHeader();
		CheckPoint.mark("test01", result1, "verifying headers");
		boolean result = nav.isUserLoggedIn();
		CheckPoint.markFinal("test01", result, "verifying accountImage");
		//Assert.assertTrue(result);
	}

	@Test(enabled=false)
	public void testInvalidLogin() {
		nav = login.signInWith("test@email", "abcabc");
		boolean result = nav.isUserLoggedIn();
		Assert.assertFalse(result);
	}

}
