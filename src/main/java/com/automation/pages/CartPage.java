package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.automation.base.BasePage;

public class CartPage extends BasePage {

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(css = ".cart_item")
    private WebElement cartItem;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;

    @FindBy(css = ".inventory_item_name")
    private WebElement productNameInCart;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCartPageDisplayed() {
        return pageTitle.getText().equals("Your Cart");
    }

    public String getProductNameInCart() {
        return productNameInCart.getText();
    }

    public boolean isProductDisplayedInCart() {
        return cartItem.isDisplayed();
    }

    public CheckoutPage clickCheckout() {
        checkoutButton.click();
        return new CheckoutPage(driver);
    }

    public InventoryPage clickContinueShopping() {
        continueShoppingButton.click();
        return new InventoryPage(driver);
    }
}
