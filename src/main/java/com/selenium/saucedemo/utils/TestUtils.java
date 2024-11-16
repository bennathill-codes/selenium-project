package com.selenium.saucedemo.utils;

import com.selenium.saucedemo.pages.LoginPage;
import org.openqa.selenium.By;

public class TestUtils {
    private static LoginPage loginPage;

    public void login() {
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();
    }

    public void loginInvalid() {
        loginPage.enterUsername("error_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();
    }

    public static By byDataTestId(String value) {
        return By.cssSelector((String.format("[data-test='%s']", value)));
    }
}
