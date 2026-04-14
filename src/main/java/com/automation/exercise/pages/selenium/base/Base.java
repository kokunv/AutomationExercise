package com.automation.exercise.pages.selenium.base;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public abstract class Base {

    protected final Logger logger = LogManager.getLogger(this.getClass());
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor threadJs;
    protected Actions actions;

    public Base(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        this.threadJs = (JavascriptExecutor) driver;
        this.actions = new Actions(driver);
        PageFactory.initElements(this.driver, this);
        logger.debug("Page Object {} initialized", this.getClass().getSimpleName());
    }

    protected void scrollToElementJs(WebElement element) {
        logger.info("Scrolling to element: {}", element.toString());
        waitUntilElementIsVisible(element);
        try {
            logger.debug("Successfully scrolled to element using JS");
            threadJs.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
        } catch (Exception e) {
            logger.error("Failed to scroll to element: {}. Error: {}", element, e.getMessage());
            throw new RuntimeException("Can't scroll to element " + element + e.getMessage());
        }
        waitUntilElementIsClickable(element);
    }

    protected void clickDynamicElement(WebElement element) {
        logger.info("Clicking on dynamic element (JS): {}", element.toString());
        waitUntilElementIsVisible(element);
        try {
            logger.debug("Successfully click on element using JS");
            threadJs.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            logger.error("JS Click failed for element: {}", element);
            throw new RuntimeException("Can't click on element " + element + e.getMessage());
        }
    }

    protected void selectByVisibleText(WebElement element, String value) {
        logger.info("Selecting text '{}' in dropdown: {}", value, element.toString());
        try {
            new Select(element).selectByVisibleText(value);
        } catch (Exception e) {
            logger.error("Selection failed for value: {}", value);
            throw new RuntimeException("Selection failed " + e.getMessage());
        }

    }

    protected void waitUntilElementIsVisible(WebElement webElement){
        logger.debug("Waiting for visibility of element: {}", webElement.toString());
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    protected void waitUntilElementIsClickable(WebElement webElement){
        logger.debug("Waiting for clickable of element: {}", webElement.toString());
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    protected void waitAndClickOnElement(WebElement webElement) {
        logger.info("Waiting and clicking on element: {}", webElement.toString());
        wait.until(ExpectedConditions.elementToBeClickable(webElement)).click();
    }

    protected void runWithImplicitWait(Duration duration, Runnable action){

        logger.debug("Running action with temporary implicit wait: {}s", duration.getSeconds());
        try {
            driver.manage().timeouts().implicitlyWait(duration);
            action.run();
        } finally {
            driver.manage().timeouts().implicitlyWait(Duration.ZERO);
            logger.debug("Implicit wait restored to ZERO");
        }
    }


}














