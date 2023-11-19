package com.hspedu.hspmybatis.sqlsession;

/**
 * @author 韩顺平
 * @version 1.0
 * HspSessionFactory 会话工厂-返回会话HspSqlSession
 */
public class HspSessionFactory {

    public static HspSqlSession openSession() {
        return new HspSqlSession();
    }
}
