package com.example.tests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Marina on 23.05.2017.
 */

/**
 * The class describes a home page of ebay.com.
 */
public class MainPage {

    public SearchForm searchForm;
    private WebDriver driver;
    public MainPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SearchForm openPage(String URL){
        this.driver.get(URL);
        if (!this.isMainPageOpen()) {
            this.driver.get(URL);
        }
        searchForm = new SearchForm(driver);
        return searchForm;
    }

    public boolean isMainPageOpen() {
        String defaultMainTitle = new String("ebay");
        String realTitle = driver.getTitle();
        return realTitle.toLowerCase().contains(defaultMainTitle);
    }
}
