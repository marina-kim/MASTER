package com.example.tests;

import com.example.TestNgTestBase;
import com.example.data_providers.DataProvidersForTestFilters;
import com.example.pages.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Marina on 24.05.2017.
 */
public class TestNewConditionFilter extends TestNgTestBase {
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
     * This method checks that items in the SERP after
     * filtering by new condition have condition = new.
     * @param REQUEST
     */
    @Test(dataProviderClass = DataProvidersForTestFilters.class,
            dataProvider = "dataForTestNewConditionFilter")
    public void testFilterProductsNewCondition(String REQUEST)
    {
        serPage = searchForm.search(REQUEST);
        filterSERP = new FilterSERP(driver);
        serPage = filterSERP.filterByNewCondition();
        List<String> snippetHrefs = serPage.getSnippetLinks();
        //for (String href: snippetHrefs)
        // {
        String href = snippetHrefs.get(0);
        driver.get(href);
        productPage = new ProductPage(driver);
        String productCondition = productPage.getProductCondition();
        assertThat(productCondition).isIn("New","Новый");
        // }
    }
}

