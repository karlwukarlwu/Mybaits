package com.hspedu.mapper;

import com.hspedu.entity.Monster;
import com.hspedu.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * @author 韩顺平
 * @version 1.0
 * MyBatisNativeTest: 演示使用MyBatis原生API操作
 */
public class MyBatisNativeTest {

    //属性
    private SqlSession sqlSession;

    //编写方法完成初始化
    @Before
    public void init() {
        //获取到sqlSession
        sqlSession = MyBatisUtils.getSqlSession();
        //sqlSession 返回的对象是 DefaultSqlSession
        System.out.println("sqlSession--" + sqlSession.getClass());

    }

    //使用sqlSession原生的API调用我们编写的方法[了解]
    @Test
    public void myBatisNativeCrud() {

        //添加
        /**
         *
         *  @Override
         *   public int insert(String statement, Object parameter) {
         *     return update(statement, parameter);
         *   }
         *
         *   statement: 就是接口方法-完整声明
         *   parameter: 入参
         */

        Monster monster = new Monster();
        monster.setAge(100);
        monster.setBirthday(new Date());
        monster.setEmail("kate2@qq.com");
        monster.setGender(0);
        monster.setName("大象精-100");
        monster.setSalary(10000);

        int insert =
                sqlSession.insert("com.hspedu.mapper.MonsterMapper.addMonster", monster);

        System.out.println("insert---" + insert);
        //删除

        //int delete = sqlSession.delete("com.hspedu.mapper.MonsterMapper.delMonster", 11);
        //System.out.println("delete--" + delete);

        //修改

        //Monster monster = new Monster();
        //monster.setAge(20);
        //monster.setBirthday(new Date());
        //monster.setEmail("kate3@qq.com");
        //monster.setGender(1);
        //monster.setName("牛魔王-100");
        //monster.setSalary(1000);
        //monster.setId(10);//这个一定要有，如果没有就不知道修改哪个对象
        //int update =
        //        sqlSession.update("com.hspedu.mapper.MonsterMapper.updateMonster", monster);
        //System.out.println("update--" + update);

        //查询

//        List<Monster> monsters =
//                sqlSession.selectList("com.hspedu.mapper.MonsterMapper.findAllMonster");
//
//        for (Monster monster : monsters) {
//            System.out.println("monster--" + monster);
//        }

        //如果是增删改, 需要提交事务
        if(sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }

        System.out.println("操作成功...");
    }
}
