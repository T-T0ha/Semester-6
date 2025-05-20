package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BoardPage;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddBoardTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private BoardPage boardPage;

    @Before
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Increased timeout to 15 seconds
        driver.manage().window().setSize(new Dimension(862, 993)); // Initial window size for setup
        boardPage = new BoardPage(driver, wait);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void addNewBoardTest() {
        // Step 1: Open the application
        boardPage.open();

        // Step 2: Set window size (can be moved to setUp if fixed for all tests)
        driver.manage().window().setSize(new Dimension(823, 993));

        // Step 3: Click "Add New Board"
        boardPage.clickAddNewBoard();

        // Step 4: Type board name
        String boardName = "add";
        boardPage.enterBoardName(boardName);

        // Step 5: Click create button
        boardPage.clickCreateBoardButton();

        // Step 6: Click on the board title (to select it, as per original test)
        boardPage.clickBoardTitle();

        // Step 7: Double click on the board title
        boardPage.doubleClickBoardTitle();

        // Step 8: Assert the board title
        assertThat(boardPage.getBoardTitleText(), is(boardName));
    }
}