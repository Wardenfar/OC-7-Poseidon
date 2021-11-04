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
public class ItTradeTests {

    @LocalServerPort
    private Integer port;

    @Test
    public static void tradeTest(ItTradeTests itTradeTests) {
        System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
        WebDriver driver = new ChromeDriver(
                new ChromeOptions()
                        .setHeadless(true)
                        .addArguments("--lang=en")
        );
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get("http://localhost:" + itTradeTests.port);
        driver.findElement(By.linkText("User management")).click();
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("theo");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("test");
        driver.findElement(By.id("password")).sendKeys(Keys.ENTER);
        driver.findElement(By.linkText("Trade")).click();
        driver.findElement(By.linkText("Add New")).click();
        driver.findElement(By.id("account")).click();
        driver.findElement(By.id("account")).clear();
        driver.findElement(By.id("account")).sendKeys("0");
        driver.findElement(By.id("type")).clear();
        driver.findElement(By.id("type")).sendKeys("0");
        driver.findElement(By.id("buyQuantity")).clear();
        driver.findElement(By.id("buyQuantity")).sendKeys("0");
        driver.findElement(By.id("sellQuantity")).clear();
        driver.findElement(By.id("sellQuantity")).sendKeys("0");
        driver.findElement(By.id("buyPrice")).clear();
        driver.findElement(By.id("buyPrice")).sendKeys("0");
        driver.findElement(By.id("sellPrice")).clear();
        driver.findElement(By.id("sellPrice")).sendKeys("0");
        driver.findElement(By.id("benchmark")).clear();
        driver.findElement(By.id("benchmark")).sendKeys("0");
        driver.findElement(By.id("tradeDate")).clear();
        driver.findElement(By.id("tradeDate")).sendKeys("08312002");
        driver.findElement(By.id("security")).clear();
        driver.findElement(By.id("security")).sendKeys("1");
        driver.findElement(By.id("status")).clear();
        driver.findElement(By.id("status")).sendKeys("1");
        driver.findElement(By.id("trader")).clear();
        driver.findElement(By.id("trader")).sendKeys("1");
        driver.findElement(By.id("book")).clear();
        driver.findElement(By.id("book")).sendKeys("0");
        driver.findElement(By.id("creationName")).clear();
        driver.findElement(By.id("creationName")).sendKeys("0");
        driver.findElement(By.id("creationDate")).clear();
        driver.findElement(By.id("creationDate")).sendKeys("08312002");
        driver.findElement(By.id("revisionName")).clear();
        driver.findElement(By.id("revisionName")).sendKeys("1");
        driver.findElement(By.id("revisionDate")).clear();
        driver.findElement(By.id("revisionDate")).sendKeys("08312002");
        driver.findElement(By.id("dealName")).clear();
        driver.findElement(By.id("dealName")).sendKeys("1");
        driver.findElement(By.id("dealType")).clear();
        driver.findElement(By.id("dealType")).sendKeys("1");
        driver.findElement(By.id("sourceListId")).clear();
        driver.findElement(By.id("sourceListId")).sendKeys("1");
        driver.findElement(By.id("side")).clear();
        driver.findElement(By.id("side")).sendKeys("1");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        assertEquals("Trade successfully added !", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Trade List'])[1]/following::div[2]")).getText());
        driver.findElement(By.linkText("Delete")).click();
        assertEquals("Trade successfully deleted !", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Trade List'])[1]/following::div[2]")).getText());
    }
}
