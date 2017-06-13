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
public class TestNewConditionFilter extends TestNgTestBase {
    private MainPage mainPage;
    private FilterSERP filterSERP;
    private SERPage serPage;
    private ProductPage productPage;
    private static final String NEW_RUS = new String("Новый");
    private static final String NEW_ENG = new String("New");

    @BeforeMethod
    public void setUp()
    {
        mainPage = new MainPage(driver);
        mainPage.openPage(baseUrl);
    }

    /**
     * This method checks that items in the SERP after
     * filtering by new condition have condition = new.
     * @param request
     */
    @Test(dataProviderClass = DataProvidersForTestFilters.class,
            dataProvider = "dataForTestNewConditionFilter")
    public void testFilterProductsNewCondition(String request)
    {
        serPage = mainPage.search(request);
        filterSERP = serPage.initFilters();
        serPage = filterSERP.filterByNewCondition();
        List<String> snippetHrefs = serPage.getSnippetLinks();
        //for (String href: snippetHrefs)
        // {
        String href = snippetHrefs.get(0);
        productPage = serPage.goToProductPage(href);
        String productCondition = productPage.getProductCondition();
        assertThat(productCondition).isIn(NEW_ENG,NEW_RUS);
        // }
    }
}

