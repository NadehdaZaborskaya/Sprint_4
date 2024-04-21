package ru.yandex.praktikum.plain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.page.MainPage;
import static org.junit.Assert.assertTrue;

public class FindOrderFailTest {
    private WebDriver webDriver;


    @Before
    public void setup() {
        webDriver = new ChromeDriver();
        //  webDriver = new FirefoxDriver();
        webDriver.get("https://qa-scooter.praktikum-services.ru/");
    }

    //Проверка сценария поиска несущ.заказа (необязательное задание)
    @Test
    public void orderNotFound() {

        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickOrderStatusButton();
        mainPage.enterOrderNumber("Вы кто такие я вас не звал 123");
        mainPage.clickGoButton();
        assertTrue(mainPage.notFoundImageIsDisplayed());
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }

}
