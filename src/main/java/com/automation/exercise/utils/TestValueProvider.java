package com.automation.exercise.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestValueProvider {

    private static volatile TestValueProvider instance;
    Properties properties;
    protected final Logger logger = LogManager.getLogger(this.getClass());

    private TestValueProvider() {

        String env = System.getProperty("env", "qa");

        String fileName = String.format("src/test/resources/config/%s.properties", env);
        logger.info("Loading properties for environment: {}", env);
        properties = new Properties();

        try (
                FileInputStream fileInputStream = new FileInputStream(fileName)) {
            properties.load(fileInputStream);
            logger.info("Successfully loaded properties from: {}", fileName);
        } catch (IOException err) {
            logger.error("Failed to read properties file: {}. Falling back to system variables.", fileName);
            throw new RuntimeException("File can't read data properties " + err.getMessage());
        }
    }

    public static TestValueProvider get(){

        if (instance == null){
            synchronized (TestValueProvider.class){
                if (instance == null){
                    instance = new TestValueProvider();
                }
            }
        }
        return instance;
    }

    private String getProperty(String key) {

        logger.info("Property start load from {}",key);

        String value = (properties != null) ? properties.getProperty(key) : null;

        if (value == null){
            value = System.getenv(key);
            if (value != null){
                logger.debug("Property {} loaded from System",key);
            }
        }else {
            logger.debug("Property {} loaded from file", key);
        }

        if (value == null){
            logger.debug("Property {} don't loaded", key);
        }
        return value;
    }

    public String getBrowser() {
        return getProperty("browser.name");
    }

    public String getBaseUIUrl() {
        return getProperty("base.url.ui");
    }

    public String getUserPassword() {
        return getProperty("user.valid.password");
    }

    public String getUserFirstName() {
        return getProperty("user.valid.firstname");
    }

    public String getUserLastName() {
        return getProperty("user.valid.lastname");
    }

    public String getUserAddress() {
        return getProperty("user.valid.address");
    }

    public String getUserCountry() {
        return getProperty("user.valid.country");
    }

    public String getUserState() {
        return getProperty("user.valid.state");
    }

    public String getUserCity() {
        return getProperty("user.valid.city");
    }

    public String getUserZipcode() {
        return getProperty("user.valid.zipcode");
    }

    public String getUserMobile() {
        return getProperty("user.valid.mobile");
    }

    public String getUserDay() {
        return getProperty("user.valid.day");
    }

    public String getUserMonth() {
        return getProperty("user.valid.month");
    }

    public String getUserYear() {
        return getProperty("user.valid.year");
    }



}





























