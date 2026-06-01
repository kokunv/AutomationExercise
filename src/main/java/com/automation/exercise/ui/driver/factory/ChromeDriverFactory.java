package com.automation.exercise.ui.driver.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverFactory extends  WebDriverFactory {

    @Override
    public WebDriver createBrowser() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
}
