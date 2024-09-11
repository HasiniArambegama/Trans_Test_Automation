package com.test.trans.client.main;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class MyInvoices {
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HASINI\\Desktop\\Automation Trans Express Project\\TransExpressTest\\src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://release.transexpress.parallaxtec.dev");

    }

    @Test(priority = 1)
    public void transClientLogin() throws InterruptedException {

        // scroll down the page to select client login button
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps = 10;
        long pauseTime = 500;
        int scrollAmount = 100;

        for (int i = 0; i < steps; i++) {
            js.executeScript("window.scrollBy(0, " + scrollAmount + ");");
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
        email.sendKeys("kelly@gmail.com");
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
    public void navigateToMyInvoices() {
        driver.get("https://release.transexpress.parallaxtec.dev/client-finances");

        // locate the form element
        WebElement titleElement = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[1]/div[1]/div/h4"));

        // get the text of the form element
        String actualTitle = titleElement.getText();

        // define the expected form title
        String expectedTitle = "Home - My Invoices";

        // assert for the form title
        Assert.assertEquals(actualTitle, expectedTitle, "Title does not match expected title");

        // to print form title
        System.out.println("Actual title is :" + actualTitle + "." + " Therefore, actual title is displayed as expected.");
    }

    @Test(priority = 3)
    public void advancedSearch() {

        WebElement invoiceDate = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div/div/input"));
        invoiceDate.click();

        WebElement dateSelect = driver.findElement(By.xpath("/html/body/div[7]/div/div[1]/ul/li[3]"));
        dateSelect.click();

        WebElement searchBtn = driver.findElement(By.xpath("//*[@id=\"filter\"]"));
        searchBtn.click();

    }

    @Test(priority = 4)
    public void viewInvoices() throws InterruptedException {

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

        WebElement invoiceNo = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[6]/div[2]/div/div/div[3]/div[2]/div[2]/table/tbody/tr/td[2]"));
        String invoiceNoText = invoiceNo.getText();
        System.out.println("Order's invoice no in main table: " + invoiceNoText);

        // find the table element you want to scroll
        WebElement tableElement = driver.findElement(By.cssSelector("div.dataTables_scrollBody"));

        // scroll the table horizontally using JavaScript Executor
        JavascriptExecutor js1 = (JavascriptExecutor) driver;
        js1.executeScript("arguments[0].scrollLeft += 500;", tableElement);

        WebElement viewIcon = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[6]/div[2]/div/div/div[3]/div[3]/div[2]/div/table/tbody/tr/td[2]/div/span[1]/a"));
        viewIcon.click();

        Thread.sleep(300);

        WebElement viewedInvoiceNo1 = driver.findElement(By.xpath("//*[@id=\"laratables_row_7195613\"]/td[3]"));
        viewedInvoiceNo1.click();
        String viewedInvoiceNoText1 = viewedInvoiceNo1.getText();
        System.out.println("Viewed order invoice no 1: " + viewedInvoiceNoText1);

        WebElement viewedInvoiceNo2 = driver.findElement(By.xpath("//*[@id=\"laratables_row_7195614\"]/td[3]"));
        viewedInvoiceNo2.click();
        String viewedInvoiceNoText2 = viewedInvoiceNo1.getText();
        System.out.println("Viewed order invoice no 2: " + viewedInvoiceNoText2);


        if (invoiceNoText.equals(viewedInvoiceNoText1) && invoiceNoText.equals(viewedInvoiceNoText2)) {
            System.out.println("Table's invoice no is same as the viewed order invoice no");
        } else {
            System.out.println("Table's invoice no is not same as the viewed order invoice no");
        }


    }

    @Test(priority = 5)
    public void printInvoice() throws InterruptedException {

        // click my invoices to redirect to my invoice main page
        WebElement myInvoice = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[1]/div[2]/ul/li[2]/a"));
        myInvoice.click();

        // to scroll down the page
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps = 10;
        long pauseTime = 30;
        int scrollAmount = 20;

        for (int i = 0; i < steps; i++) {
            js.executeScript("window.scrollBy(0, " + scrollAmount + ");");
            Thread.sleep(pauseTime);
        }


        WebElement printIcon = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[6]/div[2]/div/div/div[3]/div[3]/div[2]/div/table/tbody/tr/td[2]/div/span[2]/a"));
        printIcon.click();
        Thread.sleep(2000);

            /*WebElement printBtn = driver.findElement(By.xpath("//*[@id=\"sidebar\"]//print-preview-button-strip//div/cr-button[1]"));
        printBtn.click();*/

    }

    @Test(priority = 6)
    public void reportDownload(){

        // to refresh the previous web page
        driver.navigate().refresh();

        // to check excel download option
        WebElement excel = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[6]/div[2]/div/div/div[2]/div[2]/a[2]"));
        excel.click();

        // to check pdf option
        WebElement pdf= driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[6]/div[2]/div/div/div[2]/div[2]/a[3]"));
        pdf.click();

        // to check print option
        WebElement print = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[6]/div[2]/div/div/div[2]/div[2]/a[1]"));
        print.click();
    }

    @Test(priority = 7)
    public void searchMissMatchedRecords()throws InterruptedException{

        driver.navigate().refresh();

        WebElement invoiceDate = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div/div/input"));
        invoiceDate.click();

        WebElement dateSelect = driver.findElement(By.xpath("/html/body/div[7]/div/div[1]/ul/li[1]"));
        dateSelect.click();

        WebElement searchBtn = driver.findElement(By.xpath("//*[@id=\"filter\"]"));
        searchBtn.click();
        Thread.sleep(500);

        WebElement messageElement = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[6]/div[2]/div/div/div[3]/div[2]/div[2]/table/tbody/tr/td"));

        // get the text of the form element
        String actualMessage = messageElement.getText();

        // define the expected form title
        String expectedMessage = "No matching records found";

        System.out.println("Expected message is: " + expectedMessage);

        // assert for the form title
        Assert.assertEquals(actualMessage, expectedMessage, "Message does not match as expected message");

        // to print form title
        System.out.println("Actual message is: " + actualMessage + "." + " Therefore, actual message is displayed as expected.");

        }

    @AfterClass
    public void quitBrowser(){
        if(driver != null){
            driver.quit();
        }
    }

}



