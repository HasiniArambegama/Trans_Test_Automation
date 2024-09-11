package com.test.trans.client.positive.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SignUpTrans {
    private WebDriver driver;
    private Actions actions;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HASINI\\Desktop\\Automation Trans Express Project\\TransExpressTest\\src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        actions = new Actions(driver);
        driver.get("https://release.transexpress.parallaxtec.dev/");

    }
    @Test(priority = 1)
    public void transSignUp()throws InterruptedException{

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
    public void ownerDetailsProcess()throws InterruptedException{

        // to fill owner details section
        WebElement ownerName = driver.findElement(By.xpath("//div/div/div/div/div[2]/div[1]/div[1]/div/input"));
        ownerName.sendKeys("Hasini");
        Thread.sleep(300);

        WebElement ownerNIC = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[2]/div[1]/div[2]/div/input"));
        ownerNIC.sendKeys("984435678V");
        Thread.sleep(300);

        // to validate the nic field
        // retrieve the value from the input field
        String enteredNIC = ownerNIC.getAttribute("value");

        // validate the length of the input value
        int maxLength = 12;
        Assert.assertTrue(enteredNIC.length() <= maxLength, "NIC length exceeds maximum allowed length of " + maxLength);

        // print the result to the console
        if (enteredNIC.length() <= maxLength) {
            System.out.println("NIC length validation is passed. Entered NIC: " + enteredNIC);
        } else {
            System.out.println("NIC length validation is failed. Entered NIC: " + enteredNIC);
        }

        WebElement ownerEmail = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[2]/div[2]/div[1]/div/input"));
        ownerEmail.sendKeys("hasini.a@parallaxtec.com");
        Thread.sleep(300);

    }

    @Test (priority = 3)
    // for contact no length is less than 9
    public void contactNoValidation1()throws InterruptedException{
        WebElement ownerContactNo = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[2]/div[2]/div[2]/div/input"));
        String contactNumber = "0778574920";
        ownerContactNo.sendKeys(contactNumber);

        // use javascript executor to execute the script
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps = 2;
        long pauseTime = 200;
        int scrollAmount = 100;

        for(int i = 0; i < steps; i ++){
            js.executeScript("window.scrollBy(0, "+scrollAmount+");");
            Thread.sleep(pauseTime);
        }

        WebElement ownerAddress = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[2]/div[3]/div[1]/div/input"));
        ownerAddress.sendKeys("Kandy");
        Thread.sleep(500);


        /*   //--- phone number validation ---//
        // used WebDriverWait to wait for the field to become invalid
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.attributeContains(ownerContactNo, "aria-invalid", "true"));


        // Use WebDriverWait to wait for the error message to be visible
        WebElement errorMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("owner_phone_no-error")));

        // Retrieve the value from the input field
        String enteredContactNo = ownerContactNo.getAttribute("value");

        // Validate the length of the input value
        int minLength = 9;
        int maxiLength = 12;
        boolean isValid = enteredContactNo.length() >= minLength && enteredContactNo.length() <= maxiLength;

     // Check for error message if the length is invalid

            if (!isValid) {
                String errorMessage = errorMessageElement.getText();
                Assert.assertEquals(errorMessage, "Please enter at least 9 characters.", "Error message does not match expected");
                System.out.println("Contact no validation is failed. Error message: " + errorMessage);
            } else {
                Assert.assertTrue(isValid, "Contact number length is invalid");
                System.out.println("Contact no validation is passed. Entered contact number: " + enteredContactNo);
            }*/


            WebElement remarks = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[2]/div[3]/div[2]/div/textarea"));
            remarks.sendKeys("Test remarks");
            Thread.sleep(500);
    }

    @Test(priority = 4)
    public void businessDetailsProcess()throws InterruptedException{

        // to fill business details
        WebElement businessName = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[3]/div[1]/div[1]/div/input"));
        businessName.sendKeys("Nils");
        Thread.sleep(300);

        WebElement regNo = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[3]/div[1]/div[2]/div/input"));
        regNo.sendKeys("234643");
        Thread.sleep(300);

        WebElement businessEmail = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[3]/div[2]/div[1]/div/input"));
        businessEmail.sendKeys("nils@gmail.com");
        Thread.sleep(300);

        WebElement businessPhoneNo = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[3]/div[2]/div[2]/div/input"));
        businessPhoneNo.sendKeys("0812233456");
        Thread.sleep(300);

        WebElement address = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[3]/div[3]/div/div/input"));
        address.sendKeys("Kandy");
        Thread.sleep(300);

        WebElement password = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[3]/div[4]/div[1]/div/input"));
        password.sendKeys("12345678");
        Thread.sleep(300);

        WebElement confirmPassword = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[3]/div[4]/div[2]/div/input"));
        confirmPassword.sendKeys("12345678");
        Thread.sleep(300);

    }

    // to fill pick up details

   @Test(priority = 5)
    public void pickUpDetailsProcess()throws InterruptedException {


        WebElement pAddress = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[4]/div[1]/div[1]/div/input"));
        pAddress.sendKeys("Kandy");
        Thread.sleep(300);

        WebElement pickupPhoneNo = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[4]/div[1]/div[2]/div/input"));
        pickupPhoneNo.sendKeys("0812233456");
        Thread.sleep(300);

        //drop-down
        WebElement selectCityDropdown = driver.findElement(By.xpath("//*[@id=\"select2-nearest_city-container\"]"));
        selectCityDropdown.click();

        WebElement searchDropdown = driver.findElement(By.xpath("//span/span/span[1]/input"));
        searchDropdown.sendKeys("Kandy");
        Thread.sleep(300);

        WebElement selectOption = driver.findElement(By.xpath("//span/span/span[2]/ul/li[1]"));
        selectOption.click();

        WebElement deliEmail = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[4]/div[3]/div[1]/div/input"));
        deliEmail.sendKeys("deliveryTest@gmail.com");
        Thread.sleep(300);

        WebElement financialEmail = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[4]/div[3]/div[2]/div/input"));
        financialEmail.sendKeys("financialTest@gmail.com");
        Thread.sleep(300);
    }

    // for bank account details

    @Test(priority = 6)
    public void bankDetailsProcess()throws InterruptedException{

        WebElement accHolderName = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[4]/div[4]/div[1]/div[1]/div/input"));
        accHolderName.sendKeys("Test Name");
        Thread.sleep(300);

        WebElement accNo = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[4]/div[4]/div[1]/div[2]/div/input"));
        accNo.sendKeys("84556367910");
        Thread.sleep(300);

        WebElement branchName = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[4]/div[4]/div[1]/div[3]/div/input"));
        branchName.sendKeys("Colombo");
        Thread.sleep(300);

        WebElement branchID = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[4]/div[4]/div[2]/div[1]/div/input"));
        branchID.sendKeys("2nd");
        Thread.sleep(300);

        WebElement bankName = driver.findElement(By.xpath("//div[2]/div/div/div/div/div[5]/form/div/div/div/div/div[4]/div[4]/div[2]/div[2]/div/div/input"));
        bankName.sendKeys("Bank of Ceylon");
        Thread.sleep(300);

        // for create account button
        WebElement submitBtn = driver.findElement(By.xpath("//*[@id=\"submit-button\"]"));
        submitBtn.click();

    }


   @AfterClass
    public void browserQuit() {
        if (driver != null) {
            driver.quit();
        }
    }

}
