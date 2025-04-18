package sprintfour;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class MainPage {
    private final WebDriver driver;

    // Локаторы главной страницы
    private final By buttonCookie = By.className("App_CookieButton__3cvqF");
    private final By buttonOrderUp = By.xpath(".//div[@class='Header_Header__214zg']//button[@class='Button_Button__ra12g']");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCookie() {
        driver.findElement(buttonCookie).click();
    }

    public void clickButtonOrderUp() {
        driver.findElement(buttonOrderUp).click();
    }

    public void clickButtonOrderDown() {
        WebElement buttonOrderDown = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[4]/div[2]/div[5]/button"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", buttonOrderDown);
        buttonOrderDown.click();
    }

    public void checkFAQQuestion(String questionId, String answerId) {
        WebElement questionFAQ = driver.findElement(By.id(questionId));
        WebElement answerFAQ = driver.findElement(By.id(answerId));
        // скроллим и ждем появления вопроса
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", questionFAQ);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(questionFAQ));
        questionFAQ.click();
        // ждем раскрытия ответа
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(answerFAQ));
        assertTrue("Ответ не отображается для вопроса: " + questionId, answerFAQ.isDisplayed());
    }
}