package org.mymybatis.sqlsession;

/**
 * Karl Rules!
 * 2023/11/17
 * now File Encoding is UTF-8
 */
public interface Executor {
    public <T> T query(String statement, Object parameter);

}
