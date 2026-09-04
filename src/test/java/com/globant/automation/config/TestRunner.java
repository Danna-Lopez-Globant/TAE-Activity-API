package com.globant.automation.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class TestRunner {

    public static final String PROPERTIES_FILE = "src/test/resources/config.properties";
    public static final Properties PROPERTIES = new Properties();

    @Getter
    private static String baseUrl;
    @Getter
    private static String apiKey;

    @BeforeSuite(alwaysRun = true)
    public void setUpEnvironment(){
        loadProperties();
        baseUrl = getConfigVariable("url.base");
        apiKey = getConfigVariable("apikey");
        log.info("Configured base URL: {}", baseUrl);
    }

    private void loadProperties() {
        try {
            FileInputStream fileInputStream = new FileInputStream(PROPERTIES_FILE);
            PROPERTIES.load(fileInputStream);
        } catch (IOException e) {
            log.error("Error loading the properties file: {}", e.getMessage());
            throw new IllegalStateException("Could not possible load config.properties", e);
        }
    }

    private String getConfigVariable(String key) {
        return PROPERTIES.getProperty(key);
    }
}
