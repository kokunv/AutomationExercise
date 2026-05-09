package com.automation.exercise.ui.config;


import com.automation.exercise.ui.utils.TestValueProvider;
import com.codeborne.selenide.Configuration;

import java.util.Optional;

public class SelenideConfig {

    public static void setUp(){

        TestValueProvider config = TestValueProvider.get();

        Configuration.browser = config.getSelenideBrowser();
        Configuration.baseUrl = config.getSelenideUrl();
        Configuration.timeout = Long.parseLong(
                Optional.ofNullable(config.getSelenideTimeout())
                .orElse("10000"));

    }
}
