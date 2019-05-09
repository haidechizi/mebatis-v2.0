package com.gupaoedu.mebatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GPSqlSessionBuilder {

    private static final String SQL_MAP = "sqlMap";

    private GPConfiguration configuration;

    private Properties properties;

    public GPSqlSessionBuilder(String configLocation) {
        loadConfig(configLocation);
    }

    private void loadConfig(String configLocation) {
        this.properties = new Properties();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(configLocation);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        initConfiguration();
    }

    private void initConfiguration() {
        this.configuration = new GPConfiguration();
        String sqlMap = this.properties.getProperty(SQL_MAP);
        String[] sqlMapArr = sqlMap.split(";");
        for (String str : sqlMapArr) {
            String[] arr = str.split(">");
            this.configuration.putSqlMap(arr[0], arr[1]);
        }
        this.configuration.setEnableCache(Boolean.parseBoolean(this.properties.getProperty("enableCache")));

        this.configuration.setJdbcDriver(properties.getProperty("jdbc.driver"));
        configuration.setJdbcPassword(properties.getProperty("jdbc.password"));
        configuration.setJdbcUrl(properties.getProperty("jdbc.url"));
        configuration.setJdbcUsername(properties.getProperty("jdbc.username"));
    }

    public GPSqlSession build() {
        return new GPSqlSession(this.configuration);
    }
}
