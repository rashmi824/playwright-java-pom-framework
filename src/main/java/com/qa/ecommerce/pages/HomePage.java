package com.qa.ecommerce.pages;

import com.microsoft.playwright.Page;

public class HomePage {

    private Page page;

    //1.String Locators - Object repository

    private String search = "input[name='search']";
    private String searchIcon = "#search > span > button";
    private String searchPageHeader = "#content > h1";
    private String loginLink = "a:text('Login')";
    private String myAccountLink = "//a[@title='My Account']";


    //2.Page Constructor

    public HomePage(Page page){//1.runs automatically when creating a HomePage object.2.gets the browser page from your test class.
        this.page = page;//saves that browser page inside this class to use later.
    }

    //3.Page actions/methods
    public String getHomePageTitle(){
        String title = page.title();
        System.out.println("Page title:" + title);
        return title;
    }

    public String getHomePageURL(){
        String url = page.url();
        System.out.println("Page URL : "+url);
        return url;
    }
    public String doSearch(String productName){
        page.fill(search, productName);
        page.click(searchIcon);
        String header = page.textContent(searchPageHeader);
        System.out.println("search header : " + header);
        return header;

    }

    public LoginPage navigateToLoginPage() {
        page.click(myAccountLink);
        page.click(loginLink);
        return new LoginPage(page);//Creates a new LoginPage object with the same browser tab
    }

    }



