package com.test.trans.client.main;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreatePickupRequestMore {
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
    public void createPickupRequest()throws InterruptedException{

        WebElement vehicleType = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/form/div/div[2]/div[1]/div[1]/div/span/span[1]/span"));
        vehicleType.click();

        WebElement vehicleSelect = driver.findElement(By.xpath("/html/body/span/span/span[2]/ul/li[1]"));
        vehicleSelect.click();

        WebElement noOfOrders = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/form/div/div[2]/div[1]/div[2]/div/input"));
        noOfOrders.sendKeys("1");

        WebElement reqDate = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/form/div/div[2]/div[2]/div[1]/div/textarea"));
        reqDate.sendKeys("2024/07/29");

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
        Thread.sleep(300);

        js.executeScript("window.scrollTo(0, 0);");

       // locate the success message
        WebElement errMessage = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[1]"));

        // verify the success message is displayed
        Assert.assertTrue(errMessage.isDisplayed(), "Error message is not displayed");

        // get the actual success message text and remove extra spaces
        String actualErrMessage = errMessage.getText();
        actualErrMessage = actualErrMessage.replace("×", "").replace("Close", "").trim();

        // print the actual error message text
        System.out.println("Actual error message is: " + actualErrMessage);

        // verify the error message text
        String expectedErrMessage = "You have already made a request today.";
        System.out.println("Expected error message is: " +expectedErrMessage);

        // verify the error message text
        boolean isErrMessagePresent = actualErrMessage.contains(expectedErrMessage);
        Assert.assertTrue(isErrMessagePresent, "Error message text does not match");


    }

}
