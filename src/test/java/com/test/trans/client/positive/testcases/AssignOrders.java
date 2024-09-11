package com.test.trans.client.positive.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AssignOrders {
    private WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp(){
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\HASINI\\Desktop\\Automation Trans Express Project\\TransExpressTest\\src\\main\\resources\\chromedriver.exe");
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.get("https://qa2.transexpress.parallaxtec.dev/staff-orders");
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
    public void assignOrder()throws InterruptedException{

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

        // directing to BO operations
        WebElement boOperations = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[10]/a/span"));
        boOperations.click();

        // under BO operations go to collect dispatched orders
        WebElement assignOrders = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[10]/ul/li[2]/a"));
        assignOrders.click();

        // enter waybill
        WebElement waybillID = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[6]/div[2]/form/div/div/div/div[1]/div/input"));
        waybillID.sendKeys("54367856");

        // select current branch
        WebElement assignDropdown = driver.findElement(By.xpath("//*[@id=\"select2-rider-container\"]"));
        assignDropdown.click();

        // search branch
        WebElement searchBranch = driver.findElement(By.xpath("//span/span/span[1]/input"));
        searchBranch.sendKeys("Colombo");

        // select searched branch
        WebElement assignee = driver.findElement(By.xpath("//span/span/span[2]/ul/li[2]"));
        assignee.click();

        // click on received button
        WebElement assignBtn = driver.findElement(By.xpath("//*[@id=\"submit\"]"));
        assignBtn.click();

    }


}
