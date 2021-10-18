package com.nnk.springboot.integration;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItBidTests {

    @LocalServerPort
    private Integer port;

    @Test
    public void testUntitledTestCase() {
        System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get("http://localhost:" + port);
        driver.findElement(By.linkText("User management")).click();
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("theo");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("test");
        driver.findElement(By.id("password")).sendKeys(Keys.ENTER);
        driver.findElement(By.linkText("Add New")).click();
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.findElement(By.xpath("//body")).click();
        assertEquals("There are some errors", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Add New Bid'])[1]/following::div[2]")).getText());
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='There are some errors'])[1]/following::div[3]")).click();
        driver.findElement(By.id("account")).click();
        assertEquals("must not be blank", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Account'])[1]/following::span[2]")).getText());
        driver.findElement(By.id("account")).click();
        driver.findElement(By.id("account")).clear();
        driver.findElement(By.id("account")).sendKeys("test");
        driver.findElement(By.cssSelector(".form-horizontal")).submit();
        driver.findElement(By.id("account")).click();
        driver.findElement(By.id("account")).clear();
        driver.findElement(By.id("account")).sendKeys("0");
        driver.findElement(By.id("type")).click();
        driver.findElement(By.id("type")).clear();
        driver.findElement(By.id("type")).sendKeys("0");
        driver.findElement(By.id("bidQuantity")).click();
        driver.findElement(By.id("bidQuantity")).clear();
        driver.findElement(By.id("bidQuantity")).sendKeys("0");
        driver.findElement(By.id("askQuantity")).click();
        driver.findElement(By.id("askQuantity")).clear();
        driver.findElement(By.id("askQuantity")).sendKeys("0");
        driver.findElement(By.id("bidValue")).click();
        driver.findElement(By.id("bidValue")).clear();
        driver.findElement(By.id("bidValue")).sendKeys("0");
        driver.findElement(By.id("askValue")).click();
        driver.findElement(By.id("askValue")).clear();
        driver.findElement(By.id("askValue")).sendKeys("0");
        driver.findElement(By.id("benchmark")).click();
        driver.findElement(By.id("benchmark")).clear();
        driver.findElement(By.id("benchmark")).sendKeys("0");
        driver.findElement(By.id("bidDate")).click();
        driver.findElement(By.id("bidDate")).clear();
        driver.findElement(By.id("bidDate")).sendKeys("08312002");
        driver.findElement(By.id("commentary")).click();
        driver.findElement(By.id("commentary")).clear();
        driver.findElement(By.id("commentary")).sendKeys("0");
        driver.findElement(By.id("security")).click();
        driver.findElement(By.id("security")).clear();
        driver.findElement(By.id("security")).sendKeys("0");
        driver.findElement(By.id("status")).click();
        driver.findElement(By.id("status")).clear();
        driver.findElement(By.id("status")).sendKeys("0");
        driver.findElement(By.id("trader")).click();
        driver.findElement(By.id("trader")).clear();
        driver.findElement(By.id("trader")).sendKeys("0");
        driver.findElement(By.id("book")).click();
        driver.findElement(By.id("book")).clear();
        driver.findElement(By.id("book")).sendKeys("0");
        driver.findElement(By.id("creationName")).click();
        driver.findElement(By.id("creationName")).clear();
        driver.findElement(By.id("creationName")).sendKeys("0");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='must not be blank'])[8]/following::span[1]")).click();
        driver.findElement(By.id("creationDate")).click();
        driver.findElement(By.id("creationDate")).clear();
        driver.findElement(By.id("creationDate")).sendKeys("08312002");
        driver.findElement(By.id("revisionName")).click();
        driver.findElement(By.id("revisionName")).clear();
        driver.findElement(By.id("revisionName")).sendKeys("0");
        driver.findElement(By.id("revisionDate")).click();
        driver.findElement(By.id("revisionDate")).clear();
        driver.findElement(By.id("revisionDate")).sendKeys("08312002");
        driver.findElement(By.id("dealName")).click();
        driver.findElement(By.id("dealName")).clear();
        driver.findElement(By.id("dealName")).sendKeys("0");
        driver.findElement(By.id("dealType")).click();
        driver.findElement(By.id("dealType")).clear();
        driver.findElement(By.id("dealType")).sendKeys("0");
        driver.findElement(By.id("sourceListId")).click();
        driver.findElement(By.id("sourceListId")).clear();
        driver.findElement(By.id("sourceListId")).sendKeys("0");
        driver.findElement(By.xpath("//form[@action='/bid/validate']")).click();
        driver.findElement(By.id("side")).clear();
        driver.findElement(By.id("side")).sendKeys("0");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Bid List'])[2]/following::div[2]")).click();
        assertEquals("Bid successfully added !", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Bid List'])[2]/following::div[2]")).getText());
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Action'])[1]/following::td[2]")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Action'])[1]/following::td[2]")).click();
        assertEquals("0", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Action'])[1]/following::td[2]")).getText());
        assertEquals("Update", driver.findElement(By.linkText("Update")).getText());
        assertEquals("Delete", driver.findElement(By.linkText("Delete")).getText());
    }
}
