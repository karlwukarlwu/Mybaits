package org.mymybatis.sqlsession;

/**
 * Karl Rules!
 * 2023/11/17
 * now File Encoding is UTF-8
 */
public class MySessionFactory {
    public static MySqlSession openSession() {
        return new MySqlSession();
    }
}
