package com.automation.exercise.pages.selenium.login_signup;

import com.automation.exercise.data.signup.ISignUp;
import com.automation.exercise.pages.selenium.account.CreatedAccountPage;
import com.automation.exercise.pages.selenium.base.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class SignUpPage extends BasePage {

    @FindBy(xpath = "//b[contains(text(),'Enter Account Information')]")
    private WebElement accountInformationTitle;

    @FindBy(id = "id_gender1")
    private WebElement genderMrInput;

    @FindBy(id = "id_gender2")
    private WebElement genderMrsInput;

    @FindBy(css = "#password")
    private WebElement passwordInput;

    @FindBy(css = "#days")
    private WebElement dayOfBirthdaySelect;

    @FindBy(css = "#months")
    private WebElement monthsOfBirthdaySelect;

    @FindBy(css = "#years")
    private WebElement yearOfBirthdaySelect;

    @FindBy(id = "newsletter")
    private WebElement newsLetterCheckbox;

    @FindBy(id = "optin")
    private WebElement specialOffersCheckbox;

    @FindBy(id = "first_name")
    private WebElement firstnameInput;

    @FindBy(id = "last_name")
    private WebElement lastnameInput;

    @FindBy(id = "address1")
    private WebElement firstAddressInput;

    @FindBy(id = "address2")
    private WebElement secondAddressInput;

    @FindBy(id = "country")
    private WebElement countrySelect;

    @FindBy(id = "state")
    private WebElement stateInput;

    @FindBy(id = "city")
    private WebElement cityInput;

    @FindBy(id = "zipcode")
    private WebElement zipCodeInput;

    @FindBy(id = "mobile_number")
    private WebElement mobileNumberInput;

    @FindBy(xpath = "//button[@data-qa='create-account']")
    private WebElement createAccountButton;


    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    public String getPasswordValidationMessage() {
        String message = passwordInput.getAttribute("validationMessage");
        logger.debug("Password field validation message: '{}'", message);

        return message;
    }

    public boolean isAccountInfoTitleVisible() {
        logger.info("Checking visibility of 'Enter Account Information' title");
        return accountInformationTitle.isDisplayed();
    }

    public void submitCreateAccount() {
        logger.info("Step: Submitting 'Create Account' form");
        scrollToElementJs(createAccountButton);
        createAccountButton.click();
    }

    public void selectOptionalBirthday(ISignUp data) {

        if (data.getDay() == null || data.getMonth() == null || data.getYear() == null) {
            logger.debug("Birthday data is missing, skipping optional selection");
            return;
        }

        logger.info("Action: Selecting birthday: {} - {} - {}", data.getDay(), data.getMonth(), data.getYear());
        selectByVisibleText(dayOfBirthdaySelect, data.getDay());
        selectByVisibleText(monthsOfBirthdaySelect, data.getMonth());
        selectByVisibleText(yearOfBirthdaySelect, data.getYear());
    }

    public void selectOptionalGender(ISignUp data) {

        if (data.getGender() == null) {
            logger.debug("Gender data is missing, skipping gender selection");
            return;
        }

        logger.info("Action: Selecting gender: {}", data.getGender());
        switch (data.getGender()) {
            case Mr:
                genderMrInput.click();
                break;
            case Mrs:
                genderMrsInput.click();
                break;
        }

    }

    public void selectCountry(ISignUp data) {
        if (data.getCountry() == null) {
            logger.debug("Country data is missing, skipping country selection");
            return;
        }

        logger.info("Action: Selecting country: {}", data.getCountry());
        selectByVisibleText(countrySelect, data.getCountry());
    }

    public void fillFormSignUp(ISignUp data) {

        logger.info("Action: Filling in the Sign Up form for user: {} {}", data.getFirstName(), data.getLastName());

        passwordInput.sendKeys(data.getPassword());

        firstnameInput.sendKeys(data.getFirstName());

        lastnameInput.sendKeys(data.getLastName());

        firstAddressInput.sendKeys(data.getAddress1());

        stateInput.sendKeys(data.getState());

        cityInput.sendKeys(data.getCity());

        zipCodeInput.sendKeys(data.getZipCode());

        mobileNumberInput.sendKeys(data.getMobile());

        logger.info("Fill form sign up successfully");
    }

    public SignUpPage fillFormAndClickSubmit(ISignUp user) {

        logger.info("Step: Fill form and click submit (negative scenario expected)");
        fillFormSignUp(user);
        fillFormSignUp(user);
        selectCountry(user);
        submitCreateAccount();
        return this;
    }

    public CreatedAccountPage fillFormAndCreateAccount(ISignUp user) {

        logger.info("Step: Completing full registration process");
        fillFormSignUp(user);
        selectCountry(user);
        selectOptionalBirthday(user);
        selectOptionalGender(user);
        submitCreateAccount();
        return new CreatedAccountPage(driver);
    }

}












