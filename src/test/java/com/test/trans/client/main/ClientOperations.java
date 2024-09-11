package com.test.trans.client.main;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ClientOperations {
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HASINI\\Desktop\\Automation Trans Express Project\\TransExpressTest\\src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://release.transexpress.parallaxtec.dev");

    }
    @Test(priority = 1)
    public void transClientLogin() throws InterruptedException {

        // scroll down the page to select client login button
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps = 10;
        long pauseTime = 500;
        int scrollAmount = 100;

        for (int i = 0; i < steps; i++) {
            js.executeScript("window.scrollBy(0, " + scrollAmount + ");");
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
    public void navigateToClientOperations() {
        driver.get("https://release.transexpress.parallaxtec.dev/client-operations");

        // locate the form element
        WebElement titleElement = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[1]/div[1]/div/h4"));

        // get the text of the form element
        String actualTitle = titleElement.getText();

        // define the expected form title
        String expectedTitle = "Home - Received returned orders";

        // assert for the form title
        Assert.assertEquals(actualTitle, expectedTitle, "Title does not match expected title");

        // to print form title
        System.out.println("Actual title is :" + actualTitle + "." + " Therefore, actual title is displayed as expected.");
    }

    @Test(priority = 3)
    public void advancedSearch()throws InterruptedException {

        WebElement orderDate = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div/div/input"));
        orderDate.click();

        WebElement dateSelect = driver.findElement(By.xpath("/html/body/div[7]/div/div[1]/ul/li[1]"));
        dateSelect.click();

        WebElement searchBtn = driver.findElement(By.xpath("//*[@id=\"filter\"]"));
        searchBtn.click();

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps = 20;
        long pauseTime = 50;
        int scrollAmount = 25;

        for (int i = 0; i < steps; i++) {
            js.executeScript("window.scrollBy(0, " + scrollAmount + ");");
            Thread.sleep(pauseTime);
        }



    }

    @Test(priority = 4)
    public void searchNoRecords()throws InterruptedException{

        driver.navigate().refresh();

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps = 15;
        long pauseTime = 40;
        int scrollAmount = 20;

        for (int i = 0; i < steps; i++) {
            js.executeScript("window.scrollBy(0, " + scrollAmount + ");");
            Thread.sleep(pauseTime);
        }

        WebElement orderDate = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div/div/input"));
        orderDate.click();

        WebElement dateSelect = driver.findElement(By.xpath("/html/body/div[7]/div/div[1]/ul/li[1]"));
        dateSelect.click();

        WebElement searchBtn = driver.findElement(By.xpath("//*[@id=\"filter\"]"));
        searchBtn.click();

        WebElement messageElement = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[7]/div[2]/div/div/div[3]/div[2]/div[2]/table/tbody/tr/td"));

        // get the text of the form element
        String actualMessage = messageElement.getText();

        // define the expected form title
        String expectedMessage = "No data available in table";

        System.out.println("Expected message is: " + expectedMessage);

        // assert for the form title
        Assert.assertEquals(actualMessage, expectedMessage, "Message does not match as expected message");

        // to print form title
        System.out.println("Actual message is: " + actualMessage + "." + " Therefore, actual message is displayed as expected.");

    }

    @Test(priority = 5)
    public void reportDownload()throws InterruptedException{

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps = 15;
        long pauseTime = 40;
        int scrollAmount = 20;

        for (int i = 0; i < steps; i++) {
            js.executeScript("window.scrollBy(0, " + scrollAmount + ");");
            Thread.sleep(pauseTime);
        }


        // to check excel download option
        WebElement excel = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[7]/div[2]/div/div/div[2]/div[2]/a[4]"));
        excel.click();

        // to check pdf option
        WebElement pdf= driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[7]/div[2]/div/div/div[2]/div[2]/a[4]"));
        pdf.click();

        // to check print option
        WebElement print = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[7]/div[2]/div/div/div[2]/div[2]/a[3]"));
        print.click();

    }

    @Test(priority = 6)
    public void confirmReceivedReturnedOrdersInvalid(){

        driver.navigate().refresh();
        WebElement waybillID = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[6]/div[2]/form/div/div/div/div[1]/div/input"));
        waybillID.click();
        waybillID.sendKeys("0000033A");

        WebElement receivedBtn = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[6]/div[2]/form/div/div/div/div[2]/div/button"));
        receivedBtn.click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");

        WebElement messageElement = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[3]"));

        // get the text of the form element
        String actualMessage = messageElement.getText();
        actualMessage = actualMessage.replace("×", "").replace("Close", "").trim();
        System.out.println("Actual message is: " +actualMessage);

        // define the expected form title
        String expectedMessage = "This order has been archived and is no longer active";

        System.out.println("Expected message is: " + expectedMessage);

        // assert for the form title
        Assert.assertEquals(actualMessage, expectedMessage, "Message does not match as expected message");

        // to print form title
        System.out.println("Actual message is: " + actualMessage + "." + " Therefore, actual message is displayed as expected.");



    }


   /*@Test(priority = 7)
    public void confirmReceivedReturnedOrders(){

        driver.navigate().refresh();
        WebElement waybillID = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[6]/div[2]/form/div/div/div/div[1]/div/input"));
        waybillID.click();
        waybillID.sendKeys("0000050A");

        WebElement receivedBtn = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[6]/div[2]/form/div/div/div/div[2]/div/button"));
        receivedBtn.click();
    }*/

   /*@AfterClass
    public void quitBrowser(){
        if(driver != null){
            driver.quit();
        }*/
    }






