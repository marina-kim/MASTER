package com.example.tests;

import com.example.TestNgTestBase;
import com.example.data_providers.DataProvidersForTestFilters;
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
    private static final String USA_RUS = new String("США");
    private static final String USA_ENG = new String("United States");


    @BeforeMethod
    public void setUp()
    {
        mainPage = new MainPage(driver);
        mainPage.openPage(baseUrl);
        if (mainPage.isMainPageOpen())
            searchForm = new SearchForm(driver);
    }

    /**
     * This method checks that items' snippets in the SERP after
     * filtering by country "USA only" belong to the "USA"
     * @param request
     */
    @Test(dataProviderClass = DataProvidersForTestFilters.class,
            dataProvider = "dataForTestCiuntryUSAFilter")
    public void testFilterSnippetsCountryUSA(String request)
    {
        serPage = searchForm.search(request);
        filterSERP = serPage.initFilters();
        serPage = filterSERP.filterByLocationUSA();
        List<String> snippetCountries = serPage.getSnippetCounries();
        for (String country: snippetCountries)
        {
            assertThat(country).isIn(USA_RUS,USA_ENG);
        }
    }
}
