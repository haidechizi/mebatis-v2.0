package com.gupaoedu.mebatis;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GPConfiguration {

    private Map<String, String> sqlMap = new ConcurrentHashMap<>();

    private boolean enableCache;

    private String jdbcDriver;

    private String jdbcUrl;

    private String jdbcUsername;
    private String jdbcPassword;

    private String plugin;

    public String getSql(String key) {
        return sqlMap.get(key);
    }

    public void putSqlMap(String key, String value) {
        this.sqlMap.put(key, value);
    }

    public boolean isEnableCache() {
        return enableCache;
    }

    public void setEnableCache(boolean enableCache) {
        this.enableCache = enableCache;
    }

    public String getJdbcDriver() {
        return jdbcDriver;
    }

    public void setJdbcDriver(String jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getJdbcUsername() {
        return jdbcUsername;
    }

    public void setJdbcUsername(String jdbcUsername) {
        this.jdbcUsername = jdbcUsername;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public void setJdbcPassword(String jdbcPassword) {
        this.jdbcPassword = jdbcPassword;
    }

    public String getPlugin() {
        return plugin;
    }

    public void setPlugin(String plugin) {
        this.plugin = plugin;
    }
}
