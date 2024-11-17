import com.selenium.saucedemo.pages.LoginPage;
import com.selenium.saucedemo.pages.ProductPage;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginPageTests {
    private static WebDriver driver;
    private static LoginPage loginPage;
    private static ProductPage productPage;

    @BeforeClass
    public static void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);

    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    @Test
    public void testLogin()  {
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();

        assertThat(productPage.getTitle()).contains("Products");
    }

    @Test
    public void testLogout() throws InterruptedException {
        productPage.clickMenu();
        // wait due to menu open animation
        Thread.sleep(1000);
        productPage.clickLogout();

        assertThat(loginPage.loginButton).isNotNull();
    }

    @Test
    public void testInvalidLogin() {
        loginPage.enterUsername("wrong_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();

        assertThat(loginPage.errorMessage).isNotNull();
    }
}
