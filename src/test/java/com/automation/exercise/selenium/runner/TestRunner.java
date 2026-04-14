package com.automation.exercise.selenium.runner;

import com.automation.exercise.driver.DriverManager;
import com.automation.exercise.pages.selenium.home.HomePage;
import com.automation.exercise.utils.TestValueProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;

public class TestRunner {

    protected WebDriver driver;
    protected SoftAssert softAssert = new SoftAssert();
    protected final Logger logger = LogManager.getLogger(this.getClass());

    @BeforeMethod
    public void setUp(Method method){

        logger.info("======================================================");
        logger.info("STARTING TEST: {}", method.getName());
        logger.info("======================================================");

        driver = DriverManager.getDriver();
    }

    @AfterMethod
    public void afterEach(ITestResult result){
        String testName = result.getMethod().getMethodName();

        if (result.getStatus() == ITestResult.FAILURE) {
            logger.error("TEST FAILED: {}", testName);
            if (result.getThrowable() != null) {
                logger.error("Reason: {}", result.getThrowable().getMessage());
            }

            String screenshotPath = DriverManager.takeScreenshot(testName);

            logger.error("Screenshot saved at: {}", screenshotPath);

        } else if (result.getStatus() == ITestResult.SUCCESS) {
            logger.info("TEST PASSED: {}", testName);
        } else if (result.getStatus() == ITestResult.SKIP) {
            logger.warn("TEST SKIPPED: {}", testName);
        }

        DriverManager.closeDriver();

        logger.info("FINISHED TEST: {}", testName);
        logger.info("------------------------------------------------------");
    }

    protected HomePage loadApplication(){

        String url = TestValueProvider.get().getBaseUIUrl();
        logger.info("Navigating to Application URL: {}", url);

        try {
            WebDriver driver = DriverManager.getDriver();
            driver.get(url);
            return new HomePage(driver);
        } catch (Exception e) {
            logger.error("Failed to load application URL: {}", url);
            throw new RuntimeException("Don't load application" + e.getMessage());
        }
    }

}
