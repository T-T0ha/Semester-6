import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat; // Import Hamcrest's assertThat
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;
public class BoardDetailsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private final By addListButton = By.cssSelector(".inner"); // This locator might need to be more specific if there are other .inner elements
    private final By listNameInput = By.id("list_name");
    private final By listTitle = By.cssSelector("h4"); // Locator for the newly added list's title

    public BoardDetailsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void openBoard(String boardId) {
        driver.get("http://localhost:4000/boards/" + boardId);
    }

    public void clickAddListButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addListButton)).click();
    }

    public void enterListName(String listName) {
        WebElement listNameInputField = wait.until(ExpectedConditions.visibilityOfElementLocated(listNameInput));
        listNameInputField.sendKeys(listName);
    }

    public void submitListName() {
        driver.findElement(listNameInput).sendKeys(Keys.ENTER);
    }

    public String getListTitleText() {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(listTitle, "")); // Wait for text to appear
        return driver.findElement(listTitle).getText();
    }

    public void assertListTitle(String expectedTitle) {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(listTitle, expectedTitle));
        assertThat(getListTitleText(), is(expectedTitle));
    }
}
