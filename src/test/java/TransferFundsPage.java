import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.*;


// TransferFundsPage class extending PageBase, representing the transfer funds page
public class TransferFundsPage extends PageBase {

    // Locators for different elements on the page
    private By titleLocator = By.xpath("//div[@id='showForm']/h1[@class='title']");
    private By amountLocator = By.xpath("//*[@id=\"amount\"]");
    private By fromAccountLocator = By.xpath("//*[@id=\"fromAccountId\"]");
    private By toAccountLocator = By.xpath("//*[@id=\"toAccountId\"]");
    private By transferButtonLocator = By.xpath("//*[@id=\"transferForm\"]/div[2]/input");
    private By transferResultLocator = By.xpath("//*[@id=\"showResult\"]/p[1]");
    
    // Constructor to initialize the driver
    public TransferFundsPage(WebDriver driver) {
        super(driver);        
    }

    // Method to get the transfer funds title
    public String getTransferFundsTitle() {
        WebElement titleElement = waitAndReturnElement(titleLocator);
        return titleElement.getText();
    }

    // Method to get the transfer funds result
    public String getTransferFundsResult() {
        WebElement resultElement = waitAndReturnElement(transferResultLocator);
        return resultElement.getText();
    }

    // Method to perform fund transfer action
    public void transferFunds (String amt) {
        WebElement amount = waitAndReturnElement(amountLocator);
        WebElement fromAccount = waitAndReturnElement(fromAccountLocator);
        WebElement toAccount = waitAndReturnElement(toAccountLocator);

        Select fromAccountSelect = new Select(fromAccount);
        Select toAccountSelect = new Select(toAccount);

        String fromAccountValue = fromAccountSelect.getFirstSelectedOption().getText();
        String toAccountValue = toAccountSelect.getFirstSelectedOption().getText();

        System.out.println("From Account: " + fromAccountValue);
        System.out.println("To Account: " + toAccountValue);

        amount.sendKeys(amt);
        WebElement transferButton = waitAndReturnElement(transferButtonLocator);
        transferButton.click();
    }

}    
