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
public class TestReturnFilter extends TestNgTestBase {
    private MainPage mainPage;
    private SearchForm searchForm;
    private FilterSERP filterSERP;
    private SERPage serPage;
    private ProductPage productPage;
    private static final String NO_RETURN_RUS =
            new String("Продавец не предлагает возврат товаров.");
    private static final String NO_RETURN_ENG =
            new String("Seller does not offer returns.");

    @BeforeMethod
    public void setUp()
    {
        mainPage = new MainPage(driver);
        mainPage.openPage(baseUrl);
        searchForm = new SearchForm(driver);
    }

    /**
     * This method checks that items in the SERP after
     * filtering by return option = "Returns accepted"
     * have appropriate "returns" option value.
     * @param request
     */
    @Test(dataProviderClass = DataProvidersForTestFilters.class,
            dataProvider = "dataForTestReturnFilter")
    public void testFilterProductsReturnOption(String request)
    {
        serPage = searchForm.search(request);
        filterSERP = new FilterSERP(driver);
        serPage = filterSERP.filterByReturnOption();
        List<String> snippetHrefs = serPage.getSnippetLinks();
        //for (String href: snippetHrefs)
        // {
        String href = snippetHrefs.get(0);
        productPage = serPage.goToProductPage(href);
        String productReturnOption = productPage.getProductReturnOption();
        assertThat(productReturnOption).isNotIn(NO_RETURN_ENG,NO_RETURN_RUS);
        // }
    }
}
