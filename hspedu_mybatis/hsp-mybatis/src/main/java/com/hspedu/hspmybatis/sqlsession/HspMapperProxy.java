package com.hspedu.hspmybatis.sqlsession;

import com.hspedu.hspmybatis.config.Function;
import com.hspedu.hspmybatis.config.MapperBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author 韩顺平
 * @version 1.0
 * HspMapperProxy: 动态代理生成Mapper对象,调用HspExecutor方法
 */
public class HspMapperProxy implements InvocationHandler {
    //属性
    private HspSqlSession hspSqlSession;
    private String mapperFile;
    private HspConfiguration hspConfiguration;

    //构造器
    public HspMapperProxy(HspConfiguration hspConfiguration,
                          HspSqlSession hspSqlSession,
                          Class clazz) {

        this.hspConfiguration = hspConfiguration;
        this.hspSqlSession = hspSqlSession;
        this.mapperFile = clazz.getSimpleName() + ".xml";
    }


    //前面讲解spring 时，讲过动态代理机制=>老师提示, 如果小伙伴忘记了动态代理知识
    //去spring的笔记和代码回顾
    //老师提示：当执行Mapper接口的代理对象方法时，会执行到invoke方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        MapperBean mapperBean =
                hspConfiguration.readMapper(this.mapperFile);

        //判断是否是xml文件对应的接口
        if (!method.getDeclaringClass().getName().equals(mapperBean.getInterfaceName())) {
            return null;
        }

        //取出mapperBean的functions
        List<Function> functions = mapperBean.getFunctions();
        //判断当前mapperBean解析对应MappperXML后 , 有方法
        if (null != functions && 0 != functions.size()) {

            for (Function function : functions) {
                //当前要执行的方法和function.getFuncName()一样
                //说明我们可以从当前遍历的function对象中,取出相应的信息sql, 并执行方法
                if(method.getName().equals(function.getFuncName())) {
                    //如果我们当前的function 要执行的sqlType是select
                    //我们就去执行selectOne
                    /**
                     *
                     * 老师说明:
                     * 1. 如果要执行的方法是select , 就对应执行selectOne
                     * 2. 因为老韩在HspSqlSession就写了一个 selectOne
                     * 3. 实际上HspSqlSession 应该对应不同的方法(多个方法)
                     * , 根据不同的匹配情况调用不同方法, 并且还需要进行参数解析处理, 还有比较复杂的字符串处理,拼接sql ,处理返回类型等等工作
                     * 4. 因为老韩主要是讲解mybatis 生成mapper动态代理对象, 调用方法的机制，所以我做了简化
                     */
                    if("select".equalsIgnoreCase(function.getSqlType())) {
                        return hspSqlSession.selectOne(function.getSql(),String.valueOf(args[0]));
                    }
                }
            }
        }

        return null;
    }


}
