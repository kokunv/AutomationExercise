package com.automation.exercise.driver;

import com.automation.exercise.utils.TestValueProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;
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

    public static String takeScreenshot(String testName) {
        WebDriver driver = DRIVER_THREAD.get();

        if (driver == null) {
            return null;
        }
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            logger.info("Test failed. Capturing screenshot for test: {}", testName);

            try {
                String fileName = "target/screenshot" + testName + "_" + System.currentTimeMillis() + ".png";
                FileUtils.copyFile(screenshot, new File(fileName));
                logger.info("Screenshot saved successfully: {}", fileName);
                return fileName;

            } catch (IOException e) {
                logger.error("Failed to save screenshot for test: {}. Error: {}", testName, e.getMessage());
                throw new RuntimeException("Screenshot don't save " + e.getMessage());
            }


    }


}





















