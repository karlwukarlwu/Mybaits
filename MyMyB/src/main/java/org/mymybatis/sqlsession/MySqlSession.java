package org.mymybatis.sqlsession;

import java.lang.reflect.Proxy;

/**
 * Karl Rules!
 * 2023/11/17
 * now File Encoding is UTF-8
 */
public class MySqlSession {
//    要的执行器属性
    private Executor executor = new MyExecutor();
//    要的配置属性
    private MyConfig myConfig = new MyConfig();

//    拿查询举例
    public <T> T selectOne(String statement, Object parameter) {
        return executor.query(statement, parameter);
    }


    public <T> T getMapper(Class<T> clazz) {

        //返回动态代理对象
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz},
                new MyMapperPorxy(this,clazz,myConfig));
    }
}
