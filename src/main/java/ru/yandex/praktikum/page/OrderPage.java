package ru.yandex.praktikum.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    private final WebDriver webDriver;
    // задаем локаторы
    private final By orderHeader = By.xpath("//div[(text()= 'Для кого самокат')]");
    private final By rentHeader = By.xpath("//div[(text()= 'Про аренду')]");
    private final By inputName = By.xpath("//input[@placeholder ='* Имя']");
    private final By inputSurname = By.xpath("//input[@placeholder ='* Фамилия']");
    private final By inputAddress = By.xpath("//input[@placeholder ='* Адрес: куда привезти заказ']");
    private final By inputPhone = By.xpath("//input[@placeholder ='* Телефон: на него позвонит курьер']");
    private final By inputMetro = By.xpath("//input[@placeholder ='* Станция метро']");
    private final By inputCalendar = By.xpath("//input[@placeholder ='* Когда привезти самокат']");
    private final By inputPeriod = By.className("Dropdown-placeholder");
    private final By InputComment = By.xpath("//input[@placeholder ='Комментарий для курьера']");
    private final By buttonNext = By.xpath(".//div[starts-with(@class,'Order_NextButton')]//button[contains(text(), 'Далее')]");
    private final By buttonOrder = By.xpath(".//div[starts-with(@class,'Order_Buttons')]//button[contains(text(), 'Заказать')]");
    private final By buttonYes = By.xpath("//button[contains(text(), 'Да')]");
    public By orderPlaced = By.xpath("//div[(text()= 'Заказ оформлен')]");

    public OrderPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void waitForLoadOrderPage() {
        new WebDriverWait(webDriver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(getTitleOrder()));
    }

    public void setDataFieldsAndClickNext(String valueName, String valueSurname, String valueAddress,
                                          String valueMetro, String valuePhone) {
        getName().sendKeys(valueName);
        getSurname().sendKeys(valueSurname);
        getAddress().sendKeys(valueAddress);
        getMetro().sendKeys(valueMetro, Keys.ARROW_DOWN, Keys.ENTER);
        getPhoneNumber().sendKeys(valuePhone);
        getButtonNext().click();
    }

    public void waitForLoadRentPage() {
        new WebDriverWait(webDriver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(getTitleRent()));
    }

    public void setOtherFieldsAndClickOrder(String valueDateOrder, String valuePeriod, String valueColor, String valueComment) {
        getCalendar().sendKeys(valueDateOrder, Keys.ARROW_DOWN, Keys.ENTER);
        getPeriod().click();
        getDays(valuePeriod).click();
        getColor(valueColor).click();
        getComment().sendKeys(valueComment);
        getButtonOrder().click();
        new WebDriverWait(webDriver, Duration.ofSeconds(3)).until(driver -> (getButtonYes().isDisplayed()));
        getButtonYes().click();
    }

    public By getTitleOrder() {
        return orderHeader;
    }

    public By getTitleRent() {
        return rentHeader;
    }

    public WebElement getName() {
        return webDriver.findElement(inputName);
    }

    public WebElement getSurname() {
        return webDriver.findElement(inputSurname);
    }

    public WebElement getAddress() {
        return webDriver.findElement(inputAddress);
    }

    public WebElement getPhoneNumber() {
        return webDriver.findElement(inputPhone);
    }

    public WebElement getMetro() {
        return webDriver.findElement(inputMetro);
    }

    public WebElement getCalendar() {
        return webDriver.findElement(inputCalendar);
    }

    public WebElement getPeriod() {
        return webDriver.findElement(inputPeriod);
    }

    public WebElement getDays(String valueDays) {
        return webDriver.findElement(By.xpath(".//div[@class='Dropdown-menu']/div[text()='"+valueDays+"']"));
    }

    public WebElement getColor(String colorName) {
        return webDriver.findElement(By.id(colorName));
    }

    public WebElement getComment() {
        return webDriver.findElement(InputComment);
    }

    public WebElement getButtonNext() {
        return webDriver.findElement(buttonNext);
    }

    public WebElement getButtonOrder() {
        return webDriver.findElement(buttonOrder);
    }

    public WebElement getButtonYes() {
        return webDriver.findElement(buttonYes);
    }

}
