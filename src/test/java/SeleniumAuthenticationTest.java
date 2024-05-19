import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.net.MalformedURLException;
import org.junit.*;


public class SeleniumAuthenticationTest {

    // WebDriver instance for controlling the browser
    private WebDriver driver;

    // Setup method to initialize the WebDriver and browser settings before each test
    @Before
    public void setup() throws MalformedURLException {
        // Setting up Chrome browser options
        ChromeOptions options = new ChromeOptions();
        
        // Initialize the RemoteWebDriver with the Selenium Grid URL and options
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        
        // Maximize the browser window
        this.driver.manage().window().maximize();    
    }

    // Test method to verify the sign-up functionality
    @Test
    public void testSignUp() {
        // Initialize the login page and navigate to the sign-up page
        LoginPage loginPage = new LoginPage(this.driver);
        SignUpPage signUpPage = loginPage.signUp();
        
        // Print the title of the sign-up page
        System.out.println(signUpPage.getTitleText());
        
        // Assert that the title contains "Signing up"
        assertTrue(signUpPage.getTitleText().contains("Signing up"));
        
        // Perform the sign-up action with provided user details
        signUpPage.signUp("Abderrahmane", "Blidi", "Ulloi ut", "Budapest",
                    "Hungary", "1091", "1122233544", "123456789",
                    "Abderrahmane__4", "qwerty123", "qwerty123");

        // Assert that the success message is displayed after sign-up
        assertEquals("Your account was created successfully. You are now logged in.", signUpPage.getSuccesfulSignUpMsg());
    }

    // Test method to verify the login functionality
    @Test
    public void testLogin() {
        // Initialize the login page and navigate to the sign-up page
        LoginPage loginPage = new LoginPage(this.driver);
        SignUpPage signUpPage = loginPage.signUp();
        
        // Perform the sign-up action with provided user details
        MainPage mainPage = signUpPage.signUp("Abderrahmane", "Blidi", "Ulloi ut", "Budapest",
                    "Hungary", "1091", "1122233544", "123456789",
                    "Abderrahmane__5", "qwerty123", "qwerty123");

        // Logout and assert that the user is returned to the login page
        LoginPage returnloginPage = mainPage.logout();
        assertEquals("ParaBank", returnloginPage.getTitleText());

        // Perform login action and assert that the user is on the main page
        MainPage mainPage2 = returnloginPage.login("Abderrahmane__5", "qwerty123");
        assertTrue(mainPage2.getBodyText().contains("Accounts Overview"));
    }

    // Test method to verify the logout functionality
    @Test
    public void testLogout() {
        // Initialize the login page and navigate to the sign-up page
        LoginPage loginPage = new LoginPage(this.driver);
        SignUpPage signUpPage = loginPage.signUp();
        
        // Perform the sign-up action with provided user details
        MainPage mainPage = signUpPage.signUp("Abderrahmane", "Blidi", "Ulloi ut", "Budapest",
                    "Hungary", "1091", "1122233544", "123456789",
                    "Abderrahmane__6", "qwerty123", "qwerty123");

        // Logout and assert that the user is returned to the login page
        LoginPage returnloginPage = mainPage.logout();
        assertEquals("ParaBank", returnloginPage.getTitleText());
    }

    // Close method to clean up after each test
    @After
    public void close() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }
}
