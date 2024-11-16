package com.selenium.saucedemo.pages;

import com.selenium.saucedemo.utils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {
    private final WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public By productPageTitle = TestUtils.byDataTestId("title");
    public By productPageMenu = TestUtils.byDataTestId("open-menu");
    public By ProductList = TestUtils.byDataTestId("inventory-list");
    public By backpackAddToCart = TestUtils.byDataTestId("add-to-cart-sauce-labs-backpack");
    public By onesieAddToCart = TestUtils.byDataTestId("add-to-cart-sauce-labs-onesie");
    public By productSortFilter = TestUtils.byDataTestId("product-sort-container");
    public By shoppingCart = TestUtils.byDataTestId("shopping-cart-link");


    // product helpers

}
