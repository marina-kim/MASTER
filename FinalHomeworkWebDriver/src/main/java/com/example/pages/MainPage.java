package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;

/**
 * Created by Marina on 23.05.2017.
 */

/**
 * The class describes a home page of ebay.com.
 */
public class MainPage {

    private WebDriver driver;
    public MainPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openPage(String URL){
        this.driver.get(URL);
    }

    public boolean isMainPageOpen()
    {
        String defaultMainTitle = "ebay.com";
        String realTitle = driver.getTitle();
        return realTitle.toLowerCase().contains(defaultMainTitle);
    }
}
