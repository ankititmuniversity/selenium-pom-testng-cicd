package com.automation.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.base.BaseTest;
import com.automation.pages.InventoryPage;
import com.automation.pages.LoginPage;
import com.automation.utils.DriverManager;
import com.automation.utils.RetryAnalyzer;
import com.automation.utils.TestDataProvider;

public class LoginTest extends BaseTest {
	private static final Logger logger = LogManager.getLogger(LoginTest.class);

	@Test(priority = 1, groups = "smoke",dataProvider = "LoginData", dataProviderClass = TestDataProvider.class)
	public void validLoginTest(String user, String pwd) {
		logger.info("Starting valid login test");
		LoginPage loginPage = new LoginPage(DriverManager.getDriver());
		InventoryPage inventory = loginPage.login(user, pwd);
		Assert.assertTrue(inventory.isInventoryPageDisplayed());
		logger.info("Login successful");
	}

	@Test(priority = 2, groups = "regression",retryAnalyzer = RetryAnalyzer.class)
	public void invalidLoginTest() {
		logger.info("Starting invalid login test");
		LoginPage loginPage = new LoginPage(DriverManager.getDriver());
		loginPage.login("wrong", "wrong");
		Assert.assertTrue(loginPage.getErrorMessage().contains("Epic sadface"));
		logger.info("Login failed");
	}
}
