package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Marina on 23.05.2017.
 */

/**
 * This class describes a product page.
 */
public class ProductPage {
    WebDriver driver;
    private static final String CATEGORY_XPATH =
            ".//div[@id=\"TopPanel\"]//" +
            "td[@id=\"vi-VR-brumb-lnkLst\"]//li[1]/a/span";

    private static final String CONDITION_XPATH = ".//div[@id=\"vi-itm-cond\"]";

    private static final String RETURN_OPTION_XPATH =
            ".//div[@class=\"u-flL rpColWid\"]//" +
            "span[@id=\"vi-ret-accrd-txt\"]";

    private static final String DELIVERY_OPTION_XPATH =
            ".//*[@id=\"fshippingCost\"]/span[1]";

    private static final String BUY_IT_NOW_BTN_XPATH =
            ".//*[@id=\"binBtn_btn\"]";

    @FindBy(xpath=CATEGORY_XPATH)
    WebElement productCategory;

    @FindBy(xpath=CONDITION_XPATH)
    WebElement productCondition;

    @FindBy(xpath=RETURN_OPTION_XPATH)
    WebElement productReturnOption;

    @FindBy(xpath=DELIVERY_OPTION_XPATH)
    WebElement productDeliveryOption;

    public ProductPage (WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * This method returns main category of the product
     * (first in hierarchy).
     * @return
     */
    public String getProductCategory()
    {
        return productCategory.getText();
    }

    /**
     * This method returns condition of the product
     * @return
     */
    public String getProductCondition()
    {
        return productCondition.getText();
    }

    /**
     * This method returns the return conditions for the product
     * @return
     */
    public String getProductReturnOption()
    {
        return productReturnOption.getText();
    }

    /**
     * This method returns delivery options for the product
     * @return
     */
    public String getProductDeliveryOption()
    {
        return productDeliveryOption.getText();
    }

    /**
     * This method searches the button "Buy It Now" on the product page.
     * If the button is found, it returns true.
     * Else returns false.
     * @return
     */
    public boolean getBuyItNowOption()
    {
        if (driver.findElements(By.xpath(BUY_IT_NOW_BTN_XPATH)).size()>0)
            return true;
        else return false;
    }

}
