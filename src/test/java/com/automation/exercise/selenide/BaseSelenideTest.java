package com.automation.exercise.selenide;

import com.automation.exercise.config.SelenideConfig;
import com.automation.exercise.pages.selenide.LoginPageSelenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseSelenideTest {

    protected LoginPageSelenide loginPage;
    @BeforeMethod
    public void setup(){
        loginPage = new LoginPageSelenide();
        SelenideConfig.setUp();
    }

    @AfterMethod
    public void close(){
        closeWebDriver();
    }

}
