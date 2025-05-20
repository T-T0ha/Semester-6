import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SignUpPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void open() {
        driver.get("http://localhost:4000/sign_in");
    }

    public void clickCreateAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Create new account"))).click();
    }

    public void enterFirstName(String firstName) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user_first_name")));
        field.sendKeys(firstName);
        field.sendKeys(Keys.ENTER);
    }

    public void enterLastName(String lastName) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user_last_name")));
        field.sendKeys(lastName);
        field.sendKeys(Keys.ENTER);
    }

    public void enterEmail(String email) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user_email")));
        field.sendKeys(email);
        field.sendKeys(Keys.ENTER);
    }

    public void correctEmail(String email) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user_email")));
        field.clear();
        field.sendKeys(email);
        field.sendKeys(Keys.ENTER);
    }

    public void enterPassword(String password) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user_password")));
        field.sendKeys(password);
        field.sendKeys(Keys.ENTER);
    }

    public void enterConfirmPassword(String password) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user_password_confirmation")));
        field.sendKeys(password);
        field.sendKeys(Keys.ENTER);
    }

    public String getDisplayedName() {
        WebElement nameSpan = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span:nth-child(3)")));
        return nameSpan.getText();
    }
}
