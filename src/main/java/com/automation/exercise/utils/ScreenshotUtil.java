package com.automation.exercise.utils;

import com.automation.exercise.driver.DriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtil {

    private static final Logger logger = LogManager.getLogger(ScreenshotUtil.class);

    public static String takeScreenshot(String testName) {
        WebDriver driver = DriverManager.getDriver();

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
