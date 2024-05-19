import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class SeleniumMultiplePageTest {
    // WebDriver instance to control the browser
    private WebDriver driver;

    // Setup method to initialize the WebDriver and browser settings before each test
    @Before
    public void setup() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        // WebDriver configuration: enabling headless mode for running tests without a GUI
        options.addArguments("--headless");
        
        // Initialize the RemoteWebDriver with the Selenium Grid URL and options
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        // Maximize the browser window
        driver.manage().window().maximize();
    }

    // Test method to navigate through multiple pages and perform common tests
    @Test
    public void testMultiplePages() {
        // Initialize the login page and navigate to the sign-up page
        LoginPage loginPage = new LoginPage(this.driver);
        SignUpPage signUpPage = loginPage.signUp();
        
        // Sign up with the provided user details
        signUpPage.signUp("Abderrahmane", "Blidi", "Ulloi ut", "Budapest",
                "Hungary", "1091", "1122233544", "123456789",
                "Abderrahmane__3", "qwerty123", "qwerty123");

        // List of URLs to visit and test
        List<String> urls = Arrays.asList(
                "https://parabank.parasoft.com/parabank/billpay.htm",
                "https://parabank.parasoft.com/parabank/findtrans.htm"
        );

        // Loop through each URL, navigate to it, and perform common tests
        for (String url : urls) {
            driver.get(url);
            performCommonTests();
        }
    }

    // Method to perform common tests on the pages
    private void performCommonTests() {
        // Find the element with class name 'title' and verify it's displayed
        WebElement billpayTitle = driver.findElement(By.className("title"));
        assertTrue("Title in billpayForm is not displayed", billpayTitle.isDisplayed());

        // Find another element with class name 'title' and verify it's displayed
        WebElement formContainerTitle = driver.findElement(By.className("title"));
        assertTrue("Title in formContainer is not displayed", formContainerTitle.isDisplayed());
    }

    // Close method to clean up after each test
    @After
    public void close() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }
}