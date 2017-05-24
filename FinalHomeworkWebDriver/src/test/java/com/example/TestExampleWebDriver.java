package com.example;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;


/**
 * Created by tatanaberlenko on 04.05.17.
 */

public class TestExampleWebDriver {
    private static final String URL = "http://ebay.com";
    private static final String XPATH_SEARCH = "//*[@id=\"gh-ac\"]";
    private static final String XPATH_BTN_SEARCH = "//*[@id=\"gh-btn\"]";
    private static final String XPATH_ELEMENT = "//h3[@class=\"lvtitle\"]/a";
    private static final String WRONG_WORD = "тюльпен";
    private static final String RIGHT_WORD = "Тюльпан";
    private WebDriver driver;

    @BeforeClass
    public void setUp(){
        driver = new ChromeDriver();
    }


    @Test
    public void testExample() {
        driver.get(URL);
        WebElement gh = driver.findElement(By.xpath(XPATH_SEARCH));
        gh.clear();
        gh.sendKeys(WRONG_WORD);

        WebElement btn = driver.findElement(By.xpath(XPATH_BTN_SEARCH));
        btn.click();

        WebElement element = driver.findElement(By.xpath(XPATH_ELEMENT));
        String title = element.getAttribute("title");
        System.out.println(title);
        Boolean word = title.contains(RIGHT_WORD);

        assertTrue(word);
    }

    @AfterClass
     public void tearDown(){

        driver.quit();
    }
}
