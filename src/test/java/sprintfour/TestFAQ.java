package sprintfour;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class TestFAQ {
    @Rule
    public DriverRule driverRule = new DriverRule();

    @Test
    public void checkQuestionFAQ() {
        WebDriver driver = driverRule.getDriver();
        var mainPage = new MainPage(driver);

        // Открываем страницу
        mainPage.openMainPage();

        // Нажимаем куки
        mainPage.clickCookie();

        // Проверяем соответствие вопросов и ответов
        mainPage.checkQuestionAnswerMatch();
    }
}