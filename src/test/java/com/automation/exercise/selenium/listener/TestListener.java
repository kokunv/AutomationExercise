package com.automation.exercise.selenium.listener;

import com.automation.exercise.utils.ScreenshotUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static final Logger logger = LogManager.getLogger(TestListener.class);

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();

        logger.error("TEST FAILED: {}", testName);

        if (result.getThrowable() != null) {
            logger.error("Reason: {}", result.getThrowable().getMessage());
        }

        String screenshotPath = ScreenshotUtil.takeScreenshot(testName);
        logger.error("Screenshot saved at: {}", screenshotPath);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        logger.info("TEST PASSED: {}", testName);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        logger.warn("TEST SKIPPED: {}", testName);
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();

        logger.info("======================================================");
        logger.info("STARTING TEST: {}", testName);
        logger.info("======================================================");
    }
}
