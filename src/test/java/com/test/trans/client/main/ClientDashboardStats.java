package com.test.trans.client.main;

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

public class ClientDashboardStats {
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
        email.sendKeys("nilsStores1@gmail.com");
        Thread.sleep(200);

        // for password field
        WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        password.sendKeys("nils@1235");
        Thread.sleep(200);

        //for login button
        WebElement loginBtn = driver.findElement(By.xpath("//*[@id=\"login_panel\"]/div[9]/button"));
        loginBtn.click();
    }

    @Test(priority = 2)
    public void dashboardNavigation(){

        // to navigate the dashboard
        WebElement dashBoard = driver.findElement(By.xpath("//div[4]/div/div[1]/div/div[2]/div/ul/li[3]/a"));
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
        String expectedSubTitle = "Status Statistic";

        boolean isPageSubTitlePresent = actualSubTitle.contains(expectedSubTitle);
        Assert.assertTrue(isPageSubTitlePresent, "Dashboard page sub title text does not match");

        // to print page title
        System.out.println("Dashboard Sub Title is: " + actualSubTitle);
    }

    // verify the dashboard order statuses statistics

    // processing status
    @Test(priority = 3)
    public void processingStats() throws InterruptedException {

        // to check processing order metrics
        WebElement myOrders = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[5]"));
        myOrders.click();

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

        WebElement searchByStatus = driver.findElement(By.xpath("//div[4]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/ul/li/input"));
        searchByStatus.click();
        searchByStatus.clear();

        WebElement processing = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[1]"));
        processing.click();

        // to scroll down the page
        JavascriptExecutor js2 = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps2 = 10;
        long pauseTime2 = 100;
        int scrollAmount2 = 50;

        for (int i = 0; i < steps2; i++) {
            js2.executeScript("window.scrollBy(0, " + scrollAmount2 + ");");
            Thread.sleep(pauseTime2);
        }

        WebElement searchBtn1 = driver.findElement(By.xpath("//*[@id=\"filter\"]"));
        searchBtn1.click();
        Thread.sleep(3000);

        // to scroll down the page
        JavascriptExecutor js3 = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps3 = 10;
        long pauseTime3 = 200;
        int scrollAmount3 = 100;

        for (int i = 0; i < steps3; i++) {
            js3.executeScript("window.scrollBy(0, " + scrollAmount3 + ");");
            Thread.sleep(pauseTime3);
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
        js3.executeScript("window.scrollTo(0, 0);");

        // to navigate the dashboard
        WebElement dashBoard = driver.findElement(By.xpath("//div[4]/div/div[1]/div/div[2]/div/ul/li[3]/a"));
        dashBoard.click();
        Thread.sleep(6000);

        // check dashboard's order processing statistics are equals to all orders processing stats
        WebElement dashProcessing = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[1]/div/div[1]/div/div/div[1]/h3"));

        String processingOrders = dashProcessing.getText();

        System.out.println("Processing Stat:" + processingOrders);

        if(extractedNumber.equals(processingOrders)){
            System.out.println("My order statistics and dashboard statistics are matched");
        }else {
            System.out.println("My order statistics and dashboard statistics are not matched");
        }
    }

   // collect and dispatched to destination status
    @Test(priority = 4)
    public void collectDispatchedDestiStats()throws InterruptedException{

        WebElement myOrders = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[5]"));
        myOrders.click();

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

        WebElement searchByStatus = driver.findElement(By.xpath("//div[4]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/ul/li/input"));
        searchByStatus.click();
        searchByStatus.clear();

        WebElement dispatched = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[2]"));
        dispatched.click();

        // to scroll down the page
        JavascriptExecutor js2 = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps2 = 10;
        long pauseTime2 = 100;
        int scrollAmount2 = 50;

        for (int i = 0; i < steps2; i++) {
            js2.executeScript("window.scrollBy(0, " + scrollAmount2 + ");");
            Thread.sleep(pauseTime2);
        }

        WebElement searchBtn1 = driver.findElement(By.xpath("//*[@id=\"filter\"]"));
        searchBtn1.click();
        Thread.sleep(3000);

        // to scroll down the page
        JavascriptExecutor js3 = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps3 = 10;
        long pauseTime3 = 200;
        int scrollAmount3 = 100;

        for (int i = 0; i < steps3; i++) {
            js3.executeScript("window.scrollBy(0, " + scrollAmount3 + ");");
            Thread.sleep(pauseTime3);
        }

        Thread.sleep(1000);

        // Locate the web element containing the text
        WebElement element = driver.findElement(By.xpath("//*[@id=\"datatable-basic_info\"]"));
        Thread.sleep(1000);

        // Get the text of the web element
        String elementText = element.getText();
        System.out.println("No of orders in collect & dispatched to destination state: " + elementText);

        // Extract the number using a regular expression
        Pattern pattern = Pattern.compile("of\\s(\\d+)\\sentries");
        Matcher matcher = pattern.matcher(elementText);

        String extractedNumber = "";
        if (matcher.find()) {
            extractedNumber = matcher.group(1);
        }

        // Check if extractedNumber is empty
        if (extractedNumber.isEmpty()) {
            System.out.println("No number found in the text.");
        } else {
            System.out.println("Extracted no of orders in collect & dispatched to destination state:" + extractedNumber);
        }

        //scroll from bottom to top
        js3.executeScript("window.scrollTo(0, 0);");

        // to navigate the dashboard
        WebElement dashBoard = driver.findElement(By.xpath("//div[4]/div/div[1]/div/div[2]/div/ul/li[3]/a"));
        dashBoard.click();
        Thread.sleep(6000);

        WebElement dashDispatched = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[1]/div/div[2]/div/div[2]/div[1]/h3"));

        String dispatchedOrders = dashDispatched.getText();

        System.out.println("Collect & Dispatched Stat:" + dispatchedOrders);

        if(extractedNumber.equals(dispatchedOrders)){
            System.out.println("My order statistics and dashboard statistics are matched");
        }else {
            System.out.println("My order statistics and dashboard statistics are not matched");
        }


    }

    // received at destination status
    @Test(priority = 5)
    public void receivedAtDestiStats()throws InterruptedException{

        WebElement myOrders = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[5]"));
        myOrders.click();

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

        WebElement searchByStatus = driver.findElement(By.xpath("//div[4]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/ul/li/input"));
        searchByStatus.click();
        searchByStatus.clear();

        WebElement desti = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[3]"));
        desti.click();

        // to scroll down the page
        JavascriptExecutor js2 = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps2 = 10;
        long pauseTime2 = 100;
        int scrollAmount2 = 50;

        for (int i = 0; i < steps2; i++) {
            js2.executeScript("window.scrollBy(0, " + scrollAmount2 + ");");
            Thread.sleep(pauseTime2);
        }

        WebElement searchBtn1 = driver.findElement(By.xpath("//*[@id=\"filter\"]"));
        searchBtn1.click();
        Thread.sleep(3000);

        // to scroll down the page
        JavascriptExecutor js3 = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps3 = 10;
        long pauseTime3 = 200;
        int scrollAmount3 = 100;

        for (int i = 0; i < steps3; i++) {
            js3.executeScript("window.scrollBy(0, " + scrollAmount3 + ");");
            Thread.sleep(pauseTime3);
        }

        Thread.sleep(1000);

        // Locate the web element containing the text
        WebElement element = driver.findElement(By.xpath("//*[@id=\"datatable-basic_info\"]"));
        Thread.sleep(1000);

        // Get the text of the web element
        String elementText = element.getText();
        System.out.println("No of orders in received at destination state: " + elementText);

        // Extract the number using a regular expression
        Pattern pattern = Pattern.compile("of\\s(\\d+)\\sentries");
        Matcher matcher = pattern.matcher(elementText);

        String extractedNumber = "";
        if (matcher.find()) {
            extractedNumber = matcher.group(1);
        }

        // Check if extractedNumber is empty
        if (extractedNumber.isEmpty()) {
            System.out.println("No number found in the text.");
        } else {
            System.out.println("Extracted no of state:" + extractedNumber);
        }

        //scroll from bottom to top
        js3.executeScript("window.scrollTo(0, 0);");

        // to navigate the dashboard
        WebElement dashBoard = driver.findElement(By.xpath("//div[4]/div/div[1]/div/div[2]/div/ul/li[3]/a"));
        dashBoard.click();
        Thread.sleep(6000);

        WebElement dashDesti = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[1]/div/div[3]/div/div[2]/div[1]/h3"));

        String distiOrders = dashDesti.getText();

        System.out.println("Received at Destination Stat:" + distiOrders);

        if(extractedNumber.equals(distiOrders)){
            System.out.println("My order statistics and dashboard statistics are matched");
        }else {
            System.out.println("My order statistics and dashboard statistics are not matched");
        }


    }

    // out for delivery status
    @Test(priority = 6)
    public void outForDeliStat()throws InterruptedException{

        WebElement myOrders = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[5]"));
        myOrders.click();

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

        WebElement searchByStatus = driver.findElement(By.xpath("//div[4]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/ul/li/input"));
        searchByStatus.click();
        searchByStatus.clear();

        WebElement outDeli = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[4]"));
        outDeli.click();

        // to scroll down the page
        JavascriptExecutor js2 = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps2 = 10;
        long pauseTime2 = 100;
        int scrollAmount2 = 50;

        for (int i = 0; i < steps2; i++) {
            js2.executeScript("window.scrollBy(0, " + scrollAmount2 + ");");
            Thread.sleep(pauseTime2);
        }

        WebElement searchBtn = driver.findElement(By.xpath("//div[4]/div/div[2]/div[2]/div[5]/div[2]/form/div[3]/div/input[1]"));
        searchBtn.click();
        Thread.sleep(3000);

        // to scroll down the page
        JavascriptExecutor js3 = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps3 = 10;
        long pauseTime3 = 200;
        int scrollAmount3 = 100;

        for (int i = 0; i < steps3; i++) {
            js3.executeScript("window.scrollBy(0, " + scrollAmount3 + ");");
            Thread.sleep(pauseTime3);
        }

        Thread.sleep(1000);

        // Locate the web element containing the text
        WebElement element = driver.findElement(By.xpath("//*[@id=\"datatable-basic_info\"]"));
        Thread.sleep(1000);

        // Get the text of the web element
        String elementText = element.getText();
        System.out.println("No of orders in out for delivery state: " + elementText);

        // Extract the number using a regular expression
        Pattern pattern = Pattern.compile("of\\s(\\d+)\\sentries");
        Matcher matcher = pattern.matcher(elementText);

        String extractedNumber = "";
        if (matcher.find()) {
            extractedNumber = matcher.group(1);
        }

        // Check if extractedNumber is empty
        if (extractedNumber.isEmpty()) {
            System.out.println("No number found in the text.");
        } else {
            System.out.println("Extracted no of orders in out for delivery state:" + extractedNumber);
        }

        //scroll from bottom to top
        js3.executeScript("window.scrollTo(0, 0);");

        // to navigate the dashboard
        WebElement dashBoard = driver.findElement(By.xpath("//div[4]/div/div[1]/div/div[2]/div/ul/li[3]/a"));
        dashBoard.click();
        Thread.sleep(6000);

        WebElement dashOutDeli = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[1]/div/div[4]/div/div[2]/div[1]/h3"));

        String delutOrders = dashOutDeli.getText();

        System.out.println("Out for delivery Stat:" + delutOrders);

        if(extractedNumber.equals(delutOrders)){
            System.out.println("My order statistics and dashboard statistics are matched");
        }else {
            System.out.println("My order statistics and dashboard statistics are not matched");
        }
    }

    // rescheduled status
    @Test(priority = 7)
    public void rescheduleStats()throws InterruptedException{
        // to check rescheduled order metrics
        WebElement myOrders = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[5]"));
        myOrders.click();

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

        WebElement searchByStatus = driver.findElement(By.xpath("//div[4]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/ul/li/input"));
        searchByStatus.click();
        searchByStatus.clear();

        WebElement reschedule = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[9]"));
        reschedule.click();

        // to scroll down the page
        JavascriptExecutor js2 = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps2 = 10;
        long pauseTime2 = 100;
        int scrollAmount2 = 50;

        for (int i = 0; i < steps2; i++) {
            js2.executeScript("window.scrollBy(0, " + scrollAmount2 + ");");
            Thread.sleep(pauseTime2);
        }

        WebElement searchBtn = driver.findElement(By.xpath("//div[4]/div/div[2]/div[2]/div[5]/div[2]/form/div[3]/div/input[1]"));
        searchBtn.click();
        Thread.sleep(3000);

        // to scroll down the page
        JavascriptExecutor js3 = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps3 = 10;
        long pauseTime3 = 200;
        int scrollAmount3 = 100;

        for (int i = 0; i < steps3; i++) {
            js3.executeScript("window.scrollBy(0, " + scrollAmount3 + ");");
            Thread.sleep(pauseTime3);
        }

        Thread.sleep(1000);

        // to check all processing orders count

        // Locate the web element containing the text
        WebElement element = driver.findElement(By.xpath("//*[@id=\"datatable-basic_info\"]"));
        Thread.sleep(1000);

        // Get the text of the web element
        String elementText = element.getText();
        System.out.println("No of orders in rescheduled state: " + elementText);

        // Extract the number using a regular expression
        Pattern pattern = Pattern.compile("of\\s(\\d+)\\sentries");
        Matcher matcher = pattern.matcher(elementText);

        String extractedNumber = "";
        if (matcher.find()) {
            extractedNumber = matcher.group(1);
        }

        // Check if extractedNumber is empty
        if (extractedNumber.isEmpty()) {
            System.out.println("No number found in the text.");
        } else {
            System.out.println("Extracted no of orders in rescheduled state:" + extractedNumber);
        }

        //scroll from bottom to top
        js3.executeScript("window.scrollTo(0, 0);");

        // to navigate the dashboard
        WebElement dashBoard = driver.findElement(By.xpath("//div[4]/div/div[1]/div/div[2]/div/ul/li[3]/a"));
        dashBoard.click();
        Thread.sleep(6000);

        // check dashboard's rescheduled order statistics are equals to all orders rescheduled orders stats
        WebElement dashRescheduled = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[1]/div/div[3]/div/div/div[1]/h3"));

        String rescheduledOrders = dashRescheduled.getText();

        System.out.println("Reschedule Stat:" + rescheduledOrders);

        if(extractedNumber.equals(rescheduledOrders)){
            System.out.println("My order statistics and dashboard statistics are matched");
        }else {
            System.out.println("My order statistics and dashboard statistics are not matched");
        }
    }

    // failed to delivery status
    @Test(priority = 8)
    public void failedToDeliverStats()throws InterruptedException{

        // to check failed to deliver order metrics
        WebElement myOrders = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[5]"));
        myOrders.click();

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

        WebElement searchByStatus = driver.findElement(By.xpath("//div[4]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/ul/li/input"));
        searchByStatus.click();
        searchByStatus.clear();

        WebElement failedToDeli = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[10]"));
        failedToDeli.click();

        // to scroll down the page
        JavascriptExecutor js2 = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps2 = 10;
        long pauseTime2 = 100;
        int scrollAmount2 = 50;

        for (int i = 0; i < steps2; i++) {
            js2.executeScript("window.scrollBy(0, " + scrollAmount2 + ");");
            Thread.sleep(pauseTime2);
        }

        WebElement searchBtn = driver.findElement(By.xpath("//div[4]/div/div[2]/div[2]/div[5]/div[2]/form/div[3]/div/input[1]"));
        searchBtn.click();
        Thread.sleep(3000);

        // to scroll down the page
        JavascriptExecutor js3 = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps3 = 10;
        long pauseTime3 = 200;
        int scrollAmount3 = 100;

        for (int i = 0; i < steps3; i++) {
            js3.executeScript("window.scrollBy(0, " + scrollAmount3 + ");");
            Thread.sleep(pauseTime3);
        }

        Thread.sleep(1000);

        // to check all failed to deliver orders count

        // Locate the web element containing the text
        WebElement element = driver.findElement(By.xpath("//*[@id=\"datatable-basic_info\"]"));
        Thread.sleep(1000);

        // Get the text of the web element
        String elementText = element.getText();
        System.out.println("No of orders in failed to delivered state: " + elementText);

        // Extract the number using a regular expression
        Pattern pattern = Pattern.compile("of\\s(\\d+)\\sentries");
        Matcher matcher = pattern.matcher(elementText);

        String extractedNumber = "";
        if (matcher.find()) {
            extractedNumber = matcher.group(1);
        }

        // Check if extractedNumber is empty
        if (extractedNumber.isEmpty()) {
            System.out.println("No number found in the text.");
        } else {
            System.out.println("Extracted no of orders in failed to delivered state:" + extractedNumber);
        }

        //scroll from bottom to top
        js3.executeScript("window.scrollTo(0, 0);");

        // to navigate the dashboard
        WebElement dashBoard = driver.findElement(By.xpath("//div[4]/div/div[1]/div/div[2]/div/ul/li[3]/a"));
        dashBoard.click();
        Thread.sleep(6000);

        // to scroll down the page
        JavascriptExecutor js4 = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps4 = 4;
        long pauseTime4 = 10;
        int scrollAmount4 = 10;

        for (int i = 0; i < steps4; i++) {
            js4.executeScript("window.scrollBy(0, " + scrollAmount4 + ");");
            Thread.sleep(pauseTime4);
        }

        // check dashboard's failed to deliver orders statistics are equals to all orders failed to deliver orders stats
        WebElement dashFailedToDeli = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[1]/div/div[4]/div/div/div[1]/h3"));

        String failedToDeliverOrders = dashFailedToDeli.getText();

        System.out.println("Failed to deliver Stat:" + failedToDeliverOrders);

        if(extractedNumber.equals(failedToDeliverOrders)){
            System.out.println("My order statistics and dashboard statistics are matched");
        }else {
            System.out.println("My order statistics and dashboard statistics are not matched");
        }
    }

    //verify to navigate home page by click on the home option.
    @Test(priority = 9)
    public void navigateHome(){

        //for  Home / Dashboard option
        WebElement homeIcon = driver.findElement(By.xpath("//div[3]/div/div[2]/div[1]/div[3]/ul/li[1]/a"));
        homeIcon.click();
    }
}
