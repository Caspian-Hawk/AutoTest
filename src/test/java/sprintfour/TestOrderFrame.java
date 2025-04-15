package sprintfour;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

@RunWith(Parameterized.class)
public class TestOrderFrame { // флоу верхней кнопки "Заказать"

    private final String name;
    private final String surname;
    private final String address;
    private final String telephone;
    private final String buttonType;

    public TestOrderFrame(String name, String surname, String address, String telephone, String buttonType) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.telephone = telephone;
        this.buttonType = buttonType;
    }

    @Parameterized.Parameters
    public static Object[][] userData() {
        return new Object[][] {
                {"Роман", "Карасев", "Калининская", "89001112233", "up"},
                {"Алексей", "Петров", "Ленина", "89001234567", "down"},
        };
    }

    @Rule
    public DriverRule driverRule = new DriverRule();

    @Test
    public void checkButtonOrderUp() {
        WebDriver driver = driverRule.getDriver();
        var mainPage = new MainPage(driver);
        var formAboutUser = new FormAboutUser(driver);
        var formAboutRent = new FormAboutRent(driver);

        // открываем страницу
        mainPage.openMainPage();
        // принимаем куки
        mainPage.clickCookie(); // может это лишнее...

        if (buttonType.equals("up")) {
            mainPage.clickButtonOrderUp();
        } else {
            mainPage.clickButtonOrderDown();
        }

        // форма "Для кого самокат"
        // заполняем поле Имя
        formAboutUser.inputFieldName(name);
        // заполняем поле Фамилия
        formAboutUser.inputFieldSurname(surname);
        // заполняем поле Адрес
        formAboutUser.inputFieldAddress(address);
        // выбираем станцию
        formAboutUser.selectStation();
        // заполняем поле Телефон
        formAboutUser.inputFieldTelephone(telephone);
        // кликаем по кнопке Далее
        formAboutUser.clickButtonNext();

        // форма "Про аренду"

        // заполняем поле Дата
        formAboutRent.inputFieldDate();
        // заполняем поле Срок аренды
        formAboutRent.inputFieldRent();
        // кликаем по кнопке Заказать
        formAboutRent.clickButtonOrderForm();

        // диалоговое окно Хотите оформить заказ?

        // нажимаем на кнопку Да
        formAboutRent.clickButtonYes();

        formAboutRent.checkOrderComplete();
    }
}
