package com.automation.exercise.ui.driver.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FireFoxWebdriverFactory extends WebDriverFactory {

    @Override
    public WebDriver createBrowser() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }
}
