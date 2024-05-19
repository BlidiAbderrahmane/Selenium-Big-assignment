import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;


// PageBase class representing the base page with common functionalities
public class PageBase {

    // Locators for common elements on the page
    protected By bodyLocator = By.tagName("body");
    private By footerLocator = By.xpath("//div[@id=\"footerPanel\"]");
    protected WebDriver driver;
    protected WebDriverWait wait;

    // Constructor to initialize the driver and wait
    public PageBase(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    // Method to wait for an element and return it
    protected WebElement waitAndReturnElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    }

    // Method to get the body text of the page
    protected String getBodyText() {
        WebElement bodyElement = waitAndReturnElement(bodyLocator);
        return bodyElement.getText();
    }

    // Method to get the footer text of the page
    public String getFooterText() {
        WebElement footerElement = waitAndReturnElement(footerLocator);
        return footerElement.getText();
    }
}