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
public class ItCurvePointTests {

    @LocalServerPort
    private Integer port;

    @Test
    public void testUntitledTestCase() {
        System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
        WebDriver driver = new ChromeDriver(
                new ChromeOptions()
                        .setHeadless(true)
                        .addArguments("--lang=en")
        );
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get("http://localhost:" + port + "/app/login");
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("theo");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("test");
        driver.findElement(By.id("password")).sendKeys(Keys.ENTER);
        driver.findElement(By.linkText("Curve Points")).click();
        driver.findElement(By.linkText("Add New")).click();
        driver.findElement(By.id("asOfDate")).click();
        driver.findElement(By.id("asOfDate")).clear();
        driver.findElement(By.id("asOfDate")).sendKeys("08312002");
        driver.findElement(By.id("term")).clear();
        driver.findElement(By.id("term")).sendKeys("0");
        driver.findElement(By.id("value")).clear();
        driver.findElement(By.id("value")).sendKeys("0");
        driver.findElement(By.id("creationDate")).clear();
        driver.findElement(By.id("creationDate")).sendKeys("08312002");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        assertEquals("CurvePoint succesfully added !", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Curve Point List'])[1]/following::div[2]")).getText());
        driver.findElement(By.linkText("Delete")).click();
        assertEquals("CurvePoint successfully deleted !", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Curve Point List'])[1]/following::div[2]")).getText());
    }
}
