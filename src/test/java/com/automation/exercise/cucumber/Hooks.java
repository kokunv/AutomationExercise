package com.automation.exercise.cucumber;

import com.automation.exercise.ui.utils.TestValueProvider;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;

public class Hooks {

    private SoftAssert softAssert;

    private WebDriver driver;

    private final String url = TestValueProvider.get().getBaseUIUrl();

    @Before
    public void setUp(){

        getDriver().get(url);

    }

    @After
    public void tearDown(){

        if (driver != null) {
            driver.quit();
            driver = null;
        }
        if (softAssert != null) {
            softAssert.assertAll();
        }
    }

    public WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    public SoftAssert getSoftAssert() {
        if (softAssert == null) {
            softAssert = new SoftAssert();
        }
        return softAssert;
    }


    private void initDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();
    }


}
