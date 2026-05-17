package com.automation.exercise.cucumber.steps;

import com.automation.exercise.cucumber.Hooks;
import com.automation.exercise.ui.components.header.HeaderLoggedUserComponent;
import com.automation.exercise.ui.data.genertor.EmailGenerator;
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

    private final String name = "Garry";
    private final String email = EmailGenerator.generateEmail();
    private final ISignUp validUser = SignUpRepository.get().validUser();
    private final ISignUp emptyUser = SignUpRepository.get().userWithEmptyField();

    private static final String EMAIL_ALREADY_EXISTS_ERROR = "Email Address already exist!";


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

    @Given("user open login page")
    @Given("user open sign up page")
    public void userOpenSignupPage(){
        getHomePage().getHeader().goToLoginPage();
    }

    @Then("sign up page should be loaded")
    @Then("login page should be loaded")
    public void signUpShouldBeLoaded(){
        hooks.getSoftAssert().assertTrue(getLoginPage().isLoginTextVisible());
    }

    @When("user registers with valid data")
    public void registerUser(){
        getLoginPage().signUp(name, email)
                      .fillFormAndCreateAccount(validUser)
                      .continueToHomePage();


    }

    @When("user sign up with name {string}")
    public void signUpUser(String name){
        getLoginPage().signUp(name, email);
    }

    @When("user fills registration form and create account")
    public void fillFormAndCreateAccount(){
        getSignUpPage().fillFormAndCreateAccount(validUser);
    }

    @When("user keep empty fields and click create account")
    public void userKeepEmptyFields(){
        getSignUpPage().fillFormAndClickSubmit(emptyUser);
    }

    @When("user continues to home page")
    public void continueToHomePage(){
        getCreatedAccountPage()
                .continueToHomePage();
    }

    @When("user logs out")
    public void userLogout(){
        getHeaderLoggedUserComponent().clickLogoutGoToHomePage();
    }

    @When("user tries to register with the same email")
    public void userTriesRegisterWithTheSameEmail(){
        getLoginPage().signUpWithIncorrectData(name, email);
    }

    @Then("user can't register with empty field")
    public void userCanNotRegisterWithEmptyFields(){
        hooks.getSoftAssert().assertFalse(getSignUpPage()
                        .getLoggedHeader()
                        .isUserLogged(),
                "User should not be registered with empty fields");
    }

    @Then("validation message should be displayed")
    public void validationMsgDisplayed(){
        String validationMsg = getSignUpPage().getPasswordValidationMessage();
        hooks.getSoftAssert().assertFalse(validationMsg.isEmpty(),
                "Validation message should be displayed for empty password");
    }

    @Then("account already exists message should be displayed")
    public void accountAlreadyExistDisplayed(){
        String actualError = getLoginPage().getAccountExistMessage().getText();
        hooks.getSoftAssert().assertEquals(actualError,
                EMAIL_ALREADY_EXISTS_ERROR,
                "Incorrect error message");
    }

    @Then("user should not be logged in")
    public void userShouldNotBeLoggedIn(){
        hooks.getSoftAssert().assertFalse(getLoginPage()
                .getLoggedHeader()
                .isUserLogged(),
                "User must be not logged");
    }

    @Then("user should stay on sign up page")
    public void userShouldStayOnSignupPage(){
        hooks.getSoftAssert().assertTrue(getSignUpPage()
                        .isAccountInfoTitleVisible(),
                "User can't register with empty field.");
    }

    @Then("user should stay on login page")
    public void userShouldStayOnLoginPage(){
        hooks.getSoftAssert().assertTrue(
                getLoginPage().isSignUpTextVisible(),
                "User is not on Login page"
        );
    }

    @Then("account created message should be visible")
    public void accountShouldBeCreated(){
        hooks.getSoftAssert().assertTrue(getCreatedAccountPage()
                .createdAccountMessageIsVisible());
    }

    @Then("logged {string} should be visible")
    public void loggedNameShouldBeVisible(String username){
        getHeaderLoggedUserComponent().isUserLoggedName(username);
    }

}
