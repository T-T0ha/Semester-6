package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BoardPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private final By addNewBoardButton = By.id("add_new_board");
    private final By boardNameInput = By.id("board_name");
    private final By createBoardButton = By.cssSelector("button"); // Assuming this is the "Create Board" button
    private final By boardTitle = By.cssSelector("h3");

    public BoardPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void open() {
        driver.get("http://localhost:4000/");
    }

    public void clickAddNewBoard() {
        wait.until(ExpectedConditions.elementToBeClickable(addNewBoardButton)).click();
    }

    public void enterBoardName(String boardName) {
        WebElement boardNameInputField = wait.until(ExpectedConditions.visibilityOfElementLocated(boardNameInput));
        boardNameInputField.sendKeys(boardName);
    }

    public void clickCreateBoardButton() {
        wait.until(ExpectedConditions.elementToBeClickable(createBoardButton)).click();
    }

    public void clickBoardTitle() {
        wait.until(ExpectedConditions.elementToBeClickable(boardTitle)).click();
    }

    public void doubleClickBoardTitle() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(boardTitle));
        Actions builder = new Actions(driver);
        builder.doubleClick(element).perform();
    }

    public String getBoardTitleText() {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(boardTitle, "")); // Wait for text to appear
        return driver.findElement(boardTitle).getText();
    }
}