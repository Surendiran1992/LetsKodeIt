package com.letsKodeIt.test_classes;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.letsKodeIt.Base.BaseTest;

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
		//boolean result = nav.isUserLoggedIn();
		boolean result = nav.verifyHeader();
		Assert.assertTrue(result);
	}

	@Test
	public void testInvalidLogin() {
		nav = login.signInWith("test@email", "abcabc");
		boolean result = nav.isUserLoggedIn();
		Assert.assertFalse(result);
	}

}
