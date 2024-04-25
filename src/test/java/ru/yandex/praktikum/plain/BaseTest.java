package ru.yandex.praktikum.plain;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.page.MainPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseTest {
    
    protected WebDriver webDriver;


    @Before
    public void setup() {
        webDriver = new ChromeDriver();
        // webDriver = new FirefoxDriver();
        webDriver.get(MainPage.URL);
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }
}        
