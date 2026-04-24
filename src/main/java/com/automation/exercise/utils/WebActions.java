package com.automation.exercise.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class WebActions {

    protected final Logger logger = LogManager.getLogger(this.getClass());
    protected JavascriptExecutor js;
    protected Actions actions;
    private final WaitHelper waitHelper;


    public WebActions(WebDriver driver, WaitHelper waitHelper) {
        this.js = (JavascriptExecutor) driver;
        this.actions = new Actions(driver);
        this.waitHelper = waitHelper;
    }


    public void scrollToElementJs(WebElement element) {
        logger.info("Scrolling to element: {}", element);
        try {
            js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
            waitHelper.waitUntilElementIsClickable(element);
            logger.debug("Successfully scrolled to element using JS");
        } catch (Exception e) {
            logger.error("Failed to scroll to element: {}. Error: {}", element, e);
            throw new RuntimeException("Can't scroll to element " + element + e);
        }
    }

    public void clickDynamicElement(WebElement element) {
        logger.info("Clicking on dynamic element (JS): {}", element.toString());
        try {
            js.executeScript("arguments[0].click();", element);

            logger.debug("Successfully click on element using JS");
        } catch (Exception e) {
            logger.error("JS Click failed for element: {}", element, e);
            throw new RuntimeException("Can't click on element " + element, e);
        }
    }

    public void selectByVisibleText(WebElement element, String value) {
        logger.info("Selecting text '{}' in dropdown: {}", value, element.toString());
        try {
            new Select(element).selectByVisibleText(value);
        } catch (Exception e) {
            logger.error("Selection failed for value: {}", value);
            throw new RuntimeException("Selection failed " + e.getMessage());
        }

    }
}
