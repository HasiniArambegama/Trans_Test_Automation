package com.test.trans.staff.main;

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


public class DashboardStatistics {
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

    // to verify dashboard statistics

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
            System.out.println("Extracted no of orders in processing state:" + extractedNumber);
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
            System.out.println("All orders processing state stats are matched to dashboard processing state statistics");
        }else {
            System.out.println("All order processing state stats are different than the dashboard processing state statistics");
        }
    }

    //return to branch status
    @Test(priority = 4)
    public void returnedToBranchStats()throws InterruptedException{
        // to check return to branch order metrics
        WebElement allOrders = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[5]/a"));
        allOrders.click();

        WebElement searchByStatus = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/ul/li/input"));
        searchByStatus.click();

        WebElement returnToBranch = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[8]"));
        returnToBranch.click();

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
        System.out.println("No of orders in return to branch state: " + elementText);

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
            System.out.println("Extracted no of orders in return to branch state:" + extractedNumber);
        }

        //scroll from bottom to top
        js2.executeScript("window.scrollTo(0, 0);");

        // to navigate the dashboard
        WebElement dashBoard = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[2]/a"));
        dashBoard.click();

        // check dashboard's order processing statistics are equals to all orders processing stats
        WebElement dashReturnToBranch = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[1]/div/div[2]/div/div/div[1]/h3"));

        String returnToBranchOrders = dashReturnToBranch.getText();

        System.out.println("Processing Stat:" + returnToBranchOrders);

        if(extractedNumber.equals(returnToBranchOrders)){
            System.out.println("All orders return to branch state stats are matched to dashboard return to branch state statistics");
        }else {
            System.out.println("All order return to branch state stats are different than the dashboard return to branch state statistics");
        }
    }

    // reschedule status
    @Test(priority = 5)
    public void rescheduleStats()throws InterruptedException{
        // to check rescheduled order metrics
        WebElement allOrders = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[5]/a"));
        allOrders.click();

        WebElement searchByStatus = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/ul/li/input"));
        searchByStatus.click();

        WebElement reschedule = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[9]"));
        reschedule.click();

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
        System.out.println("No of orders in rescheduled state: " + elementText);

        // Extract the number using a regular expression
        Pattern pattern = Pattern.compile("of\\s([\\d,]+)\\sentries");
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
        js2.executeScript("window.scrollTo(0, 0);");

        // to navigate the dashboard
        WebElement dashBoard = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[2]/a"));
        dashBoard.click();

        // check dashboard's rescheduled order statistics are equals to all orders rescheduled orders stats
        WebElement dashRescheduled = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[1]/div/div[3]/div/div/div[1]/h3"));

        String rescheduledOrders = dashRescheduled.getText();

        System.out.println("Reschedule Stat:" + rescheduledOrders);

        if(extractedNumber.equals(rescheduledOrders)){
            System.out.println("All orders rescheduled state stats are matched to dashboard rescheduled state statistics");
        }else {
            System.out.println("All order rescheduled state stats are different than the dashboard rescheduled state statistics");
        }
    }

    // failed to deliver status
    @Test(priority = 6)
    public void failedToDeliverStats()throws InterruptedException{

        // to check failed to deliver order metrics
        WebElement allOrders = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[5]/a"));
        allOrders.click();

        WebElement searchByStatus = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/ul/li/input"));
        searchByStatus.click();

        WebElement failedToDeli = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[10]"));
        failedToDeli.click();

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

        // to check all failed to deliver orders count

        // Locate the web element containing the text
        WebElement element = driver.findElement(By.xpath("//*[@id=\"datatable-basic_info\"]"));
        Thread.sleep(1000);

        // Get the text of the web element
        String elementText = element.getText();
        System.out.println("No of orders in rescheduled state: " + elementText);

        // Extract the number using a regular expression
        Pattern pattern = Pattern.compile("of\\s([\\d,]+)\\sentries");
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
        js2.executeScript("window.scrollTo(0, 0);");

        // to navigate the dashboard
        WebElement dashBoard = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[2]/a"));
        dashBoard.click();

        // check dashboard's failed to deliver orders statistics are equals to all orders failed to deliver orders stats
        WebElement dashFailedToDeli = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[1]/div/div[4]/div/div/div[1]/h3"));

        String failedToDeliverOrders = dashFailedToDeli.getText();

        System.out.println("Failed to deliver Stat:" + failedToDeliverOrders);

        if(extractedNumber.equals(failedToDeliverOrders)){
            System.out.println("All orders failed to deliver state stats are matched to dashboard failed to deliver state statistics");
        }else {
            System.out.println("All order failed to deliver state stats are different than the dashboard failed to deliver state statistics");
        }
    }

    // returned to Ho status
    @Test(priority = 7)
    public void returnedToHOStats()throws InterruptedException{

        // to check returned to Ho order metrics
        WebElement allOrders = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[5]/a"));
        allOrders.click();

        WebElement searchByStatus = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/ul/li/input"));
        searchByStatus.click();

        WebElement returnToHO = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[11]"));
        returnToHO.click();

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

        // to check all failed to deliver orders count

        // Locate the web element containing the text
        WebElement element = driver.findElement(By.xpath("//*[@id=\"datatable-basic_info\"]"));
        Thread.sleep(1000);

        // Get the text of the web element
        String elementText = element.getText();
        System.out.println("No of orders in rescheduled state: " + elementText);

        // Extract the number using a regular expression
        Pattern pattern = Pattern.compile("of\\s([\\d,]+)\\sentries");
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
        js2.executeScript("window.scrollTo(0, 0);");

        // to navigate the dashboard
        WebElement dashBoard = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[2]/a"));
        dashBoard.click();

        // check dashboard's return to HO order statistics are equals to all orders return to HO orders stats
        WebElement dashReturnToHO = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[1]/div/div[5]/div/div/div[1]/h3"));

        String returnToHOOrders = dashReturnToHO.getText();

        System.out.println("Return to HO Stat:" + returnToHOOrders);

        if(extractedNumber.equals(returnToHOOrders)){
            System.out.println("All orders return to HO state stats are matched to dashboard return to HO state statistics");
        }else {
            System.out.println("All order return to HO state stats are different than the dashboard return to HO state statistics");
        }

    }

    // received at HO status
    @Test(priority = 8)
    public void receivedAtOHStat()throws InterruptedException{

        // to check received at Ho order metrics
        WebElement allOrders = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[5]/a"));
        allOrders.click();

        WebElement searchByStatus = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/ul/li/input"));
        searchByStatus.click();

        WebElement receivedAtHO = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[12]"));
        receivedAtHO.click();

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

        // to check all failed to deliver orders count

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
        js2.executeScript("window.scrollTo(0, 0);");

        // to navigate the dashboard
        WebElement dashBoard = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[2]/a"));
        dashBoard.click();

        // check dashboard's return to HO order statistics are equals to all orders return to HO orders stats
        WebElement dashReceivedAtHO = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[1]/div/div[6]/div/div/div[1]/h3"));

        String receivedAtHOOrders = dashReceivedAtHO.getText();

        System.out.println("Received at HO Stat:" + receivedAtHOOrders);

        if(extractedNumber.equals(receivedAtHOOrders)){
            System.out.println("All orders received at HO state stats are matched to dashboard received at HO state statistics");
        }else {
            System.out.println("All order received at HO state stats are different than the dashboard received at HO state statistics");
        }

    }

    // return to client status
    @Test(priority = 9)
    public void returnToClientStat()throws InterruptedException{

        // to check return to client order metrics
        WebElement allOrders = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[5]/a"));
        allOrders.click();

        WebElement searchByStatus = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/ul/li/input"));
        searchByStatus.click();

        WebElement returnToClient = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[13]"));
        returnToClient.click();

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

        // to check all failed to deliver orders count

        // Locate the web element containing the text
        WebElement element = driver.findElement(By.xpath("//*[@id=\"datatable-basic_info\"]"));
        Thread.sleep(1000);

        // Get the text of the web element
        String elementText = element.getText();
        System.out.println("No of orders in returned to client state: " + elementText);

        // Extract the number using a regular expression
        Pattern pattern = Pattern.compile("of\\s([\\d,]+)\\sentries");
        Matcher matcher = pattern.matcher(elementText);

        String extractedNumber = "";
        if (matcher.find()) {
            extractedNumber = matcher.group(1);
        }

        // Check if extractedNumber is empty
        if (extractedNumber.isEmpty()) {
            System.out.println("No number found in the text.");
        } else {
            System.out.println("Extracted no of orders in returned to client state:" + extractedNumber);
        }

        //scroll from bottom to top
        js2.executeScript("window.scrollTo(0, 0);");

        // to navigate the dashboard
        WebElement dashBoard = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[2]/a"));
        dashBoard.click();

        // check dashboard's return to HO order statistics are equals to all orders return to HO orders stats
        WebElement dashReturnedToClient = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[1]/div/div[7]/div/div/div[1]/h3"));

        String returnedToClientOrders = dashReturnedToClient.getText();

        System.out.println("Returned to client Stat:" + returnedToClientOrders);

        if(extractedNumber.equals(returnedToClientOrders)){
            System.out.println("All orders returned to client state stats are matched to dashboard returned to client state statistics");
        }else {
            System.out.println("All orders returned to client state stats are different than the dashboard returned to client state statistics");
        }

    }

    // re-delivery status
    @Test(priority = 10)
    public void redeliveryStat()throws InterruptedException{

        WebElement allOrders = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[5]/a"));
        allOrders.click();

        WebElement searchByStatus = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/ul/li/input"));
        searchByStatus.click();

        WebElement redelivery = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[14]"));
        redelivery.click();

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

        // Locate the web element containing the text
        WebElement element = driver.findElement(By.xpath("//*[@id=\"datatable-basic_info\"]"));
        Thread.sleep(1000);

        // Get the text of the web element
        String elementText = element.getText();
        System.out.println("No of orders in returned to client state: " + elementText);

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
            System.out.println("Extracted no of orders in returned to client state:" + extractedNumber);
        }

        //scroll from bottom to top
        js2.executeScript("window.scrollTo(0, 0);");

        // to navigate the dashboard
        WebElement dashBoard = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[2]/a"));
        dashBoard.click();

        WebElement dashRedelivery = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[1]/div/div[8]/div/div/div[1]/h3"));

        String redeliveryOrders = dashRedelivery.getText();

        System.out.println("Returned to client Stat:" + redeliveryOrders);

        if(extractedNumber.equals(redeliveryOrders)){
            System.out.println("All orders redelivery state stats are matched to dashboard returned to client state statistics");
        }else {
            System.out.println("All orders redelivery to client state stats are different than the dashboard returned to client state statistics");
        }

    }

    // received by client status
    @Test(priority = 11)
    public void receivedByClientStat()throws InterruptedException{

        WebElement allOrders = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[5]/a"));
        allOrders.click();

        WebElement searchByStatus = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/ul/li/input"));
        searchByStatus.click();

        WebElement receivedByClient = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[15]"));
        receivedByClient.click();

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

        // Locate the web element containing the text
        WebElement element = driver.findElement(By.xpath("//*[@id=\"datatable-basic_info\"]"));
        Thread.sleep(1000);

        // Get the text of the web element
        String elementText = element.getText();
        System.out.println("No of orders in returned to client state: " + elementText);

        // Extract the number using a regular expression
        Pattern pattern = Pattern.compile("of\\s([\\d,]+)\\sentries");
        Matcher matcher = pattern.matcher(elementText);

        String extractedNumber = "";
        if (matcher.find()) {
            extractedNumber = matcher.group(1);
        }

        // Check if extractedNumber is empty
        if (extractedNumber.isEmpty()) {
            System.out.println("No number found in the text.");
        } else {
            System.out.println("Extracted no of orders in returned to client state:" + extractedNumber);
        }

        //scroll from bottom to top
        js2.executeScript("window.scrollTo(0, 0);");

        // to navigate the dashboard
        WebElement dashBoard = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[2]/a"));
        dashBoard.click();

        WebElement dashRecieved = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[1]/div/div[9]/div/div/div[1]/h3"));

        String receivedClient = dashRecieved.getText();

        System.out.println("Returned to client Stat:" + receivedClient);

        if(extractedNumber.equals(receivedClient)){
            System.out.println("All orders received by client state stats are matched to dashboard received by client state statistics");
        }else {
            System.out.println("All orders received by client state stats are different than the dashboard received by client state statistics");
        }
    }

    // cancelled status
    @Test(priority = 12)
    public void cancelledStat()throws InterruptedException{

        WebElement allOrders = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[5]/a"));
        allOrders.click();

        WebElement searchByStatus = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/ul/li/input"));
        searchByStatus.click();

        WebElement cancelled = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[16]"));
        cancelled.click();

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

        // Locate the web element containing the text
        WebElement element = driver.findElement(By.xpath("//*[@id=\"datatable-basic_info\"]"));
        Thread.sleep(1000);

        // Get the text of the web element
        String elementText = element.getText();
        System.out.println("No of orders in returned to client state: " + elementText);

        // Extract the number using a regular expression
        Pattern pattern = Pattern.compile("of\\s([\\d,]+)\\sentries");
        Matcher matcher = pattern.matcher(elementText);

        String extractedNumber = "";
        if (matcher.find()) {
            extractedNumber = matcher.group(1);
        }

        // Check if extractedNumber is empty
        if (extractedNumber.isEmpty()) {
            System.out.println("No number found in the text.");
        } else {
            System.out.println("Extracted no of orders in returned to client state:" + extractedNumber);
        }

        //scroll from bottom to top
        js2.executeScript("window.scrollTo(0, 0);");

        // to navigate the dashboard
        WebElement dashBoard = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[2]/a"));
        dashBoard.click();

        WebElement dashCancelled = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[1]/div/div[10]/div/div/div[1]/h3"));

        String cancelledOrders = dashCancelled.getText();

        System.out.println("Returned to client Stat:" + cancelledOrders);

        if(extractedNumber.equals(cancelledOrders)){
            System.out.println("All orders cancelled order state stats are matched to dashboard cancelled order state statistics");
        }else {
            System.out.println("All orders cancelled order state stats are different than the dashboard cancelled order state statistics");
        }
    }

    // purchased by tranex status
    @Test(priority = 13)
    public void purchasedByTranexStat()throws InterruptedException{

        WebElement allOrders = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[5]/a"));
        allOrders.click();

        WebElement searchByStatus = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/ul/li/input"));
        searchByStatus.click();

        WebElement purchased = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[17]"));
        purchased.click();

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

        // Locate the web element containing the text
        WebElement element = driver.findElement(By.xpath("//*[@id=\"datatable-basic_info\"]"));
        Thread.sleep(1000);

        // Get the text of the web element
        String elementText = element.getText();
        System.out.println("No of orders in returned to client state: " + elementText);

        // Extract the number using a regular expression
        Pattern pattern = Pattern.compile("of\\s([\\d,]+)\\sentries");
        Matcher matcher = pattern.matcher(elementText);

        String extractedNumber = "";
        if (matcher.find()) {
            extractedNumber = matcher.group(1);
        }

        // Check if extractedNumber is empty
        if (extractedNumber.isEmpty()) {
            System.out.println("No number found in the text.");
        } else {
            System.out.println("Extracted no of orders in returned to client state:" + extractedNumber);
        }

        //scroll from bottom to top
        js2.executeScript("window.scrollTo(0, 0);");

        // to navigate the dashboard
        WebElement dashBoard = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[2]/a"));
        dashBoard.click();

        WebElement dashPurchased = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[1]/div/div[11]/div/div/div[1]/h3"));

        String purchasedOrders = dashPurchased.getText();

        System.out.println("Purchased by Trans Stat:" + purchasedOrders);

        if(extractedNumber.equals(purchasedOrders)){
            System.out.println("All orders purchased order state stats are matched to dashboard purchased order state statistics");
        }else {
            System.out.println("All orders purchased order state stats are different than the dashboard purchased order state statistics");
        }


    }

    //return to HO(ID) status
    @Test(priority = 14)
    public void returnTOHOIDStat()throws InterruptedException{
        WebElement allOrders = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[5]/a"));
        allOrders.click();

        WebElement searchByStatus = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/ul/li/input"));
        searchByStatus.click();

        WebElement returnID = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[18]"));
        returnID.click();

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

        // Locate the web element containing the text
        WebElement element = driver.findElement(By.xpath("//*[@id=\"datatable-basic_info\"]"));
        Thread.sleep(1000);

        // Get the text of the web element
        String elementText = element.getText();
        System.out.println("No of orders in returned to client state: " + elementText);

        // Extract the number using a regular expression
        Pattern pattern = Pattern.compile("of\\s([\\d,]+)\\sentries");
        Matcher matcher = pattern.matcher(elementText);

        String extractedNumber = "";
        if (matcher.find()) {
            extractedNumber = matcher.group(1);
        }

        // Check if extractedNumber is empty
        if (extractedNumber.isEmpty()) {
            System.out.println("No number found in the text.");
        } else {
            System.out.println("Extracted no of orders in returned to client state:" + extractedNumber);
        }

        //scroll from bottom to top
        js2.executeScript("window.scrollTo(0, 0);");

        // to navigate the dashboard
        WebElement dashBoard = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[2]/a"));
        dashBoard.click();

        WebElement dashReturnID = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[1]/div/div[12]/div/div/div[1]/h3"));

        String returnIDOrders = dashReturnID.getText();

        System.out.println("Purchased by Trans Stat:" + returnIDOrders);

        if(extractedNumber.equals(returnIDOrders)){
            System.out.println("All orders return ID order state stats are matched to dashboard return ID order state statistics");
        }else {
            System.out.println("All orders return ID order state stats are different than the dashboard return ID order state statistics");
        }


    }

    //received by HO(ID) status
    @Test(priority = 15)
    public void receivedByHOIDStat()throws InterruptedException{
        WebElement allOrders = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[5]/a"));
        allOrders.click();

        WebElement searchByStatus = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/ul/li/input"));
        searchByStatus.click();

        WebElement receivedID = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[19]"));
        receivedID.click();

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

        // Locate the web element containing the text
        WebElement element = driver.findElement(By.xpath("//*[@id=\"datatable-basic_info\"]"));
        Thread.sleep(1000);

        // Get the text of the web element
        String elementText = element.getText();
        System.out.println("No of orders in returned to client state: " + elementText);

        // Extract the number using a regular expression
        Pattern pattern = Pattern.compile("of\\s([\\d,]+)\\sentries");
        Matcher matcher = pattern.matcher(elementText);

        String extractedNumber = "";
        if (matcher.find()) {
            extractedNumber = matcher.group(1);
        }

        // Check if extractedNumber is empty
        if (extractedNumber.isEmpty()) {
            System.out.println("No number found in the text.");
        } else {
            System.out.println("Extracted no of orders in returned to client state:" + extractedNumber);
        }

        //scroll from bottom to top
        js2.executeScript("window.scrollTo(0, 0);");

        // to navigate the dashboard
        WebElement dashBoard = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[2]/a"));
        dashBoard.click();

        WebElement dashReceivedID = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[1]/div/div[13]/div/div/div[1]/h3"));

        String receivedIDOrders = dashReceivedID.getText();

        System.out.println("Received ID Stat:" + receivedIDOrders);

        if(extractedNumber.equals(receivedIDOrders)){
            System.out.println("All orders received ID order state stats are matched to dashboard received ID order state statistics");
        }else {
            System.out.println("All orders received ID order state stats are different than the dashboard received ID order state statistics");
        }


    }

    //HO clearance status
    @Test(priority = 16)
    public void hoClearanceStat()throws InterruptedException{

        WebElement allOrders = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[5]/a"));
        allOrders.click();

        WebElement searchByStatus = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/ul/li/input"));
        searchByStatus.click();

        WebElement hoClearance = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[20]"));
        hoClearance.click();

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

        // Locate the web element containing the text
        WebElement element = driver.findElement(By.xpath("//*[@id=\"datatable-basic_info\"]"));
        Thread.sleep(1000);

        // Get the text of the web element
        String elementText = element.getText();
        System.out.println("No of orders in returned to client state: " + elementText);

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
            System.out.println("Extracted no of orders in returned to client state:" + extractedNumber);
        }

        //scroll from bottom to top
        js2.executeScript("window.scrollTo(0, 0);");

        // to navigate the dashboard
        WebElement dashBoard = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[2]/a"));
        dashBoard.click();

        WebElement dashClearance = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[1]/div/div[13]/div/div/div[1]/h3"));

        String clearanceOrders = dashClearance.getText();

        System.out.println("Clearance Stat:" + clearanceOrders);

        if(extractedNumber.equals(clearanceOrders)){
            System.out.println("All orders clearance order state stats are matched to dashboard clearance order state statistics");
        }else {
            System.out.println("All orders clearance ID order state stats are different than the dashboard clearance order state statistics");
        }

    }

    // re-assign rider status
    @Test(priority = 17)
    public void reAssignRiderStat()throws InterruptedException{

        WebElement allOrders = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[5]/a"));
        allOrders.click();

        WebElement searchByStatus = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/ul/li/input"));
        searchByStatus.click();

        WebElement hoClearance = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[20]"));
        hoClearance.click();

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

        // Locate the web element containing the text
        WebElement element = driver.findElement(By.xpath("//*[@id=\"datatable-basic_info\"]"));
        Thread.sleep(1000);

        // Get the text of the web element
        String elementText = element.getText();
        System.out.println("No of orders in returned to client state: " + elementText);

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
            System.out.println("Extracted no of orders in returned to client state:" + extractedNumber);
        }

        //scroll from bottom to top
        js2.executeScript("window.scrollTo(0, 0);");

        // to navigate the dashboard
        WebElement dashBoard = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[2]/a"));
        dashBoard.click();

        WebElement dashClearance = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[1]/div/div[13]/div/div/div[1]/h3"));

        String clearanceOrders = dashClearance.getText();

        System.out.println("Clearance Stat:" + clearanceOrders);

        if(extractedNumber.equals(clearanceOrders)){
            System.out.println("All orders clearance order state stats are matched to dashboard clearance order state statistics");
        }else {
            System.out.println("All orders clearance ID order state stats are different than the dashboard clearance order state statistics");
        }


    }

    // collected n dispatched to destination status
    @Test(priority = 18)
    public void collectDispatchedDestiStat()throws InterruptedException{

        WebElement allOrders = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[5]/a"));
        allOrders.click();

        WebElement searchByStatus = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/ul/li/input"));
        searchByStatus.click();

        WebElement dispatched = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[2]"));
        dispatched.click();

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

        // Locate the web element containing the text
        WebElement element = driver.findElement(By.xpath("//*[@id=\"datatable-basic_info\"]"));
        Thread.sleep(1000);

        // Get the text of the web element
        String elementText = element.getText();
        System.out.println("No of orders in returned to client state: " + elementText);

        // Extract the number using a regular expression
        Pattern pattern = Pattern.compile("of\\s([\\d,]+)\\sentries");
        Matcher matcher = pattern.matcher(elementText);

        String extractedNumber = "";
        if (matcher.find()) {
            extractedNumber = matcher.group(1);
        }

        // Check if extractedNumber is empty
        if (extractedNumber.isEmpty()) {
            System.out.println("No number found in the text.");
        } else {
            System.out.println("Extracted no of orders in returned to client state:" + extractedNumber);
        }

        //scroll from bottom to top
        js2.executeScript("window.scrollTo(0, 0);");

        // to navigate the dashboard
        WebElement dashBoard = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[2]/a"));
        dashBoard.click();

        WebElement dashDispatched = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[1]/div/div[16]/div/div/div[1]/h3"));

        String dispatchedOrders = dashDispatched.getText();

        System.out.println("Collect & Dispatched Stat:" + dispatchedOrders);

        if(extractedNumber.equals(dispatchedOrders)){
            System.out.println("All orders dispatched order state stats are matched to dashboard dispatched order state statistics");
        }else {
            System.out.println("All orders dispatched order state stats are different than the dashboard dispatched order state statistics");
        }


    }

    // received at destination status
    @Test(priority = 19)
    public void receivedAtDestiStat()throws InterruptedException{

        WebElement allOrders = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[5]/a"));
        allOrders.click();

        WebElement searchByStatus = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/ul/li/input"));
        searchByStatus.click();

        WebElement desti = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[3]"));
        desti.click();

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

        // Locate the web element containing the text
        WebElement element = driver.findElement(By.xpath("//*[@id=\"datatable-basic_info\"]"));
        Thread.sleep(1000);

        // Get the text of the web element
        String elementText = element.getText();
        System.out.println("No of orders in returned to client state: " + elementText);

        // Extract the number using a regular expression
        Pattern pattern = Pattern.compile("of\\s([\\d,]+)\\sentries");
        Matcher matcher = pattern.matcher(elementText);

        String extractedNumber = "";
        if (matcher.find()) {
            extractedNumber = matcher.group(1);
        }

        // Check if extractedNumber is empty
        if (extractedNumber.isEmpty()) {
            System.out.println("No number found in the text.");
        } else {
            System.out.println("Extracted no of orders in returned to client state:" + extractedNumber);
        }

        //scroll from bottom to top
        js2.executeScript("window.scrollTo(0, 0);");

        // to navigate the dashboard
        WebElement dashBoard = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[2]/a"));
        dashBoard.click();

        WebElement dashDesti = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[1]/div/div[17]/div/div/div[1]/h3"));

        String distiOrders = dashDesti.getText();

        System.out.println("Received at Destination Stat:" + distiOrders);

        if(extractedNumber.equals(distiOrders)){
            System.out.println("All orders destination order state stats are matched to dashboard destination order state statistics");
        }else {
            System.out.println("All orders destination order state stats are different than the dashboard destination order state statistics");
        }


    }

    // out for delivery status
    @Test(priority = 20)
    public void outForDeliStat()throws InterruptedException{

        WebElement allOrders = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[5]/a"));
        allOrders.click();

        WebElement searchByStatus = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/ul/li/input"));
        searchByStatus.click();

        WebElement outDeli = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[4]"));
        outDeli.click();

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

        // Locate the web element containing the text
        WebElement element = driver.findElement(By.xpath("//*[@id=\"datatable-basic_info\"]"));
        Thread.sleep(1000);

        // Get the text of the web element
        String elementText = element.getText();
        System.out.println("No of orders in returned to client state: " + elementText);

        // Extract the number using a regular expression
        Pattern pattern = Pattern.compile("of\\s([\\d,]+)\\sentries");
        Matcher matcher = pattern.matcher(elementText);

        String extractedNumber = "";
        if (matcher.find()) {
            extractedNumber = matcher.group(1);
        }

        // Check if extractedNumber is empty
        if (extractedNumber.isEmpty()) {
            System.out.println("No number found in the text.");
        } else {
            System.out.println("Extracted no of orders in returned to client state:" + extractedNumber);
        }

        //scroll from bottom to top
        js2.executeScript("window.scrollTo(0, 0);");

        // to navigate the dashboard
        WebElement dashBoard = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[2]/a"));
        dashBoard.click();

        WebElement dashOutDeli = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[1]/div/div[18]/div/div/div[1]/h3"));

        String delutOrders = dashOutDeli.getText();

        System.out.println("Received at Destination Stat:" + delutOrders);

        if(extractedNumber.equals(delutOrders)){
            System.out.println("All orders out for delivery state stats are matched to dashboard out for delivery state statistics");
        }else {
            System.out.println("All orders out for delivery state stats are different than the dashboard out for delivery state statistics");
        }
    }

    // different destination status
    @Test(priority = 21)
    public void differentDestiStat()throws InterruptedException{

        WebElement allOrders = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[5]/a"));
        allOrders.click();

        WebElement searchByStatus = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/ul/li/input"));
        searchByStatus.click();

        WebElement difDesti = driver.findElement(By.xpath("//span/span/span/ul/li[5]"));
        difDesti.click();

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

        // Locate the web element containing the text
        WebElement element = driver.findElement(By.xpath("//*[@id=\"datatable-basic_info\"]"));
        Thread.sleep(1000);

        // Get the text of the web element
        String elementText = element.getText();
        System.out.println("No of orders in returned to client state: " + elementText);

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
            System.out.println("Extracted no of orders in returned to client state:" + extractedNumber);
        }

        //scroll from bottom to top
        js2.executeScript("window.scrollTo(0, 0);");

        // to navigate the dashboard
        WebElement dashBoard = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[2]/a"));
        dashBoard.click();

        WebElement dashDifDesti = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[1]/div/div[19]/div/div/div[1]/h3"));

        String difDestiOrders = dashDifDesti.getText();

        System.out.println("Different Destination Stat:" + difDestiOrders);

        if(extractedNumber.equals(difDestiOrders)){
            System.out.println("All orders different destination state stats are matched to dashboard different destination order state statistics");
        }else {
            System.out.println("All orders different destination state stats are different than the dashboard different destination order state statistics");
        }
    }

    // delivered status
    @Test(priority = 22)
    public void deliveredStat()throws InterruptedException{

        WebElement allOrders = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[5]/a"));
        allOrders.click();

        WebElement searchByStatus = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/ul/li/input"));
        searchByStatus.click();

        WebElement delivered = driver.findElement(By.xpath(""));
        delivered.click();

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

        // Locate the web element containing the text
        WebElement element = driver.findElement(By.xpath("//*[@id=\"datatable-basic_info\"]"));
        Thread.sleep(1000);

        // Get the text of the web element
        String elementText = element.getText();
        System.out.println("No of orders in returned to client state: " + elementText);

        // Extract the number using a regular expression
        Pattern pattern = Pattern.compile("of\\s([\\d,]+)\\sentries");
        Matcher matcher = pattern.matcher(elementText);

        String extractedNumber = "";
        if (matcher.find()) {
            extractedNumber = matcher.group(1);
        }

        // Check if extractedNumber is empty
        if (extractedNumber.isEmpty()) {
            System.out.println("No number found in the text.");
        } else {
            System.out.println("Extracted no of orders in returned to client state:" + extractedNumber);
        }

        //scroll from bottom to top
        js2.executeScript("window.scrollTo(0, 0);");

        // to navigate the dashboard
        WebElement dashBoard = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[2]/a"));
        dashBoard.click();

        WebElement dashDelivered = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[1]/div/div[20]/div/div/div[1]/h3"));

        String deliveredOrders = dashDelivered.getText();

        System.out.println("Delivered Stat:" + deliveredOrders);

        if(extractedNumber.equals(deliveredOrders)){
            System.out.println("All orders delivered order state stats are matched to dashboard delivered order state statistics");
        }else {
            System.out.println("All orders delivered order state stats are different than the dashboard delivered order state statistics");
        }
    }

    // partial delivered status
    @Test(priority = 23)
    public void partialDelivered()throws InterruptedException{

        WebElement allOrders = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[5]/a"));
        allOrders.click();

        WebElement searchByStatus = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/ul/li/input"));
        searchByStatus.click();

        WebElement pDeli = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[7]"));
        pDeli.click();

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

        // Locate the web element containing the text
        WebElement element = driver.findElement(By.xpath("//*[@id=\"datatable-basic_info\"]"));
        Thread.sleep(1000);

        // Get the text of the web element
        String elementText = element.getText();
        System.out.println("No of orders in returned to client state: " + elementText);

        // Extract the number using a regular expression
        Pattern pattern = Pattern.compile("of\\s([\\d,]+)\\sentries");
        Matcher matcher = pattern.matcher(elementText);

        String extractedNumber = "";
        if (matcher.find()) {
            extractedNumber = matcher.group(1);
        }

        // Check if extractedNumber is empty
        if (extractedNumber.isEmpty()) {
            System.out.println("No number found in the text.");
        } else {
            System.out.println("Extracted no of orders in returned to client state:" + extractedNumber);
        }

        //scroll from bottom to top
        js2.executeScript("window.scrollTo(0, 0);");

        // to navigate the dashboard
        WebElement dashBoard = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[2]/a"));
        dashBoard.click();

        WebElement dashPdeli = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[1]/div/div[21]/div/div/div[1]/h3"));

        String partialDeliOrders = dashPdeli.getText();

        System.out.println("Partial Delivery Stat:" + partialDeliOrders);

        if(extractedNumber.equals(partialDeliOrders)){
            System.out.println("All orders partially delivered order state stats are matched to dashboard partially delivered state statistics");
        }else {
            System.out.println("All orders partially delivered state stats are different than the dashboard partially delivered state statistics");
        }
    }
















}
