package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by tatanaberlenko on 11.05.17.
 */
public class MainPage {
    private WebDriver driver;
    private static final String SEARCH_BTN_XPATH = ".//*[@id=\"gh-btn\"]";
    private static final String SEARCH_TEXT_AREA_XPATH = ".//*[@id=\"gh-ac\"]";

    @FindBy(xpath=SEARCH_BTN_XPATH)
    WebElement searchButton;

    @FindBy(xpath=SEARCH_TEXT_AREA_XPATH)
    WebElement requestInput;

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    public void openPage(String URL){

        this.driver.get(URL);
    }


    public ResultPage search(String REQUEST) {
        requestInput.sendKeys(REQUEST);
        searchButton.click();

        return new ResultPage(driver);
    }

}
