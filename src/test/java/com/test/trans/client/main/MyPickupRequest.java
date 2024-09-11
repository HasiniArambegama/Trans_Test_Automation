package com.test.trans.client.main;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MyPickupRequest {
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
    public void navigateMyPickupRequests(){

        driver.get("https://release.transexpress.parallaxtec.dev/pickup-requests");
        // locate the form element
        WebElement titleElement = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[1]/div[1]/div/h4"));

        // get the text of the form element
        String actualTitle = titleElement.getText();

        // define the expected form title
        String expectedTitle = "Home - Pickup Request";

        // assert for the form title
        Assert.assertEquals(actualTitle, expectedTitle, "Title does not match expected title");

        // to print form title
        System.out.println("Actual title is :" +actualTitle +"."+ " Therefore, actual title is displayed as expected.");
    }

    @Test(priority = 3)
    public void advancedSearch() throws InterruptedException{

        WebElement reqDate = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[1]/div/input"));
        reqDate.click();

        WebElement selReqDate = driver.findElement(By.xpath("/html/body/div[7]/div/div[1]/ul/li[4]"));
        selReqDate.click();

        WebElement status = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/span[1]"));
        status.click();

        WebElement selStatus = driver.findElement(By.xpath("/html/body/span/span/span[1]/input"));
        selStatus.click();

        WebElement statusPass = driver.findElement(By.xpath("/html/body/span/span/span[2]/ul/li[3]"));
        statusPass.click();

        WebElement branch = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[3]/div/span/span[1]/span"));
        branch.click();

        WebElement branchSearch = driver.findElement(By.xpath("/html/body/span/span/span[1]/input"));
        branchSearch.click();
        branchSearch.sendKeys("Pa");

        WebElement selBranch = driver.findElement(By.xpath("/html/body/span/span/span[2]/ul/li[4]"));
        selBranch.click();

        WebElement searchBtn = driver.findElement(By.xpath("//*[@id=\"filter\"]"));
        searchBtn.click();

        // to scroll down the page
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps = 16;
        long pauseTime = 50;
        int scrollAmount = 25;

        for (int i = 0; i < steps; i++) {
            js.executeScript("window.scrollBy(0, " + scrollAmount + ");");
            Thread.sleep(pauseTime);
        }

    }

    @Test(priority = 4)
    public void filterSearch()throws InterruptedException{
        WebElement filterSearch = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[6]/div[2]/div/div/div[2]/div[1]/label/input"));
        filterSearch.click();
        filterSearch.sendKeys("Mini van");
        Thread.sleep(500);
    }

    @Test(priority = 5)
    public void viewOrderDetails()throws InterruptedException {
        WebElement filterSearch = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[6]/div[2]/div/div/div[2]/div[1]/label/input"));
        filterSearch.clear();

        WebElement view = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[6]/div[2]/div/div/div[3]/div[3]/div[2]/div/table/tbody/tr[1]/td[2]/ul/li/a"));
        view.click();

        WebElement view1 = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[6]/div[2]/div/div/div[3]/div[3]/div[2]/div/table/tbody/tr[1]/td[2]/ul/li/ul/div/li/span/a"));
        view1.click();

        // to scroll down the page
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps = 16;
        long pauseTime = 50;
        int scrollAmount = 25;

        for (int i = 0; i < steps; i++) {
            js.executeScript("window.scrollBy(0, " + scrollAmount + ");");
            Thread.sleep(pauseTime);
        }

        JavascriptExecutor jsObj = (JavascriptExecutor) driver;
        jsObj.executeScript("window.scrollTo(0, 0);");

        driver.navigate().back();
    }

    @Test(priority = 6)
    public void reportDownload(){

        // to check excel download option
        WebElement excel = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[6]/div[2]/div/div/div[2]/div[2]/a[2]"));
        excel.click();

        // to check pdf option
        WebElement pdf= driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[6]/div[2]/div/div/div[2]/div[2]/a[3]"));
        pdf.click();

        // to check print option
        WebElement print = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[6]/div[2]/div/div/div[2]/div[2]/a[1]"));
        print.click();

    }

}
