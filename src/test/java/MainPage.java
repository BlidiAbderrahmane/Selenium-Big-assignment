import org.openqa.selenium.*;

// MainPage class extending PageBase, representing the main page
public class MainPage extends PageBase {
    
    // Locators for different elements on the page
    private By menuLocator = By.xpath("//*[@id=\"leftPanel\"]/ul");
    private By transferFundsLocator = By.xpath("//*[@id=\"leftPanel\"]/ul/li[3]/a");
    private By logoutButtonLocator = By.xpath("//*[@id=\"leftPanel\"]/ul/li[8]/a");
    private By titleLocator = By.xpath("//*[@id=\"showOverview\"]/h1");

    // Constructor to initialize the driver
    public MainPage(WebDriver driver) {
        super(driver);        
    }

    // Method to get the menu text
    public String getMenuText() {
        WebElement menuElement = waitAndReturnElement(menuLocator);
        return menuElement.getText();
    }

    // Method to get the title text of the page
    public String getTitleText() {
        WebElement titleElement = waitAndReturnElement(titleLocator);
        return titleElement.getText();
    }

    // Method to navigate to the Transfer Funds page
    public TransferFundsPage navigateToTransferFundsPage() {
        WebElement transferFundsButton = waitAndReturnElement(transferFundsLocator);
        transferFundsButton.click();
        return new TransferFundsPage(this.driver);
    }

    // Method to log out from the application
    public LoginPage logout() {
        WebElement logoutButton = waitAndReturnElement(logoutButtonLocator);
        logoutButton.click();
        return new LoginPage(this.driver);
    }
}