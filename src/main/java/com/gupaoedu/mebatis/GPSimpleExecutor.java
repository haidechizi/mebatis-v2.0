package com.gupaoedu.mebatis;

import java.sql.PreparedStatement;
import java.util.List;

public class GPSimpleExecutor implements GPExecutor {

    private GPConfiguration configuration;

    public GPSimpleExecutor(GPConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T selectOne(String sql, Object[] args, Class<?> clazz) {
        List<T> lists = this.selectList(sql, args, clazz);
        if (lists == null || lists.size() == 0) {
            return null;
        }
        if (lists.size() == 1) {
            return lists.get(0);
        }
        throw new RuntimeException("expected one result but find two or more");
    }

    @Override
    public <T> List<T> selectList(String sql, Object[] args, Class<?> clazz) {

        GPStatementHandler statementHandler = new GPStatementHandler(configuration);
        try {
            return statementHandler.query(sql, args, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
