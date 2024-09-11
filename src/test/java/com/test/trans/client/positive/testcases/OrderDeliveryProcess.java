package com.test.trans.client.positive.testcases;

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


public class OrderDeliveryProcess {
    private WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HASINI\\Desktop\\Automation Trans Express Project\\TransExpressTest\\src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://qa2.transexpress.parallaxtec.dev");
    }

    @Test(priority = 1)
    public void staffLogin()throws InterruptedException{

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

        // locate the form element
        WebElement formElement = driver.findElement(By.tagName("h5"));

        // get the text of the form element
        String actualFormTitle = formElement.getText();

        // define the expected form title
        String expectedFormTitle = ("Login to your account");

        // assert for the form title
        Assert.assertEquals(actualFormTitle, expectedFormTitle, "Form title does not match expected title");

        // for email field
        WebElement staffEmail = driver.findElement(By.xpath("//*[@id=\"email\"]"));
        staffEmail.sendKeys("prasadika.l@parallax.lk");
        Thread.sleep(200);

        // for password field
        WebElement staffPassword = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        staffPassword.sendKeys("12341234");
        Thread.sleep(200);

        //for login button
        WebElement stLoginBtn = driver.findElement(By.xpath("//*[@id=\"login_panel\"]/div[9]/button"));
        stLoginBtn.click();

    }

    @Test(priority = 2)
    public void deliveryOrderProcess()throws InterruptedException{

        //search the order by date -> advance search
        WebElement clickDate = driver.findElement(By.xpath("//*[@id=\"order_date\"]"));
        clickDate.click();

        WebElement selectOption = driver.findElement(By.xpath("//div[7]/div/div[1]/ul/li[1]"));
        selectOption.click();
        Thread.sleep(800);

        // scroll down the page to select client login button
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps = 10;
        long pauseTime = 50;
        int scrollAmount = 50;

        for(int i = 0; i < steps; i ++){
            js.executeScript("window.scrollBy(0, "+scrollAmount+");");
            Thread.sleep(pauseTime);
        }

        /*//to check order status -> Processing
        // locate the form element
        WebElement formElement = driver.findElement(By.xpath("//div[3]/div[3]/div[2]/div/table/tbody/tr[6]/td[2]/span"));

        // get the text of the form element
        String actualOrderStatus = formElement.getText();

        // define the expected form title
        String expectedOrderStatus = ("Processing");

        // assert for the form title
        Assert.assertEquals(actualOrderStatus, expectedOrderStatus, "Form title does not match expected title");*/
    }

    @Test(priority = 3)
    public void collectProcessingOrder()throws InterruptedException{
        // directing to HO operations
        WebElement hoOperations = driver.findElement(By.linkText("HO Operations"));
        hoOperations.click();

        // under HO operations go to collect processing orders
        WebElement collectProcessingOrders = driver.findElement(By.linkText("Collect Processing Orders"));
        collectProcessingOrders.click();

        // enter waybill
        WebElement waybillID = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div/div[1]/div[1]/div/div[2]/div/input"));
        waybillID.sendKeys("54367856");

        // enter KGs
        WebElement weight = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div/div[1]/div[2]/div/div[2]/input"));
        weight.clear();
        weight.sendKeys("3");

        // collect order
        WebElement collectBtn = driver.findElement(By.xpath("//*[@id=\"submit\"]"));
        collectBtn.click();
        Thread.sleep(800);

        /*try {
            WebElement successMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"successAlert\"]")));
            String messageText = successMessage.getText();
            Assert.assertEquals(messageText, "Update Successful");
        } catch (Exception e) {
            Assert.fail("Success message not found or disappeared too quickly.");
        }*/
        // scroll down the page to select client login button
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps = 10;
        long pauseTime = 50;
        int scrollAmount = 50;

        for(int i = 0; i < steps; i ++){
            js.executeScript("window.scrollBy(0, "+scrollAmount+");");
            Thread.sleep(pauseTime);
        }
    }

    @Test(priority = 4)
    public void branchOperations()throws InterruptedException{
        // directing to BO operations
        WebElement boOperations = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[10]/a/span"));
        boOperations.click();

        // under BO operations go to collect dispatched orders
        WebElement collectDispatchedOrders = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[10]/ul/li[1]/a/span"));
        collectDispatchedOrders.click();

        // enter waybill
        WebElement waybillID = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div/div/div/div[1]/div/input"));
        waybillID.sendKeys("54367856");

        // select current branch
        WebElement branchDropdown = driver.findElement(By.xpath("//*[@id=\"select2-branch-container\"]"));
        branchDropdown.click();

        // search branch
        WebElement searchBranch = driver.findElement(By.xpath("//span/span/span[1]/input"));
        searchBranch.sendKeys("Colombo");
        Thread.sleep(100);

        // select searched branch
        WebElement branch = driver.findElement(By.xpath("//span/span/span[2]/ul/li"));
        branch.click();

        // click on received button
        WebElement receivedBtn = driver.findElement(By.xpath("//*[@id=\"submit\"]"));
        receivedBtn.click();

    }
    @AfterClass
    public void browserQuit(){
        if(driver != null){
            driver.quit();
        }
    }

}


