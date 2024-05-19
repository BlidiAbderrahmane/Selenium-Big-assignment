import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.net.MalformedURLException;
import org.junit.*;

public class SeleniumWebsiteContentTest {

    // WebDriver instance to control the browser
    private WebDriver driver;

    // Setup method to initialize the WebDriver and browser settings before each test
    @Before
    public void setup() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        // WebDriver configurations
        // Disable extensions
        options.addArguments("--disable-extensions");

        // Disable notifications
        options.addArguments("--disable-notifications");

        // Disable info bars
        options.addArguments("--disable-infobars");

        // Set download directory
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("download.default_directory", "/path/to/download/directory");
        options.setExperimentalOption("prefs", prefs);

        // Initialize the RemoteWebDriver with the Selenium Grid URL and options
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        // Maximize the browser window
        this.driver.manage().window().maximize();    
    }

    // Test method to verify the body text of the login page
    @Test
    public void testGetPageBody() {
        LoginPage loginPage = new LoginPage(this.driver);
        System.out.println(loginPage.getBodyText());
    }

    // Test method to verify the title attribute of the login page
    @Test
    public void testGetTitleAttribute() {
        LoginPage loginPage = new LoginPage(this.driver);
        String title = loginPage.getTitleText();
        System.out.println("Title attribute value: " + title);
        assertEquals("ParaBank", title);
    }

    // Test method to verify the main page after signing up
    // note : the website doesn't register the created account for too long
    //        so we have to sign up when testing every functionality. 
    @Test
    public void testMainPage() {
        LoginPage loginPage = new LoginPage(this.driver);
        SignUpPage signUpPage = loginPage.signUp();
        MainPage mainPage = signUpPage.signUp("Abderrahmane", "Blidi", "Ulloi ut", "Budapest",
                    "Hungary", "1091", "1122233544", "123456789",
                    "Abderrahmane__1", "qwerty123", "qwerty123");

        // Verify the presence of expected menu items on the main page
        assertTrue(mainPage.getMenuText().contains("Open New Account") && 
           mainPage.getMenuText().contains("Transfer Funds") && 
           mainPage.getMenuText().contains("Bill Pay")&& 
           mainPage.getMenuText().contains("Find Transactions")&& 
           mainPage.getMenuText().contains("Update Contact Info")&& 
           mainPage.getMenuText().contains("Request Loan")&& 
           mainPage.getMenuText().contains("Log Out"));
    }

    // Test method to verify the transfer funds functionality
    @Test
    public void testTransferFunds() {
        LoginPage loginPage = new LoginPage(this.driver);
        SignUpPage signUpPage = loginPage.signUp();
        MainPage mainPage = signUpPage.signUp("Abderrahmane", "Blidi", "Ulloi ut", "Budapest",
                    "Hungary", "1091", "1122233544", "123456789",
                    "Abderrahmane__2", "qwerty123", "qwerty123");
        TransferFundsPage transferFundsPage = mainPage.navigateToTransferFundsPage();
        
        // Verify the title of the transfer funds page
        assertTrue(transferFundsPage.getTransferFundsTitle().contains("Transfer Funds"));
        
        // Perform a fund transfer and verify the result
        transferFundsPage.transferFunds("123456");
        assertTrue(transferFundsPage.getTransferFundsResult().contains("has been transferred"));
    }
    
    // Test method to verify hovering over the title element
    @Test
    public void testHoverOverTitle() {
        LoginPage loginPage = new LoginPage(this.driver);
        String titleText = loginPage.hoverOverTitleAndGetText();
        System.out.println("Hovered title text: " + titleText);
        assertEquals("ParaBank", titleText);
    }

    // Test method to verify browser back button functionality
    @Test
    public void testBrowserBackButton() {
        // Navigate to the login page
        LoginPage loginPage = new LoginPage(this.driver);
        
        // Click the signup button to go to the signup page
        SignUpPage signUpPage = loginPage.signUp();
        
        // Verify we are on the Signup Page
        assertTrue(signUpPage.getTitleText().contains("Signing up"));
        
        // Go back to the previous page
        driver.navigate().back();
        
        // Verify that the browser is back on the login page
        assertEquals("ParaBank", loginPage.getTitleText());
    }

    // Test method to verify cookie manipulation
    @Test
    public void testCookieManipulation() {
        // Navigate to the login page
        new LoginPage(this.driver);

        // Add a custom cookie to the browser session
        Cookie customCookie = new Cookie("test_cookie", "cookie_value");
        driver.manage().addCookie(customCookie);

        // Refresh the page to apply the cookie
        driver.navigate().refresh();

        // Retrieve the cookie from the browser session
        Cookie retrievedCookie = driver.manage().getCookieNamed("test_cookie");

        // Verify the cookie's presence and value
        assertEquals("cookie_value", retrievedCookie.getValue());
    }

    // Close method to clean up after each test
    @After
    public void close() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }
}
