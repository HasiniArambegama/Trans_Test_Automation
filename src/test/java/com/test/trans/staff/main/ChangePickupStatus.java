package com.test.trans.staff.main;

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

public class ChangePickupStatus {
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HASINI\\Desktop\\Automation Trans Express Project\\TransExpressTest\\src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://qa-2.transexpress.parallaxtec.dev/");
    }

    @Test(priority = 1)
    public void transStaffValidLogin()throws InterruptedException{

        // scroll down the page to select staff login button
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps = 03;
        long pauseTime = 100;
        int scrollAmount = 50;

        for(int i = 0; i < steps; i ++){
            js.executeScript("window.scrollBy(0, "+scrollAmount+");");
            Thread.sleep(pauseTime);
        }

        // login for staff portal
        WebElement staffLoginBtn = driver.findElement(By.xpath("//div[1]/div/div/div/a[2]"));
        staffLoginBtn.click();

        // locate the form element
        WebElement formElement = driver.findElement(By.tagName("h5"));

        // get the text of the form element
        String actualFormTitle = formElement.getText();

        // define the expected form title
        String expectedFormTitle = ("Login to your account");

        // assert for the form title
        Assert.assertEquals(actualFormTitle, expectedFormTitle, "Form title does not match expected title");

        // to print the expected title
        System.out.println("Expected title of Login Page: " + expectedFormTitle);

        // for email field
        WebElement staffEmail = driver.findElement(By.xpath("//*[@id=\"email\"]"));
        staffEmail.sendKeys("staff1@gmail.com");
        Thread.sleep(200);

        // for password field
        WebElement staffPassword = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        staffPassword.sendKeys("12345678");
        Thread.sleep(200);

        //for login button
        WebElement stLoginBtn = driver.findElement(By.xpath("//*[@id=\"login_panel\"]/div[9]/button"));
        stLoginBtn.click();

    }

    @Test(priority = 2)
    public void assignRiderToPickupOrders()throws InterruptedException{

        // initialize SoftAssert
        SoftAssert softAssert = new SoftAssert();

        // wait until the elements are visible and interactable
        WebDriverWait wait = new WebDriverWait(driver, 50);

        // scroll down the page to select staff login button
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps = 20;
        long pauseTime = 100;
        int scrollAmount = 50;

        for(int i = 0; i < steps; i ++){
            js.executeScript("window.scrollBy(0, "+scrollAmount+");");
            Thread.sleep(pauseTime);
        }

       WebElement pickupOperations = driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/div[2]/div/ul/li[11]/a"));
       pickupOperations.click();
       Thread.sleep(200);

       WebElement changePickup = driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/div[2]/div/ul/li[11]/ul/li[3]/a"));
       changePickup.click();

        // scroll down the page to select staff login button
        JavascriptExecutor js2 = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps2 = 15;
        long pauseTime2 = 100;
        int scrollAmount2 = 50;

        for(int i = 0; i < steps2; i ++){
            js2.executeScript("window.scrollBy(0, "+scrollAmount2+");");
            Thread.sleep(pauseTime2);
        }

       WebElement checkBox = driver.findElement(By.xpath("//*[@id=\"PR203272\"]"));
        checkBox.click();

        js2.executeScript("window.scrollTo(0, 0);");

        WebElement changeStatusBtn = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[6]/div[1]/div/ul/li/a"));
        changeStatusBtn.click();

        WebElement assignToRider = driver.findElement(By.xpath("//*[@id=\"bulk-assign-rider\"]/a"));
        assignToRider.click();

        WebElement rider = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/form/div/div[2]/div[1]/div[2]/div/span/span[1]/span/span[1]"));
        rider.click();

        WebElement assignBtn = driver.findElement(By.xpath("//*[@id=\"submit-button\"]"));
        assignBtn.click();

        // increase wait time for the error message
        WebElement alertMessage2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"rider-error\"]")));

        // get the text of the form element
        String actualMessage2 = alertMessage2.getText();

        // verify the error message is displayed
        softAssert.assertTrue(alertMessage2.isDisplayed(), "Error message is not displayed");

        // print the actual error message text
        System.out.println("Actual alert message for leave required field (Rider field) : " + actualMessage2);

        // verify the error message text
        String expectedErrorMessage2 = "This field is required.";
        System.out.println("Expected alert message for leave required field (Rider field) : " +expectedErrorMessage2);
        softAssert.assertEquals(actualMessage2, expectedErrorMessage2, "Alert message text does not match");

        if (actualMessage2.equals(expectedErrorMessage2)){
            System.out.println("Alert message displayed as expected!");
        }else {
            System.out.println("Alert message not displayed as expected!");
        }

        WebElement rider1 = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/form/div/div[2]/div[1]/div[2]/div/span/span[1]/span/span[1]"));
        rider1.click();

        WebElement riderSel = driver.findElement(By.xpath("/html/body/span/span/span[2]/ul/li[5]"));
        riderSel.click();

        WebElement assignBtn1 = driver.findElement(By.xpath("//*[@id=\"submit-button\"]"));
        assignBtn1.click();

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
        String expectedSuccessMessage = "Riders Assigned Successfully.";
        System.out.println("Expected success message is: " +expectedSuccessMessage);

        // verify the error message text
        boolean isSuccessMessagePresent = actualSuccessMessage.contains(expectedSuccessMessage);
        Assert.assertTrue(isSuccessMessagePresent, "Success message text does not match");

    }

    @Test(priority = 2)
    public void branchReceivePickupOrders()throws InterruptedException{

        // scroll down the page to select staff login button
        JavascriptExecutor js3 = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps3 = 15;
        long pauseTime3 = 100;
        int scrollAmount3 = 50;

        for(int i = 0; i < steps3; i ++){
            js3.executeScript("window.scrollBy(0, "+scrollAmount3+");");
            Thread.sleep(pauseTime3);
        }

        WebElement checkBox = driver.findElement(By.xpath("//*[@id=\"PR203272\"]"));
        checkBox.click();

        js3.executeScript("window.scrollTo(0, 0);");

        WebElement changeStatusBtn = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[6]/div[1]/div/ul/li/a"));
        changeStatusBtn.click();

        WebElement branchReceive = driver.findElement(By.xpath("//*[@id=\"branch-receive\"]/a"));
        branchReceive.click();

        WebElement confirmBtn = driver.findElement(By.xpath("//*[@id=\"submit-button\"]"));
        confirmBtn.click();

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
        String expectedSuccessMessage = "Bulk update completed successfully.";
        System.out.println("Expected success message is: " +expectedSuccessMessage);

        // verify the error message text
        boolean isSuccessMessagePresent = actualSuccessMessage.contains(expectedSuccessMessage);
        Assert.assertTrue(isSuccessMessagePresent, "Success message text does not match");
    }

}
