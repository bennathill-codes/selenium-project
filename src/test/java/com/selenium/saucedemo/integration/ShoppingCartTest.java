package com.selenium.saucedemo.integration;

import com.selenium.saucedemo.pages.LoginPage;
import com.selenium.saucedemo.pages.ProductPage;
import com.selenium.saucedemo.pages.ShoppingCartPage;
import com.selenium.saucedemo.utils.TestUtils;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class ShoppingCartTest {
    private static WebDriver driver;
    private static LoginPage loginPage;
    private static ProductPage productPage;
    private static ShoppingCartPage shoppingCartPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        // poms
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);

        // navigate to shopping cart page
        TestUtils.login(driver);
        productPage.addAllProductsToCart();
        productPage.clickShoppingCartLink();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testRemoveProductsFromCart() {
        assertThat(shoppingCartPage.getShoppingCartBadge()).contains("6");
        shoppingCartPage.removeAllProductsFromCart();
        assertThat(shoppingCartPage.shoppingCartBadgeDisplayed()).isFalse();
    }

    @Test
    public void testContinueShopping() {
        shoppingCartPage.clickContinueShoppingButton();
        assertThat(productPage.getTitle()).contains("Products");
        productPage.clickShoppingCartLink();
        assertThat(shoppingCartPage.getTitle()).contains("Your Cart");
    }

    @Test
    public void testShoppingCartCheckout() {
        shoppingCartPage.clickCheckoutButton();
        WebElement checkoutPageTitle = driver.findElement(TestUtils.byDataTestId("title"));
        assertThat(checkoutPageTitle.getText()).contains("Checkout");
    }
}
