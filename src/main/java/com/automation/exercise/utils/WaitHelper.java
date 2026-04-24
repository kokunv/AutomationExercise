package com.automation.exercise.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitHelper {

    private final WebDriver driver;
    private WebDriverWait wait;
    private final Logger logger = LogManager.getLogger(this.getClass());

    public WaitHelper(WebDriver driver, Duration timeout) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, timeout);
    }

    public void waitUntilElementIsVisible(WebElement webElement){
        logger.debug("Waiting for visibility of element: {}", webElement.toString());
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public void waitUntilElementIsClickable(WebElement webElement){
        logger.debug("Waiting for clickable of element: {}", webElement.toString());
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public void waitAndClickOnElement(WebElement webElement) {
        logger.info("Waiting and clicking on element: {}", webElement.toString());
        wait.until(ExpectedConditions.elementToBeClickable(webElement)).click();
    }

    public void runWithImplicitWait(Duration duration, Runnable action){

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
