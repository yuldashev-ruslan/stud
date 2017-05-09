package com.example.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class TestSelenium2 {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver", "drv/geckodriver.exe");
        driver = new FirefoxDriver();
        baseUrl = "https://ipizza.ru/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    // Сценарий №2 Отмена заказа
    @Test
    public void Test() throws Exception {

        //1. Перейти по ссылке https://ipizza.ru/
        driver.get("https://ipizza.ru/");
        //driver.manage().window().maximize();

        //Ожидание
        Wait<WebDriver> wait = new WebDriverWait(driver, 10, 1000);
        //Ссылка стала активной
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(), 'ПЯТНИЦА')]")));

        //2. Выбрать пиццу - Пятница
        driver.findElement(By.xpath("//*[contains(text(), 'ПЯТНИЦА')]")).click();
        //Ссылка стала активной
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='seccenterBtn btn left yelshdw']")));

        //3. Проверить, что открылась страница с заголовком - Пятница
        WebElement title = driver.findElement(By.xpath("//*/h1[@class='myRed']"));
        String expectedTitle = "ПЯТНИЦА";
        assertEquals(String.format("Заголовок равен [%s]. Ожидалось - [%s]", title.getText(), expectedTitle), expectedTitle, title.getText());

        //4. Нажать на кнопку - Заказать
        driver.findElement(By.xpath("//*[@class = 'seccenterBtn btn left yelshdw']/b")).click();

        driver.manage().window().maximize();

        //5. Нажать на кнопку Корзина
        driver.findElement(By.xpath(".//div[text()='КОРЗИНА']")).click();

        //Ссылка стала активной
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='actCart']/img")));

        //6. Удалить пиццу
        driver.findElement(By.xpath("//*[@class='actCart']/img")).click();

        //Ссылка стала активной
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='subtotal-sum']")));

        //7. Проверить, что Итого = 0 руб
        String actualValue = driver.findElement(By.xpath("//*[@class='subtotal-sum']")).getText();
        String expectedValue = "0 руб.";
        assertEquals(String.format("Поле заполнено не верно. Ожидалось [%s]. Получено [%s]", expectedValue, actualValue),
                expectedValue, actualValue);

        //8. Проверить запись - Минимальная сумма заказа - 500руб
        actualValue = driver.findElement(By.xpath("//*[@class='prepareMinsum']")).getText();
        expectedValue = "Минимальная сумма заказа: 500 руб.";
        assertEquals(String.format("Поле заполнено не верно. Ожидалось [%s]. Получено [%s]", expectedValue, actualValue),
                expectedValue, actualValue);

        //driver.close();

    }

        @After
    public void tearDown() throws Exception {
        //driver.wait();
        //driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
