package com.qa.ecommerce.tests;
import com.qa.ecommerce.base.BaseTest;
import com.qa.ecommerce.constants.AppConstants;

import com.qa.ecommerce.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.*;

public class HomePageTest extends BaseTest {



    @Test
    public void homePageTitleTest(){
        String actualTitle = homePage.getHomePageTitle();
        Assert.assertEquals(actualTitle, AppConstants.HOME_PAGE_TITLE);
    }
    @Test
    public void homePageURLTest(){
        String actualURL = homePage.getHomePageURL();
        Assert.assertEquals(actualURL,prop.getProperty("url"));
    }

    @DataProvider
    public Object[][] getProductData(){
        return new Object[][]{
                {"Macbook"},
                {"iMac"},
                {"Samsung"}
        };
    }

    @Test(dataProvider = "getProductData")
    public void searchTest(String productName){
        String actualSearchHeader = homePage.doSearch(productName);
        Assert.assertEquals(actualSearchHeader,"Search - " +productName);

    }

    //@DataProvider → This TestNG annotation is used to send multiple sets of data to a test method.
    //The test method that uses it will run once for each data set.
    //Object[][] → means a two-dimensional array — it holds multiple rows of data (each row = one test input).



}
