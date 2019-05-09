package com.gupaoedu.mebatis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

public class GPStatementHandler {

    private GPConfiguration configuration;

    public GPStatementHandler(GPConfiguration configuration) {
        this.configuration = configuration;
    }

    public <T> List<T> query(String sql, Object[] args,Class<?> clazz) throws Exception {
        Connection connection = this.getConnection();

        PreparedStatement ps = connection.prepareStatement(sql);

        GPParameterHandler parameterHandler = new GPParameterHandler(ps);

        parameterHandler.setParameters(args);

        return new GPResultSetHandler().handlerResult(ps.executeQuery(),clazz);

    }


    private Connection getConnection() throws Exception {

        Class.forName(this.configuration.getJdbcDriver());
        Connection connection = DriverManager.getConnection(this.configuration.getJdbcUrl(), this.configuration.getJdbcUsername(),
                this.configuration.getJdbcPassword());
        return connection;

    }
}
