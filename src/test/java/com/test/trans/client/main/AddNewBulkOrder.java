package com.test.trans.client.main;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class AddNewBulkOrder {
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
    public void navigateAddNewOrders(){
        WebElement newOrder = driver.findElement(By.xpath("/html/body/div[4]/div/div[1]/div/div[2]/div/ul/li[4]/a"));
        newOrder.click();

        // locate the form element
        WebElement formElement = driver.findElement(By.tagName("h2"));

        // get the text of the form element
        String actualFormTitle = formElement.getText();

        // define the expected form title
        String expectedFormTitle = "Upload order by system";

        // assert for the form title
        Assert.assertEquals(actualFormTitle, expectedFormTitle, "Form title does not match expected title");

        // to print form title
        System.out.println("Form actual title is :" +actualFormTitle +"."+ " Therefore, actual form title is displayed as expected.");
    }

    @Test(priority = 3)
    public void addBulkOrder()throws InterruptedException {
        WebElement bulkUploadBtn = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[1]/div[1]/div[2]/div/div[1]/div/a"));
        bulkUploadBtn.click();
        Thread.sleep(15000);

        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 30);

        // for waybill cell
        WebElement waybill = driver.findElement(By.cssSelector(".c0 r0"));
        //WebElement waybill = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"0-0\" and class=\"c0 r0\"]")));
        actions.doubleClick(waybill).perform();
        waybill.sendKeys("34567845");
        // press TAB to move to the next cell
        actions.sendKeys("\t").perform();

        // for order no
        WebElement orderNo = driver.findElement(By.xpath(""));
        actions.doubleClick(orderNo).perform();
        orderNo.sendKeys("05");
        // press TAB to move to the next cell
        actions.sendKeys("\t").perform();

        // for customer name
        WebElement cusName = driver.findElement(By.xpath(""));
        actions.doubleClick(cusName).perform();
        cusName.sendKeys("test customer");
        // press TAB to move to the next cell
        actions.sendKeys("\t").perform();

        // for address
        WebElement address = driver.findElement(By.xpath(""));
        actions.doubleClick(address).perform();
        address.sendKeys("test add");
        // press TAB to move to the next cell
        actions.sendKeys("\t").perform();

        // for order description
        WebElement orderDes = driver.findElement(By.xpath(""));
        actions.doubleClick(orderDes).perform();
        orderDes.sendKeys("order 1");
        // press TAB to move to the next cell
        actions.sendKeys("\t").perform();

        // for cus 1st phone no
        WebElement cusFphone = driver.findElement(By.xpath(""));
        actions.doubleClick(cusFphone).perform();
        cusFphone.sendKeys("0712345674");
        // press TAB to move to the next cell
        actions.sendKeys("\t").perform();

        // for cus 2nd phone no
        WebElement cusSphone = driver.findElement(By.xpath(""));
        actions.doubleClick(cusSphone).perform();
        cusSphone.sendKeys("0712345644");
        // press TAB to move to the next cell
        actions.sendKeys("\t").perform();

        // scroll view
        // Initialize the JavascriptExecutor
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Scroll horizontally by 1000 pixels to the right
        js.executeScript("window.scrollBy(1000,0)");

        // for cod amount
        WebElement codAmount = driver.findElement(By.xpath(""));
        actions.doubleClick(codAmount).perform();
        codAmount.sendKeys("80000");
        // press TAB to move to the next cell
        actions.sendKeys("\t").perform();

        // for city
        WebElement city = driver.findElement(By.xpath(""));
        actions.doubleClick(city).perform();
        city.sendKeys("Colombo");
        // press TAB to move to the next cell
        actions.sendKeys("\t").perform();

        // for remarks
        WebElement remarks = driver.findElement(By.xpath(""));
        actions.doubleClick(remarks).perform();
        remarks.sendKeys("test remark");
        // press TAB to move to the next cell
        actions.sendKeys("\t").perform();


        // verify download excel option
        WebElement downloadExcelBtn = driver.findElement(By.xpath("//*[@id=\"download-excel\"]"));
        downloadExcelBtn.click();

        // for upload button
        WebElement uploadBtn = driver.findElement(By.xpath("//*[@id=\"upload\"]"));
        uploadBtn.click();

    }

    /*@AfterClass
    public void quitBrowser(){
        if(driver != null){
            driver.quit();
        }
    }*/

}
