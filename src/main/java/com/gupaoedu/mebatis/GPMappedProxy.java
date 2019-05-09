package com.gupaoedu.mebatis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class GPMappedProxy implements InvocationHandler {

    private GPConfiguration configuration;

    private GPSqlSession sqlSession;

    public GPMappedProxy(GPConfiguration configuration, GPSqlSession sqlSession) {
        this.configuration = configuration;
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String key = method.getDeclaringClass().getName() + "." + method.getName();
        Class<?> clazz = method.getReturnType();
        String sql = this.configuration.getSql(key);
        return this.sqlSession.selectOne(sql, args, clazz);
    }
}
