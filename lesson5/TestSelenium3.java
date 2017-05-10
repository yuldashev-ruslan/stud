package com.example.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestSelenium3 {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver", "drv/geckodriver.exe");
        driver = new FirefoxDriver();
        baseUrl = "http://www.rgs.ru";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void Test() throws Exception {

        //1. Перейти по ссылке http://www.rgs.ru
        driver.get("http://www.rgs.ru");

        //Ожидание
        Wait<WebDriver> wait = new WebDriverWait(driver, 7, 1000);
        //Ссылка стала активной
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(), 'Страхование')]")));

        //2. Выбрать пункт меню - Страхование
        driver.findElement(By.xpath("//*[@class='dropdown adv-analytics-navigation-line1-link current']")).click();

        //Ссылка стала активной
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@href='http://www.rgs.ru/products/private_person/health/dms/generalinfo/index.wbp']")));

        //3. Выбрать категорию - ДМС
        driver.findElement(By.xpath("//*[@href='http://www.rgs.ru/products/private_person/health/dms/generalinfo/index.wbp']")).click();

        //4. Проверить наличие заголовка - Добровольное медицинское страхование
        WebElement title = driver.findElement(By.xpath("//*[@class='h1']"));
        String expectedTitle = "Добровольное медицинское страхование";
        assertEquals(String.format("Заголовок равен [%s]. Ожидалось - [%s]", title.getText(), expectedTitle), expectedTitle, title.getText());

        //Ссылка стала активной
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='rgs-main-context-bar']/div/div/div/a[1]")));

        //5. Нажать на кнопку - Отправить заявку
        driver.findElement(By.xpath("//*[@id='rgs-main-context-bar']/div/div/div/a[1]")).click();

        //6. Проверить, что открылась страница платежа с заголовоком - заявка на добровольное медицинское страхование
        title = driver.findElement(By.xpath("//*[@class='modal-title']/b"));
        expectedTitle = "Заявка на добровольное медицинское страхование";
        assertEquals(String.format("Заголовок равен [%s]. Ожидалось - [%s]", title.getText(), expectedTitle), expectedTitle, title.getText());

        //7.Заполнить поля
        //        Имя
        //        Фамилия
        //        Отчество
        //        Регион
        //        Телефон
        //        Эл. почта - qwertyqwerty
        //        Дата контакта
        //        Комментарии
        driver.findElement(By.xpath("//*[@name='LastName']")).clear();
        driver.findElement(By.xpath("//*[@name='LastName']")).sendKeys("Иванов");

        driver.findElement(By.xpath("//*[@name='FirstName']")).clear();
        driver.findElement(By.xpath("//*[@name='FirstName']")).sendKeys("Иван");

        driver.findElement(By.xpath("//*[@name='MiddleName']")).clear();
        driver.findElement(By.xpath("//*[@name='MiddleName']")).sendKeys("Иванович");

        Select select = new Select(driver.findElement(By.tagName("//*[@name='Region']")));
        select.selectByVisibleText("Москва");

        driver.findElement(By.xpath("//*[@id='applicationForm']/div[2]/div[5]/input")).clear();
        driver.findElement(By.xpath("//*[@id='applicationForm']/div[2]/div[5]/input")).sendKeys("999 999-99-99");

        driver.findElement(By.xpath("//*[@name='Email']")).clear();
        driver.findElement(By.xpath("//*[@name='Email']")).sendKeys("qwertyqwerty");

        driver.findElement(By.xpath("//*[@name='ContactDate']")).clear();
        driver.findElement(By.xpath("//*[@name='ContactDate']")).sendKeys("07.06.2018");

        driver.findElement(By.xpath("//*[@name='Comment']")).clear();
        driver.findElement(By.xpath("//*[@name='Comment']")).sendKeys("Тест");

        //8. Проверить, что все поля заполнены введенными значениями
        String actualValue = driver.findElement(By.xpath("//*[@name='LastName']")).getAttribute("value");
        String expectedValue = "Иванов";
        assertEquals(String.format("Поле заполнено не верно. Ожидалось [%s]. Получено [%s]", expectedValue, actualValue),
                expectedValue, actualValue);

        actualValue = driver.findElement(By.xpath("//*[@name='FirstName']")).getAttribute("value");
        expectedValue = "Иван";
        assertEquals(String.format("Поле заполнено не верно. Ожидалось [%s]. Получено [%s]", expectedValue, actualValue),
                expectedValue, actualValue);

        actualValue = driver.findElement(By.xpath("//*[@name='MiddleName']")).getAttribute("value");
        expectedValue = "Иванович";
        assertEquals(String.format("Поле заполнено не верно. Ожидалось [%s]. Получено [%s]", expectedValue, actualValue),
                expectedValue, actualValue);

        actualValue = driver.findElement(By.xpath("//*[@name='Region']")).getAttribute("value");
        expectedValue = "Москва";
        assertEquals(String.format("Поле заполнено не верно. Ожидалось [%s]. Получено [%s]", expectedValue, actualValue),
                expectedValue, actualValue);

        actualValue = driver.findElement(By.xpath("//*[@id='applicationForm']/div[2]/div[5]/input")).getAttribute("value");
        expectedValue = "999 999-99-99";
        assertEquals(String.format("Поле заполнено не верно. Ожидалось [%s]. Получено [%s]", expectedValue, actualValue),
                expectedValue, actualValue);

        actualValue = driver.findElement(By.xpath("//*[@name='Email']")).getAttribute("value");
        expectedValue = "qwertyqwerty";
        assertEquals(String.format("Поле заполнено не верно. Ожидалось [%s]. Получено [%s]", expectedValue, actualValue),
                expectedValue, actualValue);

        actualValue = driver.findElement(By.xpath("//*[@name='ContactDate']")).getAttribute("value");
        expectedValue = "99.99.9999";
        assertEquals(String.format("Поле заполнено не верно. Ожидалось [%s]. Получено [%s]", expectedValue, actualValue),
                expectedValue, actualValue);

        actualValue = driver.findElement(By.xpath("//*[@name='Comment']")).getAttribute("value");
        expectedValue = "Тест";
        assertEquals(String.format("Поле заполнено не верно. Ожидалось [%s]. Получено [%s]", expectedValue, actualValue),
                expectedValue, actualValue);

        //9. Проверить, что у Поля - Эл. почта присутствует сообщение об ошибке - Введите корректный email
        actualValue = driver.findElement(By.xpath("//*[contains(text(), 'Введите корректный email')]")).getAttribute("value");
        expectedValue = "Введите корректный email";
        assertEquals(String.format("Поле заполнено не верно. Ожидалось [%s]. Получено [%s]", expectedValue, actualValue),
                expectedValue, actualValue);

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
