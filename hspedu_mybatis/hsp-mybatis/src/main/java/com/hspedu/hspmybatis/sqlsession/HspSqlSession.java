package com.hspedu.hspmybatis.sqlsession;

import java.lang.reflect.Proxy;

/**
 * @author 韩顺平
 * @version 1.0
 * HspSqlSession: 搭建Configuration（连接）和Executor之间桥梁
 * 这里有操作DB的方法-
 */
public class HspSqlSession {
    //属性
    //执行器
    private Executor executor = new HspExecutor();
    //配置
    private HspConfiguration hspConfiguration =
            new HspConfiguration();

    //编写方法SelectOne 返回一条记录-对象 [老师做了简化]
    //说明: 在原生的Mybatis中 statement 不是sql ，而是要执行的接口方法
    //这里老师是做简化
    public <T> T selectOne(String statement, Object parameter) {
        return executor.query(statement, parameter);
    }

    //SelectList - Update - Delete - Insert ..


    /**
     * 1. 返回mapper的动态代理对象
     * 2. 这里clazz 到时传入的是 MonsterMapper.class
     * 3. 返回的就是MonsterMapper接口代理对象
     * 4. 当执行接口方法时(通过代理对象调用), 根据动态代理机制会执行到HspMapperProxy-invoke
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> clazz) {

        //返回动态代理对象
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz},
                new HspMapperProxy(hspConfiguration,this,clazz));
    }
}
