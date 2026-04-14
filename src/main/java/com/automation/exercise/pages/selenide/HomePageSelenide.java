package com.automation.exercise.pages.selenide;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class HomePageSelenide {

    private SelenideElement sliderCarousel =
            $x("//div[@id='slider-carousel']");


    public void shouldBeOnHomePage(){

         sliderCarousel.shouldBe(visible);
    }

}
