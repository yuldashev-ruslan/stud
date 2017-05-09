package com.example.tests;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestSelenium {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver", "drv/geckodriver.exe");
        driver = new ChromeDriver();
        baseUrl = "https://ipizza.ru/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    // Сценарий №1 Заказ пиццы
    @Test
    public void test() throws Exception {

        //1. Перейти по ссылке https://ipizza.ru/
        driver.get("https://ipizza.ru/");
        //driver.manage().window().maximize();

        //Ожидание
        Wait<WebDriver> wait = new WebDriverWait(driver, 7, 1000);
        //Ссылка стала активной
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(), 'МОРСКАЯ')]")));

        //2. Выбрать пиццу - Морская
        driver.findElement(By.xpath("//*[contains(text(), 'МОРСКАЯ')]")).click();
        //Ссылка стала активной
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class = 'seccenterBtn btn left yelshdw']")));

        //3. Проверить, что открылась страница с заголовком - Морская
        WebElement title = driver.findElement(By.xpath("//h1[@class='myRed']"));
        String expectedTitle = "МОРСКАЯ";
        assertEquals(String.format("Заголовок равен [%s]. Ожидалось - [%s]", title.getText(), expectedTitle), expectedTitle, title.getText());

        //4. Нажать на кнопку - Заказать
        driver.findElement(By.xpath("//*[@class='seccenterBtn btn left yelshdw']")).click();

        //5. Запомнить значение Итого
        String total = driver.findElement(By.xpath("//div[contains(text(),'итого')]/b")).getText();

        //6. Нажать на кнопку Корзина
        driver.findElement(By.xpath("//*[@class = 'seccenterBtn btn left yelshdw']")).click();

        //7. Проверить, что открыта страница с заголовком - Вы заказали
        String elementTitle = driver.findElement(By.xpath("//*[@class='maybeAds']")).getText();
        assertTrue(String.format("Заголовок равен [%s]. Ожидалось - Вы заказали", elementTitle), elementTitle.contains("Вы заказали"));

        //8. Проверить что Итого равно сохраненному значению
        String totalSumm = driver.findElement(By.xpath("//*[@class='subtotal-sum']")).getText();
        assertTrue(String.format("Значение ИТОГО равно [%s]. Ожидалось - [%s]", totalSumm, total),
                totalSumm.equals(total));

        //9. Нажать на кнопку - Оформить
        driver.findElement(By.xpath("//*[@class='contBtn']")).click();

        //Ссылка стала активной
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class = 'seccenterBtn btn left yelshdw']")));

        //10. Проверить наличие заголовка - Информация о доставке
        title = driver.findElement(By.xpath("//div[contains(@class,'mainForm')]//h2"));
        expectedTitle = "ИНФОРМАЦИЯ О ДОСТАВКЕ";
        assertEquals(String.format("Заголовок равен [%s]. Ожидалось - [%s]", title.getText(), expectedTitle), expectedTitle, title.getText());

        //11. Заполнить поля - Ваше имя
        //        Телефон
        //        Улица
        //        Дом
        //        Корпус
        //        Строение
        //        Подъезд
        //        Этаж
        driver.findElement(By.xpath(".//*[@id='custumerName']")).clear();
        driver.findElement(By.xpath(".//*[@id='custumerName']")).sendKeys("Лев");

        driver.findElement(By.xpath(".//*[@id='custumerPhone']")).clear();
        driver.findElement(By.xpath(".//*[@id='custumerPhone']")).sendKeys("495-123-12-23");

        driver.findElement(By.xpath(".//*[@id='custumerStreet']")).clear();
        driver.findElement(By.xpath(".//*[@id='custumerStreet']")).sendKeys("1-ая линия");

        driver.findElement(By.xpath(".//*[@id='custumerHome']")).clear();
        driver.findElement(By.xpath(".//*[@id='custumerHome']")).sendKeys("17");

        driver.findElement(By.xpath(".//*[@name='custumer[building]']")).clear();
        driver.findElement(By.xpath(".//*[@name='custumer[building]']")).sendKeys("3");

        driver.findElement(By.xpath(".//*[@name='custumer[structure]']")).clear();
        driver.findElement(By.xpath(".//*[@name='custumer[structure]']")).sendKeys("1");

        driver.findElement(By.xpath(".//*[@id='custumerPorch']")).clear();
        driver.findElement(By.xpath(".//*[@id='custumerPorch']")).sendKeys("2");

        driver.findElement(By.xpath(".//*[@id='custumerFloor']")).clear();
        driver.findElement(By.xpath(".//*[@id='custumerFloor']")).sendKeys("8");

        //12. Проверить, что все поля заполнились, соответвующими значениями
        String actualValue = driver.findElement(By.xpath(".//*[@id='custumerName']")).getAttribute("value");
        String expectedValue = "Лев";
        assertEquals(String.format("Поле заполнено не верно. Ожидалось [%s]. Получено [%s]", expectedValue, actualValue),
                expectedValue, actualValue);

        actualValue = driver.findElement(By.xpath(".//*[@id='custumerPhone']")).getAttribute("value");
        expectedValue = "+7 (495) 123-12-23";
        assertEquals(String.format("Поле заполнено не верно. Ожидалось [%s]. Получено [%s]", expectedValue, actualValue),
                expectedValue, actualValue);

        actualValue = driver.findElement(By.xpath(".//*[@id='custumerStreet']")).getAttribute("value");
        expectedValue = "1-ая линия";
        assertEquals(String.format("Поле заполнено не верно. Ожидалось [%s]. Получено [%s]", expectedValue, actualValue),
                expectedValue, actualValue);

        actualValue = driver.findElement(By.xpath(".//*[@id='custumerHome']")).getAttribute("value");
        expectedValue = "17";
        assertEquals(String.format("Поле заполнено не верно. Ожидалось [%s]. Получено [%s]", expectedValue, actualValue),
                expectedValue, actualValue);

        actualValue = driver.findElement(By.xpath(".//*[@name='custumer[building]']")).getAttribute("value");
        expectedValue = "3";
        assertEquals(String.format("Поле заполнено не верно. Ожидалось [%s]. Получено [%s]", expectedValue, actualValue),
                expectedValue, actualValue);

        actualValue = driver.findElement(By.xpath(".//*[@name='custumer[structure]']")).getAttribute("value");
        expectedValue = "1";
        assertEquals(String.format("Поле заполнено не верно. Ожидалось [%s]. Получено [%s]", expectedValue, actualValue),
                expectedValue, actualValue);

        actualValue = driver.findElement(By.xpath(".//*[@id='custumerPorch']")).getAttribute("value");
        expectedValue = "2";
        assertEquals(String.format("Поле заполнено не верно. Ожидалось [%s]. Получено [%s]", expectedValue, actualValue),
                expectedValue, actualValue);

        actualValue = driver.findElement(By.xpath(".//*[@id='custumerFloor']")).getAttribute("value");
        expectedValue = "8";
        assertEquals(String.format("Поле заполнено не верно. Ожидалось [%s]. Получено [%s]", expectedValue, actualValue),
                expectedValue, actualValue);

        driver.close();

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
