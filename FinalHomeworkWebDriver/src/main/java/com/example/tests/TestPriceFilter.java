package com.example.tests;

import com.example.data_providers.DataProvidersForTestFilters;
import com.example.tests.pages.FilterSERP;
import com.example.tests.pages.MainPage;
import com.example.tests.pages.SERPage;
import com.example.tests.pages.SearchForm;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import static org.assertj.core.api.Assertions.*;

/**
 * Created by Marina on 24.05.2017.
 */
public class TestPriceFilter extends TestNgTestBase {
    private MainPage mainPage;
    private FilterSERP filterSERP;
    private SERPage serPage;

    @BeforeMethod
    public void setUp()
    {
        mainPage = new MainPage(driver);
        mainPage.openPage(baseUrl);
    }

    /**
     * This method checks that items' snippets in the SERP after
     * filtering by price belong to this limits
     * @param request
     */
    @Test (dataProviderClass = DataProvidersForTestFilters.class,
            dataProvider = "dataForTestPriceFilter")
    public void testFilterSnippetsPrices(String request, float min, float max)
    {
        serPage = mainPage.search(request);
        filterSERP = serPage.initFilters();
        serPage = filterSERP.filterByPrice(min,max);
        List<Float> snippetPrices = serPage.getSnippetPrices();
        for (Float price: snippetPrices)
        {
            assertThat(price).isBetween(min,max);
        }

    }

}
