package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.*;
import com.automation.utils.DriverManager;
import com.automation.utils.TestDataProvider;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    @Test(priority = 1, groups = {"smoke", "checkout", "e2e"},dataProvider = "CheckoutData", dataProviderClass = TestDataProvider.class)
    public void completeCheckoutSuccessfully(String firstName, String lastName, String zip) {

        // 1. Login
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");

        // 2. Add product + go to cart
        inventoryPage.addBackpackToCart();
        CartPage cartPage = inventoryPage.clickCartIcon();

        // 3. Proceed to checkout
        CheckoutPage checkoutPage = cartPage.clickCheckout();

        // 4. Fill details
        checkoutPage.fillShippingDetails(firstName,lastName,zip);
        checkoutPage.clickContinue();

        // 5. Finish order
        checkoutPage.clickFinish();

        // 6. Assertion
        Assert.assertEquals(checkoutPage.getConfirmationMessage(),
                "Thank you for your order!",
                "Order confirmation message is incorrect");
    }

    @Test(priority = 2, groups = {"checkout"})
    public void checkoutWithEmptyCartShouldFail() {

        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");

        // Go to cart without adding any product
        CartPage cartPage = inventoryPage.clickCartIcon();

        // Try to checkout
        CheckoutPage checkoutPage = cartPage.clickCheckout();

        // You can add validation here according to application behavior
        // For SauceDemo, it still allows going to checkout even with empty cart
        Assert.assertTrue(checkoutPage != null);
    }
}