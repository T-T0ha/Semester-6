import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddMemberPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private final By plusButton = By.cssSelector(".fa-plus");
    private final By memberEmailField = By.id("crawljax_member_email");
    private final By submitButton = By.cssSelector("button");
    private final By secondMemberInList = By.cssSelector("li:nth-child(2) > .react-gravatar");

    public AddMemberPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Page Actions
    public void open() {
        driver.get("http://localhost:4000/boards/2-add");
    }

    public void clickPlusButton() {
        WebElement plusBtn = wait.until(ExpectedConditions.elementToBeClickable(plusButton));
        plusBtn.click();
    }

    public void enterMemberEmail(String email) {
        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(memberEmailField));
        emailField.sendKeys(email);
    }

    public void clickSubmitButton() {
        WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submitBtn.click();
    }

    public boolean isMemberAddedSuccessfully() {
        WebElement memberElement = wait.until(ExpectedConditions.presenceOfElementLocated(secondMemberInList));
        return memberElement.isDisplayed();
    }
}