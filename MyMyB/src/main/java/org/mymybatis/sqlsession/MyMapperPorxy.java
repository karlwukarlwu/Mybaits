package org.mymybatis.sqlsession;

import org.mymybatis.config.Function;
import org.mymybatis.config.MapperBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Karl Rules!
 * 2023/11/17
 * now File Encoding is UTF-8
 * 动态代理生成Mapper对象,调用HspExecutor方法
 */
public class MyMapperPorxy implements InvocationHandler {
    private MySqlSession mySqlSession;
//    需要用这个session来调用方法
    private String mapperFile;
    private MyConfig myConfig;
//    需要用这个config来读取xml文件

    public MyMapperPorxy() {
    }

    public MyMapperPorxy(MySqlSession mySqlSession, Class clazz, MyConfig myConfig) {
//        这里主要是clazz和xml一个名字 他为了简便这样写的
        this.mySqlSession = mySqlSession;
        this.mapperFile = clazz.getSimpleName() + ".xml";
        this.myConfig = myConfig;
    }


//    这个method会调用接口的方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MapperBean mapperBean = myConfig.readMapper(this.mapperFile);
//        判断是否是xml文件对应的接口
//        getDeclaringClass 返回最初声明这个方法的地方，如果这个方法来自接口 ，就返回接口
//        如果这个方法定义在类中 就返回类
//        mapperBean.getInterfaceName()来自之前的xml配置
        if (!method.getDeclaringClass().getName().equals(mapperBean.getInterfaceName())) {
            return null;
        }
//        取出mapperBean的functions
        List<Function> functions = mapperBean.getFunctions();
        if (null != functions && 0 != functions.size()) {
            for (Function function : functions) {
//                当前要执行的方法和function.getFuncName()一样
                if(method.getName().equals(function.getFuncName())) {
//                    这里简化了，假设就一个select 实际开发可能有多个select
                    /**
                     *
                     * 老师说明:
                     * 1. 如果要执行的方法是select , 就对应执行selectOne
                     * 2. 因为老韩在HspSqlSession就写了一个 selectOne
                     * 3. 实际上HspSqlSession 应该对应不同的方法(多个方法)
                     * , 根据不同的匹配情况调用不同方法, 并且还需要进行参数解析处理, 还有比较复杂的字符串处理,拼接sql ,处理返回类型等等工作
                     * 4. 因为老韩主要是讲解mybatis 生成mapper动态代理对象, 调用方法的机制，所以我做了简化
                     */
                    if ("select".equalsIgnoreCase(function.getSqlType())) {
                        return mySqlSession.selectOne(function.getSql(), String.valueOf(args[0]));
                    }
                }
            }
        }
        return null;
    }
}
