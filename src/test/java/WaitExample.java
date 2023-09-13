import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class WaitExample {

    WebDriver driver;

    @Test
    @DisplayName("Варианты ожиданий")
    public void example() {
        System.setProperty("webdriver.chrome.driver", "/Users/lynevivan/IdeaProjects/telran/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        /* Implicity Wait (неявные ожидания), лучше использовать, когда у вас простая страничка, без подгрузки каких-то элементов */


        driver.get("https://testpages.herokuapp.com/styled/csspseudo/css-hover.html");

        Wait<WebDriver> wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait1.until(ExpectedConditions.invisibilityOfElementLocated(By.id("")));

        Wait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(10));
        /*
        Explicit waits (явные ожидания) во всех остальных случаях
         */

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(2))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(ElementNotInteractableException.class);

        wait1.until(ExpectedConditions.invisibilityOfElementLocated(By.id("")));
    }

    //Как можно реализовать ожидание, если чего-то не хватает в селениум
    private void waitTabsCount(int tabsCount) throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            if (driver.getWindowHandles().size() != tabsCount) {
                break;
            }
            Thread.sleep(100);
        }
    }
}
