package com.selenium.saucedemo.utils;

import com.selenium.saucedemo.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestUtils { ;
    private static LoginPage loginPage;

    public static By byDataTestId(String value) {
        return By.cssSelector((String.format("[data-test='%s']", value)));
    }

    public static void login(WebDriver driver) {
        loginPage = new LoginPage(driver);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();
    }

    public static void loginInvalid(WebDriver driver) {
        loginPage = new LoginPage(driver);
        loginPage.enterUsername("error_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();
    }

}
