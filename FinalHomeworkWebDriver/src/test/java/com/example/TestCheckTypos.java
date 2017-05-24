package com.example;

import com.example.pages.MainPage;
import com.example.pages.ResultPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by tatanaberlenko on 11.05.17.
 */
public class TestCheckTypos extends TestNgTestBase{
    private MainPage mainPage;
    /**
     * Go to the main page
     */
    @BeforeMethod
    public void setUp() {
        mainPage = PageFactory.initElements(driver, MainPage.class);
        mainPage.openPage(baseUrl);
    }


    @DataProvider
    private Object[][] dataRequestRu() {
        return new Object[][]{
                { "тюльпен", "тюльпан" },{ "телевизр", "телевизор" }//,
                //{ "глбус", "глобус" },{"синтатор", "синтезатор" },
                //{ "мониор", "монитор" },{ "генератр", "генератор"}
        };
    }


    @Test(dataProvider = "dataRequestRu")
    public void testCheckMisprintsOnMainPageRu(String misprintStr, String validStr) {
        ResultPage resultPage = mainPage.search(misprintStr);;

        String msg = resultPage.getContainerText();
        Assert.assertTrue(msg.contains(validStr));
        List<String> titles = resultPage.getTitles();
        for(String title:titles){
            Assert.assertTrue(title.contains(validStr));
        }
    }

    @AfterMethod
    public void tearDown() {

        driver.quit();
    }
}
