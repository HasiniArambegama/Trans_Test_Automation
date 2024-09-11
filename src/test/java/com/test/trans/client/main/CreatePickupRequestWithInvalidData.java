package com.test.trans.client.main;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreatePickupRequestWithInvalidData {
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HASINI\\Desktop\\Automation Trans Express Project\\TransExpressTest\\src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://release.transexpress.parallaxtec.dev/");
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
    public void navigateCreatePickupRequests(){

        driver.get("https://release.transexpress.parallaxtec.dev/pickup-requests-client");
        // locate the form element
        WebElement formTitle = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/form/div/div[1]/h5"));

        // get the text of the form element
        String actualFormTitle = formTitle.getText();

        // define the expected form title
        String expectedFormTitle = "Pickup Request";

        System.out.println("Expected form title is: " + expectedFormTitle);

        // assert for the form title
        Assert.assertEquals(actualFormTitle, expectedFormTitle, "Form title does not match expected form title");

        // to print form title
        System.out.println("Actual form title is :" +actualFormTitle +"."+ " Therefore, actual form title is displayed as expected.");
    }

    @Test(priority = 3)
    public void verifyAlertMessages()throws InterruptedException{

        // initialize SoftAssert
        SoftAssert softAssert = new SoftAssert();

        // wait until the elements are visible and interactable
        WebDriverWait wait = new WebDriverWait(driver, 50);


        WebElement vehicleType = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/form/div/div[2]/div[1]/div[1]/div/span/span[1]/span"));
        vehicleType.click();

        WebElement noOfOrders = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/form/div/div[2]/div[1]/div[2]/div/input"));
        noOfOrders.click();
        noOfOrders.sendKeys("");

        WebElement reqDate = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/form/div/div[2]/div[2]/div[1]/div/textarea"));
        reqDate.sendKeys("2024-07-29");

        // scroll down the page to select client login button
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps = 8;
        long pauseTime = 20;
        int scrollAmount = 15;

        for(int i = 0; i < steps; i ++){
            js.executeScript("window.scrollBy(0, "+scrollAmount+");");
            Thread.sleep(pauseTime);
        }

        WebElement reqConfirmBtn = driver.findElement(By.xpath("//*[@id=\"submit-button\"]"));
        reqConfirmBtn.click();

        // Increase wait time for the error message
        WebElement alertMessage1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"vehicle-error\"]")));

        // Get the text of the form element
        String actualMessage1 = alertMessage1.getText();

        // Verify the error message is displayed
        softAssert.assertTrue(alertMessage1.isDisplayed(), "Error message is not displayed");

        // Debugging: Print the actual error message text
        System.out.println("Actual alert message for leave required field (Vehicle Type field) : " + actualMessage1);

        // Verify the error message text
        String expectedErrorMessage1 = "This field is required.";
        System.out.println("Expected alert message is: " +expectedErrorMessage1);
        softAssert.assertEquals(actualMessage1, expectedErrorMessage1, "Alert message text does not match");

        if (actualMessage1.equals(expectedErrorMessage1)){
            System.out.println("Alert message displayed as expected!");
        }else {
            System.out.println("Alert message not displayed as expected!");
        }

        // increase wait time for the error message
        WebElement alertMessage2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"order_count-error\"]")));

        // get the text of the form element
        String actualMessage2 = alertMessage2.getText();

        // verify the error message is displayed
        softAssert.assertTrue(alertMessage2.isDisplayed(), "Error message is not displayed");

        // print the actual error message text
        System.out.println("Actual alert message for leave required field (No of Orders field) : " + actualMessage2);

        // Verify the error message text
        String expectedErrorMessage2 = "This field is required.";
        System.out.println("Expected alert message is: " +expectedErrorMessage2);
        softAssert.assertEquals(actualMessage2, expectedErrorMessage2, "Alert message text does not match");

        if (actualMessage2.equals(expectedErrorMessage2)){
            System.out.println("Alert message displayed as expected!");
        }else {
            System.out.println("Alert message not displayed as expected!");
        }
    }

}
