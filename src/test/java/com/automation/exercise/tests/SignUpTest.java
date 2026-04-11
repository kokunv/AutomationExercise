package com.automation.exercise.tests;

import com.automation.exercise.components.header.HeaderLoggedUserComponent;
import com.automation.exercise.data.genertor.EmailGenerator;
import com.automation.exercise.data.signup.ISignUp;
import com.automation.exercise.data.signup.SignUpRepository;
import com.automation.exercise.pages.account.CreatedAccountPage;
import com.automation.exercise.pages.account.DeletedAccountPage;
import com.automation.exercise.pages.home.HomePage;
import com.automation.exercise.pages.login_signup.LoginPage;
import com.automation.exercise.pages.login_signup.SignUpPage;
import com.automation.exercise.runner.TestRunner;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SignUpTest extends TestRunner {

    private static final String EMAIL_ALREADY_EXISTS_ERROR = "Email Address already exist!";
    private static final String PASSWORD_ERROR_MESSAGE = "Заповніть це поле.";

    @DataProvider(name = "dataForSuccessfullySignUp")
    public static Object[][] dataForSuccessfullySignUp(){
        return new Object[][]{
                {"Garry", EmailGenerator.generateEmail(), SignUpRepository.get().validUser()}
        };
    }

    @DataProvider(name = "dataSignUpWithExistingEmail")
    public static Object[][] dataSignUpWithExistingEmail(){
        return new Object[][]{
                {"Garry", EmailGenerator.generateEmail(), SignUpRepository.get().validUser()}
        };
    }

    @DataProvider(name = "dataSignUpWithEmptyField")
    public static Object[][] dataSignUpWithEmptyField(){
        return new Object[][]{
                {"Garry", EmailGenerator.generateEmail(), SignUpRepository.get().userWithEmptyField()}
        };
    }


    @Test(
            dataProvider = "dataForSuccessfullySignUp",
            groups = {"signup","positive"}
    )
    public void registerUserSuccessfullyTest(String name, String email,ISignUp user){

        logger.info("Test: Starting successful registration for user: {} ({})", name, email);

        HomePage homePage = loadApplication();
        softAssert.assertTrue(homePage.isHomePageLoaded(),
                "Home page is not loaded");

        logger.info("Step: go to login and check sign up text visible.");
        LoginPage loginPage = homePage.getHeader().goToLoginPage();
        softAssert.assertTrue(loginPage.isSignUpTextVisible(),
                "'New User Signup!' is not visible");

        logger.info("Step: enter user name and email and check account info title visible.");
        SignUpPage signUpPage = loginPage.signUp(name, email);
        softAssert.assertTrue(signUpPage.isAccountInfoTitleVisible(),
                "'ENTER ACCOUNT INFORMATION' is not visible");

        logger.info("Step: fill form for sign in and check message is visible.");
        CreatedAccountPage createdAccountPage = signUpPage.fillFormAndCreateAccount(user);
        softAssert.assertTrue(createdAccountPage.createdAccountMessageIsVisible(),
                "'ACCOUNT CREATED!' is not visible");

        logger.info("Step: go to home and check username");
        createdAccountPage.continueToHomePage();
        HeaderLoggedUserComponent headerLoggedUserComponent = createdAccountPage.getLoggedHeader();
        softAssert.assertTrue(headerLoggedUserComponent.isUserLoggedName(name),
                "'Logged in as username' is not visible");

        logger.info("Step: delete account and check that deleted.");
        DeletedAccountPage deletedAccountPage = headerLoggedUserComponent.deleteAccount();
        softAssert.assertTrue(deletedAccountPage.isAccountDeleteMessageVisible(),
                "'ACCOUNT DELETED!' is not visible");

        softAssert.assertAll();

    }

    @Test(
            dataProvider = "dataSignUpWithExistingEmail",
            groups = {"signup","negative"}
    )
    public void registerUserWithExistingEmail(String name, String email, ISignUp user) {

        logger.info("Test: Verify registration prevention with existing email: {}", email);

        logger.info("Step: registration user.");
        HomePage homePage = loadApplication();
        homePage.getHeader()
                .goToLoginPage()
                .signUp(name,email)
                .fillFormAndCreateAccount(user)
                .continueToHomePage()
                .getLoggedHeader()
                .clickLogoutGoToHomePage();

        logger.info("Step: Go to login page.");
        LoginPage loginPage = homePage.getHeader().goToLoginPage();

        logger.info("Step: sign uo with the same email.");
        loginPage.signUpWithIncorrectData(name,email);

        String actualError = loginPage.getAccountExistMessage().getText();
        logger.info("Step: Check error message {}",actualError);
        softAssert.assertEquals(actualError,
                EMAIL_ALREADY_EXISTS_ERROR,
                "Incorrect error message");
        softAssert.assertTrue(loginPage.isAccountExistMessageVisible(),
                "Message " + loginPage.getAccountExistMessage().getText()
                        + " is not visible");
        softAssert.assertFalse(loginPage.getLoggedHeader().isUserLogged(), "User must be not logged");
        softAssert.assertTrue(loginPage.isSignUpTextVisible(),
                "User is not on Login page.");
        softAssert.assertAll();

    }

    @Test(
            dataProvider = "dataSignUpWithEmptyField",
            groups = {"signup"}

    )
    public void registerUserWithEmptyField(String name, String email, ISignUp user){

        logger.info("Test: Verify sign in with empty field");
        SignUpPage signUpPage = loadApplication().getHeader()
                .goToLoginPage()
                .signUp(name,email)
                .fillFormAndClickSubmit(user);

        String validationMsg = signUpPage.getPasswordValidationMessage();
        logger.info("Step: Checking validation message '{}'", validationMsg);
        softAssert.assertTrue(signUpPage.isAccountInfoTitleVisible(),
                "User can't register with empty field.");
        softAssert.assertFalse(validationMsg.isEmpty(),
                "Validation message should be displayed for empty password");
        softAssert.assertFalse(signUpPage.getLoggedHeader().isUserLogged(),
                "User should not be registered with empty fields");
        softAssert.assertAll();
    }




}

















