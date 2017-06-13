package com.example.tests;

import com.example.data_providers.DataProvidersForTestFilters;
import com.example.tests.pages.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Marina on 24.05.2017.
 */
public class TestCategoryFilter extends TestNgTestBase{
    private MainPage mainPage;
    private FilterSERP filterSERP;
    private SERPage serPage;
    private ProductPage productPage;

    @BeforeMethod
    public void setUp()
    {
        mainPage = new MainPage(driver);
        mainPage.openPage(baseUrl);
    }

    /**
     * This method checks that items in the SERP after
     * filtering by category belong to this category
     * @param request
     */
    @Test(dataProviderClass = DataProvidersForTestFilters.class,
            dataProvider = "dataForTestCategoryFilter")
    public void testFilterProductsCategories(String request)
    {
        serPage = mainPage.search(request);
        filterSERP = serPage.initFilters();
        String category = filterSERP.getCategoryName();
        serPage = filterSERP.filterByCategory();
        List<String> snippetHrefs = serPage.getSnippetLinks();
        //for (String href: snippetHrefs)
       // {
        String href = snippetHrefs.get(0);
        productPage = serPage.goToProductPage(href);
        String productCategory = productPage.getProductCategory();
        assertThat(productCategory).isEqualToIgnoringCase(category);
       // }

    }
}
