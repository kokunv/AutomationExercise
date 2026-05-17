package com.automation.exercise.cucumber.steps;

import com.automation.exercise.cucumber.Hooks;
import com.automation.exercise.ui.pages.selenium.login_signup.LoginPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

    private final Hooks hooks;

    public LoginSteps(Hooks hooks) {
        this.hooks = hooks;
    }

    private LoginPage getLoginPage() {
        return new LoginPage(hooks.getDriver());
    }

    @When("user fills {string} and {string}")
    public void userFillsData(String email, String password){
        getLoginPage().loginWithIncorrectData(email, password);
    }

    @Then("user still on login page")
    public void userShouldBeOnLoginPage(){
        hooks.getSoftAssert().assertTrue(getLoginPage()
                .isLoginTextVisible(),
                "User isn't on Login Page");
    }
}
