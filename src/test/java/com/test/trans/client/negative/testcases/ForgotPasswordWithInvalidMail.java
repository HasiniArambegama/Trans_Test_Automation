package com.test.trans.client.negative.testcases;

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

public class ForgotPasswordWithInvalidMail {
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
        emailField.sendKeys("ahjhd@gmail.com");
        Thread.sleep(200);

        // for confirm button
        WebElement confirmBtn = driver.findElement(By.xpath("//div[2]/div/div/div/div[2]/form/div[2]/div/button"));
        confirmBtn.click();
        Thread.sleep(500);

        // locate the form element
        WebElement errorMessage = driver.findElement(By.xpath("//div[2]/div/div/div/div[2]/form/div[1]/div/span/strong"));

        // get the text of the form element
        String actualMessage = errorMessage.getText();

        // Verify the error message is displayed
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message is not displayed");

        // Get the actual error message text and remove extra spaces
        String actualErrorMessage = errorMessage.getText();

        // Debugging: Print the actual error message text
        System.out.println("Actual Error Message: " + actualErrorMessage);

        // Verify the error message text
        String expectedErrorMessage = "We can't find a user with that e-mail address.";
        Assert.assertEquals(actualMessage, expectedErrorMessage, "Error message text does not match");

    }


    @AfterClass
    public void browserQuit() {
        if (driver != null) {
            driver.quit();
        }
    }


}
