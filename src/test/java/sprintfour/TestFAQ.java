package sprintfour;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.junit.Rule;


@RunWith(Parameterized.class)
public class TestFAQ {
    private final String questionId;
    private final String answerId;

    public TestFAQ(String questionId, String answerId) {
        this.questionId = questionId;
        this.answerId = answerId;
    }

    @Parameterized.Parameters
    public static Object[][] getFAQData() {
        return new Object[][]{
                {"accordion__heading-0", "accordion__panel-0"},
                {"accordion__heading-1", "accordion__panel-1"},
                {"accordion__heading-2", "accordion__panel-2"},
                {"accordion__heading-3", "accordion__panel-3"},
                {"accordion__heading-4", "accordion__panel-4"},
                {"accordion__heading-5", "accordion__panel-5"},
                {"accordion__heading-6", "accordion__panel-6"},
                {"accordion__heading-7", "accordion__panel-7"}
        };
    }

    @Rule
    public DriverRule driverRule = new DriverRule();

    @Test
    public void checkFAQ() {
        WebDriver driver = driverRule.getDriver();
        var mainPage = new MainPage(driver);
        mainPage.clickCookie();
        mainPage.checkFAQQuestion(questionId, answerId); // Используем параметры из конструктора
    }
}