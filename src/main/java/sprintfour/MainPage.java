package sprintfour;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.junit.Assert.assertTrue;

// локаторы главной страницы
public class MainPage {
    private final WebDriver driver;
    // кнопка cookie
    private final By buttonCookie = By.className("App_CookieButton__3cvqF");
    // верхняя кнопка Заказать
    private final By buttonOrderUp = By.xpath("//*[@id=\"root\"]/div/div[1]/div[1]/div[2]/button[1]");

    // идентификаторы вопросов FAQ
    private final String[] questionIds = {
            "accordion__heading-0",
            "accordion__heading-1",
            "accordion__heading-2",
            "accordion__heading-3",
            "accordion__heading-4",
            "accordion__heading-5",
            "accordion__heading-6",
            "accordion__heading-7"
    };

    // идентификаторы ответов FAQ
    private final String[] answerIds = {
            "accordion__panel-0",
            "accordion__panel-1",
            "accordion__panel-2",
            "accordion__panel-3",
            "accordion__panel-4",
            "accordion__panel-5",
            "accordion__panel-6",
            "accordion__panel-7"
    };

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openMainPage() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
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

    public void scrollAndClickQuestionFAQ(String elementId) {
        WebElement questionElement = driver.findElement(By.id(elementId));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", questionElement);
        questionElement.click();
    }

    public void clickAllQuestions() {
        for (String questionId : questionIds) {
            scrollAndClickQuestionFAQ(questionId);
        }
    }

    public void checkAllAnswers() {
        for (String answerId : answerIds) {
            By answerLocator = By.id(answerId);
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(answerLocator));
            assertTrue(driver.findElement(answerLocator).isDisplayed());
        }
    }

    public void checkQuestionAnswerMatch() {
        for (int i = 0; i < questionIds.length; i++) {
            scrollAndClickQuestionFAQ(questionIds[i]);
            By answerLocator = By.id(answerIds[i]);
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(answerLocator));
            assertTrue("Ответ на вопрос с ID: " + questionIds[i] + " не отображается.", driver.findElement(answerLocator).isDisplayed());
        }
    }
}