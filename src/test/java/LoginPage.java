import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

// LoginPage class extending PageBase, representing the login page
public class LoginPage extends PageBase {
    
    // Locators for different elements on the page
    private By titleLocator = By.xpath("//div[@id='mainPanel']//img[@title='ParaBank']");
    private By usernameLocator = By.name("username");
    private By passwordLocator = By.name("password");
    private By loginButtonLocator = By.xpath("//input[@class='button' and @value='Log In']");
    private By signUpButtonLocator = By.xpath("//*[@id=\"loginPanel\"]/p[2]/a");

    // Constructor to initialize the driver and open the URL
    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://parabank.parasoft.com/parabank/index.htm");
    }

    // Method to get the title text of the page
    public String getTitleText() {
        WebElement titleElement = waitAndReturnElement(titleLocator);
        return titleElement.getAttribute("title");
    }

    // Method to perform login action
    public MainPage login(String usrn, String psw) {
        WebElement username = waitAndReturnElement(usernameLocator);
        WebElement password = waitAndReturnElement(passwordLocator);
        username.sendKeys(usrn);
        password.sendKeys(psw);
        WebElement loginButton = waitAndReturnElement(loginButtonLocator);
        loginButton.click();
        return new MainPage(this.driver);
    }

    // Method to navigate to the sign-up page
    public SignUpPage signUp() {
        WebElement signUpButton = waitAndReturnElement(signUpButtonLocator);
        signUpButton.click();
        return new SignUpPage(this.driver);
    }

    // Method to hover over the title and get the text
    public String hoverOverTitleAndGetText() {
        WebElement titleElement = waitAndReturnElement(titleLocator);
        Actions actions = new Actions(driver);
        actions.moveToElement(titleElement).perform();
        return titleElement.getAttribute("title");
    }
}

