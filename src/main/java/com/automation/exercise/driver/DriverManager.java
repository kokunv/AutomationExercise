package com.automation.exercise.driver;

import com.automation.exercise.utils.TestValueProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Optional;

public class DriverManager {

    private static final Logger logger = LogManager.getLogger(DriverManager.class);
    private static final ThreadLocal<WebDriver> DRIVER_THREAD = new ThreadLocal<>();
    private static final String EDGE_DRIVER_PATH = "src/test/resources/driver/msedgedriver.exe";

    private DriverManager() {
    }

    public static WebDriver getDriver() {

        if (DRIVER_THREAD.get() == null) {

            String browserName = Optional.ofNullable(TestValueProvider.get().getBrowser())
                    .orElse("edge")
                    .toLowerCase();

            logger.info("Initializing new WebDriver instance for browser: '{}'", browserName);

            try {
                WebDriver driverInstance = createBrowser(browserName);
                DRIVER_THREAD.set(driverInstance);
                logger.debug("WebDriver instance created successfully for thread: {}", Thread.currentThread().getName());
            } catch (Exception e) {
                logger.error("Failed to initialize WebDriver for browser: {}. Error: {}", browserName, e.getMessage());
                throw new RuntimeException("WebDriver don't initialize" + e.getMessage());
            }

        }

        return DRIVER_THREAD.get();
    }

    private static WebDriver createBrowser(String browserName) {
        WebDriver driverInstance;

        switch (browserName) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driverInstance = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driverInstance = new FirefoxDriver();
                break;
            case "edge":
            default:
                System.setProperty("webdriver.edge.driver", EDGE_DRIVER_PATH);
                driverInstance = new EdgeDriver();
                break;
        }

        driverInstance.manage().window().maximize();
        logger.debug("Browser window maximized.");
        return driverInstance;
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





















