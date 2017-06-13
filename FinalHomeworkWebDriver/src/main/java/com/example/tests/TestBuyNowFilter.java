package com.example.tests;

import com.example.data_providers.DataProvidersForTestFilters;
import com.example.tests.pages.*;
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

    private static final String BUY_NOW_RUS = new String("Buy It Now");
    private static final String BUY_NOW_ENG = new String("Купить сейчас");

    @BeforeMethod
    public void setUp(){
        mainPage = new MainPage(driver);
        searchForm = mainPage.openPage(baseUrl);
        //searchForm = mainPage.searchForm;
    }

    /**
     * testFilterSnippetsPrices(String request)
     * This method compares text in snippets of SERP with
     * constant strings "Buy It Now" and "Купить сейчас".
     * But it can work wrong in some cases (ex. when the textBox
     * under price in the snippet contains other special information,
     * such as "или предложение 'Лучшая цена'". So it's better to use
     * testFilterProductsPrices(String REQUEST) method.
     * @param request
     */
    @Test(dataProviderClass = DataProvidersForTestFilters.class,
            dataProvider = "dataForTestBuyNowFilter")
    public void testFilterSnippetsBuyNow(String request)
    {
        serPage = searchForm.search(request);
        filterSERP = serPage.initFilters();
        serPage = filterSERP.filterByBuyItNow();
        List<String> snippetFormats = serPage.getSnippetFormats();
        for (String format: snippetFormats)
        {
            assertThat(format).isIn(BUY_NOW_ENG,BUY_NOW_RUS);
        }
    }

    /**
     * testFilterProductsPrices(String request)
     * This method checks that items' pages in the SERP after
     * filtering by "Buy It Now" parameter have button "Buy It Now"
     * @param request
     */
    @Test(dataProviderClass = DataProvidersForTestFilters.class,
            dataProvider = "dataForTestBuyNowFilter")
    public void testFilterProductsBuyNow(String request)
    {
        serPage = searchForm.search(request);
        filterSERP = serPage.initFilters();
        serPage = filterSERP.filterByBuyItNow();
        List<String> snippetHrefs = serPage.getSnippetLinks();
        //for (String href: snippetHrefs)
        // {
        String href = snippetHrefs.get(0);
        productPage = serPage.goToProductPage(href);
        boolean isBuyNow = productPage.getBuyItNowOption();
        Assert.assertTrue(isBuyNow);
        // }

    }
}
