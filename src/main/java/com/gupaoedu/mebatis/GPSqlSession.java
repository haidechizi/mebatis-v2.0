package com.gupaoedu.mebatis;


import java.lang.reflect.Proxy;

/**
 * 会话
 */
public class GPSqlSession {

    private GPConfiguration gpConfiguration;

    private GPExecutor executor;

    public GPSqlSession(GPConfiguration configuration) {
        this.gpConfiguration = configuration;
        this.executor = new GPSimpleExecutor(gpConfiguration);
    }

    public <T> T getMapper(Class<?> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new GPMappedProxy(this.gpConfiguration, this));
    }

    public <T> T selectOne(String sql, Object[] args, Class<?> clazz) {
        return this.executor.selectOne(sql, args, clazz);
    }
}
