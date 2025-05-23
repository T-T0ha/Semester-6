import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddNewListTest {
  private WebDriver driver;
  private WebDriverWait wait;
  private BoardDetailsPage boardDetailsPage; // Declare the page object

  @Before
  public void setUp() {
    WebDriverManager.firefoxdriver().setup();
    driver = new FirefoxDriver();
    wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    driver.manage().window().setSize(new Dimension(862, 993));

    // Initialize the page object
    boardDetailsPage = new BoardDetailsPage(driver, wait);
  }

  @After
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }

  @Test
  public void addNewList() {
    // Test name: AddNewList
    // Step 1: Open the specific board URL
    boardDetailsPage.openBoard("1-add"); // Assuming "4-add" is the dynamic part of the URL

    // Step 2: Set window size (can be moved to setUp if fixed for all tests)
    driver.manage().window().setSize(new Dimension(823, 993));

    // Step 3: Click the "Add a list..." button (or similar, identified by .inner)
    boardDetailsPage.clickAddListButton();

    // Step 4: Type the list name
    String listName = "Hellooo";
    boardDetailsPage.enterListName(listName);

    // Step 5: Press ENTER to submit the list name
    boardDetailsPage.submitListName();

    // Step 6: Assert the text of the newly added list title
    boardDetailsPage.assertListTitle(listName);
  }
}





//// Generated by Selenium IDE
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.junit.Test;
//import org.junit.Before;
//import org.junit.After;
//import static org.junit.Assert.*;
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.core.IsNot.not;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.remote.RemoteWebDriver;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.Dimension;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.Alert;
//import org.openqa.selenium.Keys;
//
//import java.time.Duration;




//import java.util.*;
//import java.net.MalformedURLException;
//import java.net.URL;
//
//public class AddNewListTest {
//  private WebDriver driver;
//  private WebDriverWait wait;
//  private Map<String, Object> vars;
//  JavascriptExecutor js;
//
//  @Before
//  public void setUp() {
//    WebDriverManager.firefoxdriver().setup(); // Optional: manages driver binaries
//    driver = new FirefoxDriver();
//    js = (JavascriptExecutor) driver;
//    wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//    vars = new HashMap<>();
//    driver.manage().window().setSize(new Dimension(862, 993));
//  }
//  @After
//  public void tearDown() {
//    driver.quit();
//  }
//
//  @Test
//  public void addNewList() {
//    // Test name: AddNewList
//    // Step # | name | target | value
//    // 1 | open | /boards/4-add |
//    driver.get("http://localhost:4000/boards/4-add");
//
//    // 2 | setWindowSize | 823x993 |
//    driver.manage().window().setSize(new Dimension(823, 993));
//
//    // 3 | click | css=.inner |
//    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".inner"))).click();
//
//    // 4 | type | id=list_name | Hellooo
//    WebElement listNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("list_name")));
//    listNameInput.sendKeys("Hellooo");
//
//    // 5 | sendKeys | id=list_name | ${KEY_ENTER}
//    listNameInput.sendKeys(Keys.ENTER);
//
//    // 6 | assertText | css=h4 | Hellooo
//    wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("h4"), "Hellooo"));
//    assertThat(driver.findElement(By.cssSelector("h4")).getText(), is("Hellooo"));
//  }
//}