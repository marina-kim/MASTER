package com.example.tests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marina on 23.05.2017.
 */

/**
 * This class describes SERP of ebay.com
 */
public class SERPage {
    private WebDriver driver;
    private static final String TO_RUS = "до";
    private static final String TO_ENG = "to";
    private static final String PRICE_TREND_RUS = "Ценовой";
    private static final String PRICE_TREND_ENG = "Trend";
    private static final String FROM_RUS = "Страна:";
    private static final String FROM_ENG = "From";

    private static final String LINKS_XPATH =
            ".//*[@id='Results']//h3/a[@class=\"vip\"]";

    private static final String TITLES_XPATH = ".//*[@id='Results']//h3";

    private static final String PRICES_XPATH =
            ".//*[@id='Results']//li[@class=\"lvprice prc\"]/span[@class=\"bold\"]";

    private static final String PRICES_CURRENCY_XPATH =
            ".//*[@id='Results']//li[@class=\"lvprice prc\"]/span[@class=\"bold\"]/b";

    private static final String COUNTRIES_XPATH =
            ".//*[@id='Results']" +
            "//ul[@class=\"lvdetails left space-zero full-width\"]" +
            "/li[contains(text(),\"New\") or contains (text(),\"From\")]";

    private static final String BUY_NOW_TOPBTN_XPATH =
            ".//*[@id ='cbelm']" +
            "//span[@class=\"small sel tgl_button last_b\"]";

    private static final String FORMATS_XPATH =
            ".//*[@id='Results']//li[@class=\"lvformat\"]/span";


    @FindBy(xpath = LINKS_XPATH)
    private List<WebElement> snippetLinks;

    @FindBy(xpath = TITLES_XPATH)
    private List<WebElement> snippetTitles;

    @FindBy(xpath = PRICES_XPATH)
    private List<WebElement> snippetPrices;

    @FindBy(xpath = PRICES_CURRENCY_XPATH)
    private List<WebElement> snippetCurrencies;

    @FindBy(xpath = COUNTRIES_XPATH)
    private List<WebElement> snippetCountries;

    @FindBy(xpath = BUY_NOW_TOPBTN_XPATH)
    private List<WebElement> snippetBuyNow;

    @FindBy(xpath = FORMATS_XPATH)
    private List<WebElement> snippetFormats;

    public SERPage(WebDriver driver){

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * This method returns the list of snippets' titles in SERP.
     * @return
     */
    public List<String> getSnippetTitles(){
        List<String> titles = new ArrayList<String>();
        for (WebElement resultTitle: snippetTitles) {
            String itemTitleTextField = resultTitle.getText().toLowerCase();
            titles.add(itemTitleTextField);
        }
        return titles;
    }

    /**
     * This method returns the list of snippets' prices in SERP.
     * (Prices are float numbers)
     * @return
     */
    public List<Float> getSnippetPrices(){
        List<Float> prices = new ArrayList<Float>();
        for (WebElement resultPrice: snippetPrices) {
            String itemPriceTextField = resultPrice.getText();
            WebElement currencyElement = snippetCurrencies.get(0);
            String currency = currencyElement.getText();
            if (itemPriceTextField.contains(TO_RUS))
                itemPriceTextField = itemPriceTextField.substring(0,itemPriceTextField.indexOf(TO_RUS)-1);
            if (itemPriceTextField.contains(TO_ENG))
                itemPriceTextField = itemPriceTextField.substring(0,itemPriceTextField.indexOf(TO_ENG)-1);
            if (itemPriceTextField.contains(PRICE_TREND_RUS))
                itemPriceTextField = itemPriceTextField.substring(0,itemPriceTextField.indexOf(PRICE_TREND_RUS)-1);
            if (itemPriceTextField.contains(PRICE_TREND_ENG))
                itemPriceTextField = itemPriceTextField.substring(0,itemPriceTextField.indexOf(PRICE_TREND_ENG)-1);

            String itemPriceString = itemPriceTextField
                    .replace(currency,"")
                    .replace(",",".")
                    .replace(" ","")
                    .trim();
            Float itemPriceFloat = new Float(itemPriceString);
            prices.add(itemPriceFloat);
        }
        return prices;
    }

    /**
     * This method returns the list of snippets' countries in SERP.
     * @return
     */
    public List<String> getSnippetCounries(){
        List<String> counries = new ArrayList<String>();
        for (WebElement resultCountry: snippetCountries) {
            String itemCountryTextField = resultCountry.getText()
                    .replace(FROM_RUS,"")
                    .replace(FROM_ENG,"")
                    .trim();
            counries.add(itemCountryTextField);
        }
        return counries;
    }

    /**
     * This method returns the list of snippets' format options in SERP.
     * @return
     */
    public List<String> getSnippetFormats(){
        List<String> formats = new ArrayList<String>();
        for (WebElement resultBuyNow: snippetFormats) {
            String itemBuyNowTextField = resultBuyNow.getText();
            formats.add(itemBuyNowTextField);
        }
        return formats;
    }

    /**
     * This method returns the list of snippets' hrefs in SERP.
     * @return
     */
    public List<String> getSnippetLinks(){
        List<String> links = new ArrayList<String>();
        for (WebElement resultLink: snippetLinks) {
            String itemLink = resultLink.getAttribute("href");
            links.add(itemLink);
        }
        return links;
    }

    public ProductPage goToProductPage (String href)
    {
        driver.get(href);
        return new ProductPage(driver);
    }

    public FilterSERP initFilters()
    {
        return new FilterSERP(driver);
    }

}
