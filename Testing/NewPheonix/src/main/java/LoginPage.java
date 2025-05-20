import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void open() {
        driver.get("http://localhost:4000/sign_in");
    }

    public void clickSignInButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button"))).click();
    }

    public void clickUserLink(String name) {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText(name))).click();
    }

    public void clickViewContainer() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".view-container"))).click();
    }

    public String getDisplayedName() {
        WebElement nameSpan = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span:nth-child(3)")));
        return nameSpan.getText();
    }
}
