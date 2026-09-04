package com.automation.base;

import com.automation.utils.AllureUtils;
import com.automation.utils.DriverManager;
import com.automation.utils.ScreenshotUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    protected static final Logger logger = LogManager.getLogger(BaseTest.class);
    
    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser, ITestResult result)  {
        logger.info("========== Starting Test: " + result.getMethod().getMethodName() + " ==========");
        
        DriverManager.initDriver(browser);
        DriverManager.getDriver().get("https://www.saucedemo.com/");
        
        logger.info("Browser launched and navigated to SauceDemo");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {

        String testName = result.getMethod().getMethodName();

        if (result.getStatus() == ITestResult.FAILURE) {
            logger.error("Test Failed: " + testName);
            logger.error("Exception: " + result.getThrowable());

            // 1. Take Screenshot for Extent + local folder
            String screenshotPath = ScreenshotUtils.captureScreenshot(
                    DriverManager.getDriver(), testName);
            logger.info("Screenshot saved at: " + screenshotPath);

            // 2. Attach screenshot to Allure Report
            AllureUtils.attachScreenshot(DriverManager.getDriver());
        } 
        else if (result.getStatus() == ITestResult.SUCCESS) {
            logger.info("Test Passed: " + testName);
        } 
        else if (result.getStatus() == ITestResult.SKIP) {
            logger.warn("Test Skipped: " + testName);
        }

        // Quit browser
        DriverManager.quitDriver();
        logger.info("========== Finished Test: " + testName + " ==========\n");
    }
}