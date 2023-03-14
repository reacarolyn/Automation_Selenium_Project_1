package org.reacarolyn;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.net.MalformedURLException;

import static java.lang.Thread.sleep;
import static org.testng.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    WebDriver driver;
    String baseUrl = "https://app.vwo.com";

    @BeforeMethod
    public void setup(Method m, ITestContext ctx) throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
        options.addArguments("--remote-allow-origins=*");
        options.setHeadless(false);
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver();
        driver.get(baseUrl);
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void testValidLogin() throws InterruptedException {
        WebElement email = driver.findElement(By.id("login-username"));
        WebElement password = driver.findElement(By.id("login-password"));
        WebElement signButton = driver.findElement(By.id("js-login-btn"));
        sleep(2000);
        email.sendKeys("93npu2yyb0@esiix.com");
        password.sendKeys("Wingify@123");
        sleep(2000);
        signButton.click();

        sleep(2000);
        WebElement confirmLogin = driver.findElement(By.id("js-side-navigator"));
        // assert that the Dashboard page is displayed
        Assert.assertTrue(confirmLogin.isDisplayed());


    }

    @Test(priority = 2)
    public void testInvalidLogInWithInvalidUsername() throws InterruptedException {
        WebElement email = driver.findElement(By.id("login-username"));
        WebElement password = driver.findElement(By.id("login-password"));
        WebElement signButton = driver.findElement(By.id("js-login-btn"));
        sleep(2000);
        email.sendKeys("hello@esiix.com");
        password.sendKeys("Wingify@123");
        sleep(2000);
        signButton.click();
        sleep(2000);
        WebElement errorMessage = driver.findElement(By.id("js-notification-box-msg"));
        String expectedErrorMessage = "Your email, password, IP address or location did not match";
        String actualErrorMessage = errorMessage.getText();
        assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    @Test(priority = 3)
    public void testInvalidLogInWithInvalidPassword() throws InterruptedException {
        WebElement email = driver.findElement(By.id("login-username"));
        WebElement password = driver.findElement(By.id("login-password"));
        WebElement signButton = driver.findElement(By.id("js-login-btn"));
        sleep(2000);
        email.sendKeys("hello@esiix.com");
        password.sendKeys("Wingify@1234567890");
        sleep(2000);
        signButton.click();
        sleep(2000);
        WebElement errorMessage = driver.findElement(By.id("js-notification-box-msg"));
        String expectedErrorMessage = "Your email, password, IP address or location did not match";
        String actualErrorMessage = errorMessage.getText();
        assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    @Test(priority = 4)
    public void testEmptyUsername() throws InterruptedException {
        WebElement email = driver.findElement(By.id("login-username"));
        WebElement password = driver.findElement(By.id("login-password"));
        WebElement signButton = driver.findElement(By.id("js-login-btn"));
        sleep(2000);
        email.sendKeys("");
        password.sendKeys("Wingify@123");
        sleep(2000);
        signButton.click();
        sleep(2000);
        WebElement errorMessage = driver.findElement(By.id("js-notification-box-msg"));
        String expectedErrorMessage = "Your email, password, IP address or location did not match";
        String actualErrorMessage = errorMessage.getText();
        assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    @Test(priority = 5)
    public void testEmptyPassword() throws InterruptedException {
        WebElement email = driver.findElement(By.id("login-username"));
        WebElement password = driver.findElement(By.id("login-password"));
        WebElement signButton = driver.findElement(By.id("js-login-btn"));
        sleep(2000);
        email.sendKeys("93npu2yyb0@esiix.com");
        password.sendKeys("");
        sleep(2000);
        signButton.click();
        sleep(2000);
        WebElement errorMessage = driver.findElement(By.id("js-notification-box-msg"));
        String expectedErrorMessage = "Your email, password, IP address or location did not match";
        String actualErrorMessage = errorMessage.getText();
        assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
