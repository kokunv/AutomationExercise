package com.automation.exercise.selenide;

import com.automation.exercise.pages.selenide.HomePageSelenide;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class LoginSelenideTests extends BaseSelenideTest {

    private static final String ENTER_PART_BEFORE_SIGN =
            "Введіть частину електронної адреси до знака \"@\". Електронна адреса \"@\" неповна.";
    private static final String ENTER_PART_AFTER_SIGN =
            "Введіть частину електронної адреси після знака \"@\". Електронна адреса \"email@\" неповна.";
    private static final String MUST_CONTAIN_SIGN =
            "Електронна адреса має містити знак \"@\". В електронній адресі \"email\" знака \"@\" немає";
    private static final String CANNOT_CONTAIN_SYMBOL =
            "Частина після знака \"@\" не може містити символ \"!\".";

    @DataProvider(name = "incorrectEmailForLogin")
    public Object[][] incorrectEmailForLogin() {
        return new Object[][]{
                {"@", "Qwerty4!", ENTER_PART_BEFORE_SIGN},
                {"email", "Qwerty4!",MUST_CONTAIN_SIGN},
                {"email@", "Qwerty4!",ENTER_PART_AFTER_SIGN},
                {"!#$%^&*@gmail.!", "Qwerty4!", CANNOT_CONTAIN_SYMBOL}
        };
    }

    @DataProvider(name = "correctDataForLogin")
    public Object[][] correctDataForLogin() {
        return new Object[][]{
                {"xxu99510@jioso.com", " "}
        };
    }

    @DataProvider(name = "incorrectPasswordForLogin")
    public Object[][] incorrectPasswordForLogin() {
        return new Object[][]{
                {"xxu99510@jioso.com", ""},
                {"xxu99510@jioso.com", " "},
                {"xxu99510@jioso.com", "********"},
                {"xxu99510@jioso.com", "0"},
        };
    }


    @Test(dataProvider = "incorrectEmailForLogin")
    public void loginWithIncorrectEmail(String email, String password, String message) {

        loginPage.openPage()
                 .login(email, password);

         loginPage.shouldBeOnLoginPage();
         assertTrue(loginPage.getEmailValidationMessage().contains(message),
                 "Expected validation message was not displayed");
    }

    @Test(dataProvider = "incorrectPasswordForLogin")
    public void loginWithIncorrectPassword(String email,String password){
        loginPage.openPage()
                .login(email, password);

        loginPage.shouldBeOnLoginPage();
        assertFalse(loginPage.getPasswordValidationMessage().isEmpty());
    }

    @Test(dataProvider = "correctDataForLogin")
    public void successfulLogin(String email, String password){

        loginPage.openPage()
                .login(email, password);

        HomePageSelenide homePage = new HomePageSelenide();

        homePage.shouldBeOnHomePage();

    }

}
