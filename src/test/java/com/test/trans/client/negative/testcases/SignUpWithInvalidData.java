package com.test.trans.client.negative.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SignUpWithInvalidData {
    private WebDriver driver;
    private Actions actions;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HASINI\\Desktop\\Automation Trans Express Project\\TransExpressTest\\src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        actions = new Actions(driver);
        driver.get("https://qa2.transexpress.parallaxtec.dev");

    }
    @Test(priority = 1)
    public void transSignUpInvalid()throws InterruptedException{

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

        // sign-up for client portal
        WebElement clientLoginBtn = driver.findElement(By.xpath("*//div[1]/div/div/div/a[1]"));
        clientLoginBtn.click();

        // sign-up for client portal
        WebElement clientSignUpBtn = driver.findElement(By.xpath("//*[@id=\"login_panel\"]/a"));
        clientSignUpBtn.click();

    }

    @Test(priority = 2)
    public void clientDetailsProcess()throws InterruptedException {

        // Initialize SoftAssert
        SoftAssert softAssert = new SoftAssert();

        // Wait until the elements are visible and interactable
        WebDriverWait wait = new WebDriverWait(driver, 40);

        // Fill owner details section

        // name
        WebElement ownerName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/div/div/div/div[2]/div[1]/div[1]/div/input")));
        ownerName.sendKeys("Hasini");

        // NIC
        WebElement ownerNIC = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[2]/div[1]/div[2]/div/input")));
        ownerNIC.sendKeys("sdsdasrtrer");

        // Check if the NIC field still contains the entered letters
        String enteredText = ownerNIC.getAttribute("value");
        softAssert.assertNotEquals(enteredText, "sdsdasrtrer", "NIC field should not allow letters only");

        // Optionally, check for any visual indication of an error (e.g., red border)
        String nicFieldClass = ownerNIC.getAttribute("class");
        softAssert.assertTrue(nicFieldClass.contains("errorClass"), "NIC field should have error indication");

        WebElement ownerEmail = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[2]/div[2]/div[1]/div/input")));
        ownerEmail.sendKeys("hasini@gmail.");

        WebElement nextField = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[2]/div[2]/div[2]/div/input"));
        nextField.click();

        // Increase wait time for the error message
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("owner_email-error")));

        // Get the text of the form element
        String actualMessage = errorMessage.getText();

        // Verify the error message is displayed
        softAssert.assertTrue(errorMessage.isDisplayed(), "Error message is not displayed");

        // Debugging: Print the actual error message text
        System.out.println("Actual Error Message: " + actualMessage);

        // Verify the error message text
        String expectedErrorMessage = "Please enter a valid email address.";
        softAssert.assertEquals(actualMessage, expectedErrorMessage, "Error message text does not match");

        // contact number
        WebElement ownerContactNo = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[2]/div[2]/div[2]/div/input"));
        String contactNumber = "rerewrwerw";
        ownerContactNo.sendKeys(contactNumber);

        // Check if the NIC field still contains the entered letters
        String enteredNo = ownerContactNo.getAttribute("value");
        softAssert.assertNotEquals(enteredNo, "rerewrwerw", "Contact no field should not allow letters only");

        // Optionally, check for any visual indication of an error (e.g., red border)
        String contactNoFieldClass = ownerContactNo.getAttribute("class");
        softAssert.assertTrue(contactNoFieldClass.contains("errorClass"), "Contact no field should have error indication");

        // use javascript executor to execute the script
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps = 2;
        long pauseTime = 200;
        int scrollAmount = 100;

        for (int i = 0; i < steps; i++) {
            js.executeScript("window.scrollBy(0, " + scrollAmount + ");");
            Thread.sleep(pauseTime);
        }

        //address
        WebElement ownerAddress = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[2]/div[3]/div[1]/div/input"));
        ownerAddress.sendKeys("Kandy");
        Thread.sleep(500);

        //remarks
        WebElement remarks = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[2]/div[3]/div[2]/div/textarea"));
        remarks.sendKeys("Test remarks");
        Thread.sleep(100);

        // Assert all to report the results
        softAssert.assertAll();
    }

    @Test (priority = 3)
    public void requiredFieldsBusinessDetails()throws InterruptedException{

        // Initialize SoftAssert
        SoftAssert softAssert = new SoftAssert();

        // Wait until the elements are visible and interactable
        WebDriverWait wait = new WebDriverWait(driver, 60);

        // use javascript executor to execute the script
        JavascriptExecutor jsex = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps2 = 2;
        long pauseTime2 = 200;
        int scrollAmount2 = 100;

        for(int i = 0; i < steps2; i ++){
            jsex.executeScript("window.scrollBy(0, "+scrollAmount2+");");
            Thread.sleep(pauseTime2);
        }
        // for leave all required fields to check the alert msg is prompt
        // to fill business details
        WebElement businessName = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[3]/div[1]/div[1]/div/input"));
        businessName.sendKeys("");
        Thread.sleep(300);

        WebElement regNo = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[3]/div[1]/div[2]/div/input"));
        regNo.sendKeys("");
        Thread.sleep(300);

        WebElement businessEmail = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[3]/div[2]/div[1]/div/input"));
        businessEmail.sendKeys("");
        Thread.sleep(300);

        WebElement businessPhoneNo = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[3]/div[2]/div[2]/div/input"));
        businessPhoneNo.sendKeys("");
        Thread.sleep(300);

        WebElement address = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[3]/div[3]/div/div/input"));
        address.sendKeys("");
        Thread.sleep(300);

        WebElement password = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[3]/div[4]/div[1]/div/input"));
        password.sendKeys("");
        Thread.sleep(300);

        WebElement confirmPassword = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[3]/div[4]/div[2]/div/input"));
        confirmPassword.sendKeys("");
        Thread.sleep(300);

        // use javascript executor to execute the script
        JavascriptExecutor jsOb = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps = 10;
        long pauseTime = 500;
        int scrollAmount = 250;

        for(int i = 0; i < steps; i ++){
            jsOb.executeScript("window.scrollBy(0, "+scrollAmount+");");
            Thread.sleep(pauseTime);
        }
        // for create account button
        WebElement submitBtn = driver.findElement(By.xpath("//*[@id=\"submit-button\"]"));
        submitBtn.click();

        // Increase wait time for the error message
        WebElement alertMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[3]/div[1]/div[1]/div/label[2]")));

        // Get the text of the form element
        String actualMessage = alertMessage.getText();

        // Verify the error message is displayed
        softAssert.assertTrue(alertMessage.isDisplayed(), "Error message is not displayed");

        // Debugging: Print the actual error message text
        System.out.println("Actual Error Message for Business Name Field: " + actualMessage);

        // Verify the error message text
        String expectedErrorMessage = "This field is required.";
        softAssert.assertEquals(actualMessage, expectedErrorMessage, "Alert message text does not match");

        //------//
        // Increase wait time for the error message
        WebElement alertMessage2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[3]/div[2]/div[1]/div/label[2]")));

        // Get the text of the form element
        String actualMessage2 = alertMessage2.getText();

        // Verify the error message is displayed
        softAssert.assertTrue(alertMessage2.isDisplayed(), "Error message is not displayed");

        // Debugging: Print the actual error message text
        System.out.println("Actual Error Message for Email Field: " + actualMessage2);

        // Verify the error message text
        String expectedErrorMessage2 = "This field is required.";
        softAssert.assertEquals(actualMessage2, expectedErrorMessage2, "Alert message text does not match");

        //------//
        // Increase wait time for the error message
        WebElement alertMessage4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[3]/div[3]/div/div/label[2]")));

        // Get the text of the form element
        String actualMessage4 = alertMessage4.getText();

        // Verify the error message is displayed
        softAssert.assertTrue(alertMessage4.isDisplayed(), "Error message is not displayed");

        // Debugging: Print the actual error message text
        System.out.println("Actual Error Message for Address Field: " + actualMessage4);

        // Verify the error message text
        String expectedErrorMessage4 = "This field is required.";
        softAssert.assertEquals(actualMessage4, expectedErrorMessage4, "Alert message text does not match");


        //------//
        // Increase wait time for the error message
        WebElement alertMessage5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[3]/div[4]/div[1]/div/label[2]")));

        // Get the text of the form element
        String actualMessage5 = alertMessage5.getText();

        // Verify the error message is displayed
        softAssert.assertTrue(alertMessage5.isDisplayed(), "Error message is not displayed");

        // Debugging: Print the actual error message text
        System.out.println("Actual Error Message for Password Field: " + actualMessage5);

        // Verify the error message text
        String expectedErrorMessage5 = "This field is required.";
        softAssert.assertEquals(actualMessage5, expectedErrorMessage5, "Alert message text does not match");

        //------//
        // Increase wait time for the error message
        WebElement alertMessage6 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[3]/div[4]/div[2]/div/label[2]")));

        // Get the text of the form element
        String actualMessage6 = alertMessage6.getText();

        // Verify the error message is displayed
        softAssert.assertTrue(alertMessage6.isDisplayed(), "Error message is not displayed");

        // Debugging: Print the actual error message text
        System.out.println("Actual Error Message for Confirm Password Field: " + actualMessage6);

        // Verify the error message text
        String expectedErrorMessage6 = "This field is required.";
        softAssert.assertEquals(actualMessage6, expectedErrorMessage6, "Alert message text does not match");


        /*// use javascript executor to execute the script
        JavascriptExecutor jsOb3 = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps3 = 10;
        long pauseTime3 = 500;
        int scrollAmount3 = 250;

        for(int i = 0; i < steps3; i ++){
            jsOb3.executeScript("window.scrollBy(0, "+scrollAmount3+");");
            Thread.sleep(pauseTime3);
        }*/

        // to check the invalid phone no
        WebElement businessPhoneNoRenter = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[3]/div[2]/div[2]/div/input"));
        businessPhoneNoRenter.sendKeys("01156");
        Thread.sleep(300);


        //------//
        // Increase wait time for the error message
        WebElement alertMessage3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[3]/div[2]/div[2]/div/label[2]")));

        // Get the text of the form element
        String actualMessage3 = alertMessage3.getText();

        // Verify the error message is displayed
        softAssert.assertTrue(alertMessage3.isDisplayed(), "Error message is not displayed");

        // Debugging: Print the actual error message text
        System.out.println("Actual Error Message for Phone No of Digits Less than 9: " + actualMessage3);

        // Verify the error message text
        String expectedErrorMessage3 = "Please enter at least 9 characters.";
        softAssert.assertEquals(actualMessage3, expectedErrorMessage3, "Alert message text does not match");


        WebElement passwordRenter = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[3]/div[4]/div[1]/div/input"));
        passwordRenter.sendKeys("abs");
        Thread.sleep(300);

        //------//
        // Increase wait time for the error message
        WebElement alertMessage7 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[3]/div[4]/div[1]/div/label[2]")));

        // Get the text of the form element
        String actualMessage7 = alertMessage7.getText();

        // Verify the error message is displayed
        softAssert.assertTrue(alertMessage7.isDisplayed(), "Error message is not displayed");

        // Debugging: Print the actual error message text
        System.out.println("Actual Error Message for Password Field Less than 8 Characters : " + actualMessage7);

        // Verify the error message text
        String expectedErrorMessage7 = "Please enter at least 8 characters.";
        softAssert.assertEquals(actualMessage7, expectedErrorMessage7, "Alert message text does not match");

        WebElement confirmPasswordRenter = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[3]/div[4]/div[2]/div/input"));
        confirmPasswordRenter.sendKeys("abs");
        Thread.sleep(300);

        //------//
        // Increase wait time for the error message
        WebElement alertMessage8 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[3]/div[4]/div[1]/div/label[2]")));

        // Get the text of the form element
        String actualMessage8 = alertMessage8.getText();

        // Verify the error message is displayed
        softAssert.assertTrue(alertMessage8.isDisplayed(), "Error message is not displayed");

        // Debugging: Print the actual error message text
        System.out.println("Actual Error Message for Confirm Password Field Less than 8 Characters : " + actualMessage8);

        // Verify the error message text
        String expectedErrorMessage8 = "Please enter at least 8 characters.";
        softAssert.assertEquals(actualMessage8, expectedErrorMessage8, "Alert message text does not match");

        softAssert.assertAll();
    }

    @Test(priority = 4)
    public void requiredFieldsPickUpDetails()throws InterruptedException{

        // Initialize SoftAssert
        SoftAssert softAssert = new SoftAssert();

        // Wait until the elements are visible and interactable
        WebDriverWait wait = new WebDriverWait(driver, 60);

        // use javascript executor to execute the script
        JavascriptExecutor jsex = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps2 = 2;
        long pauseTime2 = 200;
        int scrollAmount2 = 100;

        for(int i = 0; i < steps2; i ++){
            jsex.executeScript("window.scrollBy(0, "+scrollAmount2+");");
            Thread.sleep(pauseTime2);
        }

        WebElement pAddress = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[4]/div[1]/div[1]/div/input"));
        pAddress.sendKeys("");
        Thread.sleep(300);

        WebElement pickupPhoneNo = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[4]/div[1]/div[2]/div/input"));
        pickupPhoneNo.sendKeys("");
        Thread.sleep(300);

        //drop-down
        WebElement selectCityDropdown = driver.findElement(By.xpath("//*[@id=\"select2-nearest_city-container\"]"));
        selectCityDropdown.click();

        WebElement deliEmail = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[4]/div[3]/div[1]/div/input"));
        deliEmail.sendKeys("");
        Thread.sleep(300);

        WebElement financialEmail = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[4]/div[3]/div[2]/div/input"));
        financialEmail.sendKeys("");
        Thread.sleep(300);

        // use javascript executor to execute the script
        JavascriptExecutor jsOb = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps = 10;
        long pauseTime = 500;
        int scrollAmount = 250;

        for(int i = 0; i < steps; i ++){
            jsOb.executeScript("window.scrollBy(0, "+scrollAmount+");");
            Thread.sleep(pauseTime);
        }
        // for create account button
        WebElement submitBtn = driver.findElement(By.xpath("//*[@id=\"submit-button\"]"));
        submitBtn.click();

        //------//
        // Increase wait time for the error message
        WebElement alertMessage1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[4]/div[1]/div[1]/div/label[2]")));

        // Get the text of the form element
        String actualMessage1= alertMessage1.getText();

        // Verify the error message is displayed
        softAssert.assertTrue(alertMessage1.isDisplayed(), "Error message is not displayed");

        // Debugging: Print the actual error message text
        System.out.println("Actual Error Message for Pickup Address Field: " + actualMessage1);

        // Verify the error message text
        String expectedErrorMessage1 = "This field is required.";
        softAssert.assertEquals(actualMessage1, expectedErrorMessage1, "Alert message text does not match");

        //------//
        // Increase wait time for the error message
        WebElement alertMessage2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[4]/div[1]/div[2]/div/label[2]")));

        // Get the text of the form element
        String actualMessage2= alertMessage2.getText();

        // Verify the error message is displayed
        softAssert.assertTrue(alertMessage2.isDisplayed(), "Error message is not displayed");

        // Debugging: Print the actual error message text
        System.out.println("Actual Error Message for Phone No Field: " + actualMessage2);

        // Verify the error message text
        String expectedErrorMessage2 = "This field is required.";
        softAssert.assertEquals(actualMessage2, expectedErrorMessage2, "Alert message text does not match");

        //------//
        // Increase wait time for the error message
        WebElement alertMessage3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[4]/div[2]/div[1]/div/label[2]")));

        // Get the text of the form element
        String actualMessage3= alertMessage3.getText();

        // Verify the error message is displayed
        softAssert.assertTrue(alertMessage3.isDisplayed(), "Error message is not displayed");

        // Debugging: Print the actual error message text
        System.out.println("Actual Error Message for Nearest City Field: " + actualMessage3);

        // Verify the error message text
        String expectedErrorMessage3 = "This field is required.";
        softAssert.assertEquals(actualMessage3, expectedErrorMessage3, "Alert message text does not match");

        //------//
        // Increase wait time for the error message
        WebElement alertMessage4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[4]/div[3]/div[1]/div/label[2]")));

        // Get the text of the form element
        String actualMessage4= alertMessage4.getText();

        // Verify the error message is displayed
        softAssert.assertTrue(alertMessage4.isDisplayed(), "Error message is not displayed");

        // Debugging: Print the actual error message text
        System.out.println("Actual Error Message for Delivery Email Field: " + actualMessage4);

        // Verify the error message text
        String expectedErrorMessage4 = "This field is required.";
        softAssert.assertEquals(actualMessage4, expectedErrorMessage4, "Alert message text does not match");

        //------//
        // Increase wait time for the error message
        WebElement alertMessage5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[4]/div[3]/div[2]/div/label[2]")));

        // Get the text of the form element
        String actualMessage5= alertMessage5.getText();

        // Verify the error message is displayed
        softAssert.assertTrue(alertMessage5.isDisplayed(), "Error message is not displayed");

        // Debugging: Print the actual error message text
        System.out.println("Actual Error Message for Financial Email Field: " + actualMessage4);

        // Verify the error message text
        String expectedErrorMessage5 = "This field is required.";
        softAssert.assertEquals(actualMessage5, expectedErrorMessage5, "Alert message text does not match");

        // to check the invalid phone no
        WebElement pickupPhoneNoRenter = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[4]/div[1]/div[2]/div/input"));
        pickupPhoneNoRenter.sendKeys("01188");
        Thread.sleep(300);

        //------//
        // Increase wait time for the error message
        WebElement alertMessage6 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[4]/div[1]/div[2]/div/label[2]")));

        // Get the text of the form element
        String actualMessage6 = alertMessage6.getText();

        // Verify the error message is displayed
        softAssert.assertTrue(alertMessage6.isDisplayed(), "Error message is not displayed");

        // Debugging: Print the actual error message text
        System.out.println("Actual Error Message for Pickup Phone No of Digits Less than 10: " + actualMessage6);

        // Verify the error message text
        String expectedErrorMessage6 = "Please enter at least 10 characters.";
        softAssert.assertEquals(actualMessage6, expectedErrorMessage6, "Alert message text does not match");

        softAssert.assertAll();
    }

    @Test(priority = 5)
    public void requiredFieldsAccountDetails()throws InterruptedException{

        // Initialize SoftAssert
        SoftAssert softAssert = new SoftAssert();

        // Wait until the elements are visible and interactable
        WebDriverWait wait = new WebDriverWait(driver, 60);

        // use javascript executor to execute the script
        JavascriptExecutor jsex = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps2 = 2;
        long pauseTime2 = 200;
        int scrollAmount2 = 100;

        for(int i = 0; i < steps2; i ++){
            jsex.executeScript("window.scrollBy(0, "+scrollAmount2+");");
            Thread.sleep(pauseTime2);
        }

        WebElement accHolderName = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[4]/div[4]/div[1]/div[1]/div/input"));
        accHolderName.sendKeys("");
        Thread.sleep(300);

        WebElement accNo = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[4]/div[4]/div[1]/div[2]/div/input"));
        accNo.sendKeys("");
        Thread.sleep(300);

        WebElement branchName = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[4]/div[4]/div[1]/div[3]/div/input"));
        branchName.sendKeys("");
        Thread.sleep(300);

        WebElement branchID = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[4]/div[4]/div[2]/div[1]/div/input"));
        branchID.sendKeys("");
        Thread.sleep(300);

        WebElement bankName = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[4]/div[4]/div[2]/div[2]/div/div/input"));
        bankName.sendKeys("");
        Thread.sleep(300);

        // for create account button
        WebElement submitBtn = driver.findElement(By.xpath("//*[@id=\"submit-button\"]"));
        submitBtn.click();

        //------//
        // Increase wait time for the error message
        WebElement alertMessage1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[4]/div[4]/div[1]/div[1]/div/label[2]")));

        // Get the text of the form element
        String actualMessage1= alertMessage1.getText();

        // Verify the error message is displayed
        softAssert.assertTrue(alertMessage1.isDisplayed(), "Error message is not displayed");

        // Debugging: Print the actual error message text
        System.out.println("Actual Error Message for Account Holder Name Field: " + actualMessage1);

        // Verify the error message text
        String expectedErrorMessage1 = "This field is required.";
        softAssert.assertEquals(actualMessage1, expectedErrorMessage1, "Alert message text does not match");

        //------//
        // Increase wait time for the error message
        WebElement alertMessage2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[4]/div[4]/div[1]/div[2]/div/label[2]")));

        // Get the text of the form element
        String actualMessage2= alertMessage2.getText();

        // Verify the error message is displayed
        softAssert.assertTrue(alertMessage2.isDisplayed(), "Error message is not displayed");

        // Debugging: Print the actual error message text
        System.out.println("Actual Error Message for Account No Field: " + actualMessage2);

        // Verify the error message text
        String expectedErrorMessage2 = "This field is required.";
        softAssert.assertEquals(actualMessage2, expectedErrorMessage2, "Alert message text does not match");

        //------//
        // Increase wait time for the error message
        WebElement alertMessage3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[4]/div[4]/div[1]/div[3]/div/label[2]")));

        // Get the text of the form element
        String actualMessage3= alertMessage3.getText();

        // Verify the error message is displayed
        softAssert.assertTrue(alertMessage3.isDisplayed(), "Error message is not displayed");

        // Debugging: Print the actual error message text
        System.out.println("Actual Error Message for Branch Name Field: " + actualMessage3);

        // Verify the error message text
        String expectedErrorMessage3 = "This field is required.";
        softAssert.assertEquals(actualMessage3, expectedErrorMessage3, "Alert message text does not match");

        //------//
        // Increase wait time for the error message
        WebElement alertMessage4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[4]/div[4]/div[2]/div[1]/div/label[2]")));

        // Get the text of the form element
        String actualMessage4= alertMessage4.getText();

        // Verify the error message is displayed
        softAssert.assertTrue(alertMessage4.isDisplayed(), "Error message is not displayed");

        // Debugging: Print the actual error message text
        System.out.println("Actual Error Message for Branch ID Field: " + actualMessage4);

        // Verify the error message text
        String expectedErrorMessage4 = "This field is required.";
        softAssert.assertEquals(actualMessage4, expectedErrorMessage4, "Alert message text does not match");

        //------//
        // Increase wait time for the error message
        WebElement alertMessage5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[4]/div[4]/div[2]/div[2]/div/div/label[2]")));

        // Get the text of the form element
        String actualMessage5= alertMessage5.getText();

        // Verify the error message is displayed
        softAssert.assertTrue(alertMessage5.isDisplayed(), "Error message is not displayed");

        // print the actual error message text
        System.out.println("Actual Error Message for Bank Name Field: " + actualMessage4);

        // Verify the error message text
        String expectedErrorMessage5 = "This field is required.";
        softAssert.assertEquals(actualMessage5, expectedErrorMessage5, "Alert message text does not match");

        softAssert.assertAll();
    }

    @AfterClass
    public void browserQuit() {
        if (driver != null) {
            driver.quit();
        }
    }

}
