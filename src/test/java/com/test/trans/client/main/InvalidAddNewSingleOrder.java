package com.test.trans.client.main;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class InvalidAddNewSingleOrder {
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
        email.sendKeys("client-user1@gmail.com");
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
    public void navigateAddNewOrders(){
        WebElement newOrder = driver.findElement(By.xpath("/html/body/div[4]/div/div[1]/div/div[2]/div/ul/li[4]/a"));
        newOrder.click();

        // locate the form element
        WebElement formElement = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[1]/div[2]/form/div[1]/h2"));

        // get the text of the form element
        String actualFormTitle = formElement.getText();

        // define the expected form title
        String expectedFormTitle = "Create an order";

        // assert for the form title
        Assert.assertEquals(actualFormTitle, expectedFormTitle, "Form title does not match expected title");

        // to print form title
        System.out.println("Form actual title is :" +actualFormTitle +"."+ " Therefore, actual form title is displayed as expected.");
    }

    @Test(priority = 3)
    public void singleOrderWithInvalidData()throws InterruptedException{
        // Initialize SoftAssert
        SoftAssert softAssert = new SoftAssert();

        // Wait until the elements are visible and interactable
        WebDriverWait wait = new WebDriverWait(driver, 50);

        // for leave all required fields to check the alert msg is prompt
        WebElement waybillNo = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[1]/div[2]/form/div[2]/fieldset[1]/div/div[1]/div/div/input"));
        waybillNo.sendKeys("");
        Thread.sleep(300);

        WebElement orderNo = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[1]/div[2]/form/div[2]/fieldset[1]/div/div[2]/div/div/input"));
        orderNo.sendKeys("");
        Thread.sleep(300);

        // use javascript executor to execute the script
        JavascriptExecutor jsex1 = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps = 10;
        long pauseTime = 200;
        int scrollAmount = 100;

        for(int i = 0; i < steps; i ++){
            jsex1.executeScript("window.scrollBy(0, "+scrollAmount+");");
            Thread.sleep(pauseTime);
        }

        WebElement cusName = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[1]/div[2]/form/div[2]/fieldset[2]/div[1]/div/input"));
        cusName.sendKeys("");
        Thread.sleep(300);

        WebElement address = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[1]/div[2]/form/div[2]/fieldset[2]/div[2]/div/input"));
        address.sendKeys("");
        Thread.sleep(300);

        WebElement firstPhoneNo = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[1]/div[2]/form/div[2]/fieldset[2]/div[3]/div/div/div/input"));
        firstPhoneNo.sendKeys("");
        Thread.sleep(300);

        WebElement secondPhoneNo = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[1]/div[2]/form/div[2]/fieldset[2]/div[4]/div/div/div/input"));
        secondPhoneNo.sendKeys("");
        Thread.sleep(300);

        WebElement orderDes = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[1]/div[2]/form/div[2]/fieldset[2]/div[5]/div/textarea"));
        orderDes.sendKeys("");
        Thread.sleep(300);

        // use javascript executor to execute the script
        JavascriptExecutor jsex2 = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps2 = 10;
        long pauseTime2 = 200;
        int scrollAmount2 = 100;

        for(int i = 0; i < steps2; i ++){
            jsex2.executeScript("window.scrollBy(0, "+scrollAmount2+");");
            Thread.sleep(pauseTime2);
        }

        //drop-down
        WebElement selectCityDropdown = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[1]/div[2]/form/div[2]/fieldset[3]/div[1]/div/div/div/span[1]/span[1]/span"));
        selectCityDropdown.click();

        WebElement codAmount = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[1]/div[2]/form/div[2]/fieldset[3]/div[2]/div/div/div/input"));
        codAmount.sendKeys("");
        Thread.sleep(300);

        WebElement remarks = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[1]/div[2]/form/div[2]/fieldset[3]/div[3]/div/div/div/textarea"));
        remarks.sendKeys("");


        // for create account button
        WebElement orderBtn = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[1]/div[2]/form/div[2]/div/button[1]"));
        orderBtn.click();
        orderBtn.click();
        Thread.sleep(300);

        // Increase wait time for the error message
        WebElement alertMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[1]/div[2]/form/div[2]/fieldset[1]/div/div[1]/div/div/label[2]")));

        // Get the text of the form element
        String actualMessage = alertMessage.getText();

        // Verify the error message is displayed
        softAssert.assertTrue(alertMessage.isDisplayed(), "Error message is not displayed");

        // Debugging: Print the actual error message text
        System.out.println("Actual Error Message for Waybill No Field: " + actualMessage);

        // Verify the error message text
        String expectedErrorMessage = "This field is required.";
        softAssert.assertEquals(actualMessage, expectedErrorMessage, "Alert message text does not match");

        //---//
        // Increase wait time for the error message
        WebElement alertMessage2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[1]/div[2]/form/div[2]/fieldset[1]/div/div[2]/div/div/label[2]")));

        // Get the text of the form element
        String actualMessage2 = alertMessage2.getText();

        // Verify the error message is displayed
        softAssert.assertTrue(alertMessage2.isDisplayed(), "Error message is not displayed");

        // Debugging: Print the actual error message text
        System.out.println("Actual Error Message for Order No Field: " + actualMessage2);

        // Verify the error message text
        String expectedErrorMessage2 = "This field is required.";
        softAssert.assertEquals(actualMessage2, expectedErrorMessage2, "Alert message text does not match");

        //---//
        // Increase wait time for the error message
        WebElement alertMessage3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"customer_name-error\"]")));

        // Get the text of the form element
        String actualMessage3 = alertMessage3.getText();

        // Verify the error message is displayed
        softAssert.assertTrue(alertMessage3.isDisplayed(), "Error message is not displayed");

        // Debugging: Print the actual error message text
        System.out.println("Actual Error Message for Customer Name Field: " + actualMessage3);

        // Verify the error message text
        String expectedErrorMessage3 = "This field is required.";
        softAssert.assertEquals(actualMessage3, expectedErrorMessage3, "Alert message text does not match");

        //---//
        // Increase wait time for the error message
        WebElement alertMessage4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"address-error\"]")));

        // Get the text of the form element
        String actualMessage4 = alertMessage4.getText();

        // Verify the error message is displayed
        softAssert.assertTrue(alertMessage4.isDisplayed(), "Error message is not displayed");

        // Debugging: Print the actual error message text
        System.out.println("Actual Error Message for Address Field: " + actualMessage4);

        // Verify the error message text
        String expectedErrorMessage4 = "This field is required.";
        softAssert.assertEquals(actualMessage4, expectedErrorMessage4, "Alert message text does not match");

        //---//
        // Increase wait time for the error message
        WebElement alertMessage5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"phone_no-error\"]")));

        // Get the text of the form element
        String actualMessage5 = alertMessage5.getText();

        // Verify the error message is displayed
        softAssert.assertTrue(alertMessage5.isDisplayed(), "Error message is not displayed");

        // Debugging: Print the actual error message text
        System.out.println("Actual Error Message for First Phone No Field: " + actualMessage5);

        // Verify the error message text
        String expectedErrorMessage5 = "This field is required.";
        softAssert.assertEquals(actualMessage5, expectedErrorMessage5, "Alert message text does not match");

        //---//
        // Increase wait time for the error message
        WebElement alertMessage6 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"city-error\"]")));

        // Get the text of the form element
        String actualMessage6 = alertMessage6.getText();

        // Verify the error message is displayed
        softAssert.assertTrue(alertMessage6.isDisplayed(), "Error message is not displayed");

        // Debugging: Print the actual error message text
        System.out.println("Actual Error Message for City Field: " + actualMessage6);

        // Verify the error message text
        String expectedErrorMessage6 = "This field is required.";
        softAssert.assertEquals(actualMessage6, expectedErrorMessage6, "Alert message text does not match");

        //---//
        // Increase wait time for the error message
        WebElement alertMessage7 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"cod-error\"]")));

        // Get the text of the form element
        String actualMessage7 = alertMessage7.getText();

        // Verify the error message is displayed
        softAssert.assertTrue(alertMessage7.isDisplayed(), "Error message is not displayed");

        // Debugging: Print the actual error message text
        System.out.println("Actual Error Message for COD Amount Field: " + actualMessage6);

        // Verify the error message text
        String expectedErrorMessage7 = "This field is required.";
        softAssert.assertEquals(actualMessage7, expectedErrorMessage7, "Alert message text does not match");

        // Assert all to report the results
        softAssert.assertAll();



    }

    @Test(priority = 4)
    public void fieldValidations()throws InterruptedException{

        // Initialize SoftAssert
        SoftAssert softAssert = new SoftAssert();

        // Wait until the elements are visible and interactable
        WebDriverWait wait = new WebDriverWait(driver, 50);

        // to validate waybill with less than 8 digits to catch the alert msg
        WebElement waybillNo = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[1]/div[2]/form/div[2]/fieldset[1]/div/div[1]/div/div/input"));
        waybillNo.sendKeys("3435");
        Thread.sleep(300);

        WebElement orderNo = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[1]/div[2]/form/div[2]/fieldset[1]/div/div[2]/div/div/input"));
        orderNo.click();

        // Increase wait time for the error message
        WebElement alertMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"waybill_id-error\"]")));

        // Get the text of the form element
        String actualMessage = alertMessage.getText();

        // Verify the error message is displayed
        softAssert.assertTrue(alertMessage.isDisplayed(), "Error message is not displayed");

        // Debugging: Print the actual error message text
        System.out.println("Actual Error Message for less than 8 digits of Waybill No : " + actualMessage);

        // Verify the error message text
        String expectedErrorMessage = "Please enter at least 8 characters.";
        softAssert.assertEquals(actualMessage, expectedErrorMessage, "Alert message text does not match");

        // to validate phone numbers starting with invalid phone no format
        WebElement firstPhoneNo = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[1]/div[2]/form/div[2]/fieldset[2]/div[3]/div/div/div/input"));
        firstPhoneNo.sendKeys("5655");
        Thread.sleep(300);

        WebElement secondPhoneNo = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[1]/div[2]/form/div[2]/fieldset[2]/div[4]/div/div/div/input"));
        secondPhoneNo.sendKeys("434");
        Thread.sleep(300);

        // Increase wait time for the error message
        WebElement alertMessage2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"primary-error-message\"]")));

        // Get the text of the form element
        String actualMessage2 = alertMessage2.getText();

        // Verify the error message is displayed
        softAssert.assertTrue(alertMessage2.isDisplayed(), "Error message is not displayed");

        // Debugging: Print the actual error message text
        System.out.println("Actual Error Message for invalid format of First Phone No : " + actualMessage2);

        // Verify the error message text
        String expectedErrorMessage2 = "Invalid phone number format. It should start with 0 or 94.";
        softAssert.assertEquals(actualMessage2, expectedErrorMessage2, "Alert message text does not match");

        // Increase wait time for the error message
        WebElement alertMessage3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"primary-error-message\"]")));

        // Get the text of the form element
        String actualMessage3 = alertMessage3.getText();

        // Verify the error message is displayed
        softAssert.assertTrue(alertMessage3.isDisplayed(), "Error message is not displayed");

        // Debugging: Print the actual error message text
        System.out.println("Actual Error Message for invalid format of Second Phone No : " + actualMessage3);

        // Verify the error message text
        String expectedErrorMessage3 = "Invalid phone number format. It should start with 0 or 94.";
        softAssert.assertEquals(actualMessage3, expectedErrorMessage3, "Alert message text does not match");

        // Assert all to report the results
        softAssert.assertAll();
    }

    @Test(priority = 5)
    public void phoneNoValidation()throws InterruptedException{
        // initialize SoftAssert
        SoftAssert softAssert = new SoftAssert();

        // wait until the elements are visible and interactable
        WebDriverWait wait = new WebDriverWait(driver, 50);

        // to validate phone numbers with digits less than 9
        WebElement firstPhoneNo = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[1]/div[2]/form/div[2]/fieldset[2]/div[3]/div/div/div/input"));
        firstPhoneNo.clear();
        firstPhoneNo.sendKeys("075676576");
        Thread.sleep(300);

        WebElement secondPhoneNo = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[1]/div[2]/form/div[2]/fieldset[2]/div[4]/div/div/div/input"));
        secondPhoneNo.clear();
        secondPhoneNo.sendKeys("078787466");
        Thread.sleep(300);

        // Increase wait time for the error message
        WebElement alertMessage2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"primary-error-message\"]")));

        // Get the text of the form element
        String actualMessage2 = alertMessage2.getText();

        // Verify the error message is displayed
        softAssert.assertTrue(alertMessage2.isDisplayed(), "Error message is not displayed");

        // Debugging: Print the actual error message text
        System.out.println("Actual Error Message for invalid format of First Phone No : " + actualMessage2);

        // Verify the error message text
        String expectedErrorMessage2 = "Phone number is too short. It should be 10 digits long.";
        softAssert.assertEquals(actualMessage2, expectedErrorMessage2, "Alert message text does not match");

        if (actualMessage2.equals(expectedErrorMessage2)){
            System.out.println("Alert message displayed as expected!");
        }else {
            System.out.println("Alert message not displayed as expected!");
        }


        // Increase wait time for the error message
        WebElement alertMessage3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"primary-error-message\"]")));

        // Get the text of the form element
        String actualMessage3 = alertMessage3.getText();

        // Verify the error message is displayed
        softAssert.assertTrue(alertMessage3.isDisplayed(), "Error message is not displayed");

        // Debugging: Print the actual error message text
        System.out.println("Actual Error Message for invalid format of Second Phone No : " + actualMessage3);

        // Verify the error message text
        String expectedErrorMessage3 = "Phone number is too short. It should be 10 digits long.";
        softAssert.assertEquals(actualMessage3, expectedErrorMessage3, "Alert message text does not match");

        if (actualMessage3.equals(expectedErrorMessage3)){
            System.out.println("Alert message displayed as expected!");
        }else {
            System.out.println("Alert message not displayed as expected!");
        }
    }

    @AfterClass
    public void quitBrowser(){
        if(driver != null){
            driver.quit();
        }
    }
}
