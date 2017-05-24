package com.example.tests;

import com.example.TestNgTestBase;
import com.example.data_providers.DataProvidersForTestFilters;
import com.example.pages.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import static org.assertj.core.api.Assertions.*;

/**
 * Created by Marina on 24.05.2017.
 */
public class TestPriceFilter extends TestNgTestBase {
    private MainPage mainPage;
    private SearchForm searchForm;
    private FilterSERP filterSERP;
    private SERPage serPage;

    @BeforeMethod
    public void setUp()
    {
        mainPage = new MainPage(driver);
        mainPage.openPage(baseUrl);
        searchForm = new SearchForm(driver);
    }

    /**
     * This method checks that items' snippets in the SERP after
     * filtering by price belong to this limits
     * @param REQUEST
     */
    @Test (dataProviderClass = DataProvidersForTestFilters.class,
            dataProvider = "dataForTestPriceFilter")
    public void testFilterSnippetsPrices(String REQUEST, float MIN, float MAX)
    {
        serPage = searchForm.search(REQUEST);
        filterSERP = new FilterSERP(driver);
        serPage = filterSERP.filterByPrice(MIN,MAX);
        List<Float> snippetPrices = serPage.getSnippetPrices();
        for (Float price: snippetPrices)
        {
            assertThat(price).isBetween(MIN,MAX);
        }

    }

}
