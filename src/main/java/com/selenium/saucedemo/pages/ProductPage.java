package com.selenium.saucedemo.pages;

import com.selenium.saucedemo.utils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ProductPage {
    private final WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By productPageTitle = TestUtils.byDataTestId("title");
    private final By productPageMenu = TestUtils.byDataTestId("open-menu");
    public final By productList = TestUtils.byDataTestId("inventory-list");
    public By productSortFilter = TestUtils.byDataTestId("product-sort-container");
    public By shoppingCartBadge = TestUtils.byDataTestId("shopping-cart-badge");

    // product helpers
    public String getTitle() {
        return driver.findElement(productPageTitle).getText();
    }

    public void clickMenu() {
        driver.findElement(productPageMenu).click();
    }

    public void addAllProductsToCart() {
        WebElement productsList = driver.findElement(productList);
        List<WebElement> products = productsList.findElements(By.cssSelector("[data-test='inventory-item']"));

        for (WebElement product : products) {
            WebElement addToCartButton = product.findElement(By.cssSelector("[data-test^='add-to-cart']"));
            addToCartButton.click();
        }
    }

    public void removeAllProductsFromCart() {
        WebElement productsList = driver.findElement(productList);
        List<WebElement> products = productsList.findElements(By.cssSelector("[data-test='inventory-item']"));

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

    public void selectSortOption(String value) {
        WebElement sortFilter = driver.findElement(productSortFilter);
        Select sortDropdown = new Select(sortFilter);
        sortDropdown.selectByValue(value);
    }

    public String getProductFromList(WebElement products, Integer index) {
        List<WebElement> productsList = products.findElements(By.cssSelector("[data-test='inventory-item']"));
        return productsList.get(index).getText();
    }


}
