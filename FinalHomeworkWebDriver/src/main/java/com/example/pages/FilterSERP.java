package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Marina on 23.05.2017.
 */

/**
 * This class describes left filter panel of SERP
 * on ebay.com. It includes filters by:
 * - category;
 * - item condition;
 * - price;
 * - format;
 * - item location;
 * - delivery parametrs;
 * - show only.
 */
public class FilterSERP {
    WebDriver driver;
    private static final String CATEGORY_XPATH =
            ".//div[@id=\"LeftPanel\"]" +
            "//div[@class=\"rlp-b\"]/div[2]/div[1]/a";

    private static final String CONDITION_NEW_XPATH =
            ".//input[@name=\"LH_ItemCondition\" " +
            "and ./following-sibling::span" +
            "[@class = \"cbx\" " +
            "and (text()=\"New\" or text()=\"Новый\")]]";

    private static final String MIN_PRICE_XPATH =
            ".//input[@class =\"price\" and @name=\"_udlo\"]";

    private static final String MAX_PRICE_XPATH =
            ".//input[@class =\"price\" and @name=\"_udhi\"]";

    private static final String SUBMIT_PRICE_BTN_XPATH =
            ".//div[@id=\"LeftPanel\"]" +
            "//form[@name=\"price\"]" +
            "/input[@class=\"sprBtnSRP1 submit\"]";

    private static final String FORMAT_BUY_IT_NOW_XPATH =
            ".//input[@name=\"LH_BuyingFormats\" " +
            "and ./following-sibling::span" +
            "[@class = \"rbx\" " +
            "and (text()=\"Buy It Now\" or text()=\"Купить сейчас\")]]";

    private static final String ITEM_LOCATION_USA_XPATH =
            ".//input[@name=\"LH_PrefLoc\" " +
            "and ./following-sibling::span" +
            "[@class = \"rbx\" " +
            "and (text()=\"US Only\" or text()=\"Только США\")]]";

    private static final String DELIVERY_OPTIONS_FREE_XPATH =
            ".//input[@name=\"LH_DeliveryOptions\" " +
            "and ./following-sibling::span" +
            "[@class = \"cbx\" " +
            "and (contains(text(),\"Free\") or contains(text(),\"Бесплатная\"))]]";

    private static final String SHOW_ONLY_RETURNS_XPATH =
            ".//input[@name=\"LH_ShowOnly\" " +
            "and ./following-sibling::span" +
            "[@class = \"cbx\" " +
            "and (contains(text(),\"Returns\") or contains(text(),\"возврат\"))]]";

    @FindBy(xpath=CATEGORY_XPATH)
    WebElement categoryInput;

    @FindBy(xpath=CONDITION_NEW_XPATH)
    WebElement conditionNewInput;

    @FindBy(xpath=MIN_PRICE_XPATH)
    WebElement minPriceInput;

    @FindBy(xpath=MAX_PRICE_XPATH)
    WebElement maxPriceInput;

    @FindBy(xpath=SUBMIT_PRICE_BTN_XPATH)
    WebElement submitPriceButton;

    @FindBy(xpath=FORMAT_BUY_IT_NOW_XPATH)
    WebElement buyItNowInput;

    @FindBy(xpath=ITEM_LOCATION_USA_XPATH)
    WebElement itemLocationUSAInput;

    @FindBy(xpath=DELIVERY_OPTIONS_FREE_XPATH)
    WebElement freeDeliveryInput;

    @FindBy(xpath=SHOW_ONLY_RETURNS_XPATH)
    WebElement returnsInput;

    public FilterSERP(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Help method, which returns the first category's name
     * in the list on left filter panel.
     * @return
     */
    public String getCategoryName()
    {
        System.out.println(categoryInput.getText());
        return categoryInput.getText();
    }

    /**
     * This method simulates clicking on the first category in
     * the list of left panel filters.
     * @return
     */
    public SERPage filterByCategory()
    {
        categoryInput.click();
        return new SERPage(driver);
    }

    /**
     * This method simulates clicking on the "New" condition
     * filter of left panel filters.
     * @return
     */
    public SERPage filterByNewCondition()
    {
        conditionNewInput.click();
        return new SERPage(driver);
    }

    /**
     * This method simulates filling of the "Price" bounds
     * and clicking on "Submit" button in price filter
     * of left panel filters.
     * @return
     */
    public SERPage filterByPrice(float min, float max)
    {
        minPriceInput.clear();
        maxPriceInput.clear();
        minPriceInput.sendKeys(Float.toString(min));
        maxPriceInput.sendKeys(Float.toString(max));
        submitPriceButton.click();
        return new SERPage(driver);
    }

    /**
     * This method simulates clicking on the "Buy It Now"
     * option in format filter of left panel filters.
     * @return
     */
    public SERPage filterByBuyItNow()
    {
        buyItNowInput.click();
        return new SERPage(driver);
    }

    /**
     * This method simulates clicking on the "USA only"
     * option in item location filter of left panel filters.
     * @return
     */
    public SERPage filterByLocationUSA()
    {
        itemLocationUSAInput.click();
        return new SERPage(driver);
    }

    /**
     * This method simulates clicking on the "Free international
     * shipping" option in delivery options filter of left panel
     * filters.
     * @return
     */
    public SERPage filterByDeliveryFree()
    {
        freeDeliveryInput.click();
        return new SERPage(driver);
    }

    /**
     * This method simulates clicking on the "Returns accepted"
     * option in "shows only" filter of left panel filters.
     * @return
     */
    public SERPage filterByReturnOption()
    {
        returnsInput.click();
        return new SERPage(driver);
    }
}
