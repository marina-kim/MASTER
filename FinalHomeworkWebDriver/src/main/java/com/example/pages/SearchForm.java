package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Marina on 24.05.2017.
 */

/**
 * This class describes search form in the top of
 * home page of ebay.com
 */
public class SearchForm {

    WebDriver driver;
    private static final String SEARCH_BTN_XPATH = ".//*[@id=\"gh-btn\"]";
    private static final String SEARCH_TEXTBOX_XPATH = ".//*[@id=\"gh-ac\"]";

    @FindBy(xpath=SEARCH_BTN_XPATH)
    WebElement searchButton;

    @FindBy(xpath=SEARCH_TEXTBOX_XPATH)
    WebElement requestInput;

    public SearchForm (WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void clearSearchForm()
    {
        requestInput.clear();
    }

    public void setSearchRequest(String request)
    {
        requestInput.sendKeys(request);
    }

    public void submit()
    {
        searchButton.click();
    }

    public SERPage search(String request) {
        //clearSearchForm();
        setSearchRequest(request);
        submit();
        return new SERPage(driver);
    }
}
