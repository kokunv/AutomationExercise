package com.automation.exercise.components.header;

import com.automation.exercise.components.baseComponent.BaseComponent;

import com.automation.exercise.pages.selenium.cart.CartPage;
import com.automation.exercise.pages.selenium.contactus.ContactUsPage;
import com.automation.exercise.pages.selenium.home.HomePage;
import com.automation.exercise.pages.selenium.login_signup.LoginPage;
import com.automation.exercise.pages.selenium.products.ProductsPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class HeaderComponent extends BaseComponent {

    @FindBy(className = ".logo.pull-left")
    private WebElement logo;

    @FindBy(xpath = ".//a[contains(text(),'Home')]")
    private WebElement homeLink;

    @FindBy(xpath = ".//a[contains(text(),'Products')]")
    private WebElement productsLink;

    @FindBy(xpath = ".//a[contains(text(),'Cart')]")
    private WebElement cartLink;

    @FindBy(xpath = ".//a[contains(text(),' Signup / Login')]")
    private WebElement loginLink;

    @FindBy(xpath = ".//a[contains(text(),'Contact us')]")
    private WebElement contactUsLink;


    public HeaderComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public HomePage goToHomePage() {
        logger.info("Clicking on home link in header");
        wait.waitAndClickOnElement(homeLink);
        return new HomePage(driver);
    }

    public ProductsPage goToProductsPage() {
        logger.info("Clicking on products link in header");
        wait.waitAndClickOnElement(productsLink);
        return new ProductsPage(driver);
    }

    public CartPage goToCartPage() {
        logger.info("Clicking on cart link in header");
        wait.waitAndClickOnElement(cartLink);
        return new CartPage(driver);
    }

    public LoginPage goToLoginPage() {
        logger.info("Clicking on login link in header");
        wait.waitAndClickOnElement(loginLink);
        return new LoginPage(driver);
    }

    public ContactUsPage goToContactUsPage() {
        logger.info("Clicking on contact us link in header");
        wait.waitAndClickOnElement(contactUsLink);
        return new ContactUsPage(driver);
    }

}














