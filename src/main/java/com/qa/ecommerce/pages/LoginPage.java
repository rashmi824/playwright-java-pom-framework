package com.qa.ecommerce.pages;

import com.microsoft.playwright.Page;

public class LoginPage {

    private final Page page;

    //1.String Locators - Object Repository
    private final String emailId = "//input[@id='input-email']";
    private final String password = "//input[@id='input-password']";
    private final String loginBtn = "//input[@value='Login']";
    private final String forgotPwdLink = "//div[@class='form-group']//a[normalize-space()='Forgotten Password']";
    private final String logoutLink ="//a[@class='list-group-item'][normalize-space()='Logout']";

    //2.Page Constructor
    public LoginPage(Page page){
        this.page=page;
    }

    //3.Page actions/methods
    public String getLoginPageTitle(){
        return page.title();
    }

    public boolean isForgotPwdLinkExist(){
        return page.isVisible(forgotPwdLink);
    }

    public boolean doLogin(String appUserName,String appPassword){
        System.out.println("App creds: " + appUserName + ":" + appPassword);
        page.fill(emailId, appUserName);
        page.fill(password, appPassword);
        page.click(loginBtn);
        if(page.isVisible(logoutLink)){
            System.out.println("user is logged in successfully......");
            return true;
        }
        return false;
    }

}
