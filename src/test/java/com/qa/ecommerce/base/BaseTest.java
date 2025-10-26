package com.qa.ecommerce.base;

import com.microsoft.playwright.Page;
import com.qa.ecommerce.factory.PlaywrightFactory;
import com.qa.ecommerce.pages.HomePage;
import com.qa.ecommerce.pages.LoginPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    PlaywrightFactory pf;//Used to launch browser
    Page page; // Represents the browser page
    protected Properties prop;

    protected HomePage homePage;// Page object for the Home page
    protected LoginPage loginPage;

    @BeforeTest
    public void setup() throws IOException {
        pf = new PlaywrightFactory();// Create PlaywrightFactory object
        prop = pf.init_prop();
        page = pf.initBrowser(prop); // Launch Chromium browser

        homePage = new HomePage(page);// Create HomePage object using page

    }

    @AfterTest
    public void tearDown(){ // Runs after all tests
        page.context().browser().close(); // Close browser
    }


}
