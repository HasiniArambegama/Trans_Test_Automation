package com.test.trans.staff.main;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class OrderRates {
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HASINI\\Desktop\\Automation Trans Express Project\\TransExpressTest\\src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://release.transexpress.parallaxtec.dev/");

    }

    @Test(priority = 1)
    public void staffLogin()throws InterruptedException{

        // scroll down the page to select staff login button
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

        // for email field
        WebElement staffEmail = driver.findElement(By.xpath("//*[@id=\"email\"]"));
        staffEmail.sendKeys("staff1@gmail.com");
        Thread.sleep(200);

        // for password field
        WebElement staffPassword = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        staffPassword.sendKeys("12345678");
        Thread.sleep(200);

        //for login button
        WebElement stLoginBtn = driver.findElement(By.xpath("//*[@id=\"login_panel\"]/div[9]/button"));
        stLoginBtn.click();
    }

    @Test(priority = 14)
    public void testBranchSuccessRates()throws InterruptedException{

        // navigate to branch dashboard
        WebElement branchDash = driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/div[2]/div/ul/li[3]/a"));
        branchDash.click();

        WebElement searchByBranch2 = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/form/div/div[2]/div[1]/div/div/div/span/span[1]/span"));
        searchByBranch2.click();

        WebElement selectBranch = driver.findElement(By.xpath("/html/body/span/span/span[2]/ul/li[1]"));
        selectBranch.click();

        WebElement nextBtn = driver.findElement(By.xpath("//*[@id=\"submit-button\"]"));
        nextBtn.click();

        Thread.sleep(3000);

        // to scroll down the page
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps = 30;
        long pauseTime = 30;
        int scrollAmount = 20;

        for (int i = 0; i < steps; i++) {
            js.executeScript("window.scrollBy(0, " + scrollAmount + ");");
            Thread.sleep(pauseTime);
        }

        Thread.sleep(100000);

        // get all the containers for the branch success rates
        List<WebElement> containers = driver.findElements(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[3]"));

        for (WebElement container : containers) {
            // extract month name
            String month = container.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[3]/div[1]/div/div/div[1]/h3")).getText();

            // extract assigned orders and express deliveries
            int assignedOrders = Integer.parseInt(container.findElement(By.xpath("//*[@id=\"tile-0\"]/div/div[1]/span[1]")).getText().split(" - ")[1]);
            int expressDeliveries = Integer.parseInt(container.findElement(By.xpath("//*[@id=\"tile-0\"]/div/div[1]/span[2]")).getText().split(" - ")[1]);

            // calculate the expected success rate
            double expectedSuccessRate = (double) expressDeliveries / assignedOrders * 100;

            // extract the displayed success rate
            double displayedSuccessRate = Double.parseDouble(container.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[3]/div[1]/div/div/div[2]/i")).getText().replace("%", ""));

            // compare the expected and displayed success rates
            Assert.assertEquals(displayedSuccessRate, expectedSuccessRate, 0.01, "Success rate mismatch for month: " + month);

            // print the month and its success rate
            System.out.printf("%s: Calculated Success Rate = %.2f%%, Displayed Success Rate = %.2f%%\n", month, expectedSuccessRate, displayedSuccessRate);

        }
    }
}
