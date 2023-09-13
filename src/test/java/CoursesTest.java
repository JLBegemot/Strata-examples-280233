import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import java.util.concurrent.TimeUnit;

public class CoursesTest extends BaseTest {

    @Test
    @DisplayName("Работа с Hover")
    public void testCourses() throws InterruptedException {

        driver.get("https://testpages.herokuapp.com/styled/csspseudo/css-hover.html");

        WebElement button = driver.findElement(By.xpath("//*[@id=\"hoverpara\"]"));

        Actions actions = new Actions(driver);

        actions.moveToElement(button);

        actions.build().perform();


        Assertions.assertTrue(driver.findElement(By.xpath("//*[@id=\"hoverparaeffect\"]")).isDisplayed());

        Thread.sleep(100);

        WebElement buttonDiv = driver.findElement(By.xpath("//*[@id=\"hoverdiv\"]"));

        Actions actionsDiv = new Actions(driver);

        actionsDiv.moveToElement(buttonDiv);

        actionsDiv.build().perform();

        WebElement buttonDivLink = driver.findElement(By.xpath("//*[@id=\"hoverlink\"]"));

        buttonDivLink.click();

        Thread.sleep(10000);
    }


    @Test
    @DisplayName("Работа со сложным списком переход по структуре Xpath")
    public void ListWorkByXpath() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/Users/lynevivan/IdeaProjects/telran/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");


        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://sky-todo-list.herokuapp.com/");

        // $x("//*[ text() = 'праолвд' ]/.././/*[contains(@data-icon,'trash')]")

        WebElement add = driver.findElement(By.xpath("//*[contains(@class,'button is-primary')]"));
        WebElement search = driver.findElement(By.xpath("//*[contains(@class,'input')]"));

        String taskTitle = "Задание";

        search.sendKeys(taskTitle);
        add.click();


        WebElement trashCanOfItem =
                driver.findElement(
                        By.xpath("//*[ text() = 'title' ]/.././/*[contains(@data-icon,'trash')]"
                                .replace("title", taskTitle)
                        ));
        trashCanOfItem.click();

        Thread.sleep(2000);

        Assertions.assertFalse(isElementPresent(By.xpath("//*[ text() = 'title']".replace("title", taskTitle))),
                "Элемент все еще в списке");

    }


    @Test
    @DisplayName("Пример работы с iframe")
    public void frameExample() throws InterruptedException {

        driver.get("https://testpages.eviltester.com/styled/iframes-test.html");

        WebElement frameElem = driver.findElement(By.id("thedynamichtml"));

        driver.switchTo().frame(frameElem);

        WebElement lastOneInTheFrame = driver.findElement(By.id("iframe59"));

        Actions actionScroll = new Actions(driver);

        actionScroll.scrollToElement(lastOneInTheFrame).perform();

        Thread.sleep(2000);
    }

    //Проверка того, что элемента не существует, стоит аккуратно использовать с Imlicity (не явным) ожиданием.
    public boolean isElementPresent(By locatorKey) {
        try {
            driver.findElement(locatorKey);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}