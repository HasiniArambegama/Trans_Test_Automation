package com.test.trans.client.positive.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddNewOrder {
    private WebDriver driver;

    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HASINI\\Desktop\\Automation Trans Express Project\\TransExpressTest\\src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://qa2.transexpress.parallaxtec.dev");
    }

    // after successful account confirmation by staff-side,
    // login to new client's account to add a new order

    @Test (priority = 1)
    public void addNewOrder()throws InterruptedException{
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
        email.sendKeys("nilsStores@gmail.com");
        Thread.sleep(200);

        // for password field
        WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        password.sendKeys("nils@1235");
        Thread.sleep(200);

        //for login button
        WebElement loginBtn = driver.findElement(By.xpath("//*[@id=\"login_panel\"]/div[9]/button"));
        loginBtn.click();

        //after successful login,

        WebElement addNewOrder = driver.findElement(By.xpath("//div[4]/div/div[1]/div/div[2]/div/ul/li[4]/a"));
        addNewOrder.click();

        WebElement waybillNo = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[1]/div[2]/form/div[2]/fieldset[1]/div/div[1]/div/div/input"));
        waybillNo.sendKeys("54367856");
        Thread.sleep(200);

        WebElement orderNo = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[1]/div[2]/form/div[2]/fieldset[1]/div/div[2]/div/div/input"));
        orderNo.sendKeys("1");
        Thread.sleep(200);

        WebElement cusName = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[1]/div[2]/form/div[2]/fieldset[2]/div[1]/div/input"));
        cusName.sendKeys("Test Customer");
        Thread.sleep(200);

        WebElement cusAddress = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[1]/div[2]/form/div[2]/fieldset[2]/div[2]/div/input"));
        cusAddress.sendKeys("Colombo");
        Thread.sleep(200);

        WebElement cusFPhoneNo = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[1]/div[2]/form/div[2]/fieldset[2]/div[3]/div/div/div/input"));
        cusFPhoneNo.sendKeys("07144556387");
        Thread.sleep(200);

        WebElement cusSPhoneNo = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[1]/div[2]/form/div[2]/fieldset[2]/div[4]/div/div/div/input"));
        cusSPhoneNo.sendKeys("07188556457");
        Thread.sleep(200);

        WebElement orderDes = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[1]/div[2]/form/div[2]/fieldset[2]/div[5]/div/textarea"));
        orderDes.sendKeys("Test order 01");
        Thread.sleep(200);

        //drop-down
        WebElement selectCityDropdown = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[1]/div[2]/form/div[2]/fieldset[3]/div[1]/div/div/div/span[1]/span[1]/span"));
        selectCityDropdown.click();

        WebElement searchDropdown = driver.findElement(By.xpath("//span/span/span[1]/input"));
        searchDropdown.sendKeys("Colom");
        Thread.sleep(10000);

        WebElement selectOption = driver.findElement(By.xpath("//span/span/span[2]/ul/li[3]"));
        selectOption.click();

        WebElement codAmount = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[1]/div[2]/form/div[2]/fieldset[3]/div[2]/div/div/div/input"));
        codAmount.sendKeys("6000");
        Thread.sleep(200);

        WebElement remarks = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[1]/div[2]/form/div[2]/fieldset[3]/div[3]/div/div/div/textarea"));
        remarks.sendKeys("Test remarks for order 01");
        Thread.sleep(200);

        WebElement placeAnOrderBtn = driver.findElement(By.xpath("//*[@id=\"submit-button\"]"));
        placeAnOrderBtn.click();

    }

    @AfterClass
    public void browserQuit(){
        if(driver != null){
            driver.quit();
        }
    }

}
