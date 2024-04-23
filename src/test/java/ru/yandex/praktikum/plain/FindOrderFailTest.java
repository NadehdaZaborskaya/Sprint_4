package ru.yandex.praktikum.plain;

import org.junit.Test;
import ru.yandex.praktikum.page.MainPage;
import static org.junit.Assert.assertTrue;

public class FindOrderFailTest extends BaseTest {

    //Проверка сценария поиска несущ.заказа (необязательное задание)
    @Test
    public void orderNotFound() {

        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickOrderStatusButton();
        mainPage.enterOrderNumber("Вы кто такие я вас не звал 123");
        mainPage.clickGoButton();
        assertTrue(mainPage.notFoundImageIsDisplayed());
    }

}
