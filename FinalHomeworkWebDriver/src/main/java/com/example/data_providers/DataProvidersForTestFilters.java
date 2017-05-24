package com.example.data_providers;

import org.testng.annotations.DataProvider;

/**
 * Created by Marina on 24.05.2017.
 */
public class DataProvidersForTestFilters {

    @DataProvider
    public static Object[][] dataForTestPriceFilter()
    {
        return new Object[][]{
                {"платье", 1000, 2000},
                {"футбольный мяч", 999.9f, 3500.5f}
               // {"калькулятор", 100, 200}
        };

    }

    @DataProvider
    public static Object[][] dataForTestCategoryFilter() {
        return new Object[][]{
                {"тюльпан"},
                {"розовый слон"}
        };
    }

    @DataProvider
    public static Object[][] dataForTestFreeDeliveryFilter() {
        return new Object[][]{
                {"калькулятор"},
                {"iphone 7"}
        };
    }

    @DataProvider
    public static Object[][] dataForTestNewConditionFilter() {
        return new Object[][]{
                {"утюг Tefal"},
                {"xiaomi mi band"}
        };
    }
    @DataProvider
    public static Object[][] dataForTestBuyNowFilter() {
        return new Object[][]{
                {"сумка Fendi"},
                {"туфли кожаные женские"}
        };
    }
    @DataProvider
    public static Object[][] dataForTestReturnFilter() {
        return new Object[][]{
                {"платье с тюльпанами"},
                {"салфетки бумажные"}
        };
    }

    @DataProvider
    public static Object[][] dataForTestCiuntryUSAFilter() {
        return new Object[][]{
                {"платье с тюльпанами"},
                {"кроссовки New Balance"}
        };
    }

}
