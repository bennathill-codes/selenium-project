import com.selenium.saucedemo.pages.LoginPage;
import com.selenium.saucedemo.pages.ProductPage;
import com.selenium.saucedemo.utils.TestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginPageTests {
    private static WebDriver driver;
    private static LoginPage loginPage;
    private static ProductPage productPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        // poms
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
    }

    @After
    public void tearDown() {
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
        TestUtils.login(driver);
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
