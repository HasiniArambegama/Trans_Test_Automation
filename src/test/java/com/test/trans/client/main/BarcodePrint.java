package com.test.trans.client.main;

import okio.ByteString;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class BarcodePrint {
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HASINI\\Desktop\\Automation Trans Express Project\\TransExpressTest\\src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://release.transexpress.parallaxtec.dev");
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
    public void navigateToBarcode(){
        driver.get("https://release.transexpress.parallaxtec.dev/client-orders/barcode-print");
    }

    @Test(priority = 3)
    public void verifyAdvanceSearch()throws InterruptedException{
        WebElement orderDate = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[1]/div/input"));
        orderDate.click();

        WebElement dateSelect = driver.findElement(By.xpath("/html/body/div[7]/div/div[1]/ul/li[3]"));
        dateSelect.click();

        WebElement status = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/span[1]"));
        status.click();

        WebElement selStatus = driver.findElement(By.xpath("/html/body/span/span/span[1]/input"));
        selStatus.click();

        WebElement statusPass = driver.findElement(By.xpath("/html/body/span/span/span[2]/ul/li[6]"));
        statusPass.click();

        WebElement district = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[3]/div/div/span/span[1]/span"));
        district.click();
        Thread.sleep(200);

        WebElement districtSearch = driver.findElement(By.xpath("/html/body/span/span/span[1]/input"));
        districtSearch.click();
        districtSearch.click();
        districtSearch.sendKeys("Ka");

        Thread.sleep(400);

        WebElement districtSelect = driver.findElement(By.xpath("/html/body/span/span/span[2]/ul/li[1]"));
        districtSelect.click();

        WebElement city = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[4]/div/div/span[1]/span[1]/span"));
        city.click();

        WebElement citySearch = driver.findElement(By.xpath("/html/body/span/span/span[1]/input"));
        citySearch.click();
        citySearch.sendKeys("Pa");

        Thread.sleep(400);

        WebElement citySelect = driver.findElement(By.xpath("/html/body/span/span/span[2]/ul/li[1]"));
        citySelect.click();

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
        // find the table element you want to scroll
        WebElement tableElement = driver.findElement(By.cssSelector("div.dataTables_scrollBody"));

        // scroll the table horizontally using JavaScript Executor
        JavascriptExecutor js1 = (JavascriptExecutor) driver;
        js1.executeScript("arguments[0].scrollLeft += 500;", tableElement);

        js.executeScript("window.scrollTo(0, 0);");

        WebElement searchedStatus = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[5]/div[2]/form/div[1]/div[2]/div/span/span[1]/span/span[1]"));
        String statusPassText = searchedStatus.getText();
        statusPassText = statusPassText.replace("×", "").trim();
        System.out.println(statusPassText);

        // to scroll down the page
        JavascriptExecutor js2 = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps2 = 16;
        long pauseTime2 = 50;
        int scrollAmount2 = 25;

        for (int i = 0; i < steps2; i++) {
            js2.executeScript("window.scrollBy(0, " + scrollAmount2 + ");");
            Thread.sleep(pauseTime2);
        }

        // find all elements under the "Status" column
        List<WebElement> statusElements = driver.findElements(By.xpath("//td[@class='text-center']/span[@class='badge' and contains(@style, 'background-color: #27ae60')]"));

        // check if all found statuses match the search status
        boolean allMatch = true;
        for (WebElement statusElement : statusElements) {
            String statusText = statusElement.getText();
            if (!statusText.equals(statusPassText)) {
                allMatch = false;
                break;
            }
        }

        // print the result
        if (allMatch) {
            System.out.println("Table statuses are matched with the searched status.");
        } else {
            System.out.println("Table statuses are not matched with the searched status.");
        }

    }

    @Test(priority = 4)
    public void verifyFilterSearch()throws InterruptedException{

        // refresh the browser
        driver.navigate().refresh();

        WebElement filter = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[6]/div[2]/div/div/div[2]/div[1]/label/input"));
        filter.click();
        filter.sendKeys("Kelly");
        Thread.sleep(3000);

        WebElement filter2 = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[6]/div[2]/div/div/div[2]/div[1]/label/input"));
        filter2.click();
        filter2.clear();
        filter2.sendKeys("0000048A");
        Thread.sleep(3000);

        WebElement filter3 = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[6]/div[2]/div/div/div[2]/div[1]/label/input"));
        filter3.click();
        filter2.clear();
        filter3.sendKeys("94701825283");
        Thread.sleep(3000);

        WebElement filter4 = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[6]/div[2]/div/div/div[2]/div[1]/label/input"));
        filter4.click();
        filter4.clear();
        filter4.sendKeys("1500");
        Thread.sleep(3000);

        WebElement filter5 = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[6]/div[2]/div/div/div[2]/div[1]/label/input"));
        filter5.click();
        filter5.clear();
        filter5.sendKeys("375");
        Thread.sleep(3000);

        WebElement filter6 = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[6]/div[2]/div/div/div[2]/div[1]/label/input"));
        filter6.click();
        filter6.clear();
        filter6.sendKeys("gg");
        Thread.sleep(3000);

        WebElement filter7 = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[6]/div[2]/div/div/div[2]/div[1]/label/input"));
        filter7.click();
        filter7.clear();
        filter7.sendKeys("Gampaha");
        Thread.sleep(3000);

        WebElement filter8 = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[6]/div[2]/div/div/div[2]/div[1]/label/input"));
        filter8.click();
        filter8.clear();
        filter.sendKeys("Ekala");
        Thread.sleep(3000);
    }

    @Test(priority = 5)
    public void verifyViewOrder()throws InterruptedException{
        // refresh the browser
        driver.navigate().refresh();

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

        WebElement waybillNo = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[6]/div[2]/div/div/div[3]/div[2]/div[2]/table/tbody/tr[5]/td[3]"));
        String waybillNoText = waybillNo.getText();
        System.out.println("Order's waybill no in table: " + waybillNoText);

        WebElement viewIcon = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[6]/div[2]/div/div/div[3]/div[3]/div[2]/div/table/tbody/tr[5]/td/div/div[1]/span/a"));
        viewIcon.click();

        WebElement viewedWaybillNo = driver.findElement(By.xpath("//*[@id=\"right-tab1\"]/div/div[1]/ul/li[1]/div[2]/h3"));
        viewedWaybillNo.click();
        String viewedWaybillNoText = viewedWaybillNo.getText();
        viewedWaybillNoText = viewedWaybillNoText.replace("#", "").trim();
        System.out.println("Viewed order waybill no: " + viewedWaybillNoText);


        if(waybillNoText.equals(viewedWaybillNoText)){
            System.out.println("Table's waybill no is same as the viewed order waybill no");
        }else {
            System.out.println("Table's waybill no is not same as the viewed order waybill no");
        }


        // to scroll down the page
        JavascriptExecutor js1 = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps1 = 30;
        long pauseTime1 = 80;
        int scrollAmount1 = 40;

        for (int i = 0; i < steps1; i++) {
            js1.executeScript("window.scrollBy(0, " + scrollAmount1 + ");");
            Thread.sleep(pauseTime1);
        }

        driver.navigate().back();
        driver.navigate().refresh();
    }

    @Test(priority = 6)
    public void barcodeOption()throws InterruptedException{

        Thread.sleep(500);
        WebElement viewIcon = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[6]/div[2]/div/div/div[3]/div[3]/div[2]/div/table/tbody/tr[1]/td/div/div[1]/span/a"));
        viewIcon.click();

        driver.navigate().back();

        WebElement printBarcodeBtn = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[6]/div[1]/div[2]/ul/li/a"));
        printBarcodeBtn.click();

        WebElement selectBarcodeOption = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[6]/div[1]/div[2]/ul/li/ul/li[1]/span/a"));
        selectBarcodeOption.click();

        WebElement confirmBtn = driver.findElement(By.xpath("/html/body/div[9]/div[7]/div/button"));
        confirmBtn.click();


    }

    @Test(priority = 7)
    public void reportDownload()throws InterruptedException{

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");

        WebElement excel = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[6]/div[2]/div/div/div[2]/div[2]/a[4]"));
        excel.click();
        Thread.sleep(200);

        // to check print option
        WebElement print = driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[6]/div[2]/div/div/div[2]/div[2]/a[3]"));
        print.click();
        Thread.sleep(200);

        // to check pdf option
        WebElement pdf= driver.findElement(By.xpath("//div[3]/div/div[2]/div[2]/div[6]/div[2]/div/div/div[2]/div[2]/a[5]"));
        pdf.click();
        Thread.sleep(200);
    }

    @AfterClass
    public void browserQuit(){
        if(driver != null){
            driver.quit();
        }
    }

}
