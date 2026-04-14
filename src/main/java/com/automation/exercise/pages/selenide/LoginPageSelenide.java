package com.automation.exercise.pages.selenide;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Getter
public class LoginPageSelenide {

    private SelenideElement emailLoginInput =
            $x("//input[@data-qa='login-email']");

    private SelenideElement passwordLoginInput =
            $x("//input[@data-qa='login-password']");

    private SelenideElement loginButton =
            $x("//button[@data-qa='login-button']");

    private SelenideElement loginTitle =
            $x("//h2[contains(text(),'Login to your account')]");


    public LoginPageSelenide openPage() {
        open("/login");
        return this;
    }

    public LoginPageSelenide login(String email, String password){
        emailLoginInput.setValue(email);
        passwordLoginInput.setValue(password);
        loginButton.click();

        return this;
    }

    public void shouldBeOnLoginPage() {
         loginTitle.shouldBe(visible);
    }

    public String getEmailValidationMessage(){
        String message = emailLoginInput.getAttribute("validationMessage");
        return message;
    }

    public String getPasswordValidationMessage(){
        String message = passwordLoginInput.getAttribute("validationMessage");
        return message;
    }



}
