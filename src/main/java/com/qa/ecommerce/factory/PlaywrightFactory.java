package com.qa.ecommerce.factory;

import com.microsoft.playwright.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Properties;

public class PlaywrightFactory {

    Properties prop;

    private static ThreadLocal<Browser>tlBrowser = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext>tlBrowserContext = new ThreadLocal<>();
    private static ThreadLocal<Page>tlPage = new ThreadLocal<>();
    private static ThreadLocal<Playwright>tlPlaywright = new ThreadLocal<>();


    public static Playwright getPlaywright(){
        return tlPlaywright.get();
    }
    public static Browser getBrowser(){
        return tlBrowser.get();
    }
    public static BrowserContext getBrowserContext(){
        return tlBrowserContext.get();
    }
    public static Page getPage(){
        return tlPage.get();
    }

    public Page initBrowser(Properties prop){
        String browserName = prop.getProperty("browser").trim();
        System.out.println("browser name is : " + browserName);
        //playwright = Playwright.create();
        tlPlaywright.set(Playwright.create());


        switch (browserName.toLowerCase()){
            case "chromium":
               /*browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));*/
                tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "firefox":
                tlBrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "safari":
                tlBrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "chrome"://If browserName is "chrome"
                tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false)));//Launch Google Chrome (through Chromium engine) in visible mode.
                break;

            default :
                System.out.println("please pass the right browser name......");
                break;
        }

       /*browserContext = browser.newContext();*/
        tlBrowserContext.set(getBrowser().newContext());
        //page = browserContext.newPage();
        tlPage.set(getBrowserContext().newPage());
       // page.navigate(prop.getProperty("url").trim());
        getPage().navigate(prop.getProperty("url").trim());
        return getPage();
    }


    //this method is used to initialize the properties from config.properties file

    public Properties init_prop() throws IOException {
        FileInputStream ip = new FileInputStream("src/test/resources/config/config.properties");
        prop = new Properties();
        prop.load(ip);
        return prop;
    }

//take screenshort - handle by Playwright
public static String takeScreenshot() {
    // Take screenshot as bytes
    byte[] buffer = getPage().screenshot(
            new Page.ScreenshotOptions().setFullPage(true)
    );

    // Convert bytes to Base64 string
    String base64Screenshot = Base64.getEncoder().encodeToString(buffer);

    return base64Screenshot;
}



    }



