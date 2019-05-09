package com.gupaoedu;

import com.gupaoedu.dao.Log;
import com.gupaoedu.dao.LogDao;
import com.gupaoedu.mebatis.GPSqlSession;
import com.gupaoedu.mebatis.GPSqlSessionBuilder;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String configLocation = "sqlMap.properties";
        GPSqlSession sqlSession = new GPSqlSessionBuilder(configLocation).build();
        LogDao logDao = sqlSession.getMapper(LogDao.class);
        Log log = logDao.getLog("1");
        System.out.println(log);
    }
}
