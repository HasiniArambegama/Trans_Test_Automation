package com.test.trans.client.main;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyOrders {
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
    public void navigateMyOrders(){

        // locate the form element
        WebElement titleElement = driver.findElement(By.xpath("//div[4]/div/div[2]/div[1]/div[1]/div[1]/h4"));

        // get the text of the form element
        String actualTitle = titleElement.getText();

        // define the expected form title
        String expectedTitle = "Home - My Orders";

        // assert for the form title
        Assert.assertEquals(actualTitle, expectedTitle, "Title does not match expected title");

        // to print form title
        System.out.println("Actual title is :" +actualTitle +"."+ " Therefore, actual title is displayed as expected.");
    }

    @Test(priority = 3)
    public void verifyDeliveredOrderStatistics()throws InterruptedException{
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

        WebElement searchByStatus = driver.findElement(By.xpath("//div[4]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span"));
        searchByStatus.click();

        WebElement delivered = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[6]"));
        delivered.click();

        // to scroll down the page
        JavascriptExecutor js2 = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps2 = 15;
        long pauseTime2 = 200;
        int scrollAmount2 = 100;

        for (int i = 0; i < steps2; i++) {
            js2.executeScript("window.scrollBy(0, " + scrollAmount2 + ");");
            Thread.sleep(pauseTime2);
        }

        WebElement searchBtn = driver.findElement(By.xpath("//*[@id=\"filter\"]"));
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
        System.out.println("No of orders in delivered state: " + elementText);

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
            System.out.println("Extracted no of orders in delivered state:" + extractedNumber);
        }

        //scroll from bottom to top
        js2.executeScript("window.scrollTo(0, 0);");

        WebElement myOrderDelivered = driver.findElement(By.xpath("//div[4]/div/div[2]/div[1]/div[4]/div[2]/div/div[1]/div/div/div/h3"));

        String deliveredOrders = myOrderDelivered.getText();

        System.out.println("Delivered Stat:" + deliveredOrders);

        if(extractedNumber.equals(deliveredOrders)){
            System.out.println("My order statistics and table statistics are matched");
        }else {
            System.out.println("My order statistics and table statistics are not matched");
        }
    }

    @Test(priority = 4)
    public void verifyPartiallyDeliveredOrderStatistics()throws InterruptedException{
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

        WebElement removeStatusButton = driver.findElement(By.xpath("//div[4]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/ul/li[1]/span"));

        // click the 'x' button to remove the status
        removeStatusButton.click();

        WebElement searchByStatus = driver.findElement(By.xpath("//div[4]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/ul/li/input"));
        searchByStatus.click();
        searchByStatus.sendKeys("P");

        // to scroll down the page
        JavascriptExecutor jsex = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int stp = 3;
        long psTime2 = 20;
        int scrollAmt = 10;

        for (int i = 0; i < stp; i++) {
            jsex.executeScript("window.scrollBy(0, " + scrollAmt + ");");
            Thread.sleep(psTime2);
        }


        WebElement pDelivered = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[3]"));
        pDelivered.click();

        // to scroll down the page
        JavascriptExecutor js2 = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps2 = 15;
        long pauseTime2 = 200;
        int scrollAmount2 = 100;

        for (int i = 0; i < steps2; i++) {
            js2.executeScript("window.scrollBy(0, " + scrollAmount2 + ");");
            Thread.sleep(pauseTime2);
        }

        WebElement searchBtn = driver.findElement(By.xpath("//*[@id=\"filter\"]"));
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
        System.out.println("No of orders in delivered state: " + elementText);

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
            System.out.println("Extracted no of orders in partially delivered state:" + extractedNumber);
        }

        //scroll from bottom to top
        js2.executeScript("window.scrollTo(0, 0);");

        WebElement myOrderPartiallyDelivered = driver.findElement(By.xpath("//div[4]/div/div[2]/div[1]/div[4]/div[2]/div/div[2]/div/div/div/h3"));

        String partiallyDeliveredOrders = myOrderPartiallyDelivered.getText();

        System.out.println("Partially delivered Stat:" + partiallyDeliveredOrders);

        if(extractedNumber.equals(partiallyDeliveredOrders)){
            System.out.println("My order statistics and table statistics are matched");
        }else {
            System.out.println("My order statistics and table statistics are not matched");
        }
    }

    @Test(priority = 5)
    public void verifyReturnedToClientsOrderStatistics()throws InterruptedException{
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

        WebElement removeStatusButton = driver.findElement(By.xpath("//div[4]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/ul/li[1]/span"));

        // click the 'x' button to remove the status
        removeStatusButton.click();

        WebElement searchByStatus = driver.findElement(By.xpath("//div[4]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/ul/li/input"));
        searchByStatus.click();
        searchByStatus.sendKeys("Return");

        // to scroll down the page
        JavascriptExecutor jsex = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int stp = 3;
        long psTime2 = 20;
        int scrollAmt = 10;

        for (int i = 0; i < stp; i++) {
            jsex.executeScript("window.scrollBy(0, " + scrollAmt + ");");
            Thread.sleep(psTime2);
        }


        WebElement returned = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[4]"));
        returned.click();

        // to scroll down the page
        JavascriptExecutor js2 = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps2 = 15;
        long pauseTime2 = 200;
        int scrollAmount2 = 100;

        for (int i = 0; i < steps2; i++) {
            js2.executeScript("window.scrollBy(0, " + scrollAmount2 + ");");
            Thread.sleep(pauseTime2);
        }

        WebElement searchBtn = driver.findElement(By.xpath("//*[@id=\"filter\"]"));
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
        System.out.println("No of orders in return to client state: " + elementText);

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
            System.out.println("Extracted no of orders in return to state:" + extractedNumber);
        }

        //scroll from bottom to top
        js2.executeScript("window.scrollTo(0, 0);");

        WebElement returnedToClient = driver.findElement(By.xpath("//div[4]/div/div[2]/div[1]/div[4]/div[2]/div/div[3]/div/div/div/h3"));

        String returnToClientOrders = returnedToClient.getText();

        System.out.println("Returned to Clients Stat:" + returnToClientOrders);

        if(extractedNumber.equals(returnToClientOrders)){
            System.out.println("My order statistics and table statistics are matched");
        }else {
            System.out.println("My order statistics and table statistics are not matched");
        }
    }

    @Test(priority = 6)
    public void verifyReceivedByClientsOrderStatistics()throws InterruptedException{
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

        WebElement removeStatusButton = driver.findElement(By.xpath("//div[4]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/ul/li[1]/span"));

        // click the 'x' button to remove the status
        removeStatusButton.click();

        WebElement searchByStatus = driver.findElement(By.xpath("//div[4]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/ul/li/input"));
        searchByStatus.click();
        searchByStatus.sendKeys("Received");

        // to scroll down the page
        JavascriptExecutor jsex = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int stp = 3;
        long psTime2 = 20;
        int scrollAmt = 10;

        for (int i = 0; i < stp; i++) {
            jsex.executeScript("window.scrollBy(0, " + scrollAmt + ");");
            Thread.sleep(psTime2);
        }


        WebElement receivedByClients = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[3]"));
        receivedByClients.click();

        // to scroll down the page
        JavascriptExecutor js2 = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps2 = 15;
        long pauseTime2 = 200;
        int scrollAmount2 = 100;

        for (int i = 0; i < steps2; i++) {
            js2.executeScript("window.scrollBy(0, " + scrollAmount2 + ");");
            Thread.sleep(pauseTime2);
        }

        WebElement searchBtn = driver.findElement(By.xpath("//*[@id=\"filter\"]"));
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
        System.out.println("No of orders in return to client state: " + elementText);

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
            System.out.println("Extracted no of orders in received by clients state:" + extractedNumber);
        }

        //scroll from bottom to top
        js2.executeScript("window.scrollTo(0, 0);");

        WebElement receivedByClient = driver.findElement(By.xpath("//div[4]/div/div[2]/div[1]/div[4]/div[2]/div/div[4]/div/div/div/h3"));

        String receivedOrders = receivedByClient.getText();

        System.out.println("Received by Clients Stat:" + receivedOrders);

        if(extractedNumber.equals(receivedOrders)){
            System.out.println("My order statistics and table statistics are matched");
        }else {
            System.out.println("My order statistics and table statistics are not matched");
        }
    }

    @Test(priority = 7)
    public void verifyAdvancedSearch()throws InterruptedException{

        // refresh the browser
        driver.navigate().refresh();

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

        WebElement orderDate = driver.findElement(By.xpath("//*[@id=\"order_date\"]"));
        orderDate.click();

        // to scroll down the page
        JavascriptExecutor js2 = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps2 = 10;
        long pauseTime2 = 50;
        int scrollAmount2 = 25;

        for (int i = 0; i < steps2; i++) {
            js2.executeScript("window.scrollBy(0, " + scrollAmount2 + ");");
            Thread.sleep(pauseTime2);
        }

        WebElement dateSelect = driver.findElement(By.xpath("/html/body/div[8]/div/div[1]/ul/li[4]"));
        dateSelect.click();

        WebElement searchByStatus = driver.findElement(By.xpath("//div[4]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/ul/li/input"));
        searchByStatus.click();
        searchByStatus.sendKeys("Deli");

        WebElement delivered = driver.findElement(By.xpath("/html/body/span/span/span/ul/li[2]"));
        delivered.click();

        WebElement changedDate = driver.findElement(By.xpath("//div[4]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[3]/div/input"));
        changedDate.click();

        WebElement changedDateSelect = driver.findElement(By.xpath("/html/body/div[9]/div/div[1]/ul/li[4]"));
        changedDateSelect.click();

        WebElement district = driver.findElement(By.xpath("//div[4]/div/div[2]/div[2]/div[5]/div[2]/form/div[2]/div[1]/div/div/span/span[1]/span"));
        district.click();
        Thread.sleep(200);

        WebElement districtSearch = driver.findElement(By.xpath("/html/body/span/span/span[1]/input"));
        districtSearch.click();
        districtSearch.click();
        districtSearch.sendKeys("Ka");

        Thread.sleep(400);

        WebElement districtSelect = driver.findElement(By.xpath("/html/body/span/span/span[2]/ul/li[1]"));
        districtSelect.click();

        WebElement city = driver.findElement(By.xpath("//div[4]/div/div[2]/div[2]/div[5]/div[2]/form/div[2]/div[2]/div/div/span[1]/span[1]/span/span[1]"));
        city.click();

        WebElement citySearch = driver.findElement(By.xpath("/html/body/span/span/span[1]/input"));
        citySearch.click();
        citySearch.sendKeys("Pa");

        Thread.sleep(400);

        WebElement citySelect = driver.findElement(By.xpath("/html/body/span/span/span[2]/ul/li[1]"));
        citySelect.click();

        WebElement invoice = driver.findElement(By.xpath("//div[4]/div/div[2]/div[2]/div[5]/div[2]/form/div[2]/div[3]/div/input"));
        invoice.click();
        //invoice.sendKeys("TRA-24-07-267703");

        WebElement searchBtn = driver.findElement(By.xpath("//*[@id=\"filter\"]"));
        searchBtn.click();

        //WebElement clearBtn = driver.findElement(By.xpath("//*[@id="clear"]"));
        //clearBtn.click();
    }

    @Test(priority = 8)
    public void reportDownload()throws InterruptedException{
        // to check excel download option
        // to scroll down the page
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps = 10;
        long pauseTime = 20;
        int scrollAmount = 10;

        for (int i = 0; i < steps; i++) {
            js.executeScript("window.scrollBy(0, " + scrollAmount + ");");
            Thread.sleep(pauseTime);
        }


        WebElement excel = driver.findElement(By.xpath("//div[4]/div/div[2]/div[2]/div[6]/div[2]/div/div/div[2]/div[2]/a[2]"));
        excel.click();

        // to check print option
        WebElement print = driver.findElement(By.xpath("//div[4]/div/div[2]/div[2]/div[6]/div[2]/div/div/div[2]/div[2]/a[1]"));
        print.click();

        // to check pdf option
        WebElement pdf= driver.findElement(By.xpath("//div[4]/div/div[2]/div[2]/div[6]/div[2]/div/div/div[2]/div[2]/a[3]"));
        pdf.click();
    }

    @AfterClass
    public void quitBrowser(){
        if(driver != null){
            driver.quit();
        }
    }
}
