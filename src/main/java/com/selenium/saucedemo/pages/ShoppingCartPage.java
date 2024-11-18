package com.selenium.saucedemo.pages;

import com.selenium.saucedemo.utils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ShoppingCartPage {
    private final WebDriver driver;

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By shoppingCartPageTitle = TestUtils.byDataTestId("title");
    private final By checkoutButton = TestUtils.byDataTestId("checkout");
    private final By continueShoppingButton = TestUtils.byDataTestId("continue-shopping");
    public final By shoppingCartList = TestUtils.byDataTestId("cart-list");
    public By shoppingCartBadge = TestUtils.byDataTestId("shopping-cart-badge");

    // shopping cart helper
    public String getTitle() {
        return driver.findElement(shoppingCartPageTitle).getText();
    }

    public void removeAllProductsFromCart() {
        WebElement cartList = driver.findElement(shoppingCartList);
        List<WebElement> products = cartList.findElements(By.cssSelector("[data-test='inventory-item']"));

        for (WebElement product : products) {
            WebElement removeFromCartButton = product.findElement(By.cssSelector("[data-test^='remove']"));
            removeFromCartButton.click();
        }
    }

    public boolean shoppingCartBadgeDisplayed() {
        return !driver.findElements(By.cssSelector("[data-test='shopping-cart-badge']")).isEmpty();
    }

    public String getShoppingCartBadge() {
        return driver.findElement(shoppingCartBadge).getText();
    }

    public void clickCheckoutButton() {
        driver.findElement(checkoutButton).click();
    }

    public void clickContinueShoppingButton() {
        driver.findElement(continueShoppingButton).click();
    }
}
