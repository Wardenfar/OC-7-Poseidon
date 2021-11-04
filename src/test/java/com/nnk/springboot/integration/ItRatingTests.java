package com.nnk.springboot.integration;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItRatingTests {

    @LocalServerPort
    private Integer port;

    @Test
    public void ratingTest() {
        System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
        WebDriver driver = new ChromeDriver(
                new ChromeOptions()
                        .setHeadless(true)
                        .addArguments("--lang=en")
        );
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get("http://localhost:" + port);
        driver.findElement(By.linkText("User management")).click();
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("theo");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("test");
        driver.findElement(By.id("password")).sendKeys(Keys.ENTER);
        driver.findElement(By.linkText("Ratings")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Rating List'])[1]/following::div[2]")).click();
        driver.findElement(By.linkText("Add New")).click();
        driver.findElement(By.id("moodysRating")).click();
        driver.findElement(By.id("moodysRating")).clear();
        driver.findElement(By.id("moodysRating")).sendKeys("0");
        driver.findElement(By.id("orderNumber")).click();
        driver.findElement(By.id("orderNumber")).clear();
        driver.findElement(By.id("orderNumber")).sendKeys("0");
        driver.findElement(By.id("sandPRating")).click();
        driver.findElement(By.id("sandPRating")).clear();
        driver.findElement(By.id("sandPRating")).sendKeys("0");
        driver.findElement(By.id("fitchRating")).click();
        driver.findElement(By.id("fitchRating")).clear();
        driver.findElement(By.id("fitchRating")).sendKeys("0");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        assertEquals("Rating successfully added !", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Rating List'])[1]/following::div[2]")).getText());
        driver.findElement(By.linkText("Update")).click();
        driver.findElement(By.xpath("//body")).click();
        driver.findElement(By.id("orderNumber")).clear();
        driver.findElement(By.id("orderNumber")).sendKeys("1");
        driver.findElement(By.xpath("//body")).click();
        driver.findElement(By.id("sandPRating")).clear();
        driver.findElement(By.id("sandPRating")).sendKeys("1");
        driver.findElement(By.xpath("//body")).click();
        driver.findElement(By.id("fitchRating")).clear();
        driver.findElement(By.id("fitchRating")).sendKeys("1");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        assertEquals("Rating successfully updated !", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Rating List'])[1]/following::div[2]")).getText());
        driver.findElement(By.linkText("Delete")).click();
        assertEquals("Rating successfully deleted !", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Rating List'])[1]/following::div[2]")).getText());
    }
}
