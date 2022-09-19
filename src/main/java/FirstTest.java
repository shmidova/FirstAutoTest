import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class FirstTest {

    String MOBIPAY_URL = "https://next.privat24.ua/mobile";

    @Test
    void MobiPay() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        By PhoneNum = By.xpath("//input[@data-qa-node='phone-number']");
        By Amount = By.xpath("//input[@data-qa-node='amount']");
        By CardNum = By.xpath("//input[@data-qa-node='numberdebitSource']");
        By ExpDate = By.xpath("//input[@data-qa-node='expiredebitSource']");
        By CvvCard = By.xpath("//input[@data-qa-node='cvvdebitSource']");
        By firstName = By.xpath("//input[@data-qa-node='firstNamedebitSource']");
        By lastName = By.xpath("//input[@data-qa-node='lastNamedebitSource']");
        By termsLink = By.xpath("//a[@href='https://privatbank.ua/terms']");
        By submitButton = By.xpath("//button[@data-qa-node=\"submit\"]");

        By expectedCard = By.xpath("//td[@data-qa-node='card']");
        By expectedAmount = By.xpath("//div[@data-qa-node='amount']");
        By expectedReciver = By.xpath("//span[@data-qa-node='nameB']");

        driver.get(MOBIPAY_URL);
        driver.findElement(PhoneNum).sendKeys("956124746");
        driver.findElement(CardNum).sendKeys("4567739561253907");
        driver.findElement(ExpDate).sendKeys("09/24");
        driver.findElement(CvvCard).sendKeys("528");
        driver.findElement(firstName).sendKeys("Test");
        driver.findElement(lastName).sendKeys("Shmidova");
        driver.findElement(termsLink).click();

        driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
        Assertions.assertEquals("Умови та правила", driver.getTitle());

        driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
        driver.findElement(submitButton).click();

        Assertions.assertEquals("4567 **** **** 3907", driver.findElement(expectedCard).getText());
        Assertions.assertEquals("120 UAH", driver.findElement(expectedAmount).getText());
        Assertions.assertEquals("Vodafone", driver.findElement(expectedReciver).getText());
    }


}
