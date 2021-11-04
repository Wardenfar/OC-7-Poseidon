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
public class ItRuleTests {

    @LocalServerPort
    private Integer port;

    @Test
    public void ruleTest() {
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
        driver.findElement(By.linkText("Rule")).click();
        driver.findElement(By.linkText("Add New")).click();
        driver.findElement(By.id("name")).click();
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("0");
        driver.findElement(By.id("description")).clear();
        driver.findElement(By.id("description")).sendKeys("0");
        driver.findElement(By.id("json")).clear();
        driver.findElement(By.id("json")).sendKeys("0");
        driver.findElement(By.id("template")).clear();
        driver.findElement(By.id("template")).sendKeys("0");
        driver.findElement(By.id("sqlStr")).clear();
        driver.findElement(By.id("sqlStr")).sendKeys("0");
        driver.findElement(By.id("sqlPart")).clear();
        driver.findElement(By.id("sqlPart")).sendKeys("0");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        assertEquals("Rule successfully added !", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Rule List'])[1]/following::div[2]")).getText());
        driver.findElement(By.linkText("Update")).click();
        driver.findElement(By.xpath("//body")).click();
        driver.findElement(By.id("description")).clear();
        driver.findElement(By.id("description")).sendKeys("1");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        assertEquals("1", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Action'])[1]/following::td[3]")).getText());
        assertEquals("Rule successfully updated !", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Rule List'])[1]/following::div[2]")).getText());
        driver.findElement(By.linkText("Delete")).click();
        assertEquals("Rule successfully deleted !", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Rule List'])[1]/following::div[2]")).getText());
    }
}
