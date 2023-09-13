import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static java.lang.Thread.sleep;

public class JSTests extends BaseTest {

    WebDriver driver;

    @Test
    @DisplayName("Работа с JS Executor по нажатию кнопок")
    public void loginTest() throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/");

        JavascriptExecutor jsExec = (JavascriptExecutor) driver;

        driver.findElement(By.name("username")).sendKeys("Admin");
        //jsExec.executeScript("document.getElementById('username').value='Admin'");

        driver.findElement(By.name("password")).sendKeys("admin123");

        WebElement button = driver.findElement(By.xpath("//*[contains(@type,'submit')]"));
        jsExec.executeScript("arguments[0].click();", button);

        sleep(2000);
    }

    @Test
    @DisplayName("Получение свойств сайта")
    public void getPagePropertiesTest() {
        driver.get("https://testpages.eviltester.com/styled/iframes-test.html");

        JavascriptExecutor jsExec = (JavascriptExecutor) driver;

        String DomainName = jsExec.executeScript("return document.domain;").toString();
        System.out.println(DomainName);

        String DomainURL = jsExec.executeScript("return document.URL;").toString();
        System.out.println(DomainURL);

        jsExec.executeScript("window.location = 'https://testpages.eviltester.com/styled/index.html'");
    }
}
