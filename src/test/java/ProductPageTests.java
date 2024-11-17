import com.selenium.saucedemo.pages.LoginPage;
import com.selenium.saucedemo.pages.ProductPage;
import com.selenium.saucedemo.utils.TestUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductPageTests {
    private static WebDriver driver;
    private static LoginPage loginPage;
    private static ProductPage productPage;

    @BeforeClass
    public static void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        // poms
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);

        // user login
        TestUtils.login(driver);
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    @Test
    public void testAddRemoveProducts() {
        productPage.addAllProductsToCart();
        assertThat(productPage.shoppingCartBadgeDisplayed()).isTrue();
        assertThat(productPage.getShoppingCartBadge()).contains("6");

        productPage.removeAllProductsFromCart();
        assertThat(productPage.shoppingCartBadgeDisplayed()).isFalse();
    }

    @Test
    public void testSortProducts() {
        productPage.selectSortOption("hilo");
        WebElement hiloSortedList = driver.findElement(productPage.productList);
        assertThat(productPage.getProductFromList(hiloSortedList, 0)).contains("Sauce Labs Fleece Jacket");

        productPage.selectSortOption("lohi");
        WebElement lohiSortedList = driver.findElement(productPage.productList);
        assertThat(productPage.getProductFromList(lohiSortedList, 0)).contains("Sauce Labs Onesie");

        productPage.selectSortOption("za");
        WebElement zaSortedList = driver.findElement(productPage.productList);
        assertThat(productPage.getProductFromList(zaSortedList, 0)).contains("T-Shirt (Red)");

        productPage.selectSortOption("az");
        WebElement azSortedList = driver.findElement(productPage.productList);
        assertThat(productPage.getProductFromList(azSortedList, 0)).contains("Sauce Labs Backpack");
    }
}
