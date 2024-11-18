package com.selenium.saucedemo.end2end;

import com.selenium.saucedemo.pages.CheckoutPage;
import com.selenium.saucedemo.pages.LoginPage;
import com.selenium.saucedemo.pages.ProductPage;
import com.selenium.saucedemo.pages.ShoppingCartPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class EndToEndTest {
    private static WebDriver driver;
    private static LoginPage loginPage;
    private static ProductPage productPage;
    private static ShoppingCartPage shoppingCartPage;
    private static CheckoutPage checkoutPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        // page object models
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testEndToEnd() {
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();
        assertThat(productPage.getTitle()).contains("Products");

        productPage.addAllProductsToCart();
        productPage.clickShoppingCartLink();
        assertThat(shoppingCartPage.getTitle()).contains("Cart");

        shoppingCartPage.clickCheckoutButton();
        assertThat(checkoutPage.getTitle()).contains("Checkout");

        checkoutPage.enterFirstName("Coco");
        checkoutPage.enterLastName("Bean");
        checkoutPage.enterPostalCode("12345");
        checkoutPage.clickContinueButton();
        assertThat(checkoutPage.getTitle()).contains("Overview");
        checkoutPage.clickFinishButton();
        assertThat(checkoutPage.getTitle()).contains("Complete!");
    }
}
