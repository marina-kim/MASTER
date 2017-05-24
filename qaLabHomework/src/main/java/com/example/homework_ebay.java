package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Marina on 07.05.2017.
 */
public class homework_ebay {
    private static final String URL = "http://ebay.com";
    private static final String XPATH_SEARCH = "//*[@id=\"gh-ac\"]";
    private static final String XPATH_BTN_SEARCH = "//*[@id=\"gh-btn\"]";
    private static final String XPATH_ELEMENT = "//*[@id=\"ListViewInner\"]/li/h3/a";//"//h3[@class=\"lvtitle\"]/a";
    private static final String XPATH_ITEM_NAME = "//*[@id=\"itemTitle\"]";
    private static final String XPATH_BTN_NEXTPAGE = "//*[@id=\"Pagination\"]/tbody/tr/td[3]/a";
    private static final String XPATH_BTN_BUY_NOW = "*[@id=\"cbelm\"]/div[1]/div[2]/a[1]";
    private static final String RIGHT_WORD = "тюльпан зонт автоматический";
    private WebDriver driver;

    @BeforeClass
    public void setUp(){
        File file = new File("C:/tools/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        driver = new ChromeDriver();
    }


    @Test (dataProvider = "getDataForTask1")
    public void task1(String wrong, String right) {
        driver.get(URL);
        WebElement gh = driver.findElement(By.xpath(XPATH_SEARCH));
        gh.clear();
        gh.sendKeys(wrong);

        WebElement btn = driver.findElement(By.xpath(XPATH_BTN_SEARCH));
        btn.click();

        List<WebElement> elements = driver.findElements(By.xpath(XPATH_ELEMENT));
        for (WebElement element : elements) {
            String title = element.getAttribute("title");
            System.out.println(title);
            Boolean word = title.toLowerCase().contains(right.toLowerCase());
            assertTrue(word);

        }
    }

    @Test
    public void task2() {
        driver.get(URL);
        WebElement gh = driver.findElement(By.xpath(XPATH_SEARCH));
        gh.clear();
        gh.sendKeys(RIGHT_WORD);

        WebElement btn = driver.findElement(By.xpath(XPATH_BTN_SEARCH));
        btn.click();

        List<String> elements_href = new ArrayList<String>();
        List<String> elements_short_name = new ArrayList<String>();

        WebElement btn_next;
        while (true) {
            List<WebElement> elements = driver.findElements(By.xpath(XPATH_ELEMENT));
            for (WebElement element : elements) {
                elements_href.add(element.getAttribute("href"));
                elements_short_name.add(element.getAttribute("title").replaceFirst("Нажмите на эту ссылку, чтобы перейти к ",""));
            }

            boolean isPresent = (driver.findElements(By.xpath(XPATH_BTN_NEXTPAGE))).isEmpty();
            if (!isPresent) btn_next = driver.findElement(By.xpath(XPATH_BTN_NEXTPAGE));
            else break;
            if (btn_next.getAttribute("href").equals("javascript:;"))
            {
                break;
            }
            btn_next.click();
        }
        System.out.println(elements_short_name.size()+" элементов в списке");

        int i = 0;
        for (String element : elements_short_name) {
            System.out.println(i+") "+element);
            driver.get(elements_href.get(i));
            WebElement name = driver.findElement(By.xpath(XPATH_ITEM_NAME));
            String fool_name = name.getText();
            System.out.println(fool_name);
            Boolean word = fool_name.toLowerCase().trim().contains(element.toLowerCase().trim());
            assertTrue(word);
            i++;
        }

    }

    @DataProvider
    public Object[][] getDataForTask1()
    {
        Object[][] data = new Object[3][2];

        // 1st row
        data[0][0] ="пртфель";
        data[0][1] = "портфель";

        // 2nd row
        data[1][0] ="яблрко";
        data[1][1] = "яблоко";

        // 3rd row
         data[2][0] ="сумкаа";
         data[2][1] = "сумка";

        return data;
    }

    @AfterClass
    public void tearDown(){
        try{
            driver.quit();
        }
        catch (Exception anException)
        {
            anException.printStackTrace();
        }
    }
}

