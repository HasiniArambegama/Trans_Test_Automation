package com.test.trans.client.positive.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ConfirmationClientAcc {
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HASINI\\Desktop\\Automation Trans Express Project\\TransExpressTest\\src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://qa2.transexpress.parallaxtec.dev");

    }

    @Test(priority = 1)
    public void transStaffLogin()throws InterruptedException{

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
    public void clientAccountAcceptance() throws InterruptedException{
        //javascript executor for scroll-down the page
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps = 8;
        long pauseTime = 60;
        int scrollAmount = 100;

        for(int i = 0; i < steps; i ++){
            js.executeScript("window.scrollBy(0, "+scrollAmount+");");
            Thread.sleep(pauseTime);
        }

        WebElement selClientManagement = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[22]/a"));
        selClientManagement.click();

        WebElement selAllClient = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[22]/ul/li[1]/a"));
        selAllClient.click();

        //search relevant client under all client option

        WebElement selectCityDropdown = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span"));
        selectCityDropdown.click();

        WebElement searchDropdown = driver.findElement(By.xpath("//span/span/span[1]/input"));
        searchDropdown.sendKeys("Nils");
        Thread.sleep(300);

        WebElement selectOption = driver.findElement(By.xpath("//span/span/span[2]/ul/li"));
        selectOption.click();

        // define the number of steps and the pause duration between each scroll step
        int nwSteps = 10;
        long nwPauseTime = 20;
        int nwScrollAmount = 50;

        for(int i = 0; i < nwSteps; i ++){
            js.executeScript("window.scrollBy(0, "+nwScrollAmount+");");
            Thread.sleep(nwPauseTime);
        }

        WebElement acceptBtn = driver.findElement(By.xpath("//div[3]/div[3]/div[2]/div/table/tbody/tr/td[2]/div/span[2]/a"));
        acceptBtn.click();

        // locate the form element
        WebElement formElement = driver.findElement(By.tagName("h2"));

        // get the text of the form element
        String actualTitle = formElement.getText();

        // define the expected form title
        String expectedTitle = ("Are you sure?");

        // assert for the form title
        Assert.assertEquals(actualTitle, expectedTitle, "Form title does not match expected title");
        Thread.sleep(500);

        //confirm alert message
        /*WebElement cancleBtn = driver.findElement(By.xpath("//div[8]/div[7]/button"));
        cancleBtn.click();*/

        WebElement yesBtn = driver.findElement(By.xpath("//div[8]/div[7]/div/button"));
        yesBtn.click();

        // after confirm

        // define the number of steps and the pause duration between each scroll step
        int nwSteps1 = 20;
        long nwPauseTime1 = 20;
        int nwScrollAmount1 = 70;

        for(int i = 0; i < nwSteps1; i ++){
            js.executeScript("window.scrollBy(0, "+nwScrollAmount1+");");
            Thread.sleep(nwPauseTime1);
        }

        WebElement clientCommission = driver.findElement(By.xpath("//div/div[2]/div[5]/div[2]/div/div/input"));
        clientCommission.clear();
        clientCommission.sendKeys("1");

        WebElement allowedUsers = driver.findElement(By.xpath("//*[@id=\"max_account\"]"));
        allowedUsers.sendKeys("1");


        WebElement updateBtn = driver.findElement(By.xpath("//*[@id=\"submit-button\"]"));
        updateBtn.click();


    }


}
