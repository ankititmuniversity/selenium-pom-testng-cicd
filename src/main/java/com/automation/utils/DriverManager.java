package com.automation.utils;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

//public class DriverManager {
//    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
//
//    public static WebDriver getDriver() {
//        return driver.get();
//    }
//
//    public static void setDriver(WebDriver webDriver) {
//        driver.set(webDriver);
//    }
//    public static void initDriver() {
//        WebDriverManager.chromedriver().setup();
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless=new");
//        options.addArguments("--no-sandbox");
//        options.addArguments("--disable-dev-shm-usage");
//        options.addArguments("--window-size=1920,1080");
//        setDriver(new ChromeDriver());
//        getDriver().manage().window().maximize();
//    }
// 
//    public static void quitDriver() {
//        if (getDriver() != null) {
//            getDriver().quit();
//            driver.remove();
//        }
//    }
//}

public class DriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver webDriver) {
        driver.set(webDriver);
    }

    public static void initDriver(String browser) {
        WebDriver webDriver = null;
        try {
            if (browser.equalsIgnoreCase("chrome")) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                options.addArguments("--headless=new"); // for CI
                webDriver = new RemoteWebDriver(new URL("http://localhost:4444"), options);
            } 
            else if (browser.equalsIgnoreCase("firefox")) {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--start-maximized");
                options.addArguments("--headless=new"); // for CI
                webDriver = new RemoteWebDriver(new URL("http://localhost:4444"), options);
            }

            setDriver(webDriver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void quitDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }
}