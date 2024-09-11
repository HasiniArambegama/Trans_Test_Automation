package com.test.trans.staff.positive.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class StaffForgotPasswordWithValidMail {
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HASINI\\Desktop\\Automation Trans Express Project\\TransExpressTest\\src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://qa2.transexpress.parallaxtec.dev");

    }

    @Test(priority = 1)
    public void staffForgotPasswordWithValidEmail()throws InterruptedException{

        // scroll down the page to select client login button
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps = 03;
        long pauseTime = 100;
        int scrollAmount = 50;

        for(int i = 0; i < steps; i ++){
            js.executeScript("window.scrollBy(0, "+scrollAmount+");");
            Thread.sleep(pauseTime);
        }

        // login for client portal
        WebElement staffLoginBtn = driver.findElement(By.xpath("//div[1]/div/div/div/a[2]"));
        staffLoginBtn.click();

        WebElement forgotPwd = driver.findElement(By.xpath("//div[1]/div/div[2]/div/div[2]/form/div/div/div[8]/div/div[2]/a"));
        forgotPwd.click();

        // locate the password rest form title
        WebElement formTitle = driver.findElement(By.xpath("//div[2]/div/div/div/div[1]"));

        String actualTitle = formTitle.getText();

        // define the expected form title
        String expectedTitle = ("Staff Reset Password");

        // assert for the form title
        Assert.assertEquals(actualTitle, expectedTitle, "Form title does not match expected title");

        // to print actual form title
        System.out.println("Form title is displayed as expected: " + expectedTitle);

        // to enter staff valid email
        WebElement emailField = driver.findElement(By.xpath("//div[2]/div/div/div/div[2]/form/div[1]/div/input"));
        emailField.sendKeys("prasadika.l@parallax.lk");
        Thread.sleep(200);

        // for confirm button
        WebElement confirmBtn = driver.findElement(By.xpath("//div[2]/div/div/div/div[2]/form/div[2]/div/button"));
        confirmBtn.click();

        // locate the success message
        WebElement successMessage = driver.findElement(By.xpath("//div[2]/div/div/div/div[2]/div"));

        // Verify the success message is displayed
        Assert.assertTrue(successMessage.isDisplayed(), "Success message is not displayed");

        // Get the actual success message text
        String actualSuccessMessage = successMessage.getText();

        // print the actual success message text
        System.out.println("Actual Success Message: " + actualSuccessMessage);

        // Verify the error message text
        String expectedSuccessMessage = "We have e-mailed your password reset link!";

        // Verify the error message text
        boolean isSuccessMessagePresent = actualSuccessMessage.contains(expectedSuccessMessage);
        Assert.assertTrue(isSuccessMessagePresent, "Error message text does not match");

    }

}
