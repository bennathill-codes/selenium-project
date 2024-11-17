package com.selenium.saucedemo.pages;

import com.selenium.saucedemo.utils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;

    private final By usernameField = TestUtils.byDataTestId("username");
    private final By passwordField = TestUtils.byDataTestId("password");
    public final By loginButton = TestUtils.byDataTestId("login-button");
    public final By errorMessage = TestUtils.byDataTestId("error");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // login helpers
    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
}
