package com.automation.exercise.cucumber.steps;

import com.automation.exercise.cucumber.Hooks;
import com.automation.exercise.ui.components.header.HeaderLoggedUserComponent;
import com.automation.exercise.ui.data.signup.ISignUp;
import com.automation.exercise.ui.data.signup.SignUpRepository;
import com.automation.exercise.ui.pages.selenium.account.CreatedAccountPage;
import com.automation.exercise.ui.pages.selenium.home.HomePage;
import com.automation.exercise.ui.pages.selenium.login_signup.LoginPage;
import com.automation.exercise.ui.pages.selenium.login_signup.SignUpPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SignUpSteps {

    private final Hooks hooks;

    private final ISignUp user = SignUpRepository.get().validUser();

    public SignUpSteps(Hooks hooks) {
        this.hooks = hooks;
    }

    private HomePage getHomePage() {
        return new HomePage(hooks.getDriver());
    }

    private LoginPage getLoginPage() {
        return new LoginPage(hooks.getDriver());
    }

    private SignUpPage getSignUpPage() {
        return new SignUpPage(hooks.getDriver());
    }

    private CreatedAccountPage getCreatedAccountPage() {
        return new CreatedAccountPage(hooks.getDriver());
    }

    private HeaderLoggedUserComponent getHeaderLoggedUserComponent() {
        return getHomePage().getLoggedHeader();
    }

    @Given("user open sign up page")
    public void userOpenSignupPage(){
        getHomePage().getHeader().goToLoginPage();
    }

    @Then("sign up page should be loaded")
    public void signUpShouldBeLoaded(){
        hooks.getSoftAssert().assertTrue(getLoginPage().isLoginTextVisible());
    }

    @When("user sign up with {string} and {string}")
    public void signUpUser(String name, String email){
        getLoginPage().signUp(name, email);
    }

    @When("user fills registration form and create account")
    public void fillFormAndCreateAccount(){
        getSignUpPage().fillFormAndCreateAccount(user);
    }

    @Then("account created message should be visible")
    public void accountShouldBeCreated(){
        hooks.getSoftAssert().assertTrue(getCreatedAccountPage().createdAccountMessageIsVisible());
    }

    @When("user continues to home page")
    public void continueToHomePage(){
        getCreatedAccountPage().continueToHomePage();
    }

    @Then("logged {string} should be visible")
    public void loggedNameShouldBeVisible(String username){
        getHeaderLoggedUserComponent().isUserLoggedName(username);
    }

}
