package com.adidas.elasticsearch.util;

import java.io.IOException;
import java.util.Properties;

public class KeyValue {

    public static String getDataProperty(String key) {
        String dataFileName = "data.properties";
        return getValueFromPropertyFile(dataFileName, key);
    }

    private static String getValueFromPropertyFile(String fileName, String key) {
        String propertyValue = "";
        Properties properties = new Properties();

        try {
            properties.load(KeyValue.class.getClassLoader().getResourceAsStream(fileName));
            propertyValue = properties.getProperty(key);
            return propertyValue;
        } catch (NullPointerException var5) {
            var5.printStackTrace();
            throw new RuntimeException("Unable to access properties file: " + fileName);
        } catch (IOException var6) {
            var6.printStackTrace();
            throw new RuntimeException("Found the properties file but could access it");
        }
    }
}
