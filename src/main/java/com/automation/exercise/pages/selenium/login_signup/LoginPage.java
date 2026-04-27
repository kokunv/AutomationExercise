package com.automation.exercise.pages.selenium.login_signup;

import com.automation.exercise.pages.selenium.base.BasePage;
import com.automation.exercise.pages.selenium.home.HomePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

@Getter
public class LoginPage extends BasePage {

    @FindBy(xpath = "//h2[@class='or']")
    private WebElement orTitle;

    @FindBy(xpath = "//h2[contains(text(),'Login to your account')]")
    private WebElement loginTitle;

    @FindBy(xpath = "//h2[contains(text(),'New User Signup!')]")
    private WebElement signUpTitle;

    @FindBy(xpath = "//input[@data-qa='login-email']")
    private WebElement emailLoginInput;

    @FindBy(xpath = "//input[@data-qa='login-password']")
    private WebElement passwordLoginInput;

    @FindBy(xpath = "//button[@data-qa='login-button']")
    private WebElement loginButton;

    @FindBy(xpath = "//input[@data-qa='signup-name']")
    private WebElement nameSignUpInput;

    @FindBy(xpath = "//input[@data-qa='signup-email']")
    private WebElement emailSignUpInput;

    @FindBy(xpath = "//button[@data-qa='signup-button']")
    private WebElement signUpButton;

    @FindBy(xpath = "//p[contains(text(),'Email Address already exist!')]")
    private WebElement accountExistMessage;


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isSignUpTextVisible() {
        logger.info("Verifying visibility of SignUp title");
        return signUpTitle.isDisplayed();
    }

    public boolean isLoginTextVisible() {
        logger.info("Verifying visibility of Login title");
        return loginTitle.isDisplayed();
    }

    public boolean isAccountExistMessageVisible() {
        logger.info("Checking if 'Email Address already exist!' message is visible");
        return accountExistMessage.isDisplayed();
    }

    public void inputEmailInLogin(String email) {
        logger.info("Action: Entering login email: {}", email);
        actions.clickDynamicElement(emailLoginInput);
        emailLoginInput.clear();
        emailLoginInput.sendKeys(email);
    }

    public void inputPasswordInLogin(String password) {
        logger.info("Action: Entering login password");
        actions.clickDynamicElement(passwordLoginInput);
        passwordLoginInput.clear();
        passwordLoginInput.sendKeys(password);
    }

    public void inputNameInSignUp(String name) {
        logger.info("Action: Entering signup name: {}", name);
        wait.waitAndClickOnElement(nameSignUpInput);
        nameSignUpInput.clear();
        nameSignUpInput.sendKeys(name);
    }

    public void inputEmailInSignUp(String email) {
        logger.info("Action: Entering signup email: {}", email);
        wait.waitAndClickOnElement(emailSignUpInput);
        emailSignUpInput.clear();
        emailSignUpInput.sendKeys(email);
    }

    public LoginPage signUpWithIncorrectData(String name, String email) {
        logger.info("Attempting SignUp with incorrect data: Name={}, Email={}", name, email);
        inputNameInSignUp(name);
        inputEmailInSignUp(email);
        wait.waitAndClickOnElement(signUpButton);

        return this;
    }

    public LoginPage loginWithIncorrectData(String email, String password) {
        logger.info("Attempting Login with incorrect data for email: {}", email);
        inputEmailInLogin(email);
        inputPasswordInLogin(password);
        wait.waitAndClickOnElement(loginButton);

        return this;
    }

    public SignUpPage signUp(String name, String email) {
        logger.info("Starting SignUp process for user: {} ({})", name, email);
        inputNameInSignUp(name);
        inputEmailInSignUp(email);
        wait.waitAndClickOnElement(signUpButton);

        return new SignUpPage(driver);
    }

    public HomePage login(String email, String password) {
        logger.info("Performing Login for user: {}", email);
        inputEmailInLogin(email);
        inputPasswordInLogin(password);
        wait.runWithImplicitWait(Duration.ofSeconds(4), () -> loginButton.click());

        return new HomePage(driver);
    }


}


















