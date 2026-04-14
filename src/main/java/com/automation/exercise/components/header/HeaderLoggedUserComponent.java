package com.automation.exercise.components.header;

import com.automation.exercise.components.baseComponent.BaseComponent;
import com.automation.exercise.pages.selenium.account.DeletedAccountPage;
import com.automation.exercise.pages.selenium.home.HomePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class HeaderLoggedUserComponent extends BaseComponent {

    @FindBy(xpath = ".//a[@href='/delete_account']")
    private WebElement deleteAccountButton;

    @FindBy(xpath = ".//li//a[@href='/logout']")
    private WebElement logoutButton;

    @FindBy(xpath = ".//i[@class='fa fa-user']/following-sibling::b")
    private WebElement nameLoggedUser;

    public HeaderLoggedUserComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public DeletedAccountPage deleteAccount(){
        logger.info("Action: delete account.");
        clickDynamicElement(deleteAccountButton);
        return new DeletedAccountPage(driver);
    }

    public boolean isUserLoggedName(String name){
        waitUntilElementIsVisible(nameLoggedUser);
        String loggedName = nameLoggedUser.getText().trim();
        logger.info("Verifying logged-in username. Expected: '{}', Actual: '{}'", name, loggedName);
        return name.equals(loggedName);
    }

    public boolean isUserLogged(){
        logger.debug("Checking if user is currently logged in (looking for logout button)");
        try {
            boolean isVisible = logoutButton.isDisplayed();
            logger.debug("User login status: {}", isVisible);
            return isVisible;
        } catch (Exception e) {
            logger.debug("Logout button not found - user is not logged in");
            return false;
        }
    }

    public HomePage clickLogoutGoToHomePage(){
        logger.info("Action: Clicking Logout and returning to Home Page");
        waitAndClickOnElement(logoutButton);
        return new HomePage(driver);
    }

}
