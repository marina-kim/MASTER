package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Marina on 11.05.2017.
 */
public class MainPage {

    private WebDriver driver;

    private static final String XPATH_SEARCH = "//*[@id=\"gh-ac\"]";
    private static final String XPATH_BTN_SEARCH = "//*[@id=\"gh-btn\"]";
    private static final String XPATH_ELEMENT = "//*[@id=\"ListViewInner\"]/li/h3/a";//"//h3[@class=\"lvtitle\"]/a";
    private static final String XPATH_ITEM_NAME = "//*[@id=\"itemTitle\"]";
    private static final String XPATH_BTN_NEXTPAGE = "//*[@id=\"Pagination\"]/tbody/tr/td[3]/a";
    private static final String XPATH_BTN_BUY_NOW = "*[@id=\"cbelm\"]/div[1]/div[2]/a[1]";

    public MainPage (WebDriver driver){
        this.driver = driver;
    }

    public void openPage(String URL){
        this.driver.get(URL);
    }

    public void search(String REQUEST){
        WebElement gh = driver.findElement(By.xpath(XPATH_SEARCH));
        gh.clear();
        gh.sendKeys(REQUEST);
        WebElement btn = driver.findElement(By.xpath(XPATH_BTN_SEARCH));
        btn.click();
    }
}
