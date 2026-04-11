package com.automation.exercise.pages.home;

import com.automation.exercise.pages.base.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class HomePage extends BasePage {

    @FindBy(xpath = "//div[@id='slider-carousel']")
    private WebElement sliderCarousel;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

    }

    public boolean isHomePageLoaded(){
        logger.info("Checking visibility of element {}", sliderCarousel);
        boolean isDisplayed = sliderCarousel.isDisplayed();
        logger.debug("Slider carousel visibility status: {}", isDisplayed);
        return isDisplayed;
    }

}
