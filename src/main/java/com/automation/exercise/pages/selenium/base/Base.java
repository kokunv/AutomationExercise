package com.automation.exercise.pages.selenium.base;

import com.automation.exercise.utils.WaitHelper;
import com.automation.exercise.utils.WebActions;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


import java.time.Duration;

@Getter
public abstract class Base {

    protected final Logger logger = LogManager.getLogger(this.getClass());
    protected WebDriver driver;
    protected WaitHelper wait;
    protected WebActions actions;

    public Base(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitHelper(driver, Duration.ofSeconds(4));
        this.actions = new WebActions(driver, wait);
        PageFactory.initElements(this.driver, this);
        logger.debug("Page Object {} initialized", this.getClass().getSimpleName());
    }

}














