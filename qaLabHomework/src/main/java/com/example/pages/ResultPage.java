package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marina on 11.05.2017.
 */
public class ResultPage {
    private WebDriver driver;
    private static final String XPATH_ELEMENT = "//*[@id=\"ListViewInner\"]/li/h3/a";//"//h3[@class=\"lvtitle\"]/a";
    private static final String XPATH_ITEM_NAME = "//*[@id=\"itemTitle\"]";


    public ResultPage(WebDriver driver)
    {
        this.driver = driver;
    }

    public List<String> getTitles()
    {
        //List<String> elements_href = new ArrayList<String>();
        List<String> elements_short_name = new ArrayList<String>();
        List<WebElement> elements = driver.findElements(By.xpath(XPATH_ELEMENT));
        for (WebElement element : elements) {
            //elements_href.add(element.getAttribute("href"));
            elements_short_name.add(element.getAttribute("title").replaceFirst("Нажмите на эту ссылку, чтобы перейти к ",""));
        }
        return elements_short_name;
    }
}
