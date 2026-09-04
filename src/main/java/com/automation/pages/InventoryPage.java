package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.automation.base.BasePage;

public class InventoryPage extends BasePage {

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "inventory_list")
    private WebElement inventoryList;
    
 // Add inside InventoryPage.java

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addBackpackButton;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;
    
    
    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public void addBackpackToCart() {
        addBackpackButton.click();
    }

    public CartPage clickCartIcon() {
        cartIcon.click();
        return new CartPage(driver);
    }


    public boolean isInventoryPageDisplayed() {
        return inventoryList.isDisplayed();
    }

    public String getPageTitle() {
        return pageTitle.getText();
    }
}