package com.example.tests;

import com.example.TestNgTestBase;
import com.example.data_providers.DataProvidersForTestFilters;
import com.example.pages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Marina on 24.05.2017.
 */
public class TestBuyNowFilter extends TestNgTestBase{
    private MainPage mainPage;
    private SearchForm searchForm;
    private FilterSERP filterSERP;
    private SERPage serPage;
    private ProductPage productPage;

    @BeforeMethod
    public void setUp()
    {
        mainPage = new MainPage(driver);
        mainPage.openPage(baseUrl);
        searchForm = new SearchForm(driver);
    }

    /**
     * testFilterSnippetsPrices(String REQUEST)
     * This method compares text in snippets of SERP with
     * constant strings "Buy It Now" and "Купить сейчас".
     * But it can work wrong in some cases (ex. when the textBox
     * under price in the snippet contains other special information,
     * such as "или предложение 'Лучшая цена'". So it's better to use
     * testFilterProductsPrices(String REQUEST) method.
     * @param REQUEST
     */
    @Test(dataProviderClass = DataProvidersForTestFilters.class,
            dataProvider = "dataForTestBuyNowFilter")
    public void testFilterSnippetsPrices(String REQUEST)
    {
        serPage = searchForm.search(REQUEST);
        filterSERP = new FilterSERP(driver);
        serPage = filterSERP.filterByBuyItNow();
        List<String> snippetFormats = serPage.getSnippetFormats();
        for (String format: snippetFormats)
        {
            assertThat(format).isIn("Buy It Now","Купить сейчас");
        }
    }

    /**
     * testFilterProductsPrices(String REQUEST)
     * This method checks that items' pages in the SERP after
     * filtering by "Buy It Now" parameter have button "Buy It Now"
     * @param REQUEST
     */
    @Test(dataProviderClass = DataProvidersForTestFilters.class,
            dataProvider = "dataForTestBuyNowFilter")
    public void testFilterProductsPrices(String REQUEST)
    {
        serPage = searchForm.search(REQUEST);
        filterSERP = new FilterSERP(driver);
        serPage = filterSERP.filterByBuyItNow();
        List<String> snippetHrefs = serPage.getSnippetLinks();
        //for (String href: snippetHrefs)
        // {
        String href = snippetHrefs.get(0);
        driver.get(href);
        productPage = new ProductPage(driver);
        boolean isBuyNow = productPage.getBuyItNowOption();
        Assert.assertTrue(isBuyNow);
        // }

    }
}
