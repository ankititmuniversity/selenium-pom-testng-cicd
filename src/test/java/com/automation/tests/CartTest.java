package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.CartPage;
import com.automation.pages.InventoryPage;
import com.automation.pages.LoginPage;
import com.automation.utils.DriverManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @Test(priority = 1, groups = {"smoke", "cart"})
    public void addProductToCartAndVerify() {

        // Login
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");

        // Add product to cart
        inventoryPage.addBackpackToCart();
        CartPage cartPage = inventoryPage.clickCartIcon();

        // Assertions
        Assert.assertTrue(cartPage.isCartPageDisplayed(), "Cart page is not displayed");
        Assert.assertTrue(cartPage.isProductDisplayedInCart(), "Product is not present in the cart");
        Assert.assertEquals(cartPage.getProductNameInCart(), "Sauce Labs Backpack", "Product name mismatch");
    }

    @Test(priority = 2, groups = {"cart"})
    public void continueShoppingFromCart() {

        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");

        inventoryPage.addBackpackToCart();
        CartPage cartPage = inventoryPage.clickCartIcon();

        // Click Continue Shopping
        InventoryPage inventoryPageAgain = cartPage.clickContinueShopping();

        Assert.assertTrue(inventoryPageAgain.isInventoryPageDisplayed(), "Did not return to Inventory page");
    }
}