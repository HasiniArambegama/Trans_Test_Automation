package com.test.trans.staff.positive.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class StaffLogoutOption {
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HASINI\\Desktop\\Automation Trans Express Project\\TransExpressTest\\src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://qa2.transexpress.parallaxtec.dev");

    }

    @Test(priority = 1)
    public void transStaffLogin()throws InterruptedException{

        // scroll down the page to select client login button
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

    //logout option from upper right corner

    @Test(priority = 2)
    public void transStaffLogout()throws InterruptedException {
        // locate the form element
        WebElement formElement = driver.findElement(By.xpath("//div[3]/div/div[2]/div[1]/div[1]/div/h4"));

        // get the text of the form element
        String actualFormTitle = formElement.getText();

        // define the expected form title
        String expectedFormTitle = "Home - All Orders";

        /*// assert for the form title
        Assert.assertEquals(actualFormTitle, expectedFormTitle, "Home page title does not match expected title");*/

        boolean isHomePageTitlePresent = actualFormTitle.contains(expectedFormTitle);
        Assert.assertTrue(isHomePageTitlePresent, "Home page title text does not match");

        // to print home page title
        System.out.println("Home page actual title is: " + actualFormTitle);


        // automate logout option from upper right corner

        WebElement logoutIcon = driver.findElement(By.xpath("//div[2]/div[2]/div/ul[3]/li/a"));
        logoutIcon.click();

    }

    @Test(priority = 3)
    public void transStaffLogin2()throws InterruptedException{

        // scroll down the page to select client login button
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

    // logout option from side navigation

    @Test(priority = 4)
    public void transStaffLogoutSideNav()throws InterruptedException {
        // locate the form element
        WebElement formElement = driver.findElement(By.xpath("//div[3]/div/div[2]/div[1]/div[1]/div/h4"));

        // get the text of the form element
        String actualFormTitle = formElement.getText();

        // define the expected form title
        String expectedFormTitle = "Home - All Orders";

        /*// assert for the form title
        Assert.assertEquals(actualFormTitle, expectedFormTitle, "Home page title does not match expected title");*/

        boolean isHomePageTitlePresent = actualFormTitle.contains(expectedFormTitle);
        Assert.assertTrue(isHomePageTitlePresent, "Home page title text does not match");

        // to print home page title
        System.out.println("Home page actual title is: " + actualFormTitle);


        // scroll down the page to select client login button
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // define the number of steps and the pause duration between each scroll step
        int steps = 12;
        long pauseTime = 200;
        int scrollAmount = 140;

        for(int i = 0; i < steps; i ++){
            js.executeScript("window.scrollBy(0, "+scrollAmount+");");
            Thread.sleep(pauseTime);
        }

        // automate logout option from side navigation bar

        WebElement logoutOptionSideNav = driver.findElement(By.xpath("//div[3]/div/div[1]/div/div[2]/div/ul/li[37]/a"));
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

}
