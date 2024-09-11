package com.test.trans.client.positive.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ClientLogoutOption {
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HASINI\\Desktop\\Automation Trans Express Project\\TransExpressTest\\src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://qa2.transexpress.parallaxtec.dev");

    }

    @Test(priority = 1)
    public void transLogin() throws InterruptedException {

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
        email.sendKeys("nilsStores@gmail.com");
        Thread.sleep(200);

        // for password field
        WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        password.sendKeys("nils@1235");
        Thread.sleep(200);

        //for login button
        WebElement loginBtn = driver.findElement(By.xpath("//*[@id=\"login_panel\"]/div[9]/button"));
        loginBtn.click();
    }

    //logout option from upper right corner
    @Test(priority = 2)
    public void transClientLogout(){
        // locate the form element
        WebElement formElement = driver.findElement(By.xpath("//div[4]/div/div[2]/div[1]/div[1]/div[1]/h4"));

        // get the text of the form element
        String actualFormTitle = formElement.getText();

        // define the expected form title
        String expectedFormTitle = "Home - My Orders";

        boolean isHomePageTitlePresent = actualFormTitle.contains(expectedFormTitle);
        Assert.assertTrue(isHomePageTitlePresent, "Client's home page title text does not match");

        // to print home page title
        System.out.println("Client's home page actual title is: " + actualFormTitle);

        // automate logout option from upper right corner

        WebElement logoutIcon = driver.findElement(By.xpath("//div[2]/div[2]/div/ul[6]/li/a"));
        logoutIcon.click();

    }

    @Test(priority = 3)
    public void transLogin2() throws InterruptedException {

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
        email.sendKeys("nilsStores@gmail.com");
        Thread.sleep(200);

        // for password field
        WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        password.sendKeys("nils@1235");
        Thread.sleep(200);

        //for login button
        WebElement loginBtn = driver.findElement(By.xpath("//*[@id=\"login_panel\"]/div[9]/button"));
        loginBtn.click();
    }

    //logout option from left-side navigation
    @Test(priority = 4)
    public void transClientLogoutSideNav()throws InterruptedException{
        // locate the form element
        WebElement formElement = driver.findElement(By.xpath("//div[4]/div/div[2]/div[1]/div[1]/div[1]/h4"));

        // get the text of the form element
        String actualFormTitle = formElement.getText();

        // define the expected form title
        String expectedFormTitle = "Home - My Orders";

        boolean isHomePageTitlePresent = actualFormTitle.contains(expectedFormTitle);
        Assert.assertTrue(isHomePageTitlePresent, "Client's home page title text does not match");

        // to print home page title
        System.out.println("Client's home page actual title is: " + actualFormTitle);

        // scroll down the page to select client login button
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps = 10;
        long pauseTime = 150;
        int scrollAmount = 75;

        for(int i = 0; i < steps; i ++){
            js.executeScript("window.scrollBy(0, "+scrollAmount+");");
            Thread.sleep(pauseTime);
        }

        // automate logout option from side navigation bar

        WebElement logoutOptionSideNav = driver.findElement(By.xpath("//div[4]/div/div[1]/div/div[2]/div/ul/li[28]/a"));
        logoutOptionSideNav.click();

        // locate the page element
        WebElement loginPortalTitle = driver.findElement(By.xpath("//div[1]/div/div/div/h2"));

        // get the text of the page element
        String actualPortalTitle = loginPortalTitle.getText();

        // define the expected page title
        String expectedPortalTitle = "Delivery Management System";

        boolean isPageTitlePresent = actualPortalTitle.contains(expectedPortalTitle);
        Assert.assertTrue(isPageTitlePresent, "Home page title text does not match");

        // to print page title
        System.out.println("Login Portal Main title is: " + actualPortalTitle);
    }
    //time track - 1 to 2p.m
}
