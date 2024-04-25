package ru.yandex.praktikum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;
import java.util.List;

public class MainPage {

    private final WebDriver webDriver;
    //Переменная для ссылки на главную страницу
    public static final String URL = "https://qa-scooter.praktikum-services.ru/";

    //локаторы для создания заказа на главной странице
    private final By imageScooter = By.xpath(".//img[@alt = 'Scooter blueprint']");
    private final By buttonAcceptCookie = By.id("rcc-confirm-button");
    private final By buttonOrderTop = By.xpath(".//div[starts-with(@class,'Header_Nav')]//button[text()='Заказать']"); // локатор кнопки заказа верхней
    private final By buttonOrderBottom = By.xpath(".//div[contains(@class,'FinishButton')]//button[text()='Заказать']"); // локатор кнопки заказа нижней

    //создаем локаторы для сценария FAQ
    private final By sectionFaq = By.xpath(".//div[starts-with(@class,'Home_FAQ')]"); // секция Вопросы о важном
    private final By accordionItem = By.className("accordion__item"); // элемент секции
    private final By accordionButton = By.className("accordion__button"); // кнопка с вопросом
    private final By accordionPanel = By.className("accordion__panel"); // панель с ответом

    //создаем локаторы для сценария поиска несуществующего заказа (допзадание)
    private final By orderStatusLocator = By.xpath("//button[text()='Статус заказа']"); //локатор кнопки Статус заказа
    private final By orderNumberInputLocator = By.xpath("//input[@placeholder='Введите номер заказа']"); //локатор поля Введите номер заказа
    private final By goButtonLocator = By.xpath("//button[text()='Go!']"); //локатор кнопки Go!
    private final By notFoundImageLocator = By.xpath("//img[@alt='Not found']"); //локатор картинки Не найдено заказов


    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    //методы тестирования создания заказа
    //ожидание главного окна
    public void waitForLoadPage() {
        WebElement imageElement = webDriver.findElement(imageScooter);
        new WebDriverWait(webDriver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(imageScooter));
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].scrollIntoView();", imageElement);
    }

    // Метод Проверки наличия кнопки
    public boolean isElementExist(By locatorBy) {
        try {
            webDriver.findElement(locatorBy);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
    //Принять куки
    public void clickGetCookie() {
        if (isElementExist(buttonAcceptCookie))
            webDriver.findElement(buttonAcceptCookie).click();
    }
    // Создание Нового заказа
    public void clickOrder(int indexButton) {
        switch (indexButton) {
            case 0:
                webDriver.findElement(buttonOrderTop).click();
                break;
            case 1:
                JavascriptExecutor js = (JavascriptExecutor) webDriver;
                js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
                WebElement buttonOrder = webDriver.findElement(buttonOrderBottom);
                new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(driver -> (buttonOrder.isDisplayed()));
                buttonOrder.click();
                break;
        }
    }

    //Методы тестирования страницы FAQ

    // ждем пока прогрузятся вопросы о важном
    public void waitForLoadFaq() {
        WebElement faqElement = webDriver.findElement(sectionFaq);
        new WebDriverWait(webDriver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(sectionFaq));
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].scrollIntoView();", faqElement);

    }
    // возвращает список всех вопросов-ответов
    public List<WebElement> getFaqItems(){
        return webDriver.findElements(accordionItem);
    }

    public boolean isButtonClickable(WebElement faqElement) {
        try {
            new WebDriverWait(webDriver, Duration.ofSeconds(2)).until(ExpectedConditions.elementToBeClickable(faqElement.findElement(accordionButton)));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public String getQuestion(WebElement faqElement) {
        return faqElement.findElement(accordionButton).getText();
    }

    public String getAnswer(WebElement faqElement) {
        return faqElement.findElement(accordionPanel).getText();
    }

    //Методы Сценария поиска несущ.заказа (доп.задание)

    //кнопка Статус заказа
    public void clickOrderStatusButton() {
        WebElement orderStatusButton = webDriver.findElement(orderStatusLocator);
        orderStatusButton.click();
    }

    //Поле ввода номера заказа
    public void enterOrderNumber(String orderNumber) {
        WebElement orderInput = webDriver.findElement(orderNumberInputLocator);
        orderInput.sendKeys(orderNumber);
    }

    //Кнопка Go!
    public void clickGoButton() {
        WebElement goButton = webDriver.findElement(goButtonLocator);
        new WebDriverWait(webDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(goButton));
        goButton.click();
    }

    //Такого заказа нет
    public boolean notFoundImageIsDisplayed() {
        return webDriver.findElement(notFoundImageLocator).isDisplayed();
    }





}
