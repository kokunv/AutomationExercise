package com.automation.exercise.selenium.tests;

import com.automation.exercise.data.genertor.EmailGenerator;
import com.automation.exercise.data.signup.ISignUp;
import com.automation.exercise.data.signup.SignUpRepository;
import com.automation.exercise.data.user.UserCredentials;
import com.automation.exercise.pages.selenium.account.DeletedAccountPage;
import com.automation.exercise.pages.selenium.home.HomePage;
import com.automation.exercise.pages.selenium.login_signup.LoginPage;
import com.automation.exercise.selenium.runner.TestRunner;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends TestRunner {

    private static final String DEFAULT_USERNAME = "Garry";

    @DataProvider(name = "incorrectDataForLogin")
    public static Object[][] incorrectDataForLogin(){
        return new Object[][]{
                {" "," "},
                {"email","Qwerty4!"},
                {"email@gmail.com"," "},
                {"!@#$%^","*/!)(*&"}
        };
    }

    @Test(
            dataProvider = "incorrectDataForLogin",
            groups = {"login","negative"}
    )
    public void loginWithIncorrectData(String email, String password) {

        logger.info("Test: Login with incorrect credentials. Email: '{}', Password: '{}'", email, password);

        LoginPage loginPage = loadApplication().getHeader()
                .goToLoginPage()
                .loginWithIncorrectData(email, password);
        softAssert.assertTrue(loginPage.isLoginTextVisible(), "User isn't on Login Page");

    }

    @Test(
            groups = {"login","positive"}
    )
    public void loginUserSuccessfullyTest() {

        logger.info("Test: Successful login, name verification and account deletion");

        UserCredentials data = registerDefaultUser();

        LoginPage loginPage = loadApplication().getHeader()
                .goToLoginPage();

        softAssert.assertTrue(loginPage.isLoginTextVisible(),
                "Login text isn't visible.");

        HomePage homePage = loginPage.login(data.getEmail(), data.getPassword());
        boolean isLoggedNameCorrect = homePage.getLoggedHeader()
                .isUserLoggedName(DEFAULT_USERNAME);

        softAssert.assertTrue(isLoggedNameCorrect,"Logged name isn't the same.");

        DeletedAccountPage deletedAccountPage = homePage.getLoggedHeader().deleteAccount();
        softAssert.assertTrue(deletedAccountPage.isAccountDeleteMessageVisible(),
                "Account isn't deleted");

    }

    private UserCredentials registerDefaultUser(){

        logger.info("Pre-condition: Registering a new default user for the test");

        ISignUp user = SignUpRepository.get().validUser();
        String email = EmailGenerator.generateEmail();
        String password = user.getPassword();

        loadApplication().getHeader()
                .goToLoginPage()
                .signUp(DEFAULT_USERNAME, email)
                .fillFormAndCreateAccount(user)
                .continueToHomePage()
                .getLoggedHeader()
                .clickLogoutGoToHomePage();

        return new UserCredentials(email, password);
    }


}
