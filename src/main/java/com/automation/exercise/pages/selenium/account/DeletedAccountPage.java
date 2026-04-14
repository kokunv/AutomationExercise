package com.automation.exercise.pages.selenium.account;

import com.automation.exercise.pages.selenium.base.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class DeletedAccountPage extends BasePage {

    @FindBy(xpath = "//h2[@data-qa='account-deleted']")
    private WebElement accountDeletedMessage;


    public DeletedAccountPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAccountDeleteMessageVisible(){
        logger.info("Verifying if 'Account Deleted' message is displayed");
        try {
            waitUntilElementIsVisible(accountDeletedMessage);
            boolean isDisplayed = accountDeletedMessage.isDisplayed();
            logger.info("Account deleted status: {}", isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            logger.error("Error while checking 'Account Deleted' message: {}", e.getMessage());
            return false;
        }
    }


}
