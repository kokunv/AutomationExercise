package com.automation.exercise.pages.account;

import com.automation.exercise.pages.base.BasePage;
import com.automation.exercise.pages.home.HomePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class CreatedAccountPage extends BasePage {

    @FindBy(xpath = "//h2[@data-qa='account-created']")
    private WebElement createdAccountMessage;

    @FindBy(xpath = "//a[@data-qa='continue-button']")
    private WebElement continueButton;

    public CreatedAccountPage(WebDriver driver) {
        super(driver);
    }

    public boolean createdAccountMessageIsVisible(){
        logger.info("Checking visibility of 'Account Created' message");
        boolean isVisible = createdAccountMessage.isDisplayed();
        logger.debug("'Account Created' message visibility status: {}", isVisible);
        return isVisible;
    }
    public HomePage continueToHomePage(){
        clickDynamicElement(continueButton);
        return new HomePage(driver);
    }
}
