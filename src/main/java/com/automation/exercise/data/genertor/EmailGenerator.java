package com.automation.exercise.data.genertor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class EmailGenerator {

    private static final Logger logger = LogManager.getLogger(EmailGenerator.class);

    public static String generateEmail() {

        String email = "test_" + System.currentTimeMillis() + "@mail.com";
        logger.info("Generate email: {}", email);

        return email;
    }
}
