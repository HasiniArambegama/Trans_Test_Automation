package com.test.trans.client.positive.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ForgotPassword {
    private WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HASINI\\Desktop\\Automation Trans Express Project\\TransExpressTest\\src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://qa2.transexpress.parallaxtec.dev");
    }

    @Test(priority = 1)
    public void forgotPasswordOption() throws InterruptedException {

        // scroll down the page to select client login button
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps = 03;
        long pauseTime = 100;
        int scrollAmount = 50;

        for (int i = 0; i < steps; i++) {
            js.executeScript("window.scrollBy(0, " + scrollAmount + ");");
            Thread.sleep(pauseTime);
        }

        // login for client portal
        WebElement clientLoginBtn = driver.findElement(By.xpath("//div[1]/div/div/div/a[1]"));
        clientLoginBtn.click();

        WebElement forgotPwd = driver.findElement(By.xpath("//div[1]/div/div[2]/div/div[2]/form/div/div/div[8]/div/div[2]/a"));
        forgotPwd.click();

        // for pwd reset option field
        WebElement emailField = driver.findElement(By.xpath("//div[2]/div/div/div/div[2]/form/div[1]/div/input"));
        emailField.sendKeys("hasini.a@parallaxtec.com");
        Thread.sleep(200);

        // for confirm button
        WebElement confirmBtn = driver.findElement(By.xpath("//div[2]/div/div/div/div[2]/form/div[2]/div/button"));
        confirmBtn.click();

        // locate the form element
        WebElement formElement = driver.findElement(By.xpath("//div[2]/div/div/div/div[2]/div"));

        // get the text of the form element
        String actualMessage = formElement.getText();

        // define the expected success message
        String expectedMessage = "We have e-mailed your password reset link!";

        // assert for the form title
        if(expectedMessage != actualMessage){
            Assert.assertEquals(actualMessage, expectedMessage, "Success message does not match as expected");
        }else if (expectedMessage == actualMessage) {
            Assert.assertEquals(actualMessage, expectedMessage, "We have e-mailed your password reset link!");
        }



    }


    @AfterClass
    public void browserQuit() {
        if (driver != null) {
            driver.quit();
        }
    }




}