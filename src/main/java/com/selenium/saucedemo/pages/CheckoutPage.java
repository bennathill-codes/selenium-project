package com.selenium.saucedemo.pages;

import com.selenium.saucedemo.utils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    private final WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By checkoutPageTitle = TestUtils.byDataTestId("title");
    private final By firstNameField = TestUtils.byDataTestId("firstName");
    private final By lastNameField = TestUtils.byDataTestId("lastName");
    private final By postalCodeField = TestUtils.byDataTestId("postalCode");
    private final By continueButton = TestUtils.byDataTestId("continue");
    private final By cancelButton = TestUtils.byDataTestId("cancel");
    private final By finishButton = TestUtils.byDataTestId("finish");
    private final By errorMessage = TestUtils.byDataTestId("error");

    // checkout page helper methods
    public String getTitle() {
        return driver.findElement(checkoutPageTitle).getText();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    public void enterFirstName(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    public void enterPostalCode(String zip) {
        driver.findElement(postalCodeField).sendKeys(zip);
    }

    public void clickContinueButton() {
        driver.findElement(continueButton).click();
    }

    public void clickCancelButton() {
        driver.findElement(cancelButton).click();
    }

    public void clickFinishButton() {
        driver.findElement(finishButton).click();
    }
}
