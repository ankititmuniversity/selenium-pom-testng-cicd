package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.automation.base.BasePage;

public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public InventoryPage login(String username, String password) {
    	 usernameField.clear();
         usernameField.sendKeys(username);
         passwordField.clear();
        
         passwordField.sendKeys(password);
        loginButton.click();
        return new InventoryPage(driver);
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}