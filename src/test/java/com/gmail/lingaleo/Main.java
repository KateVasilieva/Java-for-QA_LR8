package com.gmail.lingaleo;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Main {

    public static WebDriver driver;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/kate/Java_for_QA/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

    }

    @Test
    public void successfullLoginToLinguaLeo() {

        driver.get("https://lingualeo.com/ru#welcome");
        WebElement headLoginButton = driver.findElement(By.id("headEnterBtn"));
        headLoginButton.click();
        WebElement loginField = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div/div[1]/input"));
        loginField.sendKeys("vasilieva.katerina26@gmail.com");
        WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div/div[2]/input"));
        passwordField.sendKeys("d9PWnw");
        WebElement LoginButton = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/button"));
        LoginButton.click();

        WebElement dynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.
                presenceOfElementLocated(By.xpath("//*[@id=\"tabsControl\"]/li[1]/a")));
        Assert.assertEquals("Мои задания", driver.getTitle());

        driver.quit();
    }


    @Test
    public void loginToLinguaLeoWithWrongPassword() {
        driver.get("https://lingualeo.com/ru#welcome");
        driver.findElement(By.id("headEnterBtn")).click();  //кликаем на кнопку Войти
        driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div/div[1]/input")).sendKeys("vasilieva.katerina26@gmail.com");  //вводим email
        driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div/div[2]/input")).sendKeys("123456");  //вводим неверный пароль
        driver.findElement(By.xpath("//*[@id=\"loginForm\"]/button")).click();  //кликаем на кнопку Войти
        driver.findElement(By.xpath("/html/body/div[8]/div[2]/div/div[2]/div/div[3]/div/div[2]/div/div[3]/div/p"));

        int time=1;
        try {
            // thread to sleep for 1000 milliseconds
            Thread.sleep(time * 1000);
        } catch (Exception e) {
            System.out.println(e);
        }

        WebElement invalidLoginText = driver.findElement(By.xpath("/html/body/div[8]/div[2]/div/div[2]/div/div[3]/div/div[2]/div/div[3]/div/p"));
        Assert.assertEquals("Пароль/email введены неверно", invalidLoginText.getText());

        driver.quit();
    }


    @Test
    public void navigateToMyProgressMenu () {
        driver.get("https://lingualeo.com/ru#welcome");
        WebElement headLoginButton = driver.findElement(By.id("headEnterBtn"));
        headLoginButton.click();
        WebElement loginField = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div/div[1]/input"));
        loginField.sendKeys("vasilieva.katerina26@gmail.com");
        WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div/div[2]/input"));
        passwordField.sendKeys("d9PWnw");
        WebElement LoginButton = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/button"));
        LoginButton.click();

        WebElement dynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.
                presenceOfElementLocated(By.xpath("//*[@id=\"tabsControl\"]/li[1]/a")));
        Assert.assertEquals("Мои задания", driver.getTitle());

        WebElement myProgress = driver.findElement(By.xpath("//*[@id=\"tabsControl\"]/li[2]/a/i"));
        myProgress.click();

        WebElement sitostLeo = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div/div[2]/div/div[1]/div"));
        Assert.assertEquals("Сытость Лео", sitostLeo.getText());

        driver.quit();
    }


    @Test
    public void addNewWordToDictionary () {
        driver.get("https://lingualeo.com/ru#welcome");
        WebElement headLoginButton = driver.findElement(By.id("headEnterBtn"));
        headLoginButton.click();
        WebElement loginField = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div/div[1]/input"));
        loginField.sendKeys("vasilieva.katerina26@gmail.com");
        WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div/div[2]/input"));
        passwordField.sendKeys("d9PWnw");
        WebElement LoginButton = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/button"));
        LoginButton.click();

        WebElement dynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.
                presenceOfElementLocated(By.xpath("//*[@id=\"tabsControl\"]/li[1]/a")));
        Assert.assertEquals("Мои задания", driver.getTitle());

        WebElement slovar = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div[1]/a"));
        slovar.click();

        WebElement poleVvodaSlov = driver.findElement(By.name("search"));
        poleVvodaSlov.sendKeys("dog");

        WebElement addButton = driver.findElement(By.xpath("//*[@id=\"glossaryPage\"]/div[3]/div[2]/div[1]/div[2]/div/div/form/button/span"));
        addButton.click();

        driver.quit();

    }
}

