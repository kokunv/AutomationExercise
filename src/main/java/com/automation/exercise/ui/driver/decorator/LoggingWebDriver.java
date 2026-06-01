package com.automation.exercise.ui.driver.decorator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.*;

import java.util.List;
import java.util.Set;

public class LoggingWebDriver implements WebDriver, TakesScreenshot, JavascriptExecutor {

    private static final Logger logger = LogManager.getLogger(LoggingWebDriver.class);
    private final WebDriver delegate;
    private final TakesScreenshot screenshotDelegate;
    private final JavascriptExecutor jsDelegate;

    public LoggingWebDriver(WebDriver delegate) {
        this.delegate = delegate;
        this.screenshotDelegate = (TakesScreenshot) delegate;
        this.jsDelegate = (JavascriptExecutor) delegate;
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        logger.info("Taking screenshot...");
        return screenshotDelegate.getScreenshotAs(target);
    }

    @Override
    public Object executeScript(String script, Object... args) {
        logger.debug("Executing JavaScript: {}", script);
        return jsDelegate.executeScript(script, args);
    }

    @Override
    public Object executeAsyncScript(String script, Object... args) {
        logger.debug("Executing Async JavaScript: {}", script);
        return jsDelegate.executeAsyncScript(script, args);
    }

    @Override
    public void get(String url) {
        logger.info("Navigating to URL: {}", url);
        delegate.get(url);
    }

    @Override
    public String getCurrentUrl() {
        String url = delegate.getCurrentUrl();
        logger.debug("Current URL is: '{}'", url);
        return url;
    }

    @Override
    public String getTitle() {
        String tittle = delegate.getTitle();
        logger.info("Tittle: {}",tittle);
        return tittle;
    }

    @Override
    public List<WebElement> findElements(By by) {
        logger.debug("Finding elements: {}", by);
        return delegate.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        logger.info("Finding element: {}", by);
        return delegate.findElement(by);
    }

    @Override
    public String getPageSource() {
        String pageSource = delegate.getPageSource();
        logger.info("Page source: {}", pageSource);
        return pageSource;
    }

    @Override
    public void close() {
        logger.info("Closing WebDriver session.");
        delegate.close();
    }

    @Override
    public void quit() {
        logger.info("Quitting WebDriver session.");
        delegate.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        Set<String> handles = delegate.getWindowHandles();
        logger.debug("Fetched all window handles. Total count: {}", handles.size());
        return handles;
    }

    @Override
    public String getWindowHandle() {
        String handle = delegate.getWindowHandle();
        logger.debug("Fetched current window handle: '{}'", handle);
        return handle;
    }

    @Override
    public TargetLocator switchTo() {
        logger.info("Switching context (frame/window/alert)...");
        return delegate.switchTo();
    }

    @Override
    public Navigation navigate() {
        logger.debug("Accessing navigation interface.");
        return delegate.navigate();
    }

    @Override
    public Options manage() {
        logger.debug("Accessing driver options/timeouts/cookies interface.");
        return delegate.manage();
    }
}
