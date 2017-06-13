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
public class TestFreeDeliveryFilter extends TestNgTestBase{
    private MainPage mainPage;
    private FilterSERP filterSERP;
    private SERPage serPage;
    private ProductPage productPage;
    private static final String FREE_RUS = new String("БЕСПЛАТНО");
    private static final String FREE_ENG = new String("FREE");

    @BeforeMethod
    public void setUp()
    {
        mainPage = new MainPage(driver);
        mainPage.openPage(baseUrl);
    }

    /**
     * This method checks that items' snippets in the SERP after
     * filtering by free delivery have delivery property = free.
     * @param request
     */
    @Test(dataProviderClass = DataProvidersForTestFilters.class,
            dataProvider = "dataForTestFreeDeliveryFilter")

    public void testFilterProductsFreeDelivery(String request)
    {
        serPage = mainPage.search(request);
        filterSERP = serPage.initFilters();
        serPage = filterSERP.filterByDeliveryFree();
        List<String> snippetHrefs = serPage.getSnippetLinks();
        //for (String href: snippetHrefs)
        // {
        String href = snippetHrefs.get(0);
        productPage = serPage.goToProductPage(href);
        String productDelivery = productPage.getProductDeliveryOption();
        assertThat(productDelivery).isIn(FREE_ENG,FREE_RUS);
        // }

    }
}
