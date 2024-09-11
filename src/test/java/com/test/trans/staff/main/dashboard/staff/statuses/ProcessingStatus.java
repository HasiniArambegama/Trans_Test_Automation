package com.test.trans.staff.main.dashboard.staff.statuses;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcessingStatus {
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HASINI\\Desktop\\Automation Trans Express Project\\TransExpressTest\\src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://qa2.transexpress.parallaxtec.dev");

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
    public void dashboardNavigation(){

        // to navigate the dashboard
        WebElement dashBoard = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[2]/a"));
        dashBoard.click();

        // verify the page titles
        // locate the page title element
        WebElement dashboardTitle = driver.findElement(By.xpath("//div[3]/div/div[2]/div[1]/div[1]/div/h4"));

        // get the text of the page title element
        String actualTitle = dashboardTitle.getText();

        // define the expected page title
        String expectedTitle = "Home - Dashboard";

        boolean isPageTitlePresent = actualTitle.contains(expectedTitle);
        Assert.assertTrue(isPageTitlePresent, "Dashboard page main title text does not match");

        // to print page title
        System.out.println("Dashboard Main Title is: " + actualTitle);

        // locate page subtitle element
        WebElement subTitle = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[1]/h4"));

        // get the text of the page subtitle element
        String actualSubTitle = subTitle.getText();

        // define the expected page subtitle
        String expectedSubTitle = "Statistics";

        boolean isPageSubTitlePresent = actualSubTitle.contains(expectedSubTitle);
        Assert.assertTrue(isPageSubTitlePresent, "Dashboard page sub title text does not match");

        // to print page title
        System.out.println("Dashboard Sub Title is: " + actualSubTitle);
    }

    // processing status
    @Test(priority = 3)
    public void processingStats() throws InterruptedException {

        // to check processing order metrics
        WebElement allOrders = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[5]/a"));
        allOrders.click();

        WebElement searchByStatus = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/ul/li/input"));
        searchByStatus.click();

        WebElement processing = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[1]"));
        processing.click();

        // to scroll down the page
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps = 4;
        long pauseTime = 50;
        int scrollAmount = 25;

        for (int i = 0; i < steps; i++) {
            js.executeScript("window.scrollBy(0, " + scrollAmount + ");");
            Thread.sleep(pauseTime);
        }

        WebElement searchBtn = driver.findElement(By.xpath("//*[@id=\"filter\"]"));
        searchBtn.click();
        Thread.sleep(3000);

        // to scroll down the page
        JavascriptExecutor js2 = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps2 = 10;
        long pauseTime2 = 200;
        int scrollAmount2 = 100;

        for (int i = 0; i < steps2; i++) {
            js2.executeScript("window.scrollBy(0, " + scrollAmount2 + ");");
            Thread.sleep(pauseTime2);
        }

        Thread.sleep(1000);

        // to check all processing orders count

        // Locate the web element containing the text
        WebElement element = driver.findElement(By.xpath("//*[@id=\"datatable-basic_info\"]"));
        Thread.sleep(1000);

        // Get the text of the web element
        String elementText = element.getText();
        System.out.println("No of orders in processing state: " + elementText);

        // Extract the number using a regular expression
        Pattern pattern = Pattern.compile("of\\s(\\d+)\\sentries");
        Matcher matcher = pattern.matcher(elementText);

        String extractedNumber = "";
        if (matcher.find()) {
            extractedNumber = matcher.group(1).replace(",", "");
        }

        // Check if extractedNumber is empty
        if (extractedNumber.isEmpty()) {
            System.out.println("No number found in the text.");
        } else {
            System.out.println("Extracted no of stat:" + extractedNumber);
        }

        //scroll from bottom to top
        js2.executeScript("window.scrollTo(0, 0);");

        // to navigate the dashboard
        WebElement dashBoard = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[2]/a"));
        dashBoard.click();

        // check dashboard's order processing statistics are equals to all orders processing stats
        WebElement dashProcessing = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[1]/div/div[1]/div/div/div[1]/h3"));

        String processingOrders = dashProcessing.getText();

        System.out.println("Processing Stat:" + processingOrders);

        if(extractedNumber.equals(processingOrders)){
            System.out.println("All order statistics and dashboard statistics are matched");
        }else {
            System.out.println("All order statistics and dashboard statistics are not matched");
        }
    }
}
