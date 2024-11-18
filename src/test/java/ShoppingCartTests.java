import com.selenium.saucedemo.pages.LoginPage;
import com.selenium.saucedemo.pages.ProductPage;
import com.selenium.saucedemo.pages.ShoppingCartPage;
import com.selenium.saucedemo.utils.TestUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class ShoppingCartTests {
    private static WebDriver driver;
    private static LoginPage loginPage;
    private static ProductPage productPage;
    private static ShoppingCartPage shoppingCartPage;

    @BeforeClass
    public static void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        // poms
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);

        TestUtils.login(driver);
        productPage.addAllProductsToCart();
        productPage.clickShoppingCartLink();
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    @Test
    public void testContinueShopping() {
        shoppingCartPage.clickContinueShoppingButton();
        assertThat(productPage.getTitle()).contains("Products");
        productPage.clickShoppingCartLink();
        assertThat(shoppingCartPage.getTitle()).contains("Your Cart");
    }
}
