package com.gupaoedu.mebatis;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public interface GPExecutor {

    <T> T selectOne(String sql, Object[] args, Class<?> clazz);

    <T> List<T> selectList(String sql, Object[] args, Class<?> clazz);


}
