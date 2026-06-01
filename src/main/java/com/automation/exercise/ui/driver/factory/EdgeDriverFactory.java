package com.automation.exercise.ui.driver.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class EdgeDriverFactory extends WebDriverFactory {

    private static final String EDGE_DRIVER_PATH = "src/test/resources/driver/msedgedriver.exe";

    @Override
    public WebDriver createBrowser() {
        System.setProperty("webdriver.edge.driver", EDGE_DRIVER_PATH);
        return new EdgeDriver();
    }
}
