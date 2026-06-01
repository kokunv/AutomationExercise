package com.automation.exercise.ui.driver;

import com.automation.exercise.ui.driver.decorator.LoggingWebDriver;
import com.automation.exercise.ui.driver.factory.ChromeDriverFactory;
import com.automation.exercise.ui.driver.factory.EdgeDriverFactory;
import com.automation.exercise.ui.driver.factory.FireFoxWebdriverFactory;
import com.automation.exercise.ui.driver.factory.WebDriverFactory;
import com.automation.exercise.ui.utils.TestValueProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.Map;
import java.util.Optional;

public class DriverManager {

    private static final Logger logger = LogManager.getLogger(DriverManager.class);
    private static final ThreadLocal<WebDriver> DRIVER_THREAD = new ThreadLocal<>();

    private DriverManager() {
    }

    private static final Map<String, WebDriverFactory> FACTORY_MAP = Map.of(
            "edge", new EdgeDriverFactory(),
            "chrome", new ChromeDriverFactory(),
            "firefox", new FireFoxWebdriverFactory()
    );

    public static WebDriver getDriver() {

        if (DRIVER_THREAD.get() == null) {

            String browserName = Optional.ofNullable(TestValueProvider.get().getBrowser())
                    .orElse("edge")
                    .toLowerCase();

            logger.info("Initializing new WebDriver instance for browser: '{}'", browserName);

            try {
                WebDriverFactory factory = FACTORY_MAP.getOrDefault(browserName,new EdgeDriverFactory());
                WebDriver rawDriver = factory.createBrowser();

                WebDriver decoratorDriver = new LoggingWebDriver(rawDriver);

                decoratorDriver.manage().window().maximize();
                logger.debug("Browser windows maximized");

                DRIVER_THREAD.set(decoratorDriver);
                logger.debug("WebDriver instance created successfully for thread: {}", Thread.currentThread().getName());
            } catch (Exception e) {
                logger.error("Failed to initialize WebDriver for browser: {}. Error: {}", browserName, e.getMessage());
                throw new RuntimeException("WebDriver don't initialize" + e.getMessage());
            }

        }

        return DRIVER_THREAD.get();
    }

    public static void closeDriver() {

        WebDriver driver = DRIVER_THREAD.get();

        if (driver != null) {
            logger.info("Closing WebDriver instance for thread: {}", Thread.currentThread().getName());
            driver.quit();
            DRIVER_THREAD.remove();
            logger.debug("WebDriver removed from ThreadLocal.");
        }

    }


}