import com.selenium.saucedemo.pages.CheckoutPage;
import com.selenium.saucedemo.pages.LoginPage;
import com.selenium.saucedemo.pages.ProductPage;
import com.selenium.saucedemo.pages.ShoppingCartPage;
import com.selenium.saucedemo.utils.TestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckoutPageTests {
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

        // navigate to checkout page
        TestUtils.login(driver);
        productPage.addAllProductsToCart();
        productPage.clickShoppingCartLink();
        shoppingCartPage.clickCheckoutButton();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testCheckoutContinue() {
        assertThat(checkoutPage.getTitle()).contains("Checkout");
        checkoutPage.enterFirstName("Coco");
        checkoutPage.enterLastName("Bean");
        checkoutPage.enterPostalCode("12345");
        checkoutPage.clickContinueButton();

        WebElement overviewPageTitle = driver.findElement(TestUtils.byDataTestId("title"));
        assertThat(overviewPageTitle.getText()).contains("Overview");
    }

    @Test
    public void testCheckoutCancel() {
        assertThat(checkoutPage.getTitle()).contains("Checkout");
        checkoutPage.clickCancelButton();
        assertThat(shoppingCartPage.getTitle()).contains("Cart");
    }

    @Test
    public void testCheckoutErrors() {
        checkoutPage.clickContinueButton();
        assertThat(checkoutPage.getErrorMessage()).contains("Error: First Name is required");
        checkoutPage.enterFirstName("Coco");
        checkoutPage.clickContinueButton();
        assertThat(checkoutPage.getErrorMessage()).contains("Error: Last Name is required");
        checkoutPage.enterLastName("Bean");
        checkoutPage.clickContinueButton();
        assertThat(checkoutPage.getErrorMessage()).contains("Error: Postal Code is required");
    }
}
