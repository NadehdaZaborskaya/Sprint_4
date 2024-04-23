package ru.yandex.praktikum.plain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.page.MainPage;
import ru.yandex.praktikum.page.OrderPage;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)

public class CreateOrderTest extends BaseTest{

    public MainPage objMainPage;
    public OrderPage objOrderPage;
    private final int indexButton;
    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;
    private final String dateOrder;
    private final String period;
    private final String color;
    private final String comment;

    public CreateOrderTest(int indexButton, String name, String surname, String address, String metro, String phone, String dateOrder, String period, String color, String comment) {
        this.indexButton = indexButton;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.dateOrder = dateOrder;
        this.period = period;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters(name = "Оформление заказа: " +
            "Способ вызова: {0}; " +
            "Имя: {1}; " +
            "Фамилия: {2}; " +
            "Адрес: {3}; " +
            "Метро: {4}; " +
            "Телефон: {5}; " +
            "Когда нужен: {6}; " +
            "Срок аренды: {7}; " +
            "Цвет: {8}; " +
            "Комментарий: {9}")
    public static Object[][] getTestData() {
        return new Object[][] {
                {0, "Петр", "Иванов", "Москва", "Выставочная", "88005553535", "01.01.2025", "трое суток", "grey", "оставить у двери"},
                {1, "Василий", "Сидоров", "Москва", "Беговая", "+79005553535", "01.05.2024", "сутки", "black", "осторожно злая собака"}
        };
    }

    //Проверка сценария создания заказа
    @Test
    public void createOrder() {
        objMainPage = new MainPage(webDriver);
        objMainPage.waitForLoadPage();
        objMainPage.clickGetCookie();
        objMainPage.clickOrder(indexButton);
        objOrderPage = new OrderPage(webDriver);
        objOrderPage.waitForLoadOrderPage();
        objOrderPage.setDataFieldsAndClickNext(name, surname, address, metro, phone);
        objOrderPage.waitForLoadRentPage();
        objOrderPage.setOtherFieldsAndClickOrder(dateOrder, period, color, comment);

        assertTrue("Отсутствует сообщение об успешном завершении заказа", objMainPage.isElementExist(objOrderPage.orderPlaced));
    }

}
