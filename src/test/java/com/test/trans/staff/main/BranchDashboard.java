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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BranchDashboard {
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

 /*   @Test(priority = 2)
    public void dashboardNavigation()throws InterruptedException{

        // to navigate the dashboard
        WebElement branchDashboard = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[4]/a"));
        branchDashboard.click();

        // verify the page titles
        // locate the page title element
        WebElement branchDashboardTitle = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/form/div/div[1]/h5"));

        // get the text of the page title element
        String actualTitle = branchDashboardTitle.getText();

        // define the expected page title
        String expectedTitle = "Preselect";

        boolean isPageTitlePresent = actualTitle.contains(expectedTitle);
        Assert.assertTrue(isPageTitlePresent, "Branch dashboard title text does not match");

        // to print page title
        System.out.println("Branch dashboard Title is: " + actualTitle);

    }

    @Test(priority = 3)
    public void branchDashboardStatistics1()throws InterruptedException{

        // to check collect and dispatched to destination state matrix
        WebElement allOrders = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[5]/a"));
        allOrders.click();

        WebElement searchByStatus = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/ul/li/input"));
        searchByStatus.click();

        WebElement status1 = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[2]"));
        status1.click();

        WebElement searchByBranch1 = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[3]/div/span/span[1]/span/ul/li/input"));
        searchByBranch1.click();

        WebElement branch = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[1]"));
        branch.click();

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
        System.out.println("No of orders in selected status: " + elementText);

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
            System.out.println("Extracted no of stats:" + extractedNumber);
        }

        //scroll from bottom to top
        js2.executeScript("window.scrollTo(0, 0);");

        WebElement branchDash = driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/div[2]/div/ul/li[3]/a"));
        branchDash.click();

        WebElement searchByBranch2 = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/form/div/div[2]/div[1]/div/div/div/span/span[1]/span"));
        searchByBranch2.click();

        WebElement selectBranch = driver.findElement(By.xpath("/html/body/span/span/span[2]/ul/li[1]"));
        selectBranch.click();

        WebElement nextBtn = driver.findElement(By.xpath("//*[@id=\"submit-button\"]"));
        nextBtn.click();

        Thread.sleep(3000);

        // check dashboard's order processing statistics are equals to all orders processing stats
        WebElement branchStatusMatrix = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[1]/div[2]/div/div/div[1]/h3"));

        String orderStatus1 = branchStatusMatrix.getText();

        System.out.println("Dashboard statistic for selected branch :" + orderStatus1);

        if(extractedNumber.equals(orderStatus1)){
            System.out.println("All order statistics and branch dashboard statistics are matched.");
        }else {
            System.out.println("All order statistics and branch dashboard statistics are not matched.");
        }
    }

    @Test(priority = 4)
    public void branchDashboardStatistics2()throws InterruptedException{

        // to check out for delivery to destination state matrix
        WebElement allOrders = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[5]/a"));
        allOrders.click();

        WebElement searchByStatus = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/ul/li/input"));
        searchByStatus.click();

        WebElement status2 = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[4]"));
        status2.click();

        WebElement searchByBranch = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[3]/div/span/span[1]/span/ul/li/input"));
        searchByBranch.click();

        WebElement branch = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[1]"));
        branch.click();

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
        long pauseTime2 = 100;
        int scrollAmount2 = 50;

        for (int i = 0; i < steps2; i++) {
            js2.executeScript("window.scrollBy(0, " + scrollAmount2 + ");");
            Thread.sleep(pauseTime2);
        }

        Thread.sleep(5000);

        // to check all out for delivery orders count

        // Locate the web element containing the text
        WebElement element = driver.findElement(By.xpath("//*[@id=\"datatable-basic_info\"]"));
        Thread.sleep(1000);

        // Get the text of the web element
        String elementText = element.getText();
        System.out.println("No of orders in selected status: " + elementText);

        // Extract the number using a regular expression
        Pattern pattern = Pattern.compile("of\\s([\\d,]+)\\sentries");
        Matcher matcher = pattern.matcher(elementText);

        String extractedNumber = "";
        if (matcher.find()) {
            extractedNumber = matcher.group(1).replace(",", "");
        }

        // Check if extractedNumber is empty
        if (extractedNumber.isEmpty()) {
            System.out.println("No number found in the text.");
        } else {
            System.out.println("Extracted no of stats:" + extractedNumber);
        }

        //scroll from bottom to top
        js2.executeScript("window.scrollTo(0, 0);");

        WebElement branchDash = driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/div[2]/div/ul/li[3]/a"));
        branchDash.click();

        WebElement searchByBranch3 = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/form/div/div[2]/div[1]/div/div/div/span/span[1]/span"));
        searchByBranch3.click();

        WebElement selectBranch = driver.findElement(By.xpath("/html/body/span/span/span[2]/ul/li[1]"));
        selectBranch.click();

        WebElement nextBtn = driver.findElement(By.xpath("//*[@id=\"submit-button\"]"));
        nextBtn.click();

        Thread.sleep(3000);

        WebElement branchStatusMatrix = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[1]/div[4]/div/div/div[1]/h3"));

        String orderStatus1 = branchStatusMatrix.getText();

        System.out.println("Dashboard statistic for selected branch :" + orderStatus1);

        if(extractedNumber.equals(orderStatus1)){
            System.out.println("All order statistics and branch dashboard statistics are matched.");
        }else {
            System.out.println("All order statistics and branch dashboard statistics are not matched.");
        }
    }

    @Test(priority = 5)
    public void branchDashboardStatistics3()throws InterruptedException{

        // to check out for delivery to destination state matrix
        WebElement allOrders = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[5]/a"));
        allOrders.click();

        WebElement searchByStatus = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/ul/li/input"));
        searchByStatus.click();

        WebElement status3 = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[6]"));
        status3.click();

        WebElement searchByBranch = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[3]/div/span/span[1]/span/ul/li/input"));
        searchByBranch.click();

        WebElement branch = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[1]"));
        branch.click();

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
        int steps2 = 20;
        long pauseTime2 = 200;
        int scrollAmount2 = 100;

        for (int i = 0; i < steps2; i++) {
            js2.executeScript("window.scrollBy(0, " + scrollAmount2 + ");");
            Thread.sleep(pauseTime2);
        }

        Thread.sleep(5000);

        // to check all out for delivery orders count

        // Locate the web element containing the text
        WebElement element = driver.findElement(By.xpath("//*[@id=\"datatable-basic_info\"]"));
        Thread.sleep(1000);

        // Get the text of the web element
        String elementText = element.getText();
        System.out.println("No of orders in selected status: " + elementText);

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
            System.out.println("Extracted no of stats:" + extractedNumber);
        }

        //scroll from bottom to top
        js2.executeScript("window.scrollTo(0, 0);");

        WebElement branchDash = driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/div[2]/div/ul/li[3]/a"));
        branchDash.click();

        WebElement searchByBranch3 = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/form/div/div[2]/div[1]/div/div/div/span/span[1]/span"));
        searchByBranch3.click();

        WebElement selectBranch = driver.findElement(By.xpath("/html/body/span/span/span[2]/ul/li[1]"));
        selectBranch.click();

        WebElement nextBtn = driver.findElement(By.xpath("//*[@id=\"submit-button\"]"));
        nextBtn.click();

        Thread.sleep(3000);

        WebElement branchStatusMatrix = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[1]/div[6]/div/div/div[1]/h3"));

        String orderStatus3 = branchStatusMatrix.getText();

        System.out.println("Dashboard statistic for selected branch :" + orderStatus3);

        if(extractedNumber.equals(orderStatus3)){
            System.out.println("All order statistics and branch dashboard statistics are matched.");
        }else {
            System.out.println("All order statistics and branch dashboard statistics are not matched.");
        }
    }

    @Test(priority = 6)
    public void branchDashboardStatistics4()throws InterruptedException{

        // to check out for delivery to destination state matrix
        WebElement allOrders = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[5]/a"));
        allOrders.click();

        WebElement searchByStatus = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/ul/li/input"));
        searchByStatus.click();

        WebElement status3 = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[7]"));
        status3.click();

        WebElement searchByBranch = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[3]/div/span/span[1]/span/ul/li/input"));
        searchByBranch.click();

        WebElement branch = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[1]"));
        branch.click();

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
        int steps2 = 20;
        long pauseTime2 = 200;
        int scrollAmount2 = 100;

        for (int i = 0; i < steps2; i++) {
            js2.executeScript("window.scrollBy(0, " + scrollAmount2 + ");");
            Thread.sleep(pauseTime2);
        }

        Thread.sleep(5000);

        // to check all out for delivery orders count

        // Locate the web element containing the text
        WebElement element = driver.findElement(By.xpath("//*[@id=\"datatable-basic_info\"]"));
        Thread.sleep(1000);

        // Get the text of the web element
        String elementText = element.getText();
        System.out.println("No of orders in selected status: " + elementText);

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
            System.out.println("Extracted no of stats:" + extractedNumber);
        }

        //scroll from bottom to top
        js2.executeScript("window.scrollTo(0, 0);");

        WebElement branchDash = driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/div[2]/div/ul/li[3]/a"));
        branchDash.click();

        WebElement searchByBranch3 = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/form/div/div[2]/div[1]/div/div/div/span/span[1]/span"));
        searchByBranch3.click();

        WebElement selectBranch = driver.findElement(By.xpath("/html/body/span/span/span[2]/ul/li[1]"));
        selectBranch.click();

        WebElement nextBtn = driver.findElement(By.xpath("//*[@id=\"submit-button\"]"));
        nextBtn.click();

        Thread.sleep(3000);

        WebElement branchStatusMatrix = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[1]/div[7]/div/div/div[1]/h3"));

        String orderStatus3 = branchStatusMatrix.getText();

        System.out.println("Dashboard statistic for selected branch :" + orderStatus3);

        if(extractedNumber.equals(orderStatus3)){
            System.out.println("All order statistics and branch dashboard statistics are matched.");
        }else {
            System.out.println("All order statistics and branch dashboard statistics are not matched.");
        }
    }

    @Test(priority = 7)
    public void branchDashboardStatistics5()throws InterruptedException{

        // to check partially delivered to destination state matrix
        WebElement allOrders = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[5]/a"));
        allOrders.click();

        WebElement searchByStatus = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/ul/li/input"));
        searchByStatus.click();

        WebElement status3 = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[9]"));
        status3.click();

        WebElement searchByBranch = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[3]/div/span/span[1]/span/ul/li/input"));
        searchByBranch.click();

        WebElement branch = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[1]"));
        branch.click();

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
        int steps2 = 20;
        long pauseTime2 = 200;
        int scrollAmount2 = 100;

        for (int i = 0; i < steps2; i++) {
            js2.executeScript("window.scrollBy(0, " + scrollAmount2 + ");");
            Thread.sleep(pauseTime2);
        }

        Thread.sleep(5000);

        // to check all out for delivery orders count

        // Locate the web element containing the text
        WebElement element = driver.findElement(By.xpath("//*[@id=\"datatable-basic_info\"]"));
        Thread.sleep(1000);

        // Get the text of the web element
        String elementText = element.getText();
        System.out.println("No of orders in selected status: " + elementText);

        // Extract the number using a regular expression
        Pattern pattern = Pattern.compile("of\\s([\\d,]+)\\sentries");
        Matcher matcher = pattern.matcher(elementText);

        String extractedNumber = "";
        if (matcher.find()) {
            extractedNumber = matcher.group(1).replace(",", "");
        }

        // Check if extractedNumber is empty
        if (extractedNumber.isEmpty()) {
            System.out.println("No number found in the text.");
        } else {
            System.out.println("Extracted no of stats:" + extractedNumber);
        }

        //scroll from bottom to top
        js2.executeScript("window.scrollTo(0, 0);");

        WebElement branchDash = driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/div[2]/div/ul/li[3]/a"));
        branchDash.click();

        WebElement searchByBranch3 = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/form/div/div[2]/div[1]/div/div/div/span/span[1]/span"));
        searchByBranch3.click();

        WebElement selectBranch = driver.findElement(By.xpath("/html/body/span/span/span[2]/ul/li[1]"));
        selectBranch.click();

        WebElement nextBtn = driver.findElement(By.xpath("//*[@id=\"submit-button\"]"));
        nextBtn.click();

        Thread.sleep(3000);

        WebElement branchStatusMatrix = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[1]/div[9]/div/div/div[1]/h3"));

        String orderStatus3 = branchStatusMatrix.getText();

        System.out.println("Dashboard statistic for selected branch :" + orderStatus3);

        if(extractedNumber.equals(orderStatus3)){
            System.out.println("All order statistics and branch dashboard statistics are matched.");
        }else {
            System.out.println("All order statistics and branch dashboard statistics are not matched.");
        }
    }

    @Test(priority = 8)
    public void branchDashboardStatistics6()throws InterruptedException{

        // to check failed to deliver state matrix
        WebElement allOrders = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[5]/a"));
        allOrders.click();

        WebElement searchByStatus = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/ul/li/input"));
        searchByStatus.click();

        WebElement status6 = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[10]"));
        status6.click();

        WebElement searchByBranch = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[3]/div/span/span[1]/span/ul/li/input"));
        searchByBranch.click();

        WebElement branch = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[1]"));
        branch.click();

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
        int steps2 = 20;
        long pauseTime2 = 200;
        int scrollAmount2 = 100;

        for (int i = 0; i < steps2; i++) {
            js2.executeScript("window.scrollBy(0, " + scrollAmount2 + ");");
            Thread.sleep(pauseTime2);
        }

        Thread.sleep(5000);

        // to check all out for delivery orders count

        // Locate the web element containing the text
        WebElement element = driver.findElement(By.xpath("//*[@id=\"datatable-basic_info\"]"));
        Thread.sleep(1000);

        // Get the text of the web element
        String elementText = element.getText();
        System.out.println("No of orders in selected status: " + elementText);

        // Extract the number using a regular expression
        Pattern pattern = Pattern.compile("of\\s([\\d,]+)\\sentries");
        Matcher matcher = pattern.matcher(elementText);

        String extractedNumber = "";
        if (matcher.find()) {
            extractedNumber = matcher.group(1).replace(",", "");
        }

        // Check if extractedNumber is empty
        if (extractedNumber.isEmpty()) {
            System.out.println("No number found in the text.");
        } else {
            System.out.println("Extracted no of stats:" + extractedNumber);
        }

        //scroll from bottom to top
        js2.executeScript("window.scrollTo(0, 0);");

        WebElement branchDash = driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/div[2]/div/ul/li[3]/a"));
        branchDash.click();

        WebElement searchByBranch3 = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/form/div/div[2]/div[1]/div/div/div/span/span[1]/span"));
        searchByBranch3.click();

        WebElement selectBranch = driver.findElement(By.xpath("/html/body/span/span/span[2]/ul/li[1]"));
        selectBranch.click();

        WebElement nextBtn = driver.findElement(By.xpath("//*[@id=\"submit-button\"]"));
        nextBtn.click();

        Thread.sleep(3000);

        WebElement branchStatusMatrix = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[1]/div[10]/div/div/div[1]/h3"));

        String orderStatus3 = branchStatusMatrix.getText();

        System.out.println("Dashboard statistic for selected branch :" + orderStatus3);

        if(extractedNumber.equals(orderStatus3)){
            System.out.println("All order statistics and branch dashboard statistics are matched.");
        }else {
            System.out.println("All order statistics and branch dashboard statistics are not matched.");
        }
    }

   @Test(priority = 9)
    public void branchDashboardStatistics7()throws InterruptedException{

        // to check return to HO state matrix
        WebElement allOrders = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[5]/a"));
        allOrders.click();

        WebElement searchByStatus = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/ul/li/input"));
        searchByStatus.click();

        WebElement status7 = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[11]"));
        status7.click();

        WebElement searchByBranch = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[3]/div/span/span[1]/span/ul/li/input"));
        searchByBranch.click();

        WebElement branch = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[1]"));
        branch.click();

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
        int steps2 = 20;
        long pauseTime2 = 200;
        int scrollAmount2 = 100;

        for (int i = 0; i < steps2; i++) {
            js2.executeScript("window.scrollBy(0, " + scrollAmount2 + ");");
            Thread.sleep(pauseTime2);
        }

        Thread.sleep(5000);

        // to check all out for delivery orders count

        // Locate the web element containing the text
        WebElement element = driver.findElement(By.xpath("//*[@id=\"datatable-basic_info\"]"));
        Thread.sleep(1000);

        // Get the text of the web element
        String elementText = element.getText();
        System.out.println("No of orders in selected status: " + elementText);

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
            System.out.println("Extracted no of stats:" + extractedNumber);
        }

        //scroll from bottom to top
        js2.executeScript("window.scrollTo(0, 0);");

        WebElement branchDash = driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/div[2]/div/ul/li[3]/a"));
        branchDash.click();

        WebElement searchByBranch3 = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/form/div/div[2]/div[1]/div/div/div/span/span[1]/span"));
        searchByBranch3.click();

        WebElement selectBranch = driver.findElement(By.xpath("/html/body/span/span/span[2]/ul/li[1]"));
        selectBranch.click();

        WebElement nextBtn = driver.findElement(By.xpath("//*[@id=\"submit-button\"]"));
        nextBtn.click();

        Thread.sleep(3000);

        WebElement branchStatusMatrix = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[1]/div[11]/div/div/div[1]/h3"));

        String orderStatus3 = branchStatusMatrix.getText();

        System.out.println("Dashboard statistic for selected branch :" + orderStatus3);

        if(extractedNumber.equals(orderStatus3)){
            System.out.println("All order statistics and branch dashboard statistics are matched.");
        }else {
            System.out.println("All order statistics and branch dashboard statistics are not matched.");
        }
    }

    @Test(priority = 10)
    public void branchDashboardStatistics8()throws InterruptedException{

        // to check re-delivery state matrix
        WebElement allOrders = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[5]/a"));
        allOrders.click();

        WebElement searchByStatus = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/ul/li/input"));
        searchByStatus.click();

        WebElement status3 = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[14]"));
        status3.click();

        WebElement searchByBranch = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[3]/div/span/span[1]/span/ul/li/input"));
        searchByBranch.click();

        WebElement branch = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[1]"));
        branch.click();

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
        int steps2 = 20;
        long pauseTime2 = 200;
        int scrollAmount2 = 100;

        for (int i = 0; i < steps2; i++) {
            js2.executeScript("window.scrollBy(0, " + scrollAmount2 + ");");
            Thread.sleep(pauseTime2);
        }

        Thread.sleep(5000);

        // to check all out for delivery orders count

        // Locate the web element containing the text
        WebElement element = driver.findElement(By.xpath("//*[@id=\"datatable-basic_info\"]"));
        Thread.sleep(1000);

        // Get the text of the web element
        String elementText = element.getText();
        System.out.println("No of orders in selected status: " + elementText);

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
            System.out.println("Extracted no of stats:" + extractedNumber);
        }

        //scroll from bottom to top
        js2.executeScript("window.scrollTo(0, 0);");

        WebElement branchDash = driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/div[2]/div/ul/li[3]/a"));
        branchDash.click();

        WebElement searchByBranch3 = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/form/div/div[2]/div[1]/div/div/div/span/span[1]/span"));
        searchByBranch3.click();

        WebElement selectBranch = driver.findElement(By.xpath("/html/body/span/span/span[2]/ul/li[1]"));
        selectBranch.click();

        WebElement nextBtn = driver.findElement(By.xpath("//*[@id=\"submit-button\"]"));
        nextBtn.click();

        Thread.sleep(3000);

        WebElement branchStatusMatrix = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[1]/div[13]/div/div/div[1]/h3"));

        String orderStatus3 = branchStatusMatrix.getText();

        System.out.println("Dashboard statistic for selected branch :" + orderStatus3);

        if(extractedNumber.equals(orderStatus3)){
            System.out.println("All order statistics and branch dashboard statistics are matched.");
        }else {
            System.out.println("All order statistics and branch dashboard statistics are not matched.");
        }
    }

    @Test(priority = 11)
    public void branchDashboardStatistics9()throws InterruptedException{

        // to check return to HO (invalid destination) state matrix
        WebElement allOrders = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[5]/a"));
        allOrders.click();

        WebElement searchByStatus = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/ul/li/input"));
        searchByStatus.click();

        WebElement status3 = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[18]"));
        status3.click();

        WebElement searchByBranch = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[3]/div/span/span[1]/span/ul/li/input"));
        searchByBranch.click();

        WebElement branch = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[1]"));
        branch.click();

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
        int steps2 = 20;
        long pauseTime2 = 200;
        int scrollAmount2 = 100;

        for (int i = 0; i < steps2; i++) {
            js2.executeScript("window.scrollBy(0, " + scrollAmount2 + ");");
            Thread.sleep(pauseTime2);
        }

        Thread.sleep(5000);

        // to check all out for delivery orders count

        // Locate the web element containing the text
        WebElement element = driver.findElement(By.xpath("//*[@id=\"datatable-basic_info\"]"));
        Thread.sleep(1000);

        // Get the text of the web element
        String elementText = element.getText();
        System.out.println("No of orders in selected status: " + elementText);

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
            System.out.println("Extracted no of stats:" + extractedNumber);
        }

        //scroll from bottom to top
        js2.executeScript("window.scrollTo(0, 0);");

        WebElement branchDash = driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/div[2]/div/ul/li[3]/a"));
        branchDash.click();

        WebElement searchByBranch3 = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/form/div/div[2]/div[1]/div/div/div/span/span[1]/span"));
        searchByBranch3.click();

        WebElement selectBranch = driver.findElement(By.xpath("/html/body/span/span/span[2]/ul/li[1]"));
        selectBranch.click();

        WebElement nextBtn = driver.findElement(By.xpath("//*[@id=\"submit-button\"]"));
        nextBtn.click();

        Thread.sleep(3000);

        WebElement branchStatusMatrix = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[1]/div[15]/div/div/div[1]/h3"));

        String orderStatus3 = branchStatusMatrix.getText();

        System.out.println("Dashboard statistic for selected branch :" + orderStatus3);

        if(extractedNumber.equals(orderStatus3)){
            System.out.println("All order statistics and branch dashboard statistics are matched.");
        }else {
            System.out.println("All order statistics and branch dashboard statistics are not matched.");
        }
    }

    @Test(priority = 12)
    public void branchDashboardStatistics10()throws InterruptedException{

        // to check HO clearance state matrix
        WebElement allOrders = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[5]/a"));
        allOrders.click();

        WebElement searchByStatus = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/ul/li/input"));
        searchByStatus.click();

        WebElement status3 = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[20]"));
        status3.click();

        WebElement searchByBranch = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[3]/div/span/span[1]/span/ul/li/input"));
        searchByBranch.click();

        WebElement branch = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[1]"));
        branch.click();

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
        int steps2 = 20;
        long pauseTime2 = 200;
        int scrollAmount2 = 100;

        for (int i = 0; i < steps2; i++) {
            js2.executeScript("window.scrollBy(0, " + scrollAmount2 + ");");
            Thread.sleep(pauseTime2);
        }

        Thread.sleep(5000);

        // to check all out for delivery orders count

        // Locate the web element containing the text
        WebElement element = driver.findElement(By.xpath("//*[@id=\"datatable-basic_info\"]"));
        Thread.sleep(1000);

        // Get the text of the web element
        String elementText = element.getText();
        System.out.println("No of orders in selected status: " + elementText);

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
            System.out.println("Extracted no of stats:" + extractedNumber);
        }

        //scroll from bottom to top
        js2.executeScript("window.scrollTo(0, 0);");

        WebElement branchDash = driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/div[2]/div/ul/li[3]/a"));
        branchDash.click();

        WebElement searchByBranch3 = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/form/div/div[2]/div[1]/div/div/div/span/span[1]/span"));
        searchByBranch3.click();

        WebElement selectBranch = driver.findElement(By.xpath("/html/body/span/span/span[2]/ul/li[1]"));
        selectBranch.click();

        WebElement nextBtn = driver.findElement(By.xpath("//*[@id=\"submit-button\"]"));
        nextBtn.click();

        Thread.sleep(3000);

        WebElement branchStatusMatrix = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[1]/div[16]/div/div/div[1]/h3"));

        String orderStatus3 = branchStatusMatrix.getText();

        System.out.println("Dashboard statistic for selected branch :" + orderStatus3);

        if(extractedNumber.equals(orderStatus3)){
            System.out.println("All order statistics and branch dashboard statistics are matched.");
        }else {
            System.out.println("All order statistics and branch dashboard statistics are not matched.");
        }
    }

    @Test(priority = 13)
    public void branchDashboardStatistics11()throws InterruptedException{

        // to check re-assign rider state matrix
        WebElement allOrders = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[5]/a"));
        allOrders.click();

        WebElement searchByStatus = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/ul/li/input"));
        searchByStatus.click();

        WebElement status3 = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[21]"));
        status3.click();

        WebElement searchByBranch = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[3]/div/span/span[1]/span/ul/li/input"));
        searchByBranch.click();

        WebElement branch = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[1]"));
        branch.click();

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
        int steps2 = 4;
        long pauseTime2 = 10;
        int scrollAmount2 = 5;

        for (int i = 0; i < steps2; i++) {
            js2.executeScript("window.scrollBy(0, " + scrollAmount2 + ");");
            Thread.sleep(pauseTime2);
        }

        Thread.sleep(5000);

        // to check all out for delivery orders count

        // Locate the web element containing the text
        WebElement element = driver.findElement(By.xpath("//*[@id=\"datatable-basic_info\"]"));
        Thread.sleep(1000);

        // Get the text of the web element
        String elementText = element.getText();
        System.out.println("No of orders in selected status: " + elementText);

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
            System.out.println("Extracted no of stats:" + extractedNumber);
        }

        //scroll from bottom to top
        js2.executeScript("window.scrollTo(0, 0);");

        WebElement branchDash = driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/div[2]/div/ul/li[3]/a"));
        branchDash.click();

        WebElement searchByBranch3 = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/form/div/div[2]/div[1]/div/div/div/span/span[1]/span"));
        searchByBranch3.click();

        WebElement selectBranch = driver.findElement(By.xpath("/html/body/span/span/span[2]/ul/li[1]"));
        selectBranch.click();

        WebElement nextBtn = driver.findElement(By.xpath("//*[@id=\"submit-button\"]"));
        nextBtn.click();

        Thread.sleep(3000);

        WebElement branchStatusMatrix = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[1]/div[17]/div/div/div[1]/h3"));

        String orderStatus3 = branchStatusMatrix.getText();

        System.out.println("Dashboard statistic for selected branch :" + orderStatus3);

        if(extractedNumber.equals(orderStatus3)){
            System.out.println("All order statistics and branch dashboard statistics are matched.");
        }else {
            System.out.println("All order statistics and branch dashboard statistics are not matched.");
        }
    }*/


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

        Thread.sleep(30000);

        // Get all the containers for the branch success rates
        List<WebElement> containers = driver.findElements(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[3]"));

        for (WebElement container : containers) {
            // Extract month name
            String month = container.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[3]/div[1]/div/div/div[1]/h3")).getText();

            // Extract assigned orders and express deliveries
            int assignedOrders = Integer.parseInt(container.findElement(By.xpath("//*[@id=\"tile-0\"]/div/div[1]/span[1]")).getText().split(" - ")[1]);
            int expressDeliveries = Integer.parseInt(container.findElement(By.xpath("//*[@id=\"tile-0\"]/div/div[1]/span[2]")).getText().split(" - ")[1]);

            // Calculate the expected success rate
            double expectedSuccessRate = (double) expressDeliveries / assignedOrders * 100;

            // Extract the displayed success rate
            double displayedSuccessRate = Double.parseDouble(container.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[3]/div[1]/div/div/div[2]/i")).getText().replace("%", ""));

            // Compare the expected and displayed success rates
            Assert.assertEquals(displayedSuccessRate, expectedSuccessRate, 0.01, "Success rate mismatch for month: " + month);
        }
    }
}
