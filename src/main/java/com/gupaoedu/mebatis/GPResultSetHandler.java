package com.gupaoedu.mebatis;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GPResultSetHandler {

    public <T> List<T> handlerResult(ResultSet resultSet, Class<?> clazz) throws Exception {
        List<T> list = new ArrayList<>();

        ResultSetMetaData md = resultSet.getMetaData();
        int count = md.getColumnCount();
        while (resultSet.next()) {
            Object instance = clazz.newInstance();
            mapRow(resultSet, instance, md, count, clazz);
            list.add((T) instance);
        }
        return list;
    }

    private void mapRow(ResultSet resultSet, Object instance, ResultSetMetaData md, int count, Class<?> clazz) throws Exception {
        for (int i = 1; i <= count; i++) {
            String columnName = md.getColumnName(i);
            Object value = resultSet.getObject(i);

            setValue(columnName, value, instance, clazz);

        }

    }

    private void setValue(String columnName, Object value, Object instance, Class<?> clazz) throws Exception {
        columnName = underLineToHump(columnName);
        Field field = clazz.getDeclaredField(columnName);
        field.setAccessible(true);
        field.set(instance, value);
    }


    /**
     * 将下划线转成驼峰式命名
     *
     * @param columnName
     * @return
     */
    /**
     * 将下划线转成驼峰式命名
     *
     * @param columnName
     * @return
     */
    private static String underLineToHump(String columnName) {
        // 将字段转成小写
        columnName = columnName.toLowerCase();

        String regex = "_[a-zA-Z]";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(columnName);
        while (matcher.find()) {
            String str = matcher.group();
            String replaceStr = str.replaceAll("_", "");
            replaceStr = replaceStr.toUpperCase();
            columnName = columnName.replace(str, replaceStr);
            matcher = pattern.matcher(columnName);
        }
        return columnName;
    }

    public static void main(String[] args) {
        String columnName = "create_date";
        columnName = underLineToHump(columnName);
        System.out.println(columnName);

    }
}
