package com.example.tests;

import com.example.TestNgTestBase;
import com.example.data_providers.DataProvidersForTestFilters;
import com.example.pages.FilterSERP;
import com.example.pages.MainPage;
import com.example.pages.SERPage;
import com.example.pages.SearchForm;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Marina on 24.05.2017.
 */
public class TestCountryUSAFilter extends TestNgTestBase{
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
     * filtering by country "USA only" belong to the "USA"
     * @param REQUEST
     */
    @Test(dataProviderClass = DataProvidersForTestFilters.class,
            dataProvider = "dataForTestCiuntryUSAFilter")
    public void testFilterSnippetsCountryUSA(String REQUEST)
    {
        serPage = searchForm.search(REQUEST);
        filterSERP = new FilterSERP(driver);
        serPage = filterSERP.filterByLocationUSA();
        List<String> snippetCountries = serPage.getSnippetCounries();
        for (String country: snippetCountries)
        {
            assertThat(country).isIn("США","United States");
        }
    }
}
