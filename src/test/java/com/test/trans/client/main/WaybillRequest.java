package com.test.trans.client.main;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WaybillRequest {
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HASINI\\Desktop\\Automation Trans Express Project\\TransExpressTest\\src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://qa-1.transexpress.parallaxtec.dev/");
    }
    @Test(priority = 1)
    public void transClientLogin()throws InterruptedException{

        // scroll down the page to select client login button
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps = 10;
        long pauseTime = 500;
        int scrollAmount = 100;

        for(int i = 0; i < steps; i ++){
            js.executeScript("window.scrollBy(0, "+scrollAmount+");");
            Thread.sleep(pauseTime);
        }

        // login for client portal
        WebElement clientLoginBtn = driver.findElement(By.xpath("//div[1]/div/div/div/a[1]"));
        clientLoginBtn.click();

        // locate the form element
        WebElement formElement = driver.findElement(By.tagName("h5"));

        // get the text of the form element
        String actualFormTitle = formElement.getText();

        // define the expected form title
        String expectedFormTitle = "Login to your account";

        // assert for the form title
        Assert.assertEquals(actualFormTitle, expectedFormTitle, "Form title does not match expected title");

        // for email field
        WebElement email = driver.findElement(By.xpath("//*[@id=\"email\"]"));
        email.sendKeys("kelly@gmail.com");
        Thread.sleep(200);

        // for password field
        WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        password.sendKeys("12345678");
        Thread.sleep(200);

        //for login button
        WebElement loginBtn = driver.findElement(By.xpath("//*[@id=\"login_panel\"]/div[9]/button"));
        loginBtn.click();
    }

    @Test(priority = 2)
    public void navigateWaybill()throws InterruptedException{
        Thread.sleep(300);
        WebElement waybillNav = driver.findElement(By.xpath("/html/body/div[4]/div/div[1]/div/div[2]/div/ul/li[10]/a"));
        waybillNav.click();

        // locate the form element
        WebElement mainTitle = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[1]/div[1]/div"));

        // get the text of the form element
        String actualMainTitle = mainTitle.getText();

        // define the expected form title
        String expectedMainTitle = "Home - Client/ Waybill-Request";

        // assert for the form title
        Assert.assertEquals(actualMainTitle, expectedMainTitle, "Form title does not match expected title");
    }

    @Test(priority = 3)
    public void requestWaybill()throws InterruptedException{

        WebElement waybillAmount = driver.findElement(By.xpath("//*[@id=\"waybill_amount\"]"));
        waybillAmount.clear();
        Thread.sleep(100);
        waybillAmount.sendKeys("10");

        WebElement barcodeAmount = driver.findElement(By.xpath("//*[@id=\"barcode_amount\"]"));
        barcodeAmount.clear();
        Thread.sleep(100);
        barcodeAmount.sendKeys("10");

        WebElement submitBtn = driver.findElement(By.xpath("//*[@id=\"submit-button\"]"));
        submitBtn.click();

        // locate the success message
        WebElement successMessage = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[1]"));

        // verify the success message is displayed
        Assert.assertTrue(successMessage.isDisplayed(), "Success message is not displayed");

        // get the actual success message text and remove extra spaces
        String actualSuccessMessage = successMessage.getText();
        actualSuccessMessage = actualSuccessMessage.replace("×", "").replace("Close", "").trim();

        // print the actual error message text
        System.out.println("Actual Success Message: " + actualSuccessMessage);

        // verify the error message text
        String expectedSuccessMessage = "Waybill Request Successful.";
        System.out.println("Expected success message is: " +expectedSuccessMessage);

        // verify the error message text
        boolean isSuccessMessagePresent = actualSuccessMessage.contains(expectedSuccessMessage);
        Assert.assertTrue(isSuccessMessagePresent, "Success message text does not match");
    }

}
