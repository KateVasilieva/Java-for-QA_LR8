package com.gmail.lingaleo;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static com.thoughtworks.selenium.SeleneseTestNgHelper.assertEquals;

public class Main {

    public static WebDriver driver;

    @BeforeTest
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/kate/Java_for_QA/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void successfullLoginToLinguaLeo() {

        driver.get("https://lingualeo.com/ru#welcome");

        String appTitle = driver.getTitle();
        String expectedTitle = "Lingualeo — английский язык онлайн";
        assertEquals(appTitle, expectedTitle);

        String loginButton = "//*[@id='headEnterBtn']";
        String emailInput = "//form[@id='loginForm']//child::*[@name='email']";
        String passwordInput = "//form[@id='loginForm']//child::*[@name='password']";
        String submitButton = "//*[@id='loginForm']/child::*[contains(text(),'Войти')]";

        driver.findElement(By.xpath(loginButton)).click();
        driver.findElement(By.xpath(emailInput)).sendKeys("vasilieva.katerina26@gmail.com");
        driver.findElement(By.xpath(passwordInput)).sendKeys("d9PWnw");
        driver.findElement(By.xpath(submitButton)).click();

        WebElement dynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.
                presenceOfElementLocated(By.xpath("//*[@id=\"tabsControl\"]/li[1]/a")));

        Assert.assertEquals(driver.getTitle(), "Мои задания");

    }


    @Test
    public void loginToLinguaLeoWithWrongPassword() {
        driver.get("https://lingualeo.com/ru#welcome");

        String appTitle = driver.getTitle();
        String expectedTitle = "Lingualeo — английский язык онлайн";
        assertEquals(appTitle, expectedTitle);

        String loginButton = "//*[@id='headEnterBtn']";
        String emailInput = "//form[@id='loginForm']//child::*[@name='email']";
        String passwordInput = "//form[@id='loginForm']//child::*[@name='password']";
        String submitButton = "//*[@id='loginForm']/child::*[contains(text(),'Войти')]";

        driver.findElement(By.xpath(loginButton)).click();
        driver.findElement(By.xpath(emailInput)).sendKeys("vasilieva.katerina26@gmail.com");
        driver.findElement(By.xpath(passwordInput)).sendKeys("123456");
        driver.findElement(By.xpath(submitButton)).click();

        int time = 1;
        try {
            // thread to sleep for 1000 milliseconds
            Thread.sleep(time * 1000);
        } catch (Exception e) {
            System.out.println(e);
        }

        String expectedInvalidText = "Пароль/email введены неверно";
        String invalidLoginText = "p.uauth-email__error.t-ellps";
        assertEquals(driver.findElement(By.cssSelector(invalidLoginText)).getText(), expectedInvalidText);

    }


    @Test
    public void navigateToMyProgressMenu() {
        driver.get("https://lingualeo.com/ru#welcome");

        String appTitle = driver.getTitle();
        String expectedTitle = "Lingualeo — английский язык онлайн";
        assertEquals(appTitle, expectedTitle);

        String loginButton = "//*[@id='headEnterBtn']";
        String emailInput = "//form[@id='loginForm']//child::*[@name='email']";
        String passwordInput = "//form[@id='loginForm']//child::*[@name='password']";
        String submitButton = "//*[@id='loginForm']/child::*[contains(text(),'Войти')]";

        driver.findElement(By.xpath(loginButton)).click();
        driver.findElement(By.xpath(emailInput)).sendKeys("vasilieva.katerina26@gmail.com");
        driver.findElement(By.xpath(passwordInput)).sendKeys("d9PWnw");
        driver.findElement(By.xpath(submitButton)).click();

        WebElement dynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.
                presenceOfElementLocated(By.xpath("//*[@id=\"tabsControl\"]/li[1]/a")));

        Assert.assertEquals(driver.getTitle(), "Мои задания");

        String myProgress = "//*[@id=\"tabsControl\"]/li[2]/a/i";
        driver.findElement(By.xpath(myProgress)).click();

        String expectedsitostLeo = "Сытость Лео";
        String sitostLeo = "//*[@id=\"content\"]/div[2]/div/div[2]/div/div[1]/div";
        assertEquals(driver.findElement(By.xpath(sitostLeo)).getText(), expectedsitostLeo);

    }


    @Test
    public void addNewWordToDictionary() {
        driver.get("https://lingualeo.com/ru#welcome");
        
        String appTitle = driver.getTitle();
        String expectedTitle = "Lingualeo — английский язык онлайн";
        assertEquals(appTitle, expectedTitle);

        String loginButton = "//*[@id='headEnterBtn']";
        String emailInput = "//form[@id='loginForm']//child::*[@name='email']";
        String passwordInput = "//form[@id='loginForm']//child::*[@name='password']";
        String submitButton = "//*[@id='loginForm']/child::*[contains(text(),'Войти')]";

        driver.findElement(By.xpath(loginButton)).click();
        driver.findElement(By.xpath(emailInput)).sendKeys("vasilieva.katerina26@gmail.com");
        driver.findElement(By.xpath(passwordInput)).sendKeys("d9PWnw");
        driver.findElement(By.xpath(submitButton)).click();

        WebElement dynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.
                presenceOfElementLocated(By.xpath("//*[@id=\"tabsControl\"]/li[1]/a")));

        Assert.assertEquals(driver.getTitle(), "Мои задания");

        String slovar = "/html/body/div[2]/div[1]/div/div[1]/a";
        driver.findElement(By.xpath(slovar)).click();

        String poleVvodaSlov = "search";
        driver.findElement(By.name(poleVvodaSlov)).sendKeys("dog");

        String addButton = "//*[@id=\"glossaryPage\"]/div[3]/div[2]/div[1]/div[2]/div/div/form/button/span";
        driver.findElement(By.xpath(addButton)).click();
    }


    @AfterTest
    public void tearDown() {
        driver.close();
    }

}

