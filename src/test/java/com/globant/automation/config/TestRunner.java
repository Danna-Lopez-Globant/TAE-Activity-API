package com.globant.automation.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Base configuration class for the PerfDog (Petstore) API test suite.
 * <p>
 * Loads environment properties before tests run and exposes
 * the base URL and API key used by Rest Assured.
 * </p>
 */
@Slf4j
public class TestRunner {

    /** Path to the configuration properties file. */
    public static final String PROPERTIES_FILE = "src/test/resources/config.properties";

    /** Properties loaded from the configuration file. */
    public static final Properties PROPERTIES = new Properties();

    /** Base URL of the Petstore API. */
    @Getter
    private static String baseUrl;

    /** Base URL of the Petstore API. */
    @Getter
    private static String apiKey;

    /**
     * Initializes the test environment by loading required properties.
     * Runs once at the start of the suite.
     */
    @BeforeSuite(alwaysRun = true)
    public void setUpEnvironment(){
        loadProperties();
        baseUrl = getConfigVariable("url.base");
        apiKey = getConfigVariable("apikey");
        log.info("Configured base URL: {}", baseUrl);
    }

    /**
     * Reads {@code config.properties} and loads its values into memory.
     */
    private void loadProperties() {
        try {
            FileInputStream fileInputStream = new FileInputStream(PROPERTIES_FILE);
            PROPERTIES.load(fileInputStream);
        } catch (IOException e) {
            log.error("Error loading the properties file: {}", e.getMessage());
            throw new IllegalStateException("Could not possible load config.properties", e);
        }
    }

    /**
     * Returns a configuration property value by key.
     *
     * @param key property key
     * @return value associated with the key, or {@code null} if missing
     */
    private String getConfigVariable(String key) {
        return PROPERTIES.getProperty(key);
    }
}
