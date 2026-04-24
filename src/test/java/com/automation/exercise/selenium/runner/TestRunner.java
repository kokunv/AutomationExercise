package com.automation.exercise.selenium.runner;

import com.automation.exercise.driver.DriverManager;
import com.automation.exercise.pages.selenium.home.HomePage;
import com.automation.exercise.selenium.listener.TestListener;
import com.automation.exercise.utils.TestValueProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

@Listeners(TestListener.class)
public class TestRunner {

    protected WebDriver driver;
    protected SoftAssert softAssert;
    protected final Logger logger = LogManager.getLogger(this.getClass());

    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        driver = DriverManager.getDriver();
        softAssert = new SoftAssert();
    }

    @AfterMethod(alwaysRun = true)
    public void afterEach(){

        softAssert.assertAll();
        DriverManager.closeDriver();

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
