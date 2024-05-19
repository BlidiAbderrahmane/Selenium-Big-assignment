import org.openqa.selenium.*;

// SignUpPage class extending PageBase, representing the sign-up page
public class SignUpPage extends PageBase {
    
    // Locators for different elements on the page
    private By titleLocator = By.xpath("//*[@id=\"rightPanel\"]/h1");
    private By succesfulSignUpMsgLocator = By.xpath("//*[@id=\"rightPanel\"]/p");
    private By frirstNameFieldLocator = By.xpath("//*[@id=\"customer.firstName\"]");
    private By lastNameFieldLocator = By.xpath("//*[@id=\"customer.lastName\"]");
    private By adressFieldLocator = By.xpath("//*[@id=\"customer.address.street\"]");
    private By cityFieldLocator = By.xpath("//*[@id=\"customer.address.city\"]");
    private By stateFieldLocator = By.xpath("//*[@id=\"customer.address.state\"]");
    private By zipCodeFieldLocator = By.xpath("//*[@id=\"customer.address.zipCode\"]");
    private By phoneNumberFieldLocator = By.xpath("//*[@id=\"customer.phoneNumber\"]");
    private By SSNFieldLocator = By.xpath("//*[@id=\"customer.ssn\"]");
    private By usernameLocator = By.xpath("//*[@id=\"customer.username\"]");
    private By passwordLocator = By.xpath("//*[@id=\"customer.password\"]");
    private By repeatedPasswordLocator = By.xpath("//*[@id=\"repeatedPassword\"]");
    private By signUpButtonLocator = By.xpath("//*[@id=\"customerForm\"]/table/tbody/tr[13]/td[2]/input");

    // Constructor to initialize the driver
    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    // Method to get the title text of the page
    public String getTitleText() {
        WebElement titleElement = waitAndReturnElement(titleLocator);
        return titleElement.getText();
    }

    // Method to get the successful sign-up message
    public String getSuccesfulSignUpMsg() {
        WebElement succesfulSignUpMsgElement = waitAndReturnElement(succesfulSignUpMsgLocator);
        return succesfulSignUpMsgElement.getText();
    }

    // Method to perform sign-up action
    public MainPage signUp(String frirstName, String lastName, String adress, String city,
                        String state, String zipCode, String phoneNumber, String SSN,
                        String username, String password, String repeatedPassword) 
    {
        waitAndReturnElement(frirstNameFieldLocator).sendKeys(frirstName);
        waitAndReturnElement(lastNameFieldLocator).sendKeys(lastName);
        waitAndReturnElement(adressFieldLocator).sendKeys(adress);
        waitAndReturnElement(cityFieldLocator).sendKeys(city);
        waitAndReturnElement(stateFieldLocator).sendKeys(state);
        waitAndReturnElement(zipCodeFieldLocator).sendKeys(zipCode);
        waitAndReturnElement(phoneNumberFieldLocator).sendKeys(phoneNumber);
        waitAndReturnElement(SSNFieldLocator).sendKeys(SSN);
        waitAndReturnElement(usernameLocator).sendKeys(username);
        waitAndReturnElement(passwordLocator).sendKeys(password);
        waitAndReturnElement(repeatedPasswordLocator).sendKeys(repeatedPassword);

        WebElement signUpButton = waitAndReturnElement(signUpButtonLocator);
        signUpButton.click();

        return new MainPage(this.driver);
    }
}

