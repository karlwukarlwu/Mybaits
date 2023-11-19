package org.mymybatis.sqlsession;

import org.entity.Monster;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Karl Rules!
 * 2023/11/17
 * now File Encoding is UTF-8
 */

//这里类似于basicDAO 里面的方法封装不含语句的增删改查
public class MyExecutor implements Executor{
    private MyConfig myConfig = new MyConfig();
//    以查询为例
    @Override
    public <T> T query(String sql, Object parameter) {
        //得到连接Connection 这一步用到连接数据库 读取配置文件
        Connection connection = getConnection();
        //查询返回的结果集
        ResultSet set = null;
        PreparedStatement pre = null;

        try {
            pre = connection.prepareStatement(sql);
            //设置参数, 如果参数多, 可以使用数组处理. 他这里简化了 假设就一个参数
            pre.setString(1, parameter.toString());
            set = pre.executeQuery();
            //把set数据封装到对象-monster

            //老师说明: 这里老师做了简化处理
            //认为返回的结果就是一个monster记录 假设数据库就一条记录
            //完善的写法是一套反射机制.-jdbc
            Monster monster = new Monster();

            //遍历结果集, 把数据封装到monster对象
//            在循环的每一次迭代中，代码从ResultSet对象set中提取数据，并使用这些数据来设置Monster对象的属性。
            while (set.next()) {
                monster.setId(set.getInt("id"));
                monster.setName(set.getString("name"));
                monster.setEmail(set.getString("email"));
                monster.setAge(set.getInt("age"));
                monster.setGender(set.getInt("gender"));
                monster.setBirthday(set.getDate("birthday"));
                monster.setSalary(set.getDouble("salary"));
            }
            return (T) monster;

        } catch (Exception throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (set != null) {
                    set.close();
                }
                if (pre != null) {
                    pre.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
        }

        return null;
    }

    //编写方法,通过HspConfiguration对象，返回连接
    private Connection getConnection() {
        Connection connection =
                myConfig.build("my_mybatis.xml");
        return connection;
    }
}
