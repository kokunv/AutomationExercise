package com.automation.exercise.pages.selenium.base;

import com.automation.exercise.components.footer.FooterComponent;
import com.automation.exercise.components.header.HeaderComponent;
import com.automation.exercise.components.header.HeaderLoggedUserComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class BasePage extends Base {

    protected HeaderComponent header;

    protected FooterComponent footer;

    @FindBy(xpath = "//header")
    private WebElement headerRoot;

    @FindBy(xpath = "//footer")
    private WebElement footerRoot;

    public BasePage(WebDriver driver) {
        super(driver);
        header = new HeaderComponent(driver, headerRoot);
        footer = new FooterComponent(driver, footerRoot);
        logger.debug("Initializing global components for {}", this.getClass().getSimpleName());
    }

    public HeaderLoggedUserComponent getLoggedHeader() {
        logger.info("Switching to Logged User Header context");
        return new HeaderLoggedUserComponent(driver, headerRoot);
    }





}


















