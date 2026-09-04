package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.*;
import com.automation.utils.DriverManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutFlowTest extends BaseTest {

    @Test(priority = 1, groups = {"smoke", "e2e"})
    public void completeCheckoutFlowTest() {

        // 1. Login
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");

        Assert.assertTrue(inventoryPage.isInventoryPageDisplayed(), "Inventory page not displayed");

        // 2. Add product to cart
        inventoryPage.addBackpackToCart();          // You need to add this method
        inventoryPage.clickCartIcon();

        // 3. Go to Cart
        CartPage cartPage = new CartPage(DriverManager.getDriver());
        Assert.assertTrue(cartPage.isCartPageDisplayed(), "Cart page not displayed");
        Assert.assertTrue(cartPage.isProductDisplayedInCart(), "Product not found in cart");

        // 4. Proceed to Checkout
        CheckoutPage checkoutPage = cartPage.clickCheckout();

        // 5. Fill details and finish
        checkoutPage.fillShippingDetails("John", "Doe", "12345");
        checkoutPage.clickContinue();
        checkoutPage.clickFinish();

        // 6. Verification
        Assert.assertEquals(checkoutPage.getConfirmationMessage(), "Thank you for your order!");
    }
}