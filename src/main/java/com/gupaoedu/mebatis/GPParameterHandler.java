package com.gupaoedu.mebatis;


import java.sql.PreparedStatement;
import java.util.Date;

public class GPParameterHandler {

    private PreparedStatement preparedStatement;

    public GPParameterHandler(PreparedStatement preparedStatement) {
        this.preparedStatement = preparedStatement;
    }

    public void setParameters(Object[] parameters) throws Exception {
        int i = 1;
        for (Object param : parameters) {
            if (param instanceof Integer) {
                this.preparedStatement.setInt(i, (Integer) param);
            } else if (param instanceof String) {
                this.preparedStatement.setString(i, String.valueOf(param));
            } else if(param instanceof Long) {
                this.preparedStatement.setLong(i,(Long) param);
            } else if(param instanceof Double) {
                this.preparedStatement.setDouble(i,(Double) param);
            } else if(param instanceof Date) {
                this.preparedStatement.setDate(i, (java.sql.Date) param);
            }
            i++;
        }
    }
}
