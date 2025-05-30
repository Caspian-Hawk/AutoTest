package sprintfour;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// локаторы формы Для кого самокат
public class FormAboutUser {
    private final WebDriver driver;
    // поле Имя
    private final By fieldName = By.cssSelector("input[placeholder='* Имя']");
    // поле Фамилия
    private final By fieldSurname = By.cssSelector("input[placeholder='* Фамилия']");
    // поле Адрес
    private final By fieldAddress = By.cssSelector("input[placeholder='* Адрес: куда привезти заказ']");
    // поле Станция
    private final By fieldStation = By.className("select-search__input");
    // выпадающий список станций
    private final By listStation = By.className("select-search__select");
    // поле Телефон
    private final By fieldTelephone = By.cssSelector("input[placeholder='* Телефон: на него позвонит курьер']");
    // кнопка Далее формы Для кого самокат
    private final By buttonNext = By.className("Button_Middle__1CSJM");

    public FormAboutUser(WebDriver driver) {
        this.driver = driver;
    }

    public void inputFieldName(String name) {
        driver.findElement(fieldName).sendKeys(name);
    }

    public void inputFieldSurname(String surname) {
        driver.findElement(fieldSurname).sendKeys(surname);
    }

    public void inputFieldAddress(String address) {
        driver.findElement(fieldAddress).sendKeys(address);
    }

    public void selectStation() {
        driver.findElement(fieldStation).click();
        driver.findElement(listStation).click();
    }

    public void inputFieldTelephone(String telephone) {
        driver.findElement(fieldTelephone).sendKeys(telephone);
    }

    public void clickButtonNext() {
        driver.findElement(buttonNext).click();
    }
}