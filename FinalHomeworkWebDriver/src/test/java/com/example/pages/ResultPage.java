package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tatanaberlenko on 11.05.17.
 */
public class ResultPage {
    private WebDriver driver;
    private String TITLES_XPATH = ".//*[@id='Results']//h3";
    private String RESULTS_CONTAINER_XPATH = ".//*[@id='cbelm']//h1";

    public ResultPage(WebDriver driver){
        this.driver = driver;
    }

    public List<String> getTitles(){
        List<WebElement> itemsOnPageResult = driver.findElements(By.xpath(TITLES_XPATH));
        List<String> titles = new ArrayList<String>();
        for (WebElement resultItem: itemsOnPageResult) {
            String results = resultItem.getText().toLowerCase();
            titles.add(results);
        }
        return titles;
    }

    public String getContainerText(){
        WebElement resultsContainer = driver.findElement(By.xpath(RESULTS_CONTAINER_XPATH));
        return resultsContainer.getText().toLowerCase();

    }


}
